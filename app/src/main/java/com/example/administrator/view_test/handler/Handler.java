package com.example.administrator.view_test.handler;

public class Handler {

    private MessageQueue mQueue;

    public Handler() {
        Looper mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public void sendMessage(Message message) {
        message.target = this;
        mQueue.enqueueMessage(message);
    }

    public void dispatchMessage(Message next) {

    }
}
