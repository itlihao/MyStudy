package com.lia.lib;

public class ActivityThread {

    public static void main(String[] args) {
        // 创建Looper对象
        Looper.prepare();

        // 创建Handler对象
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = "Hello Lia";
                handler.sendMessage(message);
            }
        }).start();


        // 创建Looper轮询
        Looper.loop();
    }
}
