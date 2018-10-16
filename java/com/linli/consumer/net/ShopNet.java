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
import com.linli.consumer.bean.ShopBeanV2;
import com.linli.consumer.bean.ShopExtraInfoBean;
import com.linli.consumer.bean.ShopHonorBean;
import com.linli.consumer.bean.ShopListBean;
import com.linli.consumer.bean.ShopListCategoryBean;
import com.linli.consumer.bean.ShopRegisterBean;
import com.linli.consumer.bean.ShopSortBean;
import com.linli.consumer.bean.UpdateGoodsBean;
import com.linli.consumer.bean.VipUpBean;
import com.linli.consumer.common.HandleSuccess;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by tomoyo on 2016/12/28.
 */

public class ShopNet {


    /**
     * 商城商家
     *
     * (对应于 <商城>主界面)
     *
     * @param type1 1-商城首页店铺2-全部店铺
     * @param categoryId 商城类型id
     * @param distance 距离：首页默认3全部默认10
     * @param longitude 经度
     * @param latitude 纬度
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     *
     * */
    public static void shopRecommend(int type1, long categoryId,
                                     long distance, double longitude,
                                     double latitude, int pageNum,
                                     int numPerPage, final HandleSuccess<ShopListBean> handleSuccess){

        Call<ShopListBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopRecommend(type1,categoryId,distance,longitude,latitude,pageNum,numPerPage);
        call.enqueue(handleSuccess);

    }

    /**
     * 商城商家
     *
     * (对应于 <精选店铺, 全部店铺>主界面)
     *
     * @param categoryId 商城类型id
     * @param distance 距离：首页默认3全部默认10
     * @param longitude 经度
     * @param latitude 纬度
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     *
     * */
    public static void shopRecommendCategory(long categoryId,
                                     long distance, double longitude,
                                     double latitude, int pageNum,
                                     int numPerPage, final HandleSuccess<ShopListCategoryBean> handleSuccess){

        Call<ShopListCategoryBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopRecommendCategory(2,categoryId,distance,longitude,latitude,pageNum,numPerPage);
        call.enqueue(handleSuccess);

    }




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
    public static void goodsSortOfShop(String shopId, final HandleSuccess<ShopSortBean> handleSuccess){

        Call<ShopSortBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsSortOfShop(shopId);
        call.enqueue(handleSuccess);

    }

    /**
     * 根据店铺id以及商家分类id查询商品列表
     *
     * (对应于 <商城> 小分类下的 商品列表)
     *
     * {@link ShopBeanV2} 返回的是一个list
     *
     * 商家id获取及分类{@linkplain #shopRecommend}
     *
     * @param storeId 店铺id
     * @param storeCategoryId 商家分类id
     * @param pageNum 当前页数
     * @param numPerPage 每页显示记录数
     * */
    @Deprecated
    public static void goodsListOfShop(long storeId , long storeCategoryId,
                                       long pageNum,long numPerPage, final HandleSuccess<ShopBeanV2> handleSuccess){

        Call<ShopBeanV2> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsListOfShop(storeId,storeCategoryId,pageNum,numPerPage);
        call.enqueue(handleSuccess);
    }


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
     * */
    public static void goodsListOfShop2(long storeId , long storeCategoryId,
                                       long pageNum,long numPerPage, final HandleSuccess<GoodsDetailListBean> handleSuccess){

        Call<GoodsDetailListBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsListOfShop(storeId,storeCategoryId,pageNum,numPerPage,1);
        call.enqueue(handleSuccess);
    }




    /**
     * 查询商品详情
     * 根据商品id查询商品
     *
     * (对应于商品详情页面)+
     * (对应于dialog中商品的查询展示)
     *
     * {@link com.linli.consumer.bean.ShopBeanV2} 返回的是单个的
     *
     * @param ids 商品id
     * */
    @Deprecated
    public static void goodsDetail(String ids, HandleSuccess<ShopBeanV2> handleSuccess){
        Call<ShopBeanV2> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsDetail(ids);
        call.enqueue(handleSuccess);
    }


