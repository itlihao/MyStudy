package com.lia.lib.designPattern.singleton;

// 静态内部类 单例
public class Singleton2 {

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final Singleton2 instance = new Singleton2();
    }
}
