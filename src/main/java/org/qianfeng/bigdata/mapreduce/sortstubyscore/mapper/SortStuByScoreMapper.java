package org.qianfeng.bigdata.mapreduce.sortstubyscore.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.mapreduce.sortstubyscore.bean.StudentWritable;

import java.io.IOException;

/**
 * @description 自定义类型的mapper
 * @author: 张斌能
 * @create: 2018-11-22 08:31:39
 **/

public class SortStuByScoreMapper extends Mapper<LongWritable, Text, StudentWritable, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //将Text类型的行内容转换成String类型
        String lineContext = value.toString();
        //将给定的一行文本切割成多个字符
        String[] items = lineContext.split(" ");
        StudentWritable currentStu = new StudentWritable();
        currentStu.setName(items[0]);
        currentStu.setScore(Integer.parseInt(items[1])+Integer.parseInt(items[2])+Integer.parseInt(items[3]));
        context.write(currentStu,NullWritable.get());
    }
}
