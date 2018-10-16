package com.linli.consumer.net;

import android.util.Log;

import com.linli.consumer.bean.FindFoodBean;
import com.linli.consumer.bean.FoodExInfoBean;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.FoodMainCateBean;
import com.linli.consumer.bean.FoodOrderPayBean;
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.bean.FoodRecommendBean;
import com.linli.consumer.bean.FoodShopInfoBean;
import com.linli.consumer.bean.FoodTitleTenantBean;
import com.linli.consumer.bean.HdFoodOrderGoods;
import com.linli.consumer.bean.OrderServiceList;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.bean.ServiceOrderBean;
import com.linli.consumer.bean.ServiceShopsBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.ServicesBean;
import com.linli.consumer.bean.UpdateFoodBean;
import com.linli.consumer.common.HandleSuccess;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tomoyo on 2016/12/19.
 */

public class FoodNet {


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
     * */
    public static void getFoodAndShopRecommend(String numPerPage, int pageNum
            , String distance, double longitude, double latitude, final HandleSuccess<FoodRecommendBean> handleSuccess){
        Call<FoodRecommendBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .foodAndShopRecommend(numPerPage,pageNum,distance,longitude,latitude,"0");
        call.enqueue(handleSuccess);
/*
        call.enqueue(new Callback<FoodRecommendBean>() {
            @Override
            public void onResponse(Call<FoodRecommendBean> call, Response<FoodRecommendBean> response) {
                //Log.i("WATER",response.toString());

                //FoodRecommendBean bean = JSON.parseObject(response.toString(),FoodRecommendBean.class);
                Log.i("WATER",response.body().getData().get(0).getHdFoodStore().getName());
            }

            @Override
            public void onFailure(Call<FoodRecommendBean> call, Throwable t) {
                Log.i("WATER_onFailure",t.toString());
            }
        });
*/
    }

