package com.linli.consumer.net;

import com.linli.consumer.bean.FoodExInfoBean;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.FoodTitleTenantBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tomoyo on 2017/1/3.
 */

public interface IFoodsApiTenant {

    //根地址
    public static final String FOOD_Tenant_URL = NetUtil.BASE_URL_V2+"food/";


    /**
     * 根据店铺id查询菜谱舌签
     *
     * (用于美食 进入店铺 店铺的title)
     *
     * {@link com.linli.consumer.bean.FoodTitleTenantBean}
     *
     * @param storeId 店铺id
     * */
    @FormUrlEncoded
    @POST("findMenuType")
    Call<FoodTitleTenantBean> foodSortById(@Field("storeId")long storeId);


    /**
     * 查询菜谱信息
     *
     * (用于美食 进入店铺 店铺的title，根据title获取到的list)
     *
     * {@link com.linli.consumer.bean.FoodListBean}
     *
     * @param bussId 店铺id
     * @param belongType 标签id
     * @param status 状态 0
     * @param pageNum 分页
     * @param numPerPage 每页
     * */
    @FormUrlEncoded
    @POST("findCookBook")
    Call<FoodListBean> foodListOfSHop(@Field("bussId")long bussId,@Field("belongtype")long belongType,
                                      @Field("status")String status,@Field("pageNum")int pageNum,
                                      @Field("numPerPage")int numPerPage);

    /**
     * 根据店铺id查询经营设置
     *
     * (对应于订单部分，美食)
     *
     * @param storeId 店铺id
     * */
    @FormUrlEncoded
    @POST("findStoreOperate")
    Call<FoodExInfoBean> foodExInfo(@Field("storeId")long storeId);
}
