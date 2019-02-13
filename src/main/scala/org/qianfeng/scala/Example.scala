package org.qianfeng.scala

import scala.io.Source

object Example {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("data/1.txt")
    val lines = source.mkString.split(System.lineSeparator())

    //需求如下：
    //1.一共有多少人参加考试
//    val num:Int = lines.map(t => {val name = t.split(" ");name(1)}).distinct.size
//    println("一共有" + num + "人参加考试")
//
//    //1.1 一共有多少个小于20岁的人参加考试？
//
////    val ages = lines.map(t => {val age = t.split(" "); age(0) + "," + age(1) + "," + age(2)})
////    val filterAge = ages.filter(_.split(",")(2).toInt < 20).distinct
////    println("一共有" + filterAge.size + "人小于20岁的人参加考试")
//
//    //1.2 一共有多少个等于20岁的人参加考试？
////    val filterAge = ages.filter(_.split(",")(2).toInt == 20).distinct
////    println("一共有" + filterAge.length + "人等于20岁的人参加考试")
//
//    //1.3 一共有多少个大于20岁的人参加考试？
////    val filterAge = ages.filter(_.split(",")(2).toInt > 20).distinct
////    println("一共有" + filterAge.length + "人大于20岁的人参加考试")
//
//    //2. 一共有多个男生参加考试？
//    val gender = lines.map(t => {val s = t.split(" "); s(0) + "," + s(1) + "," + s(3)})
//    val man = gender.filter(_.split(",")(2) == "男").distinct
//    println("一共有" + man.length + "个男生参加考试")
//
//    //2.1 一共有多少个女生参加考试？
//    val women = gender.filter(_.split(",")(2) == "女").distinct
//    println("一共有" + women.length + "个女生参加考试")
//
//    //3 12班有多少人参加考试？
////    val filterClass = lines.map(t => {val age = t.split(" "); age(0) + "," + age(1)})
////    val cla = filterClass.filter(_.split(",")(0) == "12").distinct
////    println("12班有" + cla.length + "人参加考试")
//
//    //3.1 13班有多少人参加考试？
////    val cla = filterClass.filter(_.split(",")(0) == "13").distinct
////    println("13班有" + cla.length + "人参加考试")
//
//    //4.语文科目的平均成绩是多少？
//    var scoreCount = 0.0	 //记录语文成绩总分
//    val chineseArr = lines.filter(_.split(" ")(4).equals("chinese"))  //目的：过滤出每个人的语文成绩信息  结果数据每一行：12 张三 25 男 chinese 50 返回的是字符串数组
//    chineseArr.foreach(lineStr => {scoreCount += lineStr.split(" ")(5).toDouble}) //遍历字符串数组，取出分数求和
//    println("语文科目的平均成绩是：" + (scoreCount/chineseArr.length).formatted("%.2f")) //语文总分除以考试人数 求出平均分，打印
//
//    //4.1 数学科目的平均成绩是多少？
////    val mathArr = lines.filter(_.split(" ")(4).equals("math"))
////    mathArr.foreach(lineStr => {scoreCount += lineStr.split(" ")(5).toDouble})
////    val avg = (scoreCount/mathArr.length).formatted("%.2f")
////    println("数学科目的平均成绩是：" + avg)
//    //4.2 英语科目的平均成绩是多少？
//    val englishArr = lines.filter(_.split(" ")(4).equals("english"))
//    englishArr.foreach(lineStr => {scoreCount += lineStr.split(" ")(5).toDouble})
//    val avg = (scoreCount/englishArr.length).formatted("%.2f")
//    println("数学科目的平均成绩是：" + avg)
    //5. 单个人平均成绩是多少？
//    println("个人的平均成绩是：")
//    lines.map(lineStr => (lineStr.split(" ")(1),lineStr.split(" ")(5).toDouble))
//    // (李大四,List((李大四,50.0), (李大四,60.0), (李大四,50.0)))
//      .groupBy(_._1)//根据姓名分组
//      //将分数聚合
//      .foreach(stuMap => {
//      println((stuMap._2.map(_._2).reduce(_+_)/stuMap._2.length).formatted("%.2f"))
//    })
//    //6. 12班科目平均成绩是多少？
//    val score:Array[Double] = lines.map(lineStr => (lineStr.split(" ")(0),lineStr.split(" ")(5).toDouble))
//      .filter(_._1.equals("12")).map(_._2)
//    println((score.sum/score.length).formatted("%.2f"))
//    //6.1 12班男生平均总成绩是多少？
//    val score = lines.map(lineStr => (lineStr.split(" ")(0)+","+lineStr.split(" ")(3),lineStr.split(" ")(5).toDouble))
//      .filter(_._1.split(",")(1).equals("男"))
//      .filter(_._1.split(",")(0).equals("12"))
//      println(score.sum/score.length)
//      .foreach(t => println((t._2.map(_._2).reduce(_+_)/t._2.length).formatted("%.2f")))

