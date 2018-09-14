package com.baixinbk.cn

import kafka.serializer.StringDecoder
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 需求：判断玩家是否在用顶药外挂
  * 判断依据：游戏中规定每秒只能吃一瓶“疗伤药”,
  *   如果玩家吃一瓶药的时间小于1秒，该玩家就属于外挂玩家
  * 思路：
  *   1、首先按照事件类型顾虑出属于吃药的数据,事件类型为11
  *   2、按照时间戳进行排序
  *   3、最后一次吃药时间减去第一次吃药时间，得到吃药的总时长
  *   4、吃药总时长 / 吃药的次数 = 吃药的平均时间    如果小于1秒，该玩家就是外挂玩家
  */
object ScanPlugins {
  def main(args: Array[String]): Unit = {
    LoggerLevels.setStreamingLogLevels()

    val conf = new SparkConf()
      .setAppName("scanplugins")
      .setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))

    val dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")

    // 设置检查点
    ssc.checkpoint("hdfs://node01:9000/cp-20180720-1")

    // 设置获取kafka数据的参数
    val Array(zkQuorum, group, topics, numThread) =
      Array("node01:2181,node02:2181,node03:2181", "group01", "gamelogs", "2")
    val topicMap = topics.split(",").map((_, numThread.toInt)).toMap
    val kafkaParam = Map[String, String](
      "zookeeper.connect" -> zkQuorum,
      "group.id" -> group,
      "auto.offset.reset" -> "smallest"
    )

    // 获取kafka的数据   从kafka获取的数据是key、value类型的，可以就是offset，value为offset对应的数据
    val dStream: ReceiverInputDStream[(String, String)] =
      KafkaUtils.createStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParam, topicMap, StorageLevel.MEMORY_AND_DISK)

    // 把数据过滤出来，key顾虑掉
    val lines: DStream[String] = dStream.map(_._2)

    // 将数据切分
    val splited: DStream[Array[String]] = lines.map(_.split("\t"))

    // 按照需求条件(事件类型为11，并且物品字段为“疗伤药”)进行数据过滤
    val fitered: DStream[Array[String]] = splited.filter(line => {
      val et = line(3) // 事件类型
      val item = line(8) // 物品
      et.equals("11") && item.equals("疗伤药")
    })

    // 把数据中无效的字段顾虑掉，只留：玩家账号、时间戳
    val userAndtime: DStream[(String, Long)] =
      fitered.map(line => (line(7), dateFormat.parse(line(12)).getTime))

    // 用窗口操作来进行分组：将相同玩家账号对应的时间戳反倒一起
    val groupedUaerAndTime: DStream[(String, Iterable[Long])] =
      userAndtime.groupByKeyAndWindow(Seconds(10), Seconds(10))

    // 为了避免误判，玩家吃药的次数大于等于5次才做记录
    val filteredGroupedUserAndTime: DStream[(String, Iterable[Long])] =
      groupedUaerAndTime.filter(_._2.size >= 5)

    // 获取每次吃药的平均时间间隔
    val itemAvgTime: DStream[(String, Long)] = filteredGroupedUserAndTime.mapValues(it => {
      val list = it.toList.sorted // 把吃药的时间进行升序排序
      val size = list.size // 吃药的次数
      val first = list(0) // 第一次吃药的时间
      val last = list(size - 1) // 最后一次吃药的时间

      (last - first) / size // 每次吃药的平均时间间隔
    })

    // 判断: 如果平均时间间隔小于1秒，就认为该玩家属于外挂玩家
    val badUser: DStream[(String, Long)] = itemAvgTime.filter(_._2 < 1000)

    badUser.print()

    badUser.foreachRDD(rdd => {
      rdd.foreachPartition(it => {
        val conn = JedisConnectionPool.getConnection
        it.foreach(x => {
          val user = x._1
          val avgTime = x._2
          val currentTime = System.currentTimeMillis()
          conn.set(user + "_" + currentTime, avgTime.toString)
        })
        conn.close()
      })
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
