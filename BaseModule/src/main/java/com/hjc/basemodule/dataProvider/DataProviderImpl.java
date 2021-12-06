package com.hjc.basemodule.dataProvider;


import androidx.annotation.NonNull;

import com.hjc.basemodule.bean.UserInfo;
import com.hjc.basemodule.network.NetWorkManager;

import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;

/**
 * @Describe: 业务层实现
 * @Author: huangjc
 * @Email: 252431193@qq.com
 * @Date: 2018/3/19
 */


public class DataProviderImpl implements DataProvider {

    @Override
    public Flowable<UserInfo> login(String mobile, String password) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("username", mobile);
        map.put("password", password);
        map.put("mac", "123456");
        map.put("version", "1");
        map.put("softType", "1");
        NetWorkManager.getInstance().getNetwork().login(map).subscribe(new FlowableSubscriber<UserInfo>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {

            }

            @Override
            public void onNext(UserInfo userInfo) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        return NetWorkManager.getInstance().getNetwork().login(map);
    }

}
