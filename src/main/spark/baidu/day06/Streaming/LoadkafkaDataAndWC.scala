package baidu.day06.Streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
/**
  *
  */
object LoadkafkaDataAndWC {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("SparkStreamingACCWC")
      .setMaster("local[2]")
    //  val sc = new SparkContext()  不写 就隐式了
    val ssc = new StreamingContext(conf,Milliseconds(5000))
    // 设置检查点
    ssc.checkpoint("hdfs://hadoop1:9000/cp-20180710-2")
    // 设置请求kafaka的参数
    val Array(zkQuorm,group,topics,numThreads)=args
    // 获取每个topics 放到一个map里
    val topicMap = topics.split(",").map((_,numThreads.toInt)).toMap
    // 调用kafaka工具类获取kafak集群的数据
   val data = KafkaUtils.createStream(ssc,zkQuorm,group,topicMap)
      data.print()
    // data 里的key 是topic
    val lines:DStream[String]= data.map(_._2)
    ssc.start()
    ssc.awaitTermination()






  }
}
