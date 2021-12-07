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
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " : produce");
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("queue is null");
            wait();
        }
        E e = queue.poll();
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " : consume");
        notifyAll();
        return e;
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue queue = new MyBlockingQueue(10);
        Thread putThread;
        Thread takeThread;
        for (int i = 0; i < 1; i++) {
            putThread = new Thread(new Runnable() {
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
            putThread.start();
        }
        for (int i = 0; i < 1; i++) {
            takeThread = new Thread(new Runnable() {
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
            takeThread.start();
        }

        // 在最开始的时候需要生产者生产完才会进行消费，因为此时根本没有走到消费者，所以两个Thread不算是真正的异步
    }
}
