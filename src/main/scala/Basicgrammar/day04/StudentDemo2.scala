package Basicgrammar.day04

import com.sun.jmx.snmp.ServiceName

/**
  * 自定义实现apply 方式来获取对象
  */
class StudentDemo2 {
  var arr=Array(1,2,3)//
  //使用_ 来进行类属性的初始化
  // 使用 _ 来初始化类的属性，必须使用var 来修饰且变量必须指定类型
  var sno:Int=  _
  var sname:String = _
  var ssex:String = _
  def this(sno:Int,sname:String){
    this()
    this.sno=sno
    this.sname=sname
  }// 定义两个辅助构造器
  def  this(sno:Int,sname:String,ssex:String){
    this(sno,sname)//调用第一个辅助构造器
    this.ssex=ssex
  }
  override def toString: String = {
    s"sno=$sno,sname=$sname,ssex=$ssex"
  }
}
object StudentDemo2{
  //定义无参的apply方法，实例化的时候就可以使用StudentDemo2获取对象
  // 无参的apply 方法返回类型为StudentDemo2  的对象
  def apply:StudentDemo2=new StudentDemo2
  // 定义一个有参数的apply
  def apply(sno:Int,sname:String)= new StudentDemo2(sno,sname)
  //定义一个有参数的方法
  def apply(sno:Int,sname:String,ssex:String)= new StudentDemo2(sno,sname,ssex)
  // 定义变长参数
  // def apply(em:String*)= new StudentDemo2() 变长不能用
  //测试自定义实现apply方法是否可以获取对象
  def main(args: Array[String]): Unit = {
    // 使用new 的 方式获取
    val stu1 = new StudentDemo2(1,"lili")
    val stu2 = new StudentDemo2(1,"liqing","男")
    println(stu1)
    println(stu2)
    //使用自定义的apply方式获取
    val stu3 =StudentDemo2(3,"ww")
    val stu4 = apply(4,"xxoo","ohohoh")
    println("stu3=="+stu3)
    println("stu4=="+stu4)
  }

}