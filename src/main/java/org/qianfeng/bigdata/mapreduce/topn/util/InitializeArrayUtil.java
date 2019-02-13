package org.qianfeng.bigdata.mapreduce.topn.util;

/**
 * @description 工具类
 * @author: 张斌能
 * @create: 2018-11-21 14:58:11
 **/

public class InitializeArrayUtil {
    /**
     * 将一个给定的double类型的数组初始化，具体操作是将数组的每一个值设置为Double.MIN_VALUE
     * @param oldArr
     */
    public static void initDoubleArr(double[] oldArr){
        for (int i =0;i<oldArr.length;i++){
            oldArr[i] = Double.NEGATIVE_INFINITY;
        }
    }
}
