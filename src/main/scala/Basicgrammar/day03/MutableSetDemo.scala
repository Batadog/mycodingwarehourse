package Basicgrammar.day03


import scala.collection.mutable

object MutableSetDemo {
  def main(args: Array[String]): Unit = {
    // 创建一个可变的set
    val  mutableset01 = new  mutable.HashSet[String]()
    //添加新的元素 += / + /add /++=/+=
      mutableset01 += "hadoop"
    mutableset01 + "spark "
    mutableset01.add("storm")
    mutableset01.add("storm")// set 去重
    mutableset01 ++= mutable.Set("hive","hbase")
    mutableset01 ++  mutable.Set("1","2") // 会生成新的set

    // 更新元素
    mutableset01.update("hive1",true) // 如果有 就去重，没有就加入
    mutableset01.update("hive",false) // 有 就删除

    // 删除
      mutableset01 -= "hive1"
      mutableset01.remove("hbase")
     val newset01= mutableset01 -  "storm"   //- 生成新的集合
     val newset01_dorp=  mutableset01.drop(2)   // 返回新的集合 drop 前两个个

    //判断元素是否存在
    println(mutableset01.apply("storm")) // 使用apply 元素判断是否存在
    // set 遍历
    for (i<- mutableset01){
      print(i+" ")
    }
    mutableset01.foreach(f=>print(f+","))
    //用apply 方式获取set
    val mutableset03 = mutable.Set("lili",3,5,1,7,9)// 去重  ,set 集合可以存储不同类型的。
    println(mutableset03.apply("lili")) //  true  apply申请
    /**
      * scala中默认创建不可变的set
      * set是一个trait，不能被new 只能使用set()
      * + ++ - 等操作对于可变或不可变的set 都会生成一个新的set集合
      * 不可变的set若要增删改元素，需要定义成var 而可变的var 、val修饰都可以
      */
  }

}
