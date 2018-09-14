package spark2

/**
  * typed操作：
  * 去重：distinct，dropDuplicateds
  * 过滤：except，filter，intersect
  */

import org.apache.spark.sql.{Dataset, SparkSession}
object  TypedOperation2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("YypedOperation2")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val employee =spark.read.json("E：//data//employee.json")
    val employeeDS =employee.as[Employee]

    val employee2= spark.read.json("E：//data//employee2.json")
    val employeeDS2 =employee2.as[Employee]

    val department =spark.read.json("E://data//department.json")
    val dempatmentDS=department.as[Department]

    // distinct 和doropDuplicate 都是用来去重的
    // distinct 是根据每一条数据，进行完整内容去重的
    // dropDuplicates 可以根据指定的字段进行去重。
    val distinctEmployeeDS =employeeDS.distinct()

    val dropDuplicatedsEmployeeDS =employeeDS.dropDuplicates("name")
    dropDuplicatedsEmployeeDS.show()

    //TODO 过滤
    employeeDS.except(employeeDS2).show()
    employeeDS.filter(employee =>employee.age > 30).show()
    employeeDS.intersect(employeeDS2).show()
    employeeDS.union(employeeDS2).show()
  // joinwith sort
    employeeDS.joinWith(dempatmentDS,$"depId"=== $"id").show()
    employeeDS.sort($"salary".desc).show()

   //randomSplit权重，讲一个RDD切分多个RDD
    //d第一个参数：权重参数，一个double数组，权重高的RDD,划分的概率大
    // 第二个为种子，可以忽略
    val DSArr: Array[Dataset[Employee]] = employeeDS.randomSplit(Array(0.2,0.8))
    DSArr.foreach(ds=>ds.show())

    //sample 按照一定的比例随机抽取数据
    // 第一个参数withReplacement是建立不同的采样器
    //false 伯努利分布（元素多次采样）true 是泊松分布
    // 第二个参数 fraction是采样比例
    // 第三个参数seed 是随机数生成的种子
    employeeDS.sample(false,0.2)
    spark.stop()
  }
}
case class Department(id: Long, name: String)