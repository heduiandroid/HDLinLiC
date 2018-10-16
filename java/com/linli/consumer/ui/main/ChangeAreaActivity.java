package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.linli.consumer.R;
import com.linli.consumer.adapter.AreasAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.domain.City;

import java.util.ArrayList;
import java.util.List;

public class ChangeAreaActivity extends BaseActivity implements AdapterView.OnItemClickListener, OnGetGeoCoderResultListener {
    private LinearLayout ll_default_areas,ll_searchedareas;
    private TextView tv_location;
    private ListView lv_nearbyareas,lv_searchedareas;
    private EditText et_search;
    private ProgressBar pb_loading,pb_waitingnearys;
    private SuggestionSearch mSuggestionSearch;
    private SuggestionSearchOption option = new SuggestionSearchOption();
    private AppContext appContext;
    private City city;
    private City myLocateCity = new City();
    private ArrayList<City> searchedAreas = new ArrayList<City>();
    private ArrayList<City> nearbyAreas = new ArrayList<City>();
    private AreasAdapter adapter;
    private AreasAdapter nadapter;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i("textwatcher","beforeTextChanged"+et_search.getText().toString());
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("textwatcher","onTextChanged"+et_search.getText().toString());
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(et_search.getText())){
                pb_loading.setVisibility(View.VISIBLE);
                mSuggestionSearch.requestSuggestion(option.keyword(et_search.getText().toString().trim()).city(city.getName()));
            }else {
                searchedAreas.clear();
                pb_loading.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }
            Log.i("textwatcher","afterTextChanged"+et_search.getText().toString());//这里写请求搜索的数据
        }
    };
    private boolean isBackToDefault = false;//判断返回按钮是否要回到首页
    //    private LocationClient locationClient = null;
//    private BDLocationListener locationListener = new MyLocationListener();
    private GeoCoder mSearch;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_area;
    }

    @Override
    protected void initView() {
        init();
        adapter = new AreasAdapter(searchedAreas,this);
        nadapter = new AreasAdapter(nearbyAreas,this);
        lv_searchedareas.setAdapter(adapter);
        lv_nearbyareas.setAdapter(nadapter);
        ll_default_areas.setVisibility(View.VISIBLE);
        pb_loading = findView(R.id.pb_loading);
        pb_waitingnearys = findView(R.id.pb_waitingnearys);
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();
        mSearch = GeoCoder.newInstance();//初始化搜索模块，注册时间监听
        mSearch.setOnGetGeoCodeResultListener(this);
        findMyLocationAndFindNearby();
        dismiss();
    }

    @Override
    protected void initData() {

    }

    private void findMyLocationAndFindNearby() {
        SharedPreferences preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        myLocateCity.setProvince(preferences.getString("province", null));
        myLocateCity.setName(preferences.getString("city", null));
        myLocateCity.setArea(preferences.getString("area", null));
        myLocateCity.setStreet(preferences.getString("street", null));
        myLocateCity.setLongitude(Double.valueOf(preferences.getString("longitude", null)));
        myLocateCity.setLatitude(Double.valueOf(preferences.getString("latitude", null)));
        if (myLocateCity.getStreet() != null && myLocateCity.getLatitude() != null && myLocateCity.getLongitude() != null){
            tv_location.setText(myLocateCity.getName()+myLocateCity.getArea()+myLocateCity.getStreet());
            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(myLocateCity.getLatitude(), myLocateCity.getLongitude())));
        }

    }

