package com.linli.consumer.net;

import com.linli.consumer.bean.FindFoodBean;
import com.linli.consumer.bean.FoodMainCateBean;
import com.linli.consumer.bean.FoodOrderPayBean;
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.bean.FoodRecommendBean;
import com.linli.consumer.bean.FoodShopExInfoBean;
import com.linli.consumer.bean.FoodShopInfoBean;
import com.linli.consumer.bean.HdFoodOrderGoods;
import com.linli.consumer.bean.OrderServiceList;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.bean.ServiceOrderBean;
import com.linli.consumer.bean.ServiceShopsBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.ServicesBean;
import com.linli.consumer.bean.UpdateFoodBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tomoyo on 2016/12/19.
 *
 * 美食的api
 */

public interface IFoodsApi {

    //根地址
    public static final String FOOD_URL = NetUtil.BASE_URL_V2+"user/food/";
    public static final String SERVICE_URL = NetUtil.BASE_URL_V2+"mall/serve/";



    /**
     * 餐饮附近推荐商家及菜品信息
     *
     * (对应于 <餐饮>主界面的数据获取)
     *
     * {@link com.linli.consumer.bean.FoodRecommendBean}
     *
     * @param numPerPage 每页几行
     * @param pageNum 第几页
     * @param distance  距离
     * @param longitude 经度
     * @param latitude 维度
     * @param status 随便传
     * */
    @GET("findNearByStoreAndBook")
    Call<FoodRecommendBean> foodAndShopRecommend(@Query("numPerPage")String numPerPage, @Query("pageNum")int pageNum ,
                                                 @Query("distance")String distance, @Query("longitude")double longitude,
                                                 @Query("latitude")double latitude, @Query("status")String status);



    /**
     * 根据主营类型查店铺
     *
     * (对应于  找美食)
     *
     * @param opeType 主营类型 不传为显示全部，1,2,3,4
     * @param pageNum 第几页
     * @param numPerPage 每页几行
     * @param distance  距离
     * @param longitude 经度
     * @param latitude 维度
     *
     * */
    @GET("findStoreByOpeType")
    Call<ResponseBody> foodFindByType(@Query("opeType")String opeType,@Query("pageNum")int pageNum,
                                      @Query("numPerPage")int numPerPage,@Query("distance")double distance,
                                      @Query("longitude")double longitude,@Query("latitude")double latitude);




    /**
     * 餐饮下订单
     *
     * (对应于饮食订单界面)
     *
     * @param addressId 收货地址表主键
     * @param hdFoodOrder 订单表信息(HdFoodOrder)，用FieldMap传
     * @param hdFoodOrderGoodsList 订单商品List<HdFoodOrderGoods>,用fastJson转化成json
     *
     * */
    @FormUrlEncoded
    @POST("addFoodOrder")
    Call<FoodOrderPayBean> foodOrder(@Field("addressId") long addressId, @FieldMap Map<String,Object > hdFoodOrder,
                                     @Field("jsonData") String hdFoodOrderGoodsList,@Field("apptime")String apptime);

    @FormUrlEncoded
    @POST("addFoodOrder")
    Call<FoodOrderPayBean> foodOrder1(@Field("addressId") long addressId, @FieldMap Map<String,Object > hdFoodOrder,
                                     @Field("hhh") List<HdFoodOrderGoods> hdFoodOrderGoodsList);



    /**
     * 批量查菜品详情
     *
     *
     * (main:这个用来同步数据库!)
     *
     * @param ids 菜品主键,把foodsId 写成字符串出入，用","分隔开,末尾去掉","
     *
     * */
    @GET("findCookbookDetailList")
    Call<UpdateFoodBean> foodDetail(@Query("ids") String ids);


    /**
     * 美食店铺附加信息查询
     *
     * (这个对应于订单结算时，获取店铺信息<配送时长，配送价格，打包费用>)
     *
     * 区别于商城的附加信息，包含打包费
     *
     * @param shopId 店铺id
     * */
    @FormUrlEncoded
    @POST("store/mall/shopCenter/queryHdMallStoreOperate")
    Call<FoodShopExInfoBean> foodShopExInfo(@Field("shopId")long shopId);


    /**
     * 根据店铺ID查询餐饮店铺信息
     *
     * (查询店铺信息，对于于下订单获取  区域码 )
     *
     * @param storeId 店铺ID
     * */
    @FormUrlEncoded
    @POST("findFoodShopByshopId")
    Call<FoodShopInfoBean> foodShopInfo(@Field("storeId")long storeId);


    /**
     * 根据主营类型查店铺
     *
     * (对应于  找美食，美食更多)
     * @param opeType 主营类型
     * @param distance 距离
     * @param longitude 经度
     * @param latitude 纬度
     * @param pageNum 第几页
     * @param numPerPage 每页几行数据
     *
     * */
    @FormUrlEncoded
    @POST("findStoreByOpeType")
    Call<FindFoodBean> findStoreByOpeType(@Field("opeType")String opeType,@Field("distance")double distance,
                                          @Field("longitude")double longitude,@Field("latitude")double latitude,
                                          @Field("pageNum")int pageNum,@Field("numPerPage")int numPerPage);

