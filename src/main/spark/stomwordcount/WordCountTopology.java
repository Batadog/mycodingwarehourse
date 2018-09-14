package  stomwordcount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * 入口类：驱动类，实现单词计数
 * 分三部分：
 * spout 获取数据
 * bolt1 切分
 * bolt2 聚合数据
 */
public class WordCountTopology {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        /**
         * 指定spout 和bolt
         */
        // 调用拓扑构建类
        TopologyBuilder builder = new TopologyBuilder();
        // 调用自定义spout来随机获取数据
        builder.setSpout("randomloaddataspout",new RandomLoadDataSpout(),1);
        // 调用自定义bolt来切分数据
        builder.setBolt(
                "splitbolt",new SplitBolt(),2)
                .shuffleGrouping("randomloaddataspout");
        // 调用自定义bolt来qiefen 数据
        builder.setBolt("count_bolt",new AggrBolt(),2).shuffleGrouping("splitbolt");

        // 配置信息类
        Config conf = new Config();
        // 设置是否生成运行日志
        // 在本地测试的时候可以设置为true，河阳可以根据日志 信息观察app的运行情况
        // 在生成环境中，一般设置为false ，以减少性能消耗
        conf.setDebug(true);


        // 两种运行模式，集群模式，本地模式
        if (args!=null&&args.length>0){
            conf.setNumWorkers(3);
            // 向集群提交任务
            StormSubmitter.submitTopologyWithProgressBar(args[0],conf,builder.createTopology());
        }else {
            // 本地设置启动几个线程数来模拟集群worder数
            conf.setMaxTaskParallelism(3);
            // 本地提交任务
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("wc",conf,builder.createTopology());
        }


    }



}
