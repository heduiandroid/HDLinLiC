package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.CollBean;
import com.linli.consumer.bean.FoodShopInfoBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.ShopHonorBean;
import com.linli.consumer.bean.ShopRegisterBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.QRDialog;
import com.squareup.picasso.Picasso;

import io.rong.imkit.RongIM;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/9.
 * 店铺详情页面
 */

public class ShopDetailSelfActivity extends BaseActivity {

    /**店铺信息**/
    private ImageView backIm;       //返回键
    private TextView nameTv;        //店铺名字
    private LinearLayout vipLl;     //会员按钮
    private TextView vipTv;         //vip提示
    private TextView onLineTv;          //货到付款
    private TextView offLineTv;         //线上支付
    private ImageView collection;   //收藏图片
    private LinearLayout ll_navigation;//店铺地址信息展示区域  点击可以进入百度导航
    private LinearLayout ll_concerned_num;
    private SimpleDraweeView avatarIm;       //头像
    private TextView scoreTv;               //积分
    private MaterialRatingBar ratingBar;
    private TextView likeTv;                //点赞数
    private TextView collectionTv;          //收藏数

    private TextView locationTv;         //位置
    private ImageView messageIm;         //发信息
    private ImageView callIm;           //打电话

    private RelativeLayout qrIm;        //二维码

    /**商誉**/
    private TextView licenseTv;         //证照齐全
    private TextView sincerityTv;       //诚信商家
    private TextView unsubscribeTv;     //随时退订
    private TextView timeoutFreeTv;     //超时免单
    private TextView limitServiceTv;    //限时送达
    private TextView parkingTv;         //免费泊车
    private TextView billTv;            //提供发票

    private SimpleDraweeView sdv_licence1,sdv_licence2;

    private LinearLayout supportLl;     //商家支持

    private String licence1,licence2;
    private long storeId;        //店铺id
    private int type;           //店铺类型 美食 & 商城

