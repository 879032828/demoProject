package com.example.datastructure;

public class testOrdArray {

    public static void main(String[] args) {
        OrdArray ordArray = new OrdArray(10);

        ordArray.insert(9);
        ordArray.insert(1);
        ordArray.insert(4);
        ordArray.insert(3);
        ordArray.insert(7);
        ordArray.insert(5);

        ordArray.display();
    }

}
