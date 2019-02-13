package org.qianfeng.bigdata.mapreduce.secondraysort.bean;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description 自定义【【【【【【【【【【【【=】
 * @author: 张斌能
 * @create: 2018-11-22 23:28:30
 **/

public class DateAndTemp implements WritableComparable<DateAndTemp> {
    private Text yearmomth = new Text();
    private Text day = new Text();
    private IntWritable wendu = new IntWritable();

    @Override
    public int compareTo(DateAndTemp o) {
        int compareResult = this.yearmomth.compareTo(o.getYearmomth());
        if (compareResult == 0){
            compareResult = wendu.compareTo(o.getWendu());
        }
        return compareResult;//升序  // return -1*compareResult 降序
    }

    @Override
    public void write(DataOutput out) throws IOException {
            out.writeUTF(this.getYearmomth().toString());
            out.writeUTF(this.getWendu().toString());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
            this.yearmomth = new Text(in.readUTF());
            this.wendu = new IntWritable(Integer.parseInt(in.readUTF()));
    }

    public Text getYearmomth() {
        return yearmomth;
    }

    public void setYearmomth(Text yearmomth) {
        this.yearmomth = yearmomth;
    }

    public Text getDay() {
        return day;
    }

    public void setDay(Text day) {
        this.day = day;
    }

    public IntWritable getWendu() {
        return wendu;
    }

    public void setWendu(IntWritable wendu) {
        this.wendu = wendu;
    }
}
