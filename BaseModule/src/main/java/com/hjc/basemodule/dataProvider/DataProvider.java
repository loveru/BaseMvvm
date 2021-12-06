package com.hjc.basemodule.dataProvider;

import com.hjc.basemodule.bean.UserInfo;

import io.reactivex.Flowable;

/**
 * @Describe: 业务层接口
 * @Author: huangjc
 * @Email: 252431193@qq.com
 * @Date: 2018/3/19
 */


public interface DataProvider {

    /**
     * 登录
     *
     * @param mobile   手机号码
     * @param password 密码
     * @return
     */
    Flowable<UserInfo> login(String mobile, String password);

}
