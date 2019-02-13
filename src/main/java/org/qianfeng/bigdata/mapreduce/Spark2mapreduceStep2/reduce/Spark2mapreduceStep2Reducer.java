package org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.reduce;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.bean.Student;


import java.io.IOException;
import java.util.ArrayList;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 18:02:29
 **/

public class Spark2mapreduceStep2Reducer extends Reducer<Text,Student,Text, NullWritable> {
    ArrayList<Double> arrayList = new ArrayList();
    Double allscore = 0.0;
    Double avg = 0.0;
    @Override
    protected void reduce(Text key, Iterable<Student> values, Context context) throws IOException, InterruptedException {
        for (Student value : values) {
            arrayList.add(value.getScore());
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        for (Double aDouble : arrayList) {
            allscore +=aDouble;
        }
        avg = allscore/arrayList.size();
        context.write(new Text(avg.toString()),NullWritable.get());
    }
}
