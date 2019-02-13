package org.qianfeng.bigdata.mapreduce.first.step3.resultjoinquestion.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 试题表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 09:25:03
 **/

public class QuestionMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "question";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001对字符串进行切割
        String[] split = value.toString().split("\001");
        // 判读读取行切割之后的数组长度是否大于等于4，防止数组下标越界
        if (split.length >= 4) {
            String question_id = split[0]; // 题目id
            String category_id = split[1]; // 分类id
            String question_type_id = split[2]; // 题目类型id
            String question_difficulty_id = split[3]; // 题目难度id
            context.write(new Text(question_id), new Text(FLAG + "_" + category_id + "\001" + question_type_id + "\001" + question_difficulty_id));
        }
    }
}
