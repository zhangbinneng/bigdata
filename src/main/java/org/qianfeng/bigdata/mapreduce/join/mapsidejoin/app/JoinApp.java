package org.qianfeng.bigdata.mapreduce.join.mapsidejoin.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.join.mapsidejoin.mapper.JoinAtMapSideMapper;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;


/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-21 22:37:32
 **/

public class JoinApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String inputPath = "/mr/join/input/emp";
        String outputPath = "/mr/join/mapsidejoin";
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJarByClass(JoinApp.class);

        job.setMapperClass(JoinAtMapSideMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setNumReduceTasks(0);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        Path inputpath = new Path(inputPath);
        Path outputpath = new Path(outputPath);
        FileInputFormat.addInputPath(job,inputpath);
        FileOutputFormat.setOutputPath(job,outputpath);

        System.exit(job.waitForCompletion(true)?0:1);
    }
}
