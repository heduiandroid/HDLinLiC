package com.linli.consumer.ui.main;

import android.graphics.Point;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.linli.consumer.R;
import com.linli.consumer.adapter.PlaceListAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.domain.City;
import com.linli.consumer.utils.LocationCallbackTool;

import java.util.ArrayList;
import java.util.List;

import io.rong.message.LocationMessage;

public class MapLocationActivity extends BaseActivity {
    MapView mMapView;
    BaiduMap baiduMap;
    private TextView text_go;
    private RelativeLayout btn_go;
    //当前经纬度

    private double jingdu;
    private double weidu;
    LatLng mLoactionLatLng;
    private String adderss = "";
    // 定位相关声明
    LocationClient locationClient;
    LocationMessage mMsg;
    //自定义图标
    boolean isFirstLoc = true;// 是否首次定位
    // MapView中央对于的屏幕坐标
    Point mCenterPoint = null;

    // 地理编码
    GeoCoder mGeoCoder = null;


    // 位置列表
    ListView mListView;
    // PlaceListAdapter mAdapter;
    List<PoiInfo> mInfoList;
    PoiInfo mCurentInfo;

    ListView Maplistview;
    PlaceListAdapter customListAdpter;
    private AppContext appContext;
    private City city;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_map_location;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("地理位置");
        ImageView iv_head_right = findView(R.id.iv_head_right);
        iv_head_right.setVisibility(View.GONE);
        text_go = findViewClick(R.id.tv_head_right);//如果是查看定位信息，不显示这个按钮
        Maplistview = findView(R.id.mymapListView);
        text_go.setText("发送");
        mMapView = findView(R.id.bmapView);
        mMapView.showZoomControls(false);
        baiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17.0f);
        baiduMap.setMapStatus(msu);
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);
        baiduMap.setOnMapTouchListener(touchListener);
        try {
            if (getIntent().hasExtra("location")) {
                mMsg = getIntent().getParcelableExtra("location");
            }
            if (mMsg != null) {
                text_go.setVisibility(View.GONE);
                Maplistview.setVisibility(View.GONE);
                locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
                MyLocationData locData = new MyLocationData.Builder()
        /*.accuracy(location.getRadius())*/
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(mMsg.getLat())
                        .longitude(mMsg.getLng()).build();
                baiduMap.setMyLocationData(locData);    //设置定位数据
                mLoactionLatLng = new LatLng(mMsg.getLat(),
                        mMsg.getLng());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(mLoactionLatLng, 16);    //设置地图中心点以及缩放级别
                baiduMap.animateMapStatus(u);
            } else {
                locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
                locationClient.registerLocationListener(myListener); // 注册监听函数
                this.setLocationOption();    //设置定位参数
                locationClient.start(); // 开始定位
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void initData() {
        dismiss();
        getimgxy();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_head_right:
                Uri uri = Uri.parse("http://api.map.baidu.com/staticimage?width=300&height=200&center="+jingdu + "," + weidu + "&zoom=17&markers=" + jingdu + "," + weidu + "&markerStyles=m,A");
                LocationMessage locationMessage = LocationMessage.obtain(weidu, jingdu, adderss, uri);
                //濡傛灉鍦板浘鍦颁綅鎴愬姛锛岄偅涔堣皟鐢�
                LocationCallbackTool.mLastLocationCallback.onSuccess(locationMessage);
                //濡傛灉鍦板浘鍦颁綅澶辫触锛岄偅涔堣皟鐢�
                LocationCallbackTool.mLastLocationCallback.onFailure("定位失败！");
                finish();
                break;

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //鍦╝ctivity鎵цonDestroy鏃舵墽琛宮MapView.onDestroy()锛屽疄鐜板湴鍥剧敓鍛藉懆鏈熺鐞�
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //鍦╝ctivity鎵цonResume鏃舵墽琛宮MapView. onResume ()锛屽疄鐜板湴鍥剧敓鍛藉懆鏈熺鐞�
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //鍦╝ctivity鎵цonPause鏃舵墽琛宮MapView. onPause ()锛屽疄鐜板湴鍥剧敓鍛藉懆鏈熺鐞�
        mMapView.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            locationClient.stop();
            finish();
        }
        return false;
    }
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 鎵撳紑GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 璁剧疆瀹氫綅妯″紡
        option.setCoorType("bd09ll"); // 杩斿洖鐨勫畾浣嶇粨鏋滄槸鐧惧害缁忕含搴�,榛樿鍊糶cj02
//        option.setScanSpan(5000); // 璁剧疆鍙戣捣瀹氫綅璇锋眰鐨勯棿闅旀椂闂翠负5000ms
        option.setIsNeedAddress(true); // 杩斿洖鐨勫畾浣嶇粨鏋滃寘鍚湴鍧�淇℃伅
        option.setNeedDeviceDirect(true); // 杩斿洖鐨勫畾浣嶇粨鏋滃寘鍚墜鏈烘満澶寸殑鏂瑰悜
        locationClient.setLocOption(option);
    }
    public BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            //定位成功之后，我定义了三个变量，去出来对应的经纬度，以及位置信息，用来生成静态地图，发送到会话页面。
            weidu = location.getLatitude();
            jingdu = location.getLongitude();
            adderss = location.getCity()+location.getDistrict()+location.getStreet()+location.getStreetNumber();
            Log.i("test",location.getAddrStr()+location.getLatitude()+location.getLongitude());
            MyLocationData locData = new MyLocationData.Builder()

                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);    //设置定位数据
            if (isFirstLoc) {
                isFirstLoc = false;

                mLoactionLatLng = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(mLoactionLatLng, 16);//设置地图中心点以及缩放级别
                baiduMap.animateMapStatus(u);
            }
