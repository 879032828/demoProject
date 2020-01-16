package com.example.datastructure.link;

/**
 * 使用有序链表，将一个无序数组排序
 */
public class ListInsertionSort {

    private Link first;
    private int size;

    /**
     * 将一个无序数组插入有序链表中
     *
     * @param array
     */
    public void insertAll(Link[] array) {
        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    /**
     * 将有序链表中的值输出到数组中
     *
     * @return
     */
    public Link[] findAll() {
        Link temp = first;
        Link[] result = new Link[size];
        int count = 0;
        while (temp != null) {
            System.out.println("the link" + temp.toString());
            result[count] = temp;
            count++;
            temp = temp.next;
        }
        return result;
    }

    public void insert(Link link) {
        Link previous = null;
        Link current = first;

        while (current != null && link.age > current.age) {
            previous = current;
            current = current.next;
        }//找到要插入的位置的（上一个值）和（下一个值）

        if (previous == null) {
            first = link;
        } else {
            previous.next = link;//将（上一个值）的next指向要插入的点
        }

        link.next = current;//将要插入的点的next指向（下一个值）

        size++;
    }

    public void deleteFirst() throws Exception {
        if (isEmpty())
            return;
        first = first.next;
        size--;
    }

    /**
     * 在链表中查找包含指定关键字的链结点
     *
     * @param age
     * @return
     */
    public Link find(int age) {
        Link current = first;
        while (current != null) {
            if (current.age == age) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Link delete(int age) {
        Link current = first;
        Link before = null;
        while (current != null) {
            if (current.age == age) {
                if (before != null) {
                    //将当前节点的前一个节点，指向当前节点的下一个节点
                    //相当于删除当前节点
                    before.next = current.next;
                } else {
                    first = current.next;
                }
                return current;
            }
            before = current;
            current = current.next;

            size--;
        }
        return null;
    }

    public void display() {
        Link temp = first;
        while (temp != null) {
            System.out.println("the link" + temp.toString());
            temp = temp.next;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return first == null;
    }

}
