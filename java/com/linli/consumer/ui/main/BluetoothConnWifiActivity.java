package com.linli.consumer.ui.main;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easimote.sdk.BeaconManager;
import com.easimote.sdk.Region;
import com.easimote.sdk.beacon;
import com.easimote.sdk.util.LogService;
import com.easimote.sdk.util.utils;
import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.StoreByWifi;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.BeaconParser;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.Event;

import java.util.ArrayList;

public class BluetoothConnWifiActivity extends BaseActivity {
    private static final String TAG = "wifi_connection";

    private static final int REQUEST_ENABLE_BT = 1234;
    public static final String DEBUGBASE = "恭喜！已成功连接";
    public static final Region ALL_BEACONS_REGION = new Region("rid", null, null, null);
    public static boolean isWifiConnected = false;
    public static BeaconManager beaconManager;
    private NotificationManager notificationManager;
    private BroadcastReceiver rssiReceiver;
    private ArrayList<Event> eventListData;
    private static String[] seq = new String[10];
    static int seqcount = 0;
    int newminor = 0;

    private TextView tv_shopinfo;
    private TextView event_btn;
    private ImageView iv_close;//关闭界面按钮
    private TextView tv_alert;//状态文字提示
    private ProgressBar pb_conning;//正在连接进度条
    private ImageView iv_icon;//连接状态图标

    private static WifiManager wifiManager;
    private static String ssid;
    private static String passwd;
    private static WifiConfiguration wifiConfig;

    private static final int REQUEST_ENABLE_LOC=0;
    private boolean okClose = false;//是否点击确认按钮关闭
    private boolean okCloseListener = false;//是否可以关闭WiFi改变的监听了
    private int connectCounts = 1;//连接失败的次数

