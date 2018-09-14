package spark2

import org.apache.spark.sql.SparkSession
/**
  * typed 操作，repartition，coalesce
  *
  */
object TypedOperation1 {
  def main(args: Array[String]): Unit = {
  val spark=  SparkSession.builder()
      .appName("TyepOperation01")
      .master("local[2]")
      .getOrCreate()
    import spark.implicits._
    val employee= spark.read.json("E://data//employee.json")
    //转为DS
    val employeeDS =employee.as[Employee]
   println("初始分区：\t"+employeeDS.rdd.partitions.length)
    //repartition
    val employeeDSRepartition =employeeDS.repartition(6)
    println("repartitioned\t"+employeeDSRepartition.rdd.partitions.length)
    // coalesce
    val employeeDSCoalesce =employeeDS.coalesce(1)//不发生shuffle，值的大小对分区不影响
    println("coalesce\t"+employeeDSCoalesce.rdd.partitions.length)
    spark.stop()






  }

}
