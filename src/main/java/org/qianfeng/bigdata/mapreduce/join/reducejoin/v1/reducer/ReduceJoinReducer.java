package org.qianfeng.bigdata.mapreduce.join.reducejoin.v1.reducer;

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

public class ReduceJoinReducer extends Reducer<Text,Text,Text,Text> {
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
                
                valueAll.remove(i);
                break;
            }
        }
        String[] deptStrSplit = deptStr.split(",");
        for (int i = 0; i < valueAll.size(); i++) {
            String outputValueStr = valueAll.get(i)+","+deptStrSplit[1]+","+deptStrSplit[2];
            Text outputValue = new Text(outputValueStr);
            context.write(new Text(),outputValue);
        }
    }
}
