package baidu.day06.Streaming


import baidu.day06.LoggerLevels
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object TransformDemo {
  def main(args: Array[String]): Unit = {
    LoggerLevels.setStreamingLogLevels()

    val conf = new SparkConf()
      .setAppName("TransformDemo")
      .setMaster("local[2]")
    val ssc = new StreamingContext(conf, Milliseconds(5000))

    // 获取数据
    val dStream = ssc.socketTextStream("node01", 6666)

    val res: DStream[(String, Int)] = dStream.transform(
      rdd => rdd.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_))

    res.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
