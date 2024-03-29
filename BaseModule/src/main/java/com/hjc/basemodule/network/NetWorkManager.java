package com.hjc.basemodule.network;

import android.util.Log;


import com.hjc.basemodule.constant.URLs;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @Describe: 网络请求管理类
 * @Author: huangjc
 * @Email: 252431193@qq.com
 * @Date: 2018/3/19
 */


public class NetWorkManager {
    public static NetWorkManager netWorkManager;

    private NetWorkApi netWorkApi;

    public static NetWorkManager getInstance() {
        if (netWorkManager == null) {
            netWorkManager = new NetWorkManager();
        }
        return netWorkManager;
    }

    private NetWorkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
        netWorkApi = retrofit.create(NetWorkApi.class);
    }

    public NetWorkApi getNetwork() {
        return netWorkApi;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        //设置超时时间
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS);


        //日志处理  notice：目前日志拦截器与上传进度监听器冲突，导致上传流执行两遍，在使用上传监听时需要注释掉日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(s -> Log.i("OkHttp", s));

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        //忽略证书验证
//        clientBuilder.sslSocketFactory(SSLSocketClientUtil.getSSLSocketFactory());
//        clientBuilder.hostnameVerifier(SSLSocketClientUtil.getHostnameVerifier());

        //缓存处理

        return clientBuilder.build();
    }
}
