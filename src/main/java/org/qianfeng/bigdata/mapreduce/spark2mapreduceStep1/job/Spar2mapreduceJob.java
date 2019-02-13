package org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.hdfs.oldhdfs.HDFSOperator;
import org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.bean.Student;
import org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.map.Spark2mapreduceMapper;
import org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.reduce.Spark2mapreduceReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 19:30:57
 **/

public class Spar2mapreduceJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJarByClass(Spar2mapreduceJob.class);
        job.setMapperClass(Spark2mapreduceMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(Spark2mapreduceReducer.class);
        job.setOutputKeyClass(Student.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.waitForCompletion(true);
    }
}
