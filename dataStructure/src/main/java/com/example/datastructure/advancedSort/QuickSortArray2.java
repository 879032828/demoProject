package com.example.datastructure.advancedSort;

/**
 * 快速排序
 */
public class QuickSortArray2 {

    private long[] a;
    private int nElems;

    public QuickSortArray2(int max) {
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
     * 采用三数据项取中的递归的快速排序算法
     */
    public void recQuickSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 3) {
            mamualSort(left, right);
        } else {
            int pivot = (int) medianOf3(left, right);
            int partition = partition(left, right, pivot);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    /**
     * 获取中值
     *
     * @param left
     * @param right
     * @return
     */
    public long medianOf3(int left, int right) {
        int medianOffset = (right + left) / 2;
        if (a[left] > a[medianOffset]) {
            swap(left, medianOffset);
        }

        if (a[left] > a[right]) {
            swap(left, right);
        }

        if (a[medianOffset] > a[right]) {
            swap(medianOffset, right);
        }

        swap(medianOffset, right - 1);
        return a[right - 1];
    }

    private int partition(int left, int right, int pivot) {
        int leftPtr = left;
        int righttPtr = right - 1;

        while (true) {
            while (a[++leftPtr] < pivot) {
                ;
            }
            while (a[--righttPtr] > pivot) {
                ;
            }

            if (leftPtr >= righttPtr) {
                break;
            } else {
                swap(leftPtr, righttPtr);
            }
        }

        swap(leftPtr, right - 1);//划分成两个子数组之后，将枢纽插入到两个子数组的分界处。
        return leftPtr;
    }

    public void swap(int dex1, int dex2) {
        long temp;
        temp = a[dex1];
        a[dex1] = a[dex2];
        a[dex2] = temp;
    }

    /**
     * 正常计算
     *
     * @param left
     * @param right
     */
    public void mamualSort(int left, int right) {
        int size = right - left + 1;
        if (size == 1) {
            return;
        }
        if (size == 2) {
            if (a[left] > a[right]) {
                swap(left, right);
            }
        }

        if (size == 3) {
            if (a[left] > a[right - 1]) {
                swap(left, right - 1);
            }

            if (a[left] > a[right]) {
                swap(left, right);
            }

            if (a[right - 1] > a[right]) {
                swap(right - 1, right);
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
