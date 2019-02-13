package org.qianfeng.bigdata.mapreduce.everyoneavgscore.everyoneavgscoreMapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 计算每个人的凭据分数
 * @author: 张斌能
 * @create: 2018-11-17 17:16:44
 **/

public class EveryoneavgscoreMapper extends Mapper<LongWritable, Text,Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(key.get()!=0){
            double allscore = 0.0;
            String[] splits = value.toString().split(" ");
            String name = splits[0];
            for(int i = 1;i < splits.length;i++){
                allscore +=Double.parseDouble(splits[i]);
            }
            context.write(new Text(name),new DoubleWritable(allscore/(splits.length-1)));
        }
    }
}
