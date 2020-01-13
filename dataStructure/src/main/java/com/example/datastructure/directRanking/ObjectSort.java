package com.example.datastructure.directRanking;

/**
 * 对象排序
 */
public class ObjectSort {

    private Person[] person;

    private int count;

    public ObjectSort(int max) {
        person = new Person[max];
        count = 0;
    }

    public void insert(String last, String first, int age) {
        person[count] = new Person(last, first, age);
        count++;
    }

    public void display() {
        for (int i = 0; i < count; i++) {
            person[i].toString();
            System.out.println("");
        }
    }

    /**
     * 单词排序
     * <p>
     * compareTo方法的操作
     * <p>
     * s1 < s2  <0
     * s1 = s2  0
     * s1 > s2  >0
     */
    public void insertionSort() {
        int in, out;
        for (out = 1; out < count; out++) {
            Person temp = person[out];
            in = out;
            //in > 0 是为了判断左边的有序数组是否循环完毕
            // a[in - 1] >= temp 是为了判断左边的有序数组的值是否大于被标记的值
            //大于的话，交换值，然后继续循环
            while (in > 0 && person[in - 1].getLastName().compareTo(temp.getLastName()) > 0) {
                person[in] = person[in - 1];
                --in;
            }
            //小于等于的话，跳出循环，将值插入
            person[in] = temp;
        }

    }

}
