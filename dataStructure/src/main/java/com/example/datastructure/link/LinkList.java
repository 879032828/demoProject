package com.example.datastructure.link;

/**
 * 这个链表仅有的操作是：
 * 1、在链表头插入一个数据项
 * 2、在链表头删除一个数据项
 * 3、遍历链表显示它的内容
 */
public class LinkList {

    private Link first;

    public void insertFirst(Link link) {
        Link temp = first;
        first = link;
        first.next = temp;
    }

    public void deleteFirst() throws Exception {
        if (isEmpty())
            return;
        first = first.next;
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

    public boolean delete(int age) {
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
                return true;
            }
            before = current;
            current = current.next;
        }
        return false;
    }

    public void display() {
        Link temp = first;
        while (temp != null) {
            System.out.println("the link" + temp.toString());
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}
