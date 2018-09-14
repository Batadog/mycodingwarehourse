package baidu.day05

import org.apache.spark.{SparkConf, SparkContext}

class SparkSQL {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("IPSearch")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val seq1 =Seq(("1","xiaofang",18),("2","xiaoli",11),("3","mimi",23),("4","xiaofen",35))
    val rdd1 =sc.parallelize(seq1)

   // val df= rdd1.toDF("id","name","age")
  }

}
