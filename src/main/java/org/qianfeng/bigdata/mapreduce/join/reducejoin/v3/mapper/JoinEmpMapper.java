package org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 19:22:23
 **/

public class JoinEmpMapper extends Mapper<LongWritable, Text,Text,Text> {
    String flag = "emp";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(",",-1);

        if(split.length == 4){
            Text outputkey = new Text(split[2]);
            Text outputvalue = new Text(flag+","+split[0]+","+split[1]+","+split[3]);
            context.write(outputkey,outputvalue);
        }
    }
}
