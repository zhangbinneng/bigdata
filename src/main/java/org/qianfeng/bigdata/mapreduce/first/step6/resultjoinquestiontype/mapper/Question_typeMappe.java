package org.qianfeng.bigdata.mapreduce.first.step6.resultjoinquestiontype.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 试题类型表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 10:20:51
 **/

public class Question_typeMappe extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FALG = "type";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001对字符串进行切割
        String[] split = value.toString().split("\001");
        String type_id = split[1]; // 题目类型id
        String type = split[2]; // 题目类型
        String is_objective = split[3]; // 是否为客观题
        context.write(new Text(type_id), new Text(FALG + "_" + type + "\001" + is_objective));
    }
}
