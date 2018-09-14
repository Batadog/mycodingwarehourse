package baidu.day05

import org.apache.spark.{SparkConf, SparkContext}

// 第一种排序方式：
case class  Girl(facevalue:Int,age:Int)
object  myPredef{
  // 第一种排序方式：
  implicit val girlOrdering =new Ordering[Girl]{
    override def compare(x: Girl, y: Girl) = {
      if (x.facevalue==y.facevalue){
        y.age-x.age
      }else{
        x.facevalue-y.facevalue
      }
    }
  }
}
object CustomSort {
  def main(args: Array[String]): Unit = {
    val conf =new SparkConf().setAppName("customsort").setMaster("local" )
    val sc = new SparkContext(conf)

   val girlInfo =sc.parallelize(List(("mimi",90,31),("bingbing",80,32),("xiaohan",80,28)))
    // 第一种排序方式：
    import myPredef.girlOrdering
    val res = girlInfo.sortBy(goddess=>Girl(goddess._2,goddess._3),false) //false降序排序
     println(res.collect.toBuffer)

    // 第二种排序方式：

sc.stop()
  }
}

