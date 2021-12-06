package com.whendada.logic.concurrency.basic;

import java.util.Collection;
import java.util.concurrent.SynchronousQueue;

public class DeadLockDemo {

    private static final Object lockA = new Object();

    private static final Object lockB = new Object();

    private static void startThreadA() {
        Thread aThread = new Thread(() -> {
            synchronized (lockA) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("this is thread A");
                };
            }
        });
        aThread.start();
    }

    private static void startThreadB() {
        Thread bThread = new Thread(() -> {
            synchronized (lockB) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("this is thread B");
                };
            }
        });
        bThread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        // 这种写法，启动是会有时差的，如果B启动太慢可能A就把锁拿完了
        startThreadA();
//        Thread.sleep(100);
        startThreadB();
    }
}
