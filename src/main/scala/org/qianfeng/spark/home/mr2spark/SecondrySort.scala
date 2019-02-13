package org.qianfeng.spark.home.mr2spark

import org.apache.spark.Partitioner
import org.qianfeng.spark.util.SparkUtil

object SecondrySort {
  def main(args: Array[String]): Unit = {
    val sc = SparkUtil.getSparkContext(SecondrySort.getClass.getSimpleName,"local[*]")
    sc.textFile("data/secondsort").map(per=>(per.split(",")(0),per.split(",")(1),per.split(",")(3)))
      .map(per=>(per._1,new SortClass(per._2.toInt,per._3.toInt)))
      .sortByKey()
      .map(per=>(per._1,per._2.x,per._2.y))
      .foreach(println)
  }
}

class SortClass(val x: Int,val y: Int) extends Serializable with Ordered[SortClass]{
  override def compare(that: SortClass): Int = {
    if (!this.x.equals(that.x)){
      this.x.hashCode - that.x.hashCode
    }
    else {
      this.y - that.y
    }
  }
}