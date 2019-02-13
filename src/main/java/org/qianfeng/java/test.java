package org.qianfeng.java;

/**
 * @description xxd
 * @author: 张斌能
 * @create: 2018-11-29 22:52:32
 **/

public class test {
    public static void main(String[] args) {
            String abs = "xxd,null,xxd1,xxd2,null,xxd3";
            String[] spilt = abs.split(",");
        System.out.println(spilt.length);
        for(int i = 0;i < spilt.length;i++){
            if(spilt[i].equals("null"))
            {
                System.out.println("kong");
            }else
            {
            System.out.println(spilt[i]);}

        }
    }
}
