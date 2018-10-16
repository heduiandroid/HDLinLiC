package com.linli.consumer.ui.main;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.StoreByWifi;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Shop;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.AnimationUtil;
import com.linli.consumer.utils.LonLat;
import com.linli.consumer.utils.SoundsUtil;

import java.io.IOException;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiActivity extends BaseActivity implements View.OnTouchListener {
    private AppContext appContext;
    private City city;
    private ImageView iv_bg_paiyipai;
    private SimpleDraweeView sdv_gifpaiyipai;
    private boolean touchAble = true;//可拍状态
    private boolean paiSound;
    private long shopId = 0l;
    private boolean isWifiConnected = false;//是否是wifi网络
    private boolean isDataInternet;//是否是手机数据流量网络
    private BluetoothAdapter mBluetoothAdapter;
    //start 关于盒子
    private static final int REQUEST_ENABLE_BT = 1234;
//    public static Region ALL_BEACONS_REGION = new Region("rid", null, null, null);//标识未定制Beacon区域，本应用中不需要使用Region
    public static boolean isBeaconWifiConnected = false;//记录wifi的连接状态，防止重复连接一个区域内的多个wifi
//    public static BeaconManager beaconManager;
    private BroadcastReceiver rssiReceiver;//wifi改变状态时的监控
    // 当前wifi的SSID
    private WifiManager wifiManager;
    private static String ssid;
    private static String passwd;
    private WifiConfiguration wifiConfig;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0x123) {
//                Toast.makeText(PaiYiPaiActivity.this, "马上就好，配置网络中...", Toast.LENGTH_LONG).show();
//                try {
//                    beaconManager.stopRanging(ALL_BEACONS_REGION);
//                    beaconManager.stopMonitoring(ALL_BEACONS_REGION);
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    Log.e("beaconManager", "stoping ranging fails");
//                }
//                final Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (wifiManager.getConnectionInfo().getBSSID() == null) {//？？？？
//                            try {
//                                Thread.sleep(1000);
//                            } catch (Exception e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                        }
//                        while (wifiManager.getConnectionInfo().getBSSID().equals("00:00:00:00:00:00")) {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                isWifiConnected = true;
//                                isBeaconWifiConnected = true;
//                                dismiss();
//                                new CountDownTimer(2 * 1000, 1000) {//两秒之后再去请求数据，因为太快去请求数据会导致还没有网络会请求失败
//                                    @Override
//                                    public void onTick(long millisUntilFinished) {
//                                        Toast.makeText(PaiYiPaiActivity.this, "成功连接店铺Wifi【" + wifiManager.getConnectionInfo().getSSID() + "】", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onFinish() {
//                                        getShopIdByWifi(wifiManager.getConnectionInfo().getBSSID());
//                                    }
//                                }.start();
//                            }
//                        });
//
//                    }
//                });
//                thread.start();
//
//
//            }
//            super.handleMessage(msg);
//        }
//    };
    private ProgressDialog bProgressDialog;//ibeacon连接wifi等待Dialog
    private boolean isFindedOneIcon = false;//没网状态下已找到一个ibeacon
    private DraweeController controller;
    private Integer category = null;//商铺类型
    //定位到店铺后显示店铺信息部分view
    private SimpleDraweeView sdv_logo;
    private TextView tv_shopname;
    private LinearLayout ll_shop_info;
//    private LinearLayout ll_connect_shop_wifi_now;
    private RatingBar room_ratingbar;
    private TextView tv_distance;
    private TextView tv_notice;
    private TextView tv_close;
    private boolean isClosedShopManual = false;
    private boolean isShowedNotSeenMessage;
    //end

    private String shopName;
    private int FAILEDPAICOUNT = 0;//拍击后无响应（拍失败）的次数记录
    private int FINGERNUM = 2;//排击屏幕手指数量 正式打包设为2 （调试时设为1）
    private int numOfResumed = 0;
    private boolean isSendMessage = false; //判断是否发送 sengmessage;
    //网络
    private ConnectivityManager connectivityManager;
    private NetworkInfo info;
//    //网络状态发生改变  首页第一步
//    private BroadcastReceiver mReceivers = new BroadcastReceiver() {
//        @Override
//        public void onReceive(final Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
//                Log.d("mark", "网络状态已经改变");
//                //网络
//                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                info = connectivityManager.getActiveNetworkInfo();
//                if (info != null && info.isAvailable()) {
//                    String name = info.getTypeName();
//                    Log.d("mark", "当前网络名称：" + name);
//                    if (numOfResumed < 1){
//                        Log.d("mark", "网络状态改变被截断"+numOfResumed);
//                        return;
//                    }
//                    if (name.equals("mobile")) {
//                        Log.d("mark", "手机流量");
//                    } else {
//                        //wifi
//                        isWifiConnected = true;
//                        isSendMessage = true;
////                        mBluetoothAdapter.disable();//已经取到盒子信息，将蓝牙关掉
//                        Message message = new Message();
//                        message.what = 0x123;
//                        handler.sendMessage(message);
//                        System.out.println("发送handler消息");
//                    }
//                } else {
//                    isSendMessage = false;
//                    Log.d("mark", "没有可用网络 请检查网络");
//                }
//            }
////            context.unregisterReceiver(this);
//        }
//    };
    private boolean isActivityOn = true;//当前activity正在栈顶

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pai_yi_pai;
    }

    @Override
    protected void initView() {
//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(mReceivers, mFilter); //注册监听网络状态

//        utils.setBeaconParserInteface(new BeaconParser());
        init();
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        rssiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Rssi", "Rssi changed");
                if (wifiManager == null) {
                    wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                }
                Log.d("当前wifi为：", wifiManager.getConnectionInfo().getSSID());
            }
        };
        //设置盒子联网监听器
