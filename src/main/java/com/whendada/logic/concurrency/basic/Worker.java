package com.whendada.logic.concurrency.basic;

public class Worker extends Thread {

    MyLatch myLatch;

    public Worker(MyLatch myLatch) {
        this.myLatch = myLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
            this.myLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " count --");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 100;
        MyLatch myLatch = new MyLatch(num);
        for (int i = 0; i < num; i++) {
            Worker worker  = new Worker(myLatch);
            worker.start();
        }
        myLatch.await();
        System.out.println("collect worker end");
    }
}
