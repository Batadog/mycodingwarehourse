package Basicgrammar.day03

import scala.collection.mutable.ListBuffer

/**
  * scala 中可变的list
  */
object ListBufferDemo {
  def main(args: Array[String]): Unit = {
  // 定义的两种方式，new listBuffer（）  和apply（）方式 ListBuffer
    val listBuffer01= ListBuffer[String]("hadoop","spark","stom")
    println(listBuffer01)
    // 添加元素  += /append /appendAll(List())  不会生成一个新的List 原有的list 改变
    listBuffer01 += "hbase"
    listBuffer01.append("hive","sqoop")
    listBuffer01.appendAll(List("AI","python"))
    println(listBuffer01)
    // 更新元素    增加，删除更新，都是从下标0开始
    listBuffer01.update(1,"hadoop3.0") // 1   update 是下标
    listBuffer01(2)="stom 1.3"   // 下标
    println(listBuffer01)
    //获取元素
    println(listBuffer01(1))
    println(listBuffer01.apply(2))

    //遍历
    listBuffer01 .foreach(e => println(e+""))

    //删除
    listBuffer01.remove(1)// 下标
    println("remove(1)"+listBuffer01)
    listBuffer01.remove(2,3)
    println("remove(2,3)"+listBuffer01)
    listBuffer01-= "python"
    println(listBuffer01)

    // 使用new 的方式来创建 ListBuffer（） ,(”xxx“)不能加元素
    val listBuffer02 = new ListBuffer[String](  )
    listBuffer02 += "1"
    listBuffer02.append("2,","3")
    //listBuffer02.appendAll(listBuffer01)//可以的
    listBuffer02++= listBuffer01 // 添加一个集合，不会生成新的list
    println("listBuffer02"+listBuffer02)
    var listBuffer03 = listBuffer02 ++listBuffer01  //生成新的list
    println( "listbuffer02 +++"+listBuffer03)
    listBuffer02 :+ "hello world " // 也会生成新的元素
    println(listBuffer02)

    val listBuffer04 = listBuffer02 :+ "hello world"
    println(listBuffer04)
    /**
      *注意： 1、scala 中默认定义的list 为不可变的 如果需要定义可变的 需要加mutable
      *       2、不可变关键字List  可变的关键字  ListBuffer
      *       3 不可变的只能进行list 的合并，会返回新的list，可变的可以进行增删改，不会身退功成新的list
      *      4、不可变的 不能用new   可变的能用new
      *       5 可变与不可变的list 两者之间很多方法不一样
      */


  }
}
