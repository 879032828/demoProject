package com.example.datastructure.link;

/**
 * 链表迭代器
 *
 * 迭代器就是为了能够更方便操作链表
 */
public class Iterator {

    private Link current;
    private Link previous;
    private DoublyLinked list;

    public Iterator(DoublyLinked list) {
        this.list = list;
        reset();
    }

    /**
     * 把迭代器设置在表头
     */
    public void reset() {
        current = this.list.getFirst();
        previous = null;
    }

    /**
     * 把迭代器移动到下一个链结点
     *
     * @return
     */
    public Link nextLink() {
        previous = current;
        current = current.next;
        return current;
    }

    /**
     * 返回迭代器指向的链结点
     *
     * @return
     */
    public Link getCurrent() {
        return current;
    }

    /**
     * 如果迭代器到达表尾，返回true
     *
     * @return
     */
    public boolean atEnd() {
        return (current.next == null);
    }

    /**
     * 在迭代器后面插入一个新链结点
     *
     * @param age
     * @return
     */
    public boolean insertAfter(int age) {
        Link newLink = new Link(age);

        if (list.isEmpty()) {
            list.setFirst(age);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();
        }
        return true;
    }

    /**
     * 在迭代器前面插入一个新链结点
     *
     * @param age
     */
    public void insertBefore(int age) {
        Link newLink = new Link(age);
        if (previous == null) {
            newLink.next = list.getFirst();
            list.setFirst(age);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    /**
     * 删除迭代器所指向链结点
     *
     * @return
     */
    public boolean deleteCurrent() {
        long value = current.age;
        if (previous == null) {
            list.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }
        return true;
    }
}
