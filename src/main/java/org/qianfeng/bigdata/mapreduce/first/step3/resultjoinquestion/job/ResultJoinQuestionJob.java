package org.qianfeng.bigdata.mapreduce.first.step3.resultjoinquestion.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.first.step3.resultjoinquestion.mapper.QuestionMapper;
import org.qianfeng.bigdata.mapreduce.first.step3.resultjoinquestion.mapper.ResultMapper;
import org.qianfeng.bigdata.mapreduce.first.step3.resultjoinquestion.reducer.ResultJoinQuestionReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

/**
 * @description
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 09:30:33
 **/

public class ResultJoinQuestionJob {
    public static void main(String[] args) throws Exception{
                // 构建Job类的对象
                Job checkRandomJob = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
                // 给当前的job类的对象设置job名称
                // checkRandomJob.setJobName("check Random app");

                // 设置运行主类
                checkRandomJob.setJarByClass(ResultJoinQuestionJob.class);

                // 设置job的Mapper及其输出k，v的类型
                // checkRandomJob.setMapperClass(Paper_questionMapper.class);
                checkRandomJob.setMapOutputKeyClass(Text.class);
                checkRandomJob.setMapOutputValueClass(Text.class);

                // 设置jov的输出k，v的类型，也可以说Reducer输出的k，v类型
                checkRandomJob.setReducerClass(ResultJoinQuestionReducer.class);
                checkRandomJob.setOutputKeyClass(Text.class);
                checkRandomJob.setOutputValueClass(NullWritable.class);

                // 设置要处理的HDFS上的文件的路径
        Path path1 = new Path("/out_qf_project/resultjoinpaper_question/out");
        Path path2 = new Path("/qf_project/question");
                MultipleInputs.addInputPath(checkRandomJob,path1, TextInputFormat.class, ResultMapper.class);
                MultipleInputs.addInputPath(checkRandomJob,path2, TextInputFormat.class, QuestionMapper.class);
                FileOutputFormat.setOutputPath(checkRandomJob,new Path("/out_qf_project/resultjoinquestion/out"));

                // 提交程序并等待完成
                checkRandomJob.waitForCompletion(true);

                // 查看程序执行结果
                // DefaultHDFSOperator.readFileOnHDFS(args[2] + "/part-r-00000");

    }
}
