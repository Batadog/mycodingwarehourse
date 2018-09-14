//package qianfeng.day06
//
//import java.nio.file.Files
//
//import scala.actors.{Actor, Future}
//import scala.collection.mutable
//import scala.collection.mutable.ListBuffer
//import scala.io.Source
//
///**
//  * 多线程处理Wordcount
//  */
//case class SourceFile(fileName:String)//封装文件路径
//case class FileWordCount(word_map:Map[String,Int])// 封装每个文件统计的词频
//case object  Stop  //退出
//class ActorWordCount extends  Actor{ //统计
//   override def act(): Unit ={
//     /**
//       * scala为了提升性能使用2中共享线程方式 ：react:替代receive方法进行消息的处理
//       *                                      loop:为了不递归调用act()方法
//       */
//  loop(react({
//    case SourceFile(path)=>{
//      // 将每一行toArray
//      val line_to_array:Array[String]=Source.fromFile(path).getLines().toArray
//      //将每一行flat--map
//      val line_word_arr:Array[String]=line_to_array.flatMap(_.split(" "))
//      //映射
//      val line_word_map:Array[(String,Int)]= line_word_arr.map((_,1))
//      //分组
//      val line_word_groupby:Map[String,Array[(String,Int)]]= line_word_map.groupBy(_._1)
//      //最终值
//      val file_word_count:Map[String,Int] =line_word_groupby.mapValues(_.size)
//      //返回
//      sender ! FileWordCount(file_word_count)
//    }
//    case Stop =>{
//      exit()
//    }
//  }))
//  }
//}
//object ActorWordCount{
//  def main(args: Array[String]): Unit = {
//    //定义文件路径
//    val files = Array("E:\\pac.txt","E:\\pad.txt")
//    // 定义一个用于存储线程返回的结果
//    val resultSet = new mutable.HashSet[Future[Any]]()// 一个可变的Hashset
//    //获取 所有的返回的每一个文件结果的对象
//    val allFileWordCount = new ListBuffer[FileWordCount]()
//    //循环统计的文件
//    for (file <- Files){
//      val wordCountActor = new ActorWordCount
//      //启动线程
//      wordCountActor.start()
//      //使用线程发送消息
//       val result:Future[Any] =wordCountActor !! SourceFile(file)
//       //将线程返回结果存储
//      resultSet.add(result)
//      //结束
//      wordCountActor ! Stop
//    }
//    //循环结合resultSet
//    while (resultSet.size >0){
//      val final_resultSet = resultSet.filter(_.isSet)
//      for (finalSet <- final_resultSet){
//        val res = finalSet.apply()
//        if (res != null && res.getClass ==classOf[FileWordCount]){
//          val fileWordCount = res.asInstanceOf[FileWordCount]
//        }
//      }
//    }
//
//  }
//}