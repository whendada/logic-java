package com.whendada.logic.concurrency.basic;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue<E> {

    private Queue<E> queue = null;

    private int limit;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        this.queue = new ArrayDeque<>(limit);
    }

    public synchronized void put(E e) throws InterruptedException {
        while (limit == queue.size()) {
            System.out.println("queue is full");
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("queue is null");
            wait();
        }
        E e = queue.poll();
        notifyAll();
        return e;
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue queue = new MyBlockingQueue(10);
        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true){
                    try {
                        queue.put(count);
                        count ++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(queue.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        putThread.start();
        takeThread.start();

    }
}
