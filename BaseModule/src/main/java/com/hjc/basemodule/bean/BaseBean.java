package com.hjc.basemodule.bean;

import java.io.Serializable;

/**
 * @Describe: 基本数据
 * @Author: huangjc
 * @Email: 252431193@qq.com
 * @Date: 2018/3/19
 */


public class BaseBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public int code;

    public String msg;

    public String sign;

    public String serialNo;

    public T data;
}
