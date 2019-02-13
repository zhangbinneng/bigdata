package org.qianfeng.scala.array

object FixedLengthArray {
  def main(args: Array[String]): Unit = {
    /**
      * 为了跟变长数组进行区分，
      * scala中定义一个数组一般称之为数组，变长数组称之为数组缓存
      */
    //如果我们需要一个长度不变的数据，可以使用Scala中的Array.示例如下
    //顶一个10个整数的数组，所有元素初始化为0，注意需要使用new,本质是java方式
    val nums = new Array[Int](10)
    //定义一个10个元素的字符串数组，所有元素都初始化为null
    val strs = new Array[String](10)
    //定义一个长度为2的Array[String]--这里所说的类型是scala自动推导出来的
    val s = Array("Hello","World")//本质是object Array 的apply方法
    //上述语句的完整写法如下：
    val s1 = Array.apply("hello","world")

    //输出数组对象s的第一个元素的值
    println(s(0)) //本质是class Array 上的apply方法
    //上述语句的完整写法如下：
    println(s.apply(0))

    //将数组对象s的第一个元素更改为"GoodBye"
    s(0) = "GoodBye" //本质是class Array 上的update方法，其作用是直接修改数组对象s中的第一个元素的值为“GoodBye”
   //上述语句的完整写法如下：
    s.update(0,"GoodBye")
    //对数组对象s的副本中的第一个元素（）修改为“haha”并且返回成一个新的对象
    val snew = s.updated(0,"haha")
    for (elem <- s) {print(elem + "\t")}
    println(System.lineSeparator() + "--------------")
    for (elem <- snew ){print(elem+"\t")}

    /**
      * 说明：
      * 在JVM中，scala的Array以java的数组的方式实现
      * scala中的String类型的数组即为java中的java.lang.String[]类型
      * 其他的I念头，Double等类型与java中的基本类型对应的数组是相同的。
      * 例如：scala中Array(1,2,5)在JVM中就是一个int[]
      */
  }
}
