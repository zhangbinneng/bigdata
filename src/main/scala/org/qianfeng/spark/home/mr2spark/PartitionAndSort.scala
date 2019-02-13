package org.qianfeng.spark.home.mr2spark

import org.apache.spark.Partitioner
import org.qianfeng.spark.util.SparkUtil

object PartitionAndSort {
  def main(args: Array[String]): Unit = {
    val partitioner = new Partition(2)
        val sc = SparkUtil.getSparkContext(PartitionAndSort.getClass.getSimpleName,"local[*]")
    val source = sc.textFile("data/partition").flatMap(_.split(",")).map(x=>{
      (x.toInt,1)
    }).partitionBy(partitioner)
      .mapPartitions(x=>x.toList.sortBy(_._1).toIterator).map(_._1)
    println(source.collect().toBuffer)
  }
}

class Partition(partitions:Int) extends Partitioner{
  override def numPartitions: Int = partitions
  override def getPartition(key: Any): Int = {
    if (key.asInstanceOf[Int]>50){
      1
    }else {
      0
    }
  }
}