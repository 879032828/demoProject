package com.example.administrator.view_test.splash;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class Splash implements Serializable {
    private static final long serializableversionID = 7777777;

    public int id;

    public String url;

    public String click_url;

    public String savePath;

    public Splash(int id, String url, String click_url, String savePath) {
        this.id = id;
        this.url = url;
        this.click_url = click_url;
        this.savePath = savePath;
    }

    @Override
    public String toString() {
        return "Splash{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", click_url='" + click_url + '\'' +
                ", savePath='" + savePath + '\'' +
                '}';
    }

}
