package Basicgrammar.day03

/**
  * scala 中的类
  * 定义point类，主构造器带有两个参数，可以给主构造器参数设置默认值or not，有默认值后，
  * 再构造对象时，不用设置，相当于初始化
  * 类名后的参数必须有类型，
  */
class PointDemo4(var x: Int = 2, var y: Int = 4) {
  // 定义方法
  def move(dx: Int, dy: Int): Unit = {
    x = dx + x
    y = dy + y
  }
  override def toString: String = {
    s"$x,$y"
  }
}
//
object Point04 {
  def main(args: Array[String]): Unit = {
    val p1 = new PointDemo4() // 直接用类名获取对象 ，二者相等
    val p2 = new PointDemo4 // 无主构造器 ，或者主构造器中参数必须有值
    val p3 = new PointDemo4(1, 2)
    val p4 = new PointDemo4(2, 3)
    println(p1.x)
    println(p2)
    println(p3)
    println(p4)
  }
}
