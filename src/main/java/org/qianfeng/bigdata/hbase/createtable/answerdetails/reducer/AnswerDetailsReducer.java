package org.qianfeng.bigdata.hbase.createtable.answerdetails.reducer;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.UUID;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-07 20:05:19
 **/

public class AnswerDetailsReducer extends TableReducer<Text, NullWritable,NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        String[] strings = key.toString().split("\001");// 获取当前行的数据
        String rowKey=strings[3];// 获取初始行健：学号
        // 如果学生id为空则随机生成一个UUID
        if(rowKey.equals("")||rowKey==null) rowKey= UUID.randomUUID().toString();
        Put put = new Put(Bytes.toBytes(strings[0]+"-"+rowKey+"-"+strings[5]));// 用拼接后的行健创建PUT对象
        // 将读取到的信息一一放入put
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("exam_id"),Bytes.toBytes(strings[0]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("start_date"),Bytes.toBytes(strings[1]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("class_name"),Bytes.toBytes(strings[2]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("examinee_id"),Bytes.toBytes(strings[3]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("examinee_name"),Bytes.toBytes(strings[4]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("quession_id"),Bytes.toBytes(strings[5]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("subject_name"),Bytes.toBytes(strings[6]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("stage"),Bytes.toBytes(strings[7]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("type"),Bytes.toBytes(strings[8]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("difficulty"),Bytes.toBytes(strings[9]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("is_objective"),Bytes.toBytes(strings[10]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("score"),Bytes.toBytes(strings[11]));
        put.addColumn(Bytes.toBytes("stuInfo"),Bytes.toBytes("goal"),Bytes.toBytes(strings[12]));
        context.write(NullWritable.get(),put);// 输出put
    }
}
