/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
 * Project  : IQQ_V2.1
 * Package  : iqq.app.action
 * File     : IMEventHandlerProxy.java
 * Author   : solosky < solosky772@qq.com >
 * Created  : 2013-3-16
 * License  : Apache License 2.0
 */
package com.example.administrator.view_test.event;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用这个类简化事件的注册，分发
 * 只需在被代理的类使用@IMEventHandler注解需要处理的事件类型即可
 *
 */
public class UIEventDispatcher implements UIEventListener {
    private Map<UIEventType, Method> methodMap = new HashMap<UIEventType, Method>();
    private Object target;

    public UIEventDispatcher(Object target) {
        this.target = target;
        for (Method m : target.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(UIEventHandler.class)) {
                UIEventHandler handler = m.getAnnotation(UIEventHandler.class);
                this.methodMap.put(handler.value(), m);
                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }
            }
        }
        for (Method m : target.getClass().getMethods()) {
            if (m.isAnnotationPresent(UIEventHandler.class)) {
                UIEventHandler handler = m.getAnnotation(UIEventHandler.class);
                this.methodMap.put(handler.value(), m);
                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }
            }
        }
    }

    public UIEventType[] getEventTypes() {
        return methodMap.keySet().toArray(new UIEventType[]{});
    }

    @Override
    public void onUIEvent(UIEvent imEvent) {
        Method m = methodMap.get(imEvent.getType());
        if (m != null) {
            try {
                m.invoke(target, imEvent);
            } catch (Throwable e) {
                Log.w("snap_im_event", e);
            }
        } else {
            Log.w("snap_im_event", "UIEventHandler Not Found!! imEvent="+imEvent);
        }
    }
}
