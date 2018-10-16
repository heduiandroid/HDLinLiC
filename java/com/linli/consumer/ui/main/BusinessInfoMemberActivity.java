package com.linli.consumer.ui.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.BusinessInfoMemberAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AdverPosition;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.FindAreaInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.BusinessInfo;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.LonLat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class BusinessInfoMemberActivity extends BaseActivity implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {
    private ListView lv_business_info_member;
    private BusinessInfoMemberAdapter adapter;
    private ArrayList<BusinessInfo> businessInfos = new ArrayList<BusinessInfo>();
    private ArrayList<BusinessInfo> newItems = new ArrayList<BusinessInfo>();
    private AppContext appContext;
    private User user;
    private City city;
    private Integer areaCode;
    private String mallPositionName = null;//用于判断该区域是否有商城广告位信息
    private String foodPositionName = null;//用于判断该区域是否有美食广告位信息
    private TextView tv_mall_info,tv_food_info;
    private View line_mall_info,line_food_info;
    private SwipeRefreshLayout srl_infos;
    private LinearLayout footerview;
    private int type;
    private int pager = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_business_info_member;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("会员订阅");
        lv_business_info_member = findView(R.id.lv_business_info_member);
        lv_business_info_member.setOnItemClickListener(this);
        lv_business_info_member.setOnScrollListener(this);
        srl_infos = findView(R.id.srl_infos);
        srl_infos.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        srl_infos.setSize(SwipeRefreshLayout.DEFAULT);
        srl_infos.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        srl_infos.setProgressViewEndTarget(true, 100);
        srl_infos.setOnRefreshListener(this);
        tv_mall_info = findViewClick(R.id.tv_mall_info);
        tv_food_info = findViewClick(R.id.tv_food_info);
        line_mall_info = findView(R.id.line_mall_info);
        line_food_info = findView(R.id.line_food_info);
        type = SHOP_MAIN;
        footerview = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);
    }
    private void clearRefreshDatas() {
        businessInfos.clear();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void initData() {
        adapter = new BusinessInfoMemberAdapter(businessInfos,this);
        lv_business_info_member.setAdapter(adapter);
        dismiss();
        line_mall_info.setBackgroundColor(getResources().getColor(R.color.orange));
        request_arecode_by_location();//想查广告先查区域ID
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_mall_info:
                if (type != SHOP_MAIN){
                    type = SHOP_MAIN;
                    clearLine();
                    pager = 1;
                    line_mall_info.setBackgroundColor(getResources().getColor(R.color.orange));
                    request_adver_position();
                }
                break;
            case R.id.tv_food_info:
                if (type != FOOD_MAIN){
                    type = FOOD_MAIN;
                    clearLine();
                    pager = 1;
                    line_food_info.setBackgroundColor(getResources().getColor(R.color.orange));
                    request_foodad_position();
                }
                break;
            default:
                break;
        }
    }

    private void clearLine() {
        line_mall_info.setBackgroundColor(getResources().getColor(R.color.white));
        line_food_info.setBackgroundColor(getResources().getColor(R.color.white));
    }
    private void request_arecode_by_location() {
        IntrestBuyNet.findcityList(city.getArea().replace("区", "").replace("县", ""), new HandleSuccess<FindAreaInfo>() {
            @Override
            public void success(FindAreaInfo s) {
                if (s.getStatus() == 1){
                    List<FindAreaInfo.DataBean> areas = s.getData();
                    if (areas != null && areas.size()>0){
                        areaCode = areas.get(0).getId();
                        request_adver_position();//想查广告第二步 再查广告位
                    }else {
                        clearRefreshDatas();
                        dismiss();
                        Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    clearRefreshDatas();
                    dismiss();
                    Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_adver_position() {
        if (areaCode == null){
            Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
            return;
        }
        IntrestBuyNet.searchStandard(areaCode, new HandleSuccess<AdverPosition>() {
            @Override
            public void success(AdverPosition s) {
                if (s.getStatus() == 1){
                    List<AdverPosition.DataBean> positions = s.getData();
                    if (positions != null && positions .size() > 0){
                        for (int i = 0;i<positions.size();i++){
                            String positionName = positions.get(i).getName().trim();
                            if (positionName.equals("会员订阅")){
                                mallPositionName = positionName;
                                request_advertisement_businessinfo(positions.get(i).getId());
                                break;
                            }
                        }
                        if (mallPositionName == null){
                            clearRefreshDatas();
                            Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        clearRefreshDatas();
                        dismiss();
                        Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    clearRefreshDatas();
                    dismiss();
                    Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_advertisement_businessinfo(Long id) {
        IntrestBuyNet.searchByParamVip(10, pager, id, user.getId(), areaCode, 5,new HandleSuccess<AdvertisementList>() {
            @Override
            public void success(AdvertisementList s) {
                newItems.clear();
                if (s.getStatus() == 1){
                    if (pager == 1)
                        businessInfos.clear();
                    if (s.getData() != null && s.getData().size() > 0){
                        List<AdvertisementList.DataBean> businfos = s.getData();
                        Log.i("test","有"+businfos.size()+"条数据");
                        for (int i = 0;i < businfos.size();i++){
                            BusinessInfo businessInfo = new BusinessInfo();
                            businessInfo.setId(businfos.get(i).getId());
                            businessInfo.setStoreId(businfos.get(i).getStoreId());
                            businessInfo.setCategory(SHOP_MAIN);
                            businessInfo.setTitle(businfos.get(i).getName());
                            Date dt = new Date(businfos.get(i).getCreatetime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            businessInfo.setDate(format.format(dt));
                            businessInfo.setImagePath(businfos.get(i).getPicurl());
                            if(businfos.get(i).getHdMallStore().getLongitude() != null && businfos.get(i).getHdMallStore().getLatitude() != null){
                                Double dis = LonLat.getDistance(city.getLongitude(), city.getLatitude(), businfos.get(i).getHdMallStore().getLongitude(), businfos.get(i).getHdMallStore().getLatitude());
                                businessInfo.setDistance(String.format("%.1f", dis));
                            }else {
                                businessInfo.setDistance(null);
                            }
                            businessInfo.setContents(businfos.get(i).getContent());
                            businessInfo.setRemark(businfos.get(i).getRemark());
                            businessInfo.setCount(businfos.get(i).getCount());
                            businessInfo.setShowCount(businfos.get(i).getHdMallAdvertisementRule().getShowcount());
                            businessInfo.setShowed(false);
                            newItems.add(businessInfo);
                            businessInfos.add(businessInfo);

                        }
                    }
                    if (pager == 1){
                        if (businessInfos.size()>0) {
                            //if  have datas
                            if (businessInfos.size()>9){
                                lv_business_info_member.addFooterView(footerview,null,false);
                            }
                            adapter.notifyDataSetChanged();
                            pager++;
                        }else {
                            //if have no datas
                            clearRefreshDatas();
                            Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
                        }
                        srl_infos.setRefreshing(false);
                    }else {
                        if (newItems.size()>0){
                            adapter.notifyDataSetChanged();
                            pager++;
                        }else {
                            if (businessInfos.size()>=10){
                                lv_business_info_member.removeFooterView(footerview);
                            }
                        }
                    }
                }else {
                    clearRefreshDatas();
                    Toast.makeText(BusinessInfoMemberActivity.this,"还没有商城订阅信息哦~",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_foodad_position() {
        if (areaCode == null){
            Toast.makeText(BusinessInfoMemberActivity.this,"还没有美食订阅信息哦~",Toast.LENGTH_SHORT).show();
            return;
        }
        IntrestBuyNet.searchStandardF(areaCode, new HandleSuccess<AdverPosition>() {
            @Override
            public void success(AdverPosition s) {
                if (s.getStatus() == 1){
                    List<AdverPosition.DataBean> positions = s.getData();
                    if (positions != null && positions .size() > 0){
                        for (int i = 0;i<positions.size();i++){
                            String positionName = positions.get(i).getName().trim();
                            if (positionName.equals("会员订阅")){
                                foodPositionName = positionName;
                                request_foodadver_businessinfo(positions.get(i).getId());
                                break;
                            }
                        }

                        if (foodPositionName == null){//都已经这样了 还不行 那对不起了有数据显示没数据再见 goodbye
                            clearRefreshDatas();
                            Toast.makeText(BusinessInfoMemberActivity.this, "还没有美食订阅信息哦~", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        clearRefreshDatas();
                        Toast.makeText(BusinessInfoMemberActivity.this, "还没有美食订阅信息哦~", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    clearRefreshDatas();
                    Toast.makeText(BusinessInfoMemberActivity.this, "还没有美食订阅信息哦~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_foodadver_businessinfo(Long id) {
        IntrestBuyNet.searchByParamFoodVip(10, pager, id, user.getId(), areaCode, new HandleSuccess<AdvertisementListF>() {
            @Override
            public void success(AdvertisementListF s) {
                newItems.clear();
                if (s.getStatus() == 1){
                    if (pager == 1)
                        businessInfos.clear();
                    if (s.getData() != null && s.getData().size() > 0){
                        List<AdvertisementListF.DataBean> businfos = s.getData();
                        Log.i("test","有"+businfos.size()+"条数据");
                        for (int i = 0;i < businfos.size();i++){
                            BusinessInfo businessInfo = new BusinessInfo();
                            businessInfo.setId(businfos.get(i).getId());
                            businessInfo.setStoreId(businfos.get(i).getStoreId());
                            businessInfo.setCategory(FOOD_MAIN);
                            businessInfo.setTitle(businfos.get(i).getName());
                            Date dt = new Date(businfos.get(i).getCreatetime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            businessInfo.setDate(format.format(dt));
                            businessInfo.setImagePath(businfos.get(i).getPicurl());
                            if(businfos.get(i).getHdFoodStore().getLongitude() != null && businfos.get(i).getHdFoodStore().getLatitude() != null){
                                Double dis = LonLat.getDistance(city.getLongitude(), city.getLatitude(), businfos.get(i).getHdFoodStore().getLongitude(), businfos.get(i).getHdFoodStore().getLatitude());
                                businessInfo.setDistance(String.format("%.1f", dis));
                            }else {
                                businessInfo.setDistance(null);
                            }
                            businessInfo.setContents(businfos.get(i).getContent());
                            businessInfo.setRemark(businfos.get(i).getRemark());
                            businessInfo.setCount(businfos.get(i).getCount());
                            businessInfo.setShowCount(businfos.get(i).getHdFoodAdvertisementRule().getShowcount());
                            businessInfo.setShowed(false);
                            newItems.add(businessInfo);
                            businessInfos.add(businessInfo);

                        }
                    }
                    if (pager == 1){
                        if (businessInfos.size()>0) {
                            //if  have datas
                            if (businessInfos.size()>9){
                                lv_business_info_member.addFooterView(footerview,null,false);
                            }
                            adapter.notifyDataSetChanged();
                            pager++;
                        }else {
                            //if have no datas
                            clearRefreshDatas();
                            Toast.makeText(BusinessInfoMemberActivity.this,"还没有美食订阅信息哦~",Toast.LENGTH_SHORT).show();
                        }
                        srl_infos.setRefreshing(false);
                    }else {
                        if (newItems.size()>0){
                            adapter.notifyDataSetChanged();
                            pager++;
                        }else {
                            if (businessInfos.size()>=10){
                                lv_business_info_member.removeFooterView(footerview);
                            }
                        }
                    }
                }else {
                    clearRefreshDatas();
                    Toast.makeText(BusinessInfoMemberActivity.this,"还没有美食订阅信息哦~",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // 跳到详细商讯界面还要请求点击数量加1 的接口
        Intent intent = new Intent();
        intent.setClass(BusinessInfoMemberActivity.this, BusinessInfoDetailActivity.class);
        intent.putExtra("businessinfo", businessInfos.get(position));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        pager = 1;
        lv_business_info_member.removeFooterView(footerview);
        if (type == SHOP_MAIN){
            request_adver_position();
        }else if (type == FOOD_MAIN){
            request_foodad_position();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount()-1)){
                    if (type == SHOP_MAIN){
                        request_adver_position();
                    }else if (type == FOOD_MAIN){
                        request_foodad_position();
                    }
                }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
