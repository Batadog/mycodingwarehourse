package Basicgrammar.day03

/**
  * 1内部类;在scala中，可以在类中定义类，称之内部类，如果要访问内部类，需要在内部类中提供一个访问的方法，方法中最少需要创建一个内部类对象
  * 2 内部类，可以像其他类的成员一样访问外部类的所有属性，包括私有的
  * 3 外部类不能访问内部类成员
  */
class OuterClass06 {
  val name: String = "xiaohong "
  def work(): String = { //定义一个普通方法
    "do study...."
  }
  //申明内部类
  class InnerClass {
    //定义属性
    var aname: String = "00"// 内部类 必须要进行初始化，否则报错
    def getInfo = {
      aname = name + "inner class" //内部类能随便访问外部类的属性和方法
      s"$name:$aname"
    }
  }
  // z在外部类中定义一个方法，用于获取内部类对象
  def getInner(): InnerClass = {
    val innerClass = new InnerClass
    innerClass.aname = "xiaohonghaon " // 外部修改内部类属性
    innerClass
  }
  object OuterClass06 {   // object 是该类的伴生对象
    def main(args: Array[String]): Unit = {
      val outerClass06 = new OuterClass06
      val outerClass0601:OuterClass06 = new OuterClass06() // : 后为数据类型
      // 获取外部类属性方法
      println(outerClass06.name)
      println(outerClass06.work())
      // 获取内部类的对象
      val innerClass = outerClass06.getInner()
      //如果要为内部类写泛型，需要使用内部类对象，内部类名
      val innerClass01: outerClass06.InnerClass = outerClass06.getInner()
      println(innerClass.aname)
      println(innerClass.getInfo)
    }
  }

}
