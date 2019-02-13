package org.qianfeng.bigdata.mapreduce.first.step1.answer_paperjoinexam.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 答题表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-05 19:48:03
 **/

public class Answer_paperMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "answer";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 将读取文件中的一行记录根据特殊字符\001切割字符串
        String[] splits = value.toString().split("\001");
        String exam_id = splits[1]; // 考试id
        String paper_id = splits[2]; // 试卷id
        String examinee_id= splits[3]; // 学号
        String examinee_name = splits[4]; // 学生姓名
        String class_name = splits[6]; // 班级名
        String start_date = splits[7]; // 考试开始时间
        // 将需要的数据拼接成字符串
        String str = exam_id + "\001" + start_date + "\001" + class_name + "\001" + examinee_id + "\001" + examinee_name + "\001" + paper_id;
        context.write(new Text(exam_id), new Text(FLAG + "\001" + str));
    }
}
