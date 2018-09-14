package day10.redis.listdemo;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class TaskCinsumer {
    private static class  TaskConsumer{

        private static Jedis jedis =new Jedis("hadoop1",6379);

        public  static void main(String[] args) throws InterruptedException {
            Random r =new Random();
            while(true){
                Thread.sleep(2000);
                // 从task_queue1 任务队列里取出一个任务，放到暂时队列里
                String taskId =jedis.rpoplpush("task-queue1","temp-queue1");
                // 模拟处理任务
                if (r.nextInt(19)%9 ==0){
                    // 任务处理失败，需要把任务信息从暂存队列里弹出来再放到任务队列里，等待继续消费
                    jedis.rpoplpush("temp-queue1","task-queue1");
                    System.out.print("任务处理失败：" +taskId);


                }else {
                    jedis.rpop("temp-queue1");
                    System.out.print("任务处理成功"+taskId);
                }


            }
        }
    }
}