//            if (mCenterPoint == null) {
//                return;
//            }
            // 获取当前MapView中心屏幕坐标对应的地理坐标
            LatLng currentLatLng = new LatLng(city.getLatitude(),city.getLongitude());
//            currentLatLng = baiduMap.getProjection().fromScreenLocation(
//                    mCenterPoint);
//            System.out.println("----定位" + mCenterPoint.x);
//            System.out.println("----定位" + currentLatLng.latitude);
            // 发起反地理编码检索
            mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption())
                    .location(currentLatLng));
        }
    };
    /**
     * 初始化地图物理坐标
     */
    private void getimgxy() {
        // 初始化POI信息列表
        mInfoList = new ArrayList<PoiInfo>();
        customListAdpter = new PlaceListAdapter(getLayoutInflater(), mInfoList);
        Maplistview.setAdapter(customListAdpter);
        mCenterPoint = baiduMap.getMapStatus().targetScreen;
        mLoactionLatLng = baiduMap.getMapStatus().target;
// 地理编码
        mGeoCoder = GeoCoder.newInstance();
        mGeoCoder.setOnGetGeoCodeResultListener(GeoListener);

        Maplistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                customListAdpter.clearSelection(i);
                customListAdpter.notifyDataSetChanged();
                locationClient.stop();
                baiduMap.clear();
                PoiInfo info = (PoiInfo) customListAdpter.getItem(i);
                LatLng la = info.location;
                weidu = la.latitude;
                jingdu = la.longitude;
                adderss = info.address+info.name;

                MyLocationData locData = new MyLocationData.Builder()
        /*.accuracy(location.getRadius())*/
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(la.latitude)
                        .longitude(la.longitude).build();
                baiduMap.setMyLocationData(locData);
                //设置定位数据
                mLoactionLatLng = new LatLng(la.latitude,
                        la.longitude);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(mLoactionLatLng, 20);    //设置地图中心点以及缩放级别
                baiduMap.animateMapStatus(u);
            }
        });
    }

        // 地理编码监听器
        OnGetGeoCoderResultListener GeoListener = new OnGetGeoCoderResultListener() {
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检索到结果
                }
                // 获取地理编码结果
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有找到检索结果
                    // 通知适配数据已改变
                    customListAdpter.notifyDataSetChanged();
                }
                // 获取反向地理编码结果
                else {
                    // 当前位置信息
                    mCurentInfo = new PoiInfo();
                    mCurentInfo.address = result.getAddress();
                    mCurentInfo.location = result.getLocation();
                    mCurentInfo.name = "[位置]";
                    mInfoList.clear();
                    mInfoList.add(mCurentInfo);

                    // 将周边信息加入表
                    if (result.getPoiList() != null) {
                        mInfoList.addAll(result.getPoiList());
                    }
                    // 通知适配数据已改变
                    customListAdpter.notifyDataSetChanged();
       /* mLoadBar.setVisibility(View.GONE);*/

                }
            }
        };
    // 地图触摸事件监听器
    BaiduMap.OnMapTouchListener touchListener = new BaiduMap.OnMapTouchListener() {
        @Override
        public void onTouch(MotionEvent event) {
            // TODO Auto-generated method stub
            if (event.getAction() == MotionEvent.ACTION_UP) {

                if (mCenterPoint == null) {
                    return;
                }
                mCenterPoint = baiduMap.getMapStatus().targetScreen;
                // 获取当前MapView中心屏幕坐标对应的地理坐标
                LatLng currentLatLng;
                currentLatLng = baiduMap.getProjection().fromScreenLocation(mCenterPoint);
                System.out.println("----触摸" + mCenterPoint.x);
                System.out.println("----触摸" + currentLatLng.latitude);
                // 发起反地理编码检索
                mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption())
                        .location(currentLatLng));
                //mLoadBar.setVisibility(View.VISIBLE);

            }
        }
    };
}

