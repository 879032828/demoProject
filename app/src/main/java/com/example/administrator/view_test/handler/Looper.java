package com.example.administrator.view_test.handler;

public class Looper {

    static final ThreadLocal<Looper> sThreadLoacal = new ThreadLocal<>();
    public MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    /**
     * 准备 looper
     */
    public static void prepare() {
        //当前线程已经存在looper，就抛出异常
        if (null != sThreadLoacal.get()) {
            throw new RuntimeException(Thread.currentThread() + "已经有了looper");
        }
        sThreadLoacal.set(new Looper());
    }

    /**
     * 获得当前looper
     *
     * @return
     */
    public static Looper myLooper() {
        return sThreadLoacal.get();
    }

    /**
     * 不停地从messageenqueue获取message
     */
    public static void loop() {
        //获得当前looper
        Looper looper = Looper.myLooper();
        MessageQueue queue = looper.mQueue;
        for (; ; ) {
            Message next = queue.next();
            if (null == next) {
                break;
            }
            next.target.dispatchMessage(next);
        }
    }

    public void quit() {
        mQueue.quit();
    }
}
