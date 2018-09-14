package Basicgrammar.day05

/**
  * scala 边界： 上边界，下边界
  * 上边界：符号   A<:TStudent, 限定传入的参数类型是 某个类的子类和类的本身 def get[T<:TStudent](t:T)={}
  * 下边界：符号   A>:TStudent, 限定传入的参数类型是 某个类的父类和类的本身def get[T>:TStudent](t:T)={}
  */
class GrandFather{
  def  getGrandFather =println("I ’m your grandfather!")
}
class  Father extends GrandFather{
  def getFather=println("I'm your father ")
}
class Children extends Father{
  def  getChildren =println("you are my son")
}
//上边界
object BoundDemo04 {
  def upBoundary[T<:Father](t:T):Unit={
    if (t.getClass==classOf[Father]){
      val fa = t.asInstanceOf[Father]
      fa.getFather
    }else if (t.getClass==classOf[Children]){
      val children=t.asInstanceOf[Children]
      children.getFather
    }
  }
    // 下边界
  def downBoundary[T>:Father](t:T):Unit={
    if (t.getClass==classOf[Father]){
      val fa = t.asInstanceOf[Father]
      fa.getFather
    }else if (t.getClass==classOf[Children]){
      val children=t.asInstanceOf[Children]
      children.getFather
    }
  }
  // 下边界
  def main(args: Array[String]): Unit = {
    val grandFather =new GrandFather
    val father      = new Father
    val children    = new Children
//    //调用下边界方法
//    downBoundary[Fathe]()//下边界：传子类和本身
//    downBoundary[Children](children)
 //  downBoundary[Children]
//    downBoundary[GrandFather](children)
//    downBoundary[GrandFather]()
//    //downBoundary[Children](grandFather)
//
//    upBoundary[Father]( father)//
//    upBoundary[]()
  }

}
