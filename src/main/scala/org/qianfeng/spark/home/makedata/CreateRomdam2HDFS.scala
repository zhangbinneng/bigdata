package org.qianfeng.spark.home.makedata

import java.io.{File, FileOutputStream, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object CreateRomdam2HDFS {
  //定义一个main方法
  def main(args: Array[String]): Unit = {
    //生成一万行数据
    for(i <- 1 to 1000){
      //初始化一个字符串序列用于后期存放产生的数据
      val stringBuffer:StringBuffer = new StringBuffer
      //每一行生成10个数字
      for(j <- 1 to 10){
        val num = Random.nextInt(100)
        if(j % 10 == 0){
          stringBuffer.append(num.toString )
          stringBuffer.append(System.lineSeparator())
        }else{
          stringBuffer.append(num.toString+",")
        }
      }
      // println(stringBuffer)
      writerData(stringBuffer)
    }
    //定义一个向指定文件中写入内容的方法
    def writerData(stringBuffer: StringBuffer) ={
      //true表示向后追加
      val writer = new PrintWriter(new FileOutputStream("D:\\idea\\bigdata\\data\\random",true))
      writer.print(stringBuffer)
      //关闭流
      writer.close()
    }
  }
}