package Basicgrammar.day03

/**
  * scala 中枚举的实现
  * 1 枚举对象需要object修饰，不是class
  * 2 枚举对象需要继承extends Enumeration
  * 3
  */
object EnumDemo0 extends Enumeration {
  val HADOOP = Value(1, "hadoop")
  val SPARK = Value(2, "spark")
  val STORM = Value(3, "storm")
}
// 枚举测试
object EnumDemoTest {
  def main(args: Array[String]): Unit = {
    println(EnumDemo0.HADOOP)
    println(EnumDemo0.SPARK)
    println(EnumDemo0.STORM)
    println(EnumDemo0.apply(1))
    println(EnumDemo0.withName("hadoop"))
  }
}