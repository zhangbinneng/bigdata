package org.qianfeng.spark.home.mr2spark

import org.qianfeng.spark.util.SparkUtil

object EveryOneAvgScore {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(EveryOneAvgScore.getClass.getSimpleName,"local[*]")
    //计算每个人的凭据分数
    sc.textFile("D:\\idea\\bigdata\\data\\readme").map(line=>{
      val lineArr =line.split(" ")
      var score = 0.0
      for(i<- 1 until  lineArr.length) {
        score += lineArr(i).toDouble
      }
     (lineArr(0) + "平均分是:" + (score/3).formatted("%.2f"))
    }).foreach(println)
  }
}
