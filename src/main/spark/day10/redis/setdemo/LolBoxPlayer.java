package day10.redis.setdemo;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class LolBoxPlayer {

   private static Jedis jedis =new Jedis("hadoop1",6379);
    public static void main(String[] args) throws Exception {
        Random r = new Random();
        String[] heros ={"盖伦","轮子妈","蒙多","压缩","木木","刀妹","提莫","菊花信"};

        while(true){
            int index = r.nextInt(heros.length);
            String hero =heros[index];
            Thread.sleep(3000);
            // ccl  出场率。。。 给英雄每次出场的分数加1
            jedis.zincrby("hero:ccl",1,hero);
            System.out.print(hero+"出场了");

        }


    }
}
