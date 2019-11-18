package com.lia.lib;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

    // 阻塞队列
    BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(50);

    // 将Message消息加入到阻塞队列中
    public void enqueueMessage(Message msg) {
        try {
            blockingQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 从阻塞队列中取出Message
    public Message next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
