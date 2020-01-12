package com.example.datastructure;

/**
 * 使用数组的优先级队列
 */
public class Priority {

    private int maxSize;
    private long[] quearray;
    private int count;

    public Priority(int size) {
        maxSize = size;
        quearray = new long[size];
        count = 0;
    }

    public void insert(long value) {
        int j = 0;
        if (count == 0) {
            quearray[count++] = value;
        } else {
            for (j = count - 1; j >= 0; j--) {
                if (value > quearray[j]) {
                    quearray[j + 1] = quearray[j];
                } else {
                    break;
                }
            }
            quearray[j + 1] = value;
            count++;
        }
    }

    public long remove() {
        return quearray[--count];
    }

    public long peekMin() {
        return quearray[count - 1];
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public boolean isFull() {
        return (count == maxSize);
    }


}
