package org.qianfeng.bigdata.mapreduce.second.step2.resultsortbyscoreandclassid.sortmapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-03 08:59:04
 **/


public class ResultSortByScoreMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 读取每一行，用“\1.txt”切割
        String[] split = value.toString().split("\001");
        //获取exam_id
        String exam_id = split[0];
        //获取class_name
        String class_name = split[2];
        //拼接输出的key
        String outkey = class_name+exam_id;
        //原样输出value
        String outvalue = value.toString();
        context.write(new Text(outkey),new Text(outvalue));
    }
}

