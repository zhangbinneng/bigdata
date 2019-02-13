package org.qianfeng.java;

/**
 * @description java中，父子类上的静态代码块，普通代码块，构造函数执行的顺序
 * @author: 张斌能
 * @create: 2018-11-14 19:51:02
 **/

public class StaticBlockAndNonStaticBlock {
    public static void main(String[] args){
        System.out.println("test的main");
        new Son();
    }
}
class Father{
    public Father() {
        System.out.println("我是Father上的构造函数");
    }
    {
        System.out.println("我是Father上的普通代码块");
    }
    static {
        System.out.println("我是Father上的静态代码块");
    }
    public static void main(String[] args){
        System.out.println("Father的main");
        new Father();
    }


}
class Son extends Father{
    {
        System.out.println("我是Son上的普通代码块");
    }
    static {
        System.out.println("我是Son上的静态代码块");
    }


    public Son() {
        System.out.println("我是Son上的构造函数");
    }
    public static void main(String[] args){
        System.out.println("son的main");
        new Son();
        new Son();
    }
}