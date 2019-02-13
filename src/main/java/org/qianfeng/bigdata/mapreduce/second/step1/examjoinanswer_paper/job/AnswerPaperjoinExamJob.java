package org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.job;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.mapper.AnswerPaperMapper;
import org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.mapper.ExamMapper;
import org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.reducer.AnswerPaperjoinExamReducer;
import org.qianfeng.bigdata.mapreduce.util.HadoopUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-30 23:14:43
 **/

public class AnswerPaperjoinExamJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 创建job,传入config参数(config已经写好的放在HadoopUtil工具类中)
        Job job = Job.getInstance(HadoopUtil.getRemoteHadoopConf());
        // 给job取名字
        job.setJobName("exam join answerpaper");
        // 设置运行job的主类
        job.setJarByClass(AnswerPaperjoinExamJob.class);
        // 设置mapper的输出k,v值的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // 设置运行reducer的主类，reducer的输出k,v值的类型
        job.setReducerClass(AnswerPaperjoinExamReducer.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 设置传入的两个参数的路径
        Path path1 = new Path("/out_qf_project/resultjoinpaper_template_part/out");//exam文件路径
        Path path2 = new Path("/qf_project/answer_paper");//answer_paper的文件路径
        // 设置多参数输入，
        MultipleInputs.addInputPath(job,path1, TextInputFormat.class, ExamMapper.class);
        MultipleInputs.addInputPath(job,path2,TextInputFormat.class, AnswerPaperMapper.class);
        // 设置输出路径
        Path output = new Path("/date/qf_project/first");
        FileOutputFormat.setOutputPath(job,output);
        // 提交job
       job.waitForCompletion(true);
    }
}
