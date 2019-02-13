package org.qianfeng.bigdata.mapreduce.sort.sortpartitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 19:52:06
 **/

public class Sortpartitioner extends Partitioner<LongWritable, Text> {
    @Override
    public int getPartition(LongWritable longWritable, Text text, int numPartitions) {
        long item = longWritable.get();
        if(item>100){
            return 1;
        }else {
            return 0;
        }
    }
}
