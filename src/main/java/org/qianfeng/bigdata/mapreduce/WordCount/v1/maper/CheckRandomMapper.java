package org.qianfeng.bigdata.mapreduce.WordCount.v1.maper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 计算所有数字的mapper
 * @author:XXD 1418208762@qq.com
 * create: 2018-11-15 15:10:02
 */
public class CheckRandomMapper extends Mapper<LongWritable,Text, Text,LongWritable> {
    /**
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(",");

        for(long i = 0 ; i < words.length;i++){
            context.write(new Text("xxd"),new LongWritable(1));
        }
    }
}

