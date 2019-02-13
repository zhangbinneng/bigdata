package org.qianfeng.bigdata.mapreduce.WordCount.v3.jobv3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;
import org.qianfeng.bigdata.mapreduce.WordCount.v3.mapper.WordCountmapperv3;
import org.qianfeng.bigdata.mapreduce.WordCount.v3.reduce.WordCountreducev3;

import java.io.IOException;

/**
 * @description job
 * @author: 张斌能
 * @create: 2018-11-15 18:41:30
 **/

public class WordCountJobv3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length != 2){
            System.out.println("参数不对");
            System.exit(1);
        }else {
            String inputPath = args[0];
            String outputPath = args[1];
            if(inputPath == null || inputPath =="" || outputPath == null || outputPath == ""){
                System.out.println("参数错误");
                System.exit(1);
            }else {
                Configuration conf = HadoopUtil.getRemoteHadoopConf();
                Job job = Job.getInstance(conf);

                job.setJobName("单词统计");

                job.setJarByClass(WordCountJobv3.class);

                job.setMapperClass(WordCountmapperv3.class);
                job.setReducerClass(WordCountreducev3.class);

                job.setMapOutputKeyClass(Text.class);
                job.setMapOutputValueClass(LongWritable.class);

                job.setMapOutputKeyClass(Text.class);
                job.setOutputValueClass(LongWritable.class);

                FileInputFormat.addInputPath(job,new Path(inputPath));
                FileOutputFormat.setOutputPath(job,new Path(outputPath));

                job.waitForCompletion(true);
            }
        }
    }
}
