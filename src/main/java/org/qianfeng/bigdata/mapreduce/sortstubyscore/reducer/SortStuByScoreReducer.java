package org.qianfeng.bigdata.mapreduce.sortstubyscore.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.qianfeng.bigdata.mapreduce.sortstubyscore.bean.StudentWritable;

import java.io.IOException;

/**
 * @description 用于全局排序的Reducer
 * @author: 张斌能
 * @create: 2018-11-22 08:38:18
 **/

public class SortStuByScoreReducer extends Reducer<StudentWritable, NullWritable, Text, IntWritable> {
   int i = 0;
    @Override
    protected void reduce(StudentWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(new Text(key.getName()),new IntWritable(++i));
    }
}
