package org.qianfeng.bigdata.mapreduce.spark2mapreduceStep1.bean;

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
    private String chinese;
    private String math;
    private String english;

    public void set(String classid, String name, String age, String sex) {
        this.classid = classid;
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }


    @Override
    public String toString() {
        return classid + "," + name + "," +
                age + "," +
                 sex + "," +
                 chinese + "," +
                 math + "," +
                 english ;
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
        out.writeUTF(chinese);
        out.writeUTF(math);
        out.writeUTF(english);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
            classid = in.readUTF();
            name = in.readUTF();
            age = in.readUTF();
            sex = in.readUTF();
            chinese = in.readUTF();
            math = in.readUTF();
            english = in.readUTF();
    }
}
