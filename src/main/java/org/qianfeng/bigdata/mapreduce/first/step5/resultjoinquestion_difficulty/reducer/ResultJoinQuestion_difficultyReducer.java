package org.qianfeng.bigdata.mapreduce.first.step5.resultjoinquestion_difficulty.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 上一次清洗结果join试题难度表reducer
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 09:53:02
 **/

public class ResultJoinQuestion_difficultyReducer extends Reducer<Text, Text, Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            // 用来存储第一次清洗数据的保留数据
            List<String> resultList = new ArrayList<>();
            // 用来存储paper表中的数据
            List<String> question_difficultyList = new ArrayList<>();
            for (Text value : values) {
                String s = value.toString();
                if (s.startsWith("result_")) {
                    resultList.add(s.substring(7));
                }else if (s.startsWith("difficulty_")) {
                    question_difficultyList.add(s.substring(11));
                }
            }
            // 将两个list里的数据进行合并
            for (String result : resultList) {
                for (String paper : question_difficultyList) {
                    context.write(new Text(result + "\001" + paper), NullWritable.get());
                }
            }
        }
}
