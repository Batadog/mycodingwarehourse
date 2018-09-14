package spark2

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
object StructuredWC {
  def main(args: Array[String]): Unit = {
    val spark  = SparkSession
      .builder()
      .appName("StructuredWC")
      .master("local[2]")
      .getOrCreate()
    import spark.implicits._
    //获取数据
    val lines = spark.readStream
      .format("socket")
      .option("host","hadoop1")
      .option("port",6666)
      .load()
    // 数据分析
    val words: Dataset[String] = lines.as[String].flatMap(_.split(" "))
    val wordcounts: DataFrame = words.groupBy("value").count()
    val query =wordcounts.writeStream
      .outputMode("complete")//还有updata
      .format("console")
      .start()
    query.awaitTermination()

    spark.stop()
  }
}
