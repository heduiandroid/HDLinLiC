package com.linli.consumer.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hasee on 2016/12/1.
 */

public interface IIntrestBuy {

    /*
   首页查询附近商家
    */
    @FormUrlEncoded
    @POST("mall/mall/hdMall/findShopList")
    Call<ResponseBody> findShopList(@Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("distance")Integer distance,
                                    @Field("latitude")Double latitude,@Field("longitude")Double longitude);
    /*
    用户登录
     */
    @FormUrlEncoded
    @POST("member/mall/userLogin/userLogin")
    Call<ResponseBody> userLogin(@Field("password")String password,@Field("phone")String phone);
    /*
    找回密码
     */
    @FormUrlEncoded
    @POST("member/mall/userLogin/lost")
    Call<ResponseBody> lost(@Field("phoneCode")String phoneCode,@Field("ressPassWord")String ressPassWord,@Field("newPassWord")String newPassWord,
                            @Field("phone")String phone);
    /*
    图片语音上传返回Token
     */
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/getToken")
    Call<ResponseBody> getToken(@Field("fileName")String fileName);
    /*
    获取语音列表
     */
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findVoiceListByUserId")
    Call<ResponseBody> findVoiceListByUserId(@Field("numPerPage")Integer numPerPage
            , @Field("pageNum")Integer pageNum, @Field("memId")Long memId);
    /*
    用户注册获取验证码
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/phoneCode")
    Call<ResponseBody> phoneCode(@Field("type")Integer type,@Field("phone")String phone);
    /*
    修改绑定手机获取验证码
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/phoneCode")
    Call<ResponseBody> phoneCode(@Field("phone")String phone);

    /*
    注册
     */
    @FormUrlEncoded
    @POST("member/mall/userRegister/regist")
    Call<ResponseBody> regist(@Field("phone")String phone,@Field("newPassWord")String newPassWord,
            @Field("phoneCode")String phoneCode,@Field("tag")Integer tag,@Field("regionId")Integer regionId);
    /*
     钱包查询个人账户信息
   */
    @FormUrlEncoded
    @POST("member/mall/userCenter/queryUserAccout")
    Call<ResponseBody> queryUserAccout(@Field("id")Long id);
    /*
    添加修改支付密码
  */
    @FormUrlEncoded
    @POST("member/mall/userCenter/setupPayPassword")
    Call<ResponseBody> setupPayPassword(@Field("userId")Long userId,@Field("oldPassword")String oldPassword,@Field("newPassword")String newPassword);
    /*
    绑定修改银行卡
  */
    @FormUrlEncoded
    @POST("member/mall/userCenter/addBankCard")
    Call<ResponseBody> addBankCard(@Field("paypassword")String paypassword,@Field("bankname")String bankname,@Field("cardno")String cardno,
                                        @Field("accountname")String accountname,@Field("memId")Long memId);
    /*
    用户充值
  */
    @FormUrlEncoded
    @POST("member/mall/userCenter/launchRechargeTrans")
    Call<ResponseBody> launchRechargeTrans(@Field("price")Double price,@Field("memId")Long memId);
    /*
   充值结果
 */
    @FormUrlEncoded
    @POST("member/mall/userCenter/launchResult")
    Call<ResponseBody> launchResult(@Field("transId")String transId);
    /*
  用户提现
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/launchWithdrawTrans")
    Call<ResponseBody> launchWithdrawTrans(@Field("price")Double price,@Field("payPassword")String payPassword,@Field("owerId")Long owerId);
    /*
修改用户密码
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/userPasswordUpdate")
    Call<ResponseBody> userPasswordUpdate(@Field("phone")String phone,@Field("ressPassWord")String ressPassWord,@Field("newPassWord")String newPassword);

    /*
个人资料修改头像
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<ResponseBody> updateUseravatar(@Field("memId")Long id,@Field("avatar")String avatar);
    /*
个人资料修改生日
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<ResponseBody> updateUserbirthday(@Field("memId")Long id,@Field("birthday")String birthday);
    /*
个人资料昵称
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<ResponseBody> updateUsernickname(@Field("memId")Long id,@Field("nickname")String nickname);
    /*
个人资料修改性别
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<ResponseBody> updateUsersex(@Field("memId")Long id,@Field("sex")Integer sex);
    /*
个人资料修改绑定手机号
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUser")
    Call<ResponseBody> updateUserphone(@Field("memId")Long id,@Field("phone")String phone);
    /*
邮箱验证发送
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/sendEmail")
    Call<ResponseBody> sendEmail(@Field("email")String email);
    /*
收藏商品查询
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collectionGoods")
    Call<ResponseBody> collectionGoods(@Field("memId")Long memId);
    /*
商城入驻添加接口
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/addShopSettled")
    Call<ResponseBody> addShopSettled(@Field("name")String name,@Field("linkman")String linkman,@Field("regionId")Long regionId,
                                      @Field("phone")String phone,@Field("content")String content,@Field("type")Integer type);
    /*
用户订单查询
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectOrderList")
    Call<ResponseBody> selectOrderList(@Field("status")Integer status,@Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,
                                      @Field("type")Integer type,@Field("memId")Long memId);
    /*
收货地址管理查列表
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/selectListUserDistributAddress")
    Call<ResponseBody> selectListUserDistributAddress(@Field("memId")Long memId);
    /*
收货地址管理删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/deleteUserDistributAddress")
    Call<ResponseBody> deleteUserDistributAddress(@Field("id")Long id);
    /*
收货地址管理修改
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/updateUserDistributAddress")
    Call<ResponseBody> updateUserDistributAddress(@Field("type")Integer type,@Field("provinceId")Integer provinceId,@Field("cityId")Integer cityId,@Field("areaId")Integer areaId,
                                                  @Field("address")String address,@Field("phone")String phone,@Field("name")String name,@Field("id")Long id);
    /*
收货地址管理添加
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/addUserDistributAddress")
    Call<ResponseBody> addUserDistributAddress(@Field("type")Integer type,@Field("provinceId")Integer provinceId,@Field("cityId")Integer cityId,@Field("areaId")Integer areaId,
                                               @Field("address")String address,@Field("phone")String phone,@Field("name")String name,
                                               @Field("memberId")Long memberId);
    /*
收货地址管理设为默认
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/setUserDistributAddress")
    Call<ResponseBody> setUserDistributAddress(@Field("id")Long id,@Field("memberId")Long memberId);
    /*
添加用户意见反馈
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/addUserFeedback")
    Call<ResponseBody> addUserFeedback(@Field("qq")String qq,@Field("phone")String phone,@Field("phoneType")Integer phoneType,
                                       @Field("remark")String remark,@Field("memId")Long memId);
    /*
店铺收藏查询
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collectionShop")
    Call<ResponseBody> collectionShop(@Field("type")Integer type,@Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,
                                       @Field("memId")Long memId);
    /*
店铺收藏删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collShopDel")
    Call<ResponseBody> collShopDel( @Field("id")Long id);
    /*
商品收藏删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collGoodDel")
    Call<ResponseBody> collGoodDel( @Field("id")Long id);
    /*
根据订单id查询订单详情
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/orderDetail")
    Call<ResponseBody> orderDetail( @Field("type")Integer type,@Field("orderId")Long orderId);
    /*
美食店铺收藏删除
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/collFoodShopDel")
    Call<ResponseBody> collFoodShopDel( @Field("id")Long id);
    /*
根据用户id查询对应的用户关系---融云查头像用户名
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findshopuser")
    Call<ResponseBody> findshopuser( @Field("type")Integer type,@Field("id")String id);
    /*
根据语音id和商家id查商品list
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/findListByVoiceId")
    Call<ResponseBody> findListByVoiceId( @Field("pubvoiceId")Long pubvoiceId,@Field("storeId")Long storeId);
    /*
订单完成添加商家积分
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/addShopPoint")
    Call<ResponseBody> addShopPoint( @Field("type")Integer type,@Field("orderNo")String orderNo);
    /*
提醒卖家发货
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/remindShop")
    Call<ResponseBody> remindShop( @Field("orderNo")String orderNo,@Field("storeId")Long storeId);
    /*
获取支付宝所需信息
*/
    @FormUrlEncoded
    @POST("thirdparty/zhifubao/ZFBPay/zhifubaoPay")
    Call<ResponseBody> zhifubaoPay(@Field("id")Long id);
    /*
获取融云token
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/getRongToken")
    Call<ResponseBody> getRongToken( @Field("avatar")String avatar,@Field("nickName")String nickName,@Field("id")Long id);
    /*
添加语音记录（图片文字都可以）
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/addUserVoice")
    Call<ResponseBody> addUserVoice( @Field("type")Integer type,@Field("memId")Long memId,@Field("name")String name);
    /*
语音图片推送
*/
    @FormUrlEncoded
    @POST("member/mall/purchaseInterest/jpush")
    Call<ResponseBody> jpush( @Field("numPerPage")Integer numPerPage,@Field("pageNum")Integer pageNum,@Field("distance")Double distance,
                                     @Field("userVoiceId")Long userVoiceId,@Field("memberId")Long memberId,@Field("longitude")Double longitude,
                                     @Field("latitude")Double latitude);
    /*
用户投诉商家
*/
    @FormUrlEncoded
    @POST("mall/mall/hdPubVoice/addUserConplaint")
    Call<ResponseBody> addUserConplaint( @Field("memname")String memname,@Field("regionid")Long regionid,@Field("content")String content,
                              @Field("memid")Long memid,@Field("storename")String storename,@Field("storeid")Long storeid);
    /*
验证手机号和验证码是否正确
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/checkPhoneAndCode")
    Call<ResponseBody> checkPhoneAndCode(@Field("type")Integer type, @Field("phoneCode")String phoneCode,@Field("phone")String phone);
    /*
验证邮箱和验证码是否正确
*/
    @FormUrlEncoded
    @POST("member/mall/userCenter/checkPhoneAndCode")
    Call<ResponseBody> checkEmailAndCode(@Field("type")Integer type, @Field("phoneCode")String phoneCode,@Field("email")String email);
    /*
查询开通的省份
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findProviceList")
    Call<ResponseBody> findProviceList(@Field("parentId")Integer parentId);
    /*
根据省份id查询开通的城市
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findCityList")
    Call<ResponseBody> findCityList(@Field("parentId")Integer parentId);
    /*
根据城市id查询开通区域
*/
    @FormUrlEncoded
    @POST("member/mall/userRegister/findDistrictList")
    Call<ResponseBody> findDistrictList(@Field("parentId")Integer parentId);
}
