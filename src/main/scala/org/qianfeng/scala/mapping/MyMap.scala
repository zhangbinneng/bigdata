package org.qianfeng.scala.mapping

import java.awt.Font
import java.util

import scala.collection.mutable

object MyMap {
  def main(args: Array[String]): Unit = {
    /*------------------定义和构造映射----------------------------*/
    //定义 值不可变映射Map[String,Int]
    val scoresVal = Map("Alice" ->10,"Bob" -> 3, "Cindy" -> 8)
    //"上述示例中的 -> 符号用来给key赋值，实际上也使代码更加易读，可以理解为一个key指向这个key的值",本例也可以写成如下形式：
    val s = Map(("Alice",10),("Bob",3),("Cindy",8))
    //定义 值可变的映射（注意：这里的Map跟上述的不可变Map不同，二者没有在同一个包中，只是名字相同）
    val scoresVar = scala.collection.mutable.Map("Alice" -> 10,"Bob" -> 3,"Cindy" -> 8)
    //定义空映射
    val scoreEmpty = new mutable.HashMap[String,Int]()

    val bobsScore = scoresVal("Bob")
    println(bobsScore)

    val bobsScore1 = if (scoresVal.contains("Bob")) scoresVal("Bob") else 0
    println(bobsScore1)
    val bobsScore2 = scoresVal.getOrElse("Bob",0)
    println(bobsScore2)
    /*---------------更新映射中的值------------------------------*/
    /**
      * 注意：只能对可变类型的映射中的值进行更改
      */
    scoresVar("Bob") = 10
    scoresVar("Mmy") = 10
    scoresVar += ("Mmy1" -> 10,"Mmy2" -> 11)
    //移除Mmy
    scoresVar -= "Mmy"
    //以一个不可变得映射为基础，对其副本做更改操作后得到一个新的映射（原映射没有被改变）
    val scoresVal2 = scoresVal + ("Bob" -> 10,"Mmy" -> 10)
    //同上，我们也可以定义一个不可变得映射进行删除操作，得到一个新的映射（原映射同样没变）
    val scoresVal3 = scoresVal - "Bob"

    /**
      * 虽然我们对不可变的映射操作后会额外生成新的映射，
      * 但是老的映射和新的映射共享大部分结构，所以这种
      * 操作的效率并不是很低。
      */
    /*-------------------迭代映射----------------------------------*/
    //迭代map
    for ((k,v) <- scoresVar)
      print(k + ":" + v + "\t")
    println

    for (elem <- scoresVar)
      print(elem._1 + ":" + elem._2 + "\t")
    println
    //迭代key
    for (k <- scoresVar.keySet)
      print(k + "\t")
    println
    //迭代value
    for (v <- scoresVar.values)
      print(v + "\t")
    println
    //翻转一个map中的key和values
    val newMap = for ((k,v) <- scoresVar) yield (v,k)

    //上述语句的等效写法如下：
    scoresVar.map((x:Tuple2[String,Int])=>{(x._2,x._1)})
    scoresVar.map(x=>(x._2,x._1))
    scoresVar.map(_.swap)
    //迭代翻转后的map
    for ((k,v) <- newMap)//
      print(k + ":" + v + "\t")
    println

    /*------------已排序映射---------------------*/
    /*
      * scala中的map默认实现是哈希map
      * 如果业务中使用的map没有很好地哈希函数
      * 或者需要顺序的使用所有的建就需要一个树map.
      */
    //得到一个不可变得树形映射（键有序）
    val scoresTreeMapImmutable = scala.collection.immutable.SortedMap("Alice" -> 10,"Cindy" -> 8,"Bob" -> 3)
    for ((k,v) <- scoresTreeMapImmutable) print(k + ":"+ v + "\t")
    println
    //如果按照插入的时候的顺序访问所有的键，可以使用LinkedHashMap.(其实这要求数据输入时要有序)
    val months = scala.collection.mutable.LinkedHashMap("Jan" -> 1,"Feb" -> 2, "Mar" -> 3,"Apr" -> 4)
    for ((k,v) <- months)
      print(k + ":" + v + "\t")
    println
    /*-------------------与java互操作------------------------------*/
    /**
      * 将java中的映射转换成scala中映射
      */
    import scala.collection.JavaConversions.mapAsScalaMap
    //将java中的TreeMap转换成scala中的映射
    val scores:scala.collection.mutable.Map[String,Int] =
      new util.TreeMap[String,Int]()
    import scala.collection.JavaConversions.propertiesAsScalaMap
    //从java.org.qianfeng.spark.util.Properties到scala中的Map[String,String]的转换
    val props:scala.collection.Map[String,String] = System.getProperties()

    /**
      * 将scala映射传递给预期java映射
      */
    import scala.collection.JavaConversions.mapAsJavaMap
    import java.awt.font.TextAttribute._
    val attrs = Map(FAMILY -> "Serif",SIZE -> 12)//scala映射
    val font = new Font(attrs) //该方法预期一个java映射
  }

}
