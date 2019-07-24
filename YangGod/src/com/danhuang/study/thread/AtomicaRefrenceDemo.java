package com.danhuang.study.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class User{
    String username;
    int age;
    public User(String username,int age){
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicaRefrenceDemo {
    public static void main(String[] args) {

        User z3 = new User("z3",22);
        User li4 = new User("li4",25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.toString());
        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.toString());
    }
}
