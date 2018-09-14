package Basicgrammar.day03

/**
  * 伴生对象和伴生类：
  * 1、在scala中，于类名相同且使用object修饰的对象叫伴生对象(对象名和类名相同)，类就叫伴生对象的伴生类。
  * 2、伴生类和伴生对象必须在一个.scala文件中
  * 3、类和伴生对象之间可以相互访问私有方法和属性
  */
class AccountDemo08(age:Int){
   val name:String = "老大"
  // 定义一个私有属性
  private var sex:String ="male"
  def getAccount():Unit={
    println(s"sacount name is $name,sex is $sex")
  }
}
//定义伴生对象
object AccountDemo08{
  val na = "伴生对象"
  def getInfo():Unit= {
    val acc = new AccountDemo08(18)
    println(acc.sex) // 私有属性可取
     acc.getAccount()
  }
}
//定义一个非伴生对象类测试
object AccountDemo08_01{
  def main(args: Array[String]): Unit = {
    //scala中没有static类型，但可以使用object来实现。object中的属性和方法都是静态的。可以直接使用object名.属性或者方法
    println(AccountDemo08.na)
    AccountDemo08.getInfo()
  }
}

