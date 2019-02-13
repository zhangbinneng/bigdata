package org.qianfeng.bigdata.mapreduce.secondraysort.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.mapreduce.secondraysort.bean.DateAndTemp;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 23:40:44
 **/

public class SecondraySortMapper extends Mapper<LongWritable, Text, DateAndTemp,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //2012,01,01,5
        String[] split = value.toString().split(",",-1);
        String yearmonth = split[0]+split[1];
        String day = split[2];
        int wendu = Integer.parseInt(split[3]);


        DateAndTemp outputkey = new DateAndTemp();
        outputkey.setYearmomth(new Text(yearmonth));
        outputkey.setDay(new Text(day));
        outputkey.setWendu(new IntWritable(wendu));

        context.write(outputkey,new Text(wendu+""));
    }
}
