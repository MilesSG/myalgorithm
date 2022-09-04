package com.company.bishi;

/**
 * 双重检查锁🔒
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton() {
    }

    private static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
