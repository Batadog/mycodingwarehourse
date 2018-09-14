package Basicgrammar.day05

/**
  * scala 统计词频
  */
object WorkCountDemo05 {
  def main(args: Array[String]): Unit = {
    val arr= Array("qianfeng welcome to qianfeng beijing ")
   //handleWord(arr)
    handlwork(arr)
  }
  // 定义一个词频统计的方法
  def handleWord(arr:Array[String]):Unit={
  val res =  arr.flatMap(_ .split(" ")).map((_,1)).groupBy(_._1).mapValues(_.size).toBuffer
    println(res)
    for ((k,v)<- res){
      println(s"$k\t$v")
    }
  }

  /**
    * 细分步骤
    */
  def  handlwork(arr:Array[String]):Unit={
    //_ 代表数组中所有的元素
    val words:Array[String]=arr.flatMap(_.split(" "))
   // println(words.toBuffer)
    val word_map:Array[(String,Int)]= words.map((_,1)) //元组
  // println(word_map.toBuffer)
    /**
      * 分组
      */
    val words_groupby= word_map.groupBy(_._1)
     //println(words_groupby)
    for ((k,v)<- words_groupby){
      //println(s"$k ,"+ v.toBuffer)
      //计算map中val的值

    }
    val word_time = words_groupby.mapValues(_.size)
    println(word_time)
    println(word_time.toBuffer)
    // 按单词出现次数排序
    val word_sort =word_time.toList.sortBy(_._2).reverse
    println(word_sort)
    //取出次数出现最高的前三名
    val word_third= word_sort.take(3)
    println(word_third.to)
  }

}
