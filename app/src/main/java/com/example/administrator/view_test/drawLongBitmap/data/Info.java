package com.example.administrator.view_test.drawLongBitmap.data;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {

    String content;
    List<String> imageList;
    String iconPath;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

}