package org.qianfeng.bigdata.mapreduce.join.reducejoin.v2.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 09:34:43
 **/

public class ReduceJoinReducerv2 extends Reducer<Text,Text,Text,Text> {
    String deptStr;
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> valueAll = new ArrayList<>();
        for (Text value : values) {
            valueAll.add(value.toString());
        }
        for (int i = 0; i < valueAll.size(); i++) {
            if(valueAll.get(i).startsWith("dept")){
                deptStr = valueAll.get(i);
                //因为一次reduce方法调用只处理一个业务key，即只处理一个部门，即只有一行部门数据
                //所以，一旦找到部门信息，就没有再找下去的必要了
                break;
            }
        }
        //将部门数据使用逗号分隔成数组
        String[] deptStrSplit = deptStr.split(",");
        //循环给每一个雇员添加部门信息
        for (int i = 0; i < valueAll.size(); i++) {
            if (valueAll.get(i).startsWith("emp,")){
                String outputValueStr = valueAll.get(i).substring(4)+","+deptStrSplit[1]+","+deptStrSplit[2];
                Text outputValue = new Text(outputValueStr);
                context.write(new Text(),outputValue);
            }

        }
    }
}
