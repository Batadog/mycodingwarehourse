package  stomwordcount;

import backtype.storm.topology.*;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

public class AggrBolt  extends BaseBasicBolt {
    // 用于存储结果数据，key=word  value =number 对应出现的次数
    Map<String,Integer> counts = new HashMap<>();
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        // 分析数据
        String word = String.valueOf(input.getValueByField("word"));
        int num = (Integer) input.getValueByField("number");
        if (!counts.containsKey(word)){
            counts.put(word,num);
        }else {
            counts.put(word,counts.get(word)+num);
        }
        System.out.println("单词统计的结果为："+counts);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
