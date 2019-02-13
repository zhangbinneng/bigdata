package org.qianfeng.bigdata.mapreduce.first.step7.resultjoinpaper_template_part.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 上一次清洗结果join试卷模板题型组成表reducer
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 10:38:49
 **/

public class ResultJoinQuestion_template_partReducer extends Reducer<Text, Text, Text, NullWritable> {
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 用来存储第一次清洗数据的保留数据
        List<String> resultList = new ArrayList<>();
        // 用来存储paper_template_part表中的数据
        List<String> template_partList = new ArrayList<>();
        for (Text value : values) {
            String s = value.toString();
            if (s.startsWith("result_")) {
                resultList.add(s.substring(7));
            }else if (s.startsWith("part_")) {
                template_partList.add(s.substring(5));
            }
        }
        // 将两个list里的数据进行合并
        for (String result : resultList) {
            for (String paper : template_partList) {
                context.write(new Text(result + "\001" + paper), NullWritable.get());
            }
        }
    }
}
