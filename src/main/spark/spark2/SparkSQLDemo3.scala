package spark2

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

/**
  * hive基本操作
  */
object SparkSQLDemo3 {
  def main(args: Array[String]): Unit = {
    val spark =SparkSession
      .builder()
      .appName("SparkSqlDemo3")
       .master("local")
      .config("spark.sql.warehouse.dir","d//spark-warehouse")
      .enableHiveSupport()  // 启用hive支持
      .getOrCreate()
  //创建hive表
    spark.sql("create table if not exists src(key int,values String)")
    //加载数据
    spark.sql(("load data local inpath 'c://kv1.txt' into table src"))
    spark.sql("select * from src").show()
    spark.sql("select count(*) from src").show()
    import spark.implicits._
    // 转换为DataSet
     val sqlHiveDF: DataFrame = spark.sql("select key,values from src where key < 10 order by key  desc ")
    val sqlHiveDS: Dataset[String] = sqlHiveDF.map {
      //匹配row
      case Row(key: Int, values: String ) => s"key:$key,value:$values"
    }

  }
}
