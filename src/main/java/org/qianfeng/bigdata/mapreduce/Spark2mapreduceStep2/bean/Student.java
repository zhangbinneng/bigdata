package org.qianfeng.bigdata.mapreduce.Spark2mapreduceStep2.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-12-24 17:12:11
 **/

public class Student implements WritableComparable<Student> {
    private String classid;
    private String name;
    private String age;
    private String sex;
    private Double chinese;
    private Double math;
    private Double english;
    private Double score;

    public void set(String classid, String name, String age, String sex, Double chinese, Double math, Double english,Double score) {
        this.classid = classid;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
        this.score = score;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getChinese() {
        return chinese;
    }

    public void setChinese(Double chinese) {
        this.chinese = chinese;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getEnglish() {
        return english;
    }

    public void setEnglish(Double english) {
        this.english = english;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return classid + "," + name + "," +
                age + "," +
                sex + "," +
                chinese + "," +
                math + "," +
                english + "," +
                score;
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(classid);
        out.writeUTF(name);
        out.writeUTF(age);
        out.writeUTF(sex);
        out.writeDouble(chinese);
        out.writeDouble(math);
        out.writeDouble(english);
        out.writeDouble(score);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
            classid = in.readUTF();
            name = in.readUTF();
            age = in.readUTF();
            sex = in.readUTF();
            chinese = in.readDouble();
            math = in.readDouble();
            english = in.readDouble();
            score = in.readDouble();
    }
}
