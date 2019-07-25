package com.danhuang.study.thread;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue:是一个基于数组数据结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序
 * LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO（先进先出）排序元素，吞吐量通常要高于ArrayBlockingQueue
 * SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须要等到另一个线程调用移除操作，吞吐量通常要高于
 *
 * 1.队列
 *
 * 2.阻塞队列
 *  2.1 阻塞队列有没有好的一面
 *
 *  2.2 不得不阻塞，那你如何管理
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

//        List list = null;
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);//队列只有3个位置

        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue.offer("b",2L, TimeUnit.SECONDS);
        blockingQueue.offer("c",2L, TimeUnit.SECONDS);
        blockingQueue.offer("d",2L, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

    }


}
