package org.qianfeng.bigdata.mapreduce.first.step2.resultjoinpaper_question.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @description 上一次清洗数据join试卷问题表reducer
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 08:48:43
 **/

public class ResultJoinPaper_questionReducer extends Reducer<Text, Text, Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 用来存储第一次清洗数据的保留数据
        List<String> resultList = new ArrayList<>();
        // 用来存储paper_question表中的数据
        List<String> paper_questionList = new ArrayList<>();
        for (Text value : values) {
            String s = value.toString();
            if (s.startsWith("result_")) {
                resultList.add(s.substring(7));
            }else if (s.startsWith("paper_question_")) {
                paper_questionList.add(s.substring(15));
            }
        }
        // 将两个list里的数据进行合并，每个考生对应的每到题目id。
        for (String result : resultList) {
            for (String paper : paper_questionList) {
                context.write(new Text(result + "\001" + paper), NullWritable.get());
            }
        }
    }
}
