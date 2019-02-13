package org.qianfeng.spark.home

import org.qianfeng.spark.util.SparkUtil

import scala.collection.mutable.ArrayBuffer

object SparkHome {

  def main(args: Array[String]): Unit = {
    val array:ArrayBuffer[Any] = ArrayBuffer()
//
//    val sc = SparkUtil.getSparkContext(SparkHome.getClass.getSimpleName,"local[*]")
//
//    println("参加考试人数:"+sc.textFile("/spark/1.txt").map(lines=>{lines.split(" ")(1)}).distinct.count)
    val sc = SparkUtil.getHDFSSparkContext("SparkHome".getClass.getSimpleName)

//    1. 一共有多少人参加考试？
    array.append("参加考试人数:"+sc.textFile("/spark/1.txt").map(lines=>{lines.split(" ")(1)}).distinct.count)
    //1.2 一共有多少个等于20岁的人参加考试？
    array.append("参加考试的人等于20岁的人数:"+sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(1) ,lines.split(" ")(2))).filter(_._2.toInt==20).distinct.count)
    //1.3 一共有多少个大于20岁的人参加考试？
    array.append("大于20岁的人参加考试的人数:"+sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(1) ,lines.split(" ")(2))).filter(_._2.toInt>20).distinct.count)
    //2. 一共有多个男生参加考试？
    array.append("参加考试的男生人数:"+sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(1) ,lines.split(" ")(3))).filter(_._2.equals("男")).distinct.count)
    //2.1 一共有多少个女生参加考试？
    array.append("参加考试的女生人数:"+ sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(1) ,lines.split(" ")(3))).filter(_._2.equals("女")).distinct.count)
    //3 12班有多少人参加考试？
    array.append("12班参加考试的人数:"+sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(0) ,lines.split(" ")(1))).filter(_._1.equals("12")).distinct.count)
    //3.1 13班有多少人参加考试？
     array.append("13班参加考试人数:"+sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(0) ,lines.split(" ")(1))).filter(_._2.equals("13")).distinct.count)
    //4.语文科目的平均成绩是多少？
    val lineArr8 = sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(4),lines.split(" ")(5).toDouble)).filter(_._1.equals("chinese")).values
    array.append("语文科目平均成绩:"+((lineArr8.reduce(_+_))/lineArr8.count()).formatted("%.2f"))
    //4.1 数学科目的平均成绩是多少？
    val lineArr9 = sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(4),lines.split(" ")(5).toDouble)).filter(_._1.equals("math")).values
    array.append("数学科目平均成绩:"+ ((lineArr8.reduce(_+_))/lineArr8.count()).formatted("%.2f"))
    //4.2 英语科目的平均成绩是多少？
    val lineArr10 = sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(4),lines.split(" ")(5).toDouble)).filter(_._1.equals("english")).values
    array.append("英语科目平均成绩:"+((lineArr8.reduce(_+_))/lineArr8.count()).formatted("%.2f"))
    //5. 单个人平均成绩是多少？
    val linArr11 = sc.textFile("/spark/1.txt").map(lines=>(lines.split(" ")(1),lines.split(" ")(5).toDouble)).groupBy(_._1)
  .foreach(stuMap=>{array.append(stuMap._1+"平均成绩:"+(stuMap._2.map(_._2).reduce(_+_)/stuMap._2.size).formatted("%.2f"))})

    //6. 12班平均成绩是多少？
    val lineArr13 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(5).toDouble)).filter(_._1.equals("12"))
        array.append("12班的平均成绩:"+(lineArr13.map(_._3).reduce(_+_)/lineArr13.map(_._2).distinct.count).formatted("%.2f"))
    //6.1 12班男生平均总成绩是多少？
    val lineArr14 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(3),lines.split(" ")(5).toDouble)).filter(_._1.equals("12")).filter(_._3.equals("男"))
        array.append("12班男生平均总成绩:"+(lineArr14.map(_._4).reduce(_+_)/lineArr14.map(_._2).distinct.count).formatted("%.2f"))
    //6.2 12班女生平均总成绩是多少？
    val lineArr15 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(3),lines.split(" ")(5).toDouble)).filter(_._1.equals("12")).filter(_._3.equals("女"))
         array.append("12班女生平均总成绩:"+(lineArr15.map(_._4).reduce(_+_)/lineArr15.map(_._2).distinct.count).formatted("%.2f"))
    // 6.3 同理求13班相关成绩

    //7. 全校语文成绩最高分是多少？
    val lineArr16 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(4),lines.split(" ")(5).toDouble)).filter(_._1.equals("chinese"))
        array.append("全校语文成绩最高分:"+lineArr16.map(_._2).max)
    //7.1 12班语文成绩最低分是多少？
    val lineArr17 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(0),lines.split(" ")(4),lines.split(" ")(5).toDouble)).filter(_._1.equals("12")).filter(_._2.equals("chinese"))
     array.append("12班语文成绩最低分:"+lineArr17.map(_._3).min)
    //7.2 13班数学最高成绩是多少？
    val lineArr18 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(0),lines.split(" ")(4),lines.split(" ")(5).toDouble)).filter(_._1.equals("13")).filter(_._2.equals("math"))
    array.append("13班数学成绩最高分:"+lineArr18.map(_._3).max)
    //8. 总成绩大于150分的12班的女生有几个？
    val lineArr19 = sc.textFile("/spark/1.txt").map(lines => (lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(3),lines.split(" ")(5).toDouble))
        .filter(_._1.equals("12"))
        .filter(_._3.equals("女"))
        .groupBy(_._1).filter(_._2.map(_._4).sum>150).count
        array.append("12班总成绩大于150分的女生的人数:"+lineArr19)

    //9. 总成绩大于150分，且数学大于等于60，且年龄大于等于20岁的学生的平均成绩是多少？
      val lineArr20 = sc.textFile("/spark/1.txt").map(lines => ((lines.split(" ")(0)+lines.split(" ")(1),lines.split(" ")(2)),(lines.split(" ")(4),lines.split(" ")(5).toDouble)))
        .filter(_._1._2.toInt>=20)
        .groupBy(_._1)
        .map(stu=>{
          (stu._1,(stu._2.map(_._2).filter(_._1.equals("math")).toMap),stu._2.map(_._2._2).sum)
        })
        .filter(x=>x._2("math")>=60 && x._3>150.0)
        .map(_._3)
//        org.qianfeng.scala.array.append("年龄大于等于20，数学成绩大于等于60，总分大于150分的学生的平均成绩:"+(lineArr20.sum()/lineArr20.count()).formatted("%.2f"))
    sc.makeRDD(array,1).saveAsTextFile("/spark/out/")

  }
}
