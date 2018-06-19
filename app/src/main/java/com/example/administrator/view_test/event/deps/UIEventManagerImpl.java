package com.example.administrator.view_test.event.deps;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.example.administrator.view_test.event.UIEvent;
import com.example.administrator.view_test.event.UIEventListener;
import com.example.administrator.view_test.event.UIEventManager;
import com.example.administrator.view_test.event.UIEventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UIEventManagerImpl extends UIEventManager {
    private Handler handler;
    /**
     * Type => list<listener> *
     */
    private Map<UIEventType, List<UIEventListener>> lookup;

    public UIEventManagerImpl() {
        super();
        lookup = new ConcurrentHashMap<UIEventType, List<UIEventListener>>();
        if (Looper.myLooper() != null) {
            handler = new Handler();
        }
    }

    @Override
    public void runOnUiThread(final Runnable run) {
        handler.post(run);
    }

    @Override
    public void register(UIEventType[] intrestedEvents, UIEventListener listener) {
        for (UIEventType type : intrestedEvents) {
            List<UIEventListener> list = lookup.get(type);
            if (list == null) {
                list = new ArrayList<UIEventListener>();
                lookup.put(type, list);
            }
            //加上去除处理
            if (!list.contains(listener)) {
                list.add(listener);
            }

        }
    }

    @Override
    public void unregister(UIEventType[] intrestedEvents, UIEventListener listener) {
        for (UIEventType type : intrestedEvents) {
            List<UIEventListener> list = lookup.get(type);
            if (list != null) {
                list.remove(listener);
            }
        }
    }

    @Override
    public void unregister(UIEventListener listener) {
        unregister(lookup.keySet().toArray(new UIEventType[0]), listener);
    }

    @Override
    public void broadcast(final UIEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                doBroadcast(event);
            }
        });
    }

    private void doBroadcast(UIEvent event) {
        List<UIEventListener> list = lookup.get(event.getType());
        Log.d("snap_im_event", "Broadcast UIEvent: " + event.getType() + ", listeners: " + (list != null ? list.size() : 0));
        if (list != null && list.size() > 0) {
            list = new ArrayList<UIEventListener>(list);
            for (UIEventListener listener : list) {
                try {
                    listener.onUIEvent(event);
                } catch (Throwable e) {
                    Log.e("snap_im_event", "Broadcast UIEvent Error!", e);
                }
            }
        }
    }

}
