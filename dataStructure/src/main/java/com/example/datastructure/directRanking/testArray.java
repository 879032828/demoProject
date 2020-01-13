package com.example.datastructure.directRanking;

public class testArray {

    public static void main(String[] args) {
//有序数组 ---- 二分查找法
//        OrdArray ordArray = new OrdArray(10);
//        ordArray.insert(9);
//        ordArray.insert(1);
//        ordArray.insert(4);
//        ordArray.insert(3);
//        ordArray.insert(7);
//        ordArray.insert(5);
//        ordArray.display();

//无序数组 ---- 选择排序
//        SelectArray selectArray = new SelectArray(10);
//        selectArray.insert(2);
//        selectArray.insert(4);
//        selectArray.insert(1);
//        selectArray.insert(7);
//        selectArray.insert(3);
//        selectArray.insert(99);
//        selectArray.selectSort();
//        selectArray.display();


        InsertArray selectArray = new InsertArray(10);
        selectArray.insert(2);
        selectArray.insert(4);
        selectArray.insert(1);
        selectArray.insert(7);
        selectArray.insert(1);
        selectArray.insert(3);
        selectArray.insert(99);
        selectArray.insertSort();
        selectArray.display();
    }

}
