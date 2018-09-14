package spark2

import org.apache.spark.sql.{DataFrame, SparkSession}

/**日期函数：current_date，current_timestamp，
  * 数学函数：round 保留几位小数
  * 随机函数：rand
  * 字符串函数：concat_ws、concat
  */
object OtherFunction {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("OtherFunction")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    import org.apache.spark.sql.functions._
    val employee: DataFrame = spark.read.json("E://data//employee.json")
    employee
      .select(employee("name"), current_date(), current_timestamp(),
        rand(), round(employee("salary"), 2),
        concat(employee("gender"), employee("age")),//字符串连接
        concat_ws("&",employee("gender"),employee("age"))//以其实字符为分割连接
      ).show()

spark.stop()
  }
}