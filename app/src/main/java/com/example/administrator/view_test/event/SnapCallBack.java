package com.example.administrator.view_test.event;

/**
 * Created by shen on 16/3/21.
 */
public interface SnapCallBack {
    final int ERROR_LOGIN_FAIL= -1;
    final int ERROR_UPDATE_USER_INFO= -2;
    final int ERROR_DELETE_FRIEND= -3;
    /**
     * 程序执行成功时执行回调函数
     */
      void onSuccess(Object data);
    /**
     * 发生错误时调用的回调函数
     * @param code 错误代码
     * @param error 包含文本类型的错误描述
     */
      void onError(int code, String error);
}
