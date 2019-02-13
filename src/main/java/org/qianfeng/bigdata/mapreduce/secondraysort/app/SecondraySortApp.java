package org.qianfeng.bigdata.mapreduce.secondraysort.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.secondraysort.bean.DateAndTemp;
import org.qianfeng.bigdata.mapreduce.secondraysort.mapper.SecondraySortMapper;
import org.qianfeng.bigdata.mapreduce.secondraysort.partitioner.DateTempPartitioner;
import org.qianfeng.bigdata.mapreduce.secondraysort.reducer.SecondraySortReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 23:50:54
 **/

public class SecondraySortApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());

        job.setJobName("second sort ");

        job.setJarByClass(SecondraySortApp.class);

        job.setMapperClass(SecondraySortMapper.class);
       job.setMapOutputKeyClass(DateAndTemp.class);
        job.setMapOutputValueClass(Text.class);

//        job.setPartitionerClass(DateTempPartitioner.class);//不设置分区也行，设置了只是性能更优

        job.setReducerClass(SecondraySortReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
    }
}
