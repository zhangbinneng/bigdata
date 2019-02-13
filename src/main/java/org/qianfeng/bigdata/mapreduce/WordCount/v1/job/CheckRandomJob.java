package org.qianfeng.bigdata.mapreduce.WordCount.v1.job;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;
import org.qianfeng.bigdata.mapreduce.WordCount.v1.maper.CheckRandomMapper;
import org.qianfeng.bigdata.mapreduce.WordCount.v1.reduce.CheckRandomReducer;


/**
 * @description 检测Random类随机性应用程序驱动程序
 * @author: 张斌能
 * @create: 2018-11-14 21:22:16
 **/

public class CheckRandomJob {
    public static void main(String[] args) throws Exception {
        if(args.length!=2){
            System.out.println("传参数必须是2个哦");
        }else {
            String  inputPath = args[0];
            String outputPath = args[1];
            if(inputPath == null || inputPath == "" || outputPath ==null || outputPath ==""){
                System.out.println("参数不对");
                System.exit(1);
            }else {
                Job checkRandomJob = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
                //给当前job类的对象设置job名称
                checkRandomJob.setJobName("check Random app");

                //设置运行主类
                checkRandomJob.setJarByClass(CheckRandomJob.class);

                //设置job的Mapper及其输出K,V的类型
                checkRandomJob.setMapperClass(CheckRandomMapper.class);
                checkRandomJob.setMapOutputKeyClass(Text.class);
                checkRandomJob.setMapOutputValueClass(LongWritable.class);

                //设置job的输出K,V的类型，也可以说是Reducer输出的K,V的类型
                checkRandomJob.setReducerClass(CheckRandomReducer.class);
                checkRandomJob.setOutputKeyClass(Text.class);
                checkRandomJob.setOutputValueClass(LongWritable.class);

                //设置要处理的HDFS上的文件的路径
                FileInputFormat.addInputPath(checkRandomJob,new Path(inputPath));
                //设置最终输出结果的路径
                FileOutputFormat.setOutputPath(checkRandomJob,new Path(outputPath));

                //等待程序完成后自动结束程序
                System.exit(checkRandomJob.waitForCompletion(true)?0:1);

            }
        }
    }
}
