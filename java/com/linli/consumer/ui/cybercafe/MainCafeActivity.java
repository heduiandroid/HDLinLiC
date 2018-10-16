package com.linli.consumer.ui.cybercafe;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.BaseFragment;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.ExampleUtil;
import com.linli.consumer.utils.LoginHelper;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class MainCafeActivity extends BaseActivity implements RongIM.UserInfoProvider  {
    private static final int LOGOUT = 2;
    private static final int SANNING_SHOP_CODE = 100;//扫描店铺码Code

    private FrameLayout fl_contents;//填充fragment布局
    private LinearLayout ll_home,ll_integralmall,ll_orders,ll_mine;//首页 积分商城 订单 我的
    private ImageView iv_home,iv_integralmall,iv_orders,iv_mine;
    private TextView tv_home,tv_integralmall,tv_orders,tv_mine;

    private FragmentHomepagePre fragmentHomepagePre;
    private FragmentHomepage fragmentHomepage;
    private FragmentIntegralmall fragmentIntegralmall;
    private FragmentOrders fragmentOrders;
    private FragmentMine fragmentMine;
    private BaseFragment fragmentNow = null;
    private List<ImageView> iv_list;
    private List<TextView> tv_list;

    private User user = AppContext.getInstance().getUser();
    private City city;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case LOGOUT:
                    finishOthersAndAlert();
                    break;
                default:
                    break;
            }
        };
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_cafe;
    }

    @Override
    protected void initView() {
        fl_contents = findView(R.id.fl_contents);

        ll_home = findViewClick(R.id.ll_home);
        ll_integralmall = findViewClick(R.id.ll_integralmall);
        ll_orders = findViewClick(R.id.ll_orders);
        ll_mine = findViewClick(R.id.ll_mine);
        iv_home = findView(R.id.iv_home);
        iv_integralmall = findView(R.id.iv_integralmall);
        iv_orders = findView(R.id.iv_orders);
        iv_mine = findView(R.id.iv_mine);
        tv_home = findView(R.id.tv_home);
        tv_integralmall = findView(R.id.tv_integralmall);
        tv_orders = findView(R.id.tv_orders);
        tv_mine = findView(R.id.tv_mine);

        iv_list = new ArrayList<>();
        tv_list = new ArrayList<>();
        iv_list.add(iv_home);
        iv_list.add(iv_integralmall);
        iv_list.add(iv_orders);
        iv_list.add(iv_mine);
        tv_list.add(tv_home);
        tv_list.add(tv_integralmall);
        tv_list.add(tv_orders);
        tv_list.add(tv_mine);
    }

    @Override
    protected void initData() {
        changePageFragment(R.id.ll_home);
        dismiss();
        abnormalLogout();//检测到其他设备登录账号 当前账号自动退出登录
        initRongIMUnReadCountlistener();//监控融云即时通讯未读消息条数
        checkLatestVersion();

        RongIM.setUserInfoProvider(this, true);
        registerMessageReceiver();
        initBDMap();//定位
        //检查是否可以执行自动登录 如果可以执行静默登录  如果不可以直接弹出登录界面
        if (CommonUtil.getCacheDatas(this, "userid", "userData") != null || CommonUtil.getCacheDatas(this, "phone", "userData") != null) {
            LoginHelper.doLoginSilently(MainCafeActivity.this);
        }else {
            UIHelper.togoLoginActivity(this);
        }
    }

    @Override
    protected void onResume() {
        getLatestUserInfo();
        super.onResume();
    }
    @Override
    public void onHDClick(View v) {
        //如果user是null 重新获取user 获取成功后设置极光别名 如果不是null 继续执行
        getLatestUserInfo();
        switch (v.getId()) {
            case R.id.ll_home:
                changePageFragment(v.getId());
                break;
            case R.id.ll_integralmall:
            case R.id.ll_orders:
            case R.id.ll_mine:
                if (user != null) {
                    if (user.getShopId() != null) {
                        changePageFragment(v.getId());
                    } else {
                        //去扫码
                        UIHelper.togoScanningBuyActivity(this, 1);
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            default:
                break;
        }
    }
    private void changePageFragment(int viewId) {
        switch (viewId) {
            case R.id.ll_home:
                if (user != null) {
                    if (user.getShopId() != null) {                                  //加载确认过店铺（扫过码）的fragment
                        if (fragmentHomepage == null) {//减少new fragmnet,避免不必要的内存消耗
                            fragmentHomepage = FragmentHomepage.newInstance();
                        }
                        switchFragment(fragmentNow, fragmentHomepage);
                    } else {                                                           //加载未确认过店铺（未扫过码）的fragment
                        if (fragmentHomepagePre == null) {//减少new fragmnet,避免不必要的内存消耗
                            fragmentHomepagePre = FragmentHomepagePre.newInstance();
                        }
                        switchFragment(fragmentNow, fragmentHomepagePre);
                    }
                }else {
                    if (fragmentHomepagePre == null) {//减少new fragmnet,避免不必要的内存消耗
                        fragmentHomepagePre = FragmentHomepagePre.newInstance();
                    }
                    switchFragment(fragmentNow, fragmentHomepagePre);
                }
                changePageSelected(0);
                break;
            case R.id.ll_integralmall:
                if (fragmentIntegralmall == null) {
                    fragmentIntegralmall = FragmentIntegralmall.newInstance();
                }
                switchFragment(fragmentNow, fragmentIntegralmall);
                changePageSelected(1);
                break;
            case R.id.ll_orders:
                if (fragmentOrders == null) {
                    fragmentOrders = FragmentOrders.newInstance();
                }
                switchFragment(fragmentNow, fragmentOrders);
                changePageSelected(2);
                break;
            case R.id.ll_mine:
                if (fragmentMine == null) {
                    fragmentMine = FragmentMine.newInstance();
                }
                switchFragment(fragmentNow, fragmentMine);
                changePageSelected(3);
                break;
            default:
                break;
        }
    }
    /**
     * 隐藏显示fragment
     *
     * @param from 需要隐藏的fragment
     * @param to   需要显示的fragment
     */
    public void switchFragment(BaseFragment from, BaseFragment to) {
        if (to == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (from == null) {
                transaction.add(R.id.fl_contents, to).show(to).commit();
            } else {
                // 隐藏当前的fragment，add下一个fragment到Activity中并显示
                transaction.hide(from).add(R.id.fl_contents, to).show(to).commitAllowingStateLoss();
            }
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit();
        }
        fragmentNow = to;

    }
    private void changePageSelected(int index) {
        for (int i = 0; i < iv_list.size(); i++) {
            if (index == i) {
                iv_list.get(i).setEnabled(false);
                tv_list.get(i).setTextColor(getResources().getColor(R.color.black));
            } else {
                iv_list.get(i).setEnabled(true);
                tv_list.get(i).setTextColor(getResources().getColor(R.color.gray));
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        RongIM.getInstance().logout();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        stopListener();
        super.onPause();
    }
    private void stopListener() {
        if (locationClient!=null && locationClient.isStarted()) {
            locationClient.stop();//关闭点位Sdk
            locationClient=null;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == SANNING_SHOP_CODE){
            changePageFragment(R.id.ll_home);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void setCostomMsg(String msg){
        try {
            JSONObject result = new JSONObject(msg);
            int type = result.getInt("type");//推送类别
            switch (type){
                case 8:
                    UIHelper.togoRedBagDialogActivity(this, BigDecimal.valueOf(result.getDouble("coupon")).toString(),0);//收到红包的弹窗通知
                    break;
//                case 9://司机已接单 正赶往乘客
//                case 10://司机已经接到乘客 正赶往目的地
//                    showSimpleDialog();
//                    UIHelper.togoTakeCarWaitDriverAcceptPassive(YZXIndexActivity.this);//如果打车页面存在不进行处理；如果打车页面不存在则打开页面
//                    dismissSimDialog();
//                    break;
//                case 11://乘客已经到达目的地 去评价页面
//                    UIHelper.togoEvaluateDriver(YZXIndexActivity.this);
//                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
     关闭除此之外所有界面 加载首屏fragment 并弹出异常登录提示框
    */
    private void finishOthersAndAlert() {
        UIHelper.finishAllActivityExceptIndex();
        changePageFragment(R.id.ll_home);
        AlertDialog.Builder builder=new AlertDialog.Builder(MainCafeActivity.this);
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
    private void abnormalLogout() {
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
            if (count > 0){
                //实时放入user信息中备用
                if (user != null) {
                    user.setUnreadMessageCount(count);
                }
            }
        }
    };

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    /**
    已在MyReceiver设置了此Action，这里便可接收
     */
    public static final String MESSAGE_RECEIVED_ACTION = "com.linli.consumer.MESSAGE_RECEIVED_ACTION";
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
    /**
     * 通过判断user是否仍为null同步User信息
     */
    private void getLatestUserInfo(){
        if (user == null){
            appContext = (AppContext) getApplicationContext();
            user = appContext.getUser();
            if (user != null) {
                setAlias();
            }
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
    /**
     * 为用户设置别名用于推送消息
     */
    private void setAlias() {
        if (user != null){
            String alias = user.getId();
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
        }

    };

    private BDAbstractLocationListener locationListener;
    private LocationClient locationClient = null;
    private SharedPreferences preferences;
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
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.i("test",location.toString());
            if (location!=null || location.getCity() != null) {
//                StringBuffer sb=new StringBuffer(128);//接收服务放回的缓冲区
//                sb.append(location.getCity());//获得城市
//                pb_location.setVisibility(View.GONE);
//                iv_area.setVisibility(View.VISIBLE);
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
//                tv_area.setText(city.getStreet());
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
            }else {
                showToast("定位失败");
                return;
            }
        }
    }

    /**
     * 检查最新版本
     */
    private void checkLatestVersion() {
    }

    /**
     * 获取融云使用的最新头像和昵称
     * @param s
     * @return
     */
    @Override
    public UserInfo getUserInfo(String s) {
        return null;
    }

}
