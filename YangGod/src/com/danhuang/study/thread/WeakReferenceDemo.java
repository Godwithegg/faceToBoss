package com.danhuang.study.thread;

import java.lang.ref.WeakReference;

/**
 * 弱引用，不管内存够不够用，只要产生gc就回收
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {

        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
