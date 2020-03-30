package com.example.datastructure.binaryTree;

import com.example.datastructure.advancedSort.partition;

public class TreeTest {

    public static void main(String[] args) {
        int maxSize = 16;
        Tree arr;

        arr = new Tree();

        for (int j = 0; j < maxSize; j++) {
            int n = (int) (Math.random() * 199);
            arr.insert(n, 5);
        }

        arr.inOrder(arr.getRoot());

        System.out.println("The minimun ;" + arr.minimum().iData);
    }

}
