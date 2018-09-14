package day10.redis.Stringdemo;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.io.*;

public class stringDemo1 {
    public static void main(String[] args) throws  Exception  {
        // 简单测试
        Jedis jedis =new Jedis("hadoop1",6379);

        String pingAck =jedis.ping();
        System.out.print(pingAck);

        String setAck =jedis.set("s1","111");
        System.out.print(setAck);

        String getAck =jedis.get("s1");
        System.out.print(getAck);
//       objectTest();
//      objectToJsonTest();

    }
    private static Jedis jedis =new Jedis("hadoop1",6379);
    /**
     * 将字符串缓存到string数据结构中
     *
     */
    public static void StringTest(){
        jedis.set("user:001:name","xiaofang");
        jedis.mset("user:002:name","xiaofen","user:003:name","yaoyao");
                String uanme001 =jedis.get("user:001:name");
            String uanme002 =jedis.get("user:002:name");
        String uanme003 =jedis.get("user:003:name");

    }

    /**
     * 将对象数据缓存到String数据结构中
     */
    public static void objectTest() throws IOException, ClassNotFoundException {
        PorduceInfo p =new PorduceInfo();
        p.setName("Iphone8plus");
        p.setPrice(2999.0);
        p.setProcuctDesc("看小视频....");

        // 将对象那个序列化为byte数组
        ByteArrayOutputStream ba= new ByteArrayOutputStream();
        ObjectOutputStream oos =  new ObjectOutputStream(ba);
        // 用对象序列化的防暑将produceInfo序列化写入流中
        oos.writeObject(p);
        // 将ba转换为字节数组
        byte[] pBytes =ba.toByteArray();
        // 将序列化好的数据缓存到redis中
        jedis.set("product:001".getBytes(),pBytes);

        //读取更改缓存的数据
        byte[] pByteRes =jedis.get("product:001.".getBytes());
        // 反序列化
        ByteArrayInputStream bi= new ByteArrayInputStream(pByteRes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        PorduceInfo pRes = (PorduceInfo) oi.readObject();

    System.out.print(pRes);
    }
    /**
     * 将对象转化为json 字符串缓存到redi
     */
  public static void objectToJsonTest(){
    PorduceInfo p = new PorduceInfo();
    p.setName("iphone4");
    p.setPrice(4000.0);
    p.setProcuctDesc("用来送小三");

    // 将对象转换为json格式
    Gson gson = new Gson();
    String jsonPorductInfo = gson.toJson(p);

    // 缓存到redis
    jedis.set("product:002",jsonPorductInfo);
    //获取数据
    String jsonRes = jedis.get("product:002");
    //将json字符串转化为对象
    PorduceInfo produceinfo= gson.fromJson(jsonRes,PorduceInfo.class);

    System.out.print(jsonRes);
    System.out.print(produceinfo);
 }

}
