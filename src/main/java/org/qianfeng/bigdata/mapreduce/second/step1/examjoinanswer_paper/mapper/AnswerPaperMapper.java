package org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.mapper;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.mapreduce.util.MD5Util;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-30 14:30:34
 **/
public class AnswerPaperMapper extends Mapper<LongWritable, Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 定义常量标志，给每个输出的value添加标志
        String FLAG = "answerpaper_";
        // 读取ansperpaper文件的一行，用\001切割
        String[] split = value.toString().split("\001");
        // 获取考试id
        String exam_id = split[1];
        // 获取考生学号
        String examinee_id = split[3];
        // 获取考生姓名,给姓名加密
        String examinee_name = MD5Util.MD5(split[4]);
        // 获取班级id
        String class_id = split[5];
        // 获取班级名
        String class_name = split[6];
        // 获取考试开始时间
        String start_date = split[7];
        // 获取已考时间
        String exam_time = split[8];
        // 提交时间
        String submit_time = split[9];
        // 客观提分数
        String objective_mark = split[10];
        // 主观题分数
        String subject_mark = split[11];
        // 将考试id作为输出的key
        String outkey = exam_id;
        // 将subject_mark为null的值替换成0
        if (subject_mark.equals("null") ) {
            subject_mark = "0";
        }
        // 将需要的字段拼接用\001分隔，前面加上标志，作为输出的value
        String  outvalue=FLAG +"\001"+
                exam_id + "\001" +
                start_date + "\001" +
                class_id + "\001" +
                class_name + "\001" +
                examinee_id + "\001" +
                examinee_name + "\001" +
                submit_time + "\001" +
                exam_time + "\001" +
                objective_mark + "\001" +
                subject_mark;
        context.write(new Text(outkey), new Text(outvalue) );
    }

}
