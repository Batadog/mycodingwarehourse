package baidu.day05

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, types}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object StructTypeSchemaDemo {
  def main(args: Array[String]): Unit = {
    val  conf =new SparkConf().setAppName("structtypeSchemademo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 获取数据，切分
    sc.textFile("hdfs://hdfs://hadoop2:9000/person1.txt").map(_.split(","))

    // 由StructType类型指定Schema
      StructType {
      Array(
          StructField("id", IntegerType, false),
          StructField("name", StringType, true),
          StructField("age", IntegerType, true),
          StructField("fv", IntegerType, true)
      )
    }

    }
    // 映射


}
