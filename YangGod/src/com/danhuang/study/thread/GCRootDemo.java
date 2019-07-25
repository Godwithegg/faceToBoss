package com.danhuang.study.thread;

/**
 * 在java中，可作为GC Roots的对象有：
 * 1.在虚拟机栈（栈帧中的本地变量表）中引用的对象
 * 2.在方法区中类静态属性引用的对象。
 * 3.方法区中常量引用的对象。
 * 4.本地方法栈中JNI（即一般说的Native方法）中引用的对象。
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[1024*1024*100];

    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
