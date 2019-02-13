package org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 试卷模板题型组成表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 10:35:21
 **/

public class Question_template_partMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "part";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001切割字符串
        String[] split = value.toString().split("\001");
        String question_template_id = split[1]; // 试卷模板id
        String question_type_id = split[2]; // 试卷类型id
        String question_mark = split[3]; // 题目得分
        context.write(new Text(question_template_id + "_" + question_type_id), new Text(FLAG + "_" + question_mark));
    }
}
