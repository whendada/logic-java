package com.whendada.logic.concurrency.basic;

public class RacerWithLatchDemo {

    static class Racer extends Thread {

        MyLatch myLatch;

        public Racer(MyLatch myLatch) {
            this.myLatch = myLatch;
        }

        @Override
        public void run() {
            try {
                this.myLatch.await();
                System.out.println("start run " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        MyLatch myLatch = new MyLatch(1);
        for (int i = 0; i < num; i++) {
            Thread racer = new Racer(myLatch);
            racer.start();
        }
        Thread.sleep(1000);
        myLatch.countDown();
    }
}
