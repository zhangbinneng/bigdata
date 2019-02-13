package org.qianfeng.bigdata.mapreduce.WordCount.v2.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;
import org.qianfeng.bigdata.mapreduce.WordCount.v2.mapper.WordCountMapper;
import org.qianfeng.bigdata.mapreduce.WordCount.v2.reduce.WordCountReduce;

import java.io.IOException;

/**
 * @description v2
 * @author: 张斌能
 * @create: 2018-11-15 17:38:45
 **/

public class WordCountJobv2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length !=2){
            System.out.println("参数不对");
            System.exit(1);
        }else {
            String inputPath = args[0];
            String outputPath = args[1];
            if(inputPath == null || inputPath == "" || outputPath ==null || outputPath ==""){
                System.out.println("参数错误");
                System.exit(1);
            }else {
                Configuration conf = HadoopUtil.getRemoteHadoopConf();
                Job job = Job.getInstance(conf);

                job.setMapperClass(WordCountMapper.class);
                job.setMapOutputKeyClass(Text.class);
                job.setMapOutputValueClass(LongWritable.class);

                job.setReducerClass(WordCountReduce.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(LongWritable.class);

                job.setJarByClass(WordCountJobv2.class);

                FileInputFormat.addInputPath(job,new Path(inputPath));
                FileOutputFormat.setOutputPath(job,new Path(outputPath));
                job.waitForCompletion(true);
            }

        }
    }
}
