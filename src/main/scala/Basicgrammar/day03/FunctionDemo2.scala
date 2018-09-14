package Basicgrammar.day03

/**
  * 函数：
  * 在scala中，函数式”等级较高的一类“
  * scala中的函数，可以像其他数据类型一样进行传递和操作
  */
object FunctionDemo2 {
  def main(args: Array[String]): Unit = {
    //定义一个函数。 def 、val  函数名 = 【（参数列表）】=> 函数体
    val fun1 = (x:Int,y:Int) => x+y
    val fun2 = (x:Int,y:Int)=> {
      val sum = x+y
      sum *100   // 作为返回值
    }
    // 定义一个参数的函数
    val fun3 = (a:String)=> "请输入名字："+a
    // 定义一个无参的函数
    val fun4 =() => 100  //无参 不能省略 （）
    // 定义一个函数
    val m = (old:Int,young:Int) =>  true

    // 定义方法
    def method1(old:Int,young:Int):String= {
      val age:Boolean =old >=18 && young <=18
      val res = if (age) "you age so young " else "so old "
      res
    }
    def method2(old:Int,young:Int):String={
      val age:Boolean =m(old,young)
      val res = if (age) "you age so young " else "so old "
      res
    }
    // 定义一个方法，参数列表需要传函数
    def method3(old:Int,young:Int,f:(Int,Int)=>Boolean):String={
      val age:Boolean = f(old,young)
      val res = if (age) "you age so young " else "so old "
      res
    }
    //函数调用
    println(fun3("name"))
    println(method1(12,15))
    println(method2(16,12))
    println(method3(12,12,m))
//  将方法转换成函数
    def  m2 = method1 _   // 使用特殊的下划线将方法转成函数
    println("转换" + m2(12,23),m2.getClass)//查看是否转换成函数可以控制台  =>  为函数
    println(method3(12,34,m))
    //println(method3(12,34,m2))   // 参数m2位置  m2返回的是string ，要求传入的是boolean ，如果m2返回Boolean一致，方法也可以当参数进行传递
    // 简单应用
    val line = Array("age=12&sex=1&name=zs")
    val  res = line.filter(fun =>{  // filter 过滤
      (fun != "") && (fun.contains("sex"))
    }).toBuffer.apply(0).split("&").apply(1).split("=")(0)   // 简洁切分
      println("# "+res)

  }

}