    //6.2 12班女生平均总成绩是多少？
//    lines.map(t => (t.split(" ")(0)+","+t.split(" ")(3),t.split(" ")(5).toDouble))
//      .groupBy(_._1)
//      .filter(_._1.split(",")(1).equals("女"))
//      .filter(_._1.split(",")(0).equals("12"))
//      .foreach(t => println((t._2.map(_._2).reduce(_+_)/t._2.length).formatted("%.2f")))
//    // 6.3 同理求13班相关成绩
//
//
//    //7. 全校语文成绩最高分是多少？
//    lines.map(lineStr => (lineStr.split(" ")(4),lineStr.split(" ")(5).toDouble))
//      .filter(_._1.equals("chinese"))
//      .groupBy(_._1)
//      .foreach(t => {println(t._2.max)})
//
//    //7.1 12班语文成绩最低分是多少？
//    lines.map(lineStr => (lineStr.split(" ")(0)+","+lineStr.split(" ")(4),lineStr.split(" ")(5).toDouble))
//      .filter(_._1.split(",")(0).equals("12"))
//      .filter(_._1.split(",")(1).equals("chinese"))
//      .groupBy(_._1)
//      .foreach(t => println(t._2.min))
//    //7.2 13班数学最高成绩是多少？
//    lines.map(lineStr => (lineStr.split(" ")(0)+","+lineStr.split(" ")(4),lineStr.split(" ")(5).toDouble))
//      .groupBy(_._1)
//      .filter(_._1.split(",")(0).equals("13"))
//      .filter(_._1.split(",")(1).equals("math"))
//      .foreach(t => println(t._2.max))
//
//    //8. 总成绩大于150分的12班的女生有几个？
//    println(
//      lines.map(lineStr => (lineStr.split(" ")(0)+","+lineStr.split(" ")(1)+","+lineStr.split(" ")(3),lineStr.split(" ")(5).toDouble))
//        .filter(_._1.split(",")(0).equals("12"))
//        .filter(_._1.split(",")(2).equals("女"))
//        .groupBy(_._1)
//        .filter(_._2.map(_._2).sum > 150)
//        .size
//    )
//
//
//    //9. 总成绩大于150分，且数学小于等于70，且年龄大于等于20岁的学生的平均成绩是多少？
//    //筛选出总成绩大于150分，且数学小于等于70，且年龄大于等于20岁的学生
        val source10 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
        val per10 = source10.getLines.map(x=>{
          val lines = x.split(" ");
          ((lines(0)+lines(1),lines(2)),(lines(4),lines(5)))
        }).filter(_._1._2.toInt>=20)
          .toList.groupBy(_._1)
          .map(x => {
            (x._1,(x._2.map(_._2).filter(_._1 == "math").toMap),x._2.map(_._2._2.toDouble).sum)
          })
          .filter(x => x._3 > 150.0 && x._2("math").toInt <= 70)
          .map(_._3.toDouble)
        println(per10.reduce(_+_)/per10.size)
  }
}
