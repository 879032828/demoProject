package com.example.administrator.view_test.test;

/**
 * Created by 李振伟 on 2019/10/11 0011.
 */

public class volatileTest {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final volatileTest test = new volatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        test.increase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("最后的i值为" + test.inc);
    }

}
