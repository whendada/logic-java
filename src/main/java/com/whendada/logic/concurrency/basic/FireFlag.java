package com.whendada.logic.concurrency.basic;

public class FireFlag {

    private volatile boolean fired = false;

    public synchronized void waitForFire() throws InterruptedException {
        while (!fired) {
            wait();
        }
    }

    public synchronized void setFired() {
        this.fired = true;
        notifyAll();
    }
}
