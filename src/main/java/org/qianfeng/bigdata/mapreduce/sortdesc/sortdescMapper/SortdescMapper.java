package org.qianfeng.bigdata.mapreduce.sortdesc.sortdescMapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 19:46:17
 **/

public class SortdescMapper extends Mapper<LongWritable, Text,LongWritable,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] lineContext = value.toString().split(",");
        for (int i =0;i<lineContext.length;i++){
            context.write(new LongWritable(Long.parseLong(lineContext[i])),new Text());
        }
    }
}
