package com.linli.consumer.net;

import com.linli.consumer.bean.AddressInfoBean;
import com.linli.consumer.bean.BroadCastBean;
import com.linli.consumer.bean.CollBean;
import com.linli.consumer.bean.CollectionBean;
import com.linli.consumer.bean.ConcernedList;
import com.linli.consumer.bean.FoodOrderPayBean;
import com.linli.consumer.bean.GoodDetailUploadBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.bean.GoodsDetailSingleBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.bean.LeaguerAdd;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.bean.ShopBeanV2;
import com.linli.consumer.bean.ShopExtraInfoBean;
import com.linli.consumer.bean.ShopHonorBean;
import com.linli.consumer.bean.ShopListBean;
import com.linli.consumer.bean.ShopListCategoryBean;
import com.linli.consumer.bean.ShopRegisterBean;
import com.linli.consumer.bean.ShopSortBean;
import com.linli.consumer.bean.UpdateGoodsBean;
import com.linli.consumer.bean.VipUpBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tomoyo on 2016/12/27.
 *
 * 商城的api
 */

public interface IShopApi {


    //根地址
    public static final String SHOP_URL = NetUtil.BASE_URL_V2;

    /**
     * 商城商家
     *
     * (对应于 <商城>主界面)
     *
     * @param type1 1-商城首页店铺2-全部店铺      这里不再使用type1字段分类，响应的实体类不同
     * @param categoryId 商城类型id
     * @param distance 距离：首页默认3全部默认10
     * @param longitude 经度
     * @param latitude 纬度
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     *
     * 有规格  价格:{@link MallGoodsVo#getPlatformPrice()}
     * 无规格  价格:{@link MallGoodsVo#getMallGoods#nospecPlatformPrice}
     *
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findStores")
    Call<ShopListBean> shopRecommend(@Field("type1")int type1, @Field("categoryId")long categoryId,
                                     @Field("distance")long distance, @Field("longitude")double longitude,
                                     @Field("latitude")double latitude, @Field("pageNum")int pageNum,
                                     @Field("numPerPage")int numPerPage);


    /**
     * 商城商家
     *
     * (对应于 <精选店铺, 全部店铺> 界面)
     *
     * @param type1 1-商城首页店铺2-全部店铺
     * @param categoryId 商城类型id
     * @param distance 距离：首页默认3全部默认10
     * @param longitude 经度
     * @param latitude 纬度
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     *
     * 有规格  价格:{@link MallGoodsVo#getPlatformPrice()}
     * 无规格  价格:{@link MallGoodsVo#getMallGoods#nospecPlatformPrice}
     *
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findStores")
    Call<ShopListCategoryBean> shopRecommendCategory(@Field("type1")int type1, @Field("categoryId")long categoryId,
                                                     @Field("distance")long distance, @Field("longitude")double longitude,
                                                     @Field("latitude")double latitude, @Field("pageNum")int pageNum,
                                                     @Field("numPerPage")int numPerPage);



    /**
     * 根据商家ID查询商家分类标题
     *
     * (对应于 <商城>商店内商品的条目，小标题  title)
     *
     * 商家id获取 {@linkplain #shopRecommend}
     *
     * @param shopId 商家id
     *
     * */
    @GET("store/mall/shopCenter/queryShopGoodsCategory")
    Call<ShopSortBean> goodsSortOfShop(@Query("shopId")String shopId);


    /**
     * 根据店铺id以及商家分类id查询商品列表
     *
     * (对应于 <商城> 小分类下的 商品列表)
     *
     * {@link ShopBeanV2} 返回的是一个list
     * 有规格  价格:{@link MallGoodsVo#getPlatformPrice()}
     * 无规格  价格:{@link MallGoodsVo#getMallGoods#nospecPlatformPrice}
     *
     * 商家id获取及分类{@linkplain #shopRecommend}
     *
     * @param storeId 店铺id
     * @param storeCategoryId 商家分类id
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     * */
    @Deprecated
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findGoodsList")
    Call<ShopBeanV2> goodsListOfShop(@Field("storeId")long storeId , @Field("storeCategoryId")long storeCategoryId,
                                     @Field("pageNum")long pageNum, @Field("numPerPage")long numPerPage);