//    private void initBDMap() {
//        locationClient = new LocationClient(this);
//        locationClient.registerLocationListener(locationListener);
//        LocationClientOption option=new LocationClientOption();
//        option.setOpenGps(false);// 打开GPS
//        option.setAddrType("all");// 返回的定位结果包含地址信息
//        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
////        option.setScanSpan(10000);// 设置发起定位请求的间隔时间为10000ms 10秒请求一次够了
//        option.disableCache(false);// 禁止启用缓存定位
//        option.setPriority(LocationClientOption.NetWorkFirst);// 网络定位优先
//        locationClient.setLocOption(option);// 使用设置
//        locationClient.start();// 开启定位SDK
//        locationClient.requestLocation();// 开始请求位置
//    }

    private void init() {
        findViewClick(R.id.iv_back);
        et_search = findView(R.id.et_search);
        et_search.addTextChangedListener(textWatcher);
        ll_default_areas = findView(R.id.ll_default_areas);
        ll_searchedareas = findView(R.id.ll_searchedareas);
        tv_location = findViewClick(R.id.tv_location);
        lv_nearbyareas = findView(R.id.lv_nearbyareas);
        lv_searchedareas = findView(R.id.lv_searchedareas);
        lv_nearbyareas.setOnItemClickListener(this);
        lv_searchedareas.setOnItemClickListener(this);
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isBackToDefault = true;
                    ll_default_areas.setVisibility(View.GONE);
                    ll_searchedareas.setVisibility(View.VISIBLE);
                } else {
                    isBackToDefault = false;
                    pb_loading.setVisibility(View.GONE);
                    ll_default_areas.setVisibility(View.VISIBLE);
                    ll_searchedareas.setVisibility(View.GONE);
                }
            }
        });
    }

    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                return;
                //未找到相关结果
            }else{
                searchedAreas.clear();
                List<SuggestionResult.SuggestionInfo> resl = suggestionResult.getAllSuggestions();
                for(int i=0;i<resl.size();i++){
                    if (resl.get(i).pt != null){
                        City city = new City();
                        city.setStreet(resl.get(i).key);
                        city.setName(resl.get(i).city);
                        city.setArea(resl.get(i).district);
                        city.setLongitude(resl.get(i).pt.longitude);
                        city.setLatitude(resl.get(i).pt.latitude);
                        searchedAreas.add(city);
                        Log.i("result: ", "city" + resl.get(i).city + " dis " + resl.get(i).district + "key " + resl.get(i).key+resl.get(i).pt.longitude+"  "+resl.get(i).pt.latitude);
                    }
                }
                if (searchedAreas.size() > 0){
                    if (adapter != null){
                        pb_loading.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            //获取在线建议检索结果
        }
    };
    @Override
    protected void onDestroy() {
//        stopListener();
        mSuggestionSearch.destroy();
        //  mMapView.onDestroy();
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理


    }
//    private void stopListener() {
//        if (locationClient!=null && locationClient.isStarted()) {
//            locationClient.stop();//关闭点位Sdk
//            locationClient=null;
//        }
//    }


    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                if (isBackToDefault){
                    et_search.clearFocus();
                }else {
                    finish();
                }
                break;
            case R.id.tv_location:
                appContext.setCity(myLocateCity);
                setResult(301);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lv_searchedareas:
                appContext.setCity(searchedAreas.get(position));
                setResult(301);
                finish();
                break;
            case R.id.lv_nearbyareas:
                appContext.setCity(nearbyAreas.get(position));
                setResult(301);
                finish();
                break;
            default:
                break;
        }
    }
    //    private class MyLocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation bdLocation) {
//            tv_location.setText(bdLocation.getCity() + bdLocation.getDistrict() + bdLocation.getStreet() + bdLocation.getStreetNumber());
//            Log.i("size", bdLocation.getLongitude() + bdLocation.getAddress().address + bdLocation.getLatitude());
//            mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude())));
//        }
//    }
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR){
            Log.i("size","未找到结果");
            return;
        }
        List<PoiInfo> result = reverseGeoCodeResult.getPoiList();
        Log.i("city",reverseGeoCodeResult.getAddressDetail().city);
        Log.i("area",reverseGeoCodeResult.getAddressDetail().district);
        Log.i("size",result.size()+" ");
        for(int i=0;i<result.size();i++){
            if (result.get(i).location != null){
                City city = new City();
                city.setStreet(result.get(i).name);
                city.setName(reverseGeoCodeResult.getAddressDetail().city);
                city.setArea(reverseGeoCodeResult.getAddressDetail().district);
                city.setLongitude(result.get(i).location.longitude);
                city.setLatitude(result.get(i).location.latitude);
                nearbyAreas.add(city);
            }
        }
        if (nearbyAreas.size() > 0){
            if (nadapter != null){
                nadapter.notifyDataSetChanged();
               pb_waitingnearys.setVisibility(View.GONE);
            }
        }
    }
}
