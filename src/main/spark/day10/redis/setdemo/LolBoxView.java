package day10.redis.setdemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class LolBoxView {
    private static Jedis jedis =new Jedis("hadoop1",6379);
    public static void main(String[] args) throws Exception {
        int i = 1;//计数器
        while (true){
            Thread.sleep(3000);
            System.out.print("第" + i + "次产看榜单");
            // 从redis获取榜单排名信息 取前五名
            Set<Tuple> heros = jedis.zrevrangeWithScores("hero", 0, 4);

            for (Tuple hero:heros){
                System.out.print(hero.getElement()+ "------------"+hero.getScore());
            }
            i++;
            System.out.print("");


        }
    }
}
