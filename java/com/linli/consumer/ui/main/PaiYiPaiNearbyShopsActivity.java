package com.linli.consumer.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.linli.consumer.R;
import com.linli.consumer.adapter.PaiYiPaiNearbyShopsAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.NearbyShopHomepage;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Shop;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.DistanceComparator;
import com.linli.consumer.utils.LonLat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaiYiPaiNearbyShopsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private LocationClient locationClient = null;
    private BDLocationListener locationListener = new MyLocationListener();
    private ArrayList<Shop> absShops = new ArrayList<Shop>();
    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private PaiYiPaiNearbyShopsAdapter adapter;
    private ListView mlv_nearbyshops;
    private AppContext appContext;
    private City city;
    private int pager = 1;
    private boolean needRefreshInBDLoc = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pai_yi_pai_nearby_shops;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        if (city != null){
            request_nearby_shops();
        }else {
            initBDMap();//定位完成后会请求一次首页数据
        }
    }

    @Override
    public void onHDClick(View v) {

    }

    private void initBDMap() {
        locationClient = new LocationClient(this);
        locationClient.registerLocationListener(locationListener);
        LocationClientOption option=new LocationClientOption();
        option.setOpenGps(false);// 打开GPS
        option.setAddrType("all");// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
//        option.setScanSpan(10000);// 设置发起定位请求的间隔时间为10000ms 10秒请求一次够了
        option.disableCache(false);// 禁止启用缓存定位
        option.setPriority(LocationClientOption.NetWorkFirst);// 网络定位优先
        locationClient.setLocOption(option);// 使用设置
        locationClient.start();// 开启定位SDK
        locationClient.requestLocation();// 开始请求位置
    }
    private int numPerPage = 300;//每页需要几条数据
    private int minNumPerPage = 8;//每页最少需要几条数据
    private int visibleNumPerPage = 20;//真实展示的数据条数
    private int maxDistance = 3;//支持的最大所有范围
    private int distance = 1;//查询附近多少距离的数据 （单位：千米） 首页查询数据逻辑特殊：先查1千米范围 若1千米范围商家不够再查询更大范围的数据
    private void request_nearby_shops() {
        if (pager == 1){
            showDialog();
        }
        IntrestBuyNet.findShopList(0,numPerPage, pager, distance, city.getLatitude(), city.getLongitude(), new HandleSuccess<NearbyShopHomepage>() {
            @Override
            public void success(NearbyShopHomepage s) {
                if (s.getStatus() == 1){
                    ArrayList<Shop> newShopItems = new ArrayList<Shop>();
                    newShopItems.clear();
                    if (s.getData() != null || s.getData().size() > 0) {
                        if (s.getData().size() < minNumPerPage && distance < maxDistance){//如果获取到的数据不够想要的数量并且距离没有扩大到最大范围
                            distance++;//扩大一千米范围
                            Log.i("test","距离扩大到"+distance+"千米");
                            request_nearby_shops();//重新请求数据
                            return;//停止继续往下执行*此时考虑何时将数据重新更改当前获取数据范围重新更改为1千米*
                        }
                        List<NearbyShopHomepage.DataBean> items = s.getData();
                        for (int i = 0; i < items.size(); i++) {
                            NearbyShopHomepage.DataBean item = items.get(i);
                            Shop shop = new Shop();
                            shop.setId(item.getId());
                            shop.setShopName(item.getName());
                            shop.setLogoPath(item.getLogoImg() + Common.MORESMALLERPICPARAM);
                            if (s.getData().get(i).getCategoryType()!=null){
                                shop.setCategory(s.getData().get(i).getCategoryType());//1-商城2-订餐3-服务
                            }else {
                                shop.setCategory(3);
                            }
                            shop.setLongitude(item.getLongitude().toString());
                            shop.setLatitude(item.getLatitude().toString());
                            Double distance = LonLat.getDistance(Double.valueOf(shop.getLongitude()), Double.valueOf(shop.getLatitude()),
                                    city.getLongitude(), city.getLatitude());
                            shop.setDistance(String.format("%.1f", distance));//用于格式化显示的距离
                            if (item.getNotice() != null){
                                shop.setIntroduce(item.getNotice());
                            }else {
                                shop.setIntroduce("暂无公告");
                            }
                            if (item.getCreditLevel() != null){
                                shop.setLevel(item.getCreditLevel());
                            }else {
                                shop.setLevel(1);
                                if (shop.getCategory() == 3){
                                    shop.setLevel(5);
                                }
                            }
                            if (city != null){//为之后执行的按距离排序做准备
                                shop.setDistance_d(LonLat.getDistance(item.getLongitude(),item.getLatitude(),
                                        city.getLongitude(),city.getLatitude()));
                            }else {
                                shop.setDistance_d(0d);
                            }
                            newShopItems.add(shop);
                            absShops.add(shop);
                        }
                        DistanceComparator dc = new DistanceComparator();//按照距离远近排序
                        Collections.sort(absShops,dc);
                        if (absShops.size() < visibleNumPerPage){
                            visibleNumPerPage = absShops.size();
                        }
                        for (int i = 0;i < visibleNumPerPage;i++){
                            shops.add(absShops.get(i));
                        }
                    }
                    if (pager == 1){
                        if (shops.size()>0) {
                            //if  have datas
                            adapter = new PaiYiPaiNearbyShopsAdapter(shops,PaiYiPaiNearbyShopsActivity.this);
                            mlv_nearbyshops.setAdapter(adapter);
//                            pager++;
                        }else {
                            //if have no datas
                            PaiYiPaiNearbyShopsActivity.this.finish();
                            Toast.makeText(PaiYiPaiNearbyShopsActivity.this, "附近还没有商家哦~", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (newShopItems.size()>0){
                            adapter.notifyDataSetChanged();
//                            pager++;
                        }else {
                            if (shops.size()>7){
                                Toast.makeText(PaiYiPaiNearbyShopsActivity.this,"没有更多了",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else {
                    Toast.makeText(PaiYiPaiNearbyShopsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

    }

    private void init() {
        mlv_nearbyshops = (ListView) findViewById(R.id.mlv_nearbyshops);
        mlv_nearbyshops.setOnItemClickListener(this);
        mlv_nearbyshops.setOnScrollListener(this);
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();
    }
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location!=null || location.getCity() != null) {
//                StringBuffer sb=new StringBuffer(128);//接收服务放回的缓冲区
//                sb.append(location.getCity());//获得城市
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
                if (needRefreshInBDLoc){
                    needRefreshInBDLoc = false;
                    request_nearby_shops();
                }
//                Toast.makeText(YZXIndexActivity.this,city.getLongitude()+" "+city.getLatitude(),Toast.LENGTH_SHORT).show();
            }else {
//                btn_city.setText("...");
                Toast.makeText(PaiYiPaiNearbyShopsActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Long shopid = shops.get(position).getId();
//        String shopname = shops.get(position).getShopName();
//        int category = shops.get(position).getCategory();
        Toast.makeText(PaiYiPaiNearbyShopsActivity.this,"欢迎光临"+shops.get(position).getShopName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("shop",shops.get(position));
        setResult(1100, intent);
        finish();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
//                if (view.getLastVisiblePosition() == (view.getCount()-1)){
//                    request_nearby_shops();
//                }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
