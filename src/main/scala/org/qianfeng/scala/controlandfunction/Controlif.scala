package org.qianfeng.scala.controlandfunction

object Controlif {
  def main(args: Array[String]): Unit = {
    val x = 0
    val resultVal = if (x>0) 1 else -1
    println(resultVal)
    var resultVar = 0
    if (x>0) resultVar = 1 else resultVar = -1
    //上述两个方式的区别在于方式一可以将结果赋值给一个常量，
    //方式二中却只能使用变量来接受条件判断的结果，所以方式以更好一些
    /**
      * any类型
      * 见下面的实例:
      */
    if (x>0) "hello" else 25

    //上述实例中返回值类型有两种，字符型和数字型，我们无法使用一种单一类型来接受这个if表达式的结果只
    //在scala中，设计了一种特殊的值类型，称之其他类型的超类型，叫做Any，类似java中的Object.
    val any:Any = if (x>0) "hello" else 25
    println(any)
    /*
     *Unit 类型
     * 如果if语句的else部分缺失了，见下面的实例：
     *
     */
    if (x>0) 1
    //如果x是0或者负数，if语句就会没有输出值了，但是在scala的设计中每个表达式都应该有某种类型的输出值。
    //上述案例中的问题的解决暗杆是引入一个Unit类，写作（）。不带else的这个if语句等同于：
    if (x>0) 1 else ()
    //如果的（）表示“无有用值”的占位符。Unit类似与java中的void,但又不完全不相同
    //Unit更像是关系型数据库的null,虽然表示空概念，但是null还是要占用底层存储的。void则表示一个空的概念，什么也没有。

  }
}
