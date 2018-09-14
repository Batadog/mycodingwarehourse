package baidu.day01
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object SparkWordCount {
  def main(args: Array[String]): Unit = {
    /**
      * 模板代码
      */
      def sparkconf(name:String)={
        val conf:SparkConf=new SparkConf()
        conf.setMaster("local")
        conf.setAppName(name)
         val sc = new SparkContext(conf)
        sc
      }
    val conf:SparkConf= new SparkConf() //1 创建配置信息
    conf.setAppName("SparkWordCount")// 2 引用程序名称
    // local 用一个线程模拟集群运行
    // local[2]   用两个线程模拟集群运行 local[*] 本地所有空闲线程
    conf.setMaster("local[2]")        //3 设置为local模式
    val sc:SparkContext= new SparkContext(conf)//4 创建集群的入口类，也叫上下文对象 sc
    //val lines:RDD[String] =sc.textFile(args(0)) //写args，不要写死
   val lines:RDD[String] =sc.textFile("hdfs://hadoop2:9000/wc")//5 获取数据
   // println("lines 的 分区数为：“"+lines.partitions.length)
    // 将数据进行按单词切分


    val words:RDD[String]= lines.flatMap(_.split(" "))
   //将单词生成元组
    val tuples:RDD[(String,Int)]=words.map((_,1))
    //将元组进行聚合
    val sumed:RDD[(String,Int)]=tuples.reduceByKey(_+_)
    //将聚合后的结果进行降序排列 false ,
    val sorted:RDD[(String,Int)]=sumed.sortBy(_._2,false)
//   println(sorted.collect.toBuffer)
//    sorted.foreach(x=>println(x))
//    sorted.foreach(println)
  //  sorted.saveAsTextFile("hdfs://hadoop2:9000/oo")
    sc.stop()
  }
}
