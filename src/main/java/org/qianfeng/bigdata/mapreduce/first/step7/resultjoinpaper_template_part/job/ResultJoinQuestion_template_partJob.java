package org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.mapper.Question_template_partMapper;
import org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.mapper.ResultMapper;
import org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.reducer.ResultJoinQuestion_template_partReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 10:40:27
 **/

public class ResultJoinQuestion_template_partJob {
    public static void main(String[] args) throws Exception {

                // 构建Job类的对象
                Job checkRandomJob = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
                // 给当前的job类的对象设置job名称
                // checkRandomJob.setJobName("check Random app");

                // 设置运行主类
                checkRandomJob.setJarByClass(ResultJoinQuestion_template_partJob.class);

                // 设置job的Mapper及其输出k，v的类型
                // checkRandomJob.setMapperClass(Paper_questionMapper.class);
                checkRandomJob.setMapOutputKeyClass(Text.class);
                checkRandomJob.setMapOutputValueClass(Text.class);


                // 设置jov的输出k，v的类型，也可以说Reducer输出的k，v类型
                checkRandomJob.setReducerClass(ResultJoinQuestion_template_partReducer.class);
                checkRandomJob.setOutputKeyClass(Text.class);
                checkRandomJob.setOutputValueClass(NullWritable.class);

                // 设置要处理的HDFS上的文件的路径
        Path path1 = new Path("/out_qf_project/resultjoinquestion_type/out");
        Path path2 = new Path("/qf_project/paper_template_part");
                MultipleInputs.addInputPath(checkRandomJob,path1, TextInputFormat.class, ResultMapper.class);
                MultipleInputs.addInputPath(checkRandomJob,path2, TextInputFormat.class, Question_template_partMapper.class);
                FileOutputFormat.setOutputPath(checkRandomJob,new Path("/out_qf_project/resultjoinpaper_template_part/out"));

                // 提交程序并等待完成
                checkRandomJob.waitForCompletion(true);

                // 查看程序执行结果
                // DefaultHDFSOperator.readFileOnHDFS(args[2] + "/part-r-00000");

    }
}
