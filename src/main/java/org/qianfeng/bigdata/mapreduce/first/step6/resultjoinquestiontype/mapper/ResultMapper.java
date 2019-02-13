package org.qianfeng.bigdata.mapreduce.first.step6.resultjoinquestiontype.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description  上一次清洗结果的数据mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 09:23:15
 **/
public class ResultMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG="result";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001对字符串进行切割
        String[] split = value.toString().split("\001");
        String type_id = split[10]; // 题目类型id
        context.write(new Text(type_id), new Text(FLAG + "_" + value.toString()));
    }
}