//        beaconManager = new BeaconManager(this);
//        beaconManager.setOnBeaconNotSeenListener(new BeaconManager.onBeaconNotSeenListener() {//这块现在根本不会执行
//            @Override
//            public void onBeaconNotSeen(List<beacon> list) {
//                if (!isShowedNotSeenMessage) {
//                    isShowedNotSeenMessage = true;
//                    Toast.makeText(PaiYiPaiActivity.this, "智能互联设备距离较远", Toast.LENGTH_SHORT).show();
//                    //重新判断数据流量是否已连接
//                    isDataInternet = isDataInternet();
//                    //重新判断WiFi是否已连接
//                    isWifiConnected = isWifi();
//                }
//            }
//        });
//        beaconManager.setOnNewBeaconFoundListener(new BeaconManager.onNewBeaconFoundListener() {
//
//            @Override
//            public void onNewBeaconFound(final beacon newComingBeacon) {
//
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (!isBeaconWifiConnected) {
////                            if (!isFindedOneIcon){
//                            Log.v("isBeaconWifiConnected", isBeaconWifiConnected + "");
//                            if (!wifiManager.isWifiEnabled()) {
//                                wifiManager.setWifiEnabled(true);
//                                while (!wifiManager.isWifiEnabled()) {
//                                    try {
//                                        Thread.sleep(100);
//                                    } catch (InterruptedException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                            try {
//                                beaconManager.stopRanging(ALL_BEACONS_REGION);
//                                beaconManager.stopMonitoring(ALL_BEACONS_REGION);
//                            } catch (RemoteException e1) {
//                                // TODO Auto-generated catch block
//                                e1.printStackTrace();
//                            }
////                                isFindedOneIcon = true;
//                            wifiConfig = new WifiConfiguration();
//                            wifiConfig.allowedAuthAlgorithms.clear();
//                            wifiConfig.allowedGroupCiphers.clear();
//                            wifiConfig.allowedKeyManagement.clear();
//                            wifiConfig.allowedPairwiseCiphers.clear();
//                            wifiConfig.allowedProtocols.clear();
//                            wifiConfig.hiddenSSID = false;
//                            wifiConfig.SSID = "\"" + newComingBeacon.getWifiSsid() + "\"";
//                            wifiConfig.preSharedKey = String.format("\"%s\"", newComingBeacon.getWifiPassword());
//                            wifiConfig.preSharedKey = "\"" + newComingBeacon.getWifiPassword() + "\"";
//                            wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
//                            wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//                            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
//                            wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//                            // wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
//                            wifiConfig.status = WifiConfiguration.Status.ENABLED;
//                            wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//                            wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//                            Log.i("test", "com1:" + newComingBeacon.getCompanyId());
//                            Log.i("test", "com2:" + newComingBeacon.getCompanyId2());
//                            Log.i("test", "ssid:" + newComingBeacon.getWifiSsid());
//                            Log.i("test", "pass:" + newComingBeacon.getWifiPassword());
//                            Log.i("test", "header:" + newComingBeacon.getBeaconHeader());
//
//
//                            if (!wifiManager.getConnectionInfo().getSSID().equals(wifiConfig.SSID) || !wifiManager
//                                    .getConnectionInfo().getSupplicantState().name().equals("COMPLETED")) {
//                                // remember id
//                                int netId = 0;
//                                boolean flag = false;
//                                for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
//                                    if (wifiConfiguration.SSID.equals(wifiConfig.SSID)) {
//                                        flag = true;
//                                        netId = wifiConfiguration.networkId;
//                                        break;
//                                    }
//                                }
////                                if (!flag) {
////                                    netId = wifiManager.addNetwork(wifiConfig);
////                                    System.out.println("添加了一次");
////
////                                }
////                                if (!isFindedOneIcon){//测试多个盒子打开状态是否可连WiFi 不行的话还是把这个放开吧
////                                    isFindedOneIcon = true;
//
//                                Log.i("Number" + newComingBeacon.getCompanyId(), wifiConfig.SSID + wifiConfig.preSharedKey);
//                                if (flag) {
//                                    wifiManager.removeNetwork(netId);
//                                }
//
//                                netId = wifiManager.addNetwork(wifiConfig);
//                                wifiManager.disconnect();
//                                wifiManager.enableNetwork(netId, true);
//                                wifiManager.reconnect();
//
//                                try {
//                                    Thread.sleep(4000);
//                                } catch (InterruptedException e) {
//                                    // TODO Auto-generated catch block
//                                    e.printStackTrace();
//                                }
//                                if (wifiManager.getConnectionInfo().getSSID().equals(wifiConfig.SSID) && wifiManager
//                                        .getConnectionInfo().getSupplicantState().name().equals("COMPLETED")) {
//                                    isWifiConnected = true;
//                                    isBeaconWifiConnected = true;
//
//                                }
//                                if (!isSendMessage) {
////                                    mBluetoothAdapter.disable();//已经取到盒子信息，将蓝牙关掉
////                                    Message message = new Message();
////                                    message.what = 0x123;
////                                    handler.sendMessage(message);
////                                    System.out.println("发送handler消息");
//                                }
//                            }
////                            }else {
////                                Log.i("test","已忽略了一个距离较远的iBecon");
////                            }
//                        }
//                    }
//                });
//                thread.start();
//            }
//        });
    }

    @Override
    protected void initData() {
//判断数据流量是否已连接
        isDataInternet = isDataInternet();
        //判断WiFi是否已连接
        isWifiConnected = isWifi();
        if (!isDataInternet && !isWifiConnected) {//既没有开流量有没有连WiFi，提前开蓝牙开无线网
            showToast("抱歉，您当前没有网络");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                getReadyToConnBox();
//                Toast.makeText(PaiYiPaiActivity.this, "轻拍一下 连接Wifi", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(PaiYiPaiActivity.this, "不支持的机型，建议手动联网后重试", Toast.LENGTH_SHORT).show();
//            }
        } else if (isDataInternet && !isWifiConnected) {//连接了数据流量，并且没有连接WiFi
            //左侧划出一个可连WiFi提示语
//            new CountDownTimer(1 * 1000, 1000) {//一秒之后在显示动画
//                @Override
//                public void onTick(long millisUntilFinished) {
//                }
//
//                @Override
//                public void onFinish() {
//                    ll_connect_shop_wifi_now.setVisibility(View.VISIBLE);
//                    ll_connect_shop_wifi_now.setAnimation(AnimationUtil.moveFromLeftToView());
//                }
//            }.start();
//            new CountDownTimer(6 * 1000, 1000) {//五秒之后隐藏
//                @Override
//                public void onTick(long millisUntilFinished) {
//                }
//
//                @Override
//                public void onFinish() {
//                    ll_connect_shop_wifi_now.setVisibility(View.GONE);
//                    ll_connect_shop_wifi_now.setAnimation(AnimationUtil.moveFromViewToLeft());
//                }
//            }.start();
        }
        SoundsUtil.init(this);
        controller = Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true)
                .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.gifpaiyipai)).build();
        sdv_gifpaiyipai.setController(controller);
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_head_right:
                //去设置界面
                startActivity(new Intent(PaiYiPaiActivity.this, SettingPaiYiPaiActivity.class));
                break;
            case R.id.ll_shop_info:
                switch (category) {
                    case 1:
                        UIHelper.togoShopDetailActivity(this, shopId, shopName, SHOP_MAIN);
                        break;
                    case 2:
                        UIHelper.togoShopDetailActivity(this, shopId, shopName, FOOD_MAIN);
                        break;
                    case 3:
                        UIHelper.togoShopDetailActivity(this, shopId, shopName, SERVICE_MAIN);
                        break;
                    default:
                        break;
                }
                break;
            case R.id.tv_close:
                shopId = 0l;
                isClosedShopManual = true;
                ll_shop_info.setVisibility(View.GONE);
                tv_close.setVisibility(View.GONE);
                ll_shop_info.setAnimation(AnimationUtil.moveFromViewToTop());
                break;
