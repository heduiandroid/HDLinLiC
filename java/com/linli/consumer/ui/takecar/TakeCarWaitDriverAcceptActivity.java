package com.linli.consumer.ui.takecar;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.DriverInfoBean;
import com.linli.consumer.bean.DriverPosBean;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.TakeCarNeedBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.DrivingRouteOverlay;
import com.linli.consumer.utils.LonLat;

import org.json.JSONObject;

import java.text.DecimalFormat;

import io.rong.imkit.RongIM;

import static com.linli.consumer.ui.main.YZXIndexActivity.KEY_EXTRAS;
import static com.linli.consumer.ui.main.YZXIndexActivity.KEY_MESSAGE;
import static com.linli.consumer.ui.main.YZXIndexActivity.MESSAGE_RECEIVED_ACTION;

public class TakeCarWaitDriverAcceptActivity extends BaseActivity {
    private int maxTime = 180;//180秒（三分钟）还没人接单 直接取消掉？
    private RelativeLayout rl_wait_for_accept,rl_map;
    private TextView tv_time,tv_status,tv_start_place,tv_end_place;
    private ImageView iv_progress_flash;
    private TextView tv_time_counts;
    private TextView cancelOrder;
    private TextView tv_driver_status;
    private SimpleDraweeView sdv_driver_portraint;
    private TextView tv_family_name;
    private RatingBar rb_driver_level;
    private TextView tv_driver_persent;
    private TextView tv_car_infos;
    private ImageView iv_message_driver,iv_call_driver;
    private AppContext appContext;
    private User user;
    private TakeCarNeedBean.DataBean nearestNeed = null;
    private Long takeCarNeedId = 0L;
    private int count = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("test","加一秒");
                    tv_time_counts.setText(String.valueOf(count));
                    count++;
                    if (count > maxTime){
                        request_cancel_takecar();//180秒（三分钟）还没人接单 直接取消掉？
                    }else {
                        sendEmptyMessageDelayed(1,1000);
                    }
                    break;
                case 2:
                    Log.i("test","请求司机位置");
                    request_driver_position();
                    sendEmptyMessageDelayed(2,10000);
                    break;
            }
        }
    };
    private Animation progressbarAnim;
    private MapView mv_location_map;
    private BaiduMap baiduMap;
    private String driverPhone = null;//司机电话
    private Long driverId = null;//司机ID
    private String driverName = null;//司机姓氏

    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_car_wait_driver_accept;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView header = findViewClick(R.id.tv_head_name);
        header.setText("我的需求");
        cancelOrder = findViewClick(R.id.tv_head_right);
        cancelOrder.setText("取消订单");
        cancelOrder.setVisibility(View.GONE);
        findView(R.id.iv_head_right).setVisibility(View.GONE);
        tv_time = findView(R.id.tv_time);
        tv_status = findView(R.id.tv_status);
        tv_start_place = findView(R.id.tv_start_place);
        tv_end_place =  findView(R.id.tv_end_place);
        iv_progress_flash = findView(R.id.iv_progress_flash);
        tv_time_counts = findView(R.id.tv_time_counts);
        tv_driver_status = findView(R.id.tv_driver_status);
        sdv_driver_portraint = findView(R.id.sdv_driver_portraint);
        tv_family_name = findView(R.id.tv_family_name);
        rb_driver_level = findView(R.id.rb_driver_level);
        tv_driver_persent = findView(R.id.tv_driver_persent);
        tv_car_infos = findView(R.id.tv_car_infos);
        iv_message_driver = findViewClick(R.id.iv_message_driver);
        iv_call_driver = findViewClick(R.id.iv_call_driver);

        rl_wait_for_accept = findView(R.id.rl_wait_for_accept);
        rl_map = findView(R.id.rl_map);
        mv_location_map = findView(R.id.mv_location_map);
        mv_location_map.showZoomControls(false);
        mv_location_map.showScaleControl(false);
        progressbarAnim = AnimationUtils.loadAnimation(this, R.anim.rolling_infinite);
        LinearInterpolator lin = new LinearInterpolator();
        progressbarAnim.setInterpolator(lin);
        iv_progress_flash.startAnimation(progressbarAnim);
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        takeCarNeedId = getIntent().getLongExtra("id",0L);
//        registJpush();
        baiduMap = mv_location_map.getMap();
        baiduMap.setMyLocationEnabled(true);
        dismiss();
        if (user != null){
            registerMessageReceiver();//////////
            request_userneedlist_takecar();
        }else {
            finish();
            UIHelper.togoTakeCarIndexActivity(this);
        }
    }


    private void request_userneedlist_takecar() {
        showSimpleDialog();
        IntrestBuyNet.findByDemandId(user.getId(), takeCarNeedId, new HandleSuccess<TakeCarNeedBean>() {
            @Override
            public void success(TakeCarNeedBean s) {
                dismissSimDialog();
                if (s.getStatus() == 1 && s.getData() != null){
                    nearestNeed = s.getData();
                }
                if (nearestNeed != null){
                    String status = "";
                    switch (nearestNeed.getStatus()){//需求状态:0-已取消  1-等待接单   2-进行中   3-已完成
                        case 0:
                            status = "已取消";
                            if (takeCarNeedId != 0L){
                                //显示地图views 并调整显示的文字
                                rl_map.setVisibility(View.VISIBLE);
                                startToDrawDrivingLine(nearestNeed.getStartingLog(),nearestNeed.getStartingLng(),nearestNeed.getEndLog(),nearestNeed.getEndLng());
                                tv_driver_status.setText("行程已取消");
                                request_driver_infos();
                            }else {
                                //关闭页面 去首页
                                TakeCarWaitDriverAcceptActivity.this.finish();
                                UIHelper.togoTakeCarIndexActivity(TakeCarWaitDriverAcceptActivity.this);
                                return;
                            }
                            break;
                        case 1:
                            status = "等待接单";
                            //显示等待接单的view 显示取消订单按钮
                            rl_wait_for_accept.setVisibility(View.VISIBLE);
                            cancelOrder.setVisibility(View.VISIBLE);
                            int tempCount = CommonUtil.timeSecondsLongToLong(nearestNeed.getCreateTime());
                            if (tempCount > 0){
                                count = tempCount;
                            }
                            handler.sendEmptyMessage(1);

                            break;
                        case 2:
                            status = "进行中";
                            //显示地图views 调整显示文字 且有两种显示1是司机赶往乘客地点显示取消订单按钮 2是司机与乘客赶往目的地点
                            rl_map.setVisibility(View.VISIBLE);
                            startToDrawDrivingLine(nearestNeed.getStartingLog(),nearestNeed.getStartingLng(),nearestNeed.getEndLog(),nearestNeed.getEndLng());
                            if (nearestNeed.getTravelState() == 1){//1-已接单   2-接到乘客   3-到达目的地  4-已关闭
                                cancelOrder.setVisibility(View.VISIBLE);
                                tv_driver_status.setText("司机已接单，正在赶来，请耐心等待...");
                            }else if (nearestNeed.getTravelState() == 2){
                                tv_driver_status.setText("正在赶往目的地...");
                            }
                            request_driver_infos();
                            break;
                        case 3:
                            status = "已完成";
                            if (takeCarNeedId != 0L){
                                //显示地图views 并调整显示文字
                                rl_map.setVisibility(View.VISIBLE);
                                startToDrawDrivingLine(nearestNeed.getStartingLog(),nearestNeed.getStartingLng(),nearestNeed.getEndLog(),nearestNeed.getEndLng());
                                tv_driver_status.setText("行程已完成");
                                request_driver_infos();
                            }else {
                                //关闭页面 去首页
                                TakeCarWaitDriverAcceptActivity.this.finish();
                                UIHelper.togoTakeCarIndexActivity(TakeCarWaitDriverAcceptActivity.this);
                                return;
                            }
                            break;
                        default:
                            //关闭页面 去首页
                            TakeCarWaitDriverAcceptActivity.this.finish();
                            UIHelper.togoTakeCarIndexActivity(TakeCarWaitDriverAcceptActivity.this);
                            break;
                    }
                    //填充数据
                    tv_start_place.setText(nearestNeed.getStartingPoint());
                    String strEnd = nearestNeed.getEndPoint();
                    if (nearestNeed.getType() != null && nearestNeed.getType() == 2){
                        strEnd = strEnd + " (转换文字内容)";
                    }
                    tv_end_place.setText(strEnd);
                    tv_status.setText(status);
                    tv_time.setText(CommonUtil.timeLongToString(nearestNeed.getCreateTime()));

                }else {
                    TakeCarWaitDriverAcceptActivity.this.finish();
                    UIHelper.togoTakeCarIndexActivity(TakeCarWaitDriverAcceptActivity.this);
                    return;
                }
            }
        });

    }
    /*
    查询司机信息
     */
    private void request_driver_infos() {
        if (nearestNeed != null){
            IntrestBuyNet.findDriverById(nearestNeed.getDriverId(), new HandleSuccess<DriverInfoBean>() {
                @Override
                public void success(DriverInfoBean s) {
                    if (s.getStatus() == 1 && s.getData() != null){
                        sdv_driver_portraint.setImageURI(s.getData().getImg());
                        tv_family_name.setText(s.getData().getSurname()+"师傅");
                        tv_car_infos.setText(s.getData().getLicensePlateNumber()+"  "+s.getData().getVehicleBrand()+"  "+s.getData().getVehicleColor());
                        driverPhone = s.getData().getPhone();
                        driverId = s.getData().getId();
                        driverName = s.getData().getSurname()+"师傅";
                        Float driverPersent = 5F;
                        DecimalFormat df = new DecimalFormat("0.0");
                        if (s.getData().getAverage() != 0F){
                            driverPersent = s.getData().getAverage();
                        }
                        tv_driver_persent.setText(df.format(driverPersent)+"分");
                        if (driverId != null && nearestNeed.getStatus() == 2){//进行中的才画小车
                            //添加心跳更新地点
                            handler.sendEmptyMessage(2);
                        }
                    }
                }
            });
        }
    }

    private Marker marker = null;
    private void request_driver_position() {
        IntrestBuyNet.findPositionById(driverId, new HandleSuccess<DriverPosBean>() {
            @Override
            public void success(DriverPosBean s) {
                if (s.getStatus() == 1 && s.getData() != null && s.getData().getLng() != null && s.getData().getLog() != null) {
                    //获取到司机当前经纬度
                    //划出司机所在的点（记得再次画点要删除上一个点）
                    LatLng startP = new LatLng(s.getData().getLng(), s.getData().getLog());
                    BitmapDescriptor s_bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_bd_cars);//R.mipmap.ic_pyp_location
                    OverlayOptions startOption = new MarkerOptions().position(startP).icon(s_bitmap);
                    if (marker != null) {
                        marker.remove();
                    }
                    marker = (Marker) baiduMap.addOverlay(startOption);
                }
            }
        });




    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_head_right://取消订单
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确认取消本次行程吗");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request_cancel_takecar();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.iv_message_driver:
                if (driverId != null){
                    //去聊天界面
                    if (RongIM.getInstance() != null){
                        RongIM.getInstance().startPrivateChat(TakeCarWaitDriverAcceptActivity.this,driverId.toString().trim(),driverName);
                    }
                }else {
                    showToast("暂无司机信息");
                }
                break;
            case R.id.iv_call_driver:
                if (driverPhone != null){
                    UIHelper.callThePhoneNumber(this,driverPhone);
                }else {
                    showToast("暂无司机信息");
                }
                break;
        }
    }

    private void request_cancel_takecar() {
        showSimpleDialog();
        IntrestBuyNet.updateTaxiDemand(nearestNeed.getId(), 0, 4,driverId, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                dismissSimDialog();
                if (s.getStatus() == 1 || s.getStatus() == 3){
                    UIHelper.togoTakeCarIndexActivity(TakeCarWaitDriverAcceptActivity.this);
                    TakeCarWaitDriverAcceptActivity.this.finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        mv_location_map.onResume();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    private void check_nearest_order_status() {
        IntrestBuyNet.findByDemandId(user.getId(), takeCarNeedId, new HandleSuccess<TakeCarNeedBean>() {
            @Override
            public void success(TakeCarNeedBean s) {
                if (s.getStatus() == 1 && s.getData() != null){
                    if (s.getData().getStatus() == 1){
                        rl_wait_for_accept.setVisibility(View.VISIBLE);
                        cancelOrder.setVisibility(View.VISIBLE);
                        int tempCount = CommonUtil.timeSecondsLongToLong(nearestNeed.getCreateTime());
                        if (tempCount > 0){
                            count = tempCount;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mv_location_map.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mv_location_map.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        mv_location_map.onDestroy();
        mv_location_map.setMapCustomEnable(false);
        baiduMap.clear();
        if (user!= null){
            unregisterReceiver(mMessageReceiver);
        }
    }

    //开始执行画行车路线的操作
    private void startToDrawDrivingLine(Double s_longitude,Double s_latitude,Double e_longitude,Double e_latitude){
        if (e_longitude != null && e_latitude != null) {
            RoutePlanSearch newInstance = RoutePlanSearch.newInstance();
            newInstance.setOnGetRoutePlanResultListener(new DrivingRouteListener());
            DrivingRoutePlanOption drivingOption = new DrivingRoutePlanOption();
            PlanNode from = PlanNode.withLocation(new LatLng(s_latitude, s_longitude));
            PlanNode to = PlanNode.withLocation(new LatLng(e_latitude, e_longitude));
            drivingOption.from(from);
            drivingOption.to(to);
            drivingOption.policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_DIS_FIRST);
            newInstance.drivingSearch(drivingOption);
        }else {
            Log.i("test",",没有目的地的需求");
            //只画一个起点
            showStartPoint(s_latitude,s_longitude,R.mipmap.icon_start);
        }


    }
    //获取到驾车路线的监听
    class DrivingRouteListener implements OnGetRoutePlanResultListener{

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            if (result == null || SearchResult.ERRORNO.RESULT_NOT_FOUND == result.error){
                return;
            }
            //start to draw driving route
            DrivingRouteOverlay overlay = new MyDivingOverlay(baiduMap);
            baiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
            double lat = nearestNeed.getStartingLng() < nearestNeed.getEndLng() ? nearestNeed.getStartingLng() : nearestNeed.getEndLng();
            double lng = (nearestNeed.getStartingLog() + nearestNeed.getEndLog()) / 2;
            LatLng center = new LatLng(lat, lng);
            float zoomlevel = CommonUtil.getMapLevelByDistance(LonLat.getDistance(nearestNeed.getStartingLog(),nearestNeed.getStartingLng(),nearestNeed.getEndLog(),nearestNeed.getEndLng()));
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(center,zoomlevel));
        }

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    }
    class MyDivingOverlay extends  DrivingRouteOverlay{
        public MyDivingOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
    }
    /*
    只绘制起点
     */
    private void showStartPoint(double s_latitude,double s_longitude, int s_icon){
        LatLng startP = new LatLng(s_latitude,s_longitude);
        BitmapDescriptor s_bitmap = BitmapDescriptorFactory.fromResource(s_icon);//R.mipmap.ic_pyp_location
        OverlayOptions startOption = new MarkerOptions().position(startP).icon(s_bitmap);
        baiduMap.addOverlay(startOption);

        LatLng center = new LatLng(s_latitude, s_longitude);
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(center,15));
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                double lat = s_latitude < e_latitude ? s_latitude : e_latitude;
//                double lng = (s_longitude + e_longitude) / 2;
//                LatLng center = new LatLng(lat, lng);
//                float zoomlevel = CommonUtil.getMapLevelByDistance(LonLat.getDistance(s_longitude,s_latitude,e_longitude,e_latitude));
//                baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(center,zoomlevel));
//            }
//        },1000);
    }
    /*
        显示地图并且绘制两个点
         */
    private void showMapAndDrawTwoPoint(final double s_latitude, final double s_longitude, final double e_latitude, final double e_longitude, int s_icon, int e_icon){
        baiduMap = mv_location_map.getMap();

        double lat = (s_latitude < e_latitude ? s_latitude : e_latitude)+0.0000001;
        double lng = ((s_longitude + e_longitude) / 2)-0.0000001;
        LatLng center = new LatLng(lat, lng);
        float zoomlevel = CommonUtil.getMapLevelByDistance(LonLat.getDistance(s_longitude,s_latitude,e_longitude,e_latitude));
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(center,zoomlevel));

        LatLng startP = new LatLng(s_latitude,s_longitude);
        LatLng endP = new LatLng(e_latitude,e_longitude);
        BitmapDescriptor s_bitmap = BitmapDescriptorFactory.fromResource(s_icon);//R.mipmap.ic_pyp_location
        BitmapDescriptor e_bitmap = BitmapDescriptorFactory.fromResource(e_icon);//R.mipmap.calendar_last_unfocused
        OverlayOptions startOption = new MarkerOptions().position(startP).icon(s_bitmap);
        OverlayOptions endOption = new MarkerOptions().position(endP).icon(e_bitmap);
        baiduMap.addOverlay(startOption);
        baiduMap.addOverlay(endOption);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                double lat = s_latitude < e_latitude ? s_latitude : e_latitude;
                double lng = (s_longitude + e_longitude) / 2;
                LatLng center = new LatLng(lat, lng);
                float zoomlevel = CommonUtil.getMapLevelByDistance(LonLat.getDistance(s_longitude,s_latitude,e_longitude,e_latitude));
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(center,zoomlevel));
            }
        },1000);
    }
    //关于本页面接收极光推送逻辑
