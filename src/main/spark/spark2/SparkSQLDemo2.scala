package spark2

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * DataSet 基本操作
  */
object SparkSQLDemo2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("SparkSQLDemo2")
      .getOrCreate()
    val jsonPath = "hdfs://hadoop1:9000/data/person1.json"

    import spark.implicits._
    // get the json file
    val df = spark.read.json(jsonPath)
    val peopleDs: Dataset[Person] = df.as[Person]
    peopleDs.write

    val personDS: Dataset[Person] = Seq(Person("xiaohong", 18, 29999)).toDS()
    personDS.show()

  }
}
case class Person(name:String,age:Long,salary:Double)

