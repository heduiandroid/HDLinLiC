package com.linli.consumer.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiAdvertisementActivity extends Activity implements View.OnClickListener {
    private Button btn_toshopindex;
    private SimpleDraweeView iv_ad_pic;
    private TextView tv_title,tv_create_time,tv_contents;
    private Long id;//广告的ID
    private long merchantId;//代理商ID
    private String named;//广告名称
    private String intro;//z广告详细介绍
    private String createTime;
    private Integer typed;//广告的类型
    private String path;//广告链接
    private int category;
    private Boolean istakeout;
    private String shopName;//商家名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_yi_pai_advertisement);
        init();
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
        merchantId = intent.getLongExtra("merchantId",0);
        named = intent.getStringExtra("named");
        intro = intent.getStringExtra("intro");
        createTime = intent.getStringExtra("createTime");
        typed = intent.getIntExtra("typed", 0);
        path = intent.getStringExtra("path");
        if (merchantId == 0 || id == 0){
            Toast.makeText(PaiYiPaiAdvertisementActivity.this, "还没有商家优惠信息哦~", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            request_shopcate();
            show_advertasiment();
        }
    }
    /*
    请求该shop的类型
     */
    private void request_shopcate() {
        IntrestBuyNet.findShopByshopId(merchantId, new HandleSuccess<MallShopInfo>() {
            @Override
            public void success(MallShopInfo s) {
                if (s.getStatus() == 1){
                    shopName = s.getData().getName();
                    category = s.getData().getCategoryType();//1商城 2餐饮 3服务
                }else if (s.getStatus() == 2){//不是商城商家 就去查订餐商家
                    request_foodshopcate();
                }
            }
        });
    }
    private void request_foodshopcate() {
        IntrestBuyNet.findFoodShopByshopId(merchantId, new HandleSuccess<StoreInfo>() {
            @Override
            public void success(StoreInfo s) {
                if (s.getStatus() == 1){
                    btn_toshopindex.setText("开始点餐");
                    shopName = s.getData().getName();
                    category = s.getData().getCategoryType();
                }
            }
        });
    }
    private void init() {
        findViewById(R.id.sv_advert).setOnClickListener(this);
        btn_toshopindex = (Button) findViewById(R.id.btn_toshopindex);
        iv_ad_pic = (SimpleDraweeView) findViewById(R.id.iv_ad_pic);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_create_time = (TextView) findViewById(R.id.tv_create_time);
        tv_contents = (TextView) findViewById(R.id.tv_contents);
        iv_ad_pic.setOnClickListener(this);
        btn_toshopindex.setOnClickListener(this);
    }

    private void show_advertasiment() {
        tv_title.setText(named);
        iv_ad_pic.setImageURI(path);
        tv_contents.setText(intro);
        tv_create_time.setText(createTime);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sv_advert:
            case R.id.btn_toshopindex:
                switch (category){
                    case 1:
                        UIHelper.togoShopDetailActivity(this,merchantId,shopName,SHOP_MAIN);
                        break;
                    case 2:
                        UIHelper.togoShopDetailActivity(this,merchantId,shopName,FOOD_MAIN);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
