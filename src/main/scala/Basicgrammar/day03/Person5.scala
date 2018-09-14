package Basicgrammar.day03
// 在scala中，一个.scala 文件可以申明多个class类
// 默认class 都是public 类型
class Person5 {
  // val 申明的类的属性为可读属性，这属性有getter（） 但是没有setter（） 方法
  val name = "老王"
  //val 申明的类的属性为可读可写属性，这属性有getter（）setter（） 可以再次赋值
  var IDCard: Long = 666L;
  // private val/var ,修饰的属性，只能被该类或者是该类的对象访问
  private val age: Int = 1;//在类的属性中使用priveate修饰，那么该属性只能在该类中访问
  private[this] var sex: Char = '女'//只能在该类中访问，对象都不能
  private[day03] var sx: Char = '男'// 只能在该类和该类的子类中 或者包和子包中访问
  private var ssex: Char = '男' //z只能在该类和伴生对象访问
}
  //定义空的类
  class  emplyment{
  }
  //  在object 对象中使用main 方法中主入口（也可以在.scala 文件中创建多个object对象
  object Person05{
    def main(args: Array[String]): Unit = {
      val person =new Person5
    //println(person.age)// 不能使用，person05 不是person5 的伴生对象
      //person.name= "xiaoming"// can not change the only getter() property
      println(person.name)
      println(person.sx)
    }
  }




