package com.example.administrator.view_test.consumer;

public class MyConsumer {

    /**
     * 线程机制为了提高运行效率，当一个线程在不断的访问一个变量
     * 线程会使用一个私有空间，存储这个变量
     * <p>
     * 可见性 ---- volatile
     * 多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
     *
     * @param args
     */
    public static void main(String[] args) {
        Object mutex = new Object();
        new Producer(mutex).start();
        new Consumer(mutex).start();
    }


    /**
     * 产品
     */
    static class Product {
        public volatile static String value = null;
    }

    /**
     * 生产者
     */
    static class Producer extends Thread {

        private Object mutex;

        public Producer(Object object) {
            this.mutex = object;
        }

        @Override
        public void run() {
            //生产产品
            while (true) {
                synchronized (mutex) {
                    //如果产品还没被消费，则等待消费
                    if (null != Product.value) {
                        try {
                            mutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Product.value = "NO:" + System.currentTimeMillis();
                    System.out.println("product thing");
                    //通知消费者可以消费了
                    mutex.notify();
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer extends Thread {

        private Object mutex;

        public Consumer(Object object) {
            this.mutex = object;
        }

        @Override
        public void run() {
            //一直消费产品
            while (true) {
                synchronized (mutex) {
                    //如果还没生产出产品，等待生产出产品
                    if (null == Product.value) {
                        try {
                            /**
                             * 加锁后，可以避免一直循环判断，提高性能
                             */
                            mutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("consum product " + Product.value);
                    Product.value = null;
                    //通知生产者可以生产了
                    mutex.notify();
                }
            }
        }
    }

}
