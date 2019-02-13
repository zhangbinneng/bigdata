package org.qianfeng.scala.home

import scala.io.Source

object Home {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    val source = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
    val lineIterator = source.mkString.split(System.lineSeparator())

//    val source1 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//    val lines:Array[String] = source1.mkString.split(System.lineSeparator())
//    var scoreCount = 0.0	 //记录语文成绩总分
//    val chineseArr:Array[String]= lines.filter(_.split(" ")(4).equals("chinese"))  //
//    chineseArr.foreach(println)
//    chineseArr.foreach(lineStr => {scoreCount += lineStr.split(" ")(5).toInt})
//    println("语文科目的平均成绩是：" + (scoreCount/chineseArr.length).formatted("%.2f"))

//    val mathArr = lines.filter(_.split(" ")(4).equals("math"))
//      mathArr.foreach(lineStr => {scoreCount += lineStr.split(" ")(5).toInt})
//    val avg = (scoreCount/mathArr.length).formatted("%.2f")
//    println("数学科目的平均成绩是：" + avg)
//      val englishArr = lines.filter(_.split(" ")(4).equals("english"))
//      englishArr.foreach(lineStr => {scoreCount += lineStr.split(" ")(5).toInt})
//      val avg = (scoreCount/englishArr.length).formatted("%.2f")
//      println("数学科目的平均成绩是：" + avg)
//    println("个人的平均成绩是：")
//    lines.map(lineStr => (lineStr.split(" ")(1),lineStr.split(" ")(5).toDouble))
//      .toList  // (李大四,List((李大四,50.0), (李大四,60.0), (李大四,50.0)))
//      .groupBy(_._1)//根据姓名分组
//      //将分数聚合
//      .foreach(stuMap => {
//      println((stuMap._2.map(_._2).reduce(_+_)/stuMap._2.length).formatted("%.2f"))
//    })
//    lines.map(lineStr => (lineStr.split(" ")(0),lineStr.split(" ")(5).toDouble))
//      .groupBy(_._1)
//      .filter(_._1.equals("12")).map(_._2)
//      .foreach(t => {println((t.map(_._2).sum/t.map(_._2).length).formatted("%.2f"))})
//    lines.map(lineStr => (lineStr.split(" ")(0)+","+lineStr.split(" ")(3),lineStr.split(" ")(5).toDouble))
//      .groupBy(_._1)
//      .filter(_._1.split(",")(1).equals("男"))
//      .filter(_._1.split(",")(0).equals("12"))
//      .foreach(t => println((t._2.map(_._2).reduce(_+_)/t._2.length).formatted("%.2f")))


