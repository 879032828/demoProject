package com.example.administrator.view_test.event;


import java.io.Serializable;


public class UIEvent extends BaseUIEvent implements Serializable{
    /**事件ID，定义在IMEventID*/
    private UIEventType type;

    public UIEvent(UIEventType type) {
        this.type = type;
    }

    public UIEvent() {
    }




    public UIEventType getType() {
        return type;
    }

    public void setType(UIEventType type) {
        this.type = type;
    }

}
