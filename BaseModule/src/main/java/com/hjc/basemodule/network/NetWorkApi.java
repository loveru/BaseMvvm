package com.hjc.basemodule.network;


import com.hjc.basemodule.bean.UserInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @Describe: 网络请求API
 * @Author: huangjc
 * @Email: 252431193@qq.com
 * @Date: 2018/3/19
 */


public interface NetWorkApi {
    /**
     * 登录
     *
     * @param queryMap
     * @return
     */
    @Headers("Authorization:Basic YmVjc3lzdGVtOmJlYzEyMzQh")
    @FormUrlEncoded
    @POST("authentication/form")
    Flowable<UserInfo> login(@FieldMap Map<String, String> queryMap);
}
