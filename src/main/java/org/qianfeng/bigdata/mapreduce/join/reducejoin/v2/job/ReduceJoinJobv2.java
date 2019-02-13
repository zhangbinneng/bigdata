package org.qianfeng.bigdata.mapreduce.join.reducejoin.v2.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v1.mapper.ReduceJoinMapper;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v1.reducer.ReduceJoinReducer;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v2.mapper.ReduceJoinMapperv2;
import org.qianfeng.bigdata.mapreduce.join.reducejoin.v2.reducer.ReduceJoinReducerv2;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 10:01:50
 **/

public class ReduceJoinJobv2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job =Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJobName("reduce join");
        job.setJarByClass(ReduceJoinJobv2.class);

        job.setMapperClass(ReduceJoinMapperv2.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(ReduceJoinReducerv2.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

      job.waitForCompletion(true);

    }
}
