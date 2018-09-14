package spark2

import java.lang

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
/**
  * 初步入门:SparkSession,DataFrame的基本操作
  */
object SparkSQLDemo1 {
  def main(args: Array[String]): Unit = {
    //模板代码
   val spark =SparkSession
      .builder()
      .appName("SqarkSQLDemo1")
      .master("local[2]")
      .getOrCreate()
    /* 实例化session之后，可以进行运行配置
    spark.conf.set("spark.sql.shuffle.partitions",6)
    spark.conf.set("spark.executor.memory",2)
    val configMap: Map[String, String] = spark.conf.getAll
      */

    /* 可以访问元数据，数据库信息和表信息
    fetch metadata data from the catalog
    spark.catalog.listDatabases.show(false)
    spark.catalog.listTables.show(false)
    */
    /*DSL 语言风格DataFram Select Language
     */
    import org.apache.spark.sql.functions._
    //create a Dataset using spark.range starting from 5 to 100, with increments of 5
    val numDS = spark.range(5, 100, 5)
    // reverse the order and display first 5 items
     numDS.orderBy(desc("id")).show(5)
    //compute descriptive stats and display them
    numDS.describe().show()
    // create a DataFrame using spark.createDataFrame from a List or Seq
    val langPercentDF = spark.createDataFrame(List(("Scala", 35), ("Python", 30), ("R", 15), ("Java", 20)))
    //rename the columns 改变列名
    val lpDF = langPercentDF.withColumnRenamed("_1", "language").withColumnRenamed("_2", "percent")
    //order the DataFrame in descending order of percentage
    lpDF.orderBy(desc("percent")).show(false)

   val jsonpath="hdfs://hadoop1:9000/data"
    val warehousePath=""
    //读取文件.构建一个untyupe弱类型的DataFrame（DataSet[row])
    // read the json file and create the dataframe
    val df: DataFrame = spark.read.json("hdfs://hadoop2:9000/data/people1.json")
    df.show()
    df.printSchema() //查询构建信息图表
    df.select("name").show()
    import spark.implicits._
    //在用表达式时，字段前需要加$ 而且需要导入import spark.implicits._
    df.select($"name",$"age"+1,$"gender").show()
   // 条件过滤
    df.filter($"age">28).show()
    df.filter(df.col("age")>23).show(false)//false 全部展示
    //分组
    df.groupBy("age").count().show()
    /**
      * SQL语句风格   structure queries Language
      */
    // 创建临时视图：
      df.createOrReplaceTempView("people")
    val sqlDF=spark.sql("select * from people ")
    //以json文件写出到hdfs，或者csv
    sqlDF.write.mode("append").json("hdfs://hadoop1:9000/data_res")
    val sqldf=spark.sql("select * from people ")
    sqldf.write.mode("append").json(jsonpath)
    spark.sql("DROP TABLE IF EXISTS zips_hive_table")
    spark.table("people").write.saveAsTable("zip_hive_table")
    val resultHiveDF =spark.sql("select name,age from zip_hive_table")
    resultHiveDF.show(10)
    sqlDF.show()
    spark.stop()

  }
}
