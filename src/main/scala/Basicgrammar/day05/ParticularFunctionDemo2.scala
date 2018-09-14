package Basicgrammar.day05

/**
  * scala 偏函数
  * 被包括在花括号之内，没有match来匹配的一组 case语句————偏函数
  *  ParticularFunction[A.B] 的一个实例，A：代表参数类型 B代表返回类型，用于输入模式匹配
  */
object ParticularFunctionDemo2 {
  def matchCase(name:String)= {
    name match {case "hadoop"=>1
    case "spark"=>2
    case "ai"=>3
    case  _  => "others"
    }
  }

  /**
    * 定义格式：def  名partialFunc:PartialFunction[String,Int]={}
    * @return
    */
  def  partialFunc:PartialFunction[String,Int]={
    case "hadoop"=>1
    case "spark"=>2
    case "ai"=>3
    case  _  =>  0
  }
  def main(args: Array[String]): Unit = {
    //偏函数的调用
    println(partialFunc("hadoop"))
    println(partialFunc)
  }

}
