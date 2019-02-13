package org.qianfeng.bigdata.mapreduce.join.reducejoin.v1.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description reducejoin的mapper
 * @author: 张斌能
 * @create: 2018-11-22 09:06:16
 **/

public class ReduceJoinMapper   extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(",");
        if (splits.length == 4){
            Text outputkey = new Text(splits[2]);
            Text outputvalue = new Text("emp"+","+splits[0]+","+splits[1]+","+splits[3]);
            context.write(outputkey,outputvalue);
        }else {
            Text outputkey = new Text(splits[0]);
            Text outputvalue = new Text("dept"+","+splits[1]+","+splits[2]);
            context.write(outputkey,outputvalue);
        }
    }
}
