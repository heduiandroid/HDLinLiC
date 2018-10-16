package com.linli.consumer.net;

import com.linli.consumer.bean.General;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;

import retrofit2.Call;

/**
 * Created by hasee on 2018/10/9.
 */

public class CafeNet {
    public static final String SUCCESS = "200";     //接口请求成功
    /**
     * 用户注册获取验证码
     *
     * @param handleSuccess
     */
    public static void sendPhoneCode(String phone, final HandleSuccess<General> handleSuccess) {
        Call<General> call = NetUtil.createCnApi(Common.myUrl).sendPhoneCode(phone);
        call.enqueue(handleSuccess);
    }
    /**
     * 用户注册
     *
     * @param handleSuccess
     */
    public static void register(String phone,String password,String phoneCode,String idCard,final HandleSuccess<General> handleSuccess) {
        Call<General> call = NetUtil.createCnApi(Common.myUrl).register(phone,password,phoneCode,idCard);
        call.enqueue(handleSuccess);
    }
}
