package Basicgrammar.day02

import scala.collection.immutable

/**
  *scala 中的列表，默认scala创建不可变的列表
  */
object MapDemo {
  def main(args: Array[String]): Unit = {
    //创建一个不可变的表
    val list01:List[String] =List("hadoop","spark","python","master")
    val list02 = immutable.List(("hadoo","speak",111))

    println(list01,list02,list01.getClass)
    //添加操作  将会生成一个新的list-
    val empty =Nil //空列表
    println("list01+empty"+list01::empty)  //:: 代表从左往右连接

    val list03 = "tensflow"::list01
    val list04 =" tensflow "+:list01
    val list05 = list01.::("tensflow")//等价list03
    val list06 = list01.+:("tensflow") // 等价于list04

    println(list03,list04,list05,list06)

    //添加到list后面
    val list07 = list01 :+"sklean"
    val list08 = list01.:+("sklean")
    println(list07,list08.getClass)
    //集合和集合的拼接
    val list09 = List("tensflow","sklean","redis","kafka")
    val list10 = list01 ++ list09  //等价于list01.++list09
    println(list10)

    //指定list01 合并到list09 前面生成一个姓的列表
    val list11 =list01 ++:list09
    val list12 = list02 ::: list09

    println(list11,list12)
    /**
      * str::list   左到右
      * str+:list   左到右
      * list :+str
      * list :::list
      *
      */
      //取list的值
    println(list01.take(1))// 取出第一个
    println(list01.take(2))//取出前两个
    println(list01.take(10))// 超出界线全部都取出来
    println(list01.head)//  取出第一个元素
    println((list01.tail))// 取出除了第一个元素
    // 遍历list
    for (i<- list01){
      println(i+" ")
    }
    // val  l= new List()  //List 是一个抽象类，不能new  只能new 子类




  }
}
