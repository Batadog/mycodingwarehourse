package spark2

import org.apache.spark.sql.SparkSession

/**
  * action操作
  */
object ActionOperation1 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("ActionOperation")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val employee = spark.read.json("E://data//employee.json")
    //collect
    println(employee.collect().toBuffer)
    println(employee.count())
    println("emoloyee" + employee.first())
    employee.foreach(println(_))
    println(employee.map(employee=>1).reduce(_+_))
    employee.take(3).foreach(println(_))
    spark.stop()
      }
}
