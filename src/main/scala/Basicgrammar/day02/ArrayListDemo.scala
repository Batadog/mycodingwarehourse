package Basicgrammar.day02

import scala.collection.mutable.ArrayBuffer
/**
  * scala 中的数组：定长和变长
  */
object ArrayListDemo {
  def main(args: Array[String]): Unit = {
    //用new定义一个数组（定长）
    val arr =new Array(6)
    val arr1:Array[String]=new Array[String](4)
    println(arr1+" //"+arr)//地址
    println(arr1.toString)// toString 与直接打印相同
    println(arr1.toBuffer)//将数组转存到buffer中 结果： ArrayBuffer(null, null, null, null)
    //不使用new获取数组 相当于调用Array 中的apply()方法 直接为数组赋值
    val arr2=Array[Int](666,999,88)
    var arr3=Array.apply(999,666,88)//二者等价
    arr3(1)=444 //更新替换
    arr3.update(0,123)  // 更新
    println(arr3(1)+" "+arr3(0))
    println(arr3(0))
    // 取数组中的单个元素
    println(arr3(0))
    println(arr3.apply(2)+""+989)
    // 将array 转换成List
    var array =Array(1,2,3,4,5,6,7)
    val li = array.toList
    val li1=li.dropRight(2)
    val li2=li.drop(0)
    val li3=li.drop(1)
    println(li)
    println(li1)
    println(li2)
    println(li3)
    println(li(1))

  //变长数组定义 两种方式：new ArrayBuffer()   ArrayBuffer.apply()
    val arrBuffer =new ArrayBuffer[String]()
    println(arrBuffer)
    //给变长数组新增元素
    arrBuffer += "chinese" //末尾添加1个 元素
    arrBuffer += ("math","english")
    arrBuffer ++= ArrayBuffer("bigdata,AI")//添加数组元素必须++=
    arrBuffer ++= ArrayBuffer("bigdata,AI")//末尾添加数组元素
    println(arrBuffer)
    println(arrBuffer(1))
    //指定位置加元素
    arrBuffer.insertAll(5,ArrayBuffer[String]("li","huang"))
    arrBuffer.insert(3,"hostory") //3 表示第三个元素
   println(arrBuffer)
   // 更新元素
    arrBuffer.update(1,"socore")  // 角标
    arrBuffer(0)="BAS"
    println(arrBuffer)
    //取值
    println(arrBuffer(1))
    println(arrBuffer.apply(2))
    for (i<-0 until arrBuffer.length){
      print(arrBuffer(i)+",")
    }
    println()
    //删除元素
    arrBuffer.remove(1)
    println(arrBuffer)
    arrBuffer.remove(2,3) //从下表位置开始(包括)，移除3个元素
    println(arrBuffer)
    arrBuffer -="li"   //这样也可以 -= 移除
    println(arrBuffer)
    arrBuffer.trimEnd(2) //后入，移除3个元素
    println(arrBuffer)

    //使用apply()
    val arrayBuffer1:ArrayBuffer[String]=ArrayBuffer("lili","erha")
    println(arrayBuffer1)


  }


}
