package Basicgrammar.day03

/**
  * scala 中构造器： 主构造器，辅助构造器
  * scala 中默认每个类都有主构造器，主构造器直接在类名后面
  * 默认情况下，主构造器的参数列表为空，在类名后面不需要写（） 写了也报错
  * 如果主构造器有参数，可以直接写在类名后面
  * 主构造器的参数也可以使用 private  val /var  name 修饰 如果不写，默认为var修饰
  * 同时主构造器也可以是私有，在主构造器前加关键字private来修饰
  */
class ConstructorDemo private (private val sno:Int,val sname:String) {
  val score: Int = 99
  var ssex: Int = 1
  var sclass: String = "gp1799"
  // 申明辅构造器，只能使用关键字this
  def this(sno:Int, sname:String,sclass:String ) {
    this(sno,sname) // 每一个辅助构造器的第一行都是主构造器 如果主构造器是空的，必须写this
    this.ssex=ssex
  }
  def this(sno:Int,sname:String,ssex:Int=2){
    this(sno,sname)
    this.ssex=ssex// 从第二句开始就是构造器的代码
  }
  override def toString: String = {
    s"sno=$sno,sname=$sname,ssex=$ssex,sclass=$sclass,score=$score"
  }
}
object ConstructorDemo{ //该类的伴生对象 ，里面都是static的，伴生与本类必须同一源文件
  def main(args: Array[String]): Unit = {
    val con=new ConstructorDemo(9,"ss")
    //并没有使用主构造器来实例化，而是使用辅助构造器(sno:Int,sname:String,score:Int=2)来实例化
    println(con)
  }

}