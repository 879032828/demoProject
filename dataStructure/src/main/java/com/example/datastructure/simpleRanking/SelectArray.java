package com.example.datastructure.simpleRanking;

/**
 * 选择排序
 * <p>
 * 是在冒泡排序的基础上进行了优化
 */
public class SelectArray {

    private long[] a;
    private int nElems;

    public SelectArray(int max) {
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
     * 选择排序
     * 存在两个概念：比较和交换
     * <p>
     * 冒泡排序是比较相邻的两个数值，并进行交换，时间复杂度是O(N2)
     * <p>
     * 选择排序则是用最小值去和剩下的值进行比较，获取所有数中的最小值的下标，最后只做一次交换，时间复杂度是O(N)
     */
    public void selectSort() {
        int out, in, min;
        for (out = 0; out < nElems; out++) {
            min = out;
            for (in = out + 1; in < nElems; in++) {
                if (a[in] < a[min])
                    min = in;//只获取最小值的下标
            }

            if (a[min] < a[out]) {
                long temp;
                temp = a[out];
                a[out] = a[min];
                a[min] = temp;
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