    /**
     * 根据店铺id查询菜谱舌签
     *
     * (用于美食 进入店铺 店铺的title)
     *
     * {@link com.linli.consumer.bean.FoodTitleTenantBean}
     *
     * @param storeId 店铺id
     * */
    public static void foodTitleById(long storeId,final HandleSuccess<FoodTitleTenantBean> handleSuccess){
        Call<FoodTitleTenantBean> call = NetUtil.createFoodTenantApi(IFoodsApiTenant.FOOD_Tenant_URL)
                .foodSortById(storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询菜谱信息
     *
     * (用于美食 进入店铺 店铺的title，根据title获取到的list)
     *
     * {@link com.linli.consumer.bean.FoodListBean}
     *
     * @param bussId 店铺id
     * @param belongType 标签id
     * @param pageNum 分页
     * @param numPerPage 每页
     * */
    public static void foodListOfSHop(long bussId,long belongType, int pageNum,
                                      int numPerPage,final HandleSuccess<FoodListBean> handleSuccess){
        Call<FoodListBean> call = NetUtil.createFoodTenantApi(IFoodsApiTenant.FOOD_Tenant_URL)
                .foodListOfSHop(bussId,belongType,"0",pageNum,numPerPage);
        call.enqueue(handleSuccess);
    }

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
    public static void foodOrder(long addressId, Map<String,Object> hdFoodOrder,
                                 String hdFoodOrderGoodsList,String apptime, final HandleSuccess<FoodOrderPayBean> handleSuccess){
        Call<FoodOrderPayBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .foodOrder(addressId,hdFoodOrder,hdFoodOrderGoodsList,apptime);
        call.enqueue(handleSuccess);
    }

    public static void foodOrder1(long addressId, Map<String,Object> hdFoodOrder,
                                  List<HdFoodOrderGoods> hdFoodOrderGoodsList, final HandleSuccess<FoodOrderPayBean> handleSuccess){
        Call<FoodOrderPayBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .foodOrder1(addressId,hdFoodOrder,hdFoodOrderGoodsList);
        call.enqueue(new Callback<FoodOrderPayBean>() {
            @Override
            public void onResponse(Call<FoodOrderPayBean> call, Response<FoodOrderPayBean> response) {
                Log.i("WATER","foodOrder1_response"+response+"");
            }

            @Override
            public void onFailure(Call<FoodOrderPayBean> call, Throwable t) {
                Log.i("WATER","foodOrder1_onFailure"+t.toString());
            }
        });
    }


    /**
     * 批量查菜品详情
     *
     *
     * @param ids 菜品主键,把foodsId 写成字符串出入，用","分隔开,末尾去掉","
     *
     * */
    public static void queryFoodDetailByIds(String ids, final HandleSuccess<UpdateFoodBean> handleSuccess){
        Call<UpdateFoodBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .foodDetail(ids);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据店铺id查询经营设置
     *
     * (对应于订单部分，美食)
     *
     * @param storeId 店铺id
     * */
    public static void foodExInfo(long storeId,final HandleSuccess<FoodExInfoBean> handleSuccess){
        Call<FoodExInfoBean> call = NetUtil.createFoodTenantApi(IFoodsApiTenant.FOOD_Tenant_URL)
                .foodExInfo(storeId);
        call.enqueue(handleSuccess);
    }


    /**
     * 根据店铺ID查询餐饮店铺信息
     *
     * (查询店铺信息，对于于下订单获取  区域码 )
     *
     * @param storeId 店铺ID
     * */
    public static void foodShopInfo(long storeId,final HandleSuccess<FoodShopInfoBean> handleSuccess){
        Call<FoodShopInfoBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .foodShopInfo(storeId);
        call.enqueue(handleSuccess);
    }

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
    public static void findStoreByOpeType(String opeType, double distance,
                                          double longitude, double latitude,
                                          int pageNum, int numPerPage, HandleSuccess<FindFoodBean> handleSuccess){
        Call<FindFoodBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .findStoreByOpeType(opeType, distance, longitude, latitude, pageNum, numPerPage);
        call.enqueue(handleSuccess);
    }

    /**
     * 食靠谱接口
     *
     * (对应于食靠谱界面)
     *
     * @param cuisine 类别，从0到10,0为全部
     * @param pageNum 第几页
     * @param numPerPage 每页几行数据
     * */
    public static void findFoodCloud(String cuisine,int pageNum,
                                     int numPerPage,String name,HandleSuccess<FoodRecipeBean> handleSuccess){
        Call<FoodRecipeBean> call = NetUtil.createFoodApi(IFoodsApi.FOOD_URL)
                .findFoodCloud(cuisine,pageNum,numPerPage,name);
        call.enqueue(handleSuccess);

    }

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
    public static void findServiceStoresRecByLocation(int distance, double longitude, double latitude,int pageNum,int numPerPage,int type1,
                                                      HandleSuccess<ServicesBean> handleSuccess){
        Call<ServicesBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findServiceStoresRecByLocation(distance, longitude, latitude,pageNum,numPerPage,type1);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询服务商铺服务项目
     *
     * @param storeId 商铺ID
     * @param pageNum 页数
     * @param numPerPage 每页数量
     *
     * */
    public static void findServiceStoresGoodsByStoreId(long storeId,int pageNum,int numPerPage,HandleSuccess<ServiceGoodBean> handleSuccess){
        Call<ServiceGoodBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findServiceStoresGoodsByStoreId(storeId, pageNum, numPerPage);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询服务商铺商铺信息
     *
     * @param storeId 商铺ID
     *
     * */
    public static void findServiceStoresInfos(long storeId,HandleSuccess<ServiceStoreBean> handleSuccess){
        Call<ServiceStoreBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findServiceStoresInfos(storeId);
        call.enqueue(handleSuccess);
    }
    /**
     * 根据服务分类查询服务店铺信息
     *
     * @param distance 距离
     * @param longitude 经度
     * @param latitude 纬度
     *
     * */
    public static void findServiceStoresByCate(int distance, double longitude, double latitude,int pageNum,int numPerPage,int type1,
                                                      long categoryId,HandleSuccess<ServiceShopsBean> handleSuccess){
        Call<ServiceShopsBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findServiceStoresByCate(distance, longitude, latitude,pageNum,numPerPage,type1,categoryId);
        call.enqueue(handleSuccess);
    }
    /**
     * 预约服务项目
     *
     * @param goodsid 服务项ID
     * @param storeid 店铺ID
     * @param linkname 联系人信息
     *
     * */
    public static void orderService(Long goodsid, Long storeid, String linkname, String linkphone, String address, String appointment, String memberid,
                                    BigDecimal goodsamount, BigDecimal orderamount, BigDecimal favorableamount, Long couponid,
                                    HandleSuccess<ServiceOrderBean> handleSuccess){
        Call<ServiceOrderBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .orderService(goodsid, storeid, linkname,linkphone,address,appointment,memberid,goodsamount,orderamount,favorableamount,couponid);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询服务预约订单列表
     *
     * @param userid 用户ID
     * @param pageNum 页码
     * @param numPerPage 每页数量
     *
     * */
    public static void findorderServices(String userid, int pageNum, int numPerPage,
                                    HandleSuccess<OrderServiceList> handleSuccess){
        Call<OrderServiceList> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findorderServices(userid, pageNum, numPerPage);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询美食/各种店铺的主营类型  当前只有查美食主类型用了这个接口  此接口写到了服务模块只是一个巧合无任何关联
     *
     * @param parentId 父类别ID
     *
     * */
    public static void findMainCatesByParentId(long parentId,HandleSuccess<FoodMainCateBean> handleSuccess){
        Call<FoodMainCateBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findMainCatesByParentId(parentId);
        call.enqueue(handleSuccess);
    }
    /**
     * 搜索服务商品（服务项）
     *
     * @param goodsName 关键词
     *
     * */
    public static void findServiceProducts(String goodsName,int distance,double longitude,double latitude,int pageNum,int numPerPage,
                                           HandleSuccess<ServiceGoodBean> handleSuccess){
        Call<ServiceGoodBean> call = NetUtil.createFoodApi(IFoodsApi.SERVICE_URL)
                .findServiceProducts(goodsName,distance,longitude,latitude,pageNum,numPerPage);
        call.enqueue(handleSuccess);
    }
}
