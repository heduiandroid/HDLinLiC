package com.linli.consumer.net;

import com.alibaba.fastjson.JSON;
import com.linli.consumer.adapter.shop_v2.NormalGoodsBean;
import com.linli.consumer.bean.AccountInfo;
import com.linli.consumer.bean.AddBankCard;
import com.linli.consumer.bean.AddVoice;
import com.linli.consumer.bean.Addbank;
import com.linli.consumer.bean.AddrNew;
import com.linli.consumer.bean.AdverPosition;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.AliPayInfo;
import com.linli.consumer.bean.BUserInfo;
import com.linli.consumer.bean.BalancePayBean;
import com.linli.consumer.bean.BankDetials;
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
import com.linli.consumer.bean.FindAccounts;
import com.linli.consumer.bean.FindAreaInfo;
import com.linli.consumer.bean.FindBanks;
import com.linli.consumer.bean.FindNoteBanks;
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

import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.mock.Mocking;

import java.math.BigDecimal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hasee on 2016/12/1.
 */

public class IntrestBuyNet {
    /**
     * 将微信登录的code给后台
     *
     * @param handleSuccess
     */
    public static void appWXLogin(String code,HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).appWXLogin(code);
        call.enqueue(handleSuccess);
    }
    /**
     * 解绑微信
     *
     * @param handleSuccess
     */
    public static void unbindWX(Long id,HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).unbindWX(id);
        call.enqueue(handleSuccess);
    }
    /**
     * 微信登录绑定手机号
     *
     * @param handleSuccess
     */
    public static void appWXBandPhone(String openId, String phoneCode, String phone, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).appWXBandPhone(openId, phoneCode, phone);
        call.enqueue(handleSuccess);
    }
    /**
     * 微信登录注册手机号
     *
     * @param handleSuccess
     */
    public static void appWXRegister(String idCard,String openId, String phoneCode, String phone, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).appWXRegister(idCard,openId, phoneCode, phone);
        call.enqueue(handleSuccess);
    }
    /**
     * 微信绑定手机获取验证码
     *
     * @param handleSuccess
     */
    public static void appWXPhoneSendCode(String phone, String openId,final HandleSuccess<General> handleSuccess) {
        Call<General> call = NetUtil.createMcApi(Common.myUrl).appWXPhoneSendCode(phone,openId);
        call.enqueue(handleSuccess);
    }
    /**
     * 首页查询附近商家
     *
     * @param handleSuccess
     */
    public static void findShopList(Integer categorytype, Integer numPerPage, Integer pageNum, Integer distance, Double latitude, Double longitude, final HandleSuccess<NearbyShopHomepage> handleSuccess) {
        Call<NearbyShopHomepage> call = NetUtil.createMcApi(IIntrestBuyApi.INTRESTBY_URL).findShopList(categorytype, numPerPage, pageNum, distance, latitude, longitude);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户登录
     *
     * @param handleSuccess
     */
    public static void userLogin(String username, String password, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(IIntrestBuyApi.INTRESTBY_URL).userLogin(username, password);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询用户信息
     *
     * @param handleSuccess
     */
    public static void findUserInfo(String memId,final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(IIntrestBuyApi.INTRESTBY_URL).findUserInfo(memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 找回密码
     *
     * @param handleSuccess
     */
    public static void lost(String phoneCode, String ressPassWord, String newPassWord, String phone, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).lost(phoneCode, ressPassWord, newPassWord, phone);
        call.enqueue(handleSuccess);
    }

    /**
     * 图片语音上传返回Token
     *
     * @param handleSuccess
     */
    public static void getToken(String fileName, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).getToken(fileName);
        call.enqueue(handleSuccess);
    }

    /**
     * 查语音列表
     *
     * @param handleSuccess
     */
    public static void PVMessageData(Integer numPerPage, Integer pageNum, String memId, final HandleSuccess<FeedBacks> handleSuccess) {
        Call<FeedBacks> call = NetUtil.createMcApi(Common.myUrl).findVoiceListByUserId(numPerPage, pageNum, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户注册获取验证码
     *
     * @param handleSuccess
     */
    public static void getPhoneCode(Integer type, String phone, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).phoneCode(type, phone);
        call.enqueue(handleSuccess);
    }

    /**
     * 修改绑定手机获取验证码
     *
     * @param handleSuccess
     */
    public static void getPhoneCode(String phone, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).phoneCode(phone);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户注册
     *
     * @param handleSuccess
     */
    public static void regist(String phone, String newPassword, String phoneCode, Integer tag, Integer regionId, final HandleSuccess<Regist> handleSuccess) {
        Call<Regist> call = NetUtil.createMcApi(Common.myUrl).regist(phone, newPassword, phoneCode, tag, regionId);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询个人信息账户
     *
     * @param handleSuccess
     */
    public static void queryUserAccout(String id, final HandleSuccess<AccountInfo> handleSuccess) {
        Call<AccountInfo> call = NetUtil.createMcApi(Common.myUrl).queryUserAccout(id);
        call.enqueue(handleSuccess);
    }

    /**
     * 修改支付密码
     *
     * @param handleSuccess
     */
    public static void setupPayPassword(String userId, String oldPassword, String newPassword, final HandleSuccess<UpdatePayPwd> handleSuccess) {
        Call<UpdatePayPwd> call = NetUtil.createMcApi(Common.myUrl).setupPayPassword(userId, oldPassword, newPassword);
        call.enqueue(handleSuccess);
    }

    /**
     * 添加支付密码
     *
     * @param handleSuccess
     */
    public static void setupPayPasswordSet(String userId, String newPassword, final HandleSuccess<UpdatePayPwd> handleSuccess) {
        Call<UpdatePayPwd> call = NetUtil.createMcApi(Common.myUrl).setupPayPasswordSet(userId, newPassword);
        call.enqueue(handleSuccess);
    }

    /**
     * 绑定修改银行卡
     *
     * @param handleSuccess
     */
    public static void addBankCard(String paypassword, String bankname, String cardno, String accountname, String memId, final HandleSuccess<AddBankCard> handleSuccess) {
        Call<AddBankCard> call = NetUtil.createMcApi(Common.myUrl).addBankCard(paypassword, bankname, cardno, accountname, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户充值
     *
     * @param handleSuccess
     */
    public static void launchRechargeTrans(Double price, String memId, final HandleSuccess<MoneyIn> handleSuccess) {
        Call<MoneyIn> call = NetUtil.createMcApi(Common.myUrl).launchRechargeTrans(price, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户充值(新版)
     *
     * @param handleSuccess
     */
    public static void launchRechargeTransNew(String userId, Long areaId, int type, Double payAmount, int payWay, int showLev,
                                              String discount, Double present_amount, final HandleSuccess<MoneyInNew> handleSuccess) {
        Call<MoneyInNew> call = NetUtil.createMcApi(Common.myUrl).launchRechargeTransNew(userId, areaId, type, payAmount, payWay, showLev, discount, present_amount);
        call.enqueue(handleSuccess);
    }

    /**
     * 充值结果
     *
     * @param handleSuccess
     */
    public static void launchResult(String transId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).launchResult(transId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户提现
     *
     * @param handleSuccess
     */
    public static void launchWithdrawTrans(Double price, String payPassword, Long owerId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).launchWithdrawTrans(price, payPassword, owerId);
        call.enqueue(handleSuccess);
    }

    /**
     * 修改用户密码
     *
     * @param handleSuccess
     */
    public static void userPasswordUpdate(String phone, String ressPassWord, String newPassword, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).userPasswordUpdate(phone, ressPassWord, newPassword);
        call.enqueue(handleSuccess);
    }

    /**
     * 个人资料修改头像
     *
     * @param handleSuccess
     */
    public static void updateUseravatar(String id, String avatar, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).updateUseravatar(id, avatar);
        call.enqueue(handleSuccess);
    }

    /**
     * 个人资料修改生日
     *
     * @param handleSuccess
     */
    public static void updateUserbirthday(String id, String birthday, final HandleSuccess<UpdateBirthday> handleSuccess) {
        Call<UpdateBirthday> call = NetUtil.createMcApi(Common.myUrl).updateUserbirthday(id, birthday);
        call.enqueue(handleSuccess);
    }

    /**
     * 个人资料修改昵称
     *
     * @param handleSuccess
     */
    public static void updateUsernickname(String id, String nickname, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).updateUsernickname(id, nickname);
        call.enqueue(handleSuccess);
    }

    /**
     * 个人资料修改性别
     *
     * @param handleSuccess
     */
    public static void updateUsersex(String id, Integer sex, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).updateUsersex(id, sex);
        call.enqueue(handleSuccess);
    }

    /**
     * 个人资料修改绑定手机
     *
     * @param handleSuccess
     */
    public static void updateUserphone(String id, String phone, String password, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).updateUserphone(id, phone, password);
        call.enqueue(handleSuccess);
    }

    /**
     * 个人资料修改绑定邮箱
     *
     * @param handleSuccess
     */
    public static void updateUseremail(String id, String email, final HandleSuccess<Login> handleSuccess) {
        Call<Login> call = NetUtil.createMcApi(Common.myUrl).updateUseremail(id, email);
        call.enqueue(handleSuccess);
    }

    /**
     * 邮箱验证发送
     *
     * @param handleSuccess
     */
    public static void sendEmail(String email, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).sendEmail(email);
        call.enqueue(handleSuccess);
    }

    /**
     * 收藏商品查询
     *
     * @param handleSuccess
     */
    public static void collectionGoods(Integer numPerPage, Integer pageNum, String memId, final HandleSuccess<CollectGood> handleSuccess) {
        Call<CollectGood> call = NetUtil.createMcApi(Common.myUrl).collectionGoods(numPerPage, pageNum, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 商城入驻添加接口
     *
     * @param handleSuccess
     */
    public static void addShopSettled(String name, String linkman, Integer regionId, String phone, String content, Integer type, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).addShopSettled(name, linkman, regionId, phone, content, type);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户订单查询商城订单
     *
     * @param handleSuccess
     */
    public static void selectOrderListMall(Integer numPerPage, Integer pageNum, Integer type, String memId, final HandleSuccess<OrderProduct> handleSuccess) {
        Call<OrderProduct> call = NetUtil.createMcApi(Common.myUrl).selectOrderListMall(numPerPage, pageNum, type, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户订单查询美食订单
     *
     * @param handleSuccess
     */
    public static void selectOrderListFood(Integer numPerPage, Integer pageNum, Integer type, String memId, final HandleSuccess<OrderFood> handleSuccess) {
        Call<OrderFood> call = NetUtil.createMcApi(Common.myUrl).selectOrderListFood(numPerPage, pageNum, type, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户订单查询服务订单
     *
     * @param handleSuccess
     */
    public static void selectOrderListService(Integer status, Integer numPerPage, Integer pageNum, Integer type, String memId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).selectOrderListService(status, numPerPage, pageNum, type, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户订单修改订餐订单状态
     *
     * @param handleSuccess
     */
    public static void updateFoodOrderStatus(Long id, Integer orderStatus, int payType, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateFoodOrderStatus(id, orderStatus, payType);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户订单修改商城订单状态
     *
     * @param handleSuccess
     */
    public static void updateMallOrderStatus(Long id, Integer orderStatus, int payType, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateMallOrderStatus(id, orderStatus, payType);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户订单修改服务订单状态
     *
     * @param handleSuccess
     */
    public static void updateServiceOrderStatus(Long id, Integer orderstatus, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateServiceOrderStatus(id, orderstatus);
        call.enqueue(handleSuccess);
    }

    /**
     * 删除订单
     *
     * @param handleSuccess
     */
    public static void deleteOrder(Integer type, Long orderId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).deleteOrder(type, orderId);
        call.enqueue(handleSuccess);
    }

    /**
     * 收货地址查列表
     *
     * @param handleSuccess
     */
    public static void selectListUserDistributAddress(String memId, final HandleSuccess<ReceiveAddr> handleSuccess) {
        Call<ReceiveAddr> call = NetUtil.createMcApi(Common.myUrl).selectListUserDistributAddress(memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 收货地址删除
     *
     * @param handleSuccess
     */
    public static void deleteUserDistributAddress(Long id, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).deleteUserDistributAddress(id);
        call.enqueue(handleSuccess);
    }

    /**
     * 收货地址修改
     *
     * @param handleSuccess
     */
    public static void updateUserDistributAddress(Integer type, Integer provinceId, Integer cityId, Integer areaId, String address, String phone, String name, Long id, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).updateUserDistributAddress(type, provinceId, cityId, areaId, address, phone, name, id);
        call.enqueue(handleSuccess);
    }

    /**
     * 收货地址添加
     *
     * @param handleSuccess
     */
    public static void addUserDistributAddress(Integer type, Integer provinceId, Integer cityId, Integer areaId, String address, String phone, String name, String memberId, final HandleSuccess<AddrNew> handleSuccess) {
        Call<AddrNew> call = NetUtil.createMcApi(Common.myUrl).addUserDistributAddress(type, provinceId, cityId, areaId, address, phone, name, memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 收货地址设为默认
     *
     * @param handleSuccess
     */
    public static void setUserDistributAddress(Long id, String memberId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).setUserDistributAddress(id, memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 添加用户意见反馈
     *
     * @param handleSuccess
     */
    public static void addUserFeedback(String qq, String phone, Integer phoneType, String remark, String memId, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).addUserFeedback(qq, phone, phoneType, remark, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 店铺收藏查询
     *
     * @param handleSuccess
     */
    public static void collectionShop(Integer type, Integer numPerPage, Integer pageNum, String memId, final HandleSuccess<CollectShop> handleSuccess) {
        Call<CollectShop> call = NetUtil.createMcApi(Common.myUrl).collectionShop(type, numPerPage, pageNum, memId);
        call.enqueue(handleSuccess);
    }

    /**
     * 店铺收藏删除
     *
     * @param handleSuccess
     */
    public static void collShopDel(Long storeId, String memberId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).collShopDel(storeId, memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 商品收藏删除
     *
     * @param handleSuccess
     */
    public static void collGoodDel(Long id, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).collGoodDel(id);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据订单id查询订单详情
     *
     * @param handleSuccess
     */
    public static void orderDetail(Integer type, Long orderId, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).orderDetail(type, orderId);
        call.enqueue(handleSuccess);
    }

    /**
     * 美食店铺收藏删除
     *
     * @param handleSuccess
     */
    public static void collFoodShopDel(Long storeId, String memberId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).collFoodShopDel(storeId, memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据用户id查询对应的用户关系---融云查头像用户名
     *
     * @param handleSuccess
     */
    public static void findshopuser(Integer type, String userId, final HandleSuccess<CUserInfo> handleSuccess) {
        Call<CUserInfo> call = NetUtil.createMcApi(Common.myUrl).findshopuser(type, userId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据用户id查询对应的用户关系---融云查头像用户名--- 商城
     *
     * @param handleSuccess
     */
    public static void findshopuser1(Integer type, String userId, final HandleSuccess<BUserInfo> handleSuccess) {
        Call<BUserInfo> call = NetUtil.createMcApi(Common.myUrl).findshopuser1(type, userId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据用户id查询对应的用户关系---融云查头像用户名--- 美食
     *
     * @param handleSuccess
     */
    public static void findshopuser2(Integer type, String userId, final HandleSuccess<BUserInfo> handleSuccess) {
        Call<BUserInfo> call = NetUtil.createMcApi(Common.myUrl).findshopuser2(type, userId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据语音id和商家id查商品list
     *
     * @param handleSuccess
     */
    public static void findListByVoiceId(Long pubvoiceId, Long storeId, final HandleSuccess<FeedBackGoods> handleSuccess) {
        Call<FeedBackGoods> call = NetUtil.createMcApi(Common.myUrl).findListByVoiceId(pubvoiceId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 订单完成添加商家积分
     *
     * @param handleSuccess
     */
    public static void addShopPoint(Integer type, String orderNo, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).addShopPoint(type, orderNo);
        call.enqueue(handleSuccess);
    }

    /**
     * 提醒卖家发货
     *
     * @param handleSuccess
     */
    public static void remindShop(String orderNo, Long storeId, final HandleSuccess<PhoneCode> handleSuccess) {
        Call<PhoneCode> call = NetUtil.createMcApi(Common.myUrl).remindShop(orderNo, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 获取支付宝所需信息
     *
     * @param handleSuccess
     */
    public static void zhifubaoPay(Long id, final HandleSuccess<AliPayInfo> handleSuccess) {
        Call<AliPayInfo> call = NetUtil.createMcApi(Common.myUrl).zhifubaoPay(id);
        call.enqueue(handleSuccess);
    }

    /**
     * 获取支付宝所需信息回调链接
     *
     * @param handleSuccess
     */
    public static void notifyUrlAliPay(int id, final HandleSuccess<NotifyUrlBean> handleSuccess) {
        Call<NotifyUrlBean> call = NetUtil.createMcApi(Common.myUrl).notifyUrlAliPay(id);
        call.enqueue(handleSuccess);
    }

    /**
     * 获取融云token
     *
     * @param handleSuccess
     */
    public static void getRongToken(String avatar, String nickName, String customerCode, final HandleSuccess<RongToken> handleSuccess) {
        Call<RongToken> call = NetUtil.createMcApi(Common.myUrl).getRongToken(avatar, nickName, customerCode);
        call.enqueue(handleSuccess);
    }

    /**
     * 添加语音记录（图片文字都可以）
     *
     * @param handleSuccess
     */
    public static void addUserVoice(Integer type, String memId, String name, final HandleSuccess<AddVoice> handleSuccess) {
        Call<AddVoice> call = NetUtil.createMcApi(Common.myUrl).addUserVoice(type, memId, name);
        call.enqueue(handleSuccess);
    }

    /**
     * 语音图片推送
     *
     * @param handleSuccess
     */
    public static void jpush(Integer numPerPage, Integer pageNum, Double distance, Long userVoiceId, String memberId, Double longitude, Double latitude, int num, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).jpush(numPerPage, pageNum, distance, userVoiceId, memberId, longitude, latitude, num);
        call.enqueue(handleSuccess);
    }
    /**
     * 新版文字语音智能推送
     *
     * @param handleSuccess
     */
    public static void userJpush(Integer numPerPage, Integer pageNum, Double distance, Long userVoiceId, String memberId, Double longitude, Double latitude, int num,
                                 String keyword,String keyword1,String keyword2,final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).userJpush(numPerPage, pageNum, distance, userVoiceId, memberId, longitude, latitude, num,
                keyword,keyword1,keyword2);
        call.enqueue(handleSuccess);
    }
    /**
     * 用户投诉商家
     *
     * @param handleSuccess
     */
    public static void addUserConplaint(int type, String memname, Long regionid, String content, String memid, String storename, Long storeid, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).addUserConplaint(type, memname, regionid, content, memid, storename, storeid);
        call.enqueue(handleSuccess);
    }

    /**
     * 验证手机号和验证码是否正确
     *
     * @param handleSuccess
     */
    public static void checkPhoneAndCode(Integer type, String phoneCode, String phone, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).checkPhoneAndCode(type, phoneCode, phone);
        call.enqueue(handleSuccess);
    }

    /**
     * 验证邮箱和验证码是否正确
     *
     * @param handleSuccess
     */
    public static void checkEmailAndCode(Integer type, String phoneCode, String email, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).checkEmailAndCode(type, phoneCode, email);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询开通的省份
     *
     * @param handleSuccess
     */
    public static void findProviceList(final HandleSuccess<ProvinceCityCounty> handleSuccess) {
        Call<ProvinceCityCounty> call = NetUtil.createMcApi(Common.myUrl).findProviceList(1);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据省份id查询开通的城市
     *
     * @param handleSuccess
     */
    public static void findCityList(Integer parentId, final HandleSuccess<ProvinceCityCounty> handleSuccess) {
        Call<ProvinceCityCounty> call = NetUtil.createMcApi(Common.myUrl).findCityList(parentId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据城市id查询开通区域
     *
     * @param handleSuccess
     */
    public static void findDistrictList(Integer parentId, final HandleSuccess<ProvinceCityCounty> handleSuccess) {
        Call<ProvinceCityCounty> call = NetUtil.createMcApi(Common.myUrl).findDistrictList(parentId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户对商品点赞
     *
     * @param handleSuccess
     */
    public static void userGoodsPraise(String orderSn, Long goodsId, String userId, Integer praisenum, Long storeId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).userGoodsPraise(orderSn, goodsId, userId, praisenum, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户对菜品点赞
     *
     * @param handleSuccess
     */
    public static void userFoodPraise(String orderSn, Long foodId, String userId, Integer praisenum, Long storeId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).userFoodPraise(orderSn, foodId, userId, praisenum, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户对服务产品点赞
     *
     * @param handleSuccess
     */
    public static void userServicePraise(String orderSn, Long serviceId, String userId, Integer praisenum, Long storeId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).userServicePraise(orderSn, serviceId, userId, praisenum, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询是否已经点过赞
     *
     * @param handleSuccess
     */
    public static void findSupportList(Integer type, String orderNo, final HandleSuccess<IfEvaluate> handleSuccess) {
        Call<IfEvaluate> call = NetUtil.createMcApi(Common.myUrl).findSupportList(type, orderNo);
        call.enqueue(handleSuccess);
    }

    /**
     * 获取语音时长
     *
     * @param handleSuccess
     */
    public static void getVoiceDuration(String url, final HandleSuccess<VoiceInfo> handleSuccess) {
        Call<VoiceInfo> call = NetUtil.createVoiceApi(Common.downloadVoiceUrl).getVoiceDuration(url);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据标识查询APP版本号
     *
     * @param handleSuccess
     */
    public static void findAppVersion(Integer flag, final HandleSuccess<VersionInfo> handleSuccess) {
        Call<VersionInfo> call = NetUtil.createMcApi(Common.myUrl).findAppVersion(flag);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告先根据当前定位查询区域ID
     *
     * @param handleSuccess
     */
    public static void findcityList(String name, final HandleSuccess<FindAreaInfo> handleSuccess) {
        Call<FindAreaInfo> call = NetUtil.createMcApi(Common.myUrl).findcityList(name);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告再根据区域ID查询广告位置 商城广告
     *
     * @param handleSuccess
     */
    public static void searchStandard(Integer areacode, final HandleSuccess<AdverPosition> handleSuccess) {
        Call<AdverPosition> call = NetUtil.createMcApi(Common.myUrl).searchStandard(areacode);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告再根据区域ID查询广告位置 美食广告
     *
     * @param handleSuccess
     */
    public static void searchStandardF(Integer areacode, final HandleSuccess<AdverPosition> handleSuccess) {
        Call<AdverPosition> call = NetUtil.createMcApi(Common.myUrl).searchStandardF(areacode);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告列表商讯
     *
     * @param handleSuccess
     */
    public static void searchByParam(Integer numPerPage, Integer pageNum, int status, Long position, String userId, Integer areaId, final HandleSuccess<AdvertisementList> handleSuccess) {
        Call<AdvertisementList> call = NetUtil.createMcApi(Common.myUrl).searchByParam(numPerPage, pageNum, status, position, userId, areaId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告列表 会员商讯
     *
     * @param handleSuccess
     */
    public static void searchByParamVip(Integer numPerPage, Integer pageNum, Long position, String userId, Integer areaId, int status,final HandleSuccess<AdvertisementList> handleSuccess) {
        Call<AdvertisementList> call = NetUtil.createMcApi(Common.myUrl).searchByParamVip(numPerPage, pageNum, position, userId, areaId,status);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告列表拍一拍
     *
     * @param handleSuccess
     */
    public static void searchByParamP(int status, Long position, String userId, Integer areaId, Long storeId, final HandleSuccess<AdvertisementList> handleSuccess) {
        Call<AdvertisementList> call = NetUtil.createMcApi(Common.myUrl).searchByParamP(status, position, userId, areaId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告再根据区域ID查询广告位置(美食)
     *
     * @param handleSuccess
     */
    public static void searchStandardFood(Integer areacode, final HandleSuccess<AdverPosition> handleSuccess) {
        Call<AdverPosition> call = NetUtil.createMcApi(Common.myUrl).searchStandardFood(areacode);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告列表商讯(美食)
     *
     * @param handleSuccess
     */
    public static void searchByParamFood(Integer numPerPage, Integer pageNum, int status, Long position, String userId, Integer areaId, final HandleSuccess<AdvertisementListF> handleSuccess) {
        Call<AdvertisementListF> call = NetUtil.createMcApi(Common.myUrl).searchByParamFood(numPerPage, pageNum, status, position, userId, areaId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告列表会员商讯(美食)
     *
     * @param handleSuccess
     */
    public static void searchByParamFoodVip(Integer numPerPage, Integer pageNum, Long position, String userId, Integer areaId, final HandleSuccess<AdvertisementListF> handleSuccess) {
        Call<AdvertisementListF> call = NetUtil.createMcApi(Common.myUrl).searchByParamFoodVip(numPerPage, pageNum, position, userId, areaId);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户查广告列表拍一拍(美食)
     *
     * @param handleSuccess
     */
    public static void searchByParamFoodP(int status, Long position, String userId, Integer areaId, Long storeId, final HandleSuccess<AdvertisementListF> handleSuccess) {
        Call<AdvertisementListF> call = NetUtil.createMcApi(Common.myUrl).searchByParamFoodP(status, position, userId, areaId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 提交广告总举报数/举报数+1
     *
     * @param handleSuccess
     */
    public static void updateAdVo(Long id, Integer report, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateAdVo(id, report);
        call.enqueue(handleSuccess);
    }

    /**
     * 提交广告总展示数/展示数+1  商城广告
     *
     * @param handleSuccess
     */
    public static void updateAdVoShowCount(Long id, Integer showcount, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateAdVoShowCount(id, showcount);
        call.enqueue(handleSuccess);
    }

    /**
     * 提交广告总展示数/展示数+1 美食广告
     *
     * @param handleSuccess
     */
    public static void updateAdVoShowCountF(Long id, Integer showcount, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateAdVoShowCountF(id, showcount);
        call.enqueue(handleSuccess);
    }

    /**
     * 提交广告总点击数/点击数+1  商城广告
     *
     * @param handleSuccess
     */
    public static void updateAdVoCount(Long id, Integer count, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateAdVoCount(id, count);
        call.enqueue(handleSuccess);
    }

    /**
     * 提交广告总点击数/点击数+1   美食广告
     *
     * @param handleSuccess
     */
    public static void updateAdVoCountF(Long id, Integer count, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateAdVoCountF(id, count);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据WiFiID查询店铺信息
     *
     * @param handleSuccess
     */
    public static void findByWifiId(String wifiid, final HandleSuccess<StoreByWifi> handleSuccess) {
        Call<StoreByWifi> call = NetUtil.createMcApi(Common.myUrl).findByWifiId(wifiid);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据店铺ID查询美食店铺信息
     *
     * @param handleSuccess
     */
    public static void findFoodShopByshopId(Long storeId, final HandleSuccess<StoreInfo> handleSuccess) {
        Call<StoreInfo> call = NetUtil.createMcApi(Common.myUrl).findFoodShopByshopId(storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 根据店铺ID查询商城店铺信息
     *
     * @param handleSuccess
     */
    public static void findShopByshopId(Long storeId, final HandleSuccess<MallShopInfo> handleSuccess) {
        Call<MallShopInfo> call = NetUtil.createMcApi(Common.myUrl).findShopByshopId(storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 微信支付请求支付所需信息
     *
     * @param handleSuccess
     */
    public static void wxPay(String amount, String orderSn, String goodSn, String desc, String detail, final HandleSuccess<WXPayInfo> handleSuccess) {
        Call<WXPayInfo> call = NetUtil.createMcApi(Common.myUrl).wxPay(amount, orderSn, goodSn, desc, detail);
        call.enqueue(handleSuccess);
    }

    /**
     * 支付宝返回异步通知
     *
     * @param handleSuccess
     * @param paymentType   支付方式:1-支付宝2-微信3-余额
     */
    public static void notifyUrl(Integer type, Long id, Integer paymentType, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).notifyUrl(type, id, paymentType);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询关注商铺列表 不包括取关店铺
     *
     * @param handleSuccess
     */
    public static void leaguer(String memberId, int status,Integer numPerPage, Integer plainPageNum, final HandleSuccess<ConcernedList> handleSuccess) {
        Call<ConcernedList> call = NetUtil.createMcApi(Common.myUrl).leaguer(memberId, status,numPerPage,plainPageNum);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询关注商铺列表 包括取关店铺
     *
     * @param handleSuccess
     */
    public static void leaguer(String memberId, final HandleSuccess<ConcernedList> handleSuccess) {
        Call<ConcernedList> call = NetUtil.createMcApi(Common.myUrl).leaguer(memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询关注商铺列表 包括取关店铺 查用户关注商户状态用
     *
     * @param handleSuccess
     */
    public static void leaguer(String memberId, Long storeId, final HandleSuccess<ConcernedList> handleSuccess) {
        Call<ConcernedList> call = NetUtil.createMcApi(Common.myUrl).leaguer(memberId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 修改关注商家状态
     *
     * @param handleSuccess
     */
    public static void leaguerUpdate(Long id, Integer status, final HandleSuccess<UpdateLeaguer> handleSuccess) {
        Call<UpdateLeaguer> call = NetUtil.createMcApi(Common.myUrl).leaguerUpdate(id, status);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询用户在该商城商家的未付款订单//根据店铺id，用户id，订单状态查询订单列表（摇一摇功能）
     *
     * @param handleSuccess
     */
    public static void findOrderList(String memberId, Long storeId, int status, final HandleSuccess<YaoPayOrder> handleSuccess) {
        Call<YaoPayOrder> call = NetUtil.createMcApi(Common.myUrl).findOrderList(memberId, storeId, status);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询用户在该餐饮商家的未付款订单
     *
     * @param handleSuccess
     */
    public static void selectPayingOrdersFood(String memberId, Long storeId, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).selectPayingOrdersFood(memberId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询商家的拍一拍状态菜品
     *
     * @param handleSuccess
     */
    public static void findCookBook(Integer numPerPage, Integer pageNum, Long bussId, int ispat,int status, final HandleSuccess<FoodBean> handleSuccess) {
        Call<FoodBean> call = NetUtil.createMcApi(Common.myUrl).findCookBook(numPerPage, pageNum, bussId, ispat,status);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询商家的拍一拍状态商品
     *
     * @param handleSuccess
     */
    public static void findGoodsIsPat(RequestParamPYPBean bean, final HandleSuccess<GoodBean> handleSuccess) {
        Call<GoodBean> call = NetUtil.createMcApi(Common.myUrl).findGoodsIsPat(bean);
        call.enqueue(handleSuccess);
    }

    /**
     * 搜索附近全平台菜品
     *
     * @param handleSuccess
     */
    public static void findNearCookByName(Integer numPerPage, Integer pageNum, String goodsName, int distance, Double latitude, Double longitude, final HandleSuccess<SearchFood> handleSuccess) {
        Call<SearchFood> call = NetUtil.createMcApi(Common.myUrl).findNearCookByName(numPerPage, pageNum, goodsName, distance, latitude, longitude);
        call.enqueue(handleSuccess);
    }

    /**
     * 搜索附近全平台商品
     *
     * @param handleSuccess
     */
    public static void findNearGoodsByName(Integer numPerPage, Integer pageNum, String goodsName, int distance, Double latitude, Double longitude, final HandleSuccess<SearchGood> handleSuccess) {
        Call<SearchGood> call = NetUtil.createMcApi(Common.myUrl).findNearGoodsByName(numPerPage, pageNum, goodsName, distance, latitude, longitude);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户关注成为会员
     *
     * @param handleSuccess
     */
    public static void leaguerFor(Long shopId, String memberId, String name, Long region_id, String phone, String headpath, String birthday, int sex, final HandleSuccess<LeaguerAdd> handleSuccess) {
        Call<LeaguerAdd> call = NetUtil.createMcApi(Common.myUrl).leaguerFor(shopId, memberId, name, region_id, phone, headpath, birthday, sex, "未填写");
        call.enqueue(handleSuccess);
    }

    /**
     * 查询美食商家附加信息 送餐费等
     *
     * @param handleSuccess
     */
    public static void findStoreOperate(Long storeId, final HandleSuccess<FoodExtraInfo> handleSuccess) {
        Call<FoodExtraInfo> call = NetUtil.createMcApi(Common.myUrl).findStoreOperate(storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 查询商城商家附加信息 配送费等
     *
     * @param handleSuccess
     */
    public static void queryHdMallStoreOperate(Long shopId, final HandleSuccess<MallStoreExtraInfo> handleSuccess) {
        Call<MallStoreExtraInfo> call = NetUtil.createMcApi(Common.myUrl).queryHdMallStoreOperate(shopId);
        call.enqueue(handleSuccess);
    }

    /**
     * 请求商家所有蛋的类型(查优惠券)
     *
     * @param handleSuccess
     */
    public static void queryStoreCoupon(Long status, String memberId, Integer type,final HandleSuccess<CouponsBean> handleSuccess) {
        Call<CouponsBean> call = NetUtil.createMcApi(Common.myUrl).queryStoreCoupon(status, memberId,type);
        call.enqueue(handleSuccess);
    }
    /**
     * 请求商家所有蛋的类型(按类型查优惠券)
     *
     * @param handleSuccess
     */
    public static void queryStoreCouponByType(Integer status, String memberId,Long storeId,int type,final HandleSuccess<CouponsBean> handleSuccess) {
        Call<CouponsBean> call = NetUtil.createMcApi(Common.myUrl).queryStoreCouponByType(status, memberId,storeId,type);
        call.enqueue(handleSuccess);
    }

    /**
     * 将蛋纳入用户蛋库
     *
     * @param handleSuccess
     */
    public static void getEgg(String memberId, Long storeId, String storeName, int storeType, final HandleSuccess<EggBean> handleSuccess) {
        Call<EggBean> call = NetUtil.createMcApi(Common.myUrl).getEgg(memberId, storeId, storeName, storeType);
        call.enqueue(handleSuccess);
    }

    /**
     * 用户打开蛋，获取优惠券（概率获取）
     *
     * @param handleSuccess
     */
    public static void getStoreCoupon(Long id, Integer type, String memberId, Long storeId, final HandleSuccess<CounponBean> handleSuccess) {
        Call<CounponBean> call = NetUtil.createMcApi(Common.myUrl).getStoreCoupon(id, type, memberId, storeId);
        call.enqueue(handleSuccess);
    }

    /**
     * 蛋库查询
     *
     * @param handleSuccess
     */
    public static void queryEgg(String memberId, final HandleSuccess<MyEggBean> handleSuccess) {
        Call<MyEggBean> call = NetUtil.createMcApi(Common.myUrl).queryEgg(memberId);
        call.enqueue(handleSuccess);
    }

    /**
     * 拉卡拉请求charge对象
     *
     * @param handleSuccess
     */
    public static void getChartLKL(String body, String title, String channel, Long time_expire, Double totalPrice, String user_id, String order_no, final HandleSuccess<ChargeBean> handleSuccess) {
        Call<ChargeBean> call = NetUtil.createMcApi(Common.myUrl).getChartLKL(body, title, channel, time_expire, totalPrice, user_id, order_no);
        call.enqueue(handleSuccess);
    }
    /**
     * 余额支付
     *
     * @param handleSuccess
     */
    public static void balancePay(Long Id, Double balance,Long orderId,final HandleSuccess<BalancePayBean> handleSuccess) {
        Call<BalancePayBean> call = NetUtil.createMcApi(Common.myUrl).balancePay(Id,balance,orderId);
        call.enqueue(handleSuccess);
    }

    /**
     * 将蛋纳入用户蛋库
     *
     * @param handleSuccess
     */
    public static void storeMemRelation(String memid, Long storeid, int type, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).storeMemRelation(memid, storeid, type);
        call.enqueue(handleSuccess);
    }

    //王培   拉卡拉
    public static void delBank(Long roleId, final HandleSuccess<Addbank> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).delBank(roleId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Addbank bean = JSON.parseObject(response.body().string(), Addbank.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void findAccounts(int type, String roleId, final HandleSuccess<FindAccounts> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).findAccounts(type, roleId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FindAccounts bean = JSON.parseObject(response.body().string(), FindAccounts.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void findBanks(int roleType, String roleId, int numpage, int page, final HandleSuccess<FindBanks> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).findBanks(roleType, roleId, numpage, page);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FindBanks bean = JSON.parseObject(response.body().string(), FindBanks.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void addBank(int type, int roleType, String roleId, String person, String bank, String bankBranch,
                               String number, String province, Long bankCode, Long bankBranchCode
            , Long provinceCode, final HandleSuccess<Addbank> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).addBank(type, roleType, roleId, person,
                bank, bankBranch, number, province, bankCode, bankBranchCode, provinceCode);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Addbank bean = JSON.parseObject(response.body().string(), Addbank.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void findRecords(int userType, String userId, int numPerPage, int pageNum, final HandleSuccess<BankDetials> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).findRecords(userType, userId, numPerPage, pageNum);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    BankDetials bean = JSON.parseObject(response.body().string(), BankDetials.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void cashAccounting(String storeId, int type, Double payAmount, Long areaId, Long bankId, Double presentAmount, final HandleSuccess<BankDetials> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).cashAccounting(storeId, type, payAmount, areaId, bankId, presentAmount);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    BankDetials bean = JSON.parseObject(response.body().string(), BankDetials.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void findNoteBanks(String bank, final HandleSuccess<FindNoteBanks> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).findNoteBanks(bank);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FindNoteBanks bean = JSON.parseObject(response.body().string(), FindNoteBanks.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void findBankBranchs(String bank, int numPerPage, int pageNum, final HandleSuccess<FindNoteBanks> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).findBankBranchs(bank, numPerPage, pageNum);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FindNoteBanks bean = JSON.parseObject(response.body().string(), FindNoteBanks.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    public static void findProvinces(String bank, final HandleSuccess<FindNoteBanks> handleSuccess) {
        Call<ResponseBody> call = NetUtil.createMcApi(Common.myUrl).findProvinces(bank);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    FindNoteBanks bean = JSON.parseObject(response.body().string(), FindNoteBanks.class);
                    handleSuccess.success(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }

    /**
     * 添加打车需求
     *
     * @param handleSuccess
     */
    public static void addTaxiDemand(Integer type,String menberId,String startingPoint, String endPoint,Double startingLog,Double startingLng,Double endLog,
                                     Double endLng,String voicePath,final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).addTaxiDemand(type,menberId,startingPoint,endPoint,startingLog,startingLng,endLog,endLng,voicePath);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询附近5km是否有司机正在接单（首页是否展示出行导航）
     *
     * @param handleSuccess
     */
    public static void findDriver(Double startingLog,Double startingLng,final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).findDriver(startingLog,startingLng);
        call.enqueue(handleSuccess);
    }
    /**
     * 根据需求id查询打车需求 若传0查最新一条
     *
     * @param handleSuccess
     */
    public static void findByDemandId(String memberId,Long id,final HandleSuccess<TakeCarNeedBean> handleSuccess) {
        Call<TakeCarNeedBean> call = NetUtil.createMcApi(Common.myUrl).findByDemandId(memberId,id);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询打车需求列表
     *
     * @param handleSuccess
     */
    public static void findListTakeCarNeed(String memberId,Integer plainPageNum, Integer numPerPage,final HandleSuccess<TakeCarNeedListBean> handleSuccess) {
        Call<TakeCarNeedListBean> call = NetUtil.createMcApi(Common.myUrl).findListTakeCarNeed(memberId,plainPageNum,numPerPage);
        call.enqueue(handleSuccess);
    }

    /**
     * 更改需求状态（取消订单）
     *
     * @param handleSuccess
     */
    public static void updateTaxiDemand(Long id,Integer status, Integer travelState,Long driverId,final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).updateTaxiDemand(id,status,travelState,driverId);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询司机信息
     *
     * @param handleSuccess
     */
    public static void findDriverById(Long driverId,final HandleSuccess<DriverInfoBean> handleSuccess) {
        Call<DriverInfoBean> call = NetUtil.createMcApi(Common.myUrl).findDriverById(driverId);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询司机经纬度信息
     *
     * @param handleSuccess
     */
    public static void findPositionById(Long driverId,final HandleSuccess<DriverPosBean> handleSuccess) {
        Call<DriverPosBean> call = NetUtil.createMcApi(Common.myUrl).findPositionById(driverId);
        call.enqueue(handleSuccess);
    }
    /**
     * 评价司机
     *
     * @param handleSuccess
     */
    public static void evalutionAdd(Long taxiDemandId,Integer type,Float starRating,String contents,String menberId,Long driverId,final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).evalutionAdd(taxiDemandId,type,starRating,contents,menberId,driverId);
        call.enqueue(handleSuccess);
    }
    /**
     * 申请退款
     *
     * @param handleSuccess
     */
    public static void refundOrderAdd(String memberId, Long refundId, int orderType, BigDecimal refundAmount, String refundReason, final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).refundOrderAdd(memberId,refundId,orderType,refundAmount,refundReason);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询菜品打包费
     *
     * @param handleSuccess
     */
    public static void findPackageFee(String json,final HandleSuccess<Generic> handleSuccess) {
        Call<Generic> call = NetUtil.createMcApi(Common.myUrl).findPackageFee(json);
        call.enqueue(handleSuccess);
    }
    /**
     * 条件查询商城商品
     *
     * @param handleSuccess
     */
    public static void findBoutique(int plainPageNum,int numPerPage,Long storeId,String keyword,Long salescategoryId,Integer maxPrice,
                                    Integer salesVolume,HandleSuccess<DirectGoodBean> handleSuccess) {
        Call<DirectGoodBean> call = NetUtil.createMcApi(Common.myUrl).findBoutique(plainPageNum,numPerPage,storeId,keyword,salescategoryId,maxPrice,salesVolume);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询商城商品优惠策略列表
     *
     * @param handleSuccess
     */
    public static void findHdMallGoodsSaleCategoryList(int plainPageNum,int numPerPage,HandleSuccess<SaleCateBean> handleSuccess) {
        Call<SaleCateBean> call = NetUtil.createMcApi(Common.myUrl).findHdMallGoodsSaleCategoryList(plainPageNum, numPerPage);
        call.enqueue(handleSuccess);
    }
    /**
     * 查询附近特推商品列表
     *
     * @param handleSuccess
     */
    public static void findRecommendList(int pageNum,int numPerPage,Double longitude,Double latitude,int distance,HandleSuccess<NormalGoodsBean> handleSuccess) {
        Call<NormalGoodsBean> call = NetUtil.createMcApi(Common.myUrl).findRecommendList(pageNum, numPerPage,longitude,latitude,distance);
        call.enqueue(handleSuccess);
    }
}
