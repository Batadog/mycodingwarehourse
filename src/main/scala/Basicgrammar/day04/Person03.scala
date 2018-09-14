package Basicgrammar.day04

/**
  * scala：类的继承，和java一样scala中类可以用extends继承
  * 子类有父类属性和方法
  * 子类可以定义父类没有的属性和方法
  * 可以有效复用代码
  */
class Person03 {
  var name:String = _
  var age:Int =  _
  val sex:String ="男" // val 修饰的不能用 _ 初始化
  //私有变量
  private val life:Boolean = true
  private [this] var marry =false
  // 普通方法
  def work(job:String)={
    //do something  子类自己重写
  }
}
//QFstusdent 继承 Person03
class  QFStudent extends Person03{
  // 子类特有的属性
  var sno:String = _
  var sclass:String = _
  // 父类私有属性不能覆盖
  // 父类的val修饰的属性一定要加 override 并且也需要val修饰
   override val sex:String="男"
  def this(son:Int,sname:String,ssex:String,sclassName:String){
    this()
    this.sno=sno
    this.name=sname// 覆盖父类中var的属性
    // val this.sex=ssex  // 覆盖父类中的val 属性，需要使用val
    this.sclass=sclassName
  }
  //子类覆盖父类的普通方法
  override def work(job: String): Unit = {
    println(s"my$job is lov to study ..")
  }
  // 定义子类特有方法
  def getStuInfo():Unit={
    val info = s"the info :[son$sno,sname:$name,sex:$sex,classname:$sclass"
    println(info)
    info
  }
} // 可以在写个QFStudent的子类
  object  ExtendsDemo{//测试
  def main(args: Array[String]): Unit = {
    val stu= new QFStudent(100,"LAOWANG","nv","69")
     stu.getStuInfo() //子类的方法
    stu.work("didi") // 调用覆盖的父类方法
  }
}
