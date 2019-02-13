package org.qianfeng.bigdata.hdfs.oldhdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

public class HDFSOperator {
    static String nameNodeURI = "";
    static Configuration hdfsConf = null;
    static FileSystem hdfs = null;
    static FileSystem local = null;
    //静态代码块，优于构造函数执行，一般用于初始化应用程序：即构造应用程序所需要的全局对象
    static {
        //记录HDFS通讯地址hdfs://namenode进程所在机器IP:8020
        nameNodeURI = "hdfs://zk1:8020";
        //定义配置对象
        hdfsConf = new Configuration();
        try{
            //HDFS文件系统
            hdfs = FileSystem.get(new URI(nameNodeURI),hdfsConf);
            //本地文件系统
            local = FileSystem.getLocal(new Configuration());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    /**
     * 应用程序入口
     */
    public static void main(String[] args) throws IOException {
        //为了方便操作，现在HDFS上将/目录及其所有多级子项的权限修改为777，
        // # hadoop fs -chmod -R 777 /
        //在特定文件系统上创建目录
//        makedir(hdfs,"/c12testdir/testdir");
//        System.out.println("HDFS上创建多级目录成功");
        //在特定文件系统上列出目录详情
//        System.out.println("HDFS上的根目录详情如下：");
//        listDir(hdfs,"/");

        //在特定的文件系统上删除目录
//        rmr(hdfs,"/c12testdir");
//        System.out.println("HDFS上删除目录成功");
        //往HDFS上上传指定内容的文件
//        createNewHDFSFile("/c12/javaio","好难理解javaIO");
//        System.out.println("往HDFS上上传指定内容的文件成功！");
        //从本地文件系统拷贝文件到HDFS上
//        uploadLocalFile2HDFS("date/test.txt","/c12");
//        System.out.println("上传本地文件到HDFS成功!");
        //从HDFS上的文件读取内容
//        System.out.println("HDFS上的文件中的内容如下:");
//        readHDFSFile("/c12/javaio");

        //删除HDFS上的文件
//        rmr(hdfs,"/c12/test.txt");
//        System.out.println("删除HDFS上的文件成功！");
//
//        //关闭与HDFS之间的链接
//        hdfs.close();
        readHDFSFile("/mr/secondraysort/out2/part-r-00000");
    }

    /**
     * 在制定的文件系统中创建目录
     * @param fs 指定的文件系统
     * @param dir 需要创建的目录
     */
    public static void makedir(FileSystem fs,String dir) throws IOException {
        fs.mkdirs(new Path(dir));
    }

    /**
     * 在指定的文件系统中列出给定的目录的详情
     * @param fs 指定的文件系统
     * @param dir   需要列出详情的目录
     * @throws IOException
     */
    public static void rmr(FileSystem fs,String dir) throws IOException {
        fs.delete(new Path(dir),true);
    }

    /**
     * 在指定文件系统中列出给定的目录的详情
     * @param fs  指定的文件系统
     * @param dir  需要列出详情的目录
     * @throws IOException
     */
    public static void listDir(FileSystem fs,String dir) throws IOException {
        FileStatus[] stats = fs.listStatus(new Path(dir));
        for(int i = 0;i<stats.length;++i){
            if(stats[i].isFile()) {
                //regular file
                System.out.println("file" + stats[i].getPath().toString());
            }else if(stats[i].isDirectory()){
                System.out.println("dir" + stats[i].getPath().toString());
            }else if(stats[i].isSymlink()){
                System.out.println("linkFile" + stats[i].getPath().toString());
            }
        }
    }

    /**
     * 在指定的文件系统中重新命名文件
     * @param fs    指定的文件
     * @param oldAbsPath    旧的文件全路径
     * @param newAbsPath    新的文件全路径
     * @throws IOException
     */
    public void rename(FileSystem fs,String oldAbsPath,String newAbsPath) throws IOException {
        fs.rename(new Path(oldAbsPath),new Path(newAbsPath));
    }
    /**
     * 通过javaPath在HDFS上新建文件直接给定文件的内容
     * @param filePath  文件路径
     * @param fileContent   文件内容
     * @throws IOException
     */
    public static void createNewHDFSFile(String filePath,String fileContent) throws IOException {
        FSDataOutputStream out = hdfs.create(new Path(filePath));
        out.write(fileContent.getBytes());
        out.close();
    }

    /**
     * 通过javaAPI在HDFS上读取指定文件的内容
     * @param filePath  文件路径
     * @throws IOException
     */
    public static void readHDFSFile(String filePath) throws IOException {
        Path path = new Path(filePath);
        FSDataInputStream in = hdfs.open(path);
        FileStatus stat = hdfs.getFileStatus(path);
        byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
        in.readFully(0,buffer);//将出入流中的全部内容一次性读取出来
        System.out.println("-------------读取的内容如下--------------");
        System.out.println(new String(buffer));//将读取字节数组转换成字符串
        in.close();
    }

    public static String readHDFSFileAndReturn(String filePath) throws IOException {
        Path path = new Path(filePath);
        FSDataInputStream in = hdfs.open(path);
        FileStatus stat = hdfs.getFileStatus(path);
        byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
        in.readFully(0,buffer);//将出入流中的全部内容一次性读取出来
        System.out.println("-------------读取的内容如下--------------");
        return new String(buffer);//将读取字节数组转换成字符串
    }
    public static String localreadHDFSFileAndReturn(String filePath) throws IOException {
        Path path = new Path(filePath);
        FSDataInputStream in = local.open(path);
        FileStatus stat = local.getFileStatus(path);
        byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
        in.readFully(0,buffer);//将出入流中的全部内容一次性读取出来
        System.out.println("-------------读取的内容如下--------------");
        return new String(buffer);//将读取字节数组转换成字符串
    }
    /**
     * 上传本地文件到HDFS
     * 注意：如果在Windows操作系统上传文件，本地系统指的是Windows的文件系统
     * @param localPath 本地文件路径
     * @param HDFSPath  HDFS文件路径
     * @throws IOException
     */
    public static void uploadLocalFile2HDFS(String localPath,String HDFSPath) throws IOException {
        hdfs.copyFromLocalFile(new Path(localPath),new Path(HDFSPath));
    }
}
