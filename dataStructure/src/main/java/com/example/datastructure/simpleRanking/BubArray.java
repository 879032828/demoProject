package com.example.datastructure.simpleRanking;

/**
 * 无序数组 ---- 冒泡排序
 */
public class BubArray {

    private long[] a;
    private int nElems;

    public BubArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    /**
     * 无序插入
     *
     * @param value
     */
    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    /**
     * 冒泡法排序
     */
    public void bubbleSort() {
        for (int i = 0; i < nElems; i++) {
            for (int j = 0; j < nElems - 1 - i; j++) {
                long temp;
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public boolean delete(long value) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (a[i] == value)
                break;
        }
        if (i == nElems) {
            return false;
        }
        for (int j = i; j < nElems; j++) {
            a[j] = a[j + 1];
        }
        nElems--;
        return true;
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(a[i]);
        }
    }

}
