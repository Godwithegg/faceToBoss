package com.danhuang.study.thread;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 设置虚引用的唯一目的，就是在对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {

        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===================================");
        o1 = null;
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
