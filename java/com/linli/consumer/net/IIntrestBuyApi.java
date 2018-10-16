package com.linli.consumer.net;

import com.linli.consumer.adapter.shop_v2.NormalGoodsBean;
import com.linli.consumer.bean.AccountInfo;
import com.linli.consumer.bean.AddBankCard;
import com.linli.consumer.bean.AddVoice;
import com.linli.consumer.bean.AddrNew;
import com.linli.consumer.bean.AdverPosition;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.AliPayInfo;
import com.linli.consumer.bean.BUserInfo;
import com.linli.consumer.bean.BalancePayBean;
import com.linli.consumer.bean.CUserInfo;
import com.linli.consumer.bean.ChargeBean;
import com.linli.consumer.bean.CollectGood;
import com.linli.consumer.bean.CollectShop;
import com.linli.consumer.bean.ConcernedList;
import com.linli.consumer.bean.CounponBean;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.bean.DirectGoodBean;
import com.linli.consumer.bean.DriverInfoBean;
import com.linli.consumer.bean.DriverPosBean;
import com.linli.consumer.bean.EggBean;
import com.linli.consumer.bean.FeedBackGoods;
import com.linli.consumer.bean.FeedBacks;
import com.linli.consumer.bean.FindAreaInfo;
import com.linli.consumer.bean.FoodBean;
import com.linli.consumer.bean.FoodExtraInfo;
import com.linli.consumer.bean.General;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.GoodBean;
import com.linli.consumer.bean.IfEvaluate;
import com.linli.consumer.bean.LeaguerAdd;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.MallStoreExtraInfo;
import com.linli.consumer.bean.MoneyIn;
import com.linli.consumer.bean.MoneyInNew;
import com.linli.consumer.bean.MyEggBean;
import com.linli.consumer.bean.NearbyShopHomepage;
import com.linli.consumer.bean.NotifyUrlBean;
import com.linli.consumer.bean.OrderFood;
import com.linli.consumer.bean.OrderProduct;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.bean.ProvinceCityCounty;
import com.linli.consumer.bean.ReceiveAddr;
import com.linli.consumer.bean.Regist;
import com.linli.consumer.bean.RequestParamPYPBean;
import com.linli.consumer.bean.RongToken;
import com.linli.consumer.bean.SaleCateBean;
import com.linli.consumer.bean.SearchFood;
import com.linli.consumer.bean.SearchGood;
import com.linli.consumer.bean.StoreByWifi;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.bean.TakeCarNeedBean;
import com.linli.consumer.bean.TakeCarNeedListBean;
import com.linli.consumer.bean.UpdateBirthday;
import com.linli.consumer.bean.UpdateLeaguer;
import com.linli.consumer.bean.UpdatePayPwd;
import com.linli.consumer.bean.VersionInfo;
import com.linli.consumer.bean.VoiceInfo;
import com.linli.consumer.bean.WXPayInfo;
import com.linli.consumer.bean.YaoPayOrder;
import com.linli.consumer.common.Common;

import java.math.BigDecimal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by hasee on 2016/12/1.
 */

public interface IIntrestBuyApi {
    //根地址
    public static final String INTRESTBY_URL = Common.myUrl;


