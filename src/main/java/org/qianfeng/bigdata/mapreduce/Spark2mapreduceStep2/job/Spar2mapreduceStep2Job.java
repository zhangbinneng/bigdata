package org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.bean.Student;
import org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.map.Spark2mapreduceStep2Mapper;
import org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.reduce.Spark2mapreduceStep2Reducer;

import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 19:30:57
 **/

public class Spar2mapreduceStep2Job {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJarByClass(Spar2mapreduceStep2Job.class);
        job.setMapperClass(Spark2mapreduceStep2Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Student.class);
        job.setReducerClass(Spark2mapreduceStep2Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(1);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.waitForCompletion(true);
    }
}