    //  private void mayRequestLocation() {
//	 if (Build.VERSION.SDK_INT >= 23) {
//	           int checkCallPhonePermission = ContextCompat.(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION);
//	           if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
//	             //判断是否需要 向用户解释，为什么要申请该权限
//	             if(ActivityCompat.shouldShowRequestPermissionRationale(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION))
//	             Toast.makeText(getApplicationContext(),"权限申请", 1).show();
//
//	               ActivityCompat.requestPermissions(this ,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_FINE_LOCATION);
//	               return;
//	           }else{
//
//	           }
//	       } else {
//
//	       }
//	    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bluetooth_conn_wifi;
    }

    @Override
    protected void initView() {
        dismiss();
        tv_shopinfo = findView(R.id.tv_shopinfo);

        event_btn = findViewClick(R.id.event_btn);
        iv_close = findViewClick(R.id.iv_close);
        tv_alert = findView(R.id.tv_alert);
        pb_conning = findView(R.id.pb_conning);
        iv_icon = findView(R.id.iv_icon);
        findViewClick(R.id.relativeLayout2);
        setViewsStatus(0);//0默认 1搜索宝盒WiFi中  2连接宝盒WiFi中 3连接成功 4已连接但需要等几秒连接稳定的状态
    }

    private void setViewsStatus(int status) {
        pb_conning.setVisibility(View.INVISIBLE);
        iv_icon.setVisibility(View.INVISIBLE);
        switch (status){
            case 0:
                tv_shopinfo.setText("");
                tv_alert.setText("准备连接中");
                pb_conning.setVisibility(View.INVISIBLE);
                iv_icon.setVisibility(View.VISIBLE);
                iv_icon.setImageResource(R.mipmap.ic_dl_help);
                event_btn.setText("开始连接");
                break;
            case 1:
                tv_shopinfo.setText("");
                tv_alert.setText("正在搜索宝盒");
                pb_conning.setVisibility(View.VISIBLE);
                iv_icon.setVisibility(View.INVISIBLE);
                event_btn.setText("请稍后...");
                break;
            case 2:
                tv_shopinfo.setText("");
                tv_alert.setText("正在连接宝盒WiFi");
                pb_conning.setVisibility(View.VISIBLE);
                iv_icon.setVisibility(View.INVISIBLE);
                event_btn.setText("请稍后...");
                break;
            case 3:
                tv_alert.setText("连接成功");
                pb_conning.setVisibility(View.INVISIBLE);
                iv_icon.setVisibility(View.VISIBLE);
                iv_icon.setImageResource(R.mipmap.ic_dialog_coll_success_right);
                event_btn.setText("我知道了");
                event_btn.setClickable(true);
                break;
            case 4://这个状态是等待WiFi连接稳定了再去请求数据
                tv_shopinfo.setText("成功连接"+wifiManager.getConnectionInfo().getSSID()+"，正在匹配WiFi信息。");
                tv_alert.setText("匹配WiFi信息");
                pb_conning.setVisibility(View.VISIBLE);
                iv_icon.setVisibility(View.INVISIBLE);
                break;
            case 5:
                tv_shopinfo.setText("");
                tv_alert.setText("抱歉，暂未发现可用的连接设备");
                pb_conning.setVisibility(View.INVISIBLE);
                iv_icon.setVisibility(View.INVISIBLE);
                event_btn.setText("再次尝试");
                event_btn.setClickable(true);
                break;
            case 6:
                tv_shopinfo.setText("");
                tv_alert.setText("连接失败");
                pb_conning.setVisibility(View.INVISIBLE);
                iv_icon.setVisibility(View.INVISIBLE);
                event_btn.setText("再次尝试");
                event_btn.setClickable(true);
                break;
            case 7:
                tv_alert.setText("已有连接");
                pb_conning.setVisibility(View.INVISIBLE);
                iv_icon.setVisibility(View.VISIBLE);
                iv_icon.setImageResource(R.mipmap.ic_dialog_coll_success_right);
                event_btn.setText("重新连接");
                break;
           default:
               break;
        }

    }

    @Override
    protected void initData() {
        utils.setBeaconParserInteface(new BeaconParser());

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        // Configure verbose debug logging.
        LogService.enableDebugLogging(false);

        rssiReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                Log.d("Rssi", "RSSI changed"+intent.toString());
                // wifiInfo.setText(obtainWifiInfo());
                if (wifiManager == null) {
                    wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                }

                if (wifiConfig != null && wifiManager.getConnectionInfo().getSSID().equals(wifiConfig.SSID) && isWifiConnected == false) {
                    new CountDownTimer(2 * 1000, 1000) {//2秒之后在去请求数据
                        @Override
                        public void onTick(long millisUntilFinished) {
                            setViewsStatus(4);
                        }
                        @Override
                        public void onFinish() {
                            findShopInfo(wifiManager.getConnectionInfo().getBSSID(),null);
                        }
                    }.start();
                    isWifiConnected = true;
                    try {
                        beaconManager.stopRanging(ALL_BEACONS_REGION);
                        beaconManager.stopMonitoring(ALL_BEACONS_REGION);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
                if (okCloseListener) {
                    okCloseListener = false;
                    try {
                        unregisterReceiver(rssiReceiver);
                        Log.i("test", "停了");
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }
        };

//        if (!CheckWifiandBLE()) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(BluetoothConnWifiActivity.this);
//            builder.setMessage("请打开您的蓝牙与wifi");
//            builder.setTitle("温馨提示：");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//
//            });
//            builder.create().show();
//        }


        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // config event list to save all events
        eventListData = new ArrayList<Event>();

        // Configure BeaconManager.
        beaconManager = new BeaconManager(this);
        // beaconManager.setForegroundScanPeriod(5,30);

        beaconManager.setOnNewBeaconFoundListener(new BeaconManager.onNewBeaconFoundListener() {

            @Override
            public void onNewBeaconFound(final beacon newComingBeacon) {
                /**
                 * when a beacon event happens, we should request data from
                 * server using: "beacon Proximity UUID" "beacon major"
                 * "beacon minor" the event it makes contain:
                 * eventType:"Beacon_into" eventRelateBeaconUUID: the beacon's
                 * Proximity UUID eventMessage: Abstract which server send
                 */

                /**
                 * check if the new beacon is in interest list. if in the
                 * interest list, we consider this beacon event type is also a
                 * interest event.
                 */
                //连接的次数大于10就算了吧
//                connectCounts++;
//                if (connectCounts > 10){
//                    Log.i("test","连接失败次数过多"+connectCounts);
//                    connectCounts = 1;
//                    setViewsStatus(5);
//                    try {
//                        beaconManager.stopRanging(ALL_BEACONS_REGION);
//                        beaconManager.stopMonitoring(ALL_BEACONS_REGION);
//                    } catch (RemoteException e1) {
//                        // TODO Auto-generated catch block
//                        e1.printStackTrace();
//                    }
//                    return;
//                }
//                Thread thread = new Thread(new Runnable() {

//                    @Override
//                    public void run() {
                        // TODO Auto-generated method stub
                        synchronized (BluetoothConnWifiActivity.this) {
                            if (!isWifiConnected) {
                                if (!wifiManager.isWifiEnabled()) {
                                    wifiManager.setWifiEnabled(true);
                                    while (!wifiManager.isWifiEnabled()) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                try {
                                    beaconManager.stopRanging(ALL_BEACONS_REGION);
                                    beaconManager.stopMonitoring(ALL_BEACONS_REGION);
                                } catch (RemoteException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }

                                wifiConfig = new WifiConfiguration();
                                wifiConfig.allowedAuthAlgorithms.clear();
                                wifiConfig.allowedGroupCiphers.clear();
                                wifiConfig.allowedKeyManagement.clear();
                                wifiConfig.allowedPairwiseCiphers.clear();
                                wifiConfig.allowedProtocols.clear();
                                wifiConfig.hiddenSSID = false;
                                String s1 = newComingBeacon.getWifiSsid();
                                // wifiConfig.SSID = String.format("\"%s\"",
                                // newComingBeacon.getWifiSsid());
                                wifiConfig.SSID = "\"" + newComingBeacon.getWifiSsid() + "\"";
                                wifiConfig.preSharedKey = String.format("\"%s\"", newComingBeacon.getWifiPassword());
                                wifiConfig.preSharedKey = "\"" + newComingBeacon.getWifiPassword() + "\"";
                                wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                                // wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                                wifiConfig.status = WifiConfiguration.Status.ENABLED;
                                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                                Log.i("test","ssid:"+newComingBeacon.getWifiSsid());
                                Log.i("test","pass:"+newComingBeacon.getWifiPassword());
                                Log.i("test","pass:"+newComingBeacon.toString());
                                if (newComingBeacon.getCompanyId() != null) {//**************************************************一代盒子逻辑-有companyId的破盒子已淘汰
                                    if ((newComingBeacon.getWifiSsid() + newComingBeacon.getWifiPassword()).trim().contains("assword")) {
                                        Log.i("test", "过滤了乱码的情况:" + (newComingBeacon.getWifiSsid() + newComingBeacon.getWifiPassword()).trim());
//                                        try {
//                                            beaconManager.stopRanging(ALL_BEACONS_REGION);
//                                            beaconManager.stopMonitoring(ALL_BEACONS_REGION);
//                                        } catch (Exception e) {
//                                            // TODO Auto-generated catch block
//                                            e.printStackTrace();
//                                        }
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
                                                setViewsStatus(5);
//                                            }
//                                        });
                                        return;
                                    }
                                    if (!wifiManager.getConnectionInfo().getSSID().equals(wifiConfig.SSID) || !wifiManager
                                            .getConnectionInfo().getSupplicantState().name().equals("COMPLETED")) {
                                        // remember id
                                        final int[] netId = {0};
                                        boolean flag = false;
                                        for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
                                            if (wifiConfiguration.SSID.equals(wifiConfig.SSID)) {
                                                flag = true;
                                                netId[0] = wifiConfiguration.networkId;
                                                break;
                                            }
                                        }
                                        if (flag) {
                                            wifiManager.removeNetwork(netId[0]);
                                        }
                                        setViewsStatus(2);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                netId[0] = wifiManager.addNetwork(wifiConfig);
                                                wifiManager.disconnect();
                                                wifiManager.enableNetwork(netId[0], true);
                                                wifiManager.reconnect();
                                            }
                                        }).start();
                                        try {
                                            Thread.sleep(4000);//休眠4秒钟，应该是不管用的，是否可删，检查是不是因为这个导致连WiFi时此页面卡死
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        Log.d(TAG, "enableNetwork connected=" + wifiManager.getConnectionInfo().toString());
                                        if (wifiManager.getConnectionInfo().getSSID().equals(wifiConfig.SSID) && wifiManager
                                                .getConnectionInfo().getSupplicantState().name().equals("COMPLETED")) {
                                            isWifiConnected = true;
                                        }
                                    }
                                }else {//*************************************************************************************************二代盒子逻辑-无companyId
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
                                            setViewsStatus(2);
//                                        }
//                                    });
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            int netId = wifiManager.addNetwork(wifiConfig);
                                            wifiManager.disconnect();
                                            wifiManager.enableNetwork(netId, true);
                                            wifiManager.reconnect();
                                        }
                                    }).start();
                                    try {
                                        beaconManager.stopRanging(ALL_BEACONS_REGION);
                                        beaconManager.stopMonitoring(ALL_BEACONS_REGION);
                                    } catch (RemoteException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                                okCloseListener = true;
                            }
                        }
//                    }
//                });
//                thread.start();

            }
        });
        if (CommonUtil.isWifi(this)){
            if (wifiManager == null) {
                wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
            }
            findShopInfo(wifiManager.getConnectionInfo().getBSSID(),7);
        }
    }

    /**
     * 根据WiFi Mac地址查询商家信息
     * @param bssid
     */
    private void findShopInfo(String bssid, final Integer status) {
        Log.i("test","id:"+bssid);
        IntrestBuyNet.findByWifiId(bssid, new HandleSuccess<StoreByWifi>() {
            @Override
            public void success(StoreByWifi s) {

                if (s.getStatus() == 1) {
                    if (s.getData() != null) {
                        StoreByWifi.DataBean.HdMallStoreBean mallStore = s.getData().getHdMallStore();
                        StoreByWifi.DataBean.HdFoodStoreBean foodStore = s.getData().getHdFoodStore();
                        String shopName = null;
                        if (mallStore != null) {

                            shopName = mallStore.getName();
                        } else if (foodStore != null) {
                            shopName = foodStore.getName();
                        }
                        if (shopName != null){
                            if (status != null){
                                setViewsStatus(status);
                            }else {
                                okClose = true;
                                setViewsStatus(3);
                            }
                            tv_shopinfo.setText(DEBUGBASE + "“"+shopName+"”的宝盒WiFi，WiFi名称："+wifiManager.getConnectionInfo().getSSID());
                        }else {
                            if (status != null){
                                setViewsStatus(status);
                            }else {
                                okClose = true;
                                setViewsStatus(3);
                            }
                            tv_shopinfo.setText(DEBUGBASE + "宝盒WiFi，WiFi名称："+wifiManager.getConnectionInfo().getSSID());
                        }
                    } else {
                        if (status != null){
                            setViewsStatus(status);
                        }else {
                            okClose = true;
                            setViewsStatus(3);
                        }
                        tv_shopinfo.setText("您当前正在使用" + "WiFi，WiFi名称："+wifiManager.getConnectionInfo().getSSID());
                    }
                } else {
                    if (status != null){
                        setViewsStatus(status);
                    }else {
                        okClose = true;
                        setViewsStatus(3);
                    }
                    tv_shopinfo.setText("您当前正在使用" + "WiFi，WiFi名称："+wifiManager.getConnectionInfo().getSSID());
                }
            }
        });

    }

    /**
     * check wifi与ble状态
     *
     * @return
     */
    private boolean CheckWifiandBLE() {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled())
            return false;

        return true;
    }
    private void connectToService() {
        // getActionBar().setSubtitle("Searching services...");

        if (eventListData.isEmpty()) {
            beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
                @Override
                public void onServiceReady() {
                    try {
                        beaconManager.startRanging(ALL_BEACONS_REGION);
                        beaconManager.startBlePeripheralRanging();
                        beaconManager.startMonitoring(ALL_BEACONS_REGION);
                    } catch (RemoteException e) {
                        setViewsStatus(0);
                        showToast("未能开启扫描");
                        Log.e("beaconManager", "Cannot start ranging");
                    }
                }
            });
        }
    }
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.event_btn:
                if (okClose){
                    finish();
                }else {
                    event_btn.setClickable(false);
                    okCloseListener = false;
                    registerReceiver(rssiReceiver,filter);
                    event_btn.setText("正在连接");
                    readyToConnectService();
                }
                break;
            case R.id.iv_close:
                finish();
                break;
            default:
                break;
        }
    }
    IntentFilter filter = new IntentFilter(WifiManager.RSSI_CHANGED_ACTION);
    private void readyToConnectService() {
        isWifiConnected = false;

//                if(!beaconManager.isLocationEnabled()){
//
//                    Intent GPSIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    //Intent GPSIntent = new Intent()
//                    startActivityForResult(GPSIntent, REQUEST_ENABLE_LOC);
//
//                }
        if (!beaconManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            setViewsStatus(1);
            connectToService();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {

        for (int i = 0; i < 12; i++) {
            notificationManager.cancel(i);
        }
        beaconManager.disconnect();
//        wifiManager.disconnect();//退出不断开WiFi连接

        super.onDestroy();
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check if device supports Bluetooth Low Energy.
        if (!beaconManager.hasBluetooth()) {
            showToast("当前设备不支持蓝牙连接");
            finish();
            return;
        }

        // If Bluetooth is not enabled, let user enable it.

        if (!beaconManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        try {
            beaconManager.stopRanging(ALL_BEACONS_REGION);
            beaconManager.stopMonitoring(ALL_BEACONS_REGION);
            unregisterReceiver(rssiReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onStop();
    }
    /**
     * get a new ID form ID pool, just a simple pool, we leave at most 3
     * messages in one event-type.
     *
     * @param notificationType
     * @return
     */
    private int IDForRegionIn = 0;
    private int IDForRegionLeave = 3;
    private int IDForBeaconIn = 6;
    private int IDForBeaconLeave = 9;

    private int getNewNotificationID(int notificationType) {
        int newID = 0;
        switch (notificationType) {
            case 1:
                newID = (IDForRegionIn++) % 3;
                break;
            case 2:
                newID = (IDForRegionLeave++) % 3 + 3;
                break;
            case 3:
                newID = (IDForBeaconIn++) % 3 + 6;
                break;
            case 4:
                newID = (IDForBeaconLeave++) % 3 + 9;
                break;
            default:
        }
        return newID;
    }
    /**
     * picture processing, cut a picture to round corners
     *
     * @param srcBitmap
     * @return
     */
    public Bitmap getRoundedBitmap(Bitmap srcBitmap) {

        Bitmap bgBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bgBitmap);

        Paint mPaint = new Paint();
        Rect mRect = new Rect(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        RectF mRectF = new RectF(mRect);

        mPaint.setAntiAlias(true);

        float roundPx = 20;
        mCanvas.drawRoundRect(mRectF, roundPx, roundPx, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCanvas.drawBitmap(srcBitmap, mRect, mRect, mPaint);

        return bgBitmap;
    }

    void updateDebugmsg() {
        String dmsg = null;
        for (int i = 0; i < seqcount; i++) {
            dmsg = dmsg + seq[i].substring(seq[i].length() - 3) + " ";
        }
        tv_shopinfo.setText(dmsg);
        tv_shopinfo.invalidate();
    }
}
