package com.danhuang.study.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决 AtomiceStampedReference
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        System.out.println("--------以下是ABA问题的产生");
        new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet(100,101);
                atomicReference.compareAndSet(101,100);
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程暂停1秒钟，保证上面的t1线程完成了一次ABA操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
            }
        },"t2").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------以下是ABA问题的解决");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);
                //暂停一秒钟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t第二次版本号："+atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t第三次版本号："+atomicStampedReference.getStamp());

            }
        },"t3").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"\t第一次版本号为："+stamp);
                //线程暂停3秒钟,保证上面的t3完成一次ABA操作
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName()+"\t修改成功是否："+result+"当前最新实际版本号"+atomicStampedReference.getStamp());
                System.out.println(Thread.currentThread().getName()+"\t当前实际最新值："+atomicStampedReference.getReference());
            }
        },"t4").start();
    }
}
