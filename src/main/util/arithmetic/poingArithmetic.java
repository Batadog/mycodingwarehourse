package arithmetic;

import java.util.*;

/**
 * 1、轮询算法：
 * 轮询很容易实现，将请求按顺序轮流分配到后台服务器上，均衡的对待每一台服务器，而不关心服务器实际的连接数和当前的系统负载。
 *
 * 这里通过实例化一个serviceWeightMap的Map变量来服务器地址和权重的映射，以此来模拟轮询算法的实现
 */
public class poingArithmetic {
    private static Integer pos = 0;
    private static Map<String, Integer> serviceWeightMap = new HashMap<String, Integer>();

    static {
        serviceWeightMap.put("192.168.1.100", 1);
        serviceWeightMap.put("192.168.1.101", 1);  //权重为4
        serviceWeightMap.put("192.168.1.102", 4);
        serviceWeightMap.put("192.168.1.103", 1);
        serviceWeightMap.put("192.168.1.104", 1);        //权重为3
        serviceWeightMap.put("192.168.1.105", 3);
        serviceWeightMap.put("192.168.1.106", 1);        //权重为2
        serviceWeightMap.put("192.168.1.107", 2);
        serviceWeightMap.put("192.168.1.108", 1);
        serviceWeightMap.put("192.168.1.109", 1);
        serviceWeightMap.put("192.168.1.110", 1);
    }
    public static String testRoundRobin() {

        // 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        String server = null;

        synchronized (pos) {
            if (pos > keySet.size()) {
                pos = 0;
            }

            server = keyList.get(pos);

            pos++;
        }
/*    由于serviceWeightMap中的地址列表是动态的，随时可能由机器上线、下线或者宕机，因此，为了避免可能出现的并发问题，比如数组越界，通过在方法内新建局部变量serverMap，先将域变量拷贝到线程本地，避免被其他线程修改。这样可能会引入新的问题，当被拷贝之后，serviceWeightMap的修改将无法被serverMap感知，也就是说，在这一轮的选择服务器中，新增服务器或者下线服务器，负载均衡算法中将无法获知。新增比较好处理，而当服务器下线或者宕机时，服务消费者将有可能访问不到不存在的地址。因此，在服务消费者服务端需要考虑该问题，并且进行相应的容错处理，比如重新发起一次调用。
//
//    对于当前轮询的位置变量pos，为了保证服务器选择的顺序性，需要对其在操作时加上synchronized锁，使得同一时刻只有一个线程能够修改pos的值，否则当pos变量被并发修改，将无法保证服务器选择的顺序性，甚至有可能导致keyList数组越界。
//
   使用轮询策略的目的是，希望做到请求转移的绝对均衡，但付出的代价性能也是相当大的。为了保证pos变量的并发互斥，引入了重量级悲观锁synchronized，将会导致该轮询代码的并发吞吐量明显下降。
*/
        return server;
    }

    // 2、随机算法
    /* 为了避免并发的问题，需要将serviceWeightMap拷贝到serverMap中。通过Random的nextInt函数，取到0～keyList.size之间的随机值， 从而从服务器列表中随机取到一台服务器的地址，进行返回。
    根据概率统计理论，吞吐量越大，随机算法的效果越接近于轮询算法的效果。*/
    public static String testRandom() {

        // 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        Random random = new Random();
        int randomPos = random.nextInt(keyList.size());

        String server = keyList.get(randomPos);

        return server;
    }
   /* 3、源地址哈希法
    思想是根据服务消费者请求客户端的IP地址，通过哈希函数计算得到一个哈希值，将此哈希值和服务器列表的大小进行取模运算，得到的结果便是要访问的服务器地址的序号。
    采用源地址哈希法进行负载均衡，相同的IP客户端，如果服务器列表不变，将映射到同一个后台服务器进行访问。*/
    public static String testConsumerHash(String remoteIp) {

        // 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        int hashCode = remoteIp.hashCode();
        int pos = hashCode % keyList.size();

        return keyList.get(pos);
    }
    // 4、加权轮询：
  /*  不同的后台服务器可能机器的配置和当前系统的负载并不相同，因此它们的抗压能力也不一样。跟配置高、负载低的机器分配更高的权重，
    使其能处理更多的请求，而配置低、负载高的机器，则给其分配较低的权重，降低其系统负载，加权轮询很好的处理了这一问题，并将请求按照顺序且根据权重分配给后端。*/
    public static String testWeightRoundRobin() {

        // 重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serviceWeightMap);

        //取得IP地址list
        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();

        List<String> serverList = new ArrayList<String>();

        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i=0; i<weight; i++) {
                serverList.add(server);
            }
        }
        String server = null;

        synchronized (pos) {
            if (pos > serverList.size()) {
                pos = 0;
            }

            server = serverList.get(pos);
            pos++;
        }

        return server;
       /* 轮询算法类似，只是在获取服务器地址之前增加了一段权重计算代码，根据权重的大小，
        将地址重复增加到服务器地址列表中，权重越大，该服务器每轮所获得的请求数量越多。
        */
    }
}
