package org.qianfeng.bigdata.mapreduce;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @description 生产随机数脚本
 * @author: 张斌能
 * @create: 2018-11-14 20:10:02
 **/

public class CreateRomdom2HDFS {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        File file = new File("D:\\idea\\bigdata\\data\\random");

        if(!file.exists()){
            try {
                file.createNewFile();
                System.out.println(file.getAbsolutePath()+"创建成功！");
            } catch (IOException e) {
                System.out.println("创建失败");
                e.printStackTrace();
            }
        }else {
            System.out.println("给定的文件：" + file.getAbsolutePath() + "已存在");
        }

        //构建文件输出流对象
        FileOutputStream outputStream = new FileOutputStream(file);
        //使用StringBuffer高效拼凑字符串
        StringBuffer sb = new StringBuffer();
                //持续往文件中输入内容
                for(long i =01;i < 100L; i++){//写入的数字的行数
                    //控制单行输入
                    for(int j = 0;j <10; j++){
                        if(j !=9){
                            sb.append(random.nextInt(10)+1+",");
                        }else {
                            sb.append(random.nextInt(10)+1+"");
                        }
                    }
                    //在每行的行尾拼接上换行符
                    sb.append(System.lineSeparator());
                    //每构建一1000行文本，往java outputstream对象中写入一次
                    if(i % 10 == 0){
                        outputStream.write(sb.toString().getBytes());
                        outputStream.flush();
                        System.out.println("已经写了"+i+"行");
                        sb.setLength(0);
            }
        }
        //最后一次写入flush，保证所有的内容度写道hdfs上
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();

        outputStream.close();
        System.out.println("整体写入程序完成！");
    }
}
