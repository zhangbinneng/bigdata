package org.qianfeng.bigdata.mapreduce.first.step2.resultjoinpaper_question.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 试卷问题关联表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 08:44:25
 **/

public class Paper_questionMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "paper_question";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001切割字符串
        String[] split = value.toString().split("\001");
        String paper_id = split[1]; // 试卷id
        String question_id = split[2]; // 试题id
        context.write(new Text(paper_id), new Text(FLAG + "_" + question_id));
    }
}
