package com.danhuang.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间顺序调用，实现A->B->C三个线程启动，要求如下：
 *  AA打印5次，BB打印10次，CC打印15次
 *  紧接着
 *  AA打印5次，BB打印10次，CC打印15次
 *  。。
 *  来10轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        MyDatas myData = new MyDatas();
        new Thread(()->{
            myData.pirntA();
        },"A").start();
        new Thread(()->{
            myData.pirntB();
        },"B").start();
        new Thread(()->{
            myData.pirntC();
        },"C").start();

    }
}

class MyDatas{

    Lock lock = new ReentrantLock();
    private int number = 1;
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void pirntA(){
        lock.lock();
        try {
            while(number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0 ;i < 5;i++){
                System.out.println(Thread.currentThread().getName()+"\t A第:"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            number = 2;
            condition2.signal();
            lock.unlock();
        }

    }
    public void pirntB(){
        lock.lock();
        try {
            while(number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0 ;i < 10;i++){
                System.out.println(Thread.currentThread().getName()+"\t B第:"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            number = 3;
            condition3.signal();
            lock.unlock();
        }

    }
    public void pirntC(){
        lock.lock();
        try {
            while(number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0 ;i < 15;i++){
                System.out.println(Thread.currentThread().getName()+"\t C第:"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            number = 1;
            condition1.signal();
            lock.unlock();
        }

    }
}
