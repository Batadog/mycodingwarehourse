package Basicgrammar.day04

/**
  * scala中的柯里化
  * 柯里化： 将原来接收两个参数的函数编程一个新的值接收一个参数的函数过程
  * 新的函数将会返回一个已原有第二个参数为参数的函数
  */
object CurryDemo {
  def main(args: Array[String]): Unit = {
  // 定义一个非柯里化的方法
   def noCurrIngFunc(x:Int,y:Int):Int=x*y
  // 定义一个柯里化的函数
  def curryingFunc(x:Int)(a:Int)=x*a
  println(noCurrIngFunc(12,22))
  println(curryingFunc(12)(33))
  //柯里化定义
  val v1 = curryingFunc(10)_ // 代表任意一个参数展位符号
  println(v1)
    // 柯里化的过程实现
    def f(x:Int)=(y:Int)=>x*y
    val res = f(10)
    println(res)
    val res1 =res(1)
    println("res1"+res1)
 }
}
