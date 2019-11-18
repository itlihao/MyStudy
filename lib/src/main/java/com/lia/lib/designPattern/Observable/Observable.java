package com.lia.lib.designPattern.Observable;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Observer> observers;//定义一个观察者list

    public Observable() {//构造函数，初始化一个观察者list来保存观察者
        observers = new ArrayList<>();
    }
    //添加观察者，带同步字段的，所以是线程安全的
    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    //删除观察者
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    // 通知所以观察者，无参数
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
