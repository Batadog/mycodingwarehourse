package spark2.pricatice

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Create by ligui on 2018-7-15 19:51:02
  * A smiple Spark2.0 example show use of SparkSeediong
  * All very  easy intuitive to use
  * spark -submit\
  * --calsss com.baidu.spark2.pricatice.Demo1
  * --master Spark://hadoop1:7077
  * /file :jar
  *
  */

object Demo1 {
  def main(args: Array[String]): Unit = {
    // if you don't have input the path of json file  and The  System will exit
if (args.length!=1)
  {
    println("Usage:SparkSessionSimpleExample《path of file is not  entirely")
    System.exit(1)
  }
    //get the JSON FILE
    val jsonFile= args(0)
    val warehouseLocation="D://data//warehouse"
    // create the SparkSesison
    val spark=SparkSession
      .builder()
      .appName("Demo1")
      .master("local")
      .getOrCreate()

    // read the json file and Creat DataFrame
    val zips: DataFrame = spark.read.json(jsonFile)

    //display the total mnumber of rows in the dataframe
   println("Total number of zipcode: " +zips.count())

    // filter all cities whose population >40
    zips.filter(zips.col("pop")>4000).show(10,false)

    //give the cache
    zips.cache()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    // find the all populous cities and zip in the state of california,and order by the populous
    zips.select("city,","zip","pos").filter('state === "CA").orderBy(desc("pop")).show(10,false)

      // create   a  previsous Hive table
    zips.createOrReplaceTempView("zip_table")

    val exm1: DataFrame = spark.sql("select city ,pop ,state,zip from zip_table where pop >4000")


  spark.sql("drop table if exists zip_hive_table")
    spark.table("zip_hive_table").cache()
    val df1: DataFrame = spark.sql("select city,pop from zip_table")
    df1.createOrReplaceTempView("people2")
    spark.table("people2").write.saveAsTable("zip_hive_table")
    val resultHiveDF = spark.sql("select city from zip_hive_table")






  }

}
