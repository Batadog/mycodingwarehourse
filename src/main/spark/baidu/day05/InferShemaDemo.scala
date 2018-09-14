package baidu.day05

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/*
  通过反射推断schema信息

 */
object InferShemaDemo {

  def main(args: Array[String]): Unit = {

    val  conf =new SparkConf().setAppName("infershemademo:").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    //获取数据
    val linesRDD   = sc.textFile("hdfs://hadoop2:9000/person1.txt").map(_.split(","))
   // 将 RDD  和person 样例类进行关联
    val personRDD =linesRDD.map(p=>Person(p(0).toInt,p(1),p(2).toInt))
   //调用toDF方法之前需要引用隐式转换函数
    import sqlContext.implicits._
    // 将RDD 转换为DataFrame
    val personDF:DataFrame=personRDD.toDF()
    // 注册 临时表
    personDF.registerTempTable("t_person")
    // 查询1 sql风格
//    val sql ="select * from t_person where age >70 order by age desc limit 10"
//    val res =sqlContext.sql(sql)
//    res.show()

   //spark sql 风格
    val res1 =personDF.select("name","age","id").filter("age>18")
    res1.write.mode("append").json("hdfs://hadoop2:9000/tes0709_1")
    // 打印
    res1.show()

    sc.stop()
  }

}
// 将数据封装成样例类
case class  Person(id: Int, name:String,age:Int)