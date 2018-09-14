package Basicgrammar.day04

import scala.util.Random

/**
  * scala 样例类
  * 在scala中样例类 是一种特殊的类，可用于模式匹配，case class 是多利 ，后面需要跟构造参数
  * 1、 case  calss  是 scala中特殊的类，叫样例类，case class 相当于java 中的javabean
  * 2、
  * 3、样例类需要使用关键字case 修饰 ，必须要主构造器，空也不能省略 case Object 不需要
  */
// 定义一个case class 来提交任务
case class SubmitTask(id:String,taskName:String)
// 心跳时间
case class HeartBeat(time:Long)
case object  CheckTimeOut
// 测试
object  CaseTest {
  def main(args: Array[String]): Unit = {
    val arr = Array(
      SubmitTask("mr_1001", "job_application_1001"),
      HeartBeat(2000L),
      CheckTimeOut
    )
    val emliment = arr(Random.nextInt(arr.length))
   // println(emliment)
   // println(HeartBeat(200).time)
    // 调用样例类的匹配
   // println(CheckTimeOut)
    caseMach_caseClass(HeartBeat(300))

  }
  def caseMach_caseClass(emliment: Any): Unit = {
    // 进行模式匹配
    emliment match {
      case SubmitTask(id, taskName) => println(s"任务：$id,taskName $taskName")
      case HeartBeat(300) => println(s"心跳间隔times")
      case CheckTimeOut => println("checktimes out")
      case _ => println("others .....")
    }
  }

}

