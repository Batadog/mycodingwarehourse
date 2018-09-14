package baidu.day06

import java.util.Properties

import kafka.message.Message
import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

object kafkaProducerDemo {
  def main(args: Array[String]): Unit = {
    //创建接收数据的topic
    val topic ="test"
    val  props = new Properties()
    props.put("serializer.class","kafka.serializer.StringEncoder")
    props.put("metadata.broker.list","hadoop5:9092,hadoop6:9092,hadoop7:9092")
    // s设置发送数据或的响应方式 1 0 -1
    props.put("request.required.acks","1")
    //调用分区器
    props.put("partitioner.class","kafka.producer.DefaultPartitioner")
    // producer配置类
    val config = new ProducerConfig(props)
    //create   a producer shili
     val producer: Producer[String,String] = new Producer(config)
    //模拟生成一些数据
    for (i<- 1 to 100000){
      val msg =s"$i:producer send data"
      producer.send(new KeyedMessage[String,String](topic,msg))
    }
  }

}
