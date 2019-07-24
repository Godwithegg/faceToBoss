package com.danhuang.study.thread;

/**
 * DCL：双重检测锁案例
 */
public class DCL {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonDemo.getInstance();
                }
            }).start();
        }
    }
}
class SingletonDemo{
    //防止超小概率下出现了指令重排要加上volatile
    private static volatile SingletonDemo SingletonDemo = null;

    public SingletonDemo(){
        System.out.println("这是构造方法");
    }
    public static SingletonDemo getInstance(){
        if(SingletonDemo == null){
            synchronized (SingletonDemo.class){
                if(SingletonDemo == null){
                    SingletonDemo = new SingletonDemo();
                }
            }
        }
        return null;
    }

}
