package Basicgrammar.day04

import scala.util.Random

/**
  * scala 中的模式匹配，类似java中的switch...case
  *
  */
object MachDemo {
  // 默认匹配内容
  def machCase_String(  ): Unit = {
    val arr = Array("hadoop", "hive", "spark", "es")
    val element = arr(Random.nextInt(arr.length))
    element match {
      case "hadoop" => println(s"the successed mach element is $element")
      case "hive" => println(s"the successed mach element is $element")
      case "spark" => println(s"the successed mach element is $element")
      case _ => println(s"都不能匹配$element")
    }
  }
  //按照类型匹配
  def machCase_type(x: Any): Unit = { // 必须要写类型
    x match {
      case a: String => println(s"匹配上String类型，值为$a") // 类型前可以加一个随便的变量
      case a: Int => println(s"匹配上Int类型，值为$x")
      case a: Double => println(s"匹配上Double类型，值为$x")
      case _ => println(s"匹配不到，或者是其他类型，值为$x")
    }
  }
  // 按照元组匹配
  def machCase_tuple(): Unit = {
    val t = ("hive", "hadoop", 1000)
    t match {
      case ("", "hadoop", 1000) => {
        println("分支1.。。。")
      }
      case (x, "hadoop", 1000) => {
        println(s"分支2.。。。x:$x")
      }
      case (h, "1000", 1000) => { // 必须满足元组的类型
        println("分支3.。。。$h")
      }
      case _ => println(s"匹配其他类型，$t")
    }
  }
  /**
    * 匹配列表
    */
  def machCase_list(): Unit = {
    val li = List(10, -29, 40)
    li match {
      case 20 :: -29 :: Nil => {
        println("匹配列表是list（20，-30）")
      }
      case x :: y :: 40 :: Nil => {
        println(s"匹配列表是list（$x，$y-30）") // 能匹配上
      }
      /*(case 20::tail => {
        println(s"匹配列表是list（$x，$y-30）") // 能匹配上
      }*/
      case head :+ -29 :+ 40 => println(s"匹配列表是list（head）")
      case _ => println("else ")
    }
  }
  def machCase_arry(): Unit = {
    val li = Array(10, -29, 40)
    li match {
      case Array(10,-29)=> println("匹配上的数组是Array(10,-29)")
      case Array(10,-23,x)=>println("匹配上的数组是Array(10,-23,x)")
      case  _ => println("其他")
    }
  }
  def main(args: Array[String]): Unit = {
    machCase_String( )
    machCase_type(11.1d)
    machCase_arry()
  }
}
