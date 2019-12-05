package com.example.administrator.view_test.handler;

public class MessageQueue {

    Message mMessage;

    boolean isQuit;//循环退出标志

    /**
     * @param message
     */
    public void enqueueMessage(Message message) {
        synchronized (this) {
            Message p = mMessage;
            if (null == p) {
                mMessage = message;
            } else {
                Message prev;
                for (; ; ) {
                    if (isQuit) {
                        return;
                    }
                    prev = p;
                    p = p.next;
                    if (null == p) {
                        break;
                    }
                }
                //将当前message插入到链表尾端
                prev.next = message;
            }
            //通知获取message 解除阻塞
            notify();
        }
    }

    public Message next() {
        synchronized (this) {
            Message p;
            for (; ; ) {
                if (isQuit) {
                    return null;
                }
                p = this.mMessage;
                if (null != p) {
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mMessage = mMessage.next;
            return p;
        }
    }

    public void quit() {
        synchronized (this) {
            Message message = this.mMessage;
            while (null != message) {
                Message next = message.next;
                message.recyle();
                message = next;
            }
            notify();
        }
    }
}
