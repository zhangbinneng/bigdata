package org.qianfeng.bigdata.mapreduce.sortstubyscore.bean;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-22 08:27:36
 **/

public class StudentWritable implements WritableComparable {
    String name;
    int score;

    @Override
    public int compareTo(Object o) {
        return this.score<((StudentWritable)o).score?1:-1;
    }

    @Override
    public void write(DataOutput out) throws IOException {
            out.writeUTF(name);
            out.writeInt(score);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
           this.name = in.readUTF();
           this.score = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