//            case R.id.ll_connect_shop_wifi_now:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    ll_connect_shop_wifi_now.setVisibility(View.GONE);
//                    ll_connect_shop_wifi_now.setAnimation(AnimationUtil.moveFromViewToLeft());
//                    isWifiConnected = false;
//                    isDataInternet = false;
//                    getReadyToConnBox();
//                    Toast.makeText(PaiYiPaiActivity.this, "轻拍一下 连接Wifi", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(PaiYiPaiActivity.this, "不支持的机型，建议手动连接店铺WiFi", Toast.LENGTH_SHORT).show();
//                }
//                break;
            default:
                break;
        }
    }

    //判断是否是数据流量网络
    private boolean isDataInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    //判断WiFi是否已连接
    private boolean isWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("拍一拍");
        TextView tv_head_right = findViewClick(R.id.tv_head_right);
        tv_head_right.setText("设置");
        iv_bg_paiyipai = findView(R.id.iv_bg_paiyipai);
        iv_bg_paiyipai.setOnTouchListener(this);
        findViewClick(R.id.iv_back);
        sdv_gifpaiyipai = findView(R.id.sdv_gifpaiyipai);

        ll_shop_info = findViewClick(R.id.ll_shop_info);
        sdv_logo = findView(R.id.sdv_logo);
        tv_shopname = findView(R.id.tv_shopname);
        room_ratingbar = findView(R.id.room_ratingbar);
        tv_distance = findView(R.id.tv_distance);
        tv_notice = findView(R.id.tv_notice);
        tv_close = findViewClick(R.id.tv_close);
