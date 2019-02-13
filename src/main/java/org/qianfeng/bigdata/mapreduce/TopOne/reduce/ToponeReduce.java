package org.qianfeng.bigdata.mapreduce.TopOne.reduce;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @description topone
 * @author: 张斌能
 * @create: 2018-11-15 22:03:47
 **/

public class ToponeReduce extends Reducer<Text, DoubleWritable,Text,DoubleWritable> {
    Double max = Double.NEGATIVE_INFINITY;
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<DoubleWritable> iterator = values.iterator();
        while (iterator.hasNext()){
            Double current = iterator.next().get();
            if(current>max){
                max =current;
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        context.write(new Text("第一个数"),new DoubleWritable(max));
    }
}
