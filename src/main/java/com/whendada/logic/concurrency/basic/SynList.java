package com.whendada.logic.concurrency.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynList {

    private static void startModifyThread(final List<String> list) {
        Thread modifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add("item_" + i);
                    try {
                        Thread.sleep((int) (Math.random() * 10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        modifyThread.start();
    }

    private static void startIteratorThread(final List<String> list) {
        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 优化后
                    synchronized (list) {
                        for (String str : list) {
                            System.out.println(str);
                        }
                    }
                }
            }
        });
        iteratorThread.start();
    }

    public static void main(String[] args) {
        final List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 遍历容器的同时修改容器，会抛出异常,为了避免这种情况，需要给整个容器对象加锁
        startIteratorThread(list);
        startModifyThread(list);
    }
}