//        ll_connect_shop_wifi_now = findViewClick(R.id.ll_connect_shop_wifi_now);
    }

    private int fingerNum = 0;//判断手指数量

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                fingerNum = 0;
                System.out.println("计数：(初始化手指数)" + fingerNum);
                fingerNum++;
                System.out.println("计数：(主手指按住)" + fingerNum);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                fingerNum++;
                System.out.println("计数：(从手指按住)" + fingerNum);
                if (fingerNum > FINGERNUM - 1){
                    if (touchAble) {
                        numOfResumed++;
                        touchAble = false;
                        if (paiSound) {
                            SoundsUtil.play(1);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touchAble = true;
                            }
                        }, 3000);
                        getMySettingDatas();/////////////////
                    } else {
                        FAILEDPAICOUNT++;
                        if (FAILEDPAICOUNT > 4) {//如果5次都没有反应 重设一下可拍状态 并且手指数变为1根就可以了 重设失败次数为稍大的数值
                            touchAble = true;
                            FINGERNUM = 1;
                            FAILEDPAICOUNT = 2;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("计数：(最后一根手指松开)" + fingerNum);
                break;
        }
        return true;
    }

    private void getReadyToConnBox() {
        //连接盒子后连接wifi，根据wifi取shop,如果没有取到，搜索附近商家
        bProgressDialog = ProgressDialog.show(PaiYiPaiActivity.this, "提示", "请稍后", false, false);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(PaiYiPaiActivity.this, "本机没有找到蓝牙硬件或驱动", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();//无声息开启蓝牙设备  mBluetoothAdapter.disable();是关闭的操作
        }
        new CountDownTimer(2 * 1000, 1000) {//两秒之后
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                bProgressDialog.dismiss();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isEnabled()) {//如果没拍就要返回，给用户把蓝牙关掉
//                mBluetoothAdapter.disable();
            }
        }
