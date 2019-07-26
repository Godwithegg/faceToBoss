package com.danhuang.study.thread;

/**
 * 强引用
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object(); //这样定义的默认值就是强引用
        Object obj2 = new Object();//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);
    }
}
