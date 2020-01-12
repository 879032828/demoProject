package com.example.datastructure;

public class Queue {

    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int count;

    public Queue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void insert(long value) throws Exception {

        if (isFull()) {
            throw new Exception("队列满了");
        }

        if (rear == maxSize - 1) {//当rear指针指向数组的顶端时，执行回绕操作
            rear = -1;
        }
        queArray[++rear] = value;//在队尾指针加一后，在队尾指针所指的位置处插入新的数据
        count++;
    }

    public long remove() throws Exception {

        if (isEmpty()) {
            throw new Exception("队列空了");
        }

        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        count--;
        return temp;
    }

    public long peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public boolean isFull() {
        return (count == maxSize);
    }

    public int size() {
        return count;
    }

}
