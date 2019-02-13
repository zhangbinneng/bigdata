package org.qianfeng.spark.home.mr2spark.join

import org.apache.spark.rdd.RDD
import org.qianfeng.spark.util.SparkUtil

object MapJoin {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(MapJoin.getClass.getSimpleName,"local[*]")
    val emp: RDD[(String, String)] = sc.textFile("data/emp").map(x=>{
      (x.split(",")(2),x)
    })
    val dept: RDD[(String, (String, String))] = sc.textFile("data/dept").map(x=>{
      (x.split(",")(0),(x.split(",")(1),x.split(",")(2)))
    })
    emp.join(dept).map(_._2).map(x=>{
      (x._1.split(",")(0).toInt,x)
    }).sortByKey().values.map(x=>{
      (x._1,x._2._1,x._2._2)
    }).foreach(println)
  }
}
