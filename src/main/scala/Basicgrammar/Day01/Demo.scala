package Basicgrammar.Day01

object Demo {
  def main(args: Array[String]): Unit = {
    //scala自动推断数据类型，也可以加上
    var ch= 'A'
    var salary =999.99
    var sa =8.33f
    var sal: Float =4.44f
    var b1= true
    print(ch.getClass)
    println(sal.getClass)
    println(salary.getClass)
    println(b1.getClass)
    println(sa.getClass)
   //数据类型转换
    /*
     Byte-->short-->Int-->Long-->Float-->Double
     Char-->Int    由低到高
     */
    val x:Long =10
    val y:Double =20
    val z:Double=x
    //val m:Long =y  不能转换
    //val m:Long =(Long)y scala 中不能强制转换
    val m =0
    var res:Int={
      if(m<0){
        -1
      }else {

        2
        }
    }
    println(res)
    var x0=1
    var y0=1
    var x1=2
    var y1=2
    val distance={
      var dis:Double =0
      val dx=x1-x0
      val dy=y1-y0
      dis=Math.sqrt(dx*dx+dy*dy)

    }
    println(distance)


  }
}
