package org.qianfeng.bigdata.mapreduce.join.reducejoin.v3.reducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 19:40:36
 **/

public class JoinReducerV3 extends Reducer<Text,Text, NullWritable,Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        context.write(NullWritable.get(),new Text("雇员ID\t雇员姓名\t所属部门\t所在地区"));
    }
    String departStr ;

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> valuesAll = new ArrayList();
        for (Text value : values) {
            valuesAll.add(value.toString());
        }
        for (int i = 0; i < valuesAll.size(); i++) {
            if (valuesAll.get(i).startsWith("dept,")){
                departStr = valuesAll.get(i);
                break;
            }
        }
        String[] dapartStrInfo = departStr.split(",");
        for (int i = 0; i < valuesAll.size(); i++) {
            String valueStr = valuesAll.get(i);
            if (valuesAll.get(i).startsWith("emp,")){
                String[] empStrInfo = valueStr.split(",");
                String outputkey = empStrInfo[1] +"\t"+empStrInfo[2]+"\t"+empStrInfo[3]+"\t"+dapartStrInfo[1]+"\t"+dapartStrInfo[2];
                context.write( NullWritable.get(),new Text(outputkey));
            }
        }
    }
}
