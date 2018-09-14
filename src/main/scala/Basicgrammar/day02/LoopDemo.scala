package Basicgrammar.day02

import scala.util.control.Breaks

/**
  * scala中的循环
  * for(i<- 表达式 /数组/集合）
  * while(表达式){}
  * do{} while(表达式)
  *
  */
object LoopDemo {
  def main(args: Array[String]): Unit = {
    //打印 1-10   to ：包括左右边界。  until：不包括右边
    for (i <- 1 to 10){
      println(i)
    }
    //定义数组
    val strArray=Array("abc",1,2,"def")
    for (i<- strArray){
      println(i+",")
    }
    //scala 中不建议写双重循环
    for (i<-1 to 5){
      for (j<-1 to (5)){
        if (i!=j){
          println("i=="+i+" j=="+j)
        }
      }
    }
     for (i<-1 to 5; j<-1 to(5) if(i==j)){ // 双重写一块，加上if语句
       print("i=="+i+" j=="+j+",")
     }
    // for的推导式执行(yeild)：
    val li=for (i<-1 to 5) yield i*3
    for (i<-li){
      println(i)
    }
    //while
    var i=0
    while(i<6){
      i+= 1 // scala 中不支持 --i ++i  i-- i++ 但是可以使用+= -= 去做
      println(","+i)
    }
    //do while  注意 上步的val i  结果， 为6
    do{
      i += 1
      print(" "+i)  // 7
    } while(i<5)
    // 循环的跳出，scala不支持break语句
    val myBreak =new Breaks
    myBreak.breakable({
      for (i<- 1 to 10){
        if (i>6){
          println("已经跳出")
          myBreak.break()
        }
        println("i=="+i)
      }
    })


  }
}
