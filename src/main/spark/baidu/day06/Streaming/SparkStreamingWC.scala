package baidu.day06.Streaming

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
object  SparkStreamingWC {
  def main(args: Array[String]): Unit = {
    val conf =new SparkConf()
      .setMaster("local[2]")
      .setAppName("SparkStreamWC")
    val sc = new SparkContext(conf)
    val ssc =new StreamingContext(sc,Seconds(5))
    // 从netcat获取数据
    val dStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop1",6667)

    val res: DStream[(String, Int)] = dStream.flatMap(_.split(" ").map((_,1))).reduceByKey(_+_)
    res.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
