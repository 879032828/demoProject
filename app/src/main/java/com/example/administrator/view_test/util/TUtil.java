package com.example.administrator.view_test.util;

import java.lang.reflect.ParameterizedType;

/**
 * 类转换初始化
 *
 * 初始化带泛型的父类的参数
 */
public class TUtil {
    public static <T> T getT(Object o, int i) {
        try {
            //先获取父类
            //再用getGenericSuperclass获取带泛型的父类
            //然后用getActualTypeArguments获取泛型的参数数组
            //最后newInstance初始化对象
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
