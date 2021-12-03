package com.whendada.logic.concurrency.basic;

public class VisibilityDemo {

    private static volatile boolean shutdown = false;

    static class HelloThread extends Thread {

        @Override
        public void run() {
            while (!shutdown) {

            }
            System.out.println("Exit Hello");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloThread().start();
        Thread.sleep(1000);
        // 在main方法中做的改变并没有到子线程中去，如果shutdown没有被volatile修饰
        shutdown = true;
        System.out.println("Exit Main");
    }
}
