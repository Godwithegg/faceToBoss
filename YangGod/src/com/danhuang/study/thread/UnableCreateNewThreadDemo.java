package com.danhuang.study.thread;

public class UnableCreateNewThreadDemo {

    //Linux下运行Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
    public static void main(String[] args) {
        for (int i = 1;  ; i++) {

            System.out.println("************ i = "+i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