    /**
     * 查询商品详情
     *
     * (对应于商品详情界面)
     *
     * @param id 商品id
     *
     * */
    public static void findGoodsById(long id , HandleSuccess<GoodsDetailSingleBean> handleSuccess){
        Call<GoodsDetailSingleBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findGoodsById(id);
        call.enqueue(handleSuccess);
    }


    /**
     * 商品批量查询
     * @param goodDetailUploadBean 上传的实体类
     *                             序列化后的样子:
     * { "goodIds":[314864271465644,914846345978654] }
     *
     * */
    public static void findGoodByIds(GoodDetailUploadBean goodDetailUploadBean,HandleSuccess<GoodsDetailListBean> handleSuccess){
        Call<GoodsDetailListBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findGoodByIds(goodDetailUploadBean);
        call.enqueue(handleSuccess);
    }

    /**
     * 同步本地数据库购物车
     * @param ids 一组规格id，用","分隔
     * */
    public static void findGoodBySpecId(String ids , HandleSuccess<UpdateGoodsBean> handleSuccess){
        Call<UpdateGoodsBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findGoodBySpecId(ids);
        call.enqueue(handleSuccess);
    }




    /**
     * 店铺附加信息查询
     *
     * (这个对应于订单结算时，获取店铺信息<配送时长，配送价格>)
     *
     * @param shopId 店铺id
     * */
    public static void shopExInfo(long shopId,final HandleSuccess<ShopExtraInfoBean> handleSuccess){
        Call<ShopExtraInfoBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopExInfo(shopId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据店铺ID查询店铺信息
     *
     * (查询店铺信息，对于于下订单获取  区域码)
     *
     * @param storeId 店铺id
     * */
    public static void shopInfo(long storeId,final HandleSuccess<GoodsShopInfoBean> handleSuccess){
        Call<GoodsShopInfoBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopInfo(storeId);
        call.enqueue(handleSuccess);
    }


    /**
     * 查询商誉计划
     *
     * @param storeId 店铺id
     *
     * */
    public static void findStoreHonor(long storeId,final HandleSuccess<ShopHonorBean> handleSuccess){
        Call<ShopHonorBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findStoreHonor(storeId);
        call.enqueue(handleSuccess);
    }





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
    public static void shopOrder(long companyMemberId, Map<String ,Object> order, String goodsList,
                                 HandleSuccess<FoodOrderPayBean> handleSuccess){
        Call<FoodOrderPayBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopOrder(companyMemberId,order,goodsList);
        call.enqueue(handleSuccess);
    }

    /********** 店铺收藏*************/

    /**
     * 店铺收藏查询
     *
     * @param memId 用户id
     * @param storeId 店铺id
     * @param type 1-美食  2-商城
     * */
    public static void shopCollectionQuery(String memId, long storeId, int type,HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopCollectionQuery(memId,storeId,type);
        call.enqueue(handleSuccess);
    }

    /**
     * 商城店铺收藏添加
     *
     *
     * @param memberId 用户id
     * @param storeId 店铺id
     *
     * */
    public static void shopCollectionAdd(String memberId,long storeId,HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopCollectionAdd(memberId,storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 商城店铺收藏删除
     *
     *
     * @param memberId 用户id
     * @param storeId 店铺id
     *
     * */
    public static void shopCollectionDel(String memberId,long storeId,HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .shopCollectionDel(storeId,memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 美食店铺收藏添加
     *
     *
     * @param memberId 用户id
     * @param storeId 店铺id
     *
     * */
    public static void foodCollectionAdd(long storeId,String memberId,HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .foodCollectionAdd(storeId,memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 美食店铺收藏删除
     *
     *
     * @param memberId 用户id
     * @param storeId 店铺id
     *
     * */
    public static void foodCollectionDel(long storeId,String memberId,HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .foodCollectionDel(storeId,memberId);
        call.enqueue(handleSuccess);
    }



    /****************点赞收藏******************/


    /**
     * 根据店铺id查询店铺 <点赞数>
     *
     * @param storeId 店铺id
     * @param type 类型   1-美食 2-商城
     *
     * */
    public static void findCommentSupport(long storeId,int type,
                                          HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findCommentSupport(storeId,type);
        call.enqueue(handleSuccess);
    }



    /**
     * 根据店铺id查询店铺 <关注数>
     *
     * @param storeId 店铺id
     *
     * */
    public static void findmenStoreNum(long storeId,HandleSuccess<CollBean> handleSuccess){
        Call<CollBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findmenStoreNum(storeId);
        call.enqueue(handleSuccess);
    }


    /**
     * 收货地址管理接口(查默认收货地址)
     *
     * (对应于递交订单中地址选择界面)
     *
     * @param memberId 用户id
     * */
    public static void changeAddress(String memberId, HandleSuccess<AddressInfoBean> handleSuccess){
        Call<AddressInfoBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .changeAddress(memberId);
        call.enqueue(handleSuccess);
    }



    /************************会员**************************/

    /**
     * 查询商家会员
     *
     * (用于添加会员接口)
     *
     * @param storeId 店铺id
     * @param memberId 用户id
     *
     * */
    public static void queryStoreMember(String memberId, long storeId, HandleSuccess<ConcernedList> handleSuccess){
        Call<ConcernedList> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .queryStoreMember(memberId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 更新商家会员
     *
     * (用于添加会员接口)
     *
     * @param id 关注ID
     * @param status 状态码 状态0成为，1取消
     *
     * */
    public static void updateStoreMember(long id,int status,HandleSuccess<VipUpBean>
                                         handleSuccess){
        Call<VipUpBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .updateStoreMember(id,status);
        call.enqueue(handleSuccess);
    }

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
    public static void leaguerFor(long storeId,String memberId,
                                  String name,long region_id,
                                  String phone,String headpath,String birthday,int sex,HandleSuccess<LeaguerAdd> handleSuccess){
        Call<LeaguerAdd> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .leaguerFor(storeId, memberId, name, region_id, phone,headpath,birthday,sex,"未填写");
        call.enqueue(handleSuccess);
    }


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
    public static void goodsCollectionQuery(String memId,int pageNum, int numPerPage
            ,HandleSuccess<CollectionBean> handleSuccess){
        Call<CollectionBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsCollectionQuery(memId, pageNum, numPerPage);
        call.enqueue(handleSuccess);

    }

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
    public static void goodsCollectionAdd(long goodsSpecId,String memberId,HandleSuccess<CollectionBean> handleSuccess){
        Call<CollectionBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsCollectionAdd(goodsSpecId, memberId);
        call.enqueue(handleSuccess);
    }

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
    public static void goodsCollectionDel(long id, HandleSuccess<CollectionBean> handleSuccess){
        Call<CollectionBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .goodsCollectionDel(id);
        call.enqueue(handleSuccess);
    }

    /**
     * 系统平台公共
     * (对应于商城美食主页 轮播字)
     *
     * */
    public static void findSysPublic(HandleSuccess<BroadCastBean> handleSuccess){
        Call<BroadCastBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findSysPublic("");
        call.enqueue(handleSuccess);
    }
    /**
     * 查询店铺注册表信息 此处用于查各种店铺的营业执照照片
     *
     * @param phone 商家手机号
     *
     * */
    public static void findBusinessLicenses(String phone,HandleSuccess<ShopRegisterBean> handleSuccess){
        Call<ShopRegisterBean> call = NetUtil.createShopApi(IShopApi.SHOP_URL)
                .findBusinessLicenses(phone);
        call.enqueue(handleSuccess);
    }
}