    /**
     * 根据店铺id以及商家分类id查询商品列表
     *
     * (对应于 <商城> 小分类下的 商品列表)
     *
     * 商家id获取及分类{@linkplain #shopRecommend}
     *
     * @param storeId 店铺id
     * @param storeCategoryId 商家分类id
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     * @param isShow 1
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findGoodsList2")
    Call<GoodsDetailListBean> goodsListOfShop(@Field("storeId")long storeId , @Field("storeCategoryId")long storeCategoryId,
                                              @Field("pageNum")long pageNum, @Field("numPerPage")long numPerPage,
                                              @Field("isShow")int isShow);




    /**
     * 查询商品详情
     * 根据商品id查询商品
     *
     * (对应于商品详情页面)
     *
     * {@link com.linli.consumer.bean.ShopBeanV2} 返回的是单个的
     * 有规格  价格:{@link MallGoodsVo#getVoList#MallGoodsSpecVo#MallGoodsSpec#platformPrice}
     * 无规格  价格:{@link MallGoodsVo#getMallGoods#nospecPlatformPrice}
     *
     * @param ids 商品id
     * */
    //@FormUrlEncoded
    @Deprecated
    @GET("mall/mall/hdMall/findGoodsDetail")
    Call<ShopBeanV2> goodsDetail(@Query("ids" ) String ids);


    /**
     * 查询商品详情
     *
     * (对应于商品详情界面)
     *
     * @param id 商品id
     *
     * */
    @FormUrlEncoded
    @POST("goods/findGoodsById")
    Call<GoodsDetailSingleBean> findGoodsById(@Field("id")long id);

    /**
     * 商品批量查询
     * @param goodDetailUploadBean 上传的实体类
     *                             序列化后的样子:
     * { "goodIds":[314864271465644,914846345978654] }
     *
     * */
    @POST("goods/findGoodByIds")
    Call<GoodsDetailListBean> findGoodByIds(@Body GoodDetailUploadBean goodDetailUploadBean);


    /**
     * 同步本地数据库购物车
     * @param ids 一组规格id，用","分隔
     * */
    @FormUrlEncoded
    @POST("goods/findGoodBySpecId")
    Call<UpdateGoodsBean> findGoodBySpecId(@Field("ids")String ids);


    /**
     * 根据分类父ID 查询分类
     *
     * (这个对应于全部店铺的分类查询，查询商城的分类)
     * @param parentId 这个数据固定为 214741996370919
     *
     * {@link com.linli.consumer.bean.ShopTitleBean}
     *
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findMallCategory")
    Call<ResponseBody> goodsBigTitle(@Field("parentId")long parentId);


    /**
     * 店铺附加信息查询
     *
     * (这个对应于订单结算时，获取店铺信息<配送时长，配送价格>)
     *
     * @param shopId 店铺id
     * */
    @FormUrlEncoded
    @POST("store/mall/shopCenter/queryHdMallStoreOperate")
    Call<ShopExtraInfoBean> shopExInfo(@Field("shopId")long shopId);


    /**
     * 根据店铺ID查询店铺信息
     *
     * (查询店铺信息，对于于下订单获取  区域码)
     *
     * @param storeId 店铺id
     * */
    @FormUrlEncoded
    @POST("store/mall/shopCenter/findShopByshopId")
    Call<GoodsShopInfoBean> shopInfo(@Field("storeId")long storeId);


    /**
     * 查询商誉计划
     *
     * @param storeId 店铺id
     *
     * */
    @FormUrlEncoded
    @POST("food/findStoreHonor")
    Call<ShopHonorBean> findStoreHonor(@Field("storeId")long storeId);


    /**
     * 用户下单
     *
     * (对应商品下单)
     *
     *@param companyMemberId 店铺管理者id
     *@param order 实体 HdMallOrder order.orderSn
     *@param goodsList 实体List<HdMallOrderGoods>
     *
     * */
    @FormUrlEncoded
    @POST("order/createorders")
    Call<FoodOrderPayBean> shopOrder(@Field("companyMemberId")long companyMemberId,
                                     @FieldMap Map<String,Object> order,
                                     @Field("jsonData")String goodsList);




    /***************** 收藏店铺 ******************/

