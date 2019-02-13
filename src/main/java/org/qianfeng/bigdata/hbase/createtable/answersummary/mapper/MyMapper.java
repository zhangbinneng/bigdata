package org.qianfeng.bigdata.hbase.createtable.answersummary.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<LongWritable, Text, Text,NullWritable> {
       @Override
       protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // 原样输出到reducer
           context.write(value,NullWritable.get());
       }
   }