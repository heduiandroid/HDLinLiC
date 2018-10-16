package com.linli.consumer.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.CityListAdapterV2;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.ProvinceCityCounty;
import com.linli.consumer.domain.City;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.List;

public class V2SelectCityActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private AppContext appContext;
    private City city;
    private ListView lv_province_city_area;
    private List<ProvinceCityCounty.DataBean> list;
    private CityListAdapterV2 adapter;
    private int part = 0;
    private int tag;
    private String mProvince;//省名
    private String mCity;//市名
    private String mCounty;//区县名
    private int proRegionId = 0,cityRegionId = 0,couRegionId = 0;//省市县区域id

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_city;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("区域选择");
        lv_province_city_area = findView(R.id.lv_province_city_area);
        lv_province_city_area.setOnItemClickListener(this);
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();

    }

    @Override
    protected void initData() {
        if (city != null){
            Intent intent = this.getIntent();
            tag = intent.getIntExtra("tag",-1);
            request_get_province();
        }
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
        }
    }

    private void request_get_province() {
        IntrestBuyNet.findProviceList(new HandleSuccess<ProvinceCityCounty>() {
            @Override
            public void success(ProvinceCityCounty s) {
                if (s.getStatus() == 1){
                    list = s.getData();
                    if (list.size() >0) {
                        adapter = new CityListAdapterV2(list,V2SelectCityActivity.this);
                        lv_province_city_area.setAdapter(adapter);
                        if (part == 0) {
                            part = 1;
                        }
                    }else {
                        finish();
                        Toast.makeText(V2SelectCityActivity.this,"抱歉，还没有开通该城市",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(V2SelectCityActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void request_get_city() {
        showDialog();
        IntrestBuyNet.findCityList(proRegionId, new HandleSuccess<ProvinceCityCounty>() {
            @Override
            public void success(ProvinceCityCounty s) {
                if (s.getStatus() == 1){
                    list = s.getData();
                    if (list.size() >0) {
                        Log.i("test","1");
                        adapter = new CityListAdapterV2(list,V2SelectCityActivity.this);
                        lv_province_city_area.setAdapter(adapter);
                        if (part == 1) {
                            part = 2;
                        }
                    }else {
                        Intent intent = new Intent();
                        intent.putExtra("city", mProvince);
                        intent.putExtra("proRegionId",proRegionId);
                        intent.putExtra("cityRegionId",cityRegionId);
                        intent.putExtra("couRegionId",couRegionId);
                        setResult(200, intent);
                        finish();
                    }
                }else {
                    Toast.makeText(V2SelectCityActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void request_get_county() {
        showDialog();
        IntrestBuyNet.findDistrictList(cityRegionId, new HandleSuccess<ProvinceCityCounty>() {
            @Override
            public void success(ProvinceCityCounty s) {
                if (s.getStatus() == 1){
                    list = s.getData();
                    if (list.size() >0) {
                        adapter = new CityListAdapterV2(list,V2SelectCityActivity.this);
                        lv_province_city_area.setAdapter(adapter);
                        if (part == 2) {
                            part = 3;
                        }
                    }else {
                        Intent intent = new Intent();
                        intent.putExtra("city", mProvince+" "+mCity);
                        intent.putExtra("proRegionId",proRegionId);
                        intent.putExtra("cityRegionId",cityRegionId);
                        intent.putExtra("couRegionId",couRegionId);
                        setResult(200, intent);
                        finish();
                    }
                }else {
                    Toast.makeText(V2SelectCityActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (tag == 1) {
            if (part == 1) {
                mProvince = list.get(i).getName();
                proRegionId = list.get(i).getId();
                request_get_city();
            } else if (part == 2){
                mCity = list.get(i).getName();
                cityRegionId = list.get(i).getId();
                request_get_county();
            }else if (part == 3){
                mCounty = list.get(i).getName();
                couRegionId = list.get(i).getId();
                Intent intent = new Intent();
                intent.putExtra("city", mProvince+" "+mCity+" "+mCounty);
                intent.putExtra("proRegionId",proRegionId);
                intent.putExtra("cityRegionId",cityRegionId);
                intent.putExtra("couRegionId",couRegionId);
                setResult(200, intent);
                finish();
            }
        }
//        else if(tag == 0){
//            if (part_key.equals("county")) {
//                city.setArea(part_value);
//                city.setLongitude(Double.valueOf(longitudes.get(position)));
//                city.setLatitude(Double.valueOf(latitudes.get(position)));
//                Intent intent = new Intent();
//                intent.putExtra("area", city.getArea());
//                setResult(201, intent);
//                finish();
//            }else if (part_key.equals("city")){
//                city.setName(part_value);
//                request_get_city(proRegionId);
//            }else if (part_key.equals("province")){
//                city.setProvince(part_value);
//                request_get_city(proRegionId);
//            }
//        }else if (tag == 2){
//            if (part_key.equals("city")){
//                cityname = part_value;
//                Intent intent = new Intent();
//                intent.putExtra("province",province);
//                intent.putExtra("city",cityname);
//                setResult(202,intent);
//                finish();
//            }else if(part_key.equals("province")){
//                province = part_value;
//                request_get_city(proRegionId);
//            }
//        }else if (tag == 3){
//            if (part_key.equals("county")){
//                mcounty = part_value;
//                Intent intent = new Intent();
//                intent.putExtra("mprovince",mprovince);
//                intent.putExtra("mcity",mcity);
//                intent.putExtra("mcounty",mcounty);
//                setResult(1001,intent);
//                finish();
//            }else if (part_key.equals("city")) {
//                mcity = part_value;
//                request_get_city(proRegionId);
//            }else if (part_key.equals("province")){
//                mprovince = part_value;
//                request_get_city(proRegionId);
//            }
//        }else {
//            if (part_key.equals("county")) {
//                finish();
//            } else {
//                request_get_city(proRegionId);
//            }
//        }
    }


}
