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

        SortedList linkList = new SortedList();

        Link link1 = new Link(1, "11");
        Link link3 = new Link(3, "33");
        Link link5 = new Link(5, "55");
        Link link2 = new Link(2, "22");
        Link link4 = new Link(4, "44");
        Link link6 = new Link(6, "66");

        linkList.insert(link1);
        linkList.insert(link2);
        linkList.insert(link3);
        linkList.insert(link4);
        linkList.insert(link5);
        linkList.insert(link6);

        linkList.display();
    }

}
