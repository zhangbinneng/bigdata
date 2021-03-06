package org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 上一次清洗后的数据结果mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 08:35:26
 **/

public class ResultMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "result";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001切割字符串
        String[] split = value.toString().split("\001");
        String part_template_id = split[6]; // 试题模板id
        String question_type_id = split[10]; // 试题类型id
        context.write(new Text(part_template_id + "_" + question_type_id), new Text(FLAG + "_" + value.toString()));
    }
}