    /*
将微信登录的code给后台
*/
    @FormUrlEncoded
    @POST("personal/appWXLogin")
    Call<Login> appWXLogin(@Field("code")String code);
    /*
解绑微信
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/unbindWX")
    Call<Generic> unbindWX(@Field("id")Long id);
    /*
微信登录绑定手机号
*/
    @FormUrlEncoded
    @POST("personal/appWXBandPhone")
    Call<Login> appWXBandPhone(@Field("openId")String openId, @Field("phoneCode")String phoneCode,@Field("phone")String phone);
    /*
微信登录注册手机号
*/
    @FormUrlEncoded
    @POST("personal/appWXRegister")
    Call<Login> appWXRegister(@Field("idCard")String idCard,@Field("openId")String openId, @Field("phoneCode")String phoneCode,@Field("phone")String phone);
    /*
微信绑定手机获取验证码
*/
    @FormUrlEncoded
    @POST("personal/appWXPhoneSendCode")
    Call<General> appWXPhoneSendCode(@Field("phone")String phone, @Field("openId")String openId);
    /*
   首页查询附近商家
    */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findShopList")
    Call<NearbyShopHomepage> findShopList(@Field("categorytype")Integer categorytype, @Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum, @Field("distance")Integer distance,
                                          @Field("latitude")Double latitude, @Field("longitude")Double longitude);
    /*
    用户登录
     */
    @FormUrlEncoded
    @POST("personal/appLogin")
    Call<Login> userLogin(@Field("username")String username, @Field("password")String password);
    /*
查询用户信息
 */
    @FormUrlEncoded
    @POST("member/mall/userCenter/findUserById")
    Call<Login> findUserInfo(@Field("memId")String memId);
    /*
    找回密码
     */
    @FormUrlEncoded
    @POST("member/mall/userLogin/lost")
    Call<Generic> lost(@Field("phoneCode")String phoneCode, @Field("ressPassWord")String ressPassWord, @Field("newPassWord")String newPassWord,
                       @Field("phone")String phone);
    /*
    图片语音上传返回Token
     */
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/getToken")
    Call<Generic> getToken(@Field("fileName")String fileName);
    /*
    获取语音列表
     */
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findVoiceListByUserId")
    Call<FeedBacks> findVoiceListByUserId(@Field("numPerPage")Integer numPerPage
            , @Field("pageNum")Integer pageNum, @Field("memId")String memId);
    /*
    用户注册获取验证码
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/phoneCode")
    Call<PhoneCode> phoneCode(@Field("type")Integer type, @Field("phone")String phone);
    /*
    修改绑定手机获取验证码
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/phoneCode")
    Call<PhoneCode> phoneCode(@Field("phone")String phone);

    /*
    注册
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/regist")
    Call<Regist> regist(@Field("phone")String phone, @Field("newPassWord")String newPassWord,
                        @Field("phoneCode")String phoneCode, @Field("tag")Integer tag, @Field("regionId")Integer regionId);
    /*
     钱包查询个人账户信息
   */
    @FormUrlEncoded
    @POST("member/mall/userCenter/queryUserAccout")
    Call<AccountInfo> queryUserAccout(@Field("id")String id);
    /*
    修改支付密码
  */
    @FormUrlEncoded
    @POST("member/mall/userCenter/setupPayPassword")
    Call<UpdatePayPwd> setupPayPassword(@Field("userId")String userId, @Field("oldPassword")String oldPassword, @Field("newPassword")String newPassword);
    /*
添加支付密码
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/setupPayPassword")
    Call<UpdatePayPwd> setupPayPasswordSet(@Field("userId")String userId, @Field("newPassword")String newPassword);
    /*
    绑定修改银行卡
  */
    @FormUrlEncoded
    @POST("member/mall/userCenter/addBankCard")
    Call<AddBankCard> addBankCard(@Field("paypassword")String paypassword, @Field("bankname")String bankname, @Field("cardno")String cardno,
                                  @Field("accountname")String accountname, @Field("memId")String memId);
    /*
    用户充值
  */
    @FormUrlEncoded
    @POST("member/mall/userCenter/launchRechargeTrans")
    Call<MoneyIn> launchRechargeTrans(@Field("price")Double price, @Field("memId")String memId);
        /*
    用户充值(新版)
    */
    @FormUrlEncoded
    @POST("company/rechargeAccounting")
    Call<MoneyInNew> launchRechargeTransNew(@Field("userId")String userId, @Field("areaId")Long areaId, @Field("type")int type,
                                            @Field("payAmount")Double payAmount, @Field("payWay")int payWay, @Field("showLev")int showLev,
                                            @Field("discount")String discount, @Field("present_amount")Double present_amount);
    /*
   充值结果
 */
    @FormUrlEncoded
    @POST("member/mall/userCenter/launchResult")
    Call<Generic> launchResult(@Field("transId")String transId);
    /*
  用户提现
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/launchWithdrawTrans")
    Call<Generic> launchWithdrawTrans(@Field("price")Double price,@Field("payPassword")String payPassword,@Field("owerId")Long owerId);
    /*
修改用户密码
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/userPasswordUpdate")
    Call<Generic> userPasswordUpdate(@Field("phone")String phone,@Field("ressPassWord")String ressPassWord,@Field("newPassWord")String newPassword);

    /*
个人资料修改头像
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<Login> updateUseravatar(@Field("memId")String id,@Field("avatar")String avatar);
    /*
个人资料修改生日
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<UpdateBirthday> updateUserbirthday(@Field("memId")String id, @Field("birthday")String birthday);
    /*
个人资料昵称
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<Login> updateUsernickname(@Field("memId")String id,@Field("nickname")String nickname);
    /*
个人资料修改性别
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<Login> updateUsersex(@Field("memId")String id,@Field("sex")Integer sex);
    /*
个人资料修改绑定手机号
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<Login> updateUserphone(@Field("memId")String id,@Field("phone")String phone,@Field("password")String password);
    /*
    /*
个人资料修改绑定邮箱
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<Login> updateUseremail(@Field("memId")String id,@Field("email")String email);
    /*
邮箱验证发送
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/sendEmail")
    Call<PhoneCode> sendEmail(@Field("email")String email);
    /*
收藏商品查询
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collectionGoods")
    Call<CollectGood> collectionGoods(@Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("memId")String memId);
    /*
商城入驻添加接口
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/addShopSettled")
    Call<Generic> addShopSettled(@Field("name")String name,@Field("linkman")String linkman,@Field("regionId")Integer regionId,
                                      @Field("phone")String phone,@Field("content")String content,@Field("type")Integer type);
    /*
用户订单查询商城订单
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectOrderList")
    Call<OrderProduct> selectOrderListMall(@Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum,
                                           @Field("type")Integer type, @Field("memId")String memId);
    /*
用户订单查询美食订单
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectOrderList")
    Call<OrderFood> selectOrderListFood(@Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum,
                                    @Field("type")Integer type, @Field("memId")String memId);
    /*
用户订单查询服务订单
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectOrderList")
    Call<Generic> selectOrderListService(@Field("status")Integer status, @Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum,
                                        @Field("type")Integer type, @Field("memId")String memId);
    /*
用户订单修改订餐订单状态
*/
    @FormUrlEncoded
    @POST("food/updateFoodOrder")
    Call<Generic> updateFoodOrderStatus(@Field("id")Long id,@Field("orderStatus")Integer orderStatus,@Field("payType")int payType);
    /*
用户订单修改商城订单状态
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/orderUpdate")
    Call<Generic> updateMallOrderStatus(@Field("id")Long id,@Field("orderStatus")Integer orderStatus,@Field("payType")int payType);
    /*
用户订单修改服务订单状态
*/
    @FormUrlEncoded
    @POST("mall/serve/hdServeStore/updateServeOrder")
    Call<Generic> updateServiceOrderStatus(@Field("id")Long id,@Field("orderstatus")Integer orderStatus);
    /*
    删除订单
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/deleteOrder")
    Call<Generic> deleteOrder(@Field("type")Integer type,@Field("orderId")Long orderId);
    /*
收货地址管理查列表
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectListUserDistributAddress")
    Call<ReceiveAddr> selectListUserDistributAddress(@Field("memId")String memId);
    /*
收货地址管理删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/deleteUserDistributAddress")
    Call<Generic> deleteUserDistributAddress(@Field("id")Long id);
    /*
收货地址管理修改
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUserDistributAddress")
    Call<PhoneCode> updateUserDistributAddress(@Field("type")Integer type,@Field("provinceId")Integer provinceId,@Field("cityId")Integer cityId,@Field("areaId")Integer areaId,
                                                  @Field("address")String address,@Field("phone")String phone,@Field("name")String name,@Field("id")Long id);
    /*
收货地址管理添加
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/addUserDistributAddress")
    Call<AddrNew> addUserDistributAddress(@Field("type")Integer type,@Field("provinceId")Integer provinceId,@Field("cityId")Integer cityId,@Field("areaId")Integer areaId,
                                               @Field("address")String address,@Field("phone")String phone,@Field("name")String name,
                                               @Field("memberId")String memberId);
    /*
收货地址管理设为默认
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/setUserDistributAddress")
    Call<Generic> setUserDistributAddress(@Field("id")Long id,@Field("memberId")String memberId);
    /*
添加用户意见反馈
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/addUserFeedback")
    Call<PhoneCode> addUserFeedback(@Field("qq")String qq,@Field("phone")String phone,@Field("phoneType")Integer phoneType,
                                       @Field("remark")String remark,@Field("memId")String memId);
    /*
店铺收藏查询
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collectionShop")
    Call<CollectShop> collectionShop(@Field("type")Integer type, @Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum,
                                     @Field("memId")String memId);
    /*
店铺收藏删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collShopDel")
    Call<Generic> collShopDel( @Field("storeId")Long storeId,@Field("memberId")String memberId);
    /*
商品收藏删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collGoodDel")
    Call<Generic> collGoodDel( @Field("id")Long id);
    /*
根据订单id查询订单详情
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/orderDetail")
    Call<PhoneCode> orderDetail( @Field("type")Integer type,@Field("orderId")Long orderId);
    /*
美食店铺收藏删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collFoodShopDel")
    Call<Generic> collFoodShopDel( @Field("storeId")Long storeId,@Field("memberId")String memberId);
    /*
根据用户id查询对应的用户关系---融云查头像用户名
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findshopuser")
    Call<CUserInfo> findshopuser(@Field("type")Integer type, @Field("userId")String userId);
    /*
根据用户id查询对应的用户关系---融云查头像用户名 商城
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findshopuser")
    Call<BUserInfo> findshopuser1(@Field("type")Integer type, @Field("userId")String userId);
    /*
根据用户id查询对应的用户关系---融云查头像用户名 美食
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findshopuser")
    Call<BUserInfo> findshopuser2(@Field("type")Integer type, @Field("userId")String userId);
    /*
根据语音id和商家id查商品list
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findListByVoiceId")
    Call<FeedBackGoods> findListByVoiceId(@Field("pubvoiceId")Long pubvoiceId, @Field("storeId")Long storeId);
    /*
订单完成添加商家积分
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/addShopPoint")
    Call<Generic> addShopPoint( @Field("type")Integer type,@Field("orderNo")String orderNo);
    /*
提醒卖家发货
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/remindShop")
    Call<PhoneCode> remindShop( @Field("orderNo")String orderNo,@Field("storeId")Long storeId);
    /*
获取支付宝所需信息
*/
    @FormUrlEncoded
    @POST("thirdparty/zhifubao/ZFBPay/zhifubaoPay")
    Call<AliPayInfo> zhifubaoPay(@Field("id")Long id);
    /*
获取支付宝所需信息回调链接
*/
    @FormUrlEncoded
    @POST("thirdparty/zhifubao/ZFBPay/notifyUrlAliPay")
    Call<NotifyUrlBean> notifyUrlAliPay(@Field("id")int id);
    /*
获取融云token
*/
    @FormUrlEncoded
    @POST("personal/getRongToken")
    Call<RongToken> getRongToken( @Field("avatar")String avatar,@Field("nickName")String nickName,@Field("customerCode")String customerCode);
    /*
添加语音记录（图片文字都可以）
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/addUserVoice")
    Call<AddVoice> addUserVoice( @Field("type")Integer type,@Field("memId")String memId,@Field("name")String name);
    /*
语音图片推送
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/jpush")
    Call<Generic> jpush( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("distance")Double distance,
                                     @Field("userVoiceId")Long userVoiceId,@Field("memberId")String memberId,@Field("longitude")Double longitude,
                                     @Field("latitude")Double latitude,@Field("num")int num);
    /*
新版文字语音智能推送
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/userJpush")
    Call<Generic> userJpush( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("distance")Double distance,
                         @Field("userVoiceId")Long userVoiceId,@Field("memberId")String memberId,@Field("longitude")Double longitude,
                         @Field("latitude")Double latitude,@Field("num")int num,@Field("keyword")String keyword,@Field("keyword1")String keyword1,@Field("keyword2")String keyword2);
    /*
用户投诉商家
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/addUserConplaint")
    Call<Generic> addUserConplaint(@Field("type")int type,@Field("memname")String memname,@Field("regionid")Long regionid,@Field("content")String content,
                              @Field("memid")String memid,@Field("storename")String storename,@Field("storeid")Long storeid);
    /*
验证手机号和验证码是否正确
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/checkPhoneAndCode")
    Call<Generic> checkPhoneAndCode(@Field("type")Integer type, @Field("phoneCode")String phoneCode,@Field("phone")String phone);
    /*
验证邮箱和验证码是否正确
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/checkPhoneAndCode")
    Call<Generic> checkEmailAndCode(@Field("type")Integer type, @Field("phoneCode")String phoneCode,@Field("email")String email);
    /*
查询开通的省份
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findProviceList")
    Call<ProvinceCityCounty> findProviceList(@Field("parentId")Integer parentId);
    /*
根据省份id查询开通的城市
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findCityList")
    Call<ProvinceCityCounty> findCityList(@Field("parentId")Integer parentId);
    /*
根据城市id查询开通区域
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findDistrictList")
    Call<ProvinceCityCounty> findDistrictList(@Field("parentId")Integer parentId);
    /*
用户对商品点赞
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/userGoodsPraise")
    Call<Generic> userGoodsPraise(@Field("orderSn")String orderSn,@Field("goodsId")Long goodsId,@Field("userId")String userId,@Field("praisenum")Integer praisenum,
                                       @Field("storeId")Long storeId);
    /*
用户对菜品点赞
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/userFoodPraise")
    Call<Generic> userFoodPraise(@Field("orderSn")String orderSn,@Field("foodId")Long foodId,@Field("userId")String userId,
                                 @Field("praisenum")Integer praisenum,@Field("storeId")Long storeId);
    /*
用户对服务产品点赞
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/userServicePraise")
    Call<Generic> userServicePraise(@Field("orderSn")String orderSn,@Field("serviceId")Long serviceId,@Field("userId")String userId,@Field("praisenum")Integer praisenum,
                                 @Field("storeId")Long storeId);
    /*
用户对服务产品点赞
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/findSupportList")
    Call<IfEvaluate> findSupportList(@Field("type")Integer type, @Field("orderNo")String orderNo);
    /*
获取语音时长
*/
    @GET
    Call<VoiceInfo> getVoiceDuration(@Url String url);
    /*
根据标识查询APP版本号
*/
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findAppVersion")
    Call<VersionInfo> findAppVersion(@Field("flag")Integer flag);
    /*
用户查广告先根据当前定位查询区域ID
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findcityList")
    Call<FindAreaInfo> findcityList(@Field("name")String name);
    /*
用户查广告再根据区域ID查询广告位置 商城广告
*/
    @FormUrlEncoded
    @POST("ad/searchStandard")
    Call<AdverPosition> searchStandard(@Field("areacode")Integer areacode);
    /*
用户查广告再根据区域ID查询广告位置 商城广告
*/
    @FormUrlEncoded
    @POST("adfood/searchStandard")
    Call<AdverPosition> searchStandardF(@Field("areacode")Integer areacode);
    /*
用户查广告列表商讯(查询商城广告)
*/
    @FormUrlEncoded
    @POST("ad/searchByParam")
    Call<AdvertisementList> searchByParam( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("status")int status,@Field("position")Long position, @Field("userId")String userId,
                                          @Field("areaId")Integer areaId);
    /*
用户查广告列表会员商讯(查询商城广告)
*/
    @FormUrlEncoded
    @POST("ad/searchByParamVip")
    Call<AdvertisementList> searchByParamVip( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("position")Long position, @Field("userId")String userId,
                                              @Field("areaId")Integer areaId,@Field("status")int status);
    /*
用户查广告列表拍一拍查询商城广告)
*/
    @FormUrlEncoded
    @POST("ad/searchByParam")
    Call<AdvertisementList> searchByParamP(@Field("status")int status,@Field("position")Long position, @Field("userId")String userId,
                                           @Field("areaId")Integer areaId, @Field("storeId")Long storeId);
    /*
用户查广告再根据区域ID查询广告位置(查询美食广告)
*/
    @FormUrlEncoded
    @POST("adfood/searchStandard")
    Call<AdverPosition> searchStandardFood(@Field("areacode")Integer areacode);
    /*
用户查广告列表商讯(查询美食广告)
*/
    @FormUrlEncoded
    @POST("adfood/searchByParam")
    Call<AdvertisementListF> searchByParamFood( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("status")int status, @Field("position")Long position, @Field("userId")String userId,
                                               @Field("areaId")Integer areaId);
    /*
用户查广告列表会员商讯(查询美食广告)
*/
    @FormUrlEncoded
    @POST("adfood/searchByParamVip")
    Call<AdvertisementListF> searchByParamFoodVip( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum, @Field("position")Long position, @Field("userId")String userId,
                                                @Field("areaId")Integer areaId);
    /*
用户查广告列表拍一拍(查询美食广告)
*/
    @FormUrlEncoded
    @POST("adfood/searchByParam")
    Call<AdvertisementListF> searchByParamFoodP(@Field("status")int status,@Field("position")Long position, @Field("userId")String userId, @Field("areaId")Integer areaId,
                                               @Field("storeId")Long storeId);
    /*
提交广告总举报数/举报数+1
*/
    @FormUrlEncoded
    @POST("ad/updateAdVo")
    Call<Generic> updateAdVo(@Field("id")Long id, @Field("report")Integer report);
    /*
提交广告总展示数/展示数+1  商城广告
*/
    @FormUrlEncoded
    @POST("ad/updateAdVo")
    Call<Generic> updateAdVoShowCount(@Field("id")Long id, @Field("showcount")Integer showcount);
    /*
提交广告总展示数/展示数+1  美食广告
*/
    @FormUrlEncoded
    @POST("adfood/updateAdVo")
    Call<Generic> updateAdVoShowCountF(@Field("id")Long id, @Field("showcount")Integer showcount);
    /*
提交广告总点击数/点击数+1  商城广告
*/
    @FormUrlEncoded
    @POST("ad/updateAdVo")
    Call<Generic> updateAdVoCount(@Field("id")Long id, @Field("count")Integer count);
    /*
提交广告总点击数/点击数+1  美食广告
*/
    @FormUrlEncoded
    @POST("adfood/updateAdVo")
    Call<Generic> updateAdVoCountF(@Field("id")Long id, @Field("count")Integer count);
    /*
根据WiFiID查询店铺信息
*/
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findByWifiId")
    Call<StoreByWifi> findByWifiId(@Field("wifiid")String wifiid);
    /*
根据店铺ID查询美食店铺信息
*/
    @FormUrlEncoded
    @POST("user/food/findFoodShopByshopId")
    Call<StoreInfo> findFoodShopByshopId(@Field("storeId")Long storeId);
    /*
根据店铺ID查询商城店铺信息
*/
    @FormUrlEncoded
    @POST("store/mall/shopCenter/findShopByshopId")
    Call<MallShopInfo> findShopByshopId(@Field("storeId")Long storeId);
    /*
微信支付请求支付所需信息
*/
    @FormUrlEncoded
    @POST("wechatPay/appOrder")
    Call<WXPayInfo> wxPay(@Field("amount")String amount, @Field("orderSn")String orderSn, @Field("goodSn")String goodSn,
                          @Field("desc")String desc, @Field("detail")String detail);
    /*
支付宝返回异步通知
*/
    @FormUrlEncoded
    @POST("thirdparty/zhifubao/ZFBPay/notifyUrl")
    Call<Generic> notifyUrl(@Field("type")Integer type,@Field("id")Long id,@Field("paymentType")Integer paymentType);
    /*
查询关注商铺列表 不包括取关店铺
*/
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguer")
    Call<ConcernedList> leaguer(@Field("memberId")String memberId, @Field("status")int status, @Field("numPerPage")Integer numPerPage, @Field("plainPageNum")int plainPageNum);
    /*
查询关注商铺列表 不包括取关店铺 查用户关注商户状态用
*/
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguer")
    Call<ConcernedList> leaguer(@Field("memberId")String memberId, @Field("storeId")Long storeId);
    /*
查询关注商铺列表 包括取关店铺
*/
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguer")
    Call<ConcernedList> leaguer(@Field("memberId")String memberId);
    /*
修改关注商家状态
*/
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguerUpdate")
    Call<UpdateLeaguer> leaguerUpdate(@Field("id")Long id, @Field("status")Integer status);
    /*
查询用户在该商城商家的未付款订单//根据店铺id，用户id，订单状态查询订单列表（摇一摇功能）
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/findOrderList")
    Call<YaoPayOrder> findOrderList(@Field("memId")String memId, @Field("storeId")Long storeId,@Field("status")int status);
    /*
查询用户在该餐饮商家的未付款订单
*/
    @FormUrlEncoded
    @POST("member/mall/StoreMember/selectPayingOrdersFood")
    Call<Generic> selectPayingOrdersFood(@Field("memberId")String memberId, @Field("storeId")Long storeId);
    /*
查询商家的拍一拍状态菜品
*/
    @FormUrlEncoded
    @POST("food/findCookBook")
    Call<FoodBean> findCookBook(@Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum, @Field("bussId")Long bussId, @Field("ispat")int ispat,
                                @Field("status")int status);

