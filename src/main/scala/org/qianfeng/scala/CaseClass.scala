package org.qianfeng.scala

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
case class Stu(classid:String,name:String,age:String,sex:String,chinese_mark:String,math_mark:String,english_mark:String)
object CaseClass {
  def examOfPerson(stuArr:ArrayBuffer[Stu]): Unit ={
      println("一共有" + stuArr.size + "人参加考试")
  }
  def examOfPersonAgeless20(stuArr:ArrayBuffer[Stu]) ={
    println("一共有" + stuArr.filter(_.age.toInt <20).size + "人小于20岁的人参加考试")
  }
  def examOfPersonAgeequal20(stuArr:ArrayBuffer[Stu]) ={
    println("一共有" + stuArr.filter(_.age.toInt ==20).size + "人等于20岁的人参加考试")
  }
  def examOfPersonAgeMorethan20(stuArr:ArrayBuffer[Stu]) ={
    println("一共有" + stuArr.filter(_.age.toInt >20).size + "人大于20岁的人参加考试")
  }
  def examOfManPerson(stuArr:ArrayBuffer[Stu]) ={
    println("一共有" + stuArr.filter(_.sex.equals("男")).size + "个男生参加考试")
  }
  def examOfWomanPerson(stuArr:ArrayBuffer[Stu]) ={
    println("一共有" + stuArr.filter(_.sex.equals("女")).size + "个女生参加考试")
  }
  def examOfPersonClass12 (stuArr:ArrayBuffer[Stu]) ={
    println("12班有" + stuArr.filter(_.classid.contains("12")).size + "人参加考试")
  }
  def examOfPersonClass13 (stuArr:ArrayBuffer[Stu]) ={
    println("13班有" + stuArr.filter(_.classid.contains("13")).size + "人参加考试")
  }
  def chineseAvgMark(stuArr:ArrayBuffer[Stu]) ={
    var chinesescore = 0.0
    stuArr.foreach(chinesescore +=_.chinese_mark.toDouble)
    println("语文成绩的平均分是:" + (chinesescore/stuArr.size).formatted("%.2f"))
  }
  def mathAvgMark(stuArr:ArrayBuffer[Stu]) ={
    var mathscore = 0.0
    stuArr.foreach(mathscore +=_.math_mark.toDouble)
    println("数学成绩的平均分是:" + (mathscore/stuArr.size).formatted("%.2f"))
  }
  def englishAvgMark(stuArr:ArrayBuffer[Stu]) ={
    var englishscore = 0.0
    stuArr.foreach(englishscore +=_.math_mark.toDouble)
    println("英语成绩的平均分是:" + (englishscore/stuArr.size).formatted("%.2f"))
  }
  def personAvgMark(stuArr:ArrayBuffer[Stu]) ={
    stuArr.foreach(stu =>{
      var personallscore = 0.0
      personallscore = stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble
      println(stu.name + "的个人平均分是:" + (personallscore/3).formatted("%.2f"))
    })
  }
  def avgMarkClass12(stuArr:ArrayBuffer[Stu])={
    var allscore = 0.0
    stuArr.filter(_.classid.equals("12")).foreach(stu =>{
      allscore += stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble
    })
    println("12班级的平均成绩:" + (allscore/stuArr.filter(_.classid.equals("12")).size).formatted("%.2f"))
  }
  def avgMarkManClass12(stuArr:ArrayBuffer[Stu])={
    var allscore = 0.0
    stuArr.filter(_.classid.equals("12")).filter(_.sex.equals("男")).foreach(stu =>{
      allscore += stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble
    })
    println("12班男生的平均总成绩:" + (allscore/stuArr.filter(_.classid.equals("12")).filter(_.sex.equals("男")).size).formatted("%.2f"))
  }
  def avgMarkWomanClass12(stuArr:ArrayBuffer[Stu])={
    var allscore = 0.0
    stuArr.filter(_.classid.equals("12")).filter(_.sex.equals("女")).foreach(stu =>{
      allscore += stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble
    })
    println("12班女生的平均总成绩:" + (allscore/stuArr.filter(_.classid.equals("12")).filter(_.sex.equals("女")).size).formatted("%.2f"))
  }
  def chineseMarkMaxOfSchool(stuArr:ArrayBuffer[Stu])={
    var chineseArr:ArrayBuffer[Double] = ArrayBuffer()
    stuArr.foreach(stu => chineseArr.append(stu.chinese_mark.toDouble))
    println("全校语文成绩最高分是:" + chineseArr.max)
  }
  def chineseMarkMinClass12(stuArr:ArrayBuffer[Stu])={
    var chineseArr:ArrayBuffer[Double] = ArrayBuffer()
    stuArr.filter(_.classid.equals("12")).foreach(stu => chineseArr.append(stu.chinese_mark.toDouble))
    println("12班语文成绩最低分是:" + chineseArr.min)
  }
  def mathMarkMaxClass13(stuArr:ArrayBuffer[Stu])={
    var mathArr:ArrayBuffer[Double] = ArrayBuffer()
    stuArr.filter(_.classid.equals("13")).foreach(stu => mathArr.append(stu.math_mark.toDouble))
    println("13班数学成绩最高分是:" + mathArr.max)
  }
  def allMarkMoreThan150OfClass12(stuArr:ArrayBuffer[Stu])={
    println("12班总成绩大于150分的女生有:" +
    stuArr.filter(_.classid.equals("12")).filter(_.sex.equals("女")).filter(stu =>(stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble)>150).size
    + "个"
    )
  }
  def allMarkMoreThan150AndMathless70AndAgeMoreThan20(stuArr:ArrayBuffer[Stu])={
    var allscore = 0.0
    val stuarr = stuArr.filter(_.age.toInt >= 20).filter(_.math_mark.toDouble <= 70)
      .filter(stu =>( stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble)>150)
    stuarr.foreach(stu =>( allscore += stu.chinese_mark.toDouble + stu.math_mark.toDouble + stu.english_mark.toDouble))
    println("总成绩大于150分，且数学小于等于70，且年龄大于等于20岁的学生的平均成绩:" + (allscore/stuarr.size).formatted("%.2f"))
  }


  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("data/1.txt","UTF-8").mkString
    val linesArr = source.split(System.lineSeparator())
    val stuArr:ArrayBuffer[Stu] = ArrayBuffer()
    linesArr.map(line => {
      (line.split(" ")(0),line.split(" ")(1),line.split(" ")(2),line.split(" ")(3),line.split(" ")(4),line.split(" ")(5))
    }).toList.groupBy(_._2).values.foreach(x =>{
      stuArr.++= (Traversable(Stu(x(0)._1,x(0)._2,x(0)._3,x(0)._4,x(0)._6,x(1)._6,x(2)._6)))
    })
    //1.一共有多少人参加考试
//    examOfPerson(stuArr)
    //1.1 一共有多少个小于20岁的人参加考试？
    examOfPersonAgeless20(stuArr)
    //1.2 一共有多少个等于20岁的人参加考试？
    examOfPersonAgeequal20(stuArr)
    //1.3 一共有多少个大于20岁的人参加考试？
    examOfPersonAgeMorethan20(stuArr)
    //2. 一共有多个男生参加考试？
    examOfManPerson(stuArr)
    //2.1 一共有多少个女生参加考试？
    examOfWomanPerson(stuArr)
    //3 12班有多少人参加考试？
    examOfPersonClass12(stuArr)
    //3.1 13班有多少人参加考试？
    examOfPersonClass13(stuArr)
    //4.语文科目的平均成绩是多少？
    chineseAvgMark(stuArr)
    //4.1 数学科目的平均成绩是多少？
    mathAvgMark(stuArr)
    //4.2 英语科目的平均成绩是多少？
    englishAvgMark(stuArr)
    //5. 单个人平均成绩是多少？
    personAvgMark(stuArr)
    //6. 12班平均成绩是多少？
    avgMarkClass12(stuArr)
    //6.1 12班男生平均总成绩是多少？
    avgMarkManClass12(stuArr)
    //6.2 12班女生平均总成绩是多少？
    avgMarkWomanClass12(stuArr)
    // 6.3 同理求13班相关成绩

    //7. 全校语文成绩最高分是多少？
    chineseMarkMaxOfSchool(stuArr)
    //7.1 12班语文成绩最低分是多少？
    chineseMarkMinClass12(stuArr)
    //7.2 13班数学最高成绩是多少？
    mathMarkMaxClass13(stuArr)
    //8. 总成绩大于150分的12班的女生有几个？
    allMarkMoreThan150OfClass12(stuArr)
    //9. 总成绩大于150分，且数学小于等于70，且年龄大于等于20岁的学生的平均成绩是多少？
    allMarkMoreThan150AndMathless70AndAgeMoreThan20(stuArr)
  }
}
