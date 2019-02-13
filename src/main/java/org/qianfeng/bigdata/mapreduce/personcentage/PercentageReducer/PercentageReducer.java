package org.qianfeng.bigdata.mapreduce.personcentage.PercentageReducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.qianfeng.util.PercentageUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description 把mapper阶段的各个学科的平均成绩
 * @author: 张斌能
 * @create: 2018-11-16 11:44:54
 **/

public class PercentageReducer  extends Reducer<Text, IntWritable,Text, Text> {

    //用来存储每个分数段一共有几个分数
   Map<String,Integer> scorePartNumberMap = new HashMap<>();

    /**
     * 每执行一次处理一个分数段
     * @param key   分数段
     * @param values    数量集合
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int currentScorePartNumber = 0;
        for(IntWritable value:values){
            currentScorePartNumber +=value.get();
        }
        scorePartNumberMap.put(key.toString(),currentScorePartNumber);
    }

    /**
     * 对每个分数段求其所占的百分比
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        Collection<Integer> allScorePartNumberCollection = scorePartNumberMap.values();
        int allScoreNumber = 0;
        for(Integer value:allScorePartNumberCollection){
            allScoreNumber += value.intValue();
        }
        Set<String> allScorepart = scorePartNumberMap.keySet();
        for(String scorePart : allScorepart){
            context.write(new Text(scorePart),new Text(PercentageUtil.getPercentage(scorePartNumberMap.get(scorePart)+"",allScoreNumber+"",2)));
        }
    }
}
