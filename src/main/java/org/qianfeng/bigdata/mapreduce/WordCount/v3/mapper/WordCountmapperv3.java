package org.qianfeng.bigdata.mapreduce.WordCount.v3.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description v3处理一个分片（block块）中的数据
 * @author: 张斌能
 * @create: 2018-11-15 18:29:29
 **/

public class WordCountmapperv3 extends Mapper<LongWritable, Text,Text,LongWritable> {
    long count = 0L;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        count += value.toString().split(",").length;
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        context.write(new Text("zbn"),new LongWritable(count));
    }
}
