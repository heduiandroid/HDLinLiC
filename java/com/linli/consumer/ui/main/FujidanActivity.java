package com.linli.consumer.ui.main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.ConcernedList;
import com.linli.consumer.bean.CounponBean;
import com.linli.consumer.bean.EggBean;
import com.linli.consumer.bean.LeaguerAdd;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.StoreByWifi;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.bean.UpdateLeaguer;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FujidanActivity extends BaseActivity implements View.OnTouchListener {
    private ImageView iv_egg;//有蛋 无蛋 3种关闭的蛋 打开的蛋变换
    private ImageView iv_title;
    private TextView tv_tag;
    private ImageView iv_tem34,iv_tem35,iv_tem36,iv_tem37,iv_tem37d5;
    private AppContext appContext;
    private User user;
    private boolean isCanHatch = false;//是否可以进行孵化 默认不可孵化
    private RelativeLayout rl_bg_fujidan;
    private int countPosints = 0;//判断所有手指数量的点被松开
    private int fingerNum = 0;//判断手指数量
    private boolean touchAble = true;//可摸状态
    private boolean isShowingProgressBar = false;//是否正在显示进度条 默认没有
    private int TOTOAL_PROGRESS = 100;//可到达最大进度数量
    private boolean isProgressFulled = false;//进度条是否已满 默认没满
    private int clo = 0;//控制文字颜色
    Timer timer = new Timer();//控制文字颜色
    private TimerTask taskcc;//控制文字颜色
    private Long shopId = 0L;
    private int shopType;
    private String shopName = "";
    private Integer isActivity = null;
    private ArrayList<Integer> kindsEgg = new ArrayList<Integer>();//店铺拥有的蛋的种类 种类（1普通蛋2银蛋3金蛋4王八蛋5坏蛋6其他）
    private Integer kind = null;//（1普通蛋2银蛋3金蛋4王八蛋5坏蛋6其他）
    private Long eggId = null;//用户的蛋ID
    private Integer eggType = null;//用户的蛋真实类别

    private boolean isConcernShop = true;//是否关注店铺  默认关注
    //优惠券视图
    private LinearLayout ll_coupon_center;
    private TextView tv_shopname,tv_price,tv_yuan;//优惠券所属店铺，优惠金额，显示“元”还是“折”
    private View view_show_couponQRCode;//立即使用（展示优惠券二维码）
    private ImageView iv_concern_shop,iv_couponname;//关注店铺状态展示按钮，优惠券类型图片展示
    private Long couponId = null;
    private Long regionId;//商户区域ID
    private Long conceredId = 0l;//关注ID
    private boolean concerned = false;//是否是用户已关注商家（默认没有关注）
    private Integer status = null;//关注状态 0-正常  1-删除
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fujidan;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        tv_tag = findView(R.id.tv_tag);// 页面提示文字
        findViewClick(R.id.iv_back);
        findViewClick(R.id.iv_owneggs);
        rl_bg_fujidan = findView(R.id.rl_bg_fujidan);
        rl_bg_fujidan.setOnTouchListener(this);
        iv_egg = findView(R.id.iv_egg);
        iv_title = findView(R.id.iv_title);
        iv_tem34 = findView(R.id.iv_tem34);
        iv_tem35 = findView(R.id.iv_tem35);
        iv_tem36 = findView(R.id.iv_tem36);
        iv_tem37 = findView(R.id.iv_tem37);
        iv_tem37d5 = findView(R.id.iv_tem37d5);
        //优惠券视图
        ll_coupon_center = findView(R.id.ll_coupon_center);
        tv_shopname = findView(R.id.tv_shopname);
        tv_price = findView(R.id.tv_price);
        view_show_couponQRCode = findViewClick(R.id.view_show_couponQRCode);
        iv_concern_shop = findViewClick(R.id.iv_concern_shop);
        iv_couponname = findView(R.id.iv_couponname);
        tv_yuan = findView(R.id.tv_yuan);
    }



    @Override
    protected void initData() {
        dismiss();
        //根据WiFi ID查询店铺信息 如果查到店铺判断是否已经发过优惠券
        getShopIdByWifi();

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                countPosints++;
                fingerNum++;
                if (!isShowingProgressBar){
                    if (eggId != null){
                        tv_tag.setText("温度太少，试试多根手指盖住蛋壳吧~");
                    }
                }
                System.out.println("ACTION_DOWN计数："+countPosints);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                countPosints++;
                fingerNum++;
                if (touchAble && fingerNum > 2 && !isShowingProgressBar){////////////////fingerNum改为2
                    if (eggId != null){
                        isShowingProgressBar = true;
                        //显示进度条view
                        animatePbStart();
                    }else {
                        tv_tag.setText("当前还没有吉蛋哦~");
                    }
                }else {
                    if (!isShowingProgressBar){
                        if (eggId != null){
                            tv_tag.setText("温度太少，试试多根手指盖住蛋壳吧~");
                        }
                    }
                }
                System.out.println("ACTION_POINTER_DOWN计数："+countPosints);
                break;

        }
        return true;
    }
    /*
    用户打开蛋，获取优惠券（概率获取）
     */
    private void request_openegg_getcoupon() {
        rl_bg_fujidan.setOnTouchListener(null);//获取到优惠券，不让多点触控了
        IntrestBuyNet.getStoreCoupon(eggId, eggType, user.getId(), shopId, new HandleSuccess<CounponBean>() {//蛋ID、蛋类型需要服务器重新给的真实数据
            @Override
            public void success(CounponBean s) {
                slightShock(200);
                if (s.getStatus() == 0){
                    //设置蛋开了的图片，或者动画（动画可以做个延迟在执行下面弹出优惠券）
                    setOpenedEggImg();
                    //iv_title去掉 设为gone
                    iv_title.setVisibility(View.INVISIBLE);
                    //弹出已获得的优惠券信息
                    ll_coupon_center.setVisibility(View.VISIBLE);
                    tv_shopname.setText(shopName);
                    if (s.getData() != null){
                        couponId = s.getData().getId();
                        if (s.getData().getCouponSum() != null){
                            int price;
                            double rprice = s.getData().getCouponSum();
                            price = (int)rprice;
                            String finalPrice = price + "";
                            if (finalPrice.length()>2 && finalPrice.length() < 4){//字太多了 字体大小小一些
                                tv_price.setTextSize(30);
                            }else if (finalPrice.length() > 3){
                                tv_price.setTextSize(20);
                            }
                            tv_price.setText(finalPrice);
                        }

                        iv_concern_shop.setImageResource(R.mipmap.cb_checked);
                        switch (s.getData().getType()){//类型（1代金卷，2折扣卷，3满减卷，4免单卷）
                            case 1:
                                iv_couponname.setImageResource(R.mipmap.iv_xianjin);//代金券是纯粹减去多少钱 免单是有多少减多少
                                break;
                            case 2:
                                //“元”字改成“折”字
                                tv_yuan.setText("折");
                                iv_couponname.setImageResource(R.mipmap.iv_zhekou);
                                break;
                            case 3:
                                //“元”字改成没字
                                tv_yuan.setText("");
                                iv_couponname.setImageResource(R.mipmap.iv_manjian);
                                if (s.getData().getCouponMaxSum() != null && s.getData().getCouponSum() != null){
                                    double subMoney = s.getData().getCouponSum();
                                    int price;
                                    int jianMoney;
                                    double rprice = s.getData().getCouponMaxSum();
                                    price = (int)rprice;
                                    jianMoney = (int) subMoney;
                                    String maxPrice = price + "";
                                    String jianPrice = jianMoney + "";
                                    tv_price.setTextSize(20);//字太多了 字体大小小一些
                                    tv_price.setText("满"+maxPrice+"减"+jianPrice);
                                } else {
                                    tv_price.setText("满减券");
                                }
                                break;
                            case 4:
                                tv_yuan.setText("");
                                iv_couponname.setImageResource(R.mipmap.iv_miandan);
                                if (s.getData().getCouponSum() != null){
                                    int price;
                                    double rprice = s.getData().getCouponSum();
                                    price = (int)rprice;
                                    String maxPrice = price + "";
                                    tv_price.setTextSize(20);//字太多了 字体大小小一些
                                    tv_price.setText(maxPrice+"元以内全免");
                                }else {
                                    tv_price.setText("全免");
                                }
                                break;
                            default:
                                break;
                        }
                        if (isConcernShop){
                            if (status != null){//改状态即可
                                request_update_status(0);
                            }else {//正常添加会员即可
                                request_add_concern();
                            }
                        }
                    }else {
                        Toast.makeText(FujidanActivity.this,"获取优惠券时出错，请到个人中心我的优惠券中查看",Toast.LENGTH_LONG).show();
                    }
                }else {
                    //弹出或显示未获取到优惠券 再接再厉
                    refeshDataToLater();
                    setTemperatureGone();
                    setOpenedEggImg();
                    tv_tag.setText("原来是个空蛋，再接再厉吧~");
                    Toast.makeText(FujidanActivity.this,"原来是个空蛋，再接再厉吧~",Toast.LENGTH_LONG).show();
                }
                iv_title.setVisibility(View.INVISIBLE);
            }
        });
    }

    /*
       将温度图片隐藏
        */
    private void setTemperatureGone() {
        iv_tem34.setVisibility(View.GONE);
        iv_tem35.setVisibility(View.GONE);
        iv_tem36.setVisibility(View.GONE);
        iv_tem37.setVisibility(View.GONE);
        iv_tem37d5.setVisibility(View.GONE);
    }
    private void setOpenedEggImg(){
        switch (kind){
            case 1:
                iv_egg.setImageResource(R.mipmap.egg_normal_opened);
                break;
            case 2:
                iv_egg.setImageResource(R.mipmap.egg_silver_opened);
                break;
            case 3:
                iv_egg.setImageResource(R.mipmap.egg_golden_opened);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
    }
    /*
    恢复默认
     */
    private void refeshDataToLater() {
        touchAble = true;
        isProgressFulled = false;
        isShowingProgressBar = false;
        tv_tag.setText("叽叽~大家一起来孵蛋~");
    }

    private void animatePbStart() {

        new Thread() {
            public void run() {
                try {
                    int process = 0;
                    while (process < TOTOAL_PROGRESS) {
                        if (isShowingProgressBar){
                            process ++;
                            if (process > 29 && process < 50){//from 30 to 49
                                Thread.currentThread().sleep(30);
                                setTagText(process, "谁的手儿？好暖和！");
                            }else if (process > 49 && process < 80){//from 50 to 79
                                Thread.currentThread().sleep(40);
                                setTagText(process, "再来，再来点温度~");
                            }else if (process > 79 && process < 100){//from 80 to 99
                                Thread.currentThread().sleep(50);
                                setTagText(process,"再来，再来点温度~");
                            }else if (process > TOTOAL_PROGRESS-1){//==100
                                isProgressFulled = true;
                                setTagText(process, "温度够啦，松开看看吧！");
                                Log.i("test","开蛋");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        openEgg();
                                    }
                                });
                            }else {//from 0 to 30
                                Thread.currentThread().sleep(20);
                                setTagText(process, "谁的手儿？好暖和！");
                            }
                        }else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void openEgg() {
        countPosints--;
        fingerNum = 0;
        if (touchAble){
            touchAble = false;
            new CountDownTimer(3*1000, 1000){//三秒之后才可以再次摸一摸
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    touchAble = true;
                }
            }.start();
            if (isCanHatch){
                if (isProgressFulled){
                    Log.i("test","孵出一个鸡蛋");//已经孵出一个鸡蛋  是不是还有孵蛋的机会
                    refeshDataToLater();//已经孵出一个鸡蛋  是不是还有孵蛋的机会
                    //查优惠券 并显示弹窗
                    if (eggId != null && eggType != null){
                        // TODO: 2017/3/18 关键接口，根据蛋去服务器取优惠券  概率获得
                        request_openegg_getcoupon();
                    }else {
                        setTemperatureGone();
                        tv_tag.setText("当前还没有吉蛋哦~");
                    }
                }else {
                    refeshDataToLater();
                    setTemperatureGone();
                    if (eggId != null){
                        Toast.makeText(FujidanActivity.this,"不要太急哦，孵蛋需要点时间",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(FujidanActivity.this,"还没有吉蛋呢~快去店铺领取吧",Toast.LENGTH_LONG).show();
                    }
                }
            }else {
                refeshDataToLater();
                setTemperatureGone();
                if (isActivity != null){
                    if (isActivity == 0){
                        Toast.makeText(FujidanActivity.this,"当前店铺还没有开启活动哦~",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(FujidanActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private void setTagText(final int process, final String alert) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_tag.setText(alert);
                if (process == 5){
                    iv_tem34.setVisibility(View.VISIBLE);
                }
                if (process == 30){
                    iv_tem35.setVisibility(View.VISIBLE);
                }
                if (process == 50){
                    iv_tem36.setVisibility(View.VISIBLE);
                }
                if (process == 80){
                    iv_tem37.setVisibility(View.VISIBLE);
                }
                if (process == 100){
                    iv_tem37d5.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_owneggs://去我的蛋界面
                Intent iteggs = new Intent(FujidanActivity.this,AllMyEggsActivity.class);
                startActivityForResult(iteggs,773);
                break;
            case R.id.view_show_couponQRCode://去显示优惠券二维码界面

                Intent intent = new Intent(FujidanActivity.this,ShowQRCodeAcitivity.class);
                intent.putExtra("couponid",couponId);
                startActivity(intent);
                break;
            case R.id.iv_concern_shop:
                if (isConcernShop){
                    isConcernShop = false;
                    iv_concern_shop.setImageResource(R.mipmap.cb_unchecked);
                    request_update_status(1);
                }else if (!isConcernShop){
                    isConcernShop = true;
                    iv_concern_shop.setImageResource(R.mipmap.cb_checked);
                    request_update_status(0);
                }
                break;
            default:
                break;
        }
    }
    /*
    设为关注店铺 关注店铺
     */
    private void request_add_concern() {
        String name;
        int sex = 3;//1男 0女  3未知
        if (user.getRealName() != null){
            name = user.getRealName();
        }else if (user.getNickName() != null){
            name = user.getNickName();
        }else {
            name = user.getPhone();
        }
        if (user.getSex() != null){
            if (user.getSex().equals("男")){
                sex = 1;
            }else if (user.getSex().equals("女")){
                sex = 0;
            }
        }
        IntrestBuyNet.leaguerFor(shopId, user.getId(), name, regionId, user.getPhone(),user.getHeadPath()!=null?user.getHeadPath():" ",user.getBirthday()!=null?user.getBirthday():" ",sex, new HandleSuccess<LeaguerAdd>() {
            @Override
            public void success(LeaguerAdd leaguerAdd) {
            }
        });
    }
    private void request_update_status(int status) {
        IntrestBuyNet.leaguerUpdate(conceredId, status, new HandleSuccess<UpdateLeaguer>() {
            @Override
            public void success(UpdateLeaguer s) {
            }
        });
    }
    /**
     * 获取WiFi MAC地址后请求服务器给相应的shop
     */
    private void getShopIdByWifi(){
        //获取当前路由的MAC地址(服务端要根据这个地址去查ShopID)
        WifiManager mWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        String netMac = null;
        if (mWifi.isWifiEnabled()){
            WifiInfo wifiInfo = mWifi.getConnectionInfo();
            netMac = wifiInfo.getBSSID();
        }//获取到路由MAC地址 请求服务器给shopId
        if (netMac != null){
            Log.i("test",netMac);
            IntrestBuyNet.findByWifiId(netMac, new HandleSuccess<StoreByWifi>() {
                @Override
                public void success(StoreByWifi s) {
                    if (s.getStatus() == 1){
                        if (s.getData() != null){
                            StoreByWifi.DataBean.HdMallStoreBean mallStore = s.getData().getHdMallStore();
                            StoreByWifi.DataBean.HdFoodStoreBean foodStore = s.getData().getHdFoodStore();
                            if (mallStore != null){
                                shopId = mallStore.getId();
                                shopName = mallStore.getName();
                                regionId = mallStore.getRegionId();
                                shopType = mallStore.getCategoryType();
                                if (mallStore.getIsactivity()!= null){
                                    isActivity = mallStore.getIsactivity();
                                }
                                // 判断是否已经发过优惠券   当前已经发过-加个蛋   没发过-蛋没有
                                if (isActivity != null){
                                    if (isActivity == 1){//0是活动关闭  1是活动开启
                                        isCanHatch = true;
                                        //加个蛋
                                        request_addegg_to_user();
                                    }else {
                                        isActivity = 0;
                                        tv_tag.setText("该店铺还没有开启活动哦~");
                                    }
                                }else {
                                    isActivity = 0;
                                    tv_tag.setText("该店铺还没有开启活动哦~");
                                }
                            }else if (foodStore != null){
                                shopId = foodStore.getId();
                                shopName = foodStore.getName();
                                regionId = foodStore.getRegionId();
                                shopType = foodStore.getCategoryType();
                                if (foodStore.getIsactivity()!= null){
                                    isActivity = foodStore.getIsactivity();
                                }
                                // 判断是否已经发过优惠券   当前已经发过-加个蛋   没发过-蛋没有
                                if (isActivity != null){
                                    if (isActivity == 1){//0是活动关闭  1是活动开启
                                        isCanHatch = true;
                                        //加个蛋
                                        request_addegg_to_user();
                                    }else {
                                        isActivity = 0;
                                        tv_tag.setText("该店铺还没有开启活动哦~");
                                    }
                                }else {
                                    isActivity = 0;
                                    tv_tag.setText("该店铺还没有开启活动哦~");
                                }
                            }else {
                                tv_tag.setText("听说去店铺就可以领吉蛋啦！");
                                Toast.makeText(FujidanActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                            }
                            if(shopId != 0l){//开始查询关注状态
                                request_if_concerned();
                            }
                        }else {
                            tv_tag.setText("听说去店铺就可以领吉蛋啦！");
                            Toast.makeText(FujidanActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        tv_tag.setText("听说去店铺就可以领吉蛋啦！");
                        Toast.makeText(FujidanActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            tv_tag.setText("听说去店铺就可以领吉蛋啦！");
            Toast.makeText(FujidanActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
        }
    }

    /*
    将蛋纳入用户蛋库,随机显示一个类型的蛋在界面
     */
    private void request_addegg_to_user() {
        IntrestBuyNet.getEgg(user.getId(), shopId, shopName,shopType,new HandleSuccess<EggBean>() {
            @Override
            public void success(EggBean s) {
                if (s.getStatus() == 0){//缺少领取成功之后的蛋ID以及蛋类型等信息
                    if (s.getData() != null){
                        eggId = s.getData().getId();
                        eggType = s.getData().getType();
                        setEggTypeView(eggType, true);
                    }else {
                        tv_tag.setText("吉蛋在哪里呀！");
                        Toast.makeText(FujidanActivity.this,"叽叽~吉蛋好像被领光了~",Toast.LENGTH_LONG).show();
                    }
                }else if (s.getStatus() == 5){
                    tv_tag.setText("吉蛋在哪里呀！");
                    Toast.makeText(FujidanActivity.this,"已领过该商家的吉蛋~，改天再来吧",Toast.LENGTH_LONG).show();
                }else if (s.getStatus() == 6){
                    tv_tag.setText("已经领过啦！");
                    Toast.makeText(FujidanActivity.this,"已领过该商家优惠券~，马上使用吧",Toast.LENGTH_LONG).show();
                }else {
                    tv_tag.setText("吉蛋在哪里呀！");
                    Toast.makeText(FujidanActivity.this,"叽叽~吉蛋好像被领光了~",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void setEggTypeView(Integer eggType, boolean needToast){
        initFlashTagText();
        kind = eggType;
        iv_title.setVisibility(View.VISIBLE);   //显示中部文字
        String eggName = "";
        switch (kind){//（0普通蛋1银蛋2金蛋3王八蛋4坏蛋5其他）
            case 1:
                eggName = "吉蛋";
                iv_egg.setImageResource(R.mipmap.egg_normal_closed);//显示普通蛋图片
                Log.i("test 已随机到蛋类型为",eggName);
                break;
            case 2:
                eggName = "银蛋";
                iv_egg.setImageResource(R.mipmap.egg_silver_closed);//显示银蛋图片
                Log.i("test 已随机到蛋类型为",eggName);
                break;
            case 3:
                eggName = "金蛋";
                iv_egg.setImageResource(R.mipmap.egg_golden_closed);//显示金蛋图片
                Log.i("test 已随机到蛋类型为",eggName);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
        if (needToast){
            Toast.makeText(FujidanActivity.this,shopName+"送您"+eggName+"一枚",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 随机一个int型
     * @param i 随机0-i的数
     * @return 随机数int
     */
    private int getRandomNum(int i){
        Random random = new Random();
        return random.nextInt(i);
    }
    /*
    轻微震动一下
     */
    private void slightShock(long milliseconds) {
        Vibrator vib = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    private void initFlashTagText() {
        tv_tag.setText("触摸我的蛋壳，给点温度呗！");
        taskcc = new TimerTask(){
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (clo == 0) {
                            clo = 1;
                            tv_tag.setTextColor(getResources().getColor(R.color.brown)); // 最深
                        } else {
                            if (clo == 1) {
                                clo = 2;
                                tv_tag.setTextColor(getResources().getColor(R.color.brown));//较深
                            } else {
                                clo = 0;
                                tv_tag.setTextColor(getResources().getColor(R.color.brown));//浅色
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(taskcc, 1, 1000);
        // 参数分别是delay（多长时间后执行），duration（执行间隔）
}
    @Override
    protected void onResume() {
        touchAble = true;///////////////////////////////////////
        super.onResume();
    }
    /*
          查询用户是否关注了此商家
           */
    private void request_if_concerned() {
        IntrestBuyNet.leaguer(user.getId(),shopId, new HandleSuccess<ConcernedList>() {
            @Override
            public void success(ConcernedList s) {
                if (s.getStatus() == 1){
                    List<ConcernedList.DataBean> concernedShops = s.getData();
                    if (concernedShops != null && concernedShops.size() > 0){
                        for (int i = 0;i < concernedShops.size();i++){
                            long serverShopId = concernedShops.get(i).getStoreId();
                            if (serverShopId == shopId){
                                concerned = true;
                                conceredId = concernedShops.get(i).getId();//如果这个ID == shopId   isConcerned = true    break;
                                //如果已关注 并且状态正常
                                status = concernedShops.get(i).getStatus();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 773){
            if (data != null){
                eggId = data.getLongExtra("eggid",0l);
                eggType = data.getIntExtra("eggtype",0);
                shopId = data.getLongExtra("shopid",0l);
                int shopType = data.getIntExtra("shoptype",0);
                shopName = data.getStringExtra("shopname");
                isCanHatch = true;
                isActivity = 1;
                if (eggId == 0l || eggType == 0){
                    eggId = null;
                    eggType = null;
                }else {
                    setEggTypeView(eggType,false);
                }
                switch (shopType){
                    case 1://商城店铺
                        IntrestBuyNet.findShopByshopId(shopId, new HandleSuccess<MallShopInfo>() {
                            @Override
                            public void success(MallShopInfo s) {
                                if (s.getStatus() == 1){
                                    if (s.getData() != null){
                                        regionId = s.getData().getRegionId();
                                    }
                                }
                            }
                        });
                        break;
                    case 2://美食店铺
                        IntrestBuyNet.findFoodShopByshopId(shopId, new HandleSuccess<StoreInfo>() {
                            @Override
                            public void success(StoreInfo s) {
                                if (s.getStatus() == 1){
                                    if (s.getData() != null){
                                        regionId = s.getData().getRegionId();
                                    }
                                }
                            }
                        });
                        break;
                    case 0:
                        IntrestBuyNet.findShopByshopId(shopId, new HandleSuccess<MallShopInfo>() {
                            @Override
                            public void success(MallShopInfo s) {
                                if (s.getStatus() == 1){
                                    if (s.getData() != null){
                                        regionId = s.getData().getRegionId();
                                    }
                                }else {
                                    IntrestBuyNet.findFoodShopByshopId(shopId, new HandleSuccess<StoreInfo>() {
                                        @Override
                                        public void success(StoreInfo s) {
                                            if (s.getStatus() == 1){
                                                if (s.getData() != null){
                                                    regionId = s.getData().getRegionId();
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
