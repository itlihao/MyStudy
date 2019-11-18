package com.lia.lib;

public class Message {
    Handler target;

    public int what;

    public Object obj;

    public Message() {

    }

    public Message(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return obj.toString();
    }
}
