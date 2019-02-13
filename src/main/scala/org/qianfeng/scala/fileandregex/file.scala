package org.qianfeng.scala.fileandregex

import java.io.{File, FileInputStream, PrintWriter}

object file {
  def main(args: Array[String]): Unit = {
    /*------------------读取文件中所有的行----------------------*/
    import scala.io.Source
    //读取桌面上的1.txt文件， 这里的参数：“文件地址”也可以替换成java.io.file对象
    val source = Source.fromFile("data/1.txt","UTF-8")
//    val mkStringResult = source.mkString;
//    println(mkStringResult)
    //获取source上的行文本迭代器（在使用迭代器之前不能进行mkString操作，此操作会将迭代器中的内容清空）
//    val lineIterator:Iterator[String] = source.getLines
//    for (line <- lineIterator) {
//      println(line)
//      println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
//    }
    println("-------------------------------------------------------------------------")
    //直接获取source上的所有文本（迭代器执行完毕之后source中的内容会被清空，所以mkString结果为空，此时输出的内容为空）
//      val sourceStr = source.mkString
//    println(sourceStr)
    //关闭source
//    source.close()
    /*--------------------------------读取文件所有的字符------------------------------------------*/
    /**
      * 直接遍历Source对象即可
      * 示例如下：
      */
    //读取桌面上的1.txt文件   这里参数："文件地址" 也可以替换成java.io.file对象
    val source1 = Source.fromFile("data/1.txt","UTF-8")
//    for (i <- source1) println(i) // 按照换行形式打印每个字符
//    for (i <- source1) print(i) // 按照非换行形式打印每个字符
    /*-------------------------只查看而不处理字符----------------------------------*/
    /**
      * 只查看不处理字符可以使用head放大
      * 示例如下：
      */
    val source2 = Source.fromFile("data/1.txt","UTF-8")
    //调用buffered方法为调用head方法做准备
    val iter:BufferedIterator[Char] = source2.buffered
//    while (iter.hasNext) {
//      val strHead = iter.head.toString //head方法的返回值类型是char,需要tostring
//      if (strHead == "~"){//如果碰到的字符是波浪线就不输出了
//        iter.drop(1)
//      }else {
//        print(iter.next())
//      }
//      println
//      println(iter)//循环遍历完成之后迭代器内容已被清空，表名next()方法本质是返回结果并删除
//    }
    /*-----------------读取词法单元和数字--------------------*/
    /**
      * demo文件中存放的字符都是浮点数，多个浮点数之间使用空格分隔
      */
    //将demo文件的内容以空格分隔成数组
//  val arr = Source.fromFile("data/1.txt").mkString.split(" ")
//    //将字符串数组转换成数字数组
//    val arrNums = for (str <- arr) yield str.toDouble
//    arrNums.foreach(println)
//    println
    /*-----------------读取二进制文件-----------------------*/
//    val file = new File("data/1.txt")
//    val in = new FileInputStream(file)
//    val bytes = new Array[Byte](file.length().toInt)
//    in.read(bytes)
//    in.close()
    /*------------------------写入文本文件--------------------------------*/
    val printer =new PrintWriter("data/1.txt")
    println("开始写入流程")
    val start = System.currentTimeMillis()
    for (i <- 1 to 200000) if (i% 100 == 0) printer.print(i + System.lineSeparator())
  }
}
