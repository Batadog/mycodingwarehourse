package Basicgrammar.day05

/**
  * scala 隐式参数：
  * 调用隐式参数的方法时，不传递参数，默认使用引入
  * 隐式区域的相同类型的隐式参数
  */
// 声明一个隐式参数的可见区域（区域就是object，或者是class）
object ImplicationDemo06 {
  //在implictDemo06 隐式区域里面申明一个Int类型的隐式参数
implicit val age:Int=16
  // 在同一个隐式区域内，不能申明两个及以上相同数据类型的隐式参数，否则会出现歧义
 // implicit  val sex:Int=1
  //但是可以在同一隐式区域内申明多个不同类型的隐式参数
  implicit  val name:String="laowang"
}
object ImplictDemo02{
  implicit  val age1:Double=1
}
//测试
object ImplictDemo{
  def getName(name:String):Unit={
    println(s"name=$name")
  }
  //申明一个带隐式参数的方法
  def getAge(implicit  age:Int):Unit={
    println(s"age =$age")
  }
  def getAge1(implicit  age:Double):Unit={
    println(s"age =$age")
  }
  //带隐式参数的方法
  def getLaoWang(implicit  name:String):Unit={
    println(s"may name is $name")
  }
  def main(args: Array[String]): Unit = {
    getName("laowang")// 调用普通方法
    getAge(18)//调用隐式参数的方法，并传递值
    //调用隐式方法并使用隐式区域中的隐式参数做为默认值
    import  ImplicationDemo06._ //引入隐式区域
     getAge
    import  ImplictDemo02._   // 同时引入时两个或多个引入类型不要一样
    getAge1
  }
}

