package org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v1.everysubjectavgscoreMapper;

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

public class EverysubjectavgscoreMapper extends Mapper<LongWritable, Text,Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(" ");
            context.write(new Text("chinese"),new DoubleWritable(Double.parseDouble(splits[1])));
            context.write(new Text("math"),new DoubleWritable(Double.parseDouble(splits[2])));
            context.write(new Text("english"),new DoubleWritable(Double.parseDouble(splits[3])));
    }
}
