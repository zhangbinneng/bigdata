package org.qianfeng.bigdata.mapreduce.first.step6.resultjoinquestiontype.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 上一次结果数据join试题类型表reducer
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 10:24:07
 **/

public class ResultJoinQuestion_typeReducer extends Reducer<Text, Text, Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 用来存储第一次清洗数据的保留数据
        List<String> resultList = new ArrayList<>();
        // 用来存储question_type表中的数据
        List<String> question_typeList = new ArrayList<>();
        for (Text value : values) {
            String s = value.toString();
            if (s.startsWith("result_")) {
                resultList.add(s.substring(7));
            }else if (s.startsWith("type_")) {
                question_typeList.add(s.substring(5));
            }
        }
        // 将两个list里的数据进行合并
        for (String result : resultList) {
            for (String paper : question_typeList) {
                context.write(new Text(result + "\001" + paper), NullWritable.get());
            }
        }
    }
}
