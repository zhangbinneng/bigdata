package org.qianfeng.bigdata.mapreduce.first.step4.resultjoincategory.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 分类表mapper
 * @author: gjq 1136391020@qq.com
 * @create: 2018-12-06 09:38:53
 **/

public class CategoryMapper extends Mapper<LongWritable, Text, Text, Text> {
    // 定义一个常量标识符
    final String FLAG = "category";
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 使用特殊字符\001切割字符串
        String[] split = value.toString().split("\001");
        String category_id = split[0]; // 分类id
        String name = split[1]; //题目所属阶段
        context.write(new Text(category_id), new Text(FLAG + "_" + name));
    }
}