    /*
查询商家的拍一拍状态商品
*/
    @POST("mall/mall/hdMall/findGoodsIsPat")
    Call<GoodBean> findGoodsIsPat(@Body RequestParamPYPBean bean);
    /*
搜索附近全平台菜品
*/
    @FormUrlEncoded
    @POST("user/food/findNearCookByName")
    Call<SearchFood> findNearCookByName(@Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum, @Field("goodsName")String goodsName,
                                        @Field("distance")int distance, @Field("latitude")Double latitude, @Field("longitude")Double longitude);
    /*
搜索附近全平台商品
*/
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findNearGoodsByName")
    Call<SearchGood> findNearGoodsByName(@Field("numPerPage")Integer numPerPage, @Field("pageNum")Integer pageNum, @Field("goodsName")String goodsName,
                                         @Field("distance")int distance, @Field("latitude")Double latitude, @Field("longitude")Double longitude);
    /*
用户关注成为会员
*/
    @FormUrlEncoded
    @POST("member/mall/StoreMember/leaguerFor")
    Call<LeaguerAdd> leaguerFor(@Field("storeId")long storeId,@Field("memberId")String memberId,
                                @Field("name")String name,@Field("region_id")long region_id,
                                @Field("phone")String phone,@Field("headpath")String headpath,
                                @Field("birthday")String birthday,@Field("sex")int sex,@Field("address")String address);
    /*
 查询美食商家附加信息 送餐费等
*/
    @FormUrlEncoded
    @POST("food/findStoreOperate")
    Call<FoodExtraInfo> findStoreOperate(@Field("storeId")Long storeId);
    /*
查询商城商家附加信息 配送费等
*/
    @FormUrlEncoded
    @POST("store/mall/shopCenter/queryHdMallStoreOperate")
    Call<MallStoreExtraInfo> queryHdMallStoreOperate(@Field("shopId")Long shopId);
    /*
请求商家所有蛋的类型
*/
    @FormUrlEncoded
    @POST("store/coupon/queryStoreCoupon")
    Call<CouponsBean> queryStoreCoupon(@Field("status")Long status,@Field("memberId")String memberId,@Field("type")Integer type);
    /*
请求商家所有蛋的类型(按类型查固定店铺优惠券)
*/
    @FormUrlEncoded
    @POST("store/coupon/queryStoreCoupon")
    Call<CouponsBean> queryStoreCouponByType(@Field("status")Integer status,@Field("memberId")String memberId,@Field("storeId")Long storeId,@Field("type")int type);
    /*
将蛋纳入用户蛋库
*/
    @FormUrlEncoded
    @POST("store/coupon/getEgg")
    Call<EggBean> getEgg(@Field("memberId")String memberId, @Field("storeId")Long storeId, @Field("storeName")String storeName,
                         @Field("storeType")int storeType);
    /*
 用户打开蛋，获取优惠券（概率获取）
*/
    @FormUrlEncoded
    @POST("store/coupon/getStoreCoupon")
    Call<CounponBean> getStoreCoupon(@Field("id")Long id, @Field("type")Integer type, @Field("memberId")String memberId, @Field("storeId")Long storeId);
    /*
蛋库查询
*/
    @FormUrlEncoded
    @POST("store/coupon/queryEgg")
    Call<MyEggBean> queryEgg(@Field("memberId")String memberId);
    /*
拉卡拉请求charge对象
*/
    @FormUrlEncoded
    @POST("ad/lakala")
    Call<ChargeBean> getChartLKL(@Field("body")String body, @Field("title")String title, @Field("channel")String channel, @Field("time_expire")Long time_expire,
                                 @Field("totalPrice")Double totalPrice, @Field("user_id")String user_id, @Field("order_no")String order_no);

