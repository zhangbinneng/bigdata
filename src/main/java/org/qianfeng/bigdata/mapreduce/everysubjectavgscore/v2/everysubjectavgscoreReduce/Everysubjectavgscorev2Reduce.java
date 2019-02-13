package org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v2.everysubjectavgscoreReduce;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description 求各科的平均成绩
 * @author: 张斌能
 * @create: 2018-11-18 18:11:04
 **/

public class Everysubjectavgscorev2Reduce extends Reducer<Text, DoubleWritable,Text,DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<DoubleWritable> iterator = values.iterator();
        Double score = 0.0;
        Integer count = 0;
        while (iterator.hasNext()){
            score += iterator.next().get();
            count ++;
        }
        context.write(key,new DoubleWritable(score/count));
    }
}
