package com.example.datastructure.simpleRanking;

/**
 * 有序数组 ---- 二分查找法
 */
public class OrdArray {

    private long[] a;
    private int nElems;

    public OrdArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    /**
     * 因为是有序，才可以通过二分查找法查询数据
     *
     * @param searchKey
     * @return
     */
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIndex;

        while (true) {
            curIndex = (lowerBound + upperBound) / 2;
            if (a[curIndex] == searchKey) {
                return (lowerBound + upperBound) / 2;
            } else if (lowerBound > upperBound) {
                return -1;
            } else {
                if (a[curIndex] < searchKey) {
                    lowerBound = curIndex + 1;
                } else {
                    upperBound = curIndex - 1;
                }
            }
        }
    }

    /**
     * 有序插入
     *
     * @param value
     */
    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] > value)
                break;
        }
        for (int k = nElems; k > j; k--) {
            a[k] = a[k - 1];
        }
        a[j] = value;
        nElems++;
    }

    /**
     * 有序删除
     *
     * @param value
     * @return
     */
    public boolean delete(long value) {
        int j = find(value);

        if (j == nElems) {
            return false;
        } else {
            for (int k = j; k < nElems; k++) {
                a[k] = a[k + 1];
            }
            nElems--;
            return true;
        }
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(a[i]);
        }
    }

}
