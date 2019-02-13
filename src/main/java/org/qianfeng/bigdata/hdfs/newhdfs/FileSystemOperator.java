package org.qianfeng.bigdata.hdfs.newhdfs;


import org.apache.hadoop.fs.*;

import java.io.IOException;

/**
 * @description 文件系统操作员对象
 * @author: 张斌能
 * @create: 2018-11-14 17:14:58
 **/

public class FileSystemOperator {
    /**
     * 在指定的文件系统中创建目录
     * @param fs    指定的文件系统
     * @param dir   需要创建的目录
     */
    public static void makedir(FileSystem fs, String dir){
        try{
            fs.mkdirs(new Path(dir));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 在指定的文件系统中级联删除目录或文件
     * @param fs 指定的文件系统
     * @param dir 需要创建的目录
     */
    public static void rmr(FileSystem fs,String dir){
        try {
            fs.delete(new Path(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 在指定的文件系统中列出给定的目录的详情
     * @param fs 指定的文件系统
     * @param dir 需要列出详情的目录
     */
    public static void listDir(FileSystem fs,String dir){
        try {
            FileStatus[] stats = fs.listStatus(new Path(dir));
            for(int i = 0;i<stats.length;++i){
                if(stats[i].isFile()){
                    System.out.println("file" + stats[i].getPath().toString());
                } else if(stats[i].isDirectory()){
                    System.out.println("dir" + stats[i].getPath().toString());
                } else if(stats[i].isSymlink()){
                    System.out.println("linkFile" + stats[i].getPath().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 在指定的文件系统中重命名文件
     * @param fs 指定的文件
     * @param oldAbsPath 旧的文件全路径
     * @param newAbsPath 新的文件全路径
     */
    public static void rename(FileSystem fs,String oldAbsPath,String newAbsPath){
        try {
            fs.rename(new Path(oldAbsPath),new Path(newAbsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 通过javaAPI在给定的文件系统上新建文件并直接给定文件的内容
     * @param filePath 文件路径
     * @param fileContent 文件内容
     */
    public static void createNewFile(FileSystem fs,String filePath,String fileContent){
        FSDataOutputStream out = null;
        try {
            out = fs.create(new Path(filePath));
            out.write(fileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 通过javaAPI在HDFS上读取指定文件的内容
     * @param filePath 文件路径
     */
    public static void readFile(FileSystem fs,String filePath) throws IOException {
        Path path = new Path(filePath);
        FSDataInputStream in = fs.open(path);
        FileStatus stats = fs.getFileStatus(path);
        byte[] buffer = new byte[(int)stats.getLen()];
        in.readFully(0,buffer);//将输入流中的全部内容一次性读取出来
        System.out.println("================读取到的内容如下=====================");
        System.out.println(new String(buffer));//将读取字节数组转换成字符串
        in.close();
    }

    /**
     * 上传本地文件到HDFS
     * 注意：如果在Windows操作系统上传文件，本地系统指的是Windows的文件系统
     * @param localPath 本地文件路径
     * @param HDFSPath  HDFS文件路径
     * @throws IOException
     */
    public static void uploadLocalFile2HDFS(FileSystem fs,String localPath,String HDFSPath){
        try {
            fs.copyFromLocalFile(new Path(localPath),new Path(HDFSPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
