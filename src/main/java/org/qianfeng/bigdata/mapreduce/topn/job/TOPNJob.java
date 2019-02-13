package org.qianfeng.bigdata.mapreduce.topn.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.topn.bean.DoubleArrayWritable;
import org.qianfeng.bigdata.mapreduce.topn.mapper.TopnMapper;
import org.qianfeng.bigdata.mapreduce.topn.reducer.TopnReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-21 22:07:52
 **/

public class TOPNJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length!=3){
            System.out.println("参数错误");
        }else {
            String inputPath = args[1];
            String outputPath = args[2];
            if(inputPath == null || inputPath ==""||outputPath==null||outputPath==""){
                System.out.println("参数不对");
                System.exit(1);
            }else {
                Configuration conf = HadoopUtil.getRemoteHadoopConf();
                conf.set("n",args[0]);
                Job TOPNJob = Job.getInstance(conf);
                TOPNJob.setJarByClass(org.qianfeng.bigdata.mapreduce.topn.job.TOPNJob.class);

                //设置job的Mapper及其输出K,V的类型
                TOPNJob.setMapperClass(TopnMapper.class);
                TOPNJob.setMapOutputKeyClass(Text.class);
                TOPNJob.setMapOutputValueClass(DoubleArrayWritable.class);

                //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
                TOPNJob.setReducerClass(TopnReducer.class);
                TOPNJob.setOutputKeyClass(Text.class);
                TOPNJob.setOutputValueClass(DoubleWritable.class);

                //设置要处理的HDFS上的文件的路径
                FileInputFormat.addInputPath(TOPNJob,new Path(inputPath));
                //设置最终输出结果的路径
                FileOutputFormat.setOutputPath(TOPNJob,new Path(outputPath));

                //等待程序完成后自动结束程序
                TOPNJob.waitForCompletion(true);
            }
        }
    }
}
