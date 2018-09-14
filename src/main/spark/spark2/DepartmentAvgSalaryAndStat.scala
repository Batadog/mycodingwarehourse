package spark2

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 计算部门的平均薪资和年龄
  * 需求;
  * 1、只统计年龄在20岁以上的员工
  * 2、根据部门名称和员工性别为粒度来进行统计
  * 3、统计出每个部门分性别的平均薪资和平均年龄
  */
object DepartmentAvgSalaryAndStat {
  def main(args: Array[String]): Unit = {
    val spark =SparkSession
      .builder()
      .appName("Department")
      .master("local")
      .getOrCreate()
    //导入saprk隐式转换
    import spark.implicits._
    //导入spark sql的functions
    import org.apache.spark.sql.functions._
    val employee: DataFrame = spark.read.json("E://data//employee.json")
    val department: DataFrame = spark.read.json("E:data//data//department.json")
    // 分析
    employee
      .filter("age>20")
      .join(department,$"depId"=== $"id")
      .groupBy(department("name"),employee("gender"))
      .agg(avg(employee("salary")),avg(employee("age")))
      .show()
      spark.stop()
  }
}