    /*
余额支付
*/
    @FormUrlEncoded
    @POST("company/updateAccount")
    Call<BalancePayBean> balancePay(@Field("Id")Long Id, @Field("balance")Double balance, @Field("orderId")Long orderId);

    // 查询当前账户的信息
    @FormUrlEncoded
    @POST("company/findAccounts")
    Call<ResponseBody> findAccounts(@Field("type") int type, @Field("roleId") String roleId);

    //查询当前账户银行卡列表   增删改查
    //  roleType 角色类型：0公司，1运营商，2商家，3餐饮，4服务，5厂商，用户
    @FormUrlEncoded
    @POST("company/findBanks")
    Call<ResponseBody> findBanks(@Field("roleType") int roleType, @Field("roleId") String roleId,
                                 @Field("numPerPage") int numPerPage, @Field("pageNum") int pageNum);

    @FormUrlEncoded
    @POST("company/addBank")
    Call<ResponseBody> addBank(@Field("type") int type, @Field("roleType") int roleType, @Field("roleId") String roleId,
                               @Field("person") String person, @Field("bank") String bank, @Field("bankBranch") String bankBranch,
                               @Field("number") String number, @Field("province") String province, @Field("bankCode") Long bankCode,
                               @Field("bankBranchCode") Long bankBranchCode, @Field("provinceCode") Long provinceCode

    );

