package org.qianfeng.scala.fileandregex

object regex {
  def main(args: Array[String]): Unit = {
    val numPattern = "[0-9]+".r//首先定义数值类型的正则

    //遍历所有匹配项的迭代器，使用findAllInt方法
    for (matchString <- numPattern.findAllIn("898ds,87 sdf"))
      print(matchString + "\t")
    println
  }
}
