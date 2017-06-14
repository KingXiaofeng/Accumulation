package com.test.thread;

/**
 * Created by wangxf on 2017/6/14.
 */
public class ThreadTest {
    /**
     * volatile
     *
     * 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。
     * volatile很容易被误用，用来进行原子性操作
     */
    private static volatile int i = 0;

    public static void main(String[] args) {

        // 线程a
        Thread a = new Thread(){
            @Override
            public void run(){
                System.out.println("===============AAAAA=============");
                while ( i < 100 ) {
                    i++;
                    System.out.println(i + "    ::A");
                }
            }
        };

        // 线程b
        Thread b = new Thread(){
            @Override
            public void run(){
                System.out.println("===============BBBBB=============");
                while ( i < 100 ) {
                    i++;
                    System.out.println(i + "    ::B");
                }
            }
        };

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("-------------------");
            System.out.println(i);
            System.out.println("-------------------");
        }
    }
}
