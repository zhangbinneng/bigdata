package org.qianfeng.bigdata.mapreduce.first.step5.resultjoinquestion_difficulty.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 试题难度表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 09:50:31
 **/

public class Question_difficultyMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "difficulty";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001对字符串进行切割
        String[] split = value.toString().split("\001");
        String difficulty_id = split[1]; // 题目困难度id
        String difficulty = split[2]; // 题目困难度
        context.write(new Text(difficulty_id), new Text(FLAG + "_" + difficulty));
    }
}
