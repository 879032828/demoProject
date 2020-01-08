package com.example.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺信息
 */
public class shopInfo {

    public int age;
    public String name;

    public static void main(String[] args) {
        List<shopInfo> shopInfoList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            shopInfo shopInfo = new shopInfo(18, "hehehe");
            shopInfoList.add(shopInfo);
        }

        for (int i = 0; i < 16; i++) {
            System.out.println(shopInfoList.get(i).getName());
        }


    }

    public shopInfo(int age) {
        this.age = age;
    }

    public shopInfo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
