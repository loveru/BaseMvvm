package com.hjc.basemodule.network;

import android.util.SparseArray;

import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Describe:
 * @Author: hjc
 * @Email: 252431193@qq.com
 * @Date: 2021/12/6
 */
public class RequestManager {

    private static final RequestManager INSTANCE = new RequestManager();

    private AtomicInteger requestId = new AtomicInteger();

    private SparseArray<Subscription> subscriptionSparseArray = new SparseArray<>();

    private RequestManager() {
    }

    public int generateRequestId() {
        return requestId.getAndIncrement();
    }

    public void putRequest(int key, Subscription subscription) {
        subscriptionSparseArray.put(key, subscription);
    }

    public void removeRequest(int key) {
        synchronized (this) {
            subscriptionSparseArray.remove(key);
        }
    }

    public static RequestManager getINSTANCE() {
        return INSTANCE;
    }
}