    @FormUrlEncoded
    @POST("company/delBank")
    Call<ResponseBody> delBank(@Field("id") Long id);


    @FormUrlEncoded
    @POST("company/updateBank")
    Call<ResponseBody> updateBank(@Field("id") Long id, @Field("type") int type, @Field("person") String person, @Field("bank") String bank, @Field("bankBranch") String bankBranch,
                                  @Field("number") String number);

    @FormUrlEncoded
    @POST("company/findRecords")
    Call<ResponseBody> findRecords(@Field("userType") int userType, @Field("userId") String userId,
                                   @Field("numPerPage") int numPerPage, @Field("pageNum") int pageNum);


    @FormUrlEncoded
    @POST("company/cashAccounting")
    Call<ResponseBody> cashAccounting(@Field("userId") String storeId,
                                      @Field("type") int type, @Field("payAmount") Double payAmount,
                                      @Field("areaId") Long areaId, @Field("bankId") Long bankId,
                                      @Field("presentAmount") Double presentAmount
    );

    @FormUrlEncoded
    @POST("company/findNoteBanks")
    Call<ResponseBody> findNoteBanks(@Field("bank") String bank);

    @FormUrlEncoded
    @POST("company/findBankBranchs")
    Call<ResponseBody> findBankBranchs(@Field("bank") String bank,
                                       @Field("numPerPage") int numPerPage, @Field("pageNum") int pageNum);

