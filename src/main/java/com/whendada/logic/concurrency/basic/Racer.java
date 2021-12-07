package com.whendada.logic.concurrency.basic;

public class Racer extends Thread {

    FireFlag fireFlag;

    public Racer(FireFlag fireFlag) {
        this.fireFlag = fireFlag;
    }

    @Override
    public void run() {
        try {
            this.fireFlag.waitForFire();
            System.out.println("start run " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 同时启动的重点是多个线程监听同一个共享变量，修改共享变量的线程修改完之后需要通过notifyAll来通知所有监听线程
    // 并且还要保证变量对所有线程可见，所以需要加 volatile
    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] threads = new Thread[num];
        for (Thread thread : threads) {
            thread = new Racer(fireFlag);
            thread.start();
        }
        Thread.sleep(1000);
        fireFlag.setFired();
    }
}
