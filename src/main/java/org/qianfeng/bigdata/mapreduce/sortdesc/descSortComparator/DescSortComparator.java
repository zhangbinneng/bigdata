package org.qianfeng.bigdata.mapreduce.sortdesc.descSortComparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-18 20:24:17
 **/

public class DescSortComparator extends WritableComparator {
    public DescSortComparator(){
        super(LongWritable.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return -super.compare(a, b);
    }
}
