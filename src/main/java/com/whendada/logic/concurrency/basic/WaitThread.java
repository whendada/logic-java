package com.whendada.logic.concurrency.basic;

public class WaitThread extends Thread{

    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!fire) {
                    wait();
                }
                System.out.println("wait");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setFire() {
        this.fire = true;
        System.out.println("notify");
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        waitThread.setFire();
    }
}
