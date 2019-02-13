package org.qianfeng.bigdata.mapreduce.first.step1.answer_paperjoinexam.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 答卷表join考试表reducer
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-05 20:43:00
 **/

public class Answer_paperJoinExamReducer extends Reducer<Text, Text, Text, NullWritable> {
    // 定义一个变量，用于存储exam表中的一行数据
    String examStr;
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> valueAll = new ArrayList<>();
        for (Text value : values) {
            valueAll.add(value.toString());
        }
        // 先找到表示exam考试表的那一行
        for (int i = 0; i < valueAll.size(); i++) {
            if (valueAll.get(i).startsWith("exam")) {
                examStr = valueAll.get(i);
                break;
            }
        }
        // 表示考试表信息的那一行切割，切割成如下的形式：
        String[] examStrSplit = examStr.split("\001");
        // 对答卷表添加考试表信息
        for (int i = 0; i < valueAll.size(); i++) {
            String valueStr = valueAll.get(i);
            if (valueStr.startsWith("answer")) {
                String[] answerStrSplit = valueStr.split("\001");
                String outputKey = answerStrSplit[1] + "\001" + answerStrSplit[2] + "\001" + answerStrSplit[3] + "\001" +
                        answerStrSplit[4] + "\001" + answerStrSplit[5] + "\001" + examStrSplit[3] + "\001" + examStrSplit[2] + "\001" + answerStrSplit[6];
                context.write(new Text(outputKey), NullWritable.get());
            }
        }
    }
}
