package com.example.datastructure.tree234;

public class DataItem {

    public long dData;

    public DataItem(long dData) {
        this.dData = dData;
    }

    public long getdData() {
        return dData;
    }

    public void setdData(long dData) {
        this.dData = dData;
    }

    public void displayItem() {
        System.out.println("/" + dData);
    }
}
