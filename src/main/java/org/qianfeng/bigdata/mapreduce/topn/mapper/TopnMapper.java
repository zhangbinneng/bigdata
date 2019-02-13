package org.qianfeng.bigdata.mapreduce.topn.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.mapreduce.topn.bean.DoubleArrayWritable;
import org.qianfeng.bigdata.mapreduce.topn.util.TOPNUtil;

import static  org.qianfeng.bigdata.mapreduce.topn.util.TOPNUtil.getTOPN;

import static org.qianfeng.bigdata.mapreduce.util.ArrayTypeTransformUtil.transStrArr2DoubleArr;
import static org.qianfeng.bigdata.mapreduce.topn.util.InitializeArrayUtil.initDoubleArr;
import java.io.IOException;

/**
 * @description 求topn，求一组数据的前n个
 * @author: 张斌能
 * @create: 2018-11-21 14:47:11
 **/

public class TopnMapper extends Mapper<LongWritable, Text,Text, DoubleArrayWritable> {
    int n;//取前n位
    double[] currentInputSplitTOPN;//存放当前输入分片中的TOPN数组

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //前n位
        n = Integer.parseInt(context.getConfiguration().get("n"));
        //根据n，初始化当前输入分片的TOPN数组
        currentInputSplitTOPN = new double[n];
        //将数组中的每个值初始化为Double类型的最小值
        initDoubleArr(currentInputSplitTOPN);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strArr = value.toString().split(",");
        //将切割好的字符串数组，转换成double类型的数组，如果有非数字，则用double类型的最小替换
        double[] doubleArr = transStrArr2DoubleArr(strArr);
        //如果当前行中的数字的个数比n小，用double类型的最小值将其数量补充至n
        if (doubleArr.length<n){
            double[] temp = new double[n];
            for(int i = 0; i<doubleArr.length;i++){
                temp[i] = doubleArr[i];
            }
            for(int i = doubleArr.length;i<n;i++){
                temp[i] = Double.NEGATIVE_INFINITY;
            }
            //用补充好的数组求当前行的TOPN
            double[] currentLineTOPN = getTOPN(n,temp);
            //求当前输入分片截止到当前行的TOPN
            currentInputSplitTOPN = getTOPN(n,currentLineTOPN,currentInputSplitTOPN);
        }else {
            //求当前行的TOPN
            double[] currentLineTOPN = getTOPN(n,doubleArr);
            //求当前输入分片截止到当前行的TOPN
            currentInputSplitTOPN = getTOPN(n,currentLineTOPN,currentInputSplitTOPN);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //构造DoubleArrayWritable类型的对象，用于Mapper输出TOPN数组
        DoubleArrayWritable inputSplitTOPNArr = new DoubleArrayWritable();
        //将当前分片的TOPN数组添加到上述创建的对象中
        Writable[] inputSplitTOPNArrTypeWritable = new DoubleWritable[n];
        for (int i =0;i<currentInputSplitTOPN.length;i++){
            inputSplitTOPNArrTypeWritable[i] = new DoubleWritable(currentInputSplitTOPN[i]);
        }
        inputSplitTOPNArr.set(inputSplitTOPNArrTypeWritable);
        //将上述填充好的对象输出到Reduce端
        context.write(new Text("topn"),inputSplitTOPNArr);
    }
}
