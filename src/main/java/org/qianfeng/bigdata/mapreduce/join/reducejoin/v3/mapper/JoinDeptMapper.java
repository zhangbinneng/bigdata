package org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 19:22:03
 **/

public class JoinDeptMapper extends Mapper<LongWritable, Text,Text,Text> {
    String flag = new String("dept");
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //使用严格的切割逻辑，保证切割出来的元素的个数符合要求
        String[] split =value.toString().split(",",-1);

        //原始数据错误检测，如果当前行数据的长度不为3则忽略此数据
        if(split.length == 3){
            Text outputkey = new Text(split[0]);
            Text outputvalue = new Text(flag+","+split[1]+","+split[2]);
            context.write(outputkey,outputvalue);
        }
    }
}
