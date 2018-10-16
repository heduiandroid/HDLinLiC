package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class ScanningBuyActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView iv_shopcart;
    private ImageView iv_have_new;
    private ListView lv_scaned_product;
    private TextView tv_errormsg;
    private ArrayList<Product> products = new ArrayList<Product>();
    private Boolean istakeout;
    private Long orderId = null;
    private Double totalPrice = null;
    private String orderNo = null;
    private Integer category = null;//店铺类型
    private AppContext appContext = AppContext.getInstance();
    private User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_buy);
        init();
        startActivityForResult(new Intent(ScanningBuyActivity.this, CaptureActivity.class), 0);
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setText("码上购");
        tv_head_name.setOnClickListener(this);
        tv_errormsg = (TextView) findViewById(R.id.tv_errormsg);
        lv_scaned_product = (ListView) findViewById(R.id.lv_scaned_product);
        iv_shopcart = (ImageView) findViewById(R.id.iv_shopcart);
        iv_have_new = (ImageView) findViewById(R.id.iv_have_new);
        lv_scaned_product.setOnItemClickListener(this);
        iv_shopcart.setOnClickListener(this);
        user = appContext.getUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.iv_shopcart:
                //去商城的购物车
//                startActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (resultCode == RESULT_OK){
                String format = data.getStringExtra(Intents.Scan.RESULT_FORMAT);
                if (format.equals("QR_CODE")){
                    //如果是二维码，就直接去某店铺的首页，需要店铺的ID
                    String result = data.getExtras().getString(Intents.Scan.RESULT);
                    Log.i("result",result);
                    Long prodId = null;
                    Long shopId = null;
                    Long fshopId = null;
                    Long sshopId = null;
                    String room = null;
                    try {
                        JSONObject res = null;
                        if (result.contains("hdfood.shanght.com") || result.contains("hdfood.sht18.com")) {
                            res = CommonUtil.getParamsFromUrl(result);
                            Log.i("resultJson","链接转json:"+res.toString());
                        }else{
                            res = new JSONObject(result);
                            Log.i("resultJson","本就是json:"+res.toString());
                        }
                        int tag = res.getInt("tag");
                        switch (tag){
                            case 0://商城商家
                                shopId = res.getLong("shopId");
                                requestUserConnectShoper(shopId,tag);
                                break;
                            case 1://商城商品
                                prodId = res.getLong("proId");
                                break;
                            case 2://餐饮商家（带桌号）
                                fshopId = res.getLong("storeId");
                                room = res.getString("num");
                                requestUserConnectShoper(fshopId,tag);
                                break;
                            case 3://餐饮商家
                                fshopId = res.getLong("shopId");
                                requestUserConnectShoper(fshopId,tag);
                                break;
                            case 4://订单支付（扫令掌柜订单支付码）
                                orderId = res.getLong("orderid");
                                totalPrice = res.getDouble("totalprice");
                                orderNo = res.getString("orderno");
                                category = res.getInt("categorytype");
//                                get_alipay_private();
                                break;
                            case 5://服务商家
                                sshopId = res.getLong("shopId");
                                requestUserConnectShoper(sshopId,tag);
                                break;
                            default:
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (prodId != null){//去商城商品详情页
                        UIHelper.togoGoodsDetailActivity(this,prodId,"商品详情");
                        finish();
                    }else if (shopId != null){//去商城商家首页
                        request_mall_shopinfo(shopId);
                    }else  if (fshopId != null){//去订餐外卖首页
                        if (room != null && !"".equals(room)){
                            request_food_shopinfo(fshopId,room);
                        }else {
                            request_food_shopinfo(fshopId,null);
                        }
                    }else if (orderId != null && totalPrice != null && orderNo != null && category != null){
                        UIHelper.togoOnLinePayActivity(ScanningBuyActivity.this,Long.valueOf(orderNo),totalPrice,orderNo,orderId);

                    }else if (sshopId != null){//去服务商家首页
                        request_service_shopinfo(shopId);
                    }else {
                        tv_errormsg.setText("未识别的二维码【" + result + "】");
//                        Toast.makeText(ScanningBuyActivity.this,"不能识别的二维码【"+result+"】",Toast.LENGTH_SHORT).show();
                    }
                }else {//应该就是条形码，在此界面显示符合条形码的商品数据就可以了
                    String OneDCode = data.getExtras().getString(Intents.Scan.RESULT);
                    products.clear();
                    tv_errormsg.setText("未知的条形码【" + OneDCode + "】");
//                    Toast.makeText(ScanningBuyActivity.this,"未知的条形码",Toast.LENGTH_SHORT).show();
                }
            }else if (resultCode == 1009){
                //将订单号设为已付款订单后finish
                boolean success = data.getBooleanExtra("success",false);
                if (success){
                    request_set_payed_order();
                }else {
                    tv_errormsg.setText("未能完成支付");
                }
            }
        }else {finish();}
    }

    private void requestUserConnectShoper(Long shopId, int tag) {
        if (user != null && shopId != null){
            IntrestBuyNet.storeMemRelation(user.getId(), shopId, tag, new HandleSuccess<Generic>() {
                @Override
                public void success(Generic generic) {
                    Log.i("Message:","Relation Has Builded");
                }
            });
        }
    }

    private void request_service_shopinfo(final Long shopId) {
        FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
            @Override
            public void success(ServiceStoreBean s) {
                if (s.getData() != null && s.getData().getName() != null) {
                    UIHelper.togoShopDetailActivity(ScanningBuyActivity.this,shopId,s.getData().getName(),SERVICE_MAIN);
                }else {
                    Toast.makeText(ScanningBuyActivity.this,"暂无此商家",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void request_mall_shopinfo(final Long storeid) {
        IntrestBuyNet.findShopByshopId(storeid, new HandleSuccess<MallShopInfo>() {
            @Override
            public void success(MallShopInfo s) {
                if (s.getStatus() == 1){
                    String shopname = s.getData().getName();
                    UIHelper.togoShopDetailActivity(ScanningBuyActivity.this,storeid,shopname,SHOP_MAIN);
                }else {
                    Toast.makeText(ScanningBuyActivity.this,s.getMsg(),Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void request_set_payed_order() {
        //已经付款成功 返回的刚刚成功的数据 可以去某个界面显示一下付款成功
        int cate = 0;
        switch (category){
            case 1:
                cate = 3;
                break;
            case 2:
                cate = 1;
                break;
            case 3:
                cate = 4;
                break;
        }
        if (cate != 0){
            ScanningBuyActivity.this.finish();
            startActivity(new Intent(ScanningBuyActivity.this, PaySuccessActivity.class));
            System.out.println("去付款成功通知界面");
        }else {
            Toast.makeText(ScanningBuyActivity.this, "店铺维护中，请稍后再试", Toast.LENGTH_SHORT).show();
        }
    }
    private void request_food_shopinfo(final Long storeid, final String room) {
        IntrestBuyNet.findFoodShopByshopId(storeid, new HandleSuccess<StoreInfo>() {
            @Override
            public void success(StoreInfo s) {
                if (s.getStatus() == 2){//兼容老版本二维码
                    request_service_shopinfo(storeid);
                    return;
                }
                if (s.getStatus() == 1){
                    String shopname = s.getData().getName();
                    if (room != null){
                        UIHelper.togoShopDetailActivityWithRoom(ScanningBuyActivity.this,storeid,shopname,FOOD_MAIN,room);
                    }else {
                        UIHelper.togoShopDetailActivity(ScanningBuyActivity.this,storeid,shopname,FOOD_MAIN);
                    }
                }else {
                    Toast.makeText(ScanningBuyActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }
}
