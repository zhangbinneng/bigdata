package org.qianfeng.bigdata.mapreduce.first.step1.answer_paperjoinexam.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 考试表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-05 20:20:27
 **/

public class ExamMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "exam";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 将一行数据按照特殊字符\001切割字符串
        String[] split = value.toString().split("\001");
        String exam_id = split[0]; // 考试id
        String paper_template_id = split[2]; // 试卷模板id
        String subject_name = split[4]; // 学科名
        String str = exam_id + "\001" + paper_template_id + "\001" + subject_name;
        context.write(new Text(exam_id), new Text(FLAG + "\001" + str));
    }
}
