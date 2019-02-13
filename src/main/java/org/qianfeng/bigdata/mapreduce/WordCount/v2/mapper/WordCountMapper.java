package org.qianfeng.bigdata.mapreduce.WordCount.v2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description v2的Mapper阶段
 * @author: 张斌能
 * @create: 2018-11-15 17:26:04
 **/

public class WordCountMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.write(new Text("zbn"),new LongWritable(value.toString().split(",").length));
    }
}
