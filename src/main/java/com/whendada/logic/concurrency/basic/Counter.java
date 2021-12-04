package com.whendada.logic.concurrency.basic;

public class Counter {

    private int count;

    public synchronized void incr() {
        count ++;
    }

    public synchronized int getCount() {
        return count;
    }
}
