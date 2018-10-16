package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.StoreByWifi;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

public class YaoYiYaoActivity extends BaseActivity implements SensorEventListener {
    private Button btn_redbag,btn_coupon,btn_bill;
    private TextView tv_tag;//等待提示语
    private LinearLayout ll_waitingtext;//摇一摇之后显示2秒后 隐藏
    private SensorManager sensorManager = null;
    private Vibrator vibrator = null;
    private int type = 1;//1红包  2优惠券  3账单  默认1红包
    private boolean isYaoING;//是否正在摇
    private AppContext appContext;
    private User user;
    public static Integer category;//店铺类型
    public static  Long orderId;//订单ID
    @Override
    protected int getLayoutId() {
        return R.layout.activity_yao_yi_yao;
    }

    @Override
    protected void initView() {
        init();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        setDefaultViews();
        switch (type){
            case 1:
                btn_redbag.setBackgroundResource(R.drawable.btn_orange);
                break;
            case 2:
                btn_coupon.setBackgroundResource(R.drawable.btn_orange);
                break;
            case 3:
                btn_bill.setBackgroundResource(R.drawable.btn_orange);
                break;
        }
    }

    @Override
    protected void initData() {
        dismiss();
    }
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.btn_redbag:
                setDefaultViews();
                btn_redbag.setBackgroundResource(R.drawable.btn_orange);
                type = 1;
                break;
            case R.id.btn_coupon:
                setDefaultViews();
                btn_coupon.setBackgroundResource(R.drawable.btn_orange);
                type = 2;
                break;
            case R.id.btn_bill:
                setDefaultViews();
                btn_bill.setBackgroundResource(R.drawable.btn_orange);
                type = 3;
                break;
            default:
                break;
        }
    }

    private void setDefaultViews() {
        btn_redbag.setBackgroundResource(R.drawable.btn_gray_background_whitetext);
        btn_coupon.setBackgroundResource(R.drawable.btn_gray_background_whitetext);
        btn_bill.setBackgroundResource(R.drawable.btn_gray_background_whitetext);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isYaoING = false;
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),sensorManager.SENSOR_DELAY_NORMAL);
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView textView = findViewClick(R.id.tv_head_name);
        textView.setText("摇一摇");
        ll_waitingtext = findView(R.id.ll_waitingtext);
        btn_redbag = findViewClick(R.id.btn_redbag);
        btn_coupon = findViewClick(R.id.btn_coupon);
        btn_bill = findViewClick(R.id.btn_bill);
        tv_tag = findView(R.id.tv_tag);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isYaoING) {
            int sensorType = event.sensor.getType();
            //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
            float[] values = event.values;
            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
                        .abs(values[2]) > 17)) {
                    Log.d("sensor x ", "============ values[0] = " + values[0]);
                    Log.d("sensor y ", "============ values[1] = " + values[1]);
                    Log.d("sensor z ", "============ values[2] = " + values[2]);

                    //摇动手机后，再伴随震动提示~~
                    vibrator.vibrate(500);
                    ll_waitingtext.setVisibility(View.VISIBLE);
                    switch (type) {//1红包  2优惠券  3账单
                        case 1:
                            new CountDownTimer(3 * 1000, 1000) {//三秒之后
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    isYaoING = true;
                                    tv_tag.setText("正在搜索附近红包");
                                }

                                @Override
                                public void onFinish() {
                                    isYaoING = false;
                                    ll_waitingtext.setVisibility(View.GONE);
                                    Log.i("test", "one time");
                                    Toast.makeText(YaoYiYaoActivity.this, "好运总在下一次！", Toast.LENGTH_SHORT).show();
                                }
                            }.start();
                            break;
                        case 2:
                            new CountDownTimer(3 * 1000, 1000) {//三秒之后
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    isYaoING = true;
                                    tv_tag.setText("正在搜索附近优惠");
                                }

                                @Override
                                public void onFinish() {
                                    isYaoING = false;
                                    ll_waitingtext.setVisibility(View.GONE);
                                    Log.i("test", "one time");
                                    Toast.makeText(YaoYiYaoActivity.this, "抱歉，再接再厉哦~", Toast.LENGTH_SHORT).show();
                                }
                            }.start();
                            break;
                        case 3://取当前WiFi物理地址查询对应商家
                            new CountDownTimer(3 * 1000, 1000) {//三秒之后
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    isYaoING = true;
                                    tv_tag.setText("正在搜索未付款订单");
                                    ll_waitingtext.setVisibility(View.GONE);
                                    getShopIdByWifi();
                                }

                                @Override
                                public void onFinish() {
                                    isYaoING = false;
                                }
                            }.start();

                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    /**
     * 获取WiFi MAC地址后请求服务器给相应的shopId  根据此shopId去各个界面做不同逻辑
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
            IntrestBuyNet.findByWifiId(netMac, new HandleSuccess<StoreByWifi>() {
                @Override
                public void success(StoreByWifi s) {
                    if (s.getStatus() == 1){
                        if (s.getData() != null){
                            StoreByWifi.DataBean.HdMallStoreBean mallStore = s.getData().getHdMallStore();
                            StoreByWifi.DataBean.HdFoodStoreBean foodStore = s.getData().getHdFoodStore();
                            if (mallStore != null){
                                Long shopId = mallStore.getId();
                                category = mallStore.getCategoryType();
                                //根据shopid和userID取订单列表
                                request_payingorders(shopId);
                            }else if (foodStore != null){
                                Long shopId = foodStore.getId();
                                category = foodStore.getCategoryType();
                                //根据shopid和userID取订单列表
                                request_payingorders(shopId);
                            }else {
                                Toast.makeText(YaoYiYaoActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Log.i("test", "one time");
                            Toast.makeText(YaoYiYaoActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(YaoYiYaoActivity.this,"请确认已连接店铺有效区域内网络",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            Toast.makeText(YaoYiYaoActivity.this,"您还没有连接店铺WiFi",Toast.LENGTH_SHORT).show();
        }
    }
    /*
    根据shopid和userID去下个界面取订单列表
     */
    private void request_payingorders(Long shopId) {
        ll_waitingtext.setVisibility(View.GONE);
        Intent intent = new Intent(YaoYiYaoActivity.this,YaoOrdersToPayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("shopid",shopId);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
