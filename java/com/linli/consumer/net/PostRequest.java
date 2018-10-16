package com.linli.consumer.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hasee on 2016/12/1.
 */

public interface PostRequest {
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findVoiceListByUserId")
    Call<ResponseBody> findVoiceListByUserId(@Field("numPerPage")Integer numPerPage
            , @Field("pageNum")Integer pageNum, @Field("memId")Long memId);
    /*
    获取验证码
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/phoneCode")
    Call<ResponseBody> phoneCode(@Field("phone")String phone);
}
