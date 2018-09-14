package Basicgrammar.day02

import scala.collection.mutable.ArrayBuffer

object ArrayOperator {
  def main(args: Array[String]): Unit = {
    //yield 关键字将原始数组，进行操作产生一个新的数组，原始数组不发生改变
    val arr = Array(1,2,3,4,5,6)
    val res1 =for (i<- arr) yield i*10
    println(res1)
    println(res1.toBuffer)
    val res2 = for  (i<-arr) yield if (i%2==0) i*10
    println(res2.toBuffer)

    val res3 =for (i<- arr if i%2==0) yield i*10
    println(res3.toBuffer)

    // 使用filter 和map 转换成新的数组
    val res4 =arr.filter(_% 2==0).map(_ *100)
    println(res4.toBuffer)
    //對數組進行求和
    val sum =arr.sum
    val max = arr.max
    val sorted =arr.sorted
    val strted1=arr.sortWith(_>_)
    val sortedR=arr.sorted.reverse //反转数组
    println(sum,max,sorted.toBuffer,sortedR.toBuffer)
    println(strted1.toBuffer)
    //使用其他arr.操作。


  }
}
