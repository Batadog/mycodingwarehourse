package Basicgrammar.day05

/**
  * scala 泛型，
  * 常用于类，方法，函数中
  * 函数可以将数据类型申明为泛型，使用时根据传递的参数决定
  * 定义格式 def 方法名[t](t:T)={}  class[T]
  */
// 定义类
class TStudent{
  def getStuInfo() = "every on of you "
}
class TTeacher{
  def getTeaInfo()="your self "
}//测试
object TDmo03{
  //获取学生信息
  def showStuInfo(stu:TStudent):Unit={
    println(stu.getStuInfo())
  }
  //获取l老师信息
  def showTeaInfo(tea:TTeacher):Unit={
    println(tea.getTeaInfo())
  }
  //创建泛型的方法来获取信息
  def showInfo[T](t:T):Unit= {
    if (t.getClass == classOf[TStudent]) {
      val stu = t.asInstanceOf[TStudent]
      showStuInfo(stu)
    } else if (t.getClass == classOf[TTeacher]) {
      val tea = t.asInstanceOf[TTeacher]
      showTeaInfo(tea)
    }
  }

  def main(args: Array[String]): Unit = {
    val stu=new TStudent
    val tea=new TTeacher
    showTeaInfo(tea)
    showStuInfo(stu)
    // 泛型方法调用
    showInfo(stu)
    showInfo(tea)
  }
}
