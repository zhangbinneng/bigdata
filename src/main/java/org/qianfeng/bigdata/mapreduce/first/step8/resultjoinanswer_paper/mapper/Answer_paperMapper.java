package org.qianfeng.bigdata.mapreduce.first.step8.resultjoinanswer_paper.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Set;

/**
 * @description 答卷表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 10:50:18
 **/

public class Answer_paperMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "score";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 用特殊字符\001进行切割
        String[] split = value.toString().split("\001");
        String exam_id = split[1]; // 考试id
        String examinee_id = split[3]; // 学生学号
        String object_answer = split[12]; // 客观题答案分数json串
        String subject_answer = split[13]; // 主观题答案分数json串

        // 将json字符串转换成json对象
        JSONObject object = JSONObject.parseObject(object_answer);
        JSONObject subject = JSONObject.parseObject(subject_answer);

        // 获取客观题json对象中的key集合
        Set<String> key1 = object.keySet();
        // 获取主观题json对象中的key集合
        Set<String> key2 = subject.keySet();

        // 遍历客观题的key
        for (String s : key1) {
            // 获得客观题对应key的value（json对象）
            JSONObject jsonObject = object.getJSONObject(s);
            // 通过value对象中的key值score获取对应的value
            String score = jsonObject.getString("score");

            // 将数据中内容为空的数据替换成0
            if (score.equals("")) {
                context.write(new Text(exam_id + "_" + examinee_id +"_" + s), new Text(FLAG + "_" + 0));
            }else {
                context.write(new Text(exam_id + "_" + examinee_id + "_" + s), new Text(FLAG + "_" + score));
            }
        }

        // 遍历主观题的key
        for (String s : key2) {
            // 获得主观题对应key的value（json对象）
            JSONObject jsonObject = subject.getJSONObject(s);

            // 通过value对象中的key值score获取对应的value
            String score = jsonObject.getString("score");

            // 将数据中内容为空的数据替换成0
            if (score.equals("")) {
                context.write(new Text(exam_id + "_" + examinee_id + "_" + s), new Text(FLAG + "_" + 0));
            }else {
                context.write(new Text(exam_id + "_" + examinee_id + "_" + s), new Text(FLAG + "_" + score));
            }
        }
    }
}
