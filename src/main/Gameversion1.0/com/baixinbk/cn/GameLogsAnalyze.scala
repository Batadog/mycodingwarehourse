package com.baixinbk.cn

import java.text.SimpleDateFormat

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark._

object GameLogsAnalyze {
  def main(args: Array[String]): Unit = {
    // 模板代码
    val conf = new SparkConf()
      .setAppName("gamelogsanalyze")
      .setMaster("local[2]")
      .set("es.nodes", "hadoop1,hadoop2,hadoop3")
      .set("es.port", "9200")
      .set("es.index.auto.create", "true")

    val sc = new SparkContext(conf)

    val queryTime = "2016-02-01 00:00:00"
    val startTime: Long = TimeUtils(queryTime)
    val endTime: Long = TimeUtils.updateCalendar(+1)
    val startTimeForMorrow = TimeUtils.updateCalendar(+1)
    val endTimeForMorrow = TimeUtils.updateCalendar(+2)


    // 查询条件
    val query =
      """
        {"query":{"match_all":{}}}
      """.stripMargin

    // 获取数据   返回类型中：String代表_id,map代表id对应的那行数据
    val queryRDD: RDD[(String, collection.Map[String, AnyRef])] =
      sc.esRDD("gamelogs1", query)

    // 把id过滤掉，其中String代表字段名称，AnyRef代表字段对应的值
    val valueRDD: RDD[collection.Map[String, AnyRef]] = queryRDD.map(_._2)

    // 把时间字段来进行过滤，把不符合时间规则的数据过滤掉
    val filtedRDD: RDD[collection.Map[String, AnyRef]] = valueRDD.filter(
      line => String.valueOf(line.getOrElse("current_time", "-1"))
        .substring(0, 1).equals("2"))

    // 切分数据
    val splitedRDD: RDD[Array[String]] = filtedRDD.map(line => {
      val et = String.valueOf(line.getOrElse("event_type", "-1"))
      val time = String.valueOf(line.getOrElse("current_time", "1971年1月1日,星期一,00:00:00"))
      val user = String.valueOf(line.getOrElse("user", ""))

      Array(et, time, user)
    })

    // 1、SimpleDateFormat不要在循环里调用该对象，这样会产生多个sdf对象，可能会造成oom
    // 2、SimpleDateFormat会出现线程安全问题，可以用另外一个格式化对象
    // 3、在以后的需求中，经常用到过滤的逻辑，所以最好抽取成一个工具类的方法，减少代码冗余
//    splitedRDD.filter(x => {
//      val time = x(1)
//      val sdf = new SimpleDateFormat("yyyy年MM月dd日,E,HH:mm:ss")
//      val time_long = sdf.parse(time).getTime
//
//    })

    // 日新增用户数--DNU
    val dnu: RDD[Array[String]] = splitedRDD.filter(arr => {
      FilterUtils.filterByTypeAndTime(arr, EventType.REGISTER, startTime, endTime)
    })


    // 活跃用户数--DAU
    val filterByTimeAndTypes: RDD[Array[String]] = splitedRDD.filter(arr => {
      FilterUtils.filterByTime(arr, startTime, endTime) &&
        FilterUtils.filterByTypes(arr, EventType.REGISTER, EventType.LOGIN)
    })

    // 因为在一天内会有多次登录的玩家，需要去重
    val dau: RDD[String] = filterByTimeAndTypes.map(_(2)).distinct

    // 次日留存
    // 在join的时候，数据必须是key、value才能进行join，所有需要把数据调整一下
    val dnuTup: RDD[(String, Int)] = dnu.map(arr => (arr(2), 1))
    // 第二天的登录的用户
    val day2Login: RDD[Array[String]] = splitedRDD.filter(arr => {
      FilterUtils.filterByTypeAndTime(arr, EventType.LOGIN, startTimeForMorrow, endTimeForMorrow)
    })
    // 把第二天登录的用户数据先去重再转换为key、value数据，便于join
    val day2Uname: RDD[(String, Int)] = day2Login.map(_(2)).distinct.map((_, 1))
    // 第一天新增的用户和第二天登录的用户进行join
    val morrowKeep: RDD[(String, (Int, Int))] = dnuTup.join(day2Uname)


//    println("日新增用户：" + dnu.map(_(2)).collect.toBuffer)
//    println("日新增用户数：" + dnu.count())
//    println("活跃用户数：" + dau.count())
    println("次日留存数：" + morrowKeep.count())
    // 次日留存率：morrowKeep / dnu


    sc.stop()
  }
}
