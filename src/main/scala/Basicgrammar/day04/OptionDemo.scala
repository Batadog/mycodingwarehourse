package Basicgrammar.day04

/**
  * scala 中的option操作
  * option ： 他点的值可能是 一个值 or  空  ，提供一个值可选（有值和无值）避免因判断null 而引起空指针
  *option[ T]  是一个类型为T的可选容器： 如果存在 Option[T] 就是一个Some[T],如果值不存在， Option[T]就是None
  * 一般和Some() 搭配使用
  */
object OptionDemo {
  def main(args: Array[String]): Unit = {
    val map = Map("hadoop"->100,"spark"->99)
    val res = map.get("spark")
    res.get
    println(res.get)
    println(res)// Some(99)  有值
    // 获取99这个值
    res match{
      case Some(x) => println(x)
      case None => println(0)
    }
    val res1= map.getOrElse("spark",0)  // getOrElse  case Some(v) => v  case None => default
    println(res1) // 99   源码中已经封装
    val  xx = res match {
      case   Some(x) => x
      case  None => println(0)
    }
     println("fjieof==="+xx)
  }


}
