package org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.reduce;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.bean.Student;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 18:02:29
 **/

public class Spark2mapreduceReducer extends Reducer<Text,Text,Student, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Student stu = new Student();
        String[] strings = key.toString().split("-");
        stu.set(strings[0],strings[1],strings[2],strings[3]);
        String chinese ;
        String math;
        String english;
        for (Text value : values) {
            if (value.toString().split("-")[0].equals("chinese")){
                 chinese = value.toString();
                stu.setChinese(chinese);
            }
            if (value.toString().split("-")[0].equals("math")){
                 math = value.toString();
                stu.setMath(math);
            }
            if (value.toString().split("-")[0].equals("english")){
                 english = value.toString();
                stu.setEnglish(english);
            }
        }
        context.write(stu,NullWritable.get());
    }
}
