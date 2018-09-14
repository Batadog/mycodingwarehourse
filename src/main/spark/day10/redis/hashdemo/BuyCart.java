package day10.redis.hashdemo;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class BuyCart {
private static Jedis jedis =new Jedis("hadoop1",6379);

        // 添加商品
    public static void addproductCart(){
    jedis.hset("cart:user001","t_shirt","2");
    jedis.hset("cart:user002","phone","2");
    jedis.hset("cart:user003","conputer","2");
    jedis.close();
    }
    // 查询购车信息
    public static  void getProductInfo(){
        String pForUser001 =jedis.hget("cart:user001","t_shirt");
        System.out.print(pForUser001);
        Map<String, String> pForUser002 = jedis.hgetAll("cart:user002");

        for (Map.Entry<String,String> entry : pForUser002.entrySet()){
            System.out.print(entry.getKey() + ":" +entry.getValue());
        }
        jedis.close();
    }
    // 修改购物车信息
    public  static void editProductInfo(){
        jedis.hset("cart:user:001","t_shirt","1");
        jedis.hincrBy("cart:user002","computer",2);
        jedis.close();

    }
    public  static void  main(String[] args){
        addproductCart();
        getProductInfo();
    }

}
