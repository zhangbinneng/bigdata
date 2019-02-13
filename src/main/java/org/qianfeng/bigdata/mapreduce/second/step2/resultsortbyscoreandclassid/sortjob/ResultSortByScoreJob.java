package org.qianfeng.bigdata.mapreduce.second.step2.resultsortbyscoreandclassid.sortjob;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.second.step2.resultsortbyscoreandclassid.sortmapper.ResultSortByScoreMapper;
import org.qianfeng.bigdata.mapreduce.second.step2.resultsortbyscoreandclassid.sortreducer.ResultSortByScoreReducer;

import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-03 10:33:22
 **/

public class ResultSortByScoreJob {
        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            // 创建job
            Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
            // 设置job运行的主类
            job.setJarByClass(ResultSortByScoreJob.class);
            // 设置mapper类
            job.setMapperClass(ResultSortByScoreMapper.class);
            // 设置reducer类
            job.setReducerClass(ResultSortByScoreReducer.class);
            // 设置map输出的key,value的类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            // 设置reducer输出的key,value的类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            //设置输入和输出路径
            FileInputFormat.addInputPath(job,new Path(args[0]));
            FileOutputFormat.setOutputPath(job,new Path(args[1]));
            job.waitForCompletion(true);
        }
}
