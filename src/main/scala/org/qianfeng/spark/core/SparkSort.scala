package org.qianfeng.spark.core

import org.apache.spark.Partitioner
import org.qianfeng.spark.util.SparkUtil

object SparkSort {
  var sampleMaxValue = 0
  var sampleMinValue = 0
  var ournumPartitions = 0
  val partitionUnti = 100
  var numbers = 0L
  def main(args: Array[String]): Unit = {
    val spark = SparkUtil.getSparkSession("spark sort")
    //通过不断的转换，获取一个包含所有数字的RDD对象
    val lineRdd = spark.sparkContext.textFile("data/random")
    val arrRDD = lineRdd.flatMap(_.split(","))
    val numberRDD = arrRDD.map(_.toInt)
    //包含的数字的总个数
     numbers = numberRDD.count()
    //按照百分之一的比例进行采样，需要采集的元素的个数
    val sampleNumberDouble = numbers*0.1
    val sampleNumber = sampleNumberDouble.toInt
    //对包含全量数字的RDD对象进行采样
    val sample = numberRDD.takeSample(false,sampleNumber)
    //计算采样数据中的最大值和最小值
    sampleMaxValue =sample.max
    sampleMinValue =sample.min
    //默认每个分区存放1000*1000*100
    ournumPartitions = numbers.toInt/partitionUnti + 2
    if (numbers.toInt%partitionUnti!=0){
      ournumPartitions+=1
    }
    val kvNumbers = numberRDD.map(x=>(MyKey(x),1))
    val newKvNumber = kvNumbers.partitionBy(new MyPartitioner())
    newKvNumber.map((x)=>x._1).saveAsTextFile("/spark/sort2")
  }
  case class MyKey(int:Int) extends Ordered[MyKey] with Serializable{
    override def compare(that: MyKey): Int = {
      that.int-this.int
    }
  }

  class MyPartitioner extends Partitioner{
    override def numPartitions: Int = ournumPartitions

    override def getPartition(key: Any): Int = {
      val keyInt = Integer.parseInt(key.toString)
      //元素在开头分区
      if (keyInt<sampleMinValue){
        0
      }else if (keyInt>sampleMaxValue){//元素在结尾分区
        numPartitions-1
      }else {
        var partitionNumber = (keyInt-sampleMinValue+1)/partitionUnti+1
        if (keyInt%partitionNumber!=0){
          partitionNumber+=1
        }
        partitionNumber-1
      }
    }
  }
}
