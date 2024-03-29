package com.spnotes.storm.bolts;
 
import java.util.HashMap;
import java.util.Map;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
 
public class WordCounterBolt implements IRichBolt{
Map<String, Integer> counters;
private OutputCollector collector;
public void prepare(Map stormConf, TopologyContext context,
OutputCollector collector) {
this.counters = new HashMap<String, Integer>();
this.collector = collector;
}
public void execute(Tuple input) {
String str = input.getString(0);
if(!counters.containsKey(str)){
counters.put(str, 1);
}else{
Integer c = counters.get(str) +1;
counters.put(str, c);
}
collector.ack(input);
}

public void cleanup() {
for(Map.Entry<String, Integer> entry:counters.entrySet()){
System.out.println(entry.getKey()+" : " + entry.getValue());
}
}

public void declareOutputFields(OutputFieldsDeclarer declarer) {
}
 

public Map<String, Object> getComponentConfiguration() {
return null;
}
}