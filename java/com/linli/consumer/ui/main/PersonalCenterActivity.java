package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.bean.FindAccounts;
import com.linli.consumer.bean.Login;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;

import io.rong.imkit.RongIM;

public class PersonalCenterActivity extends BaseActivity {
    private RelativeLayout rl_background;
    private SimpleDraweeView iv_head_portrait;
//    private ImageView iv_a_shop_advertasiment,iv_rec_prod1,iv_rec_prod2,iv_rec_prod3;
    private TextView tv_couponcount,tv_integrationcount,tv_accountcount,tv_couponcount_redbag;//当前优惠券、积分、钱包余额的数量、红包数量（特殊优惠券-红包）
    private View line_shop_order,line_food_order,line_service_order;//用于显示商城订单餐饮订单服务订单底部是否已选线条
    private TextView tv_orders;
    private TextView tv_username;
    private AppContext appContext;
    private User user;
    private boolean isPersonalDataOuted = true;//是不是从个人修改信息里出来的
    private int cate = 3;//3商城  1订餐 2服务 ====默认显示商城3

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        tv_orders = findViewClick(R.id.tv_orders);
//        line_food_order = findView(R.id.line_food_order);
//        line_service_order = findView(R.id.line_service_order);
        setDefaultOrderType();
//        line_shop_order.setBackgroundColor(getResources().getColor(R.color.white));
        iv_head_portrait = findView(R.id.iv_head_portrait);
        tv_username = findView(R.id.tv_username);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewClick(R.id.ll_coupon);
        findViewClick(R.id.ll_integration);
        findViewClick(R.id.ll_update_data);
        findViewClick(R.id.ll_collections);
        findViewClick(R.id.ll_Ewallet);
        findViewClick(R.id.ll_shopcart);
        findViewClick(R.id.ll_suggestion);
        findViewClick(R.id.iv_setting);
        findViewClick(R.id.ll_join_us);
        findViewClick(R.id.ll_help);
        findViewClick(R.id.ll_coupon_redbag);//红包

