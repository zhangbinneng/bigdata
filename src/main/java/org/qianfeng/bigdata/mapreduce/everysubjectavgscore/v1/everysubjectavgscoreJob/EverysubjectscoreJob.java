package org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v1.everysubjectavgscoreJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v1.everysubjectavgscoreMapper.EverysubjectavgscoreMapper;
import org.qianfeng.bigdata.mapreduce.everysubjectavgscore.v1.everysubjectavgscoreReduce.EverysubjectavgscoreReduce;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 19:17:35
 **/

public class EverysubjectscoreJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("HADOOP_USER_NAME", "root");
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJarByClass(EverysubjectscoreJob.class);
        job.setMapperClass(EverysubjectavgscoreMapper.class);
        job.setReducerClass(EverysubjectavgscoreReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));

        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
