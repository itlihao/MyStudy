package com.lia.lib.designPattern.singleton;

// 懒汉模式 单例
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            return  instance = new Singleton3();
        }
        return instance;
    }
}
