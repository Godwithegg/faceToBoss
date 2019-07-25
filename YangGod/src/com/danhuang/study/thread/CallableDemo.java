package com.danhuang.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable{
    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("*********come in callable");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //两个线程，一个是mian主线程，一个是AA futureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask2, "BB").start();

//        while(!futureTask.isDone()){
//
//        }
        int result01 = 100;
        int result02 = futureTask.get();    //要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致阻塞，直到计算完成

        System.out.println("****result:"+(result01+result02));

    }
}
