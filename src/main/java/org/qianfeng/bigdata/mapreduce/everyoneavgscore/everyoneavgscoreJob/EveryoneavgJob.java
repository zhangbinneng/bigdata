package org.qianfeng.bigdata.mapreduce.everyoneavgscore.everyoneavgscoreJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.everyoneavgscore.everyoneavgscoreMapper.EveryoneavgscoreMapper;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description 没有reduce阶段的job
 * @author: 张斌能
 * @create: 2018-11-17 17:28:34
 **/

public class EveryoneavgJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = HadoopUtil.getRemoteHadoopConf();
        Job job = Job.getInstance(conf);
        job.setJobName("average score");
        job.setJarByClass(EveryoneavgJob.class);
        job.setMapperClass(EveryoneavgscoreMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
