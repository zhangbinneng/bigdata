package org.qianfeng.bigdata.mapreduce.second.step1.examjoinanswer_paper.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-30 22:56:49
 **/

public class AnswerPaperjoinExamReducer extends Reducer<Text, Text, NullWritable,Text> {
    // 定义一个成员变量，用来存放读取exam文件输出的value
    String examStr ;
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 定义容器用来存放遍历的values
        List<String> valuesAll = new ArrayList();
        // 遍历values的value,并把value添加到容器中
        for (Text value : values) {
            valuesAll.add(value.toString());
        }
        // 通过匹配exam标志来取出exam文件输出的value,
        for (int i = 0; i < valuesAll.size(); i++) {
            if (valuesAll.get(i).startsWith("exam")){
                examStr = valuesAll.get(i);
                break;
            }
        }
        // 把exam输出的value用\001来切割，用examStrInfo字符数组承接
        String[] examStrInfo = examStr.split("\001");
       // 遍历valueAll容器，通过匹配标志answerpaper取出answerpaper文件的输出value，
        for (int i = 0; i < valuesAll.size(); i++) {
            // 将遍历的每一个value用valueStr承接
            String valueStr = valuesAll.get(i);
            // 匹配标志answerpaper取出answerpaper文件输出的value,
            if (valuesAll.get(i).startsWith("answerpaper")){
                // 对value用\001切割得到字符数组
                String[] answer_paperStrInfo = valueStr.split("\001");
                // 取出subject_mark客观成绩和object_mark主观成绩转换成int，计算总成绩
                int score = Integer.parseInt(answer_paperStrInfo[9])+Integer.parseInt(answer_paperStrInfo[10]);
                // 拼接输出的value
                String outputkey = answer_paperStrInfo[1] + "\001" +
                        answer_paperStrInfo[2] + "\001"+
                        answer_paperStrInfo[4] + "\001" +
                        answer_paperStrInfo[5] + "\001" +
                        answer_paperStrInfo[6] + "\001" +
                        answer_paperStrInfo[7] + "\001" +
                        answer_paperStrInfo[8] + "\001" +
                        examStrInfo[1] + "\001" +
                        answer_paperStrInfo[9] + "\001" +
                        answer_paperStrInfo[10] + "\001" +
                        score;
                // 提交输出的k,v键值对
                context.write( NullWritable.get(),new Text(outputkey));
            }
        }
    }
}
