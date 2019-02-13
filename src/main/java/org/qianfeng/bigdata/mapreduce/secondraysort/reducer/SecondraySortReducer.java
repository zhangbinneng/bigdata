package org.qianfeng.bigdata.mapreduce.secondraysort.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.qianfeng.bigdata.mapreduce.secondraysort.bean.DateAndTemp;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 23:46:05
 **/

public class SecondraySortReducer extends Reducer<DateAndTemp, Text,Text, Text> {
    @Override
    protected void reduce(DateAndTemp key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(key.getYearmomth(),new Text(key.getWendu().toString()));
    }
}
