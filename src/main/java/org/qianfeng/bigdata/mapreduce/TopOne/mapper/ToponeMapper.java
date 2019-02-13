package org.qianfeng.bigdata.mapreduce.TopOne.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 求一组数据中排好序的第一个
 * @author: 张斌能
 * @create: 2018-11-15 19:02:29
 **/

public class ToponeMapper extends Mapper<LongWritable, Text,Text,DoubleWritable>{
    Double max = Double.NEGATIVE_INFINITY;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(",");
        for(int i = 0;i<splits.length;i++){
            double current = Double.parseDouble(splits[i]);
            if(current>max){
                max =current;
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        context.write(new Text("max"),new DoubleWritable(max));
    }
}
