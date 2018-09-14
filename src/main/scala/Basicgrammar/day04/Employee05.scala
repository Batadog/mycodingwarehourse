package Basicgrammar.day04

import javax.print.attribute.standard.DocumentName

/**
  * scala 中的抽象类
  * 抽象类需要使用abstract来修饰
  * 1 一个类中如果有抽象的方法，那么必须使用abstract声明该类为抽象类
  * 2 在子类中覆盖抽象类的方法时，可以不使用override。
  * 3 抽象方法：只有方法名和参数列表，而不能实现的方法既是抽象方法
  * 4 抽象属性：父类中定义属性，但是没有给出具体的初始值。则该变量为抽象属性
  * 抽象属性：scala中根据自己的规则，为var/val 类型的属性生成对应的 getter()setter()
  * 但是父类中并没有该filed，子类必须覆盖该属性，并覆盖该属性的值，并且子类覆盖属性不需要使用override
  */
abstract class Employee05 {
// 定义普通属性
  var eno:Int= _
 val emarry:Boolean= true
  //定义抽象属性
  var ename :String
  val esalry :Double
  // 定义普通方法
  def emploryInfo()={
  }
  // 定义抽象方法
  def work(job:String):String
}
//子类不能实现抽象类，scala 没有实现的概念，但可以继承
class QFEmployee(eeno:Int,eename:String,eeslary:Double) extends Employee05{
  // 子类覆盖父类的val 、var的属性 不需要使用override，建议写上
  override var ename: String = eename // 构造器中的属性名不能和父类一样
  override val esalry: Double = eeslary
  eno = eeno // 给父类中的普通属性赋值
  override val emarry:Boolean = true
  // 覆盖子类中普通方法，必须加override
  override def emploryInfo(): Unit = {
    val info = s"eno=$eeno,ename=$ename,esally=$eeslary"
    println(info)
  }
  // 子类覆盖父类中的抽象方法 可以添加override 也可不加
  override def work(job: String): String = {
    val info = s"job=$job doing something"
    info
  }}
//测试
object AbstractDemo{
  def main(args: Array[String]): Unit = {
    val afemployee = new QFEmployee(110,"laoluo",12020)
    afemployee.emploryInfo()
    afemployee.work("ligui")
  }
}

