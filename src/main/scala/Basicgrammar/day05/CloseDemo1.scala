package Basicgrammar.day05

/**
  * scala 闭包函数
  * 函数返回值依赖于与申明在函数外的值———闭包函数
  */
object CloseDemo1 {
  def main(args: Array[String]): Unit = {
    // 定义普通函数
    val add = (i: Int) => {
      i * 100
    }
    val aa: Int = 100
    //定义一个闭包函数，返回值依赖于外部变量
    val f = (i: Int) => {
      i * aa
    }
    println("close function=="+f(100))
  }

}
