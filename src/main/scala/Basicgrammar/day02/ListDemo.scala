package Basicgrammar.day02

import scala.collection.mutable


/**
  * 映射
  */
object ListDemo {
  def main(args: Array[String]): Unit = {
    //定义一个map
    var map1: Map[String, Int] = Map("chinese" -> 99, "math" -> 99, "english" -> 100)
    val map2 = Map(("chinese", 55), ("math", 22), ("enghlish", 100))
    println(map1)
    println(map2)
    //更新  将会返回一个新的map 原来的map不会变
    val map3 = map2.updated("math", 120)
    println(map3)
    println(map2)
    //过滤
    val map3_1 = map3.filter(t => t._2 >= 90) //过滤不会改变原 map中元素
    println(map3_1)
    println(map3)
    val map4 = map3.filter(_._2 >= 90)
    println(map4)
    //遍历
    for (i <- map1) {
      println(" " + i._1, i._2) // ._1 代表key ，._2 代表value
    }
    for (i <- map1.iterator) {
      println("iterator==" + i._1, i._2)
    }
    map1.foreach(m => {
      println("foreach==", m._1, m._2)
    })
    // map 的操作
    map1 += "is history" -> 80
    println(map1)
    map1 += (("java", 100), ("bigdata", 150))
    //map1 += ("java"->100,"bigdata"->120)
    println(map1)
    // 修改指定key的值
    map1.updated("java", 99)
    // map 中的删除
    map1 -= "java" // 删除指定的key
    map1 -= ("java", "chinese") // 删除多个key
    println(map1)
    var mapp=map1.drop(1)  //不建议使用，返回的是一个新map 不改变原map
    println(mapp)
    println(map1)
    // 获取值
    val keys =map1.keySet
    println(keys)
    val math = map1.get("math")
    val ai= map1.get("ai")
    val ai1=map1.getOrElse("AI",100)  // 没有对应的key值是，使用默认值
    println(math,ai,ai1)
    val allValues =map1.values
    val allkeys =map1.keys
    println(allValues,allkeys)
    //遍历



    //scala 中默认创建的map 都是不可变的，如果创建可变的需要mu
    val mulmap =mutable.Map[String,Int]()
    val mulmap1=mutable.HashMap[String,Int]()

    // 添加元素
    mulmap += "wuli" -> 99
    mulmap += (("huaxue" ->100),("shengwu ",89))
    println(mulmap)

    mulmap.update("wuli",92)// 可变的map更新时 原map改变
    println(mulmap)
    val mm1 =mulmap.update("wuli",98) ///??
    println(mm1,mulmap)
    //删除
    mulmap -= "wuli"
    // k可变map 的获取值
    println(mulmap.get("wuli"),mulmap.get("huaxue"))
    // 获取 搜有的key values 和map不可变的中的一样
    mulmap.get("wuli")match {
      case None => println("灭有")
      case Some(v) => println("有")
    }
    println(mulmap.getOrElse("wuli","没有"))//与上等价

    /**
      * 注意：map 集合中   ？？？？？？？？
      *
      */



    }

  }

