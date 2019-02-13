package org.qianfeng.bigdata.mapreduce.util;

/**
 * @description 将string转换成double
 * @author: 张斌能
 * @create: 2018-11-21 15:47:04
 **/

public class ArrayTypeTransformUtil {
    public static double[] transStrArr2DoubleArr(String[] oldStrArr ){
        double[] newStrArr = new double[oldStrArr.length] ;
        for (int i = 0;i<oldStrArr.length;i++){
           try {
               newStrArr[i] = Double.parseDouble(oldStrArr[i]);
           }catch (Exception e){
               newStrArr[i] = Double.NEGATIVE_INFINITY;
           }
        }
        return newStrArr;
    }
}
