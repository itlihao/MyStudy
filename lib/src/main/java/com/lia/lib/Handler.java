package com.lia.lib;

public class Handler {

    private Looper mLooper;

    private MessageQueue mQueue;

    Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread " + Thread.currentThread()
                    + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    public void handleMessage(Message msg) {

    }

    void dispatchMessage(Message msg) {
        handleMessage(msg);
    }

    void sendMessage(Message msg) {
        // 赋值当前Handler
        msg.target = this;

        // 将消息放入消息队列
        mQueue.enqueueMessage(msg);
    }
}
