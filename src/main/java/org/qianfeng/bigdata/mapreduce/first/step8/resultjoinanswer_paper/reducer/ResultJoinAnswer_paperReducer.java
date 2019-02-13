package org.qianfeng.bigdata.mapreduce.first.step8.resultjoinanswer_paper.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 上一次清洗数据join答卷表
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 11:06:59
 **/

public class ResultJoinAnswer_paperReducer extends Reducer<Text, Text, Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 用来存储第一次清洗数据的保留数据
        List<String> resultList = new ArrayList<>();
        // 用来存储answer_paper表中的数据
        List<String> answerList = new ArrayList<>();
        for (Text value : values) {
            String s = value.toString();
            if (s.startsWith("result_")) {
                resultList.add(s.substring(7));
            }else if (s.startsWith("score_")) {
                answerList.add(s.substring(6));
            }
        }
        // 将两个list里的数据进行合并
        for (String result : resultList) {
            for (String paper : answerList) {
                context.write(new Text(result + "\001" + paper), NullWritable.get());
            }
        }
    }
}
