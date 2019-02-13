package org.qianfeng.scala.test

object Test {
  def main(args: Array[String]): Unit = {
    def mulBy(factor:Double)=(x:Double)=>factor*x
    val triple = mulBy(3)
    println(triple(20))
    println(mulBy(3)(5))
    val p1 = new Pair("dd","ee")
    println(p1.smaller)
//    val p2 = new Pair(1,2)
    val p2 = new Pair2(1,2)
    println(p2.smaller)
    val p3 = new Pair3(1,2)
    println(p3.smaller)
  }
}
//上界
class Pair[T<:Comparable[T]](val first:T,val second:T){
  def smaller = if (first.compareTo(second)<0) first else second
}
//视图界定
/*
  * 如果试着new一个Pair(4,2)，编译器会提示Int不是Comparable[Int]的子类型。
  * 和java.long.Integer包装类型不同，Scala的Int类型没有实现Comparable。
  * 不过，RichInt实现了Comparable[Int],同时还有一个从Int到RichInt的隐式转换
  */
class Pair2[T<% Comparable[T]](val first:T,val second:T){
  def smaller = if (first.compareTo(second)<0)first else second
}
/*用Ordered特质会更好，它在Comparable的基础上额外提供了关系操作符*/
class Pair22[T<% Ordered[T]](val first:T,val second:T){
  def smaller = if (first<second) first else second
}
/*上界没有这样做因为java.lang.String实现了Comparable[String]，
 * 但没有实现Ordered[String]。有了视图界定，这就不是问题。
 * 字符串可以被隐式转换成RichString，而RichString是Ordered[String]的子类型
 */
//---------上下文界定-----------
/*
  * 视图界定T<%V 要求必须存在一个从T到V的隐式转换。
  * 上下文界定的形式为T:M,其中M是另一个泛型类，它要求必须存在一个类型为M[T]的"隐形值"
  * 例如 class Pair[T:Ordering]
  * 上述定义要求必须存在一个类为Ordering[T]的隐式值。该隐式值可以备用在该类的方法中
  * 当你声明一个使用隐式值的方法时，需要添加一个"隐式参数"
  */
class Pair3[T:Ordering](val first:T,val second:T){
  def smaller(implicit ord:Ordering[T])={
    if (ord.compare(first,second)<0)first else second
  }
}
