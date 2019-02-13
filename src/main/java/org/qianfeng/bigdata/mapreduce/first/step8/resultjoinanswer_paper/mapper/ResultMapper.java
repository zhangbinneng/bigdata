package org.qianfeng.bigdata.mapreduce.first.step8.resultjoinanswer_paper.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.mapreduce.util.MD5Util;

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
        // 使用特殊字符\001切割字符
        String[] split = value.toString().split("\001");

        String exam_id = split[0]; // 考试id
        String start_date = split[1]; // 考试开始时间
        String class_name = split[2]; // 班级名称
        String examinee_id = split[3]; // 学生学号
        String examinee_name = split[4]; // 学生姓名
        String question_id = split[8]; // 考试题目id
        String subject_name = split[5]; // 学科名
        String stage = split[12]; // 考试阶段
        String difficulty = split[13]; // 题目困难度
        String type = split[14]; // 题目类型
        String is_objective = split[15]; // 主客观
        String score = split[16]; // 题目总分
        examinee_name = MD5Util.MD5(examinee_name); // 对学生姓名进行MD5加密

        // 将需要的数据进行拼接字符串
        String str = exam_id + "\001" +
                start_date + "\001" +
                class_name + "\001" +
                examinee_id + "\001" +
                examinee_name + "\001"+
                question_id + "\001" +
                subject_name + "\001" +
                stage + "\001" +
                difficulty + "\001" +
                type + "\001" +
                is_objective + "\001"+
                score;

        context.write(new Text(exam_id + "_" + examinee_id + "_" + question_id), new Text(FLAG + "_" + str));
    }
}
