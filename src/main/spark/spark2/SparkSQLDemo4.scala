package spark2

import org.apache.spark.sql.{DataFrame, SparkSession}

/* Create by ligui on 2018-7-14 15:42:01
* A simple  Spark 2.0 example shows use of SparkSesssion
* All very easy intuitive to use
*		/usr/local/spark-1.6.1-bin-hadoop2.6/bin/spark-submit \
*--class com.baidu.spark2.SparkSQLDemo4 \
*--master spark://hadoop1:7077 \
* // root/spark-mvn-1.0-SNAPSHOT.jar \
*/
object SparkSQLDemo4 {
  def main(args: Array[String]): Unit = {
    if(args.length!=1){
      println("Usage:SparkSessionSimpleExample < path_to_json_file")
      System.exit(1)
    }
    //get the JSON file
    val jsonFile = args(0)
    val warehouseLocation= "hdfs://hadoop1:9000/warehouse"
    //create SparkSession
    val spark = SparkSession
        .builder()
        .appName("SparkSQLDemo4")
        .config("spark.sql.warehouse.dir",warehouseLocation)
        .enableHiveSupport()
        .getOrCreate()

        // read json  file   and create  a DataFrame
    val zipsDf: DataFrame = spark.read.json(jsonFile)

    //  show the table's first 20 rows
    zipsDf.show(10,false)
    // display the total number of rows in the dataFrame
    println("Total number of zipcodes: "+ zipsDf.count())

    //filter all cities whose population >40k
    zipsDf.filter(zipsDf.col("pop")>40000).show(10,false)
    // give the cache
    zipsDf.cache()
    import spark.implicits._
    import org.apache.spark.sql.functions._
    // find the all populouss cites and zip inthe state of california  and order them by population
    zipsDf.select("city","zip","pop").filter('state === "CA").orderBy(desc("pop")).show(10,false)

    zipsDf.createOrReplaceTempView("zip_table")
    println("select city ,pop state ,zip from zip_table where pop >4000")
    val shwodd: DataFrame = spark.sql("select city ,pop state ,zip from zip_table where pop >4000")
    shwodd.show(10)

    //droop the table if exists to get
    spark.sql("drop table if exists zip_hive_table")
    spark.table("zip_hive_table").cache()
    //make a query to the hive table now
    val resultHiveDF: DataFrame = spark.sql("select city ,pop state ,zip from zip_table where pop >4000")
    resultHiveDF.show(10,false)

    spark.sql("select count(zip)," +
      "SUM(pop),city from zip_hive_table " +
      "where state=='CA' GROUP BY city " +
      "order by SUM(pop) desc "
    ).show(10,false)
 // register a simple UDF    difined  a function that belongs to you
    spark.udf.register("cityLength",(c:String)=>c.length)
    val resultsCityLens = spark.sql("SELECT city, cityLength(city) as city_length FROM hive_zips_table ORDER BY city_length DESC")
    resultsCityLens.show(10, false)
    // accessing catalog metadata using spar.catalog
    spark.catalog.listDatabases.show(false)
    //show dataebase tablenames; it should show our  table name
    spark.catalog.listTables.show(false)
spark.stop()
  }

}
