package com.hjc.basemodule.dataProvider;


import android.app.Application;

import androidx.annotation.NonNull;

import com.hjc.basemodule.bean.BaseBean;
import com.hjc.basemodule.constant.ErrorCode;
import com.hjc.basemodule.network.RequestManager;

import org.reactivestreams.Subscription;

import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

import io.reactivex.FlowableSubscriber;
import retrofit2.HttpException;
import retrofit2.http.HTTP;

/**
 * @Describe:
 * @Author: hjc
 * @Email: 252431193@qq.com
 * @Date: 2021/12/6
 */
public abstract class CustomSubscriber<T> implements FlowableSubscriber<T> {

    private int requestId;

    protected abstract void onSuccess(T response);

    protected abstract void onError(int code);

    protected abstract void onError(int code, String msg);

    @Override
    public void onSubscribe(@NonNull Subscription s) {
        int requestId = RequestManager.getINSTANCE().generateRequestId();
        RequestManager.getINSTANCE().putRequest(requestId, s);
        s.request(1);
    }

    @Override
    public void onNext(T t) {
        try {
            if (t == null) {
                onError(ErrorCode.UNKNOWN);
            }
            if (t instanceof BaseBean) {
                BaseBean baseBean = (BaseBean) t;
                if (baseBean.data == null) {
                    onError(baseBean.code, baseBean.msg);
                } else {
                    onSuccess(t);
                }
            }
        } catch (Exception e) {
            onError(ErrorCode.UNKNOWN);
        }
    }

    @Override
    public void onError(Throwable t) {
        //服务器
        if (t instanceof HttpException) {
            onError(ErrorCode.HTTP_ERROR);
        }
        //服务器超时
        if (t instanceof SocketTimeoutException) {
            onError(ErrorCode.TIME_OUT);
        }
        //找不到主机
        if (t instanceof NoRouteToHostException) {
            onError(ErrorCode.NO_ROUTE_TO_HOST);
        }
        //其它
        onError(ErrorCode.UNKNOWN);
    }

    @Override
    public void onComplete() {
        RequestManager.getINSTANCE().removeRequest(requestId);
    }
}
