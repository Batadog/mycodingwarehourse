package  stomwordcount;

import backtype.storm.topology.*;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 用于切分数据
 */
public class SplitBolt extends BaseBasicBolt {
   // 不断的运行，用于数据处理，此时用于切分数据，并发射出去到下游队列

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String sentence =input.getString(0);
        String[] words = sentence.split(" ");
        for (String word :words){
            word =word.trim();
            if (word.length()!=0){
                collector.emit(new Values(word,1));
            }
        }
    }
    //
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","number"));
    }
}
