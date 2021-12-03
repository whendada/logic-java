package com.whendada.logic.concurrency.basic;

public class HelloRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("hello world! ");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new HelloRunnable());
        thread.start();
    }
}
