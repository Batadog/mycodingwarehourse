package baidu.day04

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDemo {
  def main(args: Array[String]): Unit = {

//    val conf = new SparkConf()
//      .setAppName("IPSearch")
//      .setMaster("local")
//    val sc = new SparkContext(conf)

    def sparkContext(name:String)= {
      val conf = new SparkConf().setAppName(name).setMaster("local")
      val sc = new SparkContext(conf)
      sc
    }
    val sc1=sparkContext("xx")
    val ll: RDD[String] = sc1.textFile("E://part")
    val pad: RDD[String] = ll.map(x=>x)
val ppd =ll.flatMap(x=>x)
//val  rdd =sc1.parallelize()
    println("pad=="+pad.collect().toBuffer)
println(ppd.collect().toBuffer)

    def mapTransformation(sc:SparkContext):Unit={
//      val nums =sc.parallelize(1 to 10)
//      val mapped =nums.map(item=>2*item)
//      mapped.collect.foreach(println)

       val line = sc.textFile("E://part")

         val words = line.map(_.split(","))
      println(words)
      println(words.collect().toString)

    }

    mapTransformation(sc1)



  }

}
