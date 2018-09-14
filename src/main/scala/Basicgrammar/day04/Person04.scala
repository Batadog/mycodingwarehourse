package Basicgrammar.day04

class Person04 {
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
class  QFSstudent extends Person03{
  // 子类特有的属性s
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
object  ExxtendsDemo{//测试
def main(args: Array[String]): Unit = {
  val stu= new QFSstudent(100,"LAOWANG","nv","69")
  stu.getStuInfo() //子类的方法
  stu.work("didi") // 调用覆盖的父类方法
}

  /**
    * 1、使用instanceOf来判断对象是否属于指定的类或者是其子类，该方法不能用于精确判断到底是什么类
    * 2、使用asInstanceOf 转换对象，显示用判断，然后再转换对对应的对象
    */
  def  isIntance_obg01(obj:Person04)  ={
    //判断obj是否是
    if(obj.isInstanceOf[Person04]){
      val stu:QFStudent=obj.asInstanceOf[QFStudent]
      println(stu.getStuInfo())
    }else if(obj.isInstanceOf[QFSstudent]){
      val stu1:QFSstudent=obj.asInstanceOf[QFSstudent]
      println(stu1.getStuInfo())
    }
  }
  // 使用== 方式判断
  def  isInstance_obg02(obj:Person04)  ={
  if(obj.getClass==classOf[Person04]){
    val stu:QFStudent=obj.asInstanceOf[QFStudent]
    println(stu.getStuInfo())
  }else if (obj.getClass==classOf[QFSstudent]){
    val stu1:QFSstudent=obj.asInstanceOf[QFSstudent]
    println(stu1.getStuInfo())
  }}
    /* 区别：
    * obj.isinstanceOf[class]只能 判断出对象是否属于指定类及其子类的对象,不能精确获取对象的类
    * obj.getClass()  classOf[class] 可以精确获取对象的类
    */

}
