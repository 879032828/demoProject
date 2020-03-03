package com.example.datastructure.advancedSort;

/**
 * 划分算法测试
 */
public class partitionTest {

    public static void main(String[] args) {
        int maxSize = 16;
        partition arr;

        arr = new partition(maxSize);

        for (int j = 0; j < maxSize; j++) {
            long n = (long) (Math.random() * 199);
            arr.insert(n);
        }

        arr.display();

        long pivot = 99;

        System.out.println("the pivot is :" + pivot);

        int size = arr.size();
        int pardex = arr.partitionIt(0, size - 1, pivot);

        System.out.println("partition is at index :" + pardex);

        arr.display();
    }

}
