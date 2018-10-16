package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.BUserInfo;
import com.linli.consumer.bean.CUserInfo;
import com.linli.consumer.bean.DriverInfoBean;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.NearbyShopHomepage;
import com.linli.consumer.bean.TakeCarNeedBean;
import com.linli.consumer.bean.VersionInfo;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Shop;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.AnimationUtil;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.DistanceComparator;
import com.linli.consumer.utils.ExampleUtil;
import com.linli.consumer.utils.JsonParser;
import com.linli.consumer.utils.LoginHelper;
import com.linli.consumer.utils.LonLat;
import com.linli.consumer.utils.NetUtils;
import com.linli.consumer.utils.OnStateListener;
import com.linli.consumer.utils.RecordManger;
import com.linli.consumer.utils.TalkNetManager;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import retrofit2.Call;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class YZXIndexActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, RongIM.UserInfoProvider {
    private LocationClient locationClient = null;
    private BDAbstractLocationListener locationListener;
    private boolean needRefreshInBDLoc = true;//需要百度定位完成后，刷新首页数据，定位一次后设为false，防止一直刷新数据
    private LinearLayout ll_find,ll_message,ll_paiyipai,ll_mine;
    private LinearLayout ll_message1,ll_message2,ll_message3,ll_message4,ll_message5,ll_message6,ll_message7,ll_message8;
    private SimpleDraweeView iv_message1,iv_message2,iv_message3,iv_message4,iv_message5,iv_message6,iv_message7,iv_message8;
    private ImageView iv_egg1,iv_egg2,iv_egg3,iv_egg4,iv_egg5,iv_egg6,iv_egg7,iv_egg8;
    private TextView tv_message1,tv_message2,tv_message3,tv_message4,tv_message5,tv_message6,tv_message7,tv_message8;
    private ImageView iv_find,iv_mess,iv_paiyipai,iv_mine;
    private TextView tv_find,tv_mess,tv_paiyipai,tv_mine;
    private ImageView iv_message_have_new,iv_find_have_new;
    private TextView tv_message_have_new_rong;
    private TextView tv_area,tv_alert;
    private ImageView iv_SHT,iv_ling;
    private ImageView radar;
    private ImageView iv_area;
    private ProgressBar pb_location;

    private Animation scanningAnim,rollingAnim;
    private AppContext appContext;
    private User user;
    private City city;
    private String imageName;
    private String picPath = null;//图片路径
    private String username;
    private String password;

    private static final int REFRESH_COMPLETE = 0X110;
    private static final int NEWMESSAGE = 1;
    private static final int LOGOUT = 2;
    private SwipeRefreshLayout refreshLayout;//swipe refresh
    private String mytoken = null;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESH_COMPLETE:
//                    pageNo++;//现在只要一页数据？
                    isNoInternetFirstTime = false;
                    needRefreshInBDLoc = true;//需要刷新数据
                    distance = 1;
                    initBDMap();
//                    initDatas();
                    break;
                case NEWMESSAGE:
                    iv_message_have_new.setVisibility(View.VISIBLE);
                    break;
                case LOGOUT:
                    finishOthersAndAlert();
                    break;
                default:
                    break;
            }
        };
    };


    private String rongyunToken;
    private int cate = 0;//0-全部1-商城2-订餐3-服务 首页显示分类 默认0全部
    private SharedPreferences preferences;
    private int pageNo = 1;//首页数据分页页数 默认是1
    private boolean isWakeupAgain = false;//是否是点击了返回按钮回到了桌面 默认不是false
    private boolean isAttentionedToPaiyiPai = false;//是否提示过用户打开拍一拍
    private boolean isNotification;//判断用户是不是点通知进来的
    private boolean uploadAble = false;
    private RelativeLayout rl_recording;
    private TextView dialog_title;
    /** 显示录音车状态或报错图标 */
    private ImageView mic_icon;
    /** 显示计时器 */
    private TextView text_msg;
    private LinearLayout ll_ling;
    private boolean isRecording = true;
    public static boolean isHaveNewMessage = false;
    private boolean isCanceled = false;//是否主动取消语音录制 默认没有主动取消
    private boolean isTooShortTime = false;//是否时间太短  默认够时长
    private boolean isNoInternetFirstTime = false;//是否第一次进来APP就没网
    private LinearLayout ll_connect_shop_wifi_now;
    private ImageView ibtn_wifi;
    private ImageView civ_show_anim_again;
    //有动画效果
    private RecognizerDialog iatDialog;
    private SpeechRecognizer recognizer;
    private String result = null;//语音转文字结果
    private String audioName;//存入本地的语音名称
    private boolean moreWayToPublish,isVisibleActivity;//是否跳转到更多需求发送方式界面*****//是否正在当前页面

    private LinearLayout ll_take_car;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_yzxindex;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        abnormalLoginout();//检测到其他设备登录账号 当前账号自动退出登录

        isNotification = getIntent().getBooleanExtra("isNotification",false);//如果是点通知进来的
//        getNearByCate();//获取上次最后选择的商互通总类
        checkInternetAndLogin();

        scanningAnim = AnimationUtils.loadAnimation(this, R.anim.scanning);
        LinearInterpolator lin1 = new LinearInterpolator();
        scanningAnim.setInterpolator(lin1);

        rollingAnim = AnimationUtils.loadAnimation(this, R.anim.rolling);
        LinearInterpolator lin2 = new LinearInterpolator();
        rollingAnim.setInterpolator(lin2);
//        setMyCity();

        initRongIMUnReadCountlistener();
        checkLatestVersion();
//        showConnectShopWifiAnim(1);
        recognizer = SpeechRecognizer.createRecognizer(YZXIndexActivity.this,null);
    }

    @Override
    public void onHDClick(View v) {

    }

    private void checkInternetAndLogin() {
        if (NetUtils.checkInternet(YZXIndexActivity.this) == false){
            RongIM.setUserInfoProvider(this, true);
            registerMessageReceiver();
            LoginHelper.doLoginSilently(YZXIndexActivity.this);
            initBDMap();//定位完成后会请求一次首页数据
        }else {
            isNoInternetFirstTime = true;//没有
            if (!isAttentionedToPaiyiPai){
                isAttentionedToPaiyiPai = true;
//                startActivity(new Intent(YZXIndexActivity.this,NoInternetIfPaiPaiActivity.class));
            }else {
                Toast.makeText(this,"当前无网络",Toast.LENGTH_SHORT).show();
            }
            //显示缓存数据
            showCacheDatas(); //没网也显示
        }
    }
