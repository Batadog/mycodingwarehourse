package baidu.day06.StreamingDemo
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

class SparkStreamingWC1 {
  def main(args: Array[String]): Unit = {
  val conf = new SparkConf()
      .setAppName("demo")
      .setMaster("local[2]")
  val sc = new SparkContext(conf)
    val scc = new StreamingContext(conf,Seconds(5))
    //val sss = new StreamingContext(conf,Milliseconds(400))//毫秒

    //获取资源
    val dStream: ReceiverInputDStream[String] = scc.socketTextStream("hadoop1",5959)
    //数据处理  ,

    val res: DStream[(String, Int)] = dStream.flatMap(_.split(" ").map((_,1))).reduceByKey(_+_)
    dStream.map(_.split(" ").map((_,1)))
  }
}
