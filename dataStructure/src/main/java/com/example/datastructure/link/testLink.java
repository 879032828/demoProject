package com.example.datastructure.link;

public class testLink {


    public static void main(String[] args) {
        //链表的使用
//        LinkList linkList = new LinkList();
//
//        Link link1 = new Link(1, "11");
//        Link link2 = new Link(2, "22");
//        Link link3 = new Link(3, "33");
//        Link link4 = new Link(4, "44");
//        Link link5 = new Link(5, "55");
//        Link link6 = new Link(6, "66");
//
//        linkList.insertFirst(link1);
//        linkList.insertFirst(link2);
//        linkList.insertFirst(link3);
//        linkList.insertFirst(link4);
//        linkList.insertFirst(link5);
//        linkList.insertFirst(link6);
//
//        linkList.display();
//
//        System.out.println("find :" + linkList.find(3));
//
//        linkList.delete(3);
//
//        System.out.println("========================================================================");
//        linkList.display();

//        SortedList linkList = new SortedList();
//
//        Link link1 = new Link(1, "11");
//        Link link3 = new Link(3, "33");
//        Link link5 = new Link(5, "55");
//        Link link2 = new Link(2, "22");
//        Link link4 = new Link(4, "44");
//        Link link6 = new Link(6, "66");
//
//        linkList.insert(link1);
//        linkList.insert(link2);
//        linkList.insert(link3);
//        linkList.insert(link4);
//        linkList.insert(link5);
//        linkList.insert(link6);
//
//        linkList.display();


/***************************使用有序链表，将一个无序数组排序***************************/
//        ListInsertionSort listInsertionSort = new ListInsertionSort();
//
//        //创建一个无序数组
//        Link link1 = new Link(1, "11");
//        Link link3 = new Link(3, "33");
//        Link link5 = new Link(5, "55");
//        Link link2 = new Link(2, "22");
//        Link link4 = new Link(4, "44");
//        Link link6 = new Link(6, "66");
//        Link[] links = new Link[6];
//        links[0] = link1;
//        links[1] = link2;
//        links[2] = link3;
//        links[3] = link4;
//        links[4] = link5;
//        links[5] = link6;
//
//        listInsertionSort.insertAll(links);
//        Link[] temp = listInsertionSort.findAll();
//        for (int i = 0; i < temp.length; i++) {
//            System.out.println(temp[i]);
//        }

/*************************** 测试双向链表 ***************************/
        DoublyLinked doublyLinked = new DoublyLinked();

        doublyLinked.insertFirst(6);
        doublyLinked.insertFirst(2);
        doublyLinked.insertFirst(3);
        doublyLinked.insertFirst(4);
        doublyLinked.insertFirst(5);
        doublyLinked.insertLast(1);
        doublyLinked.insertAfter(1);

        doublyLinked.display();

//        System.out.println("deleteFirst");
//        doublyLinked.deleteFirst();
//        doublyLinked.display();
//
//        System.out.println("deleteLast");
//        doublyLinked.deleteLast();
//        doublyLinked.display();
    }

}
