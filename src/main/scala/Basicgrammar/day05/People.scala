package Basicgrammar.day05

import javax.lang.model.element.PackageElement

/**
  * 隐式函数的转换
  *
  */
class People(val name:String, val ID:String) {
}
//
class Older (val name:String,val ID:String){
  def getOlder():Unit={
    println(s"name=$name,ID=$ID")
  }
}
//小破孩
class CChildren(val name:String,val ID:String){
  def getchildren():Unit={
    println(s"name=$name,ID=$ID")
  }
}
//小年轻
class Young(val name:String,val ID:String){
  def getyoung():Unit={
    println(s"name=$name,ID=$ID")
  }
}
//军人
class Soldier(val name:String,val ID:String){
  def getsolider():Unit={
    println(s"name=$name,ID=$ID")
  }
}
//小学生
class  Student(val name:String,val ID:String){
  def getstudent():Unit={
    println(s"name=$name,ID=$ID")
  }
}
//定义隐式函数区域
object ImplicitMethod01 {
  /* implicit def people2Other(obj:Any):People={
    if (obj.getClass==classOf[Older]){
      val older =obj.asInstanceOf[Older]
      new People(older.name,older.ID)

    }else if (obj.getClass==classOf[CChildren]){
      val child =obj.asInstanceOf[CChildren]
      new People(child.name,child.ID)
    }else if(obj.getClass==classOf[Soldier]){
      val soldier=obj.asInstanceOf[Soldier]
      new People(soldier.name,soldier.ID)
    }else if(obj.getClass==classOf[Student]){
      val stu =obj.asInstanceOf[Student]
      new People(stu.name,stu.ID)
    }else if ()
  }
}*/

}