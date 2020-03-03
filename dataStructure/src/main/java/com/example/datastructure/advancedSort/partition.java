package com.example.datastructure.advancedSort;

/**
 * 划分算法
 */
public class partition {

    private long[] theArrayy;
    private int nElems;

    public partition(int max) {
        theArrayy = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        theArrayy[nElems] = value;
        nElems++;
    }

    public int size() {
        return nElems;
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(theArrayy[i]);
        }
    }

    /**
     * 划分算法
     *
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right + 1;

        while (true) {

            /**
             * 当leftPtr遇到比枢纽小的数据项时，它继续右移
             * 直到遇到比枢纽大的数据项，它就停止
             *
             * leftPtr < right 是数组边界的检测
             * */
            while (leftPtr < right && theArrayy[++leftPtr] < pivot) {
                ;
            }

            /**
             * 当rightPtr遇到比枢纽大的数据项时，它继续左移
             * 直到遇到比枢纽小的数据项，它就停止
             *
             * rightPtr > left 是数组边界的检测
             * */
            while (rightPtr > left && theArrayy[--rightPtr] > pivot) {
                ;
            }

            /**
             * 经过两层循环后，分别
             * 找到
             * 左边比枢纽大的值
             * 右边比枢纽小的值
             *
             * 然后交换这两个值
             * */
            if (leftPtr >= rightPtr)
                break;
            else {
                swap(leftPtr, rightPtr);
            }
        }
        return leftPtr;
    }

    public void swap(int dex1, int dex2) {
        long temp;
        temp = theArrayy[dex1];
        theArrayy[dex1] = theArrayy[dex2];
        theArrayy[dex2] = temp;
    }
}
