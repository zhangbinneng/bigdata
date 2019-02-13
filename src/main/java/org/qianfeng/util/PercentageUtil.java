package org.qianfeng.util;

import java.math.BigDecimal;

/**
 * @description 百分比Util
 * @author: 张斌能
 * @create: 2018-11-16 12:11:20
 **/

public class PercentageUtil {
    public static void main(String[] args) {
        String number1 = 31+"";
        String nuber2 = 60+"";
        System.out.println(getPercentage(number1,nuber2,2));
    }

    /**
     * 计算两个数相除后得到的百分比，默认使用四舍五入
     * @param number1   第一个数的String形式
     * @param number2   第二个数的String形式
     * @param scala     在百分比中保留几位小数
     * @return  百分比形式的字符串
     */
    public static String getPercentage(String number1,String number2,int scala){
        BigDecimal bNumber1 = new BigDecimal(number1);
        BigDecimal bNumber2 = new BigDecimal(number2);
        String temp = (bNumber1.divide(bNumber2,scala+2,5)).multiply(new BigDecimal("100")).toString();
        return temp.substring(0,temp.length()-2)+"%";

    }
}
