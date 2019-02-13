package org.qianfeng.bigdata.mapreduce.personcentage.PercentageMapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求各个分数阶段的百分比
 * @author: 张斌能
 * @create: 2018-11-16 11:30:00
 **/

public class PercentageMapper extends Mapper<LongWritable, Text,Text,IntWritable> {
    IntWritable one = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(" ");
        for(int i =1; i< splits.length;i++){
            int score = Integer.parseInt(splits[i]);
            if(score<60){
                context.write(new Text("<60"),one);
            }else if(score<69){
                context.write(new Text("60-69"),one);
            }else if(score<79){
                context.write(new Text("70-79"),one);
            }else if(score<89){
                context.write(new Text("80-89"),one);
            }else if(score<100){
                context.write(new Text("90-100"),one);
            }
        }
    }
}
