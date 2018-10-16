package com.linli.consumer.net;

import com.linli.consumer.bean.General;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hasee on 2018/10/9.
 */

public interface CafeNetApi {
    /*
用户注册获取验证码
 */
    @FormUrlEncoded
    @POST("personal/sendPhoneCode")
    Call<General> sendPhoneCode(@Field("phone")String phone);

    /*
用户注册
*/
    @FormUrlEncoded
    @POST("personal/register")
    Call<General> register(@Field("phone")String phone,@Field("password")String password,@Field("phoneCode")String phoneCode,@Field("idCard")String idCard);
}
