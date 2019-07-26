package com.danhuang.study.thread;

public class HelloGC {

    public static void main(String[] args) {
        System.out.println("*****HelloGC");
        for (int i = 0;  ; i++) {
            int  tempInt = i;
            new Thread(()->{
                System.out.println("第"+tempInt);
                try {
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}

