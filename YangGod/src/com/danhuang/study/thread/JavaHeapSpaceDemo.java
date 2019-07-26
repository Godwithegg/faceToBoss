package com.danhuang.study.thread;

import java.util.Random;

public class JavaHeapSpaceDemo {

    public static void main(String[] args) {

        byte[] bytes = new byte[80*1024*1024];//80M
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

    }
}
