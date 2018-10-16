package com.linli.consumer.mock;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tomoyo on 2016/11/23.
 */

public interface IMocking {

    public static String news_base_url = "http://v.juhe.cn/toutiao/";
    public static String news_key = "bb9c803f0e74793bfcb5a452f9ce9fc9";
    @GET("index?")
    Call<ResponseBody> getNewsRequest(@Query("type") String type, @Query("key") String key);


    String message = "http://118.192.22.177:8085/mall/api/";
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findVoiceListByUserId")
    Call<ResponseBody> getMessage(@Field("numPerPage")String perPage
            ,@Field("pageNum")String pagerNum,@Field("memId")String memId);

    @GET("index?")
    Call<NewsBean> getRequest(@Query("type") String type, @Query("key") String key);



}