//        unregisterReceiver(mReceivers);
//        beaconManager.disconnect();
        super.onDestroy();
    }

    //    private void playAnimation() {
//        ScaleAnimation animation = new ScaleAnimation(1,1.3f,1,1.3f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        animation.setDuration(2000);
//        animation.setRepeatCount(1);
//        iv_paipai_anim.startAnimation(animation);
//    }
    private void playSound() {
        boolean createState=false;
        MediaPlayer mediaPlayer = null;
        if(mediaPlayer==null){
            mediaPlayer=createLocalMp3();
            createState=true;
        }
        //当播放完音频资源时，会触发onCompletion事件，可以在该事件中释放音频资源，
        //以便其他应用程序可以使用该资源:
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();//释放音频资源
                Log.i("test","资源释放");
            }
        });
        try {
            //在播放音频资源之前，必须调用Prepare方法完成些准备工作
            if(createState) mediaPlayer.prepare();
            //开始播放音频
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private MediaPlayer createLocalMp3(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.papapa);
        mp.stop();
        return mp;
    }
    /*
    三种情况去获取数据
     */
    private void getMySettingDatas() {
        sdv_gifpaiyipai.setController(controller);
        if (isDataInternet) {//有数据流量
            //直接搜索附近商家
            //路由信息没有取到 则去查询附近商家
            if (shopId == 0l) {
                if (isActivityOn) {
                    Intent intent = new Intent();
                    intent.setClass(PaiYiPaiActivity.this, PaiYiPaiNearbyShopsActivity.class);
                    startActivityForResult(intent, 1100);
                }
            } else {
                if (isActivityOn) {
                    showPaiYiPaiDialogWithDatas();
                }
            }

        } else if (isWifiConnected) {//有wifi
            //根据wifi取shop，如果没有取到，搜索附近商家
            if (isClosedShopManual) {//手动取消过已经连接的商家
                if (isActivityOn) {
                    Intent intent = new Intent();
                    intent.setClass(PaiYiPaiActivity.this, PaiYiPaiNearbyShopsActivity.class);
                    startActivityForResult(intent, 1100);
                }
            } else {
                getShopIdByWifi(null);
            }
        } else {//没连WiFi 开蓝牙 开wifi 利用硬件 连WiFi
            //连接盒子后连接wifi，根据wifi取shop,如果没有取到，搜索附近商家
//            isShowedNotSeenMessage = false;？
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                if (!wifiManager.isWifiEnabled() || !mBluetoothAdapter.isEnabled()) {//没开wifi或者没开蓝牙，不让拍，继续请求用户给我权限
//                    //强制开蓝牙，开WiFi
//                    Toast.makeText(PaiYiPaiActivity.this, "请开启蓝牙和wifi后重试", Toast.LENGTH_SHORT).show();
//                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//                } else {
//                    try {
//                        showDialog();
//                    } catch (Exception e) {
//                        Log.e("showDialog", "no acitivity to depend on");
//                        e.printStackTrace();
//                    }
//                    isBeaconWifiConnected = false;//既然走到这里肯定没有连接ibeacon存储的wifi
//                    //开始扫描beacon
//                    new CountDownTimer(30 * 1000, 1000) {//三十秒之后都没连上，应该是没有盒子，正常测试时最慢大概不到30秒就连上了
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            connectToService();
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            if (!isBeaconWifiConnected) {
//                                dismiss();
//                                if (!isShowedNotSeenMessage) {
//                                    isShowedNotSeenMessage = true;
//                                    Toast.makeText(PaiYiPaiActivity.this, "智能互联设备距离较远", Toast.LENGTH_SHORT).show();
//                                    //重新判断数据流量是否已连接
//                                    isDataInternet = isDataInternet();
//                                    //重新判断WiFi是否已连接
//                                    isWifiConnected = isWifi();
//                                }
//                            }
//                        }
//                    }.start();
//                }
//            } else {
//                Toast.makeText(PaiYiPaiActivity.this, "建议先手动联网后再试", Toast.LENGTH_SHORT).show();
//            }
            showToast("抱歉，您当前没有网络");
        }
    }

//    private void connectToService() {
//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                try {
//                    beaconManager.startRanging(ALL_BEACONS_REGION);
//                    beaconManager.startBlePeripheralRanging();
//                    beaconManager.startMonitoring(ALL_BEACONS_REGION);
//                } catch (RemoteException e) {
//                    dismiss();
//                    if (!isShowedNotSeenMessage) {
//                        isShowedNotSeenMessage = true;
//                        //重新判断数据流量是否已连接
//                        isDataInternet = isDataInternet();
//                        //重新判断WiFi是否已连接
//                        isWifiConnected = isWifi();
//                        Toast.makeText(PaiYiPaiActivity.this, "智能互联设备距离较远", Toast.LENGTH_LONG).show();
//                    }
//                    Log.e("beaconManager", "Cannot start ranging");
//
//                }
//            }
//        });
//    }

    /**
     * 获取WiFi MAC地址后请求服务器给相应的shopId  根据此shopId去各个界面做不同逻辑
     */
    private void getShopIdByWifi(String beaconMac) {
        //获取当前路由的MAC地址(服务端要根据这个地址去查ShopID)
        WifiManager mWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        String netMac = null;
        if (mWifi.isWifiEnabled()) {
            WifiInfo wifiInfo = mWifi.getConnectionInfo();
            netMac = wifiInfo.getBSSID();
        }//获取到路由MAC地址 请求服务器给shopId
        if (shopId == 0l) {
            String mac = "";
            if (beaconMac != null) {
                mac = beaconMac;
            } else {
                mac = netMac;
            }
            Log.i("testMac",mac);
            IntrestBuyNet.findByWifiId(mac, new HandleSuccess<StoreByWifi>() {
                @Override
                public void success(StoreByWifi s) {
                    if (s.getStatus() == 1) {
                        if (s.getData() != null) {
                            StoreByWifi.DataBean.HdMallStoreBean mallStore = s.getData().getHdMallStore();
                            StoreByWifi.DataBean.HdFoodStoreBean foodStore = s.getData().getHdFoodStore();
                            if (mallStore != null) {
                                shopId = mallStore.getId();
                                category = mallStore.getCategoryType();
                                sdv_logo.setImageURI(mallStore.getLogoImg() + Common.MORESMALLERPICPARAM);
                                shopName = mallStore.getName();
                                tv_shopname.setText(shopName);
                                if (mallStore.getCreditLevel() != null) {
                                    room_ratingbar.setNumStars(mallStore.getCreditLevel());
                                } else {
                                    room_ratingbar.setNumStars(1);
                                }
                                if (city != null) {
                                    Double dis = LonLat.getDistance(city.getLongitude(), city.getLatitude(), mallStore.getLongitude(), mallStore.getLatitude());
                                    tv_distance.setText(String.format("%.1f", dis) + "km");
                                }

                                if (mallStore.getNotice() != null) {
                                    tv_notice.setText("公告：" + mallStore.getNotice());
                                } else {
                                    tv_notice.setText("暂无公告");
                                }
                                Toast.makeText(PaiYiPaiActivity.this, "欢迎光临" + mallStore.getName(), Toast.LENGTH_LONG).show();
                                ll_shop_info.setVisibility(View.VISIBLE);
                                tv_close.setVisibility(View.VISIBLE);
                                ll_shop_info.setAnimation(AnimationUtil.moveFromTopToView());
                                if (isActivityOn) {
                                    showPaiYiPaiDialogWithDatas();
                                }
                            } else if (foodStore != null) {
                                shopId = foodStore.getId();
                                category = foodStore.getCategoryType();
                                ll_shop_info.setVisibility(View.VISIBLE);
                                tv_close.setVisibility(View.VISIBLE);
                                sdv_logo.setImageURI(foodStore.getLogoImg() + Common.MORESMALLERPICPARAM);
                                shopName = foodStore.getName();
                                tv_shopname.setText(shopName);
                                if (foodStore.getCreditLevel() != null) {
                                    room_ratingbar.setNumStars(foodStore.getCreditLevel());
                                } else {
                                    room_ratingbar.setNumStars(1);
                                }
                                Double dis = LonLat.getDistance(city.getLongitude(), city.getLatitude(), foodStore.getLongitude(), foodStore.getLatitude());
                                tv_distance.setText(String.format("%.1f", dis) + "km");
                                if (foodStore.getNotice() != null) {
                                    tv_notice.setText("公告：" + foodStore.getNotice());
                                } else {
                                    tv_notice.setText("暂无公告");
                                }
                                Toast.makeText(PaiYiPaiActivity.this, "欢迎光临" + foodStore.getName(), Toast.LENGTH_LONG).show();
                                if (isActivityOn) {
                                    showPaiYiPaiDialogWithDatas();
                                }
                            } else {
                                if (isActivityOn) {
                                    //路由信息没有取到 则去查询附近商家
                                    Intent intent = new Intent();
                                    intent.setClass(PaiYiPaiActivity.this, PaiYiPaiNearbyShopsActivity.class);
                                    startActivityForResult(intent, 1100);
                                }
                            }
                        } else {
                            if (isActivityOn) {
                                //路由信息没有取到 则去查询附近商家
                                Intent intent = new Intent();
                                intent.setClass(PaiYiPaiActivity.this, PaiYiPaiNearbyShopsActivity.class);
                                startActivityForResult(intent, 1100);
                            }
                        }
                    } else {
                        if (isActivityOn) {
                            //路由信息没有取到 则去查询附近商家
                            Intent intent = new Intent();
                            intent.setClass(PaiYiPaiActivity.this, PaiYiPaiNearbyShopsActivity.class);
                            startActivityForResult(intent, 1100);
                        }
                    }
                }
            });
        } else {
            if (isActivityOn) {
                showPaiYiPaiDialogWithDatas();
            }
        }

    }

    private void showPaiYiPaiDialogWithDatas() {
        Intent intent = new Intent(PaiYiPaiActivity.this, PaiYiPaiAdDialogActivity.class);
        intent.putExtra("shopId", shopId);
        intent.putExtra("category", category);
        startActivity(intent);

    }

    @Override
    protected void onStop() {
//        unregisterReceiver(rssiReceiver);
        isActivityOn = false;
//        try {
//            beaconManager.stopRanging(ALL_BEACONS_REGION);
//            beaconManager.stopMonitoring(ALL_BEACONS_REGION);
//        } catch (Exception e) {
//            // TODO: handle exception
//            Log.e("beaconManager", "stoping ranging fails");
//        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();
        if (!isWifiConnected && !isDataInternet) {
            isDataInternet = isDataInternet();
            isWifiConnected = isWifi();
        }
        isActivityOn = true;
        SharedPreferences preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        paiSound = preferences.getBoolean("paisound", true);
        touchAble = true;///////////////////////////////////////
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        touchAble = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1100) {
            if (data != null) {
                isClosedShopManual = false;
                Shop shop = (Shop) data.getSerializableExtra("shop");
                shopId = shop.getId();
                category = shop.getCategory();
                sdv_logo.setImageURI(shop.getLogoPath());
                shopName = shop.getShopName();
                tv_shopname.setText(shopName);
                room_ratingbar.setNumStars(shop.getLevel());
                tv_distance.setText(shop.getDistance() + "km");
                tv_notice.setText("公告：" + shop.getIntroduce());
                new CountDownTimer(1 * 1000, 1000) {//1秒之后再去显示动画，不然首次偶尔动画不管用
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        ll_shop_info.setVisibility(View.VISIBLE);
                        tv_close.setVisibility(View.VISIBLE);
                        ll_shop_info.setAnimation(AnimationUtil.moveFromTopToView());
                    }
                }.start();
                if (isActivityOn) {
                    showPaiYiPaiDialogWithDatas();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