    @FormUrlEncoded
    @POST("company/findProvinces")
    Call<ResponseBody> findProvinces(@Field("name") String name);


    /*
建立用户与商户的关系
*/
    @FormUrlEncoded
    @POST("store/register/storeMemRelation")
    Call<Generic> storeMemRelation(@Field("memid")String memid, @Field("storeid")Long storeid, @Field("type")int type);


    /*
添加打车需求
*/
    @FormUrlEncoded
    @POST("taxiDemand/addTaxiDemand")
    Call<Generic> addTaxiDemand(@Field("type")Integer type,@Field("menberId")String menberId, @Field("startingPoint")String startingPoint, @Field("endPoint")String endPoint,
                                @Field("startingLog")Double startingLog,@Field("startingLng")Double startingLng,@Field("endLog")Double endLog,
                                @Field("endLng")Double endLng,@Field("voicePath")String voicePath);
    /*
查询附近5km是否有司机正在接单（首页是否展示出行导航）
*/
    @FormUrlEncoded
    @POST("taxiDriver/findDriver")
    Call<Generic> findDriver(@Field("startingLog")Double startingLog,@Field("startingLng")Double startingLng);
    /*
根据需求id查询打车需求 若传0查最新一条
*/
    @FormUrlEncoded
    @POST("taxiDemand/findByDemandId")
    Call<TakeCarNeedBean> findByDemandId(@Field("memberId")String memberId, @Field("id")Long id);

