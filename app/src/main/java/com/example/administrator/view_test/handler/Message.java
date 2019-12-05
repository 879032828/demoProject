package com.example.administrator.view_test.handler;

public class Message {

    int what;
    Object object;

    Message next;

    Handler target;

    public void recyle() {
        object = null;
        next = null;
        target = null;
    }
}
