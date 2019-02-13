package org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.bean.Student;


import java.io.IOException;

/**
 * 9. 总成绩大于150分，且数学大于等于60，且年龄大于等于20岁的学生的平均成绩是多少？
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 16:46:07
 **/

public class Spark2mapreduceStep2Mapper extends Mapper<LongWritable, Text,Text, Student> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(",");
        Double chinese = Double.parseDouble(strings[4].split("-")[1]);
        Double math = Double.parseDouble(strings[5].split("-")[1]);
        Double english = Double.parseDouble(strings[6].split("-")[1]);
        Student stu = new Student();
        stu.set(strings[0],strings[1],strings[2],strings[3],chinese,math,english,chinese+math+english);
        if (math >=60 && Integer.parseInt(stu.getAge())>=20 && stu.getScore()>=150){
            context.write(new Text("1"),stu);
        }

    }
}