    /**
     * 收藏商品查询接口
     *
     * (对应于商品收藏，在进行添加，删除，展示前，先进行查询)
     *
     * @param memId 用户id
     * @param pageNum 当前页数
     * @param numPerPage 每页记录数
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collectionGoods")
    Call<CollectionBean> goodsCollectionQuery(@Field("memId")String memId, @Field("pageNum")int pageNum,
                                              @Field("numPerPage")int numPerPage);

    /**
     * 商品收藏添加
     *
     * 添加前先查询{@linkplain #goodsCollectionQuery}
     *
     * (对应于商品收藏添加)
     *
     * @param goodsSpecId 商品规格id
     * @param memberId 用户id
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collGoodAdd")
    Call<CollectionBean> goodsCollectionAdd(@Field("goodsSpecId")long goodsSpecId,@Field("memberId")String memberId);

    /**
     * 商品收藏删除
     *
     * 删除前先查询{@linkplain #goodsCollectionQuery}
     *
     * (对应于商品收藏删除，只要在添加状态才能删除，传入唯一主键 id 进行删除
     *  在数据库中有一个表专门存放用户的收藏信息，出入收藏主键，直接删除信息)
     *
     * @param id 商品收藏唯一主键
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collGoodDel")
    Call<CollectionBean> goodsCollectionDel(@Field("id")long id);



    /**
     * 店铺收藏查询
     *
     * @param memId 用户id
     * @param storeId 店铺id
     * @param type 1-美食  2-商城
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findCollect")
    Call<CollBean> shopCollectionQuery(@Field("memId")String memId, @Field("storeId")long storeId,
                                       @Field("type")int type);

    /**
     * 商城店铺收藏添加
     *
     * 添加前先查询{@linkplain #shopCollectionQuery}
     *
     * @param memberId 用户id
     * @param storeId 店铺id
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collShopAdd")
    Call<CollBean> shopCollectionAdd(@Field("memberId")String memberId,@Field("storeId")long storeId);


    /**
     * 商城店铺收藏删除
     *
     * 删除前先查询{@linkplain #shopCollectionQuery}
     *
     * (对应于店铺收藏删除，只要在添加状态才能删除，传入唯一主键 id 进行删除
     *  在数据库中有一个表专门存放用户的收藏信息，出入收藏主键，直接删除信息)
     *
     * @param storeId 店铺收藏id
     * @param memberId 用户id
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collShopDel")
    Call<CollBean> shopCollectionDel(@Field("storeId")long storeId,@Field("memberId")String memberId);


    /**
     * 美食店铺收藏添加
     *
     * @param storeId 店铺id
     * @param memberId 用户id
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collFoodShopAdd")
    Call<CollBean> foodCollectionAdd(@Field("storeId")long storeId,@Field("memberId")String memberId);


    /**
     * 美食店铺收藏删除
     *
     * @param storeId 店铺id
     * @param memberId 用户id
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/collFoodShopDel")
    Call<CollBean> foodCollectionDel(@Field("storeId")long storeId,@Field("memberId")String memberId);



    /********************** 点赞 & 关注　*******************/

    /**
     * 根据店铺id查询店铺点赞数
     *
     * @param storeId 店铺id
     * @param type 类型   1-美食 2-商城
     *
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findCommentSupport")
    Call<CollBean> findCommentSupport(@Field("storeId")long storeId,@Field("type")int type);

    /**
     * 根据店铺id查询店铺关注数
     *
     * @param storeId 店铺id
     *
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findmenStoreNum")
    Call<CollBean> findmenStoreNum(@Field("storeId")long storeId);







    /**
     * 收货地址管理接口(查默认收货地址)
     *
     * (对应于递交订单中地址选择界面)
     *
     * @param memberId 用户id
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectUserDistributAddress")
    Call<AddressInfoBean> changeAddress(@Field("memberId")String memberId);




    /*********************** 会员 ***********************/

    /**
     * 查询商家会员
     *
     * (用于添加会员接口)
     *
     * @param storeId 店铺id
     * @param memberId 用户id
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguer")
    Call<ConcernedList> queryStoreMember(@Field("memberId")String memberId, @Field("storeId")long storeId);

    /**
     * 更新商家会员
     *
     * (用于添加会员接口)
     *
     * @param id 关注ID
     * @param status 状态码    状态0成为，1取消
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguerUpdate")
    Call<VipUpBean> updateStoreMember(@Field("id")long id, @Field("status")int status);


    /**
     * 关注成为商家会员
     *
     * (用于添加会员接口)
     *
     * @param storeId 店铺id
     * @param memberId 用户id
     * @param name 用户名
     * @param region_id 区域id(从店铺信息就能拿到)
     * @param phone 电话号
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguerFor")
    Call<LeaguerAdd> leaguerFor(@Field("storeId")long storeId, @Field("memberId")String memberId,
                                @Field("name")String name, @Field("region_id")long region_id,
                                @Field("phone")String phone,@Field("headpath")String headpath,
                                @Field("birthday")String birthday,@Field("sex")int sex,@Field("address")String address);

    /**
     * 系统平台公告
     * (对应于商城美食主页 轮播字)
     *
     * */
    @FormUrlEncoded
    @POST("member/mall/userCenter/findSysPublic")
    Call<BroadCastBean> findSysPublic(@Field("All")String all);

    /**
     * 查询店铺注册表信息 此处用于查各种店铺的营业执照照片
     *
     * @param phone 店铺手机号
     *
     * */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findRegisterByStoreId")
    Call<ShopRegisterBean> findBusinessLicenses(@Field("phone")String phone);

}