        findViewClick(R.id.iv_share_download);//分享微信
//        iv_a_shop_advertasiment = findViewClick(R.id.iv_a_shop_advertasiment);
//        iv_rec_prod1 = findViewClick(R.id.iv_rec_prod1);
//        iv_rec_prod2 = findViewClick(R.id.iv_rec_prod2);
//        iv_rec_prod3 = findViewClick(R.id.iv_rec_prod3);
        tv_couponcount = findView(R.id.tv_couponcount);//当前优惠券数量
        tv_couponcount_redbag = findView(R.id.tv_couponcount_redbag);//当前红包（特殊优惠券）数量
        tv_integrationcount = findView(R.id.tv_integrationcount);//当前积分数量
        tv_accountcount = findView(R.id.tv_accountcount);//当前钱包余额
        findViewClick(R.id.ll_concerned);
        rl_background = findView(R.id.rl_background);
    }

    private void setDefaultOrderType() {

//        line_shop_order.setBackgroundColor(getResources().getColor(R.color.light_gray));
//        line_food_order.setBackgroundColor(getResources().getColor(R.color.light_gray));
//        line_service_order.setBackgroundColor(getResources().getColor(R.color.light_gray));

    }

    @Override
    protected void initData() {//获取优惠券数量 积分数 账户 订单数等

    }
    private void request_balance_datas() {
        IntrestBuyNet.findAccounts(4, user.getId(), new HandleSuccess<FindAccounts>() {
            @Override
            public void success(final FindAccounts findAccounts) {
                if (findAccounts.getStatus() == 1) {
                    switch (findAccounts.getData().get(0).getStatus()) {  // // 账户状态：0正常，1冻结，2危险
                        case 0:  //
                            EWalletActivity.EWBanlance  = findAccounts.getData().get(0).getBalance();
                            if (EWalletActivity.EWBanlance != null) {
                                tv_accountcount.setText("¥ "+EWalletActivity.EWBanlance);
                                if (EWalletActivity.EWBanlance == 0d){
                                    tv_accountcount.setText("¥ 0.00");
                                }
                            }
                            break;
                        case 1:
                            tv_accountcount.setText("被冻结");
                            break;
                        case 2:
                            tv_accountcount.setText("账户危险");
                            break;
                        default:
                            tv_accountcount.setText("不可用");
                            break;
                    }
                } else {
                    Toast.makeText(PersonalCenterActivity.this, "钱包信息获取失败，暂不可用", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void setUserDatas() {
        if(user.getNickName() != null){
            tv_username.setText(CommonUtil.secretPhone(user.getPhone()));
        }else {
            tv_username.setText(user.getNickName());
        }
        if (EWalletActivity.EWBanlance != null) {//账户余额显示
            tv_accountcount.setText("¥ "+EWalletActivity.EWBanlance);
            if (EWalletActivity.EWBanlance == 0d){
                tv_accountcount.setText("¥ 0.00");
            }
        }
        tv_integrationcount.setText(user.getTotalPoints().toString());//总积分数量显示
        request_coupons_size();
        request_balance_datas();
    }
    private void setHeadImage() {
        iv_head_portrait.setImageURI(user.getHeadPath());
    }
    @Override
    public void onHDClick(View v) {
        if (user != null){
            switch (v.getId()){
//                case R.id.tv_shop_order:
//                    setDefaultOrderType();
//                    cate = 3;//商城
//                    tv_shop_order.setTextColor(getResources().getColor(R.color.orange));
//                    line_shop_order.setBackgroundColor(getResources().getColor(R.color.white));
////                    request_xxx
//                    break;
//                case R.id.tv_food_order:
//                    setDefaultOrderType();
//                    cate = 1;//餐饮
//                    tv_food_order.setTextColor(getResources().getColor(R.color.orange));
//                    line_food_order.setBackgroundColor(getResources().getColor(R.color.white));
////                    request_xxx
//                    break;
//                case R.id.tv_service_order:
//                    setDefaultOrderType();
//                    cate = 4;//服务
//                    tv_service_order.setTextColor(getResources().getColor(R.color.orange));
//                    line_service_order.setBackgroundColor(getResources().getColor(R.color.white));
////                    request_xxx
//                    break;
                case R.id.ll_coupon:
                    startActivity(new Intent(PersonalCenterActivity.this,MyCouponActivity.class));
                    //去优惠券界面
                    break;
                case R.id.ll_coupon_redbag:
                    UIHelper.togoRedBagListActivity(this);
                    //去红包（特殊优惠券）界面
                    break;
                case R.id.ll_integration:
                    //去我的积分界面
                    startActivity(new Intent(PersonalCenterActivity.this,LevelsUserActivity.class));
                    break;
                case R.id.ll_update_data:
                    isPersonalDataOuted = true;
                    startActivityForResult(new Intent(PersonalCenterActivity.this, PersonalDataChooseActivity.class), 501);
                    break;
                case R.id.ll_collections:
                    startActivity(new Intent(PersonalCenterActivity.this,CollectionsActivity.class));
                    break;
                case R.id.tv_orders:
                    Intent itpaying = new Intent(PersonalCenterActivity.this,OrdersActivity.class);
//                    itpaying.putExtra("status",1);
//                    itpaying.putExtra("cate",cate);
                    startActivity(itpaying);
                    //待付款
                    break;
//                case R.id.ll_sending:
//                    //待收货（待提供）
//                    Intent itsending = new Intent(PersonalCenterActivity.this,OrdersActivity.class);
//                    itsending.putExtra("status",2);
//                    itsending.putExtra("cate",cate);
//                    startActivity(itsending);
//                    break;
//                case R.id.ll_receiving:
//                    //待收货（待接收）
//                    Intent itreceiving = new Intent(PersonalCenterActivity.this,OrdersActivity.class);
//                    itreceiving.putExtra("status",3);
//                    itreceiving.putExtra("cate",cate);
//                    startActivity(itreceiving);
//                    break;
//                case R.id.ll_finished:
//                    //已完成
//                    Intent itfinished= new Intent(PersonalCenterActivity.this,OrdersActivity.class);
//                    itfinished.putExtra("status",4);
//                    itfinished.putExtra("cate",cate);
//                    startActivity(itfinished);
//                    break;
//                case R.id.tv_all_order:
//                    Intent itall= new Intent(PersonalCenterActivity.this,ChooseOrderTypeActivity.class);
//                    itall.putExtra("status",0);
//                    startActivity(itall);
//                    break;
                case R.id.ll_Ewallet:
                    startActivity(new Intent(PersonalCenterActivity.this,EWalletActivity.class));
                    break;
                case R.id.ll_shopcart:
                    UIHelper.togoCartActivity(this);
                    break;
                case R.id.ll_suggestion:
                    startActivity(new Intent(PersonalCenterActivity.this,SuggestionActivity.class));
                    break;
                case R.id.iv_setting:
                    startActivityForResult(new Intent(PersonalCenterActivity.this,SettingActivity.class),601);
                    break;
                case R.id.ll_join_us:
                    startActivity(new Intent(PersonalCenterActivity.this,JoinUsActivity.class));
                    break;
                case R.id.ll_help:
                    startActivity(new Intent(PersonalCenterActivity.this,HelpActivity.class));
                    break;
                case R.id.ll_concerned:
                    startActivity(new Intent(PersonalCenterActivity.this,MyConcernedListActivity.class));
                    break;
                case R.id.iv_share_download:
                    UIHelper.togoShareChoices(this);
                    break;
                default:
                    break;
            }
        }else {
            switch (v.getId()){
                case R.id.iv_share_download:
                    UIHelper.togoShareChoices(this);
                    break;
                default:
                    startActivity(new Intent(PersonalCenterActivity.this,LoginYZXActivity.class));
                    break;
            }
//            switch (v.getId()){
//                case R.id.iv_a_shop_advertasiment:
//                    //去某个商家首页
//                    startActivity(new Intent(this, TestJsonActivity.class));
//                    break;
//                case R.id.iv_rec_prod1://去某个商品详情
//                    break;
//                case R.id.iv_rec_prod2://去某个商品详情
//                    break;
//                case R.id.iv_rec_prod3://去某个商品详情
//                    break;
//                default:
//                    startActivity(new Intent(PersonalCenterActivity.this,LoginYZXActivity.class));
//                    break;
//            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user != null){
            setUserDatas();
            if (isPersonalDataOuted) {//如果是从修改个人信息返回的
                setHeadImage();
                isPersonalDataOuted = false;
            }
        }

        dismiss();
    }
    /*
    获取优惠券数量(并提取出了拥有的可用红包的数量)
     */
    private void request_coupons_size() {
        //查优惠券数量
        IntrestBuyNet.queryStoreCoupon(1L, user.getId(), null,new HandleSuccess<CouponsBean>() {//type类型（1代金卷，2折扣卷，3满减卷，4免单卷, 5红包）
            @Override
            public void success(CouponsBean s) {
                if (s.getStatus() == 0){
                    if (s.getData() != null && s.getData().size() > 0){
                        int couponNum = 0;//优惠券数量
                        for (int i = 0;i<s.getData().size();i++){
                            if (s.getData().get(i).getType() == 5){
                            }else {
                                couponNum++;
                            }
                            tv_couponcount.setText(couponNum+"");
                        }
                    }
                }
            }
        });
        //查红包金额
        IntrestBuyNet.findUserInfo(user.getId(), new HandleSuccess<Login>() {
            @Override
            public void success(Login s) {
                if (s.getResponseCode().equals(CafeNet.SUCCESS)){
//                    if (s.getData().getRedpackageAccount() != null && s.getData().getRedpackageAccount() > 0d){
//                        //设置红包金额
//                        tv_couponcount_redbag.setText("￥"+s.getData().getRedpackageAccount());
//                    }else {
//                        //设置红包金额为0
//                        tv_couponcount_redbag.setText("￥0.00");
//                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 501){
//            RongIM.getInstance().disconnect();
            RongIM.getInstance().logout();
            setResult(101, new Intent().putExtra("exit", true));
            PersonalCenterActivity.this.finish();
        }
        if (resultCode == 601){
            if (data != null){
                SharedPreferences sp = getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                user = null;
                appContext.setUser(user);
//                RongIM.getInstance().disconnect();
                RongIM.getInstance().logout();
//                JPushInterface.stopPush(getApplicationContext());
//                            YZXIndexActivity.isIndexLogined = false;
                setResult(101, data);
                PersonalCenterActivity.this.finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
