package org.qianfeng.spark.home.mr2spark

import org.apache.spark.rdd.RDD
import org.qianfeng.spark.util.SparkUtil

object ScoreSimple {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(ScoreSimple.getClass.getSimpleName,"local[*]")
    val score: RDD[String] = sc.textFile("data/readme2").map(line=>{
      (line.split(" ")(1)+","+line.split(" ")(2)+","+line.split(" ")(3))
    }).flatMap(_.split(","))
//    score.sample(false,0.1).foreach(println)
    score.takeSample(false,3).foreach(println)
  }
}
