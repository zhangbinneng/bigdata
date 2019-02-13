package org.qianfeng.bigdata.mapreduce.sortdesc.sortdescReducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 19:49:48
 **/

public class SortdescReducer extends Reducer<LongWritable, Text,LongWritable,Text> {
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(key,new Text());
    }
}
