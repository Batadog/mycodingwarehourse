package baidu.day06.StreamingDemo

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object  SparkStreamingACCWC{
  def main(args: Array[String]): Unit = {
  def Sparkconf(name:String) ={
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName(name)
    val sc =new SparkContext(conf)
    val scc =new StreamingContext(conf,Seconds(5))
    scc
  }//配置name
    val ssc =Sparkconf("SparkStreamingAccWc")
    //设置检查点
     ssc.checkpoint("hdfs://hadoop1:9000/cp20180710-1")
    //获取数据
    val dStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop1",6666)
    //数据分析
    val tuples: DStream[(String, Int)] = dStream.flatMap(_.split(" ").map((_,1))).reduceByKey(_+_)
    // 调用updateStateBykey进行聚合
    val res: DStream[(String, Int)] = tuples.updateStateByKey(func,new HashPartitioner(ssc.sparkContext.defaultParallelism),true)
    res.print()
    ssc.start()
    ssc.awaitTermination()
  }
  // it: Iterator[(String, Seq[Int], Option[Int])]
  // 其中String代表元组中的每个单词，也就是key
  // Seq[Int]代表当前批次相同单词的value出现的次数：Seq(1,1,1,1)
  // Option[Int]代表上一批次累加的结果，有可能有值也有可能没有值，
  // 所以要用Option，所以在取值的时候需要用getOrElse
  val func = (it:Iterator[(String, Seq[Int], Option[Int])])=>{
    it.map(x=>{
      (x._1,x._2.sum + x._3.getOrElse(0))
    })
  }
}

 
