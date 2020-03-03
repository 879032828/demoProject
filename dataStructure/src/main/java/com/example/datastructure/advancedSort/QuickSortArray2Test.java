package com.example.datastructure.advancedSort;

/**
 * 快速排序测试
 */
public class QuickSortArray2Test {

    public static void main(String[] args) {
        int maxSize = 16;
        QuickSortArray2 arr;

        arr = new QuickSortArray2(maxSize);

        arr.insert(38);
        arr.insert(56);
        arr.insert(13);
        arr.insert(143);
        arr.insert(90);
        arr.insert(134);
        arr.insert(161);
        arr.insert(167);
        arr.insert(155);
        arr.insert(61);
        arr.insert(123);
        arr.insert(67);
        arr.insert(132);
        arr.insert(1);
        arr.insert(147);
        arr.insert(90);


//        for (int j = 0; j < maxSize; j++) {
//            long n = (long) (Math.random() * 199);
//            arr.insert(n);
//        }

        arr.display();
        System.out.println("====================================");

        int size = arr.size();
        arr.recQuickSort(0, size - 1);
        arr.display();
    }

}