    /*
查询打车需求列表
*/
    @FormUrlEncoded
    @POST("taxiDemand/list")
    Call<TakeCarNeedListBean> findListTakeCarNeed(@Field("memberId")String memberId, @Field("plainPageNum")Integer plainPageNum, @Field("numPerPage")Integer numPerPage);

    /*
更改需求状态（取消订单）
 */
    @FormUrlEncoded
    @POST("taxiDemand/updateTaxiDemand")
    Call<Generic> updateTaxiDemand(@Field("id")Long id, @Field("status")Integer status, @Field("travelState")Integer travelState, @Field("driverId")Long driverId);

    /*
查询司机信息
*/
    @FormUrlEncoded
    @POST("taxiDriver/findDriverById")
    Call<DriverInfoBean> findDriverById(@Field("driverId")Long driverId);

    /*
查询司机经纬度信息
*/
    @FormUrlEncoded
    @POST("taxiDriver/findPositionById")
    Call<DriverPosBean> findPositionById(@Field("driverId")Long driverId);

    /*
评价司机
*/
    @FormUrlEncoded
    @POST("taxiDriver/evalutionAdd")
    Call<Generic> evalutionAdd(@Field("taxiDemandId")Long taxiDemandId,@Field("type")Integer type,@Field("starRating")Float starRating,@Field("contents")String contents,
                               @Field("menberId")String menberId,@Field("driverId")Long driverId);