//    private void setSharedPreNowTime() {
//        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
//        jptime = preferences.getString("jptime", null);
//        if (jptime == null){
//            SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
//            Date date = new Date(System.currentTimeMillis());
//            jptime = format.format(date);
//            preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor=preferences.edit();
//            editor.putString("jptime", jptime);
//            editor.commit();
//        }
//    }

    private void initBDMap() {
        locationClient = new LocationClient(this);
        locationListener = new MyLocationListener();
        locationClient.registerLocationListener(locationListener);
        LocationClientOption option=new LocationClientOption();
        option.setOpenGps(false);// 打开GPS
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
//        option.setScanSpan(10000);// 设置发起定位请求的间隔时间为10000ms 10秒请求一次够了
        option.disableCache(false);// 禁止启用缓存定位
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 同时使用网络定位和GPS定位，优先返回最高精度的定位结果
        locationClient.setLocOption(option);// 使用设置
        locationClient.start();// 开启定位SDK
        locationClient.requestLocation();// 开始请求位置
    }

    private void initDatas() {
        if (NetUtils.checkInternet(this)==false){
            request_datas_index();//request datas
        }else {
            Toast.makeText(this,"无网路",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 为用户设置别名用于推送消息
     */
    private void setAlias() {
        if (user != null){
            String alias = user.getId().toString();
            if (TextUtils.isEmpty(alias)){
                Log.i("alias为空:",alias);
                return;
            }
            if (!ExampleUtil.isValidTagAndAlias(alias)){
                Log.i("alias格式错误:",alias);
                return;
            }
            jPushHandler.sendMessage(jPushHandler.obtainMessage(MSG_SET_ALIAS,alias));
        }
    }
    public static final int MSG_SET_ALIAS = 1001;
    public static final int MSG_SET_TAGS = 1002;
    private final Handler jPushHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d("JPush", "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;

                case MSG_SET_TAGS:
                    Log.d("JPush", "Set tags in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
                    Log.i("JPush", "Unhandled msg - " + msg.what);
            }
        }
    };
    private void setMyCity() {
        //设置定位的城市/////////////////////////////////////////////////////////
        appContext = (AppContext) getApplicationContext();
        City locatecity = new City();
        locatecity.setName("北京");
        locatecity.setArea("西城区");
        locatecity.setLongitude(116.13742432171215);
        locatecity.setLatitude(39.71571430646427);
        appContext.setCity(locatecity);
        city = appContext.getCity();
        tv_area.setText(city.getStreet());
    }



//
    private void init() {
        civ_show_anim_again = (ImageView) findViewById(R.id.civ_show_anim_again);
        ll_connect_shop_wifi_now = (LinearLayout) findViewById(R.id.ll_connect_shop_wifi_now);
        ibtn_wifi = (ImageView) findViewById(R.id.ibtn_wifi);
        iv_area = (ImageView) findViewById(R.id.iv_area);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_alert = (TextView) findViewById(R.id.tv_alert);
        radar = (ImageView) findViewById(R.id.radar);
        pb_location = (ProgressBar) findViewById(R.id.pb_location);
        iv_message_have_new = (ImageView) findViewById(R.id.iv_message_have_new);
        tv_message_have_new_rong = (TextView) findViewById(R.id.tv_message_have_new_rong);
        iv_find_have_new = (ImageView) findViewById(R.id.iv_find_have_new);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_index_datas);
        refreshLayout.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        refreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        refreshLayout.setProgressViewEndTarget(true, 100);
        refreshLayout.setOnRefreshListener(this);
        ll_ling = (LinearLayout) findViewById(R.id.ll_ling);
        buttonListener listener = new buttonListener();
        ll_ling.setOnTouchListener(listener);
        ll_ling.setOnClickListener(listener);
        iv_area.setOnClickListener(listener);
        tv_area.setOnClickListener(listener);//remember to set the district area
        findViewById(R.id.ll_shopping_direct).setOnClickListener(listener);
        findViewById(R.id.ibtn_scaningbuy).setOnClickListener(listener);
        findViewById(R.id.ll_shopping).setOnClickListener(listener);
        findViewById(R.id.ll_orderfood).setOnClickListener(listener);
        findViewById(R.id.ll_services).setOnClickListener(listener);
        ll_take_car = (LinearLayout) findViewById(R.id.ll_take_car);//新加出行导航
        ll_take_car.setOnClickListener(listener);
        civ_show_anim_again.setOnClickListener(listener);
        ll_connect_shop_wifi_now.setOnClickListener(listener);
        ibtn_wifi.setOnClickListener(listener);
        iv_SHT = (ImageView) findViewById(R.id.iv_SHT);
        iv_SHT.setOnClickListener(listener);
        iv_ling = (ImageView) findViewById(R.id.iv_ling);
        ll_find = (LinearLayout) findViewById(R.id.ll_find);
        ll_find.setOnClickListener(listener);
        ll_find.setOnTouchListener(listener);
        ll_message = (LinearLayout) findViewById(R.id.ll_message);
        ll_message.setOnClickListener(listener);
        ll_message.setOnTouchListener(listener);
        ll_paiyipai = (LinearLayout) findViewById(R.id.ll_paiyipai);
        ll_paiyipai.setOnClickListener(listener);
        ll_paiyipai.setOnTouchListener(listener);
        ll_mine = (LinearLayout) findViewById(R.id.ll_mine);
        ll_mine.setOnClickListener(listener);
        ll_mine.setOnTouchListener(listener);
        iv_find = (ImageView) findViewById(R.id.iv_find);
        iv_mess = (ImageView) findViewById(R.id.iv_mess);
        iv_paiyipai = (ImageView) findViewById(R.id.iv_paiyipai);
        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        tv_find = (TextView) findViewById(R.id.tv_find);
        tv_mess = (TextView) findViewById(R.id.tv_mess);
        tv_paiyipai = (TextView) findViewById(R.id.tv_paiyipai);
        tv_mine = (TextView) findViewById(R.id.tv_mine);
        rl_recording = (RelativeLayout) findViewById(R.id.rl_recording);
        dialog_title = (TextView) findViewById(R.id.title);
        text_msg = (TextView) findViewById(R.id.msg);
        mic_icon = (ImageView) findViewById(R.id.mic);

//        progressImg[0] = this.getResources().getDrawable(R.mipmap.mic_1);//初始化振幅图片
//        progressImg[1] = this.getResources().getDrawable(R.mipmap.mic_2);//初始化振幅图片
//        progressImg[2] = this.getResources().getDrawable(R.mipmap.mic_3);//初始化振幅图片
//        progressImg[3] = this.getResources().getDrawable(R.mipmap.mic_4);//初始化振幅图片
//        progressImg[4] = this.getResources().getDrawable(R.mipmap.mic_5);//初始化振幅图片
//        progressImg[5] = this.getResources().getDrawable(R.mipmap.mic_6);//初始化振幅图片
//        progressImg[6] = this.getResources().getDrawable(R.mipmap.mic_7);//初始化振幅图片
        progress = (ImageView) findViewById(R.id.sound_progress);//振幅进度条
        ll_message1 = (LinearLayout) findViewById(R.id.ll_message1);
        ll_message2 = (LinearLayout) findViewById(R.id.ll_message2);
        ll_message3 = (LinearLayout) findViewById(R.id.ll_message3);
        ll_message4 = (LinearLayout) findViewById(R.id.ll_message4);
        ll_message5 = (LinearLayout) findViewById(R.id.ll_message5);
        ll_message6 = (LinearLayout) findViewById(R.id.ll_message6);
        ll_message7 = (LinearLayout) findViewById(R.id.ll_message7);
        ll_message8 = (LinearLayout) findViewById(R.id.ll_message8);

        iv_message1 = (SimpleDraweeView) findViewById(R.id.iv_message1);
        iv_message1.setOnClickListener(listener);
        iv_message2 = (SimpleDraweeView) findViewById(R.id.iv_message2);
        iv_message2.setOnClickListener(listener);
        iv_message3 = (SimpleDraweeView) findViewById(R.id.iv_message3);
        iv_message3.setOnClickListener(listener);
        iv_message4 = (SimpleDraweeView) findViewById(R.id.iv_message4);
        iv_message4.setOnClickListener(listener);
        iv_message5 = (SimpleDraweeView) findViewById(R.id.iv_message5);
        iv_message5.setOnClickListener(listener);
        iv_message6 = (SimpleDraweeView) findViewById(R.id.iv_message6);
        iv_message6 .setOnClickListener(listener);
        iv_message7 = (SimpleDraweeView) findViewById(R.id.iv_message7);
        iv_message7.setOnClickListener(listener);
        iv_message8 = (SimpleDraweeView) findViewById(R.id.iv_message8);
        iv_message8.setOnClickListener(listener);
        tv_message1 = (TextView) findViewById(R.id.tv_message1);
        tv_message1.setOnClickListener(listener);
        tv_message2 = (TextView) findViewById(R.id.tv_message2);
        tv_message2.setOnClickListener(listener);
        tv_message3 = (TextView) findViewById(R.id.tv_message3);
        tv_message3.setOnClickListener(listener);
        tv_message4 = (TextView) findViewById(R.id.tv_message4);
        tv_message4.setOnClickListener(listener);
        tv_message5 = (TextView) findViewById(R.id.tv_message5);
        tv_message5.setOnClickListener(listener);
        tv_message6 = (TextView) findViewById(R.id.tv_message6);
        tv_message6.setOnClickListener(listener);
        tv_message7= (TextView) findViewById(R.id.tv_message7);
        tv_message7.setOnClickListener(listener);
        tv_message8 = (TextView) findViewById(R.id.tv_message8);
        tv_message8.setOnClickListener(listener);
        iv_egg1 = (ImageView) findViewById(R.id.iv_egg1);
        iv_egg2 = (ImageView) findViewById(R.id.iv_egg2);
        iv_egg3 = (ImageView) findViewById(R.id.iv_egg3);
        iv_egg4 = (ImageView) findViewById(R.id.iv_egg4);
        iv_egg5 = (ImageView) findViewById(R.id.iv_egg5);
        iv_egg6 = (ImageView) findViewById(R.id.iv_egg6);
        iv_egg7 = (ImageView) findViewById(R.id.iv_egg7);
        iv_egg8 = (ImageView) findViewById(R.id.iv_egg8);
        String JPushKey = ExampleUtil.getAppKey(getApplicationContext());
        if (null == JPushKey) JPushKey = "JPushKey异常";
        Log.i("JPushKey:",JPushKey);
    }
    class buttonListener implements View.OnClickListener,View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float downy = 0;//当前按钮按下坐标，控制上划取消录音的上传
            switch (v.getId()) {
                case R.id.ll_find:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
                        iv_find.setImageResource(R.mipmap.icon_find_down);
                        tv_find.setTextColor(getResources().getColor(R.color.orange));
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){//松开时
                        iv_find.setImageResource(R.mipmap.icon_find);
                        tv_find.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.ll_message:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
                        iv_mess.setImageResource(R.mipmap.icon_message_down);
                        tv_mess.setTextColor(getResources().getColor(R.color.orange));
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){//松开时
                        iv_mess.setImageResource(R.mipmap.icon_message);
                        tv_mess.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.ll_paiyipai:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
                        iv_paiyipai.setImageResource(R.mipmap.icon_pyp_down);
                        tv_paiyipai.setTextColor(getResources().getColor(R.color.orange));
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){//松开时
                        iv_paiyipai.setImageResource(R.mipmap.icon_pyp);
                        tv_paiyipai.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.ll_mine:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
                        iv_mine.setImageResource(R.mipmap.icon_mine_down);
                        tv_mine.setTextColor(getResources().getColor(R.color.orange));
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){//松开时
                        iv_mine.setImageResource(R.mipmap.icon_mine);
                        tv_mine.setTextColor(getResources().getColor(R.color.gray));
                    }
                    break;
                case R.id.ll_ling:
                    if (event.getAction() == MotionEvent.ACTION_DOWN){//按下时
                        getLatestUserInfo();
                        if (user != null) {
                            downy = event.getY();
                            mytoken = null;
                            moreWayToPublish = true;
                            new CountDownTimer(1 * 1000, 1000) {//
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    if (isVisibleActivity && moreWayToPublish){//如果是当前页面并且是刚刚按下按钮
                                        moreWayToPublish = false;
                                        playSound();
                                        uploadAble = true;
                                        request_upload_token();
                                        startRecordByXunFei();
                                        countTime = 30;//重置录音时间
                                        handler.post(run);
                                    }
                                }
                            }.start();
                        } else {
                            UIHelper.togoLoginActivity(YZXIndexActivity.this);
                        }
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){//松开时
                        if (user != null) {
                            if (isCanceled){
                                try {
                                    iatDialog.dismiss();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }else if (countTime > 28 && !moreWayToPublish) {
                                try {
                                    iatDialog.dismiss();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                Toast.makeText(YZXIndexActivity.this, "录音时间太短", Toast.LENGTH_SHORT).show();
                            }else {
                                if (moreWayToPublish) {
                                    try {
                                        iatDialog.dismiss();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(new Intent(YZXIndexActivity.this, V2VoicePhotoBuyActivity.class));
                                }
                            }
//                            if (!moreWayToPublish){
//
//                                if (countTime > 26) {
//                                    Toast.makeText(YZXIndexActivity.this, "录音时间太短", Toast.LENGTH_SHORT).show();
//                                }
//                            }else if (!isCanceled){
//                                if (moreWayToPublish){
//                                    try {
//                                        iatDialog.dismiss();
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }
//                                    startActivity(new Intent(YZXIndexActivity.this, V2VoicePhotoBuyActivity.class));
//                                }else {
//                                    iatDialog.setParameter(SpeechConstant.VAD_EOS, "0");// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音0~10000
//                                    Toast.makeText(YZXIndexActivity.this,"正在发送需求",Toast.LENGTH_SHORT).show();
//                                }
//                            }
                            stopTime();
                            isCanceled = false;
                            moreWayToPublish = false;
                        }
                    }
                    if (event.getAction() == MotionEvent.ACTION_CANCEL){
                        try {
                            iatDialog.dismiss();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        countTime = 30;
                        stopTime();
                    }
                    if (event.getAction() == MotionEvent.ACTION_MOVE){
                        if (uploadAble){
                            if (downy - event.getY() > 50){
                                if (!isCanceled){
                                    Toast.makeText(YZXIndexActivity.this,"松开取消发送",Toast.LENGTH_LONG).show();
                                }
                                isCanceled = true;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
        private void request_upload_token() {
            IntrestBuyNet.getToken(null, new HandleSuccess<Generic>() {
                @Override
                public void success(Generic generic) {
                    if (generic.getStatus() == 1){
                        mytoken = generic.getData().toString();
                    }else {
                        Toast.makeText(YZXIndexActivity.this,generic.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {
            if (city != null){
                switch (v.getId()){
                    case R.id.ll_message:
                        getLatestUserInfo();
                        if (user != null){
                            if (RongIM.getInstance() != null){
                                RongIM.getInstance().startConversationList(YZXIndexActivity.this);
                            }

                        }else {
                            UIHelper.togoLoginActivity(YZXIndexActivity.this);
                        }
                        break;
                    case R.id.ll_find:
                        iv_find_have_new.setVisibility(View.GONE);
                        startActivity(new Intent(YZXIndexActivity.this,FindOutActivity.class));
                        break;
                    case R.id.ll_paiyipai:
                        startActivity(new Intent(YZXIndexActivity.this,PaiYiPaiActivity.class));
                        break;
                    case R.id.ll_mine:
                        Intent intent = new Intent();
                        intent.setClass(YZXIndexActivity.this,PersonalCenterActivity.class);
                        startActivityForResult(intent,101);
                        break;
                    case R.id.tv_area:
                    case R.id.iv_area:
                        startActivityForResult(new Intent(YZXIndexActivity.this,ChangeAreaActivity.class),301);
                        break;
                    case R.id.ibtn_scaningbuy:
                        startActivity(new Intent(YZXIndexActivity.this,ScanningBuyActivity.class));
                        break;
                    case R.id.ll_shopping_direct:
                        UIHelper.togoDirectShopActivity(YZXIndexActivity.this);
//                        UIHelper.togoShopDetailActivity(YZXIndexActivity.this,999L,"精选专区",SHOP_MAIN);//线上正在用//跳转直营店铺（精选专区）
                        break;
                    case R.id.ll_shopping:
                        UIHelper.togoShopActivity(YZXIndexActivity.this,SHOP_MAIN);
                        break;
                    case R.id.ll_orderfood:
                        UIHelper.togoShopActivity(YZXIndexActivity.this,FOOD_MAIN);
                        break;
                    case R.id.ll_services://TODO
//                        Toast.makeText(YZXIndexActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
                        UIHelper.togoShopActivity(YZXIndexActivity.this,SERVICE_MAIN);
                        break;
                    case R.id.ll_take_car://TODO 去出行首页
                       gotoTakeCarActivities();
                        break;
                    case R.id.iv_SHT:
                        startActivityForResult(new Intent(YZXIndexActivity.this, SHTMenuActivity.class), 100);
                        break;
                    case R.id.iv_message1:
                    case R.id.tv_message1:
                        //go to shop homepage or foodshop homepage or services homepage or call someone
                        if (1<=shops.size()){
                            gotoOneHomepage(shops.get(0).getCategory(),shops.get(0).getId(),shops.get(0).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message2:
                    case R.id.tv_message2:
                        if (2<=shops.size()){
                            gotoOneHomepage(shops.get(1).getCategory(),shops.get(1).getId(),shops.get(1).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message3:
                    case R.id.tv_message3:
                        if (3<=shops.size()){
                            gotoOneHomepage(shops.get(2).getCategory(),shops.get(2).getId(),shops.get(2).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message4:
                    case R.id.tv_message4:
                        if (4<=shops.size()){
                            gotoOneHomepage(shops.get(3).getCategory(),shops.get(3).getId(),shops.get(3).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message5:
                    case R.id.tv_message5:
                        if (5<=shops.size()){
                            gotoOneHomepage(shops.get(4).getCategory(),shops.get(4).getId(),shops.get(4).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message6:
                    case R.id.tv_message6:
                        if (6<=shops.size()){
                            gotoOneHomepage(shops.get(5).getCategory(),shops.get(5).getId(),shops.get(5).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message7:
                    case R.id.tv_message7:
                        if (7<=shops.size()){
                            gotoOneHomepage(shops.get(6).getCategory(),shops.get(6).getId(),shops.get(6).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.iv_message8:
                    case R.id.tv_message8:
                        if (8<=shops.size()){
                            gotoOneHomepage(shops.get(7).getCategory(),shops.get(7).getId(),shops.get(7).getShopName());//商户类型、商户ID
                        }else {
                            Toast.makeText(YZXIndexActivity.this,"网络环境较差，刷新试试吧。",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.civ_show_anim_again:
//                        showConnectShopWifiAnim(0);
                        break;
                    case R.id.ibtn_wifi:
                        //去某个界面连接WiFi
                        gotoConnectBluetoothAndWifi();
                        break;
                    default:
                        break;
                }
            }else {
                if (v.getId() == R.id.civ_show_anim_again){
//                    showConnectShopWifiAnim(0);
                }else if (v.getId() == R.id.ibtn_wifi){
                    //去某个界面连接WiFi
                    gotoConnectBluetoothAndWifi();
                }else {
                    isNoInternetFirstTime = false;
                    needRefreshInBDLoc = true;//需要刷新数据
                    initBDMap();
                    Toast.makeText(YZXIndexActivity.this, "请稍后，正在重定位", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    /*
    月光宝盒连接WiFi逻辑
     */
    private void gotoConnectBluetoothAndWifi() {
        startActivity(new Intent(YZXIndexActivity.this,BluetoothConnWifiActivity.class));
    }
    private void gotoOneHomepage(int type,long id ,String shopname) {
        switch (type){
            case 1:
                UIHelper.togoShopDetailActivity(YZXIndexActivity.this,id,shopname,SHOP_MAIN);
                break;
            case 2:
                UIHelper.togoShopDetailActivity(YZXIndexActivity.this,id,shopname,FOOD_MAIN);
                break;
            case 3:
                UIHelper.togoShopDetailActivity(YZXIndexActivity.this,id,shopname,SERVICE_MAIN);
                break;
            default:
                break;
        }

    }

    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private int numPerPage = 300;//每页需要几条数据
    private int minNumPerPage = 8;//每页至少需要几条数据 少于这个数需要增加搜索范围
    private int maxDistance = 3;//支持的最大所有范围
    private int distance = 1;//查询附近多少距离的数据 （单位：千米） 首页查询数据逻辑特殊：先查1千米范围 若1千米范围商家不够再查询更大范围的数据
    private void request_datas_index() {
        if (scanningAnim != null){
            radar.startAnimation(scanningAnim);
        }
        if (rollingAnim != null){
            iv_SHT.startAnimation(rollingAnim);
        }
        shops.clear();
        if(city != null ) {
            IntrestBuyNet.findShopList(cate,numPerPage, pageNo, distance, city.getLatitude(), city.getLongitude(), new HandleSuccess<NearbyShopHomepage>() {
                @Override
                public void onFailure(Call<NearbyShopHomepage> call, Throwable t) {
                    refreshLayout.setRefreshing(false);
                }
                @Override
                public void success(NearbyShopHomepage s) {
                    if (s.getStatus() == 1){
                        if (s.getData() == null || "null".equals(s.getData())){
                            if (pageNo != 1) {//当前页不是第一页并且已经没有数据了，后面页码就更不可能有数据了--需重新翻回第一页再次请求数据
                                pageNo = 1;
                                initDatas();
                            }else {
                                Toast.makeText(YZXIndexActivity.this,"附近还没有商家哦",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (s.getData().size() < minNumPerPage && distance < maxDistance){//如果获取到的数据不够想要的数量并且距离没有扩大到最大范围
                                distance++;//扩大一千米范围
                                Log.i("test","距离扩大到"+distance+"千米");
                                request_datas_index();//重新请求数据
                                return;//停止继续往下执行*此时考虑何时将数据重新更改当前获取数据范围重新更改为1千米*
                            }
                            showShopsByDistance(s.getData());//显示首页商家数据
                            if (s.getData().size() > 0){
                                CommonUtil.setCacheDatas(YZXIndexActivity.this,"cacheShops",com.alibaba.fastjson.JSONObject.toJSON(s).toString());//存储缓存数据
                            }
                        }
                    }else {
                        Toast.makeText(YZXIndexActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                    refreshLayout.setRefreshing(false);
                    checkIfShowTakeCar();
                }
            });
        }
    }
    private void checkIfShowTakeCar() {//是否显示打车模块
        IntrestBuyNet.findDriver(city.getLongitude(), city.getLatitude(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 3){
                    ll_take_car.setVisibility(View.VISIBLE);
                }else {
                    ll_take_car.setVisibility(View.GONE);
                }
            }
        });
    }

    /*
    展示和隐藏连接店铺WiFi按钮
     */
    private void showConnectShopWifiAnim(int second) {
        //左侧划出一个可连WiFi提示语
        new CountDownTimer(second * 1000, 1000) {//一秒之后在显示动画
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                civ_show_anim_again.setVisibility(View.GONE);
                ll_connect_shop_wifi_now.setVisibility(View.VISIBLE);
                ll_connect_shop_wifi_now.setAnimation(AnimationUtil.moveFromLeftToView());
            }
        }.start();
        new CountDownTimer(6 * 1000, 1000) {//五秒之后隐藏
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                ll_connect_shop_wifi_now.setVisibility(View.GONE);
                ll_connect_shop_wifi_now.setAnimation(AnimationUtil.moveFromViewToLeft());
                new CountDownTimer(1 * 1000, 1000) {//一秒之后在显示图片
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        civ_show_anim_again.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        }.start();
    }

    private void setImageToLogo() {
        iv_message1.setImageResource(R.mipmap.default_indexshop);
        iv_message2.setImageResource(R.mipmap.default_indexshop);
        iv_message3.setImageResource(R.mipmap.default_indexshop);
        iv_message4.setImageResource(R.mipmap.default_indexshop);
        iv_message5.setImageResource(R.mipmap.default_indexshop);
        iv_message6.setImageResource(R.mipmap.default_indexshop);
        iv_message7.setImageResource(R.mipmap.default_indexshop);
        iv_message8.setImageResource(R.mipmap.default_indexshop);
        ll_message1.setVisibility(View.INVISIBLE);
        ll_message2.setVisibility(View.INVISIBLE);
        ll_message3.setVisibility(View.INVISIBLE);
        ll_message4.setVisibility(View.INVISIBLE);
        ll_message5.setVisibility(View.INVISIBLE);
        ll_message6.setVisibility(View.INVISIBLE);
        ll_message7.setVisibility(View.INVISIBLE);
        ll_message8.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        isVisibleActivity = true;
        if (city != null){
            tv_area.setText(city.getStreet());
        }else {
            initBDMap();
        }
        if (user == null){
            getLatestUserInfo();
            if (user != null){
                setAlias();
            }
        }
        getLatestUserInfo();
        if (isWakeupAgain == true){//重新唤醒首页刷新数据
            needRefreshInBDLoc = true;
            isWakeupAgain = false;//一唤醒首页，重新变为不是被第二次唤醒首页状态
            initBDMap();
        }

        super.onResume();
    }

    @Override
    protected void onStop() {
        isVisibleActivity = false;
//        isRecording = true;
//        isCanceled = false;//是否主动取消语音录制 默认没有主动取消
//        isTooShortTime = false;//是否时间太短  默认够时长
//        rl_recording.setVisibility(View.GONE);
//        stopTime();
//        try {
//            talk.stopRecord();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        stopListener();
        super.onPause();
    }



    private void takeANewPicture() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        imageName = df.format(calendar.getTime());
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("imageName", imageName);
        editor.commit();
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), Common.IMAGE_FILE_NAME + imageName + ".jpg")));
            startActivityForResult(intentFromCapture, Common.CODE_CAMERA_REQUEST);
        } else {
            Toast.makeText(getApplication(), "检测到您没有SD卡", Toast.LENGTH_SHORT).show();
        }
    }
    //判断SD卡是否可用
    private boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }else {
            return false;
        }
    }

    private boolean isCalling = false;//是否是打电话
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 100){
            if (data != null){
                //reset index product datas
                pageNo = 1;//切换了商铺类型 需要重置页数
                cate = data.getIntExtra("cate",0);
//                if (cate != 4){//不是调通讯录
//                    preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=preferences.edit();
//                    editor.putInt("nearbycate", cate);
//                    editor.commit();
                    initDatas();
//                }else {//调通讯列表
////                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.ContactsContract.Contacts.CONTENT_URI);
////                    isCalling = true;
////                    startActivityForResult(intent,301);
//                    if (city != null){
//                        if (shops.size()>0){
//                            Intent itshops = new Intent(YZXIndexActivity.this,CallingIndexActivity.class);
//                            itshops.putExtra("shops", (Serializable) shops);
//                            startActivity(itshops);
//                        }else {
//                            Toast.makeText(YZXIndexActivity.this,"没有搜索到附近商家",Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        checkInternetAndLogin();
//                    }
//
//                }

            }
        }else if (resultCode == RESULT_CANCELED) {
            System.out.println("没有拍摄照片");
            return;
        }else if(resultCode == 101){//exit Application
            if (data.getBooleanExtra("exit",false)){
                appContext = (AppContext) getApplicationContext();
                user = appContext.getUser();
                JPushInterface.setAliasAndTags(getApplicationContext(), "", null, mAliasCallback);
                Toast.makeText(YZXIndexActivity.this,"已退出登录",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(YZXIndexActivity.this,PersonalCenterActivity.class);
                startActivityForResult(intent, 101);
            }
        }else if (resultCode == 201){
            if (data != null){
                pageNo = 1;//切换了城市区域 需重置页数
//                city = appContext.getCity();?
//                Toast.makeText(YZXIndexActivity.this,city.getLongitude()+" "+city.getLatitude(),Toast.LENGTH_LONG).show();
//                tv_area.setText(data.getStringExtra("area"));
                initDatas();
            }else {
                //重新定位后进入SelectCityActivity
                checkInternetAndLogin();
//                initBDMap();
            }
        }else if (resultCode == 102){
            if (data!=null){
                if (data.getIntExtra("ling",-1) == 1){
                    takeANewPicture();
                }
            }
        }else if (resultCode == 301){
            appContext = (AppContext) getApplicationContext();
            city = appContext.getCity();
            tv_area.setText(city.getStreet());
            pageNo = 1;
            initDatas();
        }
//        else if (isCalling){
//            if (resultCode == RESULT_OK){
//                ContentResolver resolver = getContentResolver();
//                Uri contactData = data.getData();
//                Cursor cursor = managedQuery(contactData,null,null,null,null);
//                cursor.moveToFirst();
//                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                Cursor phoneNum = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
//                        null,
//                        null);
//                while (phoneNum.moveToNext()) {
//                    String callNum = phoneNum.getString(phoneNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    Intent call = new Intent(Intent.ACTION_DIAL);
//                    Uri uri = Uri.parse("tel:"+callNum);
//                    call.setData(uri);
//                    startActivity(call);
//                }
//            }
//        }
        switch (requestCode) {
//            case Common.CODE_GALLERY_REQUEST:
//                Uri uri = data.getData();//获取到选择的图片的路径
//                try {
//                    String[] pojo={MediaStore.Images.Media.DATA};
//                    Cursor cursor = managedQuery(uri, pojo, null, null, null);
//                    if (cursor!=null) {
//                        ContentResolver cr=this.getContentResolver();
//                        int colunm_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                        cursor.moveToFirst();
//                        String path=cursor.getString(colunm_index);
//                        if (path.endsWith("jpg")||path.endsWith("png")||path.endsWith("jpeg")) {
//                            picPath=path;
//                        }else {
//                            picPath=null;
//                            Toast.makeText(YZXIndexActivity.this, "选择了无效照片", Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        picPath=null;
//                        Toast.makeText(YZXIndexActivity.this, "选择了无效照片", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                cropRawPhoto(data.getData());
//                break;
            case Common.CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                    imageName = preferences.getString("imageName", null);
                    if (imageName != null){
                        String localPath = Environment.getExternalStorageDirectory()+"/"+ Common.IMAGE_FILE_NAME+imageName+".jpg";
                        Intent intent = new Intent(YZXIndexActivity.this,CropPhotoActivity.class);
                        intent.putExtra("path",localPath);
                        startActivity(intent);
                    }else {
                        Toast.makeText(YZXIndexActivity.this,"未能获取到照片，请重试",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplication(), "没有SD卡", Toast.LENGTH_SHORT).show();
                }
                break;
//            case Common.CODE_RESULT_REQUEST:
//                if (data!=null) {
//                    setImageServerNeed(data);
//
//                }
//                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessage(REFRESH_COMPLETE);
    }


    // （Application中已经做过初始化）初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void initJPush(){
        JPushInterface.init(getApplicationContext());
    }
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.hd.hdappyzx.MESSAGE_RECEIVED_ACTION";//已在MyReceiver设置了此Action，这里便可接收
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }
    /** 控制录音和上传 */
    private TalkNetManager talk = null;
    private int countTime = 30;
    /** 显示录音振幅 */
    private ImageView progress;
    /** 显示录音振幅的图片缓存 */
    private Drawable[] progressImg = new Drawable[7];
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            countTime--;
            if (countTime < 0){
                handler.removeCallbacks(run);
                return;
            }
            handler.postDelayed(run,1000);
        }
    };
    private void stopTime() {
        handler.removeCallbacks(run);// 移除计时器
//        text_msg.setText("");
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
//                StringBuilder showMsg = new StringBuilder();
//                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                if (!ExampleUtil.isEmpty(extras)) {
//                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                }
                setCostomMsg(messge);
            }
        }
    }
    private void setCostomMsg(String msg){
        try {
            JSONObject result = new JSONObject(msg);
            int type = result.getInt("type");//推送类别
            switch (type){
                case 8:
                    UIHelper.togoRedBagDialogActivity(this, BigDecimal.valueOf(result.getDouble("coupon")).toString(),0);//收到红包的弹窗通知
                    break;
                case 9://司机已接单 正赶往乘客
                case 10://司机已经接到乘客 正赶往目的地
                    showSimpleDialog();
                    UIHelper.togoTakeCarWaitDriverAcceptPassive(YZXIndexActivity.this);//如果打车页面存在不进行处理；如果打车页面不存在则打开页面
                    dismissSimDialog();
                    break;
                case 11://乘客已经到达目的地 去评价页面
                    UIHelper.togoEvaluateDriver(YZXIndexActivity.this);
                    break;
                case 12://司机取消了订单 如果还开着页面应该迫使其关闭
                    UIHelper.cancelTravel(YZXIndexActivity.this);
                    break;
                case 13://商家反推商品的消息
                    iv_message_have_new.setVisibility(View.VISIBLE);
                    break;
                case 14://收到退款通知
                    showToast("您申请的退款已原路退还");
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (user != null || city != null){
            unregisterReceiver(mMessageReceiver);
            RongIM.getInstance().logout();
        }
        Log.i("Ondestroy","执行了结束应用");
//        JPushInterface.stopPush(getApplicationContext());
        super.onDestroy();
    }
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("JPush", logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i("JPush", logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        jPushHandler.sendMessageDelayed(jPushHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i("JPush", "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e("JPush", logs);
            }

//            ExampleUtil.showToast(logs, getApplicationContext());
        }

    };
    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("JPush", logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i("JPush", logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        jPushHandler.sendMessageDelayed(jPushHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        Log.i("JPush", "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e("JPush", logs);
            }

//            ExampleUtil.showToast(logs, getApplicationContext());
        }

    };
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.i("test",location.toString());
            if (location!=null || location.getCity() != null) {
//                StringBuffer sb=new StringBuffer(128);//接收服务放回的缓冲区
//                sb.append(location.getCity());//获得城市
                pb_location.setVisibility(View.GONE);
                iv_area.setVisibility(View.VISIBLE);
                appContext = (AppContext) getApplicationContext();
                City locatecity = new City();
                locatecity.setProvince(location.getProvince().trim());//省
                locatecity.setName(location.getCity().trim());//市
                locatecity.setArea(location.getDistrict().trim());//县/区
                locatecity.setLongitude(location.getLongitude());
                locatecity.setLatitude(location.getLatitude());
                locatecity.setStreet(location.getStreet() + location.getStreetNumber());//街道+号码

                appContext.setCity(locatecity);
                city = appContext.getCity();
                tv_area.setText(city.getStreet());
                //将定位信息存入本地Sharepreferrence
                preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("province", city.getProvince());
                editor.putString("city", city.getName());
                editor.putString("area", city.getArea());
                editor.putString("longitude", city.getLongitude().toString());
                editor.putString("latitude", city.getLatitude().toString());
                editor.putString("street", city.getStreet());
                editor.commit();
                Log.i("getCountryCode",location.getCityCode());
                if (needRefreshInBDLoc){
                    needRefreshInBDLoc = false;
                    if (isNoInternetFirstTime == true){
                        isNoInternetFirstTime = false;
                        checkInternetAndLogin();
                    }else {
                        initDatas();
                    }
                }
                dismiss();
//                Toast.makeText(YZXIndexActivity.this,city.getLongitude()+" "+city.getLatitude(),Toast.LENGTH_SHORT).show();
            }else {
//                btn_city.setText("...");
                Toast.makeText(YZXIndexActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                dismiss();
                return;
            }
        }
    }
    private void stopListener() {
        if (locationClient!=null && locationClient.isStarted()) {
            locationClient.stop();//关闭点位Sdk
            locationClient=null;
        }
    }
    @Override
    public UserInfo getUserInfo(final String id) {
        Log.i("test",id);
        IntrestBuyNet.findshopuser1(2, id, new HandleSuccess<BUserInfo>() {
            @Override
            public void success(BUserInfo muser) {
                if (muser.getStatus() == 1) {
                    Long userid = null;
                    String name = null;
                    String path = null;
                    if (muser.getData() != null) {
                        userid = muser.getData().getCompanyMemberId();
                        name = muser.getData().getName();
                        path = muser.getData().getLogoImg() + Common.MORESMALLERPICPARAM;
                        Log.i("test","设置商城商户信息成功");
                        Common.userInfo = new UserInfo(userid.toString(), name, Uri.parse(path));
                        RongIM.getInstance().refreshUserInfoCache(Common.userInfo);
                    }else {
                        IntrestBuyNet.findshopuser2(3, id, new HandleSuccess<BUserInfo>() {
                            @Override
                            public void success(BUserInfo muser) {
                                if (muser.getStatus() == 1) {
                                    Long userid = null;
                                    String name = null;
                                    String path = null;
                                    if (muser.getData() != null) {
                                        userid = muser.getData().getCompanyMemberId();
                                        name = muser.getData().getName();
                                        path = muser.getData().getLogoImg() + Common.MORESMALLERPICPARAM;
                                        Log.i("test","设置美食商户信息成功");
                                        Common.userInfo = new UserInfo(userid.toString(), name, Uri.parse(path));
                                        RongIM.getInstance().refreshUserInfoCache(Common.userInfo);
                                    }else {
                                        IntrestBuyNet.findshopuser(1, id, new HandleSuccess<CUserInfo>() {
                                            @Override
                                            public void success(CUserInfo muser) {
                                                if (muser.getStatus() == 1){
                                                    Long userid = null;
                                                    String name = null;
                                                    String path = null;
                                                    if (muser.getData() != null){
                                                        userid = muser.getData().getId();
                                                        if (muser.getData().getNickname() != null){
                                                            name = muser.getData().getNickname();
                                                        }else {
                                                            name = muser.getData().getPhone().toString();
                                                        }
                                                        path = muser.getData().getAvatar() + Common.MORESMALLERPICPARAM;
                                                        Log.i("test","设置用户信息成功");
                                                        Common.userInfo = new UserInfo(userid.toString(),name, Uri.parse(path));
                                                        RongIM.getInstance().refreshUserInfoCache(Common.userInfo);
                                                    }else {
                                                        IntrestBuyNet.findDriverById(Long.valueOf(id), new HandleSuccess<DriverInfoBean>() {
                                                            @Override
                                                            public void success(DriverInfoBean s) {
                                                                if (s.getStatus() == 1 && s.getData() != null){
                                                                    Long userid = null;
                                                                    String name = null;
                                                                    String path = null;
                                                                    if (s.getData() != null){
                                                                        userid = s.getData().getId();
                                                                        if (s.getData().getSurname() != null){
                                                                            name = s.getData().getSurname()+"师傅";
                                                                        }else {
                                                                            name = s.getData().getPhone().toString();
                                                                        }
                                                                        path = s.getData().getImg() + Common.MORESMALLERPICPARAM;
                                                                        Log.i("test","设置用户信息成功");
                                                                        Common.userInfo = new UserInfo(userid.toString(),name, Uri.parse(path));
                                                                        RongIM.getInstance().refreshUserInfoCache(Common.userInfo);
                                                                    }
                                                                }
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        if (Common.userInfo != null){
            return Common.userInfo;
        }
        return null;
//        RequestQueue requestQueue = VolleyTools.getInstance(this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("id",s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url = Common.getUserInfoById() + URLEncoder.encode(jsonParams.toString());/////////////////////////////////
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        System.out.println(s);
//                        Long userid = null;
//                        String name = null;
//                        String path = null;
//                        if (jsonObject.getInt("code") == 0){
//                            jsonObject = jsonObject.getJSONObject("data");
//                            String sshop = jsonObject.getString("shop");
//                            if ("null".equals(sshop) || "".equals(sshop) || sshop == null){//就取user里的数据
//                                JSONObject muser = jsonObject.getJSONObject("user");
//                                userid = muser.getLong("id");
//                                if (muser.getString("nickname").equals("null")){
//                                    name = muser.getString("phone");
//                                }else {
//                                    name = muser.getString("nickname");
//                                }
//                                path = muser.getString("headpath");
//                            }else {//就取shop里的数据
//                                JSONObject mshop = jsonObject.getJSONObject("shop");
//                                userid = mshop.getLong("userid");
//                                name = mshop.getString("shopname");
//                                path = mshop.getString("imagesrc");
//                            }
//                            System.out.println("设置用户信息成功");
//                            Common.userInfo = new CUserInfo(userid.toString(),name, Uri.parse(path));
//                        }else {
//                            System.out.println("id为空");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("AppContext," + volleyError.getLocalizedMessage());
//            }
//        });
//        requestQueue.add(request);
//        friend
//        final CUserInfo[] userInfo = new CUserInfo[1];////////////////////////////////////////////////////////////////////////////////////////////////
//        getUserInfoById.getUserInfo(this , s, new getUserInfoById.UserInfoCallBack() {
//            @Override
//            public void onSuccess(CUserInfo user) {
//                userInfo[0] = user;
//            }
//        });
//        if (userInfo[0] != null){
//            System.out.println("userInfo[0].getPortraitUri()"+userInfo[0].getPortraitUri());
//            return userInfo[0];
//        }
//        System.out.println(id+"刷新设置用户");//////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (city != null){
                isWakeupAgain = true;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //提取保存裁剪之后的图片数据
//    private void setImageServerNeed(Intent intent) {
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
////			Bitmap photo=MediaStore.Images.Media.getBitmap(intent);
//            byte[] serverNeed = bitmapToBase64(photo);
//            request_upload_picture(serverNeed, photo);
//
//        }
//    }
    //将裁剪后的bitmap转换成base64 准备上传到服务器
//    private byte[] bitmapToBase64(Bitmap bitmap) {
//        byte[] result=null;
//        ByteArrayOutputStream baos = null;
//        try {
//            if (bitmap != null) {
//                baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//
//                baos.flush();
//                baos.close();
//
//                result = baos.toByteArray();
////                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (baos != null) {
//                    baos.flush();
//                    baos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
    //裁剪原始的图片
//    private void cropRawPhoto(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        //设置裁剪
//        intent.putExtra("crop", true);
//        //aspectX,aspectY:宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        //outputX,outputY:裁剪图片宽高
//        intent.putExtra("outputX", Common.output_X);
//        intent.putExtra("outputY", Common.output_Y);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, Common.CODE_RESULT_REQUEST);
//    }
//    private void request_upload_picture(byte[] serverNeed, Bitmap photo) {//存到七牛
//        byte[] data = serverNeed;
//        String key = null;
////                Common.IMAGE_FILE_NAME+imageName+".jpg";
//        String token = mytoken;
//        System.out.println("token" + token);
//        UploadManager uploadManager = new UploadManager();
//        uploadManager.put(data, key, token, new UpCompletionHandler() {
//            @Override
//            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
//                //得到返回图片名 给服务器存数据库
//                if (responseInfo.isOK()) {
//                    String name = null;
//                    try {
//                        name = jsonObject.getString("key");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    if (name != null) {
//                        Intent intent = new Intent();
//                        intent.putExtra("tag", 1);
//                        intent.putExtra("imagename", name);
//                        intent.setClass(YZXIndexActivity.this, VoicePhotoBuyActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(YZXIndexActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    finish();
//                    Toast.makeText(YZXIndexActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, null);
//    }
    /** 回调振幅，根据振幅设置图片 */
    private RecordManger.SoundAmplitudeListen onSoundAmplitudeListen = new RecordManger.SoundAmplitudeListen() {

        @Override
        public void amplitude(int amplitude, int db, int value) {
            if (value >= 6) {
                value = 6;
            }
//            progress.setBackgroundDrawable(progressImg[value]);// 显示震幅图片

        }
    };
    /** 下载播放状态监听器 */
    private OnStateListener onDownloadFileFileStateListener = new OnStateListener() {
        @Override
        public void onState(int error, String msg) {
            // TODO Auto-generated method stub

        }
    };
    /** 文件上传状态监听器 */
    private OnStateListener onUploadFileStateListener = new OnStateListener() {

        @Override
        public void onState(int error, String msg) {
            // TODO Auto-generated method stub
            stopTime();
            if (error != 0) {

                progress.setVisibility(View.GONE);
                mic_icon.setBackgroundDrawable(null);
                mic_icon.setImageResource(R.mipmap.voice_search_recognize_error);
                Toast.makeText(YZXIndexActivity.this,"录音失败可能被限制录音权限",Toast.LENGTH_SHORT).show();
                rl_recording.setVisibility(View.GONE);
                return;
            }else{
                System.out.println("上传服务器成功");
                rl_recording.setVisibility(View.GONE);
            }
        }
    };
    private void playSound() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {100,100};
        vibrator.vibrate(pattern,-1);
    }

    /*
    检查当前版本
     */
    private void checkLatestVersion() {
        IntrestBuyNet.findAppVersion(1, new HandleSuccess<VersionInfo>() {
            @Override
            public void onFailure(Call<VersionInfo> call, Throwable t) {
                //用此处此方法暂时判断当前WiFi没有网路 使用缓存的数据
                if (scanningAnim != null){
                    radar.startAnimation(scanningAnim);
                }
                if (rollingAnim != null){
                    iv_SHT.startAnimation(rollingAnim);
                }
                showCacheDatas(); //
            }
            @Override
            public void success(VersionInfo s) {
                if (s.getStatus() == 1){
                    if (s.getData() != null){
                        String versionCode = s.getData();
                        if (versionCode.trim().equals(Common.getmVersionCode().trim())){
                            Log.i("Info","当前为最新版本");
                        }else {
//                            if (CommonUtil.isWifi(YZXIndexActivity.this)){                  //如果是WiFi
//                                CommonUtil.updateVersion(YZXIndexActivity.this);//直接更新
//                            }else {                                                         //如果不是WiFi
                                UIHelper.togoCheckVersion(YZXIndexActivity.this,true);//去更新页面
//                            }
                        }
                    }
                }
            }
        });
    }
    /*
    todo 无可显示数据 去显示缓存数据
     */
    private void showCacheDatas() {
        String cacheProvince = CommonUtil.getCacheDatas(YZXIndexActivity.this,"province","userSetting");
        String cacheCity = CommonUtil.getCacheDatas(YZXIndexActivity.this,"city","userSetting");
        String cacheArea = CommonUtil.getCacheDatas(YZXIndexActivity.this,"area","userSetting");
        String cacheLongitude = CommonUtil.getCacheDatas(YZXIndexActivity.this,"longitude","userSetting");
        String cacheLatitude = CommonUtil.getCacheDatas(YZXIndexActivity.this,"latitude","userSetting");
        String cacheStreet = CommonUtil.getCacheDatas(YZXIndexActivity.this,"street","userSetting");
        String cacheShops = CommonUtil.getCacheDatas(YZXIndexActivity.this,"cacheShops","userData");
        if (cacheProvince != null && cacheCity != null && cacheArea != null && cacheLongitude != null && cacheLatitude != null && cacheStreet != null){//加载缓存位置数据
            pb_location.setVisibility(View.GONE);
            iv_area.setVisibility(View.VISIBLE);
            showMyIndexLocation(cacheProvince,cacheCity,cacheArea,cacheLongitude,cacheLatitude,cacheStreet);
        }
        if (cacheShops != null){//加载缓存商家数据
            showMyIndexShops(cacheShops);
        }
    }
    private void showMyIndexShops(String cache) {
        try {
            com.alibaba.fastjson.JSONObject jsonCache = com.alibaba.fastjson.JSONObject.parseObject(cache);
            NearbyShopHomepage datas = com.alibaba.fastjson.JSONObject.toJavaObject(jsonCache,NearbyShopHomepage.class);
            Log.i("test","show cache datas:"+cache);
            showShopsByDistance(datas.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showMyIndexLocation(String cacheProvince,String cacheCity,String cacheArea,String cacheLongitude,String cacheLatitude,String cacheStreet) {
        appContext = (AppContext) getApplicationContext();
        City locatecity = new City();
        locatecity.setProvince(cacheProvince);//省
        locatecity.setName(cacheCity);//市
        locatecity.setArea(cacheArea);//县/区
        locatecity.setLongitude(Double.valueOf(cacheLongitude));
        locatecity.setLatitude(Double.valueOf(cacheLatitude));
        locatecity.setStreet(cacheStreet);//街道+号码
        appContext.setCity(locatecity);
        city = appContext.getCity();
        tv_area.setText(cacheStreet);
    }

    /**
     * 通过判断user是否仍为null同步User信息
     */
    private void getLatestUserInfo(){
        if (user == null){
            appContext = (AppContext) getApplicationContext();
            user = appContext.getUser();
            setAlias();
        }
    }
    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(YZXIndexActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };
    /*
   讯飞等待录音对话框及录音逻辑
    */
    private void startRecordByXunFei() {
        //先给存入本地的文件取名字 拼全连接并存入本地 待取用
        audioName = Common.IMAGE_FILE_NAME+ CommonUtil.getNowTimeString();
// ②初始化有交互动画的语音识别器
        iatDialog = new RecognizerDialog(YZXIndexActivity.this, mInitListener);
        resetDialogParam(audioName,"10000");//本地录音文件名，后端点静音时间
        //③设置监听，实现听写结果的回调
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                System.out.println("-----------------   onResult   -----------------");
                if (!isLast){
                    iatDialog.dismiss();
                    String json = recognizerResult.getResultString();
                    result = JsonParser.parseIatResult(json);//语音转成的文字 一定会有用途////////////////////////////////////////////////
                    Log.i("test",json);
                    if (result.length() > 1){
                        //调用推送文字信息接口
                        uploadUtilToQiNiu(json);//*********语音上传至七牛********
                    }else {
                        Toast.makeText(YZXIndexActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //自动生成的方法存根
                Log.i("test","onError执行"+speechError.getErrorCode());
                if(speechError.getErrorCode() == 20006){
                    Toast.makeText(YZXIndexActivity.this,"打开录音失败，请检查权限",Toast.LENGTH_LONG).show();
                }else if (speechError.getErrorCode() == 10118){
                    Toast.makeText(YZXIndexActivity.this,"您好像没有说话哦",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(YZXIndexActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
                }
                speechError.getPlainDescription(true);
                iatDialog.dismiss();
            }

        });
        //开始听写，需将sdk中的assets文件下的文件夹拷入项目的assets文件夹下（没有的话自己新建）
        iatDialog.show();
    }

    /**
     * @param audioName 本地录音文件名
     * @param ms  后端点静音时间
     */
    private void resetDialogParam(String audioName, String ms) {
        iatDialog.setParameter(SpeechConstant.VAD_BOS, "10000");// 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理1000~10000
        iatDialog.setParameter(SpeechConstant.VAD_EOS, ms);// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音0~10000
        iatDialog.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");//格式支持pcm、wav
        if (audioName != null) {
            iatDialog.setParameter(SpeechConstant.ASR_AUDIO_PATH, Common.getWavFilePath(audioName));//语音保存路径
        }
    }

    private String voiceName = null;//七牛返回的语音名称
    private void uploadUtilToQiNiu(final String json) {
        //先获取本地文件
        File data = new File(Common.getWavFilePath(audioName));
        if (mytoken == null){
            Toast.makeText(YZXIndexActivity.this,"再试一次，说更详细一点吧",Toast.LENGTH_SHORT).show();
            return;
        }
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(data, null, mytoken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                //得到路径 将路径给服务器存数据库
                System.out.println(jsonObject);
                if (responseInfo.isOK()) {
                    System.out.println(jsonObject);
                    try {
                        voiceName = jsonObject.getString("key");
                        Log.i("rest","上传成功,存储路径为："+Common.getWavFilePath(audioName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent();
                    intent.putExtra("tag",2);
                    intent.putExtra("jsonvoice",json);
                    intent.putExtra("voicename",voiceName);
                    intent.setClass(YZXIndexActivity.this, V2VoicePhotoBuyActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);//去到自己的录音信息确定是否发送发送生么样的需求的界面
                }else {
                    System.out.println("七牛返回失败");
                    Toast.makeText(YZXIndexActivity.this,"服务器忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        },null);

    }
    /*
    去出行页面逻辑
     */
    private  void gotoTakeCarActivities(){
        showSimpleDialog();
        if (user == null) {
            appContext = (AppContext) getApplicationContext();
            user = appContext.getUser();
        }
        if (user != null) {
            IntrestBuyNet.findByDemandId(user.getId(), 0L, new HandleSuccess<TakeCarNeedBean>() {
                @Override
                public void onFailure(Call<TakeCarNeedBean> call, Throwable t) {
                    dismissSimDialog();
                    super.onFailure(call, t);
                }

                @Override
                public void success(TakeCarNeedBean s) {
                    dismissSimDialog();
                    if (s.getStatus() == 1 && s.getData() != null) {
                        switch (s.getData().getStatus()) {
                            case 0:
                            case 3:
                                UIHelper.togoTakeCarIndexActivity(YZXIndexActivity.this);
                                break;
                            default:
                                UIHelper.togoTakeCarWaitDriverAccept(YZXIndexActivity.this);
                                break;
                        }
                    } else {
                        UIHelper.togoTakeCarIndexActivity(YZXIndexActivity.this);
                    }
                }
            });
        }else {
            dismissSimDialog();
            UIHelper.togoLoginActivity(YZXIndexActivity.this);
        }
    }

    /**
     * todo 显示首页商家数据
     * @param data 商家bean
     */
    private void showShopsByDistance(List<NearbyShopHomepage.DataBean> data){
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Shop shop = new Shop();
                shop.setId(data.get(i).getId());
                shop.setShopName(data.get(i).getName());
                shop.setLogoPath(data.get(i).getLogoImg() + Common.WSMALLERPICPARAM);//http://obqlfysk2.bkt.clouddn.com/45shopImg.png
                if (data.get(i).getCategoryType()!=null){
                    shop.setCategory(data.get(i).getCategoryType());//1-商城2-订餐3-服务
                }else {
                    shop.setCategory(3);
                }
                shop.setShopPhone(data.get(i).getPhone());
                shop.setIsActivity(data.get(i).getIsactivity());
                if (city != null){//为之后执行的按距离排序做准备
                    shop.setDistance_d(LonLat.getDistance(data.get(i).getLongitude(),data.get(i).getLatitude(),
                            city.getLongitude(),city.getLatitude()));
                }else {
                    shop.setDistance_d(0d);
                }
                shops.add(shop);
            }
            DistanceComparator dc = new DistanceComparator();//按照距离远近排序
            Collections.sort(shops,dc);
        } else {
            if (pageNo != 1) {//不是第一页即前一页肯定有数据 就减一再次请求数据  防止死循环
                pageNo--;
                initDatas();
            }else {
                Toast.makeText(YZXIndexActivity.this,"附近还没有商家哦",Toast.LENGTH_SHORT).show();
            }
        }
        if (shops.size() > 0) {
            setImageToLogo();
            for (int i = 0; i < shops.size(); i++) {
                switch (i) {
                    case 0:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message1.setImageURI(shops.get(0).getLogoPath());
                                    tv_message1.setText(shops.get(0).getShopName());
                                    ll_message1.setVisibility(View.VISIBLE);
                                    if (shops.get(0).getIsActivity() != null){
                                        switch (shops.get(0).getIsActivity()){
                                            case 2:
                                                iv_egg1.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg1.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg1.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    case 1:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message2.setImageURI(shops.get(1).getLogoPath());
                                    tv_message2.setText(shops.get(1).getShopName());
                                    ll_message2.setVisibility(View.VISIBLE);
                                    if (shops.get(1).getIsActivity() != null){
                                        switch (shops.get(1).getIsActivity()){
                                            case 2:
                                                iv_egg2.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg2.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg2.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    case 2:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message3.setImageURI(shops.get(2).getLogoPath());
                                    tv_message3.setText(shops.get(2).getShopName());
                                    ll_message3.setVisibility(View.VISIBLE);
                                    if (shops.get(2).getIsActivity() != null){
                                        switch (shops.get(2).getIsActivity()){
                                            case 2:
                                                iv_egg3.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg3.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg3.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                        break;
                    case 3:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message4.setImageURI(shops.get(3).getLogoPath());
                                    tv_message4.setText(shops.get(3).getShopName());
                                    ll_message4.setVisibility(View.VISIBLE);
                                    if (shops.get(3).getIsActivity() != null){
                                        switch (shops.get(3).getIsActivity()){
                                            case 2:
                                                iv_egg4.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg4.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg4.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    case 4:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message5.setImageURI(shops.get(4).getLogoPath());
                                    tv_message5.setText(shops.get(4).getShopName());
                                    ll_message5.setVisibility(View.VISIBLE);
                                    if (shops.get(4).getIsActivity() != null){
                                        switch (shops.get(4).getIsActivity()){
                                            case 2:
                                                iv_egg5.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg5.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg5.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    case 5:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message6.setImageURI(shops.get(5).getLogoPath());
                                    tv_message6.setText(shops.get(5).getShopName());
                                    ll_message6.setVisibility(View.VISIBLE);
                                    if (shops.get(5).getIsActivity() != null){
                                        switch (shops.get(5).getIsActivity()){
                                            case 2:
                                                iv_egg6.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg6.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg6.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    case 6:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message7.setImageURI(shops.get(6).getLogoPath());
                                    tv_message7.setText(shops.get(6).getShopName());
                                    ll_message7.setVisibility(View.VISIBLE);
                                    if (shops.get(6).getIsActivity() != null){
                                        switch (shops.get(6).getIsActivity()){
                                            case 2:
                                                iv_egg7.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg7.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg7.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }

                            }
                        }.start();
                        break;
                    case 7:
                        new CountDownTimer(i * 500, 500) {//
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                try {
                                    iv_message8.setImageURI(shops.get(7).getLogoPath());
                                    tv_message8.setText(shops.get(7).getShopName());
                                    ll_message8.setVisibility(View.VISIBLE);
                                    if (shops.get(7).getIsActivity() != null){
                                        switch (shops.get(7).getIsActivity()){
                                            case 2:
                                                iv_egg8.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                iv_egg8.setVisibility(View.VISIBLE);
                                                break;
                                            default:
                                                break;
                                        }
                                    }else {
                                        iv_egg8.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    Log.i("indexout", "越界异常");
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        break;
                    default:
                        break;
                }

            }
            if (isNotification == true) {
                getLatestUserInfo();
                if (user != null) {
                    isNotification = false;
                    if (RongIM.getInstance() != null) {
                        RongIM.getInstance().startConversationList(YZXIndexActivity.this);
                    }
                } else {
                    UIHelper.togoLoginActivity(YZXIndexActivity.this);
                }
            }
        } else {
            setImageToLogo();
            if (pageNo == 1) {
//                                        Toast.makeText(YZXIndexActivity.this, "您的附近似乎没有太多此类商家", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(YZXIndexActivity.this, "没有下一页了~", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void abnormalLoginout() {
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                switch (connectionStatus){
                    case KICKED_OFFLINE_BY_OTHER_CLIENT:
                        SharedPreferences sp = getSharedPreferences("userData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.clear();
                        editor.commit();
                        user = null;
                        appContext.setUser(user);
                        RongIM.getInstance().logout();
                        mHandler.sendEmptyMessage(LOGOUT);
                        break;
                    default:
                        break;
                }
            }
        });
    }
/*
关闭除此之外所有界面 并弹出异常登录提示框
 */
    private void finishOthersAndAlert() {
        UIHelper.finishAllActivityExceptIndex();
        AlertDialog.Builder builder=new AlertDialog.Builder(YZXIndexActivity.this);
        builder.setTitle("提示");
        builder.setMessage("检测到您的帐号在其他设备登录，请重新登录");
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }

    private void initRongIMUnReadCountlistener() {
        final Conversation.ConversationType[] conversationTypes = {Conversation.ConversationType.PRIVATE, Conversation.ConversationType.DISCUSSION,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE};

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RongIM.getInstance().setOnReceiveUnreadCountChangedListener(mCountListener, conversationTypes);
            }
        }, 500);
    }
    public RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            if (count > 0 && count < 10){
                isHaveNewMessage = true;
                tv_message_have_new_rong.setVisibility(View.VISIBLE);
                tv_message_have_new_rong.setText(count+"");
            }else if (count >= 10){
                isHaveNewMessage = true;
                tv_message_have_new_rong.setVisibility(View.VISIBLE);
                tv_message_have_new_rong.setText("···");
            }else {
                isHaveNewMessage = false;
                tv_message_have_new_rong.setVisibility(View.GONE);
            }
//            if (count == 0) {
//                mUnreadCount.setVisibility(View.GONE);
//            } else if (count > 0 && count < 10) {
//                mUnreadCount.setVisibility(View.VISIBLE);
//                mUnreadCount.setText(count + "");
//            } else {
//                mUnreadCount.setVisibility(View.VISIBLE);
//                mUnreadCount.setText("...");
//            }
        }
    };


//    private void doLogin() {
//        SharedPreferences preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
//
//        username=preferences.getString("phone", "");
//        password=preferences.getString("password", "");
//        rongyunToken = DemoContext.getInstance().getSharedPreferences().getString("RY_TOKEN" + username, null);
//        System.out.println("融云Token：" + rongyunToken);
//
//        if (!"".equals(username)) {
//            IntrestBuyNet.userLogin(password, username, new HandleSuccess<Login>() {
//                @Override
//                public void success(Login s) {
//                    if (s.getStatus() == 1) {
//                        AppContext appCtx = (AppContext) getApplicationContext();
//                        User myuser = new User();
//                        myuser.setId(s.getData().getId());
//                        if (s.getData().getUsername() == null) {
//                            myuser.setUsername(null);
//                        } else {
//                            myuser.setUsername(s.getData().getUsername().toString());
//                        }
//                        myuser.setPassword(password);
//                        if (s.getData().getNickname() == null) {
//                            myuser.setNickName(null);
//                        } else {
//                            myuser.setNickName(s.getData().getNickname().toString());
//                        }
//                        myuser.setRealName(null);
//                        if (s.getData().getSex() == null) {
//                            myuser.setSex(null);
//                        } else {
//                            if (s.getData().getSex().toString().equals("0")) {//性别 1男 0女
//                                myuser.setSex("女");
//                            } else {
//                                myuser.setSex("男");
//                            }
//                        }
//                        if (s.getData().getBirthday() == null) {
//                            myuser.setBirthday(null);
//                        } else {
//                            Date dt = new Date(s.getData().getBirthday());
//                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//                            myuser.setBirthday(format.format(dt));
//                        }
//                        myuser.setPhone(s.getData().getPhone() + "");
//                        if (s.getData().getAvatar() == null) {
//                            myuser.setHeadPath(null);
//                        } else {
//                            myuser.setHeadPath(s.getData().getAvatar().toString());
//                        }
//                        if (s.getData().getIntegral() == null) {
//                            myuser.setTotalPoints(0);
//                            myuser.setUsePoints(0);
//                        } else {
//                            myuser.setTotalPoints(s.getData().getIntegral());
//                            myuser.setUsePoints(s.getData().getIntegral());
//                        }
//                        if (s.getData().getEmail() == null){
//                            myuser.setEmail(null);
//                        }else {
//                            myuser.setEmail(s.getData().getEmail());
//                        }
//                        appCtx.setUser(myuser);
//                        getLatestUserInfo();
//                        if (user != null && rongyunToken != null) {
//                            connect(rongyunToken);
//                        }
//                        JPushInterface.resumePush(getApplicationContext());
//                        setAlias();//极光推送设置别名
//                    } else {
//                        Toast.makeText(YZXIndexActivity.this, "登录信息变更，需重新登录", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
//    private void request_rongyun_token(){
//        String avatar = "";
//        String nickname = "";
//        if (user.getNickName() == null) {
//            avatar = user.getPhone();
//        }else {
//            avatar = user.getNickName();
//        }
//        if (user.getHeadPath() == null) {
//            nickname = "";
//        } else {
//            nickname = user.getHeadPath();
//        }
//        IntrestBuyNet.getRongToken(avatar, nickname, user.getId(), new HandleSuccess<RongToken>() {
//            @Override
//            public void success(RongToken s) {
//                if (s.getCode() == 200){
//                    String token = s.getToken();
//                    connect(token);//连接融云服务器
//                }else {
//                    Toast.makeText(YZXIndexActivity.this,"提示：聊天系统维护中,可能无法使用",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//    /**
//     * 建立与融云服务器的连接
//     *
//     * @param token
//     */
//    private void connect(String token) {
//        if (getApplicationInfo().packageName.equals(AppContext.getCurProcessName(getApplicationContext()))) {
//            /**
//             * IMKit SDK调用第二步,建立与服务器的连接
//             */
//            RongIM.connect(token, new RongIMClient.ConnectCallback() {
//                /**
//                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
//                 */
//                @Override
//                public void onTokenIncorrect() {//重新获取token
//                    Log.d("YZXIndexActivity", "--onTokenIncorrect");
//                    Log.i("test","重新获取了一次Token，并重连");
//                    request_rongyun_token();
//                }
//                /**
//                 * 连接融云成功
//                 * @param userid 当前 token
//                 */
//                @Override
//                public void onSuccess(String userid) {
//                    Log.d("YZXIndexActivity", "--onSuccess" + userid);
////                    String nickname = null;
////                    String headpath = null;
////                    if (user.getNickName().equals("") || user.getNickName().equals("null") || user.getNickName() == null) {
////                        nickname = user.getPhone();
////                    } else {
////                        nickname = user.getNickName();
////                    }
////                    if (user.getHeadPath().equals("") || user.getHeadPath().equals("null") || user.getHeadPath() == null) {
////                        headpath = null;
////                    } else {
////                        headpath = user.getHeadPath();
////                    }
////                    CUserInfo userInfo = new CUserInfo(userid, nickname, Uri.parse(headpath));
////                    if (RongIM.getInstance() != null) {
////                        RongIM.getInstance().setCurrentUserInfo(userInfo);
////                    }
//                }
//
//                /**
//                 * 连接融云失败
//                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
//                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
//                 */
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//                    Log.d("LoginActivity", "--onError" + errorCode);
//                    Log.i("test","融云链接错误，并重连了一次");
//                    RongIM.getInstance().disconnect();
//                    if (rongyunToken != null){
//                        connect(rongyunToken);
//                    }else {
//                        request_rongyun_token();
//                    }
//                }
//            });
//        }
//    }
}
