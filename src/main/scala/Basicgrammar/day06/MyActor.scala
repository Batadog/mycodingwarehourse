//package qianfeng.day06
//
//import scala.actors.Actor
//
///**
//  *scala 中 actor
//  *相当于java中的thread 线程，实现多线程的编程
//  * 目标：分别调用两个start()方法，然后执行每一个线程的act()方法
//  *     自定义线程需要继承Actor
//  */
//class MyActor extends  Actor{
//  // ctrl +i  ctrl + o  快捷键
//  override def act(): Unit = {
//    //执行逻辑
//    for (i<- 1 to 6){
//      println("i==="+i)
//      Thread.sleep(1000)
//    }
//  }
//}
////Test
//object MyActorTest{
//  def main(args: Array[String]): Unit = {
//    //初始化子线程
//    val myactor = new MyActor
//     // 开启子线程
//    myactor.start()
//    //myactor.act()//发方法是线程阻塞，必须等待Actor线程执行完毕，执行下一步
//  // 获取子线程状态
//    println(myactor.getState.toString)
// // 主线程输出
//    for (j<- 1 to 10)
//      println(s"j==="+j)
//    Thread.sleep(1000)
//  }
//}
