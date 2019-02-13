package org.qianfeng.spark.home.mr2spark

import org.qianfeng.spark.util.SparkUtil

object TopOne {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(TopOne.getClass.getSimpleName,"local[*]")
    println(sc.textFile("data/top1").flatMap(_.split(",")).max())
    sc.textFile("data/top1").flatMap(_.split(",")).top(3).foreach(println)
  }
}
