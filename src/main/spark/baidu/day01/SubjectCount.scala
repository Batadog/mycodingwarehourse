package baidu.day01

import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 统计用户对每个学科的各个模块访问量，top3
  */
object SubjectCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SubjectCount").setMaster("local")
    val sc = new SparkContext(conf)
    val file = sc.textFile("E://spark/sparkcoursesinfomation/spark/data/subjectaccess")

    // 切分
   val splited: RDD[(String, Int)] =
    file.map(line => {
      val fields = line.split("\t")
      val url = fields(1)
      (url, 1)
    })
    //将相同的url进行聚合，得到每个学科的访问量
    val sumed: RDD[(String, Int)] = splited.reduceByKey(_ + _)
    // 获取学科信息  并把许可字段加入到数据中
    val subjectAndUrlAndCount: RDD[(String, String, Int)] =
    sumed.map(x => {
      val url = x._1 //用户访问的某个学科的模块
      val count = x._2 // 模块访问量
      val subject = new URL(url).getHost
      (subject, url, count)
    })
    // 按照学科信息进行分组
    val grouped: RDD[(String, Iterable[(String, String, Int)])] = subjectAndUrlAndCount.groupBy(_._1)
    val sorted: RDD[(String, List[(String, String, Int)])] = grouped.mapValues(_.toList.sortBy(_._3).reverse)
    // 获取组内top3
    val res: RDD[(String, List[(String, String, Int)])] = sorted.mapValues(_.take(3))
    println("top3=="+res.collect.toBuffer)
  }
}