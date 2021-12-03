package com.whendada.logic.concurrency.basic;

public class HelloThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread name is : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Thread name is : " + Thread.currentThread().getName());
        Thread thread = new HelloThread();
        thread.start();
    }
}
