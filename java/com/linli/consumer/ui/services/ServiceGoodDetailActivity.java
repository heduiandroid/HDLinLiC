package com.linli.consumer.ui.services;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.utils.CommonUtil;

public class ServiceGoodDetailActivity extends BaseActivity {
    private SimpleDraweeView sdv_main_img,sdv_head_img,sdv_img1,sdv_img2,sdv_img3;
    private TextView tv_shopname,tv_introduce,tv_address,tv_phone,tv_distance,tv_order_service;
    private ServiceGoodBean.DataBean service;
    private AppContext appContext = AppContext.getInstance();
    private City city = appContext.getCity();
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_good_detail;
    }

    @Override
    protected void initView() {
        //初始化视图
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("服务详情");
        tv_head_name.setVisibility(View.VISIBLE);
        sdv_main_img = findView(R.id.sdv_main_img);
        sdv_head_img = findView(R.id.sdv_head_img);
        sdv_img1 = findView(R.id.sdv_img1);
        sdv_img2 = findView(R.id.sdv_img2);
        sdv_img3 = findView(R.id.sdv_img3);
        tv_shopname = findView(R.id.tv_shopname);
        tv_introduce = findView(R.id.tv_introduce);
        tv_address = findView(R.id.tv_address);
        tv_phone = findViewClick(R.id.tv_phone);
        tv_distance = findView(R.id.tv_distance);
        tv_order_service = findViewClick(R.id.tv_order_service);
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        super.onResume();
    }

    @Override
    protected void initData() {

        //获取intent数据
        service = (ServiceGoodBean.DataBean) getIntent().getSerializableExtra("service");
        if (service != null){
            if (service.getImg() != null && !service.getImg().equals("")){
                sdv_main_img.setImageURI(service.getImg());
            }else {
                if (service.getImg1() != null && !service.getImg1().equals("")) {
                    sdv_main_img.setImageURI(service.getImg1());
                }else if (service.getImg2() != null && !service.getImg2().equals("")) {
                    sdv_main_img.setImageURI(service.getImg2());
                }else if (service.getImg3() != null && !service.getImg3().equals("")) {
                    sdv_main_img.setImageURI(service.getImg3());
                }
            }
            if (service.getImg1() != null && !service.getImg1().equals("")) {
                sdv_img1.setImageURI(service.getImg1());
            }else {
                sdv_img1.setVisibility(View.GONE);
            }
            if (service.getImg2() != null && !service.getImg2().equals("")) {
                sdv_img2.setImageURI(service.getImg2());
            }else {
                sdv_img2.setVisibility(View.GONE);
            }
            if (service.getImg3() != null && !service.getImg3().equals("")) {
                sdv_img3.setImageURI(service.getImg3());
            }else {
                sdv_img3.setVisibility(View.GONE);
            }
            tv_shopname.setText(service.getStoreName());
            tv_introduce.setText("简介："+service.getContent());

            findShopInfo();
        }
        dismiss();
    }

    private void findShopInfo() {
        FoodNet.findServiceStoresInfos(service.getStoreid(), new HandleSuccess<ServiceStoreBean>() {
            @Override
            public void success(ServiceStoreBean s) {
                if (s.getStatus() == 1 && s.getData() != null){
                    sdv_head_img.setImageURI(s.getData().getLogoImg());
                    tv_address.setText(s.getData().getAddress());
                    tv_phone.setText(s.getData().getMobilePhone());
                    tv_distance.setText(CommonUtil.setDistance(city,s.getData().getLongitude(),s.getData().getLatitude())+"km");
                }else {
                    Toast.makeText(ServiceGoodDetailActivity.this,"抱歉，未能获取到商品所属商铺信息",Toast.LENGTH_SHORT).show();
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
            case R.id.tv_phone:
                //拨打电话
                callServiceStore(tv_phone.getText().toString());
                break;
            case R.id.tv_order_service://预订服务
                if (user != null){
                    orderService();
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            default:
                break;
        }
    }
    /*
    预定服务
     */
    private void orderService() {
        UIHelper.togoServiceOrderActivity(this,service);
    }

    private void callServiceStore(String phone) {
        UIHelper.callThePhoneNumber(this,phone);
    }
}
