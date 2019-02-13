package org.qianfeng.scala

object Hellow {
  val greeting: String = "Hello,World!"

  def main(args: Array[String]): Unit = {

    //    println("asd".length)

    //    println(List(1,3,5,"seven").map{
    //      case i:Int => i+1
    //      case s:String => s.length
    //    })
    //    println( greeting )
    //    println("ad".matches("asd"))
    //    val s = Map('a->"1",'b->"3",'c->"6")
    //    println(s.minBy((t:(Symbol,String))=>Integer.parseInt(t._2)))
    //      println("qwega".min)
    //     println("asdaf".mkString)
    //      var str0 = "scala"
    //    println(str0.mkString("begin",",","end"))
    //      println(str0.mkString(","))
    //    val s = Seq(1,23,41)
    //    println(s.nonEmpty)
    //    val greet = "hello"
    //    println(greet.offsetByCodePoints(1,4))
    //    val it = Iterator("Baidu", "Google", "Runoob", "Taobao")
    //      println(it.padTo(6,"asd").mkString(","))
    //    val a = Array(1, 2, 3, 4, 5)
    //    println(a.par)
    //  val a = Array(1, 2, 3, 4, 5)
    //    val b:(Array[Int],Array[Int]) = a.partition( {x:Int => x % 2 == 0})
    //    println(b._1.mkString(","))
    //    println(b._2.mkString(","))

    //    val a = Array(1, 2, 3, 4, 5)
    //    val b = Array(3, 4, 6)
    //    val c = a.patch(1,b,2)
    //    println(c.mkString(","))
    //val a = Array(1, 2, 3)
    //    val b = a.permutations.toList
    //    b.foreach(x=>{
    //      println(x)
    //    })
    //val a = Array(1,2,3,4,1,2,3,4)
    //    val b = a.prefixLength( {x:Int => x<3})
    //println(b)
    //val a = Array(1,2,3,4,5)
    //    val b = a.product
    //    println(b)
    def seqno(m:Int,n:Int): Int ={
      val s = "seq_exp=%d+%d"
      println(s.format(m,n))
      return m+n
    }
    //    val a = Array(1,2,3,4,5)
    //    val b = a.reduceRightOption(seqno)
    //    println(b)
    //val a = Array(1,2,3,4,5)
    ////    val b = a.reverseIterator
    ////    println(b)
    ////
//    val a = Array(1,2,3,4,5)
//    val b = a.reverseMap( {x:Int => x*10} )
//    println(b.mkString(","))
//    val a = Array(1,2,3,4,5)
//    val b = Array(1,2,3,4,5)
//    println(a.sameElements(b))
//    val a = Array(1,2,3,4,5)
//    val b = a.scanRight(5)(seqno)
//    println(b.mkString(","))
//    val a = Array(1,2,3,1,1,1,1,1,4,5)
//    val b = a.segmentLength( {x:Int => x < 3},3)
//    val a = Array(1,2,3,1,1,1,1,1,4,5)
//  println(Seq(a))
//    val a = Array(1,2,3,4,5)
//    val b = a.slice(1,3)
//    println(b.mkString(","))
//    val a = Array(1,2,3,4,5)
//    val b = a.sliding(3).toList
//    for(i<-0 to b.length - 1){
//      val s = "第%d个：%s"
//      println(s.format(i,b(i).mkString(",")))
//    }
//    val a = Array(1,2,3,4,5)
//    val b = a.sliding(3,2).toList   //第一个从1开始， 第二个从3开始，因为步进是 2
//    for(i<-0 to b.length - 1){
//      val s = "第%d个：%s"
//      println(s.format(i,b(i).mkString(",")))
//    }
//    val a = Array(3,2,1,4,5)
//    val b = a.sortBy( {x:Int => x})
//    println(b.mkString(","))
//    val a = Array(3,2,1,4,5)
//    val b = a.sortWith(_.compareTo(_) > 0)  // 大数在前
//    println(b.mkString(","))
//    val a = Array(3,2,1,4,5)
//    val b = a.sorted
//    println(b.mkString(","))
//    val a = Array(3,2,1,4,5)
//    val b = a.span( {x:Int => x > 2})
//    println(b._1.mkString(","))     //  3
//    println(b._2.mkString(","))    //  2,1,4,5
//    val a = Array(3,2,1,4,5)
//    val b = a.splitAt(2)
//    println(b._1.mkString(",")) //  3,2
//    println(b._2.mkString(",")) //  1,4,5
//    val a = Array(0,1,2,3,4,5)
//    val b = Array(1,2)
//    println(a.startsWith(b,1))
//    val a = Array(1,2,3,4,5)
//    val b = Array(1,2)
//    println(a.startsWith(b))
//    val s1:String = ",ONE,,TWO,,,THREE,,"
//    val s2 = "[stop]" +"ONE[stop][stop]" +"TWO[stop][stop][stop]" + "THREE[stop][stop]"
//
//    for (elem <- s2.split("[stop]")) {
//      println(elem)
//    }
//    val s1 = "asdafafff"
//    println(s1.replace("f","bb"))
    val  s = Array("hello","word")
    s.update(0,"goodbye")
//    s(0) = "goodbye"
    println(s(0)+s(1))
    var a = "abc"
    a.updated(0,"j")
    println(a)
  }
}
