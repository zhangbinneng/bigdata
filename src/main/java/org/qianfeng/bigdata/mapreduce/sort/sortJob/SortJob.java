package org.qianfeng.bigdata.mapreduce.sort.sortJob;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.sort.sortMapper.SortMapper;
import org.qianfeng.bigdata.mapreduce.sort.sortReducer.SortReducer;
import org.qianfeng.bigdata.mapreduce.sort.sortpartitioner.Sortpartitioner;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 19:53:49
 **/

public class SortJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJarByClass(SortJob.class);
        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReducer.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        job.setPartitionerClass(Sortpartitioner.class);
        job.setNumReduceTasks(2);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.waitForCompletion(true);
    }
}
