package org.qianfeng.spark.home.mr2spark

import org.qianfeng.spark.util.SparkUtil

object CountAll {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(CountAll.getClass.getSimpleName,"local[*]")
    sc.textFile("data/random").flatMap(_.split(",")).map((_,1)).reduceByKey(_+_).foreach(println)
  }
}
