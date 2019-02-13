package org.qianfeng.bigdata.hdfs.newhdfs;

import org.apache.hadoop.fs.FileSystem;
import org.qianfeng.bigdata.hdfs.oldhdfs.HDFSOperator;

import java.io.IOException;

/**
 * @description 简单的测试器
 * @author: 张斌能
 * @create: 2018-11-14 19:08:16
 **/

public class Tester {
    public static void main(String[] args){
        FileSystem defaultNonHAHDFS = FileSystemFactory.getDefaultHAHDFS();
//        FileSystemOperator.createNewFile(defaultNonHAHDFS,"/c12","高鑫玉很nice");
        try {
            FileSystemOperator.readFile(defaultNonHAHDFS,"/c12");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
