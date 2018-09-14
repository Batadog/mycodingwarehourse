package baidu.day04
import java.sql
import java.sql.{Connection, Date, DriverManager, PreparedStatement}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.expressions.StringTrimRight
import org.apache.spark.{SparkConf, SparkContext}
/**
  * 统计用户点击流日志的区域访问量。（通过ip来获取区域）
  *
  */
object IPSearch {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("IPSearch")
      .setMaster("local")
    val sc = new SparkContext(conf)
    // ip基础数据
    val ipinfo = sc.textFile("E://spark/sparkcoursesinfomation/spark/data/ipsearch/ip.txt")
    // 切分
    val splitIPinfo: RDD[(String, String, String)] = ipinfo.map(line => {
      val fields = line.split("\\|")
      val startIP = fields(2)
      //起始ip
      val endIP = fields(3)
      //stop  ip
      val province = fields(6) // Provice for ip
      (startIP, endIP, province)
    })


    // 广播之前，需要把广播数据获取到，利用action算子
    val arrIPInfo: Array[(String, String, String)] = splitIPinfo.collect()
    // 优化
    // 广播变量 ，有些基本数据会在所有的executor端多次使用。最好先把它广播到响应的excutor端
    // 这样在后期使用过程中，excutor 只需要从本地拉去即可，避免了大量网络io，也提高了速度
    val broadcastIPInfo: Broadcast[Array[(String, String, String)]] = sc.broadcast(arrIPInfo)
    // 获取用户访问数据
    val userInfo = sc.textFile("E://spark/sparkcoursesinfomation/spark/data/ipsearch/http.log")
    //切分
    val proviceAndOne: RDD[(String, Int)] = userInfo.map(line => {
      val fields = line.split("\\|")
      val ip = fields(1) //
      val ipToLong = ip2Long(ip) // 把用户ip转为long类型
      //获取 广播到excutor 的基础数据
      val arrIpInfo: Array[(String, String, String)] = broadcastIPInfo.value
      //查询用户的ip属于哪个ip段
      val index = binarySearch(arrIpInfo, ipToLong)
      //根据索引找到对应的省份
      val provice = arrIpInfo(index)._3
      (provice, 1)
    })
    // 聚合，得到省份范围的访问量
    val reduced = proviceAndOne.reduceByKey(_ + _)
      println("reduced=="+reduced.collect().toList.sortBy(_._2).reverse)//((陕西,1824), (河北,383), (云南,126), (重庆,868), (北京,1535))
      reduced.foreachPartition(data2mysql)
    sc.stop()
  }
  def ip2Long(ip: String): Long = {
    val fragments = ip.split("[.]")// 切分ip  '.'   "\\." 正则中单独 .  表示任意字符
    var ipNum = 0L
    for (i <- 0 until fragments.length) {
      ipNum = fragments(i).toLong | ipNum << 8L
    }
    ipNum
  }
  // 二分法查找用户ip 索引
  def binarySearch(arr: Array[(String, String, String)], ip: Long): Int = {
    var low = 0 //startIP, endIP, province)
    var high = arr.length
    while (low <= high) {
      val middle = (low + high) / 2
      if ((ip >= arr(middle)._1.toLong) && (ip <= arr(middle)._2.toLong)) {
        return middle
      }
      if (ip < arr(middle)._1.toLong) {
        high = middle - 1
      } else {
        low = middle + 1
      }
    }
    -1
  }

  // 把结果存到数据库
  val data2mysql = (it:Iterator[(String, Int)]) => {
    var conn: Connection = null;
    var ps: PreparedStatement = null;
    val sql = "insert into location_info(location,counts,access_date) values(?,?,?)"
    val user = "root"
    val passowrd = "admin"
    val jdbcUrl = "jdbc:mysql://hadoop1:3306/bigdata?useUnicode=true&characterEncoding=utf8"
    try {
      conn = DriverManager.getConnection(jdbcUrl, user, passowrd)
      it.foreach(line => {
        ps = conn.prepareStatement(sql)
        ps.setString(1, line._1)
        ps.setInt(2, line._2)
        ps.setDate(3, new Date(System.currentTimeMillis()))
        ps.executeUpdate()
      })
    } catch {
      case e: Exception => println(e.printStackTrace())
    } finally {
      if (ps != null)
        ps.close()
      if (conn != null) {
        conn.close()
      }
    }
  }
}
