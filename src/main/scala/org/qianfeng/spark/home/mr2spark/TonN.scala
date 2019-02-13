package org.qianfeng.spark.home.mr2spark

import org.qianfeng.spark.util.SparkUtil

object TonN {
  val N = 3
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(TonN.getClass.getSimpleName,"local[*]")
     sc.textFile("data/topn2").map(x=>{
      (x.split(",")(0),x.split(",")(1).toInt)
    }).groupByKey().map(x=>(x._1,x._2.toList.sorted(Ordering.Int.reverse)))
       .foreach(x=>{
         println(x._1+","+x._2.take(3))
       })

  }
}
