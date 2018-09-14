package baidu.day06

import kafka.producer.Partitioner
// 自定义分区器；
class CustomPartitioner extends Partitioner{
  override def partition(key: Any, numPartitions: Int) = {
    key.hashCode()%numPartitions
  }
}
