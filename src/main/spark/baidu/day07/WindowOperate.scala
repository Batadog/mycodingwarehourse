package baidu.day07

import baidu.day03.LoggerLevels
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object  WindowOperate {
  def main(args: Array[String]): Unit = {
    LoggerLevels.setStreamingLogLevels()
    val conf  =new SparkConf()
      .setAppName("window")
      .setMaster("local[2]")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.checkpoint("hdfs://hadoop1:9000/20180711-1")
    val dSTream =ssc.socketTextStream("hadoop1",6668)
    val tuples =dSTream.flatMap(_.split(" ").map((_,1)))
    val res: DStream[(String, Int)] = tuples.reduceByKeyAndWindow((x:Int,y:Int)=>x+y,Seconds(10),Seconds(10))
    res.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
