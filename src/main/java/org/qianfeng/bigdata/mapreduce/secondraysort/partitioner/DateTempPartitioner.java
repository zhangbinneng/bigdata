package org.qianfeng.bigdata.mapreduce.secondraysort.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.qianfeng.bigdata.mapreduce.secondraysort.bean.DateAndTemp;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-23 00:11:59
 **/

public class DateTempPartitioner extends Partitioner<DateAndTemp, Text> {
    @Override
    public int getPartition(DateAndTemp dateAndTemp, Text text, int numPartitions) {
        return Math.abs(dateAndTemp.getYearmomth().hashCode()%numPartitions);
    }
}
