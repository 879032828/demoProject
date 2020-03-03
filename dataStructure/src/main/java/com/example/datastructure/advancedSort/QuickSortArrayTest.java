package com.example.datastructure.advancedSort;

public class QuickSortArrayTest {

    public static void main(String[] args) {
        int maxSize = 16;
        QuickSortArray arr;

        arr = new QuickSortArray(maxSize);

        for (int j = 0; j < maxSize; j++) {
            long n = (long) (Math.random() * 199);
            arr.insert(n);
        }

        arr.display();
        System.out.println("====================================");

        int size = arr.size();
        arr.recQuickSort(0, size - 1);
        arr.display();


    }
}