    private String linkPhone;       //联系人电话号
    private String registerPhone; //注册者电话号
    private String qrCode;      //二维码
    private String avatar;      //头像
    private long companyId;      //店铺拥有者id
    private String shopName;       //店铺名称
    private long regionId;          //区域码
    private AppContext appContext;
    private City city;
    private User user = AppContext.getInstance().getUser();
    private int isVip = 11;     //是否是会员的状态  11:以前不是vip，现在也不是  12:以前是vip,现在不是了  13:是vip，以前现在都是
    private long conceredId = 0l;
    private int collStatus = 12;     //店铺收藏状态
    private Double shopLon = null;//店铺经度
    private Double shopLat = null;//店铺纬度
    //添加收藏 1收藏成功 ;2已经收藏
    //查询收藏 11-已经收藏 12-没有收藏

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_detail_self;
    }

    @Override
    protected void initView() {
        backIm = findViewClick(R.id.shop_self_title_back_im);
        nameTv = findViewClick(R.id.shop_self_title_name_tv);
        vipLl = findViewClick(R.id.shop_self_title_vip_ll);
        collection = (ImageView)findViewById(R.id.shop_detail_title_collection_im);     //收藏图片
        vipTv = findView(R.id.shop_self_title_vip_tv);
        avatarIm = findView(R.id.shop_self_avatar_cim);
        scoreTv = findView(R.id.shop_self_score_tv);
        ratingBar = findView(R.id.shop_self_rb);
        likeTv = findView(R.id.shop_self_like_tv);
        collectionTv = findView(R.id.shop_self_colletion_tv);
        ll_navigation = findViewClick(R.id.ll_navigation);
        ll_concerned_num = findViewClick(R.id.ll_concerned_num);

        locationTv = findView(R.id.shop_self_location_tv);
        messageIm = findViewClick(R.id.shop_self_message_im);
        callIm = findViewClick(R.id.shop_self_call_im);
        qrIm = findViewClick(R.id.shop_self_qr_rl);

        /**
         * 商誉
         * */
        licenseTv = findView(R.id.shop_detail_self_license_tv);
        sincerityTv = findView(R.id.shop_detail_self_sincerity_tv);
        unsubscribeTv = findView(R.id.shop_detail_self_unsubscribe_tv);
        timeoutFreeTv = findView(R.id.shop_detail_self_timeoutFree_tv);
        limitServiceTv = findView(R.id.shop_detail_self_limitService_tv);
        parkingTv = findView(R.id.shop_detail_self_parking_tv);
        billTv = findView(R.id.shop_detail_self_bill_tv);
        sdv_licence1 = findViewClick(R.id.sdv_licence1);
        sdv_licence2 = findViewClick(R.id.sdv_licence2);

        onLineTv = findView(R.id.shop_self_online_tv);
        offLineTv = findView(R.id.shop_self_offline_tv);
    }

    @Override
    protected void initData() {
        city = appContext.getCity();
        Intent intent = getIntent();
        storeId = intent.getLongExtra("Sort",1);
        type = intent.getIntExtra("type",1);
        if (type == SERVICE_MAIN){//如果是服务类型店铺 不可以点击关注
            vipLl.setVisibility(View.INVISIBLE);
        }
        if (storeId == 999L){
            ll_concerned_num.setVisibility(View.GONE);
        }
        //获取点赞数
        ShopNet.findCommentSupport(storeId, type, new HandleSuccess<CollBean>() {
            @Override
            public void success(CollBean collBean) {
                likeTv.setText(collBean.getData()+"");
            }
        });
        //获取收藏数
        ShopNet.findmenStoreNum(storeId, new HandleSuccess<CollBean>() {
            @Override
            public void success(CollBean collBean) {
                collectionTv.setText(collBean.getData()+"");
            }
        });
        /**
         * 商誉查询
         * */
        ShopNet.findStoreHonor(storeId, new HandleSuccess<ShopHonorBean>() {
            @Override
            public void success(ShopHonorBean shopHonorBean) {
                if(shopHonorBean.getData() != null){

                    if(shopHonorBean.getData().getLicense() == 0 ){
                        licenseTv.setVisibility(View.GONE);
                    }
                    if(shopHonorBean.getData().getSincerity() == 0 ){
                        sincerityTv.setVisibility(View.GONE);
                    }
                    if(shopHonorBean.getData().getUnsubscribe() == 0 ){
                        unsubscribeTv.setVisibility(View.GONE);
                    }
                    if(shopHonorBean.getData().getTimeoutfree() == 0 ){
                        timeoutFreeTv.setVisibility(View.GONE);
                    }
                    if(shopHonorBean.getData().getLimitservice() == 0 ){
                        limitServiceTv.setVisibility(View.GONE);
                    }
                    if(shopHonorBean.getData().getParking() == 0 ){
                        parkingTv.setVisibility(View.GONE);
                    }
                    if(shopHonorBean.getData().getBill() == 0 ){
                        billTv.setVisibility(View.GONE);
                    }

                }
            }
        });

        /**
         * 查询店铺信息
         * */
        switch (type){
            case FOOD_MAIN:
                FoodNet.foodShopInfo(storeId, new HandleSuccess<FoodShopInfoBean>() {    //获取区域码
                    @Override
                    public void success(FoodShopInfoBean foodShopInfoBean) {
                        if(foodShopInfoBean.getData() != null){
                            nameTv.setText(foodShopInfoBean.getData().getName());
                            avatarIm.setImageURI(foodShopInfoBean.getData().getLogoImg());
                            scoreTv.setText("积分:"+foodShopInfoBean.getData().getIntegral());
                            ratingBar.setRating(foodShopInfoBean.getData().getCreditLevel());
                            locationTv.setText(foodShopInfoBean.getData().getAddress());
                            registerPhone = foodShopInfoBean.getData().getPhone();
                            linkPhone = foodShopInfoBean.getData().getMobilePhone();
                            qrCode = foodShopInfoBean.getData().getQrcode();
                            avatar = foodShopInfoBean.getData().getLogoImg();
                            companyId = foodShopInfoBean.getData().getCompanyMemberId();
                            shopName = foodShopInfoBean.getData().getName();
                            regionId = foodShopInfoBean.getData().getRegionId();
                            shopLon = foodShopInfoBean.getData().getLongitude();
                            shopLat = foodShopInfoBean.getData().getLatitude();

                            if(foodShopInfoBean.getData().getPaytype() == 0){  //货到付款 & 线上支付
                                onLineTv.setVisibility(View.GONE);
                            } else if(foodShopInfoBean.getData().getPaytype() == 1){
                                offLineTv.setVisibility(View.GONE);
                            }
                            requestBusinessLicense();
                        }
                    }
                });
                break;
            case SHOP_MAIN:
                ShopNet.shopInfo(storeId, new HandleSuccess<GoodsShopInfoBean>() {
                    @Override
                    public void success(GoodsShopInfoBean goodsShopInfoBean) {
                        if(goodsShopInfoBean.getData() != null){
                            nameTv.setText(goodsShopInfoBean.getData().getName());
                            avatarIm.setImageURI(goodsShopInfoBean.getData().getLogoImg());
                            scoreTv.setText("积分:"+goodsShopInfoBean.getData().getIntegral());
                            ratingBar.setRating(goodsShopInfoBean.getData().getCreditLevel());
                            locationTv.setText(goodsShopInfoBean.getData().getAddress());
                            registerPhone = goodsShopInfoBean.getData().getPhone();
                            linkPhone = goodsShopInfoBean.getData().getMobilePhone();
                            qrCode = goodsShopInfoBean.getData().getQrcode();
                            avatar = goodsShopInfoBean.getData().getLogoImg();
                            companyId = goodsShopInfoBean.getData().getCompanyMemberId();
                            shopName = goodsShopInfoBean.getData().getName();
                            regionId = goodsShopInfoBean.getData().getRegionId();
                            shopLon = goodsShopInfoBean.getData().getLongitude();
                            shopLat = goodsShopInfoBean.getData().getLatitude();

                            if(goodsShopInfoBean.getData().getPaytype() == 0){  //货到付款 & 线上支付
                                onLineTv.setVisibility(View.GONE);
                            } else if(goodsShopInfoBean.getData().getPaytype() == 1){
                                offLineTv.setVisibility(View.GONE);
                            }
                            requestBusinessLicense();
                        }
                    }
                });
                break;
            case SERVICE_MAIN:
                FoodNet.findServiceStoresInfos(storeId, new HandleSuccess<ServiceStoreBean>() {
                    @Override
                    public void success(ServiceStoreBean s) {
                        if(s.getData() != null){
                            nameTv.setText(s.getData().getName());
                            avatarIm.setImageURI(s.getData().getLogoImg());
                            scoreTv.setText("");
                            ratingBar.setRating(5f);
                            locationTv.setText(s.getData().getAddress());
                            registerPhone = s.getData().getPhone();
                            linkPhone = s.getData().getMobilePhone();
                            qrCode = s.getData().getQrcode();
                            avatar = s.getData().getLogoImg();
                            companyId = s.getData().getCompanyMemberId();
                            shopName = s.getData().getName();
                            regionId = s.getData().getRegionId();
                            shopLon = s.getData().getLongitude();
                            shopLat = s.getData().getLatitude();

                                onLineTv.setVisibility(View.GONE);//服务无购买行为 因为为gone
                                offLineTv.setVisibility(View.GONE);//服务无购买行为 因为为gone
                            requestBusinessLicense();
                        }
                    }
                });
                break;
        }

        if(user != null){
            //TODO 这里 storeID 写成同步方法比较好，在获取storeId再进行这个请求
            ShopNet.shopCollectionQuery(user.getId(), storeId, type, new HandleSuccess<CollBean>() {
                @Override
                public void success(CollBean collBean) {
                    if (collBean.getStatus() == 11) { //已收藏
                        collection.setImageResource(R.mipmap.ic_shop_detail_title_collection_orange1);
                        vipTv.setText("取消");
                        vipTv.setTextColor(getResources().getColor(R.color.color_shop_detail_num));
                    } else if (collBean.getStatus() == 12) { //没有收藏
                        collection.setImageResource(R.mipmap.ic_shop_detail_title_collection_black1);
                    }
                    collStatus = collBean.getStatus();
                }
            });
        }
        dismiss();
    }

    /**
     * 用手机号从商户注册表查询营业执照信息
     */
    private void requestBusinessLicense() {
        if (registerPhone == null || "".equals(registerPhone)){
            return;
        }
        ShopNet.findBusinessLicenses(registerPhone, new HandleSuccess<ShopRegisterBean>() {
            @Override
            public void success(ShopRegisterBean s) {
                if (s.getStatus() == 1 && s.getData() != null){
                    if (s.getData().getLicense() != null && !s.getData().getLicense().equals("")){
                        licence1 = s.getData().getLicense();
                        sdv_licence1.setImageURI(s.getData().getLicense()+ Common.MORESMALLERPICPARAM);
                    }else {
                        sdv_licence1.setVisibility(View.GONE);
                    }
                    if (s.getData().getOtherAptitude() != null && !s.getData().getOtherAptitude().equals("")){
                        licence2 = s.getData().getOtherAptitude();
                        sdv_licence2.setImageURI(s.getData().getOtherAptitude()+ Common.MORESMALLERPICPARAM);
                    }else {
                        sdv_licence2.setVisibility(View.GONE);
                    }
                }else {
                    sdv_licence1.setVisibility(View.GONE);
                    sdv_licence2.setVisibility(View.GONE);
                }
            }
        });
    }


    /**
     * 获取到数据后填充数据
     * */
    @Override
    protected void fillData() {
        if(!TextUtils.isEmpty("")){
            Picasso.with(this).load("").fit().into(avatarIm);
        }
        nameTv.setText("");
        ratingBar.setRating(5);
        scoreTv.setText("");
        likeTv.setText("");
        locationTv.setText("");
        offLineTv.setVisibility(View.VISIBLE);
        onLineTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.shop_self_title_name_tv:
            case R.id.shop_self_title_back_im:
                finish();
                break;
            case R.id.shop_self_title_vip_ll:
                onCollectionClick();
                break;
            case R.id.shop_self_message_im:                 //发信息
                if (user != null){      //对user信息判断
                    if(shopName != null){       //对店铺信息的判断
                        if (RongIM.getInstance() != null){
                            RongIM.getInstance().startPrivateChat(ShopDetailSelfActivity.this,companyId+"",shopName);
                        } else {
                            Toast.makeText(ShopDetailSelfActivity.this,"店铺信息为空",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            case R.id.shop_self_call_im:                    //打电话

                if(!TextUtils.isEmpty(linkPhone)){
                    UIHelper.callThePhoneNumber(this, linkPhone);
                } else {
                    Toast.makeText(ShopDetailSelfActivity.this,"该商家没有预留电话",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.shop_self_qr_rl:                  //查看二维码

                if(!TextUtils.isEmpty(qrCode)){
                    QRDialog dialog = new QRDialog(this,avatar,qrCode);
                    dialog.show();
                } else {
                    Toast.makeText(ShopDetailSelfActivity.this,"该商家没有设置二维码",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.sdv_licence1:
//                UIHelper.showThePicClearly(this,licence1+Common.WSMALLERPICPARAM400);
                break;
            case R.id.sdv_licence2:
//                UIHelper.showThePicClearly(this,licence2+Common.WSMALLERPICPARAM400);
                break;
            case R.id.ll_navigation://去第三方导航
                UIHelper.goToNavigationApps(ShopDetailSelfActivity.this,city.getLatitude()+"",city.getLongitude()+"",shopLat+"",shopLon+"");
                break;
            default:
                break;
        }
    }


    /**
     * 收藏按钮的点击事件
     * */
    private void onCollectionClick(){
        if(user != null){
            proDialog.show();
            //((BaseActivity)context).proDialog.show();
            if(type == SHOP_MAIN){      //商城收藏接口
                switch (collStatus){
                    case 11:        //已经收藏
                        ShopNet.shopCollectionDel(user.getId(), storeId, new HandleSuccess<CollBean>() {
                            @Override
                            public void success(CollBean collBean) {
                                dismissDialog(collBean);
                                collection.setImageResource(R.mipmap.ic_shop_detail_title_collection_black1);
                                collStatus = 12;
                                vipTv.setText("收藏");
                                vipTv.setTextColor(getResources().getColor(R.color.gray));
                                Toast.makeText(ShopDetailSelfActivity.this,"已取消收藏",Toast.LENGTH_SHORT).show();

                            }
                        });
                        break;
                    case 12:        //没有收藏
                        ShopNet.shopCollectionAdd(user.getId(), storeId, new HandleSuccess<CollBean>() {
                            @Override
                            public void success(CollBean collBean) {
                                dismissDialog(collBean);
                                collection.setImageResource(R.mipmap.ic_shop_detail_title_collection_orange1);
                                collStatus = 11;
                                vipTv.setText("取消");
                                vipTv.setTextColor(getResources().getColor(R.color.color_shop_detail_num));
                                Toast.makeText(ShopDetailSelfActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            } else if(type == FOOD_MAIN){       //美食收藏接口
                switch (collStatus){
                    case 11:        //已经收藏
                        ShopNet.foodCollectionDel(storeId, user.getId(), new HandleSuccess<CollBean>() {
                            @Override
                            public void success(CollBean collBean) {
                                dismissDialog(collBean);
                                collection.setImageResource(R.mipmap.ic_shop_detail_title_collection_black1);
                                collStatus = 12;
                                vipTv.setText("收藏");
                                vipTv.setTextColor(getResources().getColor(R.color.gray));
                                Toast.makeText(ShopDetailSelfActivity.this,"已取消收藏",Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 12:        //没有收藏
                        ShopNet.foodCollectionAdd(storeId, user.getId(), new HandleSuccess<CollBean>() {
                            @Override
                            public void success(CollBean collBean) {
                                dismissDialog(collBean);
                                collection.setImageResource(R.mipmap.ic_shop_detail_title_collection_orange1);
                                collStatus = 11;
                                vipTv.setText("取消");
                                vipTv.setTextColor(getResources().getColor(R.color.color_shop_detail_num));
                                Toast.makeText(ShopDetailSelfActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }

        }else {
            UIHelper.togoLoginActivity(ShopDetailSelfActivity.this);
        }
    }
    /**
     * dialog消失
     * */
    private void dismissDialog(CollBean collBean){
        if(collBean.getStatus() == 1 ){
            proDialog.dismiss();
        }
    }

    //11:以前不是vip，现在也不是  12:以前是vip,现在不是了  13:是vip，以前现在都是
    //TODO 这里storeId最好是Integer类型，在判断为空后再次请求数据，请求到后再执行下列请求
    private void onCollectionClick1(){
        if(user != null){

        } else {
            UIHelper.togoLoginActivity(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        user = AppContext.getInstance().getUser();
    }
}
