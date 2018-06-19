package com.example.administrator.view_test.event;

import java.util.HashMap;
import java.util.Map;

/**
 * 基事件
 * Created by shen on 16/4/8.
 */
public class BaseUIEvent {
    protected Map<String, Object> dataMap = new HashMap<String, Object>();

    public void putData(String key, Object value){
        this.dataMap.put(key, value);
    }

    public boolean hasData(String key){
        return this.dataMap.containsKey(key);
    }

    public <T> T getData(String key){
        return (T) this.dataMap.get(key);
    }

    public int getInt(String key){
        return getDataEx(key);
    }

    public long getLong(String key){
        return getDataEx(key);
    }

    public boolean getBoolean(String key){
        return getDataEx(key);
    }

    public String getString(String key){
        return getDataEx(key);
    }

    private <T> T getDataEx(String key){
        if(hasData(key)){
            return (T) getData(key);
        }
        return null;
    }


//    protected int eventId;
//    protected Object target;
//    protected BaseUIEvent relatedEvent;

//    public int getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(int eventId) {
//        this.eventId = eventId;
//    }

//    public Object getTarget() {
//        return target;
//    }
//
//    public void setTarget(Object target) {
//        this.target = target;
//    }

//    public BaseUIEvent getRelatedEvent() {
//        return relatedEvent;
//    }
//
//    public void setRelatedEvent(BaseUIEvent relatedEvent) {
//        this.relatedEvent = relatedEvent;
//    }
}
