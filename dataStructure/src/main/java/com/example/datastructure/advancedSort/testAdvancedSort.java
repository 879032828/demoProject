package com.example.datastructure.advancedSort;

public class testAdvancedSort {

    public static void main(String[] args) {
        ShellArray selectArray = new ShellArray(20);
        selectArray.insert(2);
        selectArray.insert(4);
        selectArray.insert(1);
        selectArray.insert(7);
        selectArray.insert(5);
        selectArray.insert(3);
        selectArray.insert(6);
        selectArray.insert(10);
        selectArray.insert(14);
        selectArray.insert(11);
        selectArray.insert(8);
        selectArray.insert(13);
        selectArray.insert(9);
        selectArray.insert(12);
        selectArray.shellSort();
        selectArray.display();
    }

}
