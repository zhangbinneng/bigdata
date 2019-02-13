package org.qianfeng.bigdata.mapreduce.topn.util;

/**
 * @description
 * @author: 张斌能
 * @create: 2018-11-21 15:03:55
 **/

public class TOPNUtil {
    /**
     * 从给定的数组中获取TOPN个元素
     * @param n 取前n位数据
     * @param arr
     * @return
     */
    public static double[] getTOPN(int n,double[] arr){
        double[] result = new double[n];
        for (int i = 0;i<n;i++){
            for(int j =0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            result[i] = arr[arr.length-i-1];
        }
        return result;
    }

    /**
     * 从给定的两个数组中取TOPN
     * 处理流程：先合并两个数组为一个数组，然后在从中取TOPN
     * @param n
     * @param arr1
     * @param arr2
     * @return
     */
    public static double[] getTOPN(int n,double[] arr1,double[] arr2){
        double[] mergeArray = new double[arr1.length+arr2.length];
        for (int i = 0; i< arr1.length; i++){
            mergeArray[i] = arr1[i];
        }
        for(int i = arr1.length;i<mergeArray.length;i++){
            mergeArray[i] = arr2[i-arr1.length];
        }
        return getTOPN(n,mergeArray);
    }
}
