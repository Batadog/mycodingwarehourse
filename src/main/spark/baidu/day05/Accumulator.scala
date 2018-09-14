package baidu.day05

import org.apache.spark.{Accumulator, SparkConf, SparkContext}

object Accumulator {
  def main(args: Array[String]): Unit = {
    val conf =new SparkConf().setMaster("local[2]").setAppName("accumulator")
    val sc = new SparkContext(conf)
    val numberRDD= sc.parallelize(Array(1,2,3,4,5,6),3)
    val aggr :Accumulator[Int]=sc.accumulator(1)
    numberRDD.foreach(number=>aggr+=number*2)
    println(aggr)
    sc.stop()

  }

}
