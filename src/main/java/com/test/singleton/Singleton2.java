package com.test.singleton;

/**
 * 单例——饿汉式
 */
public class Singleton2 {
    private static final Singleton2 SINGLETON = new Singleton2();

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return SINGLETON;
    }
}
