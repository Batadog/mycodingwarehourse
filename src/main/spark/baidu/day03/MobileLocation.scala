package baidu.day03

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.BlockId
import org.apache.spark.{SparkConf, SparkContext}
/**  需求：
  * 在一定范围内，求用户在所有经过的基站中停留最长的基站范围：数据格式如下
  *  18688888888,20160327082400,16030401EAFB68F1E3CDF819735E1C66,1
     18101056888,20160327082500,16030401EAFB68F1E3CDF819735E1C66,1
     基站数据：
     16030401EAFB68F1E3CDF819735E1C66,116.296302,40.032296,6
    分析：1获取基站log信息
          2对数据进行逻辑处理     切分                  -->((phone, lac), time_long)
          3对数据进行聚合求出用户在同一基站停留总时间， -->reducebykey(_+_)
          4对聚合后的信息进一步处理（为了join基站）     -->(lac, (phone, time))
          5 获取基站经纬度信息并处理                   -->(lac, (x, y))
          6 对聚合后的(lac, (phone, time))  join  维度信息(lac, (x, y))
          7 对join之后的数据[(String, ((String, Long), (String, String)))]返回(lac,(phone,time),(x,y))=>
            得到 （phone，time，xy）手机号+停留总时长+位置信息
          8 排序输出到文件
  */
object MobileLocation {
  def main(args: Array[String]): Unit = {
    //模板代码
    val conf: SparkConf = new SparkConf()
      .setAppName("MobileLocation")
      .setMaster("local")//本地模式
    val sc = new SparkContext(conf)
    // 获取基站中用户的日志数据
    val files: RDD[String] = sc.textFile("E://spark/sparkcoursesinfomation/spark/data/lacduration/log")
    //切分数据
    val phoneAndLacAndTime: RDD[((String, String), Long)] = files.map(line => {
      val fields: Array[String] = line.split(",")
      val phone = fields(0) // 手机号
      val time = fields(1).toLong
      // 时间戳
      val lac = fields(2) //基站id
      val eventType = fields(3).toInt // 事件类型
      val time_long = if (eventType == 1) -time else time
      ((phone, lac), time_long) //处理格式信息
    })
    //对数据进行聚合
    val sumedPhoneAndLacAndTime: RDD[((String, String), Long)] =
      phoneAndLacAndTime.reduceByKey(_ + _)
    //为添加位置信息进行数据预处理
    val lacAndPhoneAndTime: RDD[(String, (String, Long))] =
      sumedPhoneAndLacAndTime.map(line => {
        val phone = line._1._1 // 手机号
        val lac = line._1._2 // 基站id
        val time = line._2 // 用户在某个基站停留的时
        (lac, (phone, time))
      })
    //获取基站对应的经纬度信息
     val lacInfo: RDD[String] =
       sc.textFile("E://spark/sparkcoursesinfomation/spark/data/lacduration/lac_info.txt")
    val lacAndXY = lacInfo.map(line => {
      val fields = line.split(",")
      val lac = fields(0)
      val x = fields(1)
      val y = fields(2)
      (lac, (x, y))
    })
    //join 经纬度信息
   val joined: RDD[(String, ((String, Long), (String, String)))] =
     lacAndPhoneAndTime.join( lacAndXY)
    // 对join的信息整合，方便计算
    val phoneAndtimeAndxy:RDD[(String,Long,(String,String))] = joined.map(line => {
      val phone = line._2._1._1 //phone
      val time = line._2._1._2
      // time
      val lac = line._1
      //基站id
      val xy = line._2._2 //经纬度
      (phone, time, xy)
    })// 按手机号进行分组
    val grouped: RDD[(String, Iterable[(String, Long, (String, String))])] = phoneAndtimeAndxy.groupBy(_._1)
    //println(grouped.collect.toBuffer)
    //ArrayBuffer(
    // (18101056888,CompactBuffer((18101056888,54000,(116.304864,40.050645)),
    // (18101056888,1900,(116.303955,40.041935)),
    // (18101056888,97500,(116.296302,40.032296)))),
    // (18688888888,CompactBuffer((18688888888,51200,(116.304864,40.050645)),
    // (18688888888,1300,(116.303955,40.041935)),
    // (18688888888,87600,(116.296302,40.032296)))))
    //按照停留时长进行降序排序
    val sorted: RDD[(String, List[(String, Long, (String, String))])] = grouped.mapValues(_.toList.sortBy(_._2).reverse)
    println("sort++"+sorted.collect.toBuffer)
    //获取时长最长的前条数据
       val res: RDD[(String, List[(String, Long, (String, String))])] = sorted.mapValues(_.take(2))
    println("res=="+res.collect.toBuffer)
    sc.stop()
  }

}
