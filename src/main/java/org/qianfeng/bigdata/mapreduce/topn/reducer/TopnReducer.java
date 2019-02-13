package org.qianfeng.bigdata.mapreduce.topn.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.qianfeng.bigdata.mapreduce.topn.bean.DoubleArrayWritable;
import org.qianfeng.bigdata.mapreduce.topn.util.InitializeArrayUtil;
import org.qianfeng.bigdata.mapreduce.topn.util.TOPNUtil;

import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-21 16:19:04
 **/

public class TopnReducer extends Reducer<Text, DoubleArrayWritable,Text,DoubleWritable> {
    int n ;//前n位
    double[] fileTOPN;//当前处理文件中的TOPN
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //前n位
        n = Integer.parseInt(context.getConfiguration().get("n"));
        //根据n，初始化前文件的TOPN数组
        fileTOPN = new double[n];
        //将数组中的每个值初始化为Double类型的最小值
        InitializeArrayUtil.initDoubleArr(fileTOPN);
    }

    @Override
    protected void reduce(Text key, Iterable<DoubleArrayWritable> values, Context context) throws IOException, InterruptedException {
        //循环处理每个输入分片产生的TOPN，由DoubleArrayWritable封装
        for (DoubleArrayWritable inputSplitTOPNArray:values){
            Writable[] writables = inputSplitTOPNArray.get();
            double[] inputSplitTOPNArrayItemDouble = new double[n];
            //将Reducer接受到的DoubleArrayWritable中存储的DoubleWritable[]强转成double[]
            for (int i =0;i<writables.length;i++){
                inputSplitTOPNArrayItemDouble[i] = ((DoubleWritable)writables[i]).get();
            }
            fileTOPN = TOPNUtil.getTOPN(n,fileTOPN,inputSplitTOPNArrayItemDouble);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //循环多行输出当前文件的TOPN
        for (int i = 0;i < fileTOPN.length;i++){
            context.write(new Text("top"+(i+1)+":"),new DoubleWritable(fileTOPN[i]));
        }
    }
}
