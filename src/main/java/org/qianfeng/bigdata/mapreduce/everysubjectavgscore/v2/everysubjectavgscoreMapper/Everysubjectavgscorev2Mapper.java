package org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v2.everysubjectavgscoreMapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求各科的平均成绩
 * @author: 张斌能
 * @create: 2018-11-18 17:35:21
 **/

public class Everysubjectavgscorev2Mapper extends Mapper<LongWritable, Text,Text, DoubleWritable> {
    String subject = "";
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        subject = context.getConfiguration().get("subject");
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(key.get()!=0) {
            String[] splits = value.toString().split(" ");
            if(subject.equals("chinese'")){
                context.write(new Text("chinese"), new DoubleWritable(Double.parseDouble(splits[1])));
            }else if(subject.equals("math")){
                context.write(new Text("math"), new DoubleWritable(Double.parseDouble(splits[2])));
            }else if (subject.equals("english")){
                context.write(new Text("english"), new DoubleWritable(Double.parseDouble(splits[3])));
            }else {
                context.write(new Text("chinese"), new DoubleWritable(Double.parseDouble(splits[1])));
                context.write(new Text("math"), new DoubleWritable(Double.parseDouble(splits[2])));
                context.write(new Text("english"), new DoubleWritable(Double.parseDouble(splits[3])));
            }
        }
    }
}
