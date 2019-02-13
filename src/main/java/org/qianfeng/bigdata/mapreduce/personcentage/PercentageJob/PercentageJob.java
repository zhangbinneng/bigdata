package org.qianfeng.bigdata.mapreduce.personcentage.PercentageJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.personcentage.PercentageMapper.PercentageMapper;
import org.qianfeng.bigdata.mapreduce.personcentage.PercentageReducer.PercentageReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description 求百分比的job
 * @author: 张斌能
 * @create: 2018-11-17 09:15:19
 **/

public class PercentageJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


                Configuration conf = HadoopUtil.getRemoteHadoopConf();
                Job job = Job.getInstance(conf);
                job.setJobName("求百分比的job");
                job.setJarByClass(PercentageJob.class);
                job.setMapperClass(PercentageMapper.class);
                job.setReducerClass(PercentageReducer.class);
                job.setMapOutputKeyClass(Text.class);
                job.setMapOutputValueClass(IntWritable.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(Text.class);

                FileInputFormat.addInputPath(job,new Path(args[0]));
                FileOutputFormat.setOutputPath(job,new Path(args[1]));

                System.exit(job.waitForCompletion(true)?0:1);


    }
}
