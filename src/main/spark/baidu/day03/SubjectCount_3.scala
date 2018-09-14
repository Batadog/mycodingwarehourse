package baidu.day03

import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

/**
  * 如何自定义分区器
  */
object SubjectCount_3 {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("SubjectCount_3").setMaster("local")
    val sc= new SparkContext(conf)
    // 获取数据
    val file= sc.textFile("E://spark/sparkcoursesinfomation/spark/data/subjectaccess")
  // 切分
    val splited =file.map(line=>{
      val fields= line.split("\t")

      val url =fields(1)
      (url,1)
    })
    // 聚合 得到 每个模块访问量
    val sumed = splited.reduceByKey(_+_)
    // 获取学科信息 ，并把许可信息放到数据中
    val subjectAndUrlAndCount =sumed.map(x=>{
      val url = x._1
      val count =x._2
      val subject=new URL(url).getHost
      (subject,(url,count))
    })
   // 缓存val cached =subjectAndUrlAndCount.cache()

    val cached =subjectAndUrlAndCount.cache()

    // 调用系统默认的分区器
//    val partitioned: RDD[(String, (String, Int))] = cached.partitionBy(new HashPartitioner(3))
//    partitioned.saveAsTextFile("E://spark/sparkcoursesinfomation/spark/data/subjectoutput")
// 出现数据倾斜了,hash 碰撞

    // 获取所有学科信息
    val subjects: Array[String] = cached.keys.distinct().collect()

    //调用自定义分区
    val partitioner= new Subjectpartitoner(subjects)
    // 开始分区
    val partitioned: RDD[(String, (String, Int))] = cached.partitionBy(partitioner)
    // 计算结果
    val res: RDD[(String, (String, Int))] = partitioned.mapPartitions(it => {
      it.toList.sortBy(_._2._2).reverse.take(3).iterator
    })
    res.saveAsTextFile("E://spark/sparkcoursesinfomation/spark/data/subjectoutput")
    sc.stop()
  }
}
/**
  * 自定义分区器
  */

class Subjectpartitoner(subjects:Array[String])extends  Partitioner{
  val sujectAndNum =new mutable.HashMap[String,Int]();//?

  //计数器。用来生成分区号
   var i= 0
  for (subject<- subjects){

    sujectAndNum += (subject ->i) //def -> [B](y: B): Tuple2[A, B]
    i += 1
  }
  override def numPartitions = subjects.length
  override def getPartition(key: Any) = sujectAndNum.getOrElse(key.toString,0)

}




