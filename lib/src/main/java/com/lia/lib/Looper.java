package com.lia.lib;

public class Looper {
    private static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }

        sThreadLocal.set(new Looper());
    }

    public static void loop() {
        // 获取全局唯一的Looper对象
        Looper me = myLooper();
        // 获取全局唯一的消息队列MessageQueue对象
        MessageQueue mQueue = me.mQueue;

        // 轮询从消息队列取出消息
        while (true) {
            Message msg = mQueue.next();
            msg.target.dispatchMessage(msg);
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
