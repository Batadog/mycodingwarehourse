package Basicgrammar.day02
/**
  * 元组：（key,value)  key-value 值的对偶，元素个数，类型随意
  */
object TupleDemo {
  def main(args: Array[String]): Unit = {
    // 元组的创建：使用（） 将多个元素包在一起的意思，元素之间使用“，”分开可以存储多个不同类型的值
    val tuple1= ("qianfeng","gp1706","bigdata",123)
    println(tuple1)
    // 取值
    println(tuple1._1)
    //元组中类型任意
    val tuple2 =("qianfeng","gp1706","bigdata",123,("beijing","西三旗"))
     println(tuple2)
    println(tuple2._5._2)
  // 创建一个对偶元组 每个元组都有key-value两个
    val tuple3 =((1,"天丰利"),(2,"清华"),(3,"人大"))
    println(tuple3._2._2)
    //将元组转换成数组
    var arr = Array(tuple3)  // 把元组当成 数组中的第一个元素存储进来
    println(arr.toBuffer)
    println(arr(0))  // (1,"天丰利"),(2,"清华"),(3,"人大")
    for (i<- arr)
      println( i._3)
    //通过制定的key 创建元组，并指定key的值，key和value 的个数必须相等
    val tuple4,(a,b,c)=("北京","海淀","西安三期")
    println(tuple4,tuple4._3,a)

    //tuple 不能直接循环，如果要循环则需要转换成Array 或者 List
    for (i<- Array(tuple4)){
      println(i)
    }
    val iter = tuple4.productIterator
    //println(iter.toList)
    for  (i<-iter){
      println(i)
    }



     }
}
