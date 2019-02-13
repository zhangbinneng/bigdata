package org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-30 22:22:41
 **/

public class ExamMapper extends Mapper<LongWritable, Text,Text,Text> {
        // 定义一个标志，给mapper提交的数据添加exam标志
        String FLAG ="exam";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 读取exam文件每一行，用;号来切割，得到字符数组
        String[] split = value.toString().split("\001");
        // 拿到考试号exam_id字段
        String exam_id = split[0];
        // 拿到考试限制时间limit_minute字段
        String limit_minute = split[5];
        // 将exam_id作为key,exam+limit_minute作为value输出
        context.write(new Text(exam_id),new Text(FLAG+"\001"+limit_minute));
    }
}
