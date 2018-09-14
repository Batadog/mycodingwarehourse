package Basicgrammar.Day01

object VariableModifier {
  def main(args: Array[String]): Unit = {
    //修饰符
    /*
    var:声明变量的值可以被改变，编译后有对应的set()和get() 与java中public一样
    val：声明变量的值不可以改变，地址不能变，编译后只有get() 相当于final
    lazy：声明惰性变量，变量被调用时才实例化变量的值，变量后只能跟val
    def: 声明与val差不多 不能被改变，多用于修饰方法
     */
    var a =12
    var aa:Int =22
    var aaa:Int =333
    var aaaa=666
    lazy val aaaaa:Int=98
    def asdf=100
    print(a)
    println(aa)
    println(aaa)
    println(aaaa)
    println(aaaaa)
    val __a=123
    println(__a)
    /*
    变量使用注意：
    不能以数字开头，不能使用关键字，不能使用特殊符号* # &
    但是可以使用__
     */
    //---------------------------------------
    //表达式 if else else if
    /*
    scala 中 if 和else 中的类型一样，返回值可以确定，不一致，则不能跟确定的类型
    scala  if else 等表达式可以当成一个表达式
     */
    val age:Int =18
      val res:String =if (age==18) "成年" else "未成年"
      println(res)
    //如果没有else ，则返回值类型为Unit，等价于else()
    val name:String ="laoli"
    var res1=if(name=="laoli")"名字一樣"
    //equals
    var res2=if(name=="laoli")"名字一樣" else ()
    println(res1)
    println(res2)

    // 如果if 或者else 中，最後一句不是值，而是一個賦值語句，则返回Unit
    val sex:Int =1
    val res3=
      if (sex==2){
        val gender:String ="women"
        gender
      }else if(sex==1){
        var gender:String="man"
         gender="wowo"
      }else{
        "妖怪啊"
      }
        println(res3)
      // scala 中if else 简洁，可以当成一个表达式，且条件表达式都有类型，scala 中没有switch case
      // 可以使用if else 嵌套循环。
      /* 操作符  数学运算+ - * /  % 等价于 .+() 方法
                 关系运算  <  <=  >  >=  ! !=   ==
                 位运算 |  &
        */

  }
}
