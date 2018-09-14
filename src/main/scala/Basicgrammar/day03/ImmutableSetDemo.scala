package Basicgrammar.day03

import scala.collection.immutable.HashSet


/**
  * 不可变的set
  */
object ImmutableSetDemo {
  def main(args: Array[String]): Unit = {
    // new HashSet()方式创建,不能直接new Set()  是一个特质
    val set01 = Set(1,2,3) // 直接set也可  set 去重
    println(set01)
    var set02:Set[String]= new HashSet[String]()

    // 添加元素
    set02  += "hadoop"  // 不会生成新的set
    var set03 =set02 + "spark"  //生成一个新的set
    println(set02)
    println(set03)
    // 删除元素  -=、 -
     set03 -="spark"
     set03  - "hadoop"// 会生成新的
     println(set03)
     val ss=set03.drop(1)  // 生成新表
    println("drop====="+ss )
    println(set03)
    // 多个set 追加  会生成新的set
    val set04 = set01 ++Set("hh","mm")
    println(set04)
    //遍历set
    for (i<- set04){
      println(i+" ")
    }
    println()
    set04.foreach(f=>print(f+"=="))
  }

}
