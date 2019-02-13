package org.qianfeng.bigdata.mapreduce.WordCount.v1.reduce;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description 计算总字数的Reducer
 * @author: Mmy mamingyume@foxmail.com
 * @create: 2018-09-25 14:50:39
 **/
public class CheckRandomReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<LongWritable> iterator = values.iterator();
        long countall = 0;
        while (iterator.hasNext()){
            countall += iterator.next().get();
        }
        context.write(new Text("总字数："),new LongWritable(countall));
    }
}

