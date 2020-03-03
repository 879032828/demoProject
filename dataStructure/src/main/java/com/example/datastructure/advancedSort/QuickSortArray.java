package com.example.datastructure.advancedSort;

/**
 * 快速排序
 */
public class QuickSortArray {

    private long[] a;
    private int nElems;

    public QuickSortArray(int max) {
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
     * 基本的递归的快速排序算法
     */
    public void recQuickSort(int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            int partition = partition(left, right);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    private int partition(int left, int right) {
        int leftPtr = left - 1;
        int righttPtr = right;
        int pivot = (int) a[right];

        while (true) {
            while (leftPtr < right && a[++leftPtr] < pivot) {
                ;
            }
            while (righttPtr > left && a[--righttPtr] > pivot) {
                ;
            }

            if (leftPtr >= righttPtr) {
                break;
            } else {
                swap(leftPtr, righttPtr);
            }
        }

        swap(leftPtr, right);//划分成两个子数组之后，将枢纽插入到两个子数组的分界处。
        return leftPtr;
    }

    public void swap(int dex1, int dex2) {
        long temp;
        temp = a[dex1];
        a[dex1] = a[dex2];
        a[dex2] = temp;
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
