package  stomwordcount;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IControlSpout;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 * 实现spout类，用户获取数据，（本次为模拟生成数据）
 */
public class RandomLoadDataSpout  extends BaseRichSpout {
    // s声明一个队列，该队列用于生成的数据
  private SpoutOutputCollector collector;
  private Random rand;

    //用于初始化，只会被运行一次
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    this.collector=collector;    // 获取到的数据，传入
        rand =new Random();

    }
// 用于获取数据，不断执行，相当于有个while循环在不断调用该方法
    @Override
    public void nextTuple() {
        String[] str = new String[]{
                "xiao is my righg girl ",
                "xiaofen is my left girl",
                "mimi is my love",
                "yazhi is my lover"
        };
        String msg = str[rand.nextInt()];
        // 发射数据
        collector.emit(new Values(msg));

    }

    // 因为数据可以一次发送多条数据到collector 队列,下游bolt需要判断到底获取那条数据，
    // 需要给发送的数据起个名称，key --value   名称---数据
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("sentence"));
    }
}
