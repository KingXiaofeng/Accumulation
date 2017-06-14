package com.test.singleton;

/**
 * Java中单例模式是一种常见的设计模式，单例模式的写法有好几种，这里主要介绍三种：懒汉式单例、饿汉式单例、登记式单例。
 * 　　单例模式有以下特点：
 * 　　1、单例类只能有一个实例。
 * 　　2、单例类必须自己创建自己的唯一实例。
 * 　　3、单例类必须给所有其他对象提供这一实例。
 * 　　
 * 单例模式确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例。在计算机系统中，线程池、缓存、日志对象、
 *      对话框、打印机、显卡的驱动程序对象常被设计成单例。这些应用都或多或少具有资源管理器的功能。每台计算机可以有若干个打
 *      印机，但只能有一个Printer Spooler，以避免两个打印作业同时输出到打印机中。每台计算机可以有若干通信端口，系统应当
 *      集中管理这些通信端口，以避免一个通信端口同时被两个请求同时调用。总之，选择单例模式就是为了避免不一致状态，避免政出
 *      多头。
 */

/**
 * 单例——懒汉式
 * 所谓懒汉式，在首次使用时，来创建该单例对象
 */
public class Singleton {

    private static Singleton singleton = null;

    private Singleton(){

    }

    /**
     * 一、直接给静态方法加锁 ( 线程不安全 )
     */
    public static synchronized Singleton getInstance1() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }


    /**
     * 二、双重检查锁定
     * 方案一与方案二的区别：
     *      方案一是可行的，但是其弊端在于，每次获取该单例对象时，都有锁，导致效率慢
     *      方案二则解决了方案一的弊端，当该对象已经被创建后，不要再进行锁等待
     */
    public  static Singleton getInstance2() {
        /*方案一
        synchronized (Singleton.class) {
            if (null == singleton) {
                singleton = new Singleton();
            }
        }
        */

        // 方案二
        if ( null == singleton ) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }

    /**
     * 三、静态内部类（推荐）
     *
     * 这种处理方案是最优的，因为它既是线程安全的，有不会带来线程同步所导致的性能问题
     */
    private static class LazyHolder{
        private static final Singleton SINGLETON = new Singleton();
    }

    private static final Singleton getSingleton3(){
        return LazyHolder.SINGLETON;
    }

}
