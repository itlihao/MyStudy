package com.lia.lib.designPattern.singleton;

// DCL双重锁 单例
public class Singleton1 {
    private static volatile Singleton1 instance;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            synchronized (Singleton1.class) {
                if (instance == null) {
                    return  instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
