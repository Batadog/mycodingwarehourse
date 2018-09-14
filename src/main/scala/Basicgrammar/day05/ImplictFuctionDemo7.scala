package Basicgrammar.day05



import scala.io.Source

/**
  * scala 隐式视图  scal 中隐式函数转换，隐式函数转换必须要引入隐式函数所在区域
  */
class  ReadFile{
  def readFile(fileName:String):String={
    //使用source读取指定文件的内容
    Source.fromFile(fileName).mkString
  }
}
class showFile{
  def getFileName():String={
    "E:\\pac.txt"
  }
  def getFileData(data:String):Unit={
    println(data)
  }
}
//隐式函数区域
object ImplictFuctionDemo08{
  implicit def showFile2ReadFile(sf:showFile)={
    new ReadFile  // 相当于该类被隐藏了。
  }
}
//测试
object  Test{
  def main(args: Array[String]): Unit = {
    //调用普通方法
    val rf= new ReadFile
    var sf=new showFile
   // sf.getFileData((rf.readFile(sf.getFileName())))


    //调用隐式函数区域
    import ImplictFuctionDemo08._
    val showFile= new showFile
    val path= showFile.getFileName()

    val readfile= showFile2ReadFile(showFile)
    showFile.getFileData(readfile.readFile(path))
// 导入隐式函数区域，则隐式函数函数方法就可以直接通过传参调用该方法中暴露对象的所有属性和方法。




  }
}