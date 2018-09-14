package Basicgrammar.day03

/**
  * 方法或者函数中的参数：定长参数和变长参数
  */
object  FunctionDemo03 {
  def main(args: Array[String]): Unit = {
    //定长参数可以设定默认值
    def methodDefaultArgs(name:String,age:Int,sex:Int,marray:Boolean=false)={
      println(s"name=$name,age=$age,marry=$marray,sex=$sex")
    }
    // 变长参数，当在调用方法时，如果参数个数不够，将会用变长参数补齐，变长用*
    def methodDefaultVarArgs(x:Array[String]*)={
      println(x.size)
    }
    // 变长和定长同时存在时 变长一定放最后
    def method1(cla:String,x:Array[String]*)={
      println("班级="+cla)
      for(i<-0 until x.size)
      println(x(i).toBuffer)
    }
    //默认参数和变长参数同时存在时，定长参数不能设定默认值
    //scala 中方法是否允许重载，？ 不允许// def  method1(cla:String)={}
    // 变长参数只能放最后，且只有一个
    def method2(sca:String,name:String,loc:String*):Unit={}
    //测试
    methodDefaultArgs("ss",12,1,false)
    methodDefaultArgs("ss",12,1)       //如果调用参数为默认值参数时，放到最后，可以不传递该参数
    methodDefaultVarArgs(Array("1","2"),Array("qw","ae"),Array("1w","2c")) //变长
    method1("aaa",Array ("1","2"),Array ("qw","ae")) //变长定长同时存在
    method2("bb","haha","aaa","aax","hatano","haha")// 方法定义中 定长参数不能有默认值，后跟变长
  }

}
