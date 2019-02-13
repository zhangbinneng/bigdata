package org.qianfeng.bigdata.mapreduce.WordCount.v3.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description 聚合阶段
 * @author: 张斌能
 * @create: 2018-11-15 18:37:38
 **/

public class WordCountreducev3 extends Reducer<Text, LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long  count = 0L;
        Iterator<LongWritable> iterator = values.iterator();
        while(iterator.hasNext()){
            count += iterator.next().get();
        }
        context.write(new Text("单词总数"),new LongWritable(count));
    }
}
