package com.danhuang.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
//    int number = 0;
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }

    //请注意，此时number前面是加了volatile关键字修饰的
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性
 *  1.1加入int number = 0 ，number变量之前根本没有添加volatile关键字修饰。
 *  1.2加入volatile int number = 0，可以解决可见性问题。
 * 2.验证volatile不保证原子性
 *  2.1原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做具体业务时，中间不可被加塞或分割，需要整体完整，要么同时成功，要么同时失败。
 *  2.2volatile不保证完整性案例演示
 *  2.3why?
 *  2.4如何解决原子性？
 *      * 加synchronize
 *
 */
public class VolatileDemo {
    public static void main(String[] args) {//一切方法运行的入口

        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上面20个线程都计算完成后，在用main线程取得最终的结果值是多少
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" total value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+" total value:"+myData.atomicInteger);

    }
    //volatile可以保证可见性,及时通知其他线程，主物理内存的值已经被修改
    public static void seeOkByVolatile(){
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+
                    "\t"+"come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+
                    "\t"+myData.number);
        },"aaa").start();

        //第二个线程就是我们的main线程
        while(myData.number == 0){
            //main线程就在这里一直等待，直到number不等于0
        }
        System.out.println(Thread.currentThread().getName()+"\t"
                +"over");
    }

}
