package org.qianfeng.bigdata.mapreduce.topn.bean;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;

/**
 * @description 自定义的类型
 * @author: 张斌能
 * @create: 2018-11-21 14:49:13
 **/

public class DoubleArrayWritable extends ArrayWritable {
    public DoubleArrayWritable() {
        super(DoubleWritable.class);
    }
}
