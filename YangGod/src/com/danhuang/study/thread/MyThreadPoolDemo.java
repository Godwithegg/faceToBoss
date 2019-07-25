package com.danhuang.study.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 第四种获得/使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
//                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    public static void threadPoolInit(){

        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程

        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
//                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
