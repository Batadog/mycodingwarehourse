package spark2

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

/**
  * 基本操作
  */
object BasicOperation1 {
  def main(args: Array[String]): Unit = {
    val spark =SparkSession
      .builder()
      .appName("BasicOperation")
      .master("local")
      .getOrCreate()
    val jsonPath=args(0)
    import spark.implicits._
    // get  the JSON file

   val employee: DataFrame = spark.read.json(jsonPath)
     employee.cache()//缓存
   val employeeCached= employee.persist(StorageLevel.MEMORY_AND_DISK) //存储等级
    employeeCached.count()//统计行数
   // 写到外部数据
    employee.createOrReplaceTempView("employee")
    val employeeDF: DataFrame = spark.sql("select * from employee")
    employeeDF.write.mode("append").json(jsonPath)
    // DataSet 和DataFrame 的转换： toDF ,as
    val employeeDS: Dataset[Employee] = employee.as[Employee]
    employeeDS.show()
    employeeDS.printSchema()
    val employeeDf: DataFrame = employeeDS.toDF()
    spark.stop()
  }
}
case class Employee(name: String, age: Long, depId: Long, gender: String, salary: Double)
