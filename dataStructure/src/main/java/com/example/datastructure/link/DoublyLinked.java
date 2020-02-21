package com.example.datastructure.link;

/**
 * 双向链表
 */
public class DoublyLinked {

    private Link first;
    private Link last;


    //增删改查

    public DoublyLinked() {
    }

    /**
     * 链表为空
     *
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link link) {
        first = link;
    }

    public void setFirst(int age) {
        Link newLink = new Link(age);
        first = newLink;
    }

    public Iterator getIterator() {
        return new Iterator(this);
    }

    /**
     * 插入到头部
     *
     * @param age
     */
    public void insertFirst(int age) {
        Link newLink = new Link(age);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
            return;
        }

        newLink.next = first;
        newLink.next.previous = newLink;
        first = newLink;
    }

    /**
     * 插入到尾部
     */
    public void insertLast(int age) {
        Link newLink = new Link(age);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
            return;
        }

        last.next = newLink;
        newLink.previous = last;
        last = newLink;
    }

    /**
     * 删除第一个节点
     */
    public void deleteFirst() {
        if (isEmpty()) {
            return;
        } else {
            if (first.next != null) {
                first = first.next;
            } else {
                first = null;
                last = null;
            }
        }
    }

    /**
     * 删除最后一个节点
     */
    public void deleteLast() {
        if (isEmpty()) {
            return;
        } else {
            if (last.previous != first) {
                last = last.previous;
                last.next = null;
            } else {
                first = null;
                last = null;
            }
        }
    }

    /**
     * 插入到指定的位置后面
     *
     * @param age
     */
    public boolean insertAfter(int age) {
        Link data = find(age);
        if (data == null) {
            return false;
        }

        if (data.next == null) {
            insertLast(age);
            return true;
        }

        Link newLink = new Link(age);
        newLink.next = data.next;//第一步
        newLink.next.previous = newLink;//第二步

        data.next = newLink;//第三步
        newLink.previous = data;//第四步

        return true;
    }

    /**
     * 删除指定位置
     *
     * @param age
     */
    public void deleteKey(int age) {
        Link data = find(age);

        if (data == first) {
            first = data.next;
        } else {
            data.previous.next = data.next;//只要不是第一个，那么当前位置的上一个，要指向当前位置的下一个
        }

        if (data == last) {
            last = data.previous;
        } else {
            data.next.previous = data.previous;//只要不是最后一个，那么当前位置的下一个，要指向当前位置的上一个
        }
    }

    public Link find(int age) {
        Link next = first;
        while (next != null) {
            if (next.age == age) {
                return next;
            }
            next = next.next;
        }

        return null;
    }

    public void display() {
        Link next = first;
        while (next != null) {
            System.out.println("value :" + next.age);
            next = next.next;
        }
    }

}
