package com.hjc.basemodule.dataProvider;

/**
 * @Describe:
 * @Author: hjc
 * @Email: 252431193@qq.com
 * @Date: 2021/12/6
 */
public interface Callback<T> {

    void onSuccess(T Response);
    void onError(int code,String msg);
}
