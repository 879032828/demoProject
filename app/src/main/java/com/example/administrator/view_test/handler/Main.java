package com.example.administrator.view_test.handler;

public class Main {

    public static void main(String[] args) {
        Looper.prepare();

        final Handler handler = new Handler() {
            @Override
            public void dispatchMessage(Message next) {
                //在主线程接收消息
                super.dispatchMessage(next);
                System.out.println("recv :" + next.object);
            }
        };


        new Thread() {
            @Override
            public void run() {
                //在子线程发送消息
                Message message = new Message();
                message.object = "hello";
                System.out.println("send :" + message.object);
                handler.sendMessage(message);
            }
        }.start();



        Looper.loop();
    }

}
