package home.mr2spark

import org.qianfeng.spark.util.SparkUtil



object Everyscorepartpercentage {
  //求每个分数段的百分比
  def main(args: Array[String]): Unit = {
//    val sc = SparkUtil.getSparkContext(Everyscorepartpercentage.getClass.getSimpleName,"local[*]")
    val sc = SparkUtil.getHDFSSparkContext(Everyscorepartpercentage.getClass.getSimpleName)
    val all = sc.longAccumulator
    val ac1 = sc.longAccumulator
    val ac2 = sc.longAccumulator
    val ac3 = sc.longAccumulator
    val result = sc.textFile("/spark/readme2").map(line=>{
      val lineArr = line.split(" ")
      (lineArr(1)+","+lineArr(2)+","+lineArr(3))
    }).flatMap(_.split(",")).map(per=>{
          all.add(1)
            if (BigDecimal(per)<60){
              ac1.add(1)
              ("<60",1)
            } else if (BigDecimal(per)>=60 && BigDecimal(per)<90){
              ac2.add(1)
              ("60<=&&<90",1)
            } else {
              ac3.add(1)
              ("90<=&&<=100",1)
          }
    }).top(1)
      println(BigDecimal(ac1.value),BigDecimal(ac2.value),BigDecimal(ac3.value),all.value)
    sc.makeRDD(Seq(BigDecimal(ac1.value)/BigDecimal(all.value)
      ,BigDecimal(ac2.value)/BigDecimal(all.value)
        ,BigDecimal(ac3.value)/BigDecimal(all.value)),1)
      .saveAsTextFile("/spark/out")
  }
}
