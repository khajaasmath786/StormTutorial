
package com.spnotes.storm.bolts;
import java.util.Map;
 
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
 
public class WordSpitterBolt implements IRichBolt{
private OutputCollector collector;

public void prepare(Map stormConf, TopologyContext context,
OutputCollector collector) {
this.collector = collector;
}

public void execute(Tuple input) {
String sentence = input.getString(0);
String[] words = sentence.split(" ");
for(String word: words){
word = word.trim();
if(!word.isEmpty()){
word = word.toLowerCase();
collector.emit(new Values(word));
}
}
collector.ack(input);
}

public void declareOutputFields(OutputFieldsDeclarer declarer) {
declarer.declare(new Fields("word"));
}
 

public void cleanup() {
}

public Map<String, Object> getComponentConfiguration() {
return null;
}
}
