package org.qianfeng.bigdata.mapreduce.sortstubyscore.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.sortstubyscore.bean.StudentWritable;
import org.qianfeng.bigdata.mapreduce.sortstubyscore.mapper.SortStuByScoreMapper;
import org.qianfeng.bigdata.mapreduce.sortstubyscore.reducer.SortStuByScoreReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;


import java.io.IOException;



/**
 * @description 根据学生成绩进行排序的job
 * @author: 张斌能
 * @create: 2018-11-22 08:44:40
 **/

public class SortStuByScoreJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        job.setJarByClass(SortStuByScoreJob.class);
        job.setMapperClass(SortStuByScoreMapper.class);
        job.setReducerClass(SortStuByScoreReducer.class);
        job.setMapOutputKeyClass(StudentWritable.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        System.exit(job.waitForCompletion(true)?0:1);

    }
}
