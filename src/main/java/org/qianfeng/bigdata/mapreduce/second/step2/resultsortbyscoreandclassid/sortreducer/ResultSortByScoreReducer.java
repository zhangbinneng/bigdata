package org.qianfeng.bigdata.mapreduce.second.step2.resultsortbyscoreandclassid.sortreducer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.*;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-03 10:30:27
 **/
public class ResultSortByScoreReducer extends Reducer<Text, Text,Text, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 创建承接values的容器
        ArrayList<String> infos = new ArrayList<>();
        // 创建承接score的容器
        Set<String> scores = new HashSet<>();
        // 遍历values
        for (Text value : values) {
            scores.add(value.toString().split("\001")[10]);
            infos.add(value.toString());
        }
        // 创建一个数组，存放scores
        int[] sort = new int[scores.size()];
        //下标
        int no= 0;
        // 遍历scores,把每个值转换成int类型存放到数组中
        for (String score : scores) {
            sort[no]=Integer.parseInt(score);
            no++;
        }
        // 把数组中的值进行排序
        for (int i = 0; i < sort.length-1; i++) {
            for (int j = 0; j < sort.length-1-i; j++) {
                if(sort[j]>sort[j+1]){
                    int temp = sort[j];
                    sort[j]=sort[j+1];
                    sort[j+1]=temp;
                }
            }
        }
        // 遍历score数组,把匹配的info对应输出，这样输出的值就是排好序的
        for(int i=0;i<sort.length;i++){
            for(String info:infos){
                String s = info.split("\001")[10];
                if(s.equals(sort[i]+""))  {
                    context.write(new Text(info+"\001"+(sort.length-i)),NullWritable.get());
                }
            }
        }
    }
}
