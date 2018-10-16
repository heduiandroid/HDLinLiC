package com.linli.consumer.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.PayingOrderAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.YaoPayOrder;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Order;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class YaoOrdersToPayActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView lv_paying_order;
    private AppContext appContext;
    private User user;
    private long storeId;
    private ArrayList<Order> ordersPaying = new ArrayList<Order>();
    private PayingOrderAdapter adapter;
    private boolean isRequesting = false;//是否正在请求接口 默认没有正在请求
    private boolean isRequested = false;//是否已经请求过接口了 默认没有请求过

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yao_orders_to_pay;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("我的账单");
        lv_paying_order = findView(R.id.lv_paying_order);
        lv_paying_order.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        adapter = new PayingOrderAdapter(ordersPaying,this);
        lv_paying_order.setAdapter(adapter);
        storeId = getIntent().getLongExtra("shopid", 0l);

    }
    /*
    查询用户在该商城商家的未付款订单
     */
    private void request_payingorders() {
        isRequesting = true;
        isRequested = true;
        IntrestBuyNet.findOrderList(user.getId(), storeId, 0,new HandleSuccess<YaoPayOrder>() {
            @Override
            public void success(YaoPayOrder s) {
                if (s.getStatus() == 1){
                    //如果list的size > 0 刷新adapter
                    if (s.getData().getHdFoodOrderlist() != null){

                        List<YaoPayOrder.DataBean.HdFoodOrderlistBean> foodList = s.getData().getHdFoodOrderlist();
                        for (int i = 0;i < foodList.size();i++){
                            Order order = new Order();
                            YaoPayOrder.DataBean.HdFoodOrderlistBean.HdFoodOrderBean orderInfo = foodList.get(i).getHdFoodOrder();
                            order.setType(2);
                            order.setShopId(orderInfo.getStoreId());
                            order.setShopName(foodList.get(i).getStoreName());//
                            order.setId(orderInfo.getId());
                            order.setOrderNum(orderInfo.getOrderSn());
                            order.setTransid(orderInfo.getTransId());//transid
                            order.setStatus(orderInfo.getOrderStatus());//
                            order.setRealName(orderInfo.getPurchaser());
                            order.setFreight(0d);////////////////////////////////////////////加上运费
                            order.setPackageCost(order.getPackageCost());////////////////////////////////////////////加上打包费
                            order.setFavourableFee(0d);////////////////////////////////////////////减去优惠金额
                            if (orderInfo.getPurchaserPhone() != null){
                                order.setReceiverPhone(orderInfo.getPurchaserPhone());
                            }else {
                                order.setReceiverPhone("没有预留电话");
                            }
                            order.setReceiverAddress("");//收货地址
                            order.setPayPrice(orderInfo.getOrderAmount());
                            Date dt = new Date(orderInfo.getCreateTime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            order.setCreateTime(format.format(dt));
                            List<YaoPayOrder.DataBean.HdFoodOrderlistBean.CookbookVoListBean> foodsOrGoods = foodList.get(i).getCookbookVoList();
                            ArrayList<Product> foods = new ArrayList<Product>();
                            for (int j = 0;j<foodsOrGoods.size();j++){
                                YaoPayOrder.DataBean.HdFoodOrderlistBean.CookbookVoListBean.GoodsBean itemOrderInfo = foodsOrGoods.get(j).getGoods();
                                YaoPayOrder.DataBean.HdFoodOrderlistBean.CookbookVoListBean.CookbookBean itemFoodInfo = foodsOrGoods.get(j).getCookbook();
                                Product product = new Product();
                                product.setId(itemOrderInfo.getGoodsId());
                                if (itemFoodInfo != null){
                                    product.setName(itemFoodInfo.getName());
                                    product.setPrice(itemFoodInfo.getPrice());
                                    product.setPicPath(itemFoodInfo.getImgpath());
                                }
                                product.setCountInOrder(itemOrderInfo.getGoodsNum());
                                product.setIsEvaled(0);//是否已评价
                                if (product.getIsEvaled() == null || product.getIsEvaled() == 0){
                                    product.setPraiseNum(0);
                                }else {
                                    product.setPraiseNum(5);
                                }
                                foods.add(product);
                            }
                            order.setProCount(foods.size());
                            order.setProList(foods);
                            ordersPaying.add(order);
                        }
                        if (ordersPaying.size() > 0){
                            adapter.notifyDataSetChanged();
                        }
                        Log.i("test","美食订单 over");
                    }
                    if (s.getData().getMallOrderList() != null){
                        List<YaoPayOrder.DataBean.MallOrderListBean> goodList = s.getData().getMallOrderList();
                        for (int i = 0;i < goodList.size();i++){
                            Order order = new Order();
                            YaoPayOrder.DataBean.MallOrderListBean.HdMallOrderBean orderInfo = goodList.get(i).getHdMallOrder();
                            order.setType(1);
                            order.setShopId(orderInfo.getStoreId());
                            order.setShopName(s.getData().getMallOrderList().get(i).getStoreName());
                            order.setId(orderInfo.getId());
                            order.setOrderNum(orderInfo.getOrderSn());
                            order.setTransid(orderInfo.getTransId());//transid
                            order.setStatus(orderInfo.getOrderStatus());///////////////////////////////////////需设置真实状态
                            order.setRealName(orderInfo.getPurchaser());
                            order.setFreight(0d);////////////////////////////////////////////加上运费
                            order.setPackageCost(0d);////////////////////////////////////////////加上打包费
                            order.setFavourableFee(0d);////////////////////////////////////////////减去优惠金额
                            if (orderInfo.getPhone() != null){
                                order.setReceiverPhone(orderInfo.getPhone());
                            }else {
                                order.setReceiverPhone("没有预留电话");
                            }
                            order.setReceiverAddress(orderInfo.getAddress());
                            order.setPayPrice(orderInfo.getOrderAmount());
                            Date dt = new Date(orderInfo.getCreateTime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            order.setCreateTime(format.format(dt));
                            List<YaoPayOrder.DataBean.MallOrderListBean.MgVoListBean> foodsOrGoods = s.getData().getMallOrderList().get(i).getMgVoList();
                            ArrayList<Product> goods = new ArrayList<Product>();
                            for (int j = 0;j<foodsOrGoods.size();j++){
                                YaoPayOrder.DataBean.MallOrderListBean.MgVoListBean.MallGoodsBean itemOrderInfo = foodsOrGoods.get(j).getMallGoods();
                                Product product = new Product();
                                product.setId(itemOrderInfo.getId());
                                product.setName(itemOrderInfo.getName());
                                product.setPrice(foodsOrGoods.get(j).getPlatformPrice());
                                product.setCountInOrder(foodsOrGoods.get(j).getNum());
                                product.setPicPath(itemOrderInfo.getPrimaryImage()+ Common.WSMALLERPICPARAM);
                                goods.add(product);
                            }
                            order.setProCount(goods.size());
                            order.setProList(goods);
                            ordersPaying.add(order);
                        }
                        if (ordersPaying.size() > 0){
                            adapter.notifyDataSetChanged();
                        }else {
                            YaoOrdersToPayActivity.this.finish();
                            Toast.makeText(YaoOrdersToPayActivity.this,"该店铺没有您的未付款账单~",Toast.LENGTH_SHORT).show();
                        }
                        Log.i("test","商城订单 over");
                    }
                    dismiss();
                }else {
                    finish();
                    Toast.makeText(YaoOrdersToPayActivity.this,"没有未付款订单哦~",Toast.LENGTH_SHORT).show();
                }
                isRequesting = false;
            }
        });
    }

    @Override
    protected void onResume() {
        isRequested = false;
        if (storeId != 0l){
            if (!isRequesting && !isRequested){
                ordersPaying.clear();
                adapter.notifyDataSetChanged();
                request_payingorders();
            }
        }else {
            finish();
            Toast.makeText(this,"没有未付款订单哦~",Toast.LENGTH_SHORT).show();
        }
        super.onResume();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        YaoYiYaoActivity.orderId = ordersPaying.get(i).getId();
        if ( YaoYiYaoActivity.category != null && YaoYiYaoActivity. orderId != null){
            UIHelper.togoOnLinePayActivity(YaoOrdersToPayActivity.this,Long.valueOf(ordersPaying.get(i).getOrderNum()),ordersPaying.get(i).getPayPrice(),ordersPaying.get(i).getOrderNum(),ordersPaying.get(i).getId());
            //去选择是微信还是支付宝支付之类的
        }else {
            Toast.makeText(YaoOrdersToPayActivity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (resultCode == 1009){
                boolean success = data.getBooleanExtra("success",false);
                if (success){
                    //将订单号设为已付款订单后finish
                    request_set_payed_order();//付款成功要在调一个接口修改状态
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void request_set_payed_order() {
        if ( YaoYiYaoActivity.category == null ||  YaoYiYaoActivity.orderId == null){
            Toast.makeText(this,"支付遇到问题，请联系客服",Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(new Intent(YaoOrdersToPayActivity.this, PaySuccessActivity.class));
    }
}
