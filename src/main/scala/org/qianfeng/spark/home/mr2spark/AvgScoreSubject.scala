package org.qianfeng.spark.home.mr2spark

import org.apache.spark.rdd.RDD
import org.qianfeng.spark.util.SparkUtil

object AvgScoreSubject {
  def main(args: Array[String]): Unit = {
    val  sc = SparkUtil.getSparkContext(AvgScoreSubject.getClass.getSimpleName,"local[*]")

    val rdd = sc.textFile("data/readme2").map(line=>{
      val lineArr = line.split(" ")
      ("chinese_"+lineArr(1).toDouble+",math_"+lineArr(2).toDouble+",english_"+lineArr(3).toDouble)
    }).flatMap(_.split(",")).map(per=>{
      (per.split("_")(0),per.split("_")(1))
    }).groupByKey().map(_._2).foreach(per=>{
      var count = 0
      var scoreall =0.0
      for (i<-per){
        scoreall += i.toDouble
        count +=1
      }
      println((scoreall/count).formatted("%.2f"))
    })


  }
}
