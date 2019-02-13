package org.qianfeng.scala.array

object ShiftyLengthArray {
  def main(args: Array[String]): Unit = {
    /**
      * 对于长度需要变化数组，java有ArrayList.
      * scala中的等效的数组结构为ArrayBuffer.
      * 在scala中，变长数组称之为数组缓冲
      */
    //与java中使用ArrayList类似，需要导入ArrayBuffer包
    import scala.collection.mutable.ArrayBuffer
    //准备一个空的变长数组，准备存放数据
    val b = ArrayBuffer[Int]()
    //上述代码也可以写成java方式
    val b1 = new ArrayBuffer[Int]()
    //使用+=符号在数组尾部添加一个元素
    b+=1 ;println(b)
    b+=(1,2,3); println(b)
    b.append(5,5,5)
    b ++=Array(5,8); println(b)
    //往数组缓冲b中追加一个数组或者数组缓冲（即：任何带有TraversableOnce特质的集合）
    b.appendAll(b)
    //移除最后3个元素
    b.trimEnd(3);println(b)
    //在b的下标为1的地方插入6这个值
    b.insert(1,6);println(b)
    b.insert(2, 9,8,7);println(b)

    //移除任意位置上的数据
    b.remove(2);println(b)
    //移除从下标2开始，往后的2个数据
    b.remove(2,2);println(b)
  }
}