    /*
申请退款
*/
    @FormUrlEncoded
    @POST("hdPubRefundOrder/refundOrderAdd")
    Call<Generic> refundOrderAdd(@Field("memberId")String memberId,@Field("refundId")Long refundId,@Field("orderType")int orderType,@Field("refundAmount")BigDecimal refundAmount,
                               @Field("refundReason")String refundReason);


    /*
查询菜品打包费
*/
    @FormUrlEncoded
    @POST("user/food/calculationPackagePrice")
    Call<Generic> findPackageFee(@Field("json")String json);

    /*
条件查询商城商品
*/
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findBoutique")
    Call<DirectGoodBean> findBoutique(@Field("plainPageNum")int plainPageNum, @Field("numPerPage")int numPerPage, @Field("storeId")Long storeId,
                                      @Field("keyword")String keyword, @Field("salescategoryId")Long salescategoryId,
                                      @Field("maxPrice")Integer maxPrice,@Field("salesVolume")Integer salesVolume);
    /*
查询直营商城商品优惠策略列表
*/
    @FormUrlEncoded
    @POST("store/mall/shopCenter/findHdMallGoodsSaleCategoryList")
    Call<SaleCateBean> findHdMallGoodsSaleCategoryList(@Field("plainPageNum")int plainPageNum, @Field("numPerPage")int numPerPage);
    /*
 查询附近特推商品列表
*/
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findRecommendList")
    Call<NormalGoodsBean> findRecommendList(@Field("pageNum")int pageNum, @Field("numPerPage")int numPerPage, @Field("longitude")Double longitude,
                                            @Field("latitude")Double latitude, @Field("distance")int distance);
}
