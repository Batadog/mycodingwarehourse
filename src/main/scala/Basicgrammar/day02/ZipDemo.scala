package Basicgrammar.day02

/**
  * scala中的拉链
  */
object ZipDemo {
  def main(args: Array[String]): Unit = {
    val id = Array(1, 2, 3, 4, 5, 6)
    val name = Array("ll", "mm", "hh", "qq", "gg", "ww")
    //拉链：将编号和名字连接在一起
    // 如果两个数组的元素个数不一致，拉链操作后会生成新的数组，而且一短的数组为准，不足的补默认值
    val idName: Array[(Int, String)] = id.zip(name)
    //使用zipAll(name,"*","-1") 自身列表(zip前面的集合）较短的时候用*表示 而name较短则使用-1替代
    val idName1 = id.zipAll(name, "*", "-1")
    println(idName.toBuffer)
    println(idName1.toBuffer)
    println(idName1.apply(5)._2)

    // 对名字和其索引做一个拉链操作
    val nameWithIndex = name.zipWithIndex
    println(nameWithIndex.toBuffer)

    //解拉链操作
    val (k, v) = idName.unzip
    println(k.toBuffer, v.toBuffer)
    val aa = idName.unzip
    println(aa._1.toBuffer, aa._2.toBuffer)
    //

  }
}
