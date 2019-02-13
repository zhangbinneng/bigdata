package org.qianfeng.bigdata.mapreduce.join.reducejoin.v2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @description reducejoin的mapper
 * @author: 张斌能
 * @create: 2018-11-22 09:06:16
 **/

public class ReduceJoinMapperv2 extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        String currentLineAtFileName = split.getPath().getName();
        String[] splits = value.toString().split(",");

        if (currentLineAtFileName.equals("emp")){
            Text outputkey = new Text(splits[2]);
            Text outputvalue = new Text("emp,"+splits[0]+","+splits[1]+","+splits[3]);
            context.write(outputkey,outputvalue);
        }else {
            Text outputkey = new Text(splits[0]);
            Text outputvalue = new Text("dept,"+splits[1]+","+splits[2]);
            context.write(outputkey,outputvalue);
        }
    }
}
