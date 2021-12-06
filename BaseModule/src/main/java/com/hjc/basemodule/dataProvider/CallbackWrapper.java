package com.hjc.basemodule.dataProvider;


/**
 * @Describe:
 * @Author: hjc
 * @Email: 252431193@qq.com
 * @Date: 2021/12/6
 */
public class CallbackWrapper<T> extends CustomSubscriber<T> {

    private Callback<T> callback;

    public CallbackWrapper(Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    protected void onSuccess(T response) {
        if (callback != null) {
            callback.onSuccess(response);
        }
    }

    @Override
    protected void onError(int code) {

    }

    @Override
    protected void onError(int code, String msg) {
        if (callback != null) {
            callback.onError(code, msg);
        }
    }
}
