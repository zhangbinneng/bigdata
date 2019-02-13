package org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 9. 总成绩大于150分，且数学大于等于60，且年龄大于等于20岁的学生的平均成绩是多少？
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 16:46:07
 **/

public class Spark2mapreduceMapper extends Mapper<LongWritable, Text,Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split("\\s+");
        String classid = strings[0];
        String name = strings[1];
        String age = strings[2];
        String sex = strings[3];
        String outkey = classid + "-" + name + "-" + age + "-" + sex;
        String outvalue = strings[4]+"-"+strings[5];
        context.write(new Text(outkey),new Text(outvalue));
    }
}
