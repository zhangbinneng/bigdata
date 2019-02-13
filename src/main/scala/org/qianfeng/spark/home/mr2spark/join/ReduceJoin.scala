package org.qianfeng.spark.home.mr2spark.join

import org.apache.spark.rdd.RDD
import org.qianfeng.spark.util.SparkUtil

object ReduceJoin {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(MapJoin.getClass.getSimpleName,"local[*]")

    val dept = sc.textFile("data/dept").map(x=>{
      (x.split(",")(0),(x.split(",")(1),x.split(",")(2)))
    }).collectAsMap
    var broadCastMap = sc.broadcast(dept)

    val emp = sc.textFile("data/emp").map(x=>{
      (x.split(",")(2),x)
    })
  }
}