//    private void registJpush() {
//        mMessageReceiver = new MessageReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter.addAction(MESSAGE_RECEIVED_ACTION);
//        registerReceiver(mMessageReceiver, filter);
//    }
//    private MessageReceiver mMessageReceiver;
//    public static final String MESSAGE_RECEIVED_ACTION = "com.hd.hdappyzx.MESSAGE_RECEIVED_ACTION";//已在MyReceiver设置了此Action，这里便可接收
//    public static final String KEY_TITLE = "title";
//    public static final String KEY_MESSAGE = "message";
//    public static final String KEY_EXTRAS = "extras";
//    public void registerMessageReceiver() {
//        mMessageReceiver = new MessageReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter.addAction(MESSAGE_RECEIVED_ACTION);
//        registerReceiver(mMessageReceiver, filter);
//    }
//    public class MessageReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
//                String messge = intent.getStringExtra(KEY_MESSAGE);
//                String extras = intent.getStringExtra(KEY_EXTRAS);
//                setCostomMsg(messge);
//            }
//        }
//    }
//
//    private void setCostomMsg(String msg) {
//        Integer type = null;
//        try {
//            JSONObject result = new JSONObject(msg);
//            type = result.getInt("type");//推送类别
//            Log.i("carTest",msg);
//            switch (type){
//                case 9:
//                    break;
//                case 10:
//                    break;
//                case 11:
//                    break;
//                default:
//                    break;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    private TaxiMessageReceiver mMessageReceiver;
    public void registerMessageReceiver() {
        mMessageReceiver = new TaxiMessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }
    public class TaxiMessageReceiver extends BroadcastReceiver {
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
                case 9://司机已接单 正赶往乘客  显示地图view隐藏等待view
                    handler.removeMessages(1);
                    nearestNeed.setTravelState(1);
                    rl_wait_for_accept.setVisibility(View.GONE);
                    request_userneedlist_takecar();
                    break;
                case 10://司机已经接到乘客 正赶往目的地  更改状态文字
                    nearestNeed.setTravelState(2);
                    cancelOrder.setVisibility(View.GONE);
                    tv_driver_status.setText("正在赶往目的地...");
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
