package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.BusinessInfo;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class BusinessInfoDetailActivity extends BaseActivity {
    private WebView wv_business_info;
    private TextView tv_report_info;
    private Intent intent;
    private BusinessInfo businessInfo;
    private TextView tv_title;
    private TextView tv_create_time;
    private SimpleDraweeView iv_info_pic;
    private TextView tv_content;
    private TextView tv_gotoshop;
    public static ArrayList<Long> reportedIds = new ArrayList<Long>();//被此用户举报过的商讯ID

    @Override
    protected int getLayoutId() {
        return R.layout.activity_business_info_detail;
    }

    @Override
    protected void initView() {
        init();
//        setWebViewParams();
    }

    @Override
    protected void initData() {
        intent = this.getIntent();
        businessInfo = (BusinessInfo) intent.getSerializableExtra("businessinfo");
//        wv_business_info.loadUrl(url);
        if (reportedIds.contains(businessInfo.getId())){
            tv_report_info.setClickable(false);
            tv_report_info.setText("已举报");
        }
        tv_title.setText(businessInfo.getTitle());
        tv_create_time.setText(businessInfo.getDate());
        tv_content.setText(businessInfo.getContents());
        iv_info_pic.setImageURI(businessInfo.getImagePath()+Common.WSMALLERPICPARAM400);
        tv_gotoshop.setVisibility(View.GONE);
        if (businessInfo.getStoreId() != null){
            tv_gotoshop.setVisibility(View.VISIBLE);
        }
        dismiss();

        request_addcount();//增加点击数
    }

    private void request_addcount() {
        if (businessInfo.getCategory() == FOOD_MAIN){
            IntrestBuyNet.updateAdVoCountF(businessInfo.getId(), businessInfo.getCount()+1, new HandleSuccess<Generic>() {
                @Override
                public void success(Generic s) {
                    Log.i("test","美食广告点击数+1");
                }
            });
        }else if (businessInfo.getCategory() == SHOP_MAIN){
            IntrestBuyNet.updateAdVoCount(businessInfo.getId(), businessInfo.getCount()+1, new HandleSuccess<Generic>() {
                @Override
                public void success(Generic s) {
                    Log.i("test","商城广告点击数+1");
                }
            });
        }
    }

    private void setWebViewParams() {
        wv_business_info.setVerticalScrollbarOverlay(true);
        WebSettings settings = wv_business_info.getSettings();
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);//设定支持缩放

        wv_business_info.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                setTitle("加载中...");
                setProgress(newProgress * 100);
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void init() {
        TextView tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setText("商讯详情");
//        wv_business_info = (WebVieew) findViewById(R.id.wv_business_info);
        tv_head_name.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_create_time = (TextView) findViewById(R.id.tv_create_time);
        iv_info_pic = (SimpleDraweeView) findViewById(R.id.iv_info_pic);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_report_info = (TextView) findViewById(R.id.tv_report_info);
        tv_report_info.setOnClickListener(this);
        tv_gotoshop = findViewClick(R.id.tv_gotoshop);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_report_info:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确认举报此条讯息？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request_report_info();
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
            case R.id.tv_gotoshop:
                if (businessInfo.getCategory() == FOOD_MAIN){
                    IntrestBuyNet.findFoodShopByshopId(businessInfo.getStoreId(), new HandleSuccess<StoreInfo>() {
                        @Override
                        public void success(StoreInfo s) {
                            if (s.getStatus() == 1){
                                if (s.getData() != null){
                                    String name = s.getData().getName();
                                    UIHelper.togoShopDetailActivity(BusinessInfoDetailActivity.this,businessInfo.getStoreId(),name,businessInfo.getCategory());
                                }else {
                                    Toast.makeText(BusinessInfoDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }else if (businessInfo.getCategory() == SHOP_MAIN){
                    IntrestBuyNet.findShopByshopId(businessInfo.getStoreId(), new HandleSuccess<MallShopInfo>() {
                        @Override
                        public void success(MallShopInfo s) {
                            if (s.getStatus() == 1){
                                if (s.getData() != null){
                                    String name = s.getData().getName();
                                    UIHelper.togoShopDetailActivity(BusinessInfoDetailActivity.this,businessInfo.getStoreId(),name,businessInfo.getCategory());
                                }
                            }else {
                                Toast.makeText(BusinessInfoDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"该广告只供浏览",Toast.LENGTH_SHORT).show();
                }
                Log.i("test","storeId is "+ businessInfo.getStoreId());
                break;
            default:
                break;
        }
    }

    private void request_report_info() {//可用参数变量 id参数 用户id参数
//        IntrestBuyNet.updateAdVo(businessInfo.getId(), 1, new HandleSuccess<Generic>() {
//            @Override
//            public void success(Generic s) {
//                if (s.getStatus() == 1){
                    reportedIds.add(businessInfo.getId());
                    tv_report_info.setClickable(false);
                    tv_report_info.setText("已举报");
                    Log.i("test","举报数+1");
//                }else {
//                    Toast.makeText(BusinessInfoDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}
