package com.example.datastructure.advancedSort;

/**
 * 希尔排序
 */
public class ShellArray {

    private long[] a;
    private int nElems;

    public ShellArray(int max) {
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

    public void shellSort() {
        int inner, outer;
        long temp;

        int h = 1;//数据项之间的间隔：增量
        while (h < nElems / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;

                while (inner > h - 1 && a[inner - h] >= temp) {
                    a[inner] = a[inner - h];
                    inner -= h;
                }
                a[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * 插入排序
     * <p>
     * 对于随机顺序的数据进行插入排序也是需要O(N2)
     * <p>
     * 对于已经有序或基本有序的数据来说，插入排序要好得多。
     * 当数据有序的时候，while循环的条件总是假，所以它变成了外层循环中的一个简单语句
     */
    public void insertSort() {
        int in, out;
        for (out = 1; out < nElems; out++) {
            long temp = a[out];
            in = out;
            //in > 0 是为了判断左边的有序数组是否循环完毕
            // a[in - 1] >= temp 是为了判断左边的有序数组的值是否大于被标记的值
            //大于的话，交换值，然后继续循环
            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            //小于等于的话，跳出循环，将值插入
            a[in] = temp;
        }
    }

    /**
     * 选择排序一半的数据
     */
    public void selectSort() {
        int out, in, min;
        for (out = 0; out < nElems / 2; out++) {
            min = out;
            for (in = out + 1; in < nElems / 2; in++) {
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
