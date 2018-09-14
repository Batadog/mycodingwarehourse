package Basicgrammar.day03

/**
  * 单例 对象
  * 在scala中没有static属性和方法， 但是可以使用object 来达到目的
  * 1 使用object来存储常量和工具类，相当于java常量列
  * 2 高效共享单个不可变的实例
  * 3 单例模式
  * scala 中一个object 就是一个单例，里面定义都是静态
  * object 本身是单例而且静态，在第一次调用object的时候，会调用非方法和代码块的代码，之后调用，时
  * 就不会再执行里面的代码
  */
object UtilDemo09 {
  //将UtilDemo09 当做一个工具类
  val ip: String = "192.168.216.222"
  val url: String = s"hdfs://$ip:9000"

  def getServerConn(): Unit = {
    println(s"usl:$url")
  }
}
//测试对象
object UtilDemo09Test{
  def main(args: Array[String]): Unit = {
    val u1 = UtilDemo09 //是否能new UtilDemo09 ？？不可以
    val u2 = UtilDemo09
    //看一个 看u1 和u2的hashCode是否一样
    println(u1.hashCode())
    println(u2.hashCode())
    //获取object中的属性
    println(u1.ip)
    println(u1.getServerConn())

  }
}