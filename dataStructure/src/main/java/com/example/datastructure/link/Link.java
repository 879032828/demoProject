package com.example.datastructure.link;

/**
 * 链结点
 */
public class Link {


    public int age;
    public String name;
    public Link next;
    public Link previous;

    public Link() {

    }

    public Link(int age) {
        this.age = age;
    }


    public Link(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Link{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
