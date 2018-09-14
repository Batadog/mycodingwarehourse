package baidu.day06

import java.util.Properties
import java.util.concurrent.{Executor, Executors}

import kafka.consumer.{Consumer, ConsumerConfig, ConsumerIterator, KafkaStream}

import scala.collection.mutable
class kafkaConsumerDemo(val consumer:String,val stream:KafkaStream[Array[Byte], Array[Byte]])extends Runnable{
  override def run() = {
    val it: ConsumerIterator[Array[Byte], Array[Byte]] = stream.iterator()
    while(it.hasNext()){
      val data = it.next()
      val topic = data.topic
      val offset= data.offset
      val partition= data.partition
      val msg= new String(data.message())//拿到数据
      println(s"consumer:$consumer,topic:$topic,offset:$offset,partition:$partition,msg:$msg")
    }
  }
}
object kafaconsumeDemo {
  def main(args: Array[String]): Unit = {
    // 定义将要读取的topic
    val topic  ="test"
    //
    val topics = new mutable.HashMap[String,Int]()
    topics.put(topic,2)
    val props= new Properties()
    props.put("group.id","group01")

    props.put("zookeeper.connect","hadoop5:2181,hadoop6:2181,hadoop7:2181")
    // 调用默认分区器？
    props.put("auto.offset.reset", "smallest"
    )
    // 调用自定义分区器
    props.put("partitioner.class", "baidu.day06.CustomPartitioner")
  // 调用consumer配置类
    val config = new ConsumerConfig(props)
    //创建Consumer实例，如果没有数据，会进行线程等待
    val consumer = Consumer.create(config)

    //获取数据  key 指的是topic
     val streams: collection.Map[String, List[KafkaStream[Array[Byte], Array[Byte]]]] = consumer.createMessageStreams(topics)
    // 获取指定topic的数据
    val stream: Option[List[KafkaStream[Array[Byte], Array[Byte]]]] = streams.get(topic)
   // 创建一个固定大小的线程池
    val pool = Executors.newFixedThreadPool(3)// 这个包看看 线程池
    for (i<- 0 until stream.size){
      pool.execute(new kafkaConsumerDemo(s"Consumer:$i",stream.get(i)))
    }

  }

}
