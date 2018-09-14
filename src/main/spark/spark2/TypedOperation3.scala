package spark2

import org.apache.spark.sql.SparkSession
/**
  * typed操作
  * collect_list collect_set
  */
object TypedOperation3 {

  def main(args: Array[String]): Unit = {
    val spark= SparkSession
      .builder()
      .appName("TypedOperation")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    import org.apache.spark.sql.functions._
    val employee =spark.read.json("E://data//employee.json")
    //collect_list和collect_set 都是用于将同一分组内指定字段的值串起来变成一个数组
    // 常用于行转列
    // 比如说：depId=1, employee=leo
    //        depId=1,employee=jack
    // 结果： depId=1,employee=[leo,jack]
    employee
      .groupBy(employee("depId"))
      .agg(collect_set(employee("name")),collect_list("name"))
      .collect()
      .foreach(println(_))
    spark.stop()
  }
}
