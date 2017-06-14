package com.test.thread;

/**
 * JVM把内存分为两块，一块为线程栈，一块为堆。不同线程栈之间是不能相互访问的。比如线程栈A中有个本地变量，
 * 那么线程栈B是不能访问到该变量的。当然需要注意引用的情况，如果线程栈A中有个类的变量指向堆中的一个对象，
 * 线程栈B中也有个类的变量指向堆中的同一个对象，那么写操作是非线程安全的。
 *
 * Created by wangxf on 2017/6/14.
 */
public class ThreadTestError{

    public static void main(String[] args) {

        Count count = new Count();

        Thread a = new Thread(new ThreadError1(count));
        Thread b = new Thread(new ThreadError1(count));

        a.start();
        b.start();

        /**
         * 当没有a.join(); b.join(); 时，输出结构为0,
         * 线程执行顺序为 main、a、b
         *
         * a.join() 意思为：当前线程等待 a 线程执行完毕
         */
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------");
        System.out.println(count.i);
        System.out.println("-----------------------");
    }

}

class ThreadError1 implements Runnable{

    private Count count = null;

    public ThreadError1(Count count){
        this.count = count;
    }

    public void run() {
        add();
    }

    private void add(){
        for ( int j=0 ; j<100 ; j++){
            count.i++;
            System.out.println(count.i);
        }
    }
}

class Count{
    public int i = 0;
}


