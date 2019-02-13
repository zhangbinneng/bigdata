package org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v2.everysubjectavgscoreJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v2.everysubjectavgscoreMapper.Everysubjectavgscorev2Mapper;
import org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v2.everysubjectavgscoreReduce.Everysubjectavgscorev2Reduce;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 19:17:35
 **/

public class Everysubjectscorev2Job {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration conf =  HadoopUtil.getRemoteHadoopConf();
        conf.set("subject",args[2]);
        Job job = Job.getInstance(conf);
        job.setJarByClass(Everysubjectscorev2Job.class);
        job.setMapperClass(Everysubjectavgscorev2Mapper.class);
        job.setReducerClass(Everysubjectavgscorev2Reduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));

        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
