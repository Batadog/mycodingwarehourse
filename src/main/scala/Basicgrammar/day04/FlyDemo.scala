package Basicgrammar.day04

/**
  * Trait :特质
  * 1 scala中没有接口，只有特质，也就是可以用trait当成接口
  * 2，在trait中可以定义抽象方法和抽象属性，与抽象类中抽象方法和属性一样
  * 3 普通的类可以继承trait ，使用关键字extends scala 中没有implement 的概念
  * 4 继承trait后必须实现该特质中抽象方法，实现时可以不需要override 关键字
  * 5 scala 中，不支持继承多各类，但支持继承多个trait，使用with
  * ex: class A extends classB  with TD1 with TD2 with TD3
  * 6 特质需要使用关键字trait来修饰
  * 7 继承抽象类或者 trait，就必须实现其抽象的方法和属性
  */
trait Fly   {
  //抽象属性
  var flyTime:Int
  // 普通
  val flyNums:Int =2
  //抽象方法
  def flyWith(what:String)
  // 普通方法
  def moveAble(ty:String):Unit={
  }
}
// 定义一个特质
trait VoiceType{
  //抽象方法
  def  voiceType(voice:String)
}
//定义一个抽象类
abstract  class  Animal{
  // 抽象属性
  var ty:String
  var like:String
  // 抽象方法
  def eatWhat(what:String)
  // 普通方法
  def doWhat(what:String)={
  }
}
// 定义一个子类
class  AnimalTest(aty:String,aflyTime:Int)extends Animal with Fly
with VoiceType{
// 子类覆盖父类中的抽象方法和属性
  override var ty: String = aty
  override var flyTime: Int = aflyTime
  override var like: String = _
def this(aty:String,aflyTime:Int,lk:String){
this(aty,aflyTime)
  this.like=lk  // 不要写this
}
  def flyWith(what:String):Unit={
    println(s"the anmila type is $ty,and fly with $what")
  }
  override def eatWhat(what: String): Unit ={
  println(s"eat some thing $what")
  }
  override def voiceType(voice: String): Unit = {
 println(s"vice is ...so $voice")
  }
  //覆盖父类中的普通方法
  override def doWhat(what: String): Unit = {
    println(s"the anmial type is $ty,so can do $what")
  }
}
//测试
object TraitDemo{
  def main(args: Array[String]): Unit = {
    val bird = new AnimalTest("猪",666)
    bird.like="睡觉"
    bird.eatWhat("草")
    bird.doWhat("拱白菜")
    bird.flyWith("三十六变")
    bird.voiceType("哼哼")
    val human = new AnimalTest("良民",8000,"吃喝")
    human.flyWith("飞机")
    human.voiceType("叽叽歪歪")
    human.doWhat("扯犊子")
    human.eatWhat("黑木耳")
  }
}
