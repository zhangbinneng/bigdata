package org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.mapper.JoinDeptMapper;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.mapper.JoinEmpMapper;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.reducer.JoinReducerV3;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;


/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 20:12:52
 **/

public class JoinAppV3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJobName("join reduce 2 mapper");

        job.setJarByClass(JoinAppV3.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(JoinReducerV3.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        Path path1 = new Path(args[0]);
        Path path2 = new Path(args[1]);

        MultipleInputs.addInputPath(job,path1, TextInputFormat.class, JoinDeptMapper.class);
        MultipleInputs.addInputPath(job,path2,TextInputFormat.class, JoinEmpMapper.class);

        Path output = new Path(args[2]);
        FileOutputFormat.setOutputPath(job,output);

        System.exit(job.waitForCompletion(true)?0:1);
    }
}