    /**
     * 食靠谱接口
     *
     * (对应于食靠谱界面)
     *
     * @param cuisine 类别，从0到10,0为全部
     * @param pageNum 第几页
     * @param numPerPage 每页几行数据
     * @param name 名字，随便传
     * */
    @FormUrlEncoded
    @POST("findFoodCloud")
    Call<FoodRecipeBean> findFoodCloud(@Field("cuisine")String cuisine,@Field("pageNum")int pageNum,
                                       @Field("numPerPage")int numPerPage,@Field("name")String name);

    /**
     * 关于服务的接口暂时写在这里 因为跟订餐很像 只有展示以后可能有预订 到店消费等
     * （以下是关于服务模块的接口）
     * （以下是关于服务模块的接口）
     * （以下是关于服务模块的接口）
     * （以下是关于服务模块的接口）
     * （以下是关于服务模块的接口）
     */
    /**
     * 查询服务首页推荐店铺信息
     *
     * @param distance 距离
     * @param longitude 经度
     * @param latitude 纬度
     *
     * */
    @FormUrlEncoded
    @POST("hdServeStore/findStores")
    Call<ServicesBean> findServiceStoresRecByLocation(@Field("distance")int distance,
                                                      @Field("longitude")double longitude, @Field("latitude")double latitude,
                                                      @Field("pageNum")int pageNum, @Field("numPerPage")int numPerPage, @Field("type1")int type1);
    /**
     * 查询服务商家服务项目
     *
     * @param storeId 商铺ID
     * @param pageNum 页数
     * @param numPerPage 每页数量
     *
     * */
    @FormUrlEncoded
    @POST("hdServeGoods/findListByShopId")
    Call<ServiceGoodBean> findServiceStoresGoodsByStoreId(@Field("storeId")long storeId, @Field("pageNum")int pageNum, @Field("numPerPage")int numPerPage);

    /**
     * 查询服务商铺商铺信息
     *
     * @param storeId 商铺ID
     *
     * */
    @FormUrlEncoded
    @POST("hdServeStore/findStoreById")
    Call<ServiceStoreBean> findServiceStoresInfos(@Field("storeId")long storeId);
    /**
     * 根据分类查询服务店铺信息
     *
     * @param distance 距离
     * @param longitude 经度
     * @param latitude 纬度
     *
     * */
    @FormUrlEncoded
    @POST("hdServeStore/findStores")
    Call<ServiceShopsBean> findServiceStoresByCate(@Field("distance")int distance,
                                                   @Field("longitude")double longitude, @Field("latitude")double latitude,
                                                   @Field("pageNum")int pageNum, @Field("numPerPage")int numPerPage, @Field("type1")int type1,
                                                   @Field("categoryId")long categoryId);

    /**
     * 预约服务项目
     *
     * @param goodsid 服务项ID
     * @param storeid 店铺ID
     * @param linkname 联系人信息
     *
     * */
    @FormUrlEncoded
    @POST("hdServeStore/saveHdServeOrder")
    Call<ServiceOrderBean> orderService(@Field("goodsid")Long goodsid, @Field("storeid")Long storeid, @Field("linkname")String linkname,
                                        @Field("linkphone")String linkphone, @Field("address")String address, @Field("appointment")String appointment,
                                        @Field("memberid")String memberid, @Field("goodsamount")BigDecimal goodsamount,
                                        @Field("orderamount")BigDecimal orderamount, @Field("favorableamount")BigDecimal favorableamount,
                                        @Field("couponid")Long couponid);
    /**
     * 查询服务预约订单列表
     *
     * @param pageNum 页码
     * @param numPerPage 每页数量
     *
     * */
    @FormUrlEncoded
    @POST("hdServeStore/findListByMemId")
    Call<OrderServiceList> findorderServices(@Field("memberid")String memberid,
                                             @Field("pageNum")int pageNum, @Field("numPerPage")int numPerPage);
    /**
     * 查询美食/各种店铺的主营类型  当前只有查美食主类型用了这个接口  此接口写到了服务模块只是一个巧合无任何关联
     *
     * @param parentId 父类别ID
     *
     * */
    @FormUrlEncoded
    @POST("hdServeStore/findCategoryList")
    Call<FoodMainCateBean> findMainCatesByParentId(@Field("parentId")long parentId);

    /**
     * 搜索服务商品（服务项）
     *
     * @param goodsName 关键词
     *
     * */
    @FormUrlEncoded
    @POST("hdServeGoods/findGoodsByName")
    Call<ServiceGoodBean> findServiceProducts(@Field("goodsName")String goodsName,@Field("distance")int distance,@Field("longitude")double longitude,
                                               @Field("latitude")double latitude,@Field("pageNum")int pageNum,@Field("numPerPage")int numPerPage);
}
