package com.danhuang.study.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t invoked sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t ##### invoked sendEmail");
    }
    //=====================
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        /**
         * 可嵌套多重锁，只要释放锁的数量和锁的数量一致即可
         */
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        }finally {
            lock.unlock();
        }
    }
}

/**
 *  可重入锁（也叫递归锁）
 *
 *  指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁代码，
 *  在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *  也即是说，线程可以进入一个它已经拥有的锁所同步着的代码块    拥有的是同一把锁
 *  invoked sendSMS()   t1线程在外层方法获取锁的时候。
 *  invoked sendEmail() t1在进入内层方法会自动获取锁
 *
 *  case two
 *  ReentrantLock就是一个典型的可重入锁
 */
public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()-> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();//11
        new Thread(()-> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();
    }
}
