package org.qianfeng.bigdata.mapreduce.join.mapsidejoin.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.qianfeng.bigdata.hdfs.oldhdfs.HDFSOperator;

import java.io.IOException;
import java.util.HashMap;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-21 22:19:41
 **/

public class JoinAtMapSideMapper extends Mapper<LongWritable, Text,Text,Text> {
    HashMap<Integer,String> allDepartment = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String[] lineArray = HDFSOperator.readHDFSFileAndReturn("/mr/join/cache/dept").split(System.lineSeparator());
        for (String s : lineArray) {
            String[] lineSplit = s.split(",");
            allDepartment.put(new Integer(lineSplit[0]),lineSplit[1]+","+lineSplit[2]);
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] lineContextSplit = value.toString().split(",");
        String departmentInfo = allDepartment.get(new Integer(lineContextSplit[2]));
        String currentEmpsAllInfo = value.toString()+"," +departmentInfo;
        context.write(new Text(),new Text(currentEmpsAllInfo));

    }
}
