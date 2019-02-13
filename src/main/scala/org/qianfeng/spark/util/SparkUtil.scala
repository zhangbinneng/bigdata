package org.qianfeng.spark.util

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkUtil {
  def getSparkContext(appName:String, master:String) = {
    val sparkConf = new SparkConf
    sparkConf.setMaster(master)
    sparkConf.setAppName(appName)
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("WARN")
    sc
  }
  def getHDFSSparkContext(appName:String) ={
    System.setProperty("HADOOP_USER_NAME", "root")
    val conf = new SparkConf().setAppName(appName)
      //设置Master_IP
      .setMaster("spark://zk1:7077")
      .setJars(List("D:\\idea\\bigdata\\target\\bigdata-1.0-SNAPSHOT.jar"))
      .setIfMissing("spark.driver.host","10.30.161.70")
    new SparkContext(conf)
  }
  def getSparkSession(appName:String):SparkSession={
      SparkSession
      .builder()
      .appName(appName)
      .master("spark://zk1:7077")
      .config("spark.jars","D:\\idea\\bigdata\\target\\bigdata-1.0-SNAPSHOT.jar")
      .config("spark.driver.host","10.30.161.70")
      .getOrCreate()
  }
}
