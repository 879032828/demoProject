package com.example.administrator.view_test.event;


import com.example.administrator.view_test.event.deps.UIEventManagerImpl;

import java.lang.reflect.Method;

/**
 * 事件管理器
 */
public abstract class UIEventManager {
    private static UIEventManager instance;

    public static synchronized UIEventManager getInstance() {
        if (instance == null) {
            instance = new UIEventManagerImpl();
        }
        return instance;
    }

    protected UIEventManager() {

    }

    /**
     * 判断某个类是否可以处理UI事件
     *
     * @param clazz
     * @return
     */
    public boolean canHandleUIEvent(Class clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(UIEventHandler.class)) {
                return true;
            }
        }

        for (Method m : clazz.getMethods()) {
            if (m.isAnnotationPresent(UIEventHandler.class)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 在UI线程中执行代码
     *
     * @param run
     */
    public abstract void runOnUiThread(final Runnable run);

    /***
     * 注册自己感兴趣的事件到事件中心
     * 如果该模块被卸载或者禁用了,请务必取消注册
     *
     * @param intrestedEvents 感兴趣的事件ID，可以是多个
     * @param listener        监听器
     */
    public abstract void register(UIEventType[] intrestedEvents, UIEventListener listener);

    /***
     * 只注销已经注册特定的事件监听器
     *
     * @param intrestedEvents
     * @param listener
     */
    public abstract void unregister(UIEventType[] intrestedEvents, UIEventListener listener);

    /**
     * 注销和指定监听器所有感兴趣的事件
     *
     * @param listener
     */
    public abstract void unregister(UIEventListener listener);


    /***
     * 广播一个事件，所有对这个感兴趣的监听器都会被调用
     *
     * @param event
     */
    public abstract void broadcast(UIEvent event);

    /**
     * 成功回调
     *
     * @param callBack
     * @param data
     */
    public void sendSuccessCallBack(final SnapCallBack callBack, final Object data) {
        if (callBack != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(data);
                }
            });
        }
    }

    /**
     * 失败回调
     *
     * @param callBack
     * @param code
     * @param error
     */
    public void sendErrorCallBack(final SnapCallBack callBack, final int code, final String error) {
        if (callBack != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callBack.onError(code, error);
                }
            });
        }
    }

}