    //1.2一共有多少个等于20岁的人参加考试
//    println(tuple.filter(_._2.toInt==20).count(_=>true))
    //1.3一共有多少个大于20岁的人参加考试
//    println(tuple.filter(_._2.toInt>20).count(_=>true))
    //2. 一共有多个男生参加考试？
//    println(source.getLines.map(x=>{(x.split(" ")(1),x.split(" ")(3))}).filter(_._2.contains("男")).toSet.count(_=>true))
//    println(source.getLines.map(x=>(x.split(" ")(1),x.split(" ")(3))).filter(_._2.contains("女")).toSet.count(_=>true))
    //3. 12班有多少人参加考试？
    //3.1 13班有多少人参加考试？
//    println(source.getLines.map(x=>(x.split(" ")(0),x.split(" ")(1))).filter(_._1.contains("12")).toSet.count(_=>true))
//    println(source.getLines.map(x=>(x.split(" ")(0),x.split(" ")(1))).filter(_._1.contains("13")).toSet.count(_=>true))
  //4. 语文科目的平均成绩是多少？
    //4.1 数学科目的平均成绩是多少？
    //4.2 英语科目的平均成绩是多少？
//val result = source
//  .getLines
//  .map(lines=>{
//    if ("chinese".equals(lines.split(" ")(4))){
//      (lines.split(" ")(4),lines.split(" ")(5).toDouble)
//    }
//  })
//    if (result.contains(())){
//            println(result.size-1)
//          }else{
//            println(result.size)
//          }
//    val result = source
//  .getLines
//  .map(lines=>(lines.split(" ")(4),lines.split(" ")(5).toDouble))
//  .filter(_._1.contains("chinese"))
//    var all = 0.0
//    var size = 0
//    for (score <- result) {
//    all += score._2
//      size += 1
//    }
//    println(all/size)

//    5. 单个人平均成绩是多少？

//    val source1 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//    val per = source1.getLines.map(lines=>(lines.split(" ")(1),lines.split(" ")(5).toDouble)).toList.groupBy(_._1)
//    for (i <- per) {
//      println(i._1+"的平均成绩是："+(i._2.map(_._2).sum)/i._2.length)
//    }
    //6. 12班平均成绩是多少？
    //6.1 12班男生平均总成绩是多少？
    //6.2 12班女生平均总成绩是多少？
    //6.3 同理求13班相关成绩
//    val source6 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//    val per1 = source6.getLines.map(lines=>(lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(5).toDouble)).toList.groupBy(_._1).filter(_._1.contains("12"))
//
//    for (i <- per1){
//      var avg = i._2.map(_._3).reduce(_+_)/i._2.map(_._2).toSet.size
//      println(avg)
//    }
//    6.1 12班男生平均总成绩是多少？
//    val source7 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//    val per7 = source7.mkString.split(System.lineSeparator())
//      .map(lines=>(lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(3),lines.split(" ")(5).toDouble))
//      .filter(_._3.contains("男"))
//      .groupBy(_._1)
//      .filter(_._1.contains("12"))
//    for (i <- per7){
//      println((i._2.map(_._4).reduce(_+_)/i._2.map(_._2).distinct.size).formatted("%.2f"))
//    }


    //7. 全校语文成绩最高分是多少？
    //7.1 12班语文成绩最低分是多少？
    //7.2 13班数学最高成绩是多少？
//    val source8 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//    val per8 = source8.getLines
//      .map(lines=>(lines.split(" ")(4),lines.split(" ")(5).toDouble))
//      .toList
//      .filter(_._1.contains("chinese")).groupBy(_._1)
//    for (i <- per8){
//      println(i._2.map(_._2).max)
//    }
    //8. 总成绩大于150分的12班的女生有几个？

//        val source9 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//        val per9 = source9.getLines
    // .map(lines=>(lines.split(" ")(0),lines.split(" ")(1),lines.split(" ")(3),lines.split(" ")(5).toDouble))
    // .toList
    // .groupBy(_._1)
    // .filter(_._1.contains("12"))
//        for (i <- per9) {
//         val list = i._2.filter(_._3.equals("女")).map(x=>(x._2,x._4)).groupBy(_._1)
//          var sum =0
//          for (l <- list) {
//            if(l._2.map(_._2).reduce(_+_)>150) sum += 1
//          }
//          println(sum)
//        }

  //  9. 总成绩大于150分，且数学大于等于70，且年龄大于等于20岁的学生的平均成绩是多少？

//    val source10 = Source.fromFile("D:\\idea\\bigdata\\data\\1.txt","UTF-8")
//    val per10 = source10.getLines.map(x=>{
//      val lines = x.split(" ");
//      ((lines(0)+lines(1),lines(2)),(lines(4),lines(5)))
//    })
//      .toList.groupBy(_._1)
//      .map(x => {
//        (x._1,(x._2.map(_._2).filter(_._1 == "math").toMap),x._2.map(_._2._2.toDouble).sum)
//      })
//      .filter(x => x._1._2.toInt >= 20 && x._3 > 150.0 && x._2("math").toInt <= 70)
//      .map(_._3.toDouble)
//    println(per10.reduce(_+_)/per10.size)

  }
}
