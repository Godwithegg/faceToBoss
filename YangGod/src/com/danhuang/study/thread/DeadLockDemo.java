package com.danhuang.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 尝试获得"+lockB);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 尝试获得"+lockA);
            }
        }
    }
}

/**
 * 死锁是两个或两个以上的线程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那它们都将无法推进下去
 */
public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();

        /**
         * linux ps -ef|grep xxx    ls -l
         * window下的java运行程序 也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
         *      jpa = java ps       jps -l
         */
    }
}

