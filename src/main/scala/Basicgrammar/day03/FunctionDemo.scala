package Basicgrammar.day03

/**
  * 方法的定义：
  * 定义格式：def  /val   方法名（参数列表）：[返回值]= 方法体
  */
object FunctionDemo {
  def main(args: Array[String]): Unit = {
    //定义一个方法：刹车啦中的方法可嵌套，java不可
    // 带参数的方法
    def add(x:Int,y:Int):Int = x+y
    // 多参数列表方法
    def addMultipleParams(x:Int,y:Int)(a:Int)=(x*y)*a
    // 无参的方法
    def getName:String=System.getProperty("user.name")
    //当不写返回值类型的时候，方法可以根据返回结果推断返回类型
    def getName1 = System.getProperty("user.name")
    //方法没有返回值，则返回类型为Unit
    def getNoValue:Unit ={
      1000   //虽有最后一句1000，仍返回Unit
    }
    def getNoValue1 ={
      100
      val a= 1000  // 最后一句制动判断返回值，但必须是值类型，赋值就不太友好了
    }
    // 如果方法没有指定返回类型，则默认使用方法体最后一句作为返回值
    def sqrt(x:Double)={
      val squar = x*x
      3.14* squar
    }
    //测试方法的调用
    println(add(12,12))
    println(addMultipleParams(12,33)(4))
    println(getName)
    println(getName1,getName1.getClass)
    println(getNoValue)
    println(getNoValue1)
    println(sqrt(2))
    /**注意：
      * scala 中方法不写写类型，编译器自动推断，但是递归的方法，必须写返回类型
      * scala 中方的返回值也可以使用return 来显示返回，但是不建议，非要使用，加上返回类型
      * 返回值不存在Null 而是Unit
      */
  }
}
