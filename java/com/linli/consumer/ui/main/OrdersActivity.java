package com.linli.consumer.ui.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.OrderListAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.OrderFood;
import com.linli.consumer.bean.OrderProduct;
import com.linli.consumer.bean.OrderServiceList;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Order;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class OrdersActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private View line_mall_order,line_food_order,line_service_order;
    private ListView lv_orders;
    private TextView tv_mall_order,tv_food_order,tv_service_order;
    private TextView tv_nodata;
    private int ALL = 0;
    private int PAYING = 11;
    private int SENDING = 1;
    private int RECEIVING = 9;
    private int FINISHED = 4;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Order> newItems = new ArrayList<Order>();
    public static OrderListAdapter orderListAdapter;
    private AppContext appContext;
    private User user;
    private int type = 3;//类别 1订餐  2外卖   3商城 4服务
    private int pager = 1;
    private SwipeRefreshLayout srl_orders;
//    private LinearLayout footerview;//footer暂时不要了

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orders;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        Intent intent = getIntent();
//        footerview = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);
//        type = intent.getIntExtra("cate",0);//1订餐  2外卖   3商城 4服务
//        STATUS = intent.getIntExtra("status",0);
        switch (type){////类别 1订餐  2外卖   3商城 4服务
            case 1:
                line_food_order.setBackgroundColor(getResources().getColor(R.color.orange));
                request_order(ALL);
                break;
            case 2:
                line_food_order.setBackgroundColor(getResources().getColor(R.color.orange));
                request_order(ALL);
                break;
            case 3:
                line_mall_order.setBackgroundColor(getResources().getColor(R.color.orange));
                request_order(ALL);
                break;
            case 4:
                line_service_order.setBackgroundColor(getResources().getColor(R.color.orange));
                request_order(ALL);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("我的订单");
        line_mall_order = findView(R.id.line_mall_order);
        line_food_order = findView(R.id.line_food_order);
        line_service_order = findView(R.id.line_service_order);
        lv_orders = findView(R.id.lv_orders);
        tv_nodata= findView(R.id.tv_nodata);
        findViewClick(R.id.iv_back);
        tv_mall_order = findViewClick(R.id.tv_mall_order);
        tv_food_order = findViewClick(R.id.tv_food_order);
        tv_service_order = findViewClick(R.id.tv_service_order);
        lv_orders.setOnItemClickListener(this);
        lv_orders.setOnScrollListener(this);
        tv_mall_order = findViewClick(R.id.tv_mall_order);
        tv_food_order = findViewClick(R.id.tv_food_order);
        tv_service_order = findViewClick(R.id.tv_service_order);
        srl_orders = findView(R.id.srl_orders);
        srl_orders.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        srl_orders.setSize(SwipeRefreshLayout.DEFAULT);
        srl_orders.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        srl_orders.setProgressViewEndTarget(true, 100);
        srl_orders.setOnRefreshListener(this);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_mall_order:
                if (type != 3){
                    type = 3;
                    pager = 1;
                    clearline();
                    line_mall_order.setBackgroundColor(getResources().getColor(R.color.orange));
                    request_order(ALL);
                }
                break;
            case R.id.tv_food_order:
                if (type != 1){
                    type = 1;
                    pager = 1;
                    clearline();
                    line_food_order.setBackgroundColor(getResources().getColor(R.color.orange));
                    request_order(ALL);
                }
                break;
            case R.id.tv_service_order:
                if (type != 4){
                    type = 4;
                    pager = 1;
                    clearline();
                    line_service_order.setBackgroundColor(getResources().getColor(R.color.orange));
                    request_order(ALL);
                }
                break;
//            case R.id.tv_all_order:
//                if (ALL != STATUS){
//                    pager = 1;
//                    STATUS = ALL;
//                    clearline();
//                    line_all_order.setBackgroundColor(getResources().getColor(R.color.orange));
//                    request_order(ALL);
//                }
//                break;
//            case R.id.tv_paying_order:
//                if (PAYING != STATUS){
//                    pager = 1;
//                    STATUS = PAYING;
//                    clearline();
//                    line_paying_order.setBackgroundColor(getResources().getColor(R.color.orange));
//                    request_order(PAYING);
//                }
//
//                break;
//            case R.id.tv_sending_order:
//                if (SENDING != STATUS){
//                    pager = 1;
//                    STATUS = SENDING;
//                    clearline();
//                    line_sending_order.setBackgroundColor(getResources().getColor(R.color.orange));
//                    request_order(SENDING);
//                }
//
//                break;
//            case R.id.tv_receiving_order:
//                if (RECEIVING != STATUS){
//                    pager = 1;
//                    STATUS = RECEIVING;
//                    clearline();
//                    line_receiving_order.setBackgroundColor(getResources().getColor(R.color.orange));
//                    request_order(RECEIVING);
//                }
//
//                break;
//            case R.id.tv_evaluating_order:
//                if (FINISHED != STATUS){
//                    pager = 1;
//                    STATUS = FINISHED;
//                    clearline();
//                    line_evaluating_order.setBackgroundColor(getResources().getColor(R.color.orange));
//                    request_order(FINISHED);
//                }
//                break;
            default:
                break;
        }
    }

    private void clearline() {
        line_mall_order.setBackgroundColor(getResources().getColor(R.color.white));
        line_food_order.setBackgroundColor(getResources().getColor(R.color.white));
        line_service_order.setBackgroundColor(getResources().getColor(R.color.white));
    }
    private void request_order(int status) {
        if (pager == 1){
            showDialog();
        }
        if (type == 1 || type == 2){//如果是想查订餐订单
            IntrestBuyNet.selectOrderListFood(10, pager, type, user.getId(), new HandleSuccess<OrderFood>() {
                @Override
                public void success(OrderFood s) {
                    newItems.clear();
                    if (s.getStatus() == 1){
                        if (pager == 1)
                            orders.clear();
                        if (s.getData() != null && s.getData().size()>0){
                            for (int i = 0;i < s.getData().size();i++){
                                Order order = new Order();
                                OrderFood.DataBean.HdFoodOrderBean orderInfo = s.getData().get(i).getHdFoodOrder();
                                order.setType(type);
                                order.setShopId(orderInfo.getStoreId());
                                order.setShopName(s.getData().get(i).getStoreName());//
                                order.setId(orderInfo.getId());
                                order.setPayKind(orderInfo.getPaytype());
                                order.setOrderNum(orderInfo.getOrderSn());
                                order.setTransid(orderInfo.getTransId());//transid
                                order.setStatus(orderInfo.getOrderStatus());//
                                order.setRealName(orderInfo.getPurchaser());
                                if (orderInfo.getGoodsAmount()!=null) {
                                    order.setGoodsAmount(orderInfo.getGoodsAmount());
                                }else {
                                    order.setGoodsAmount(orderInfo.getOrderAmount());//以前的订单没有商品总价先直接显示个订单总价吧
                                }
                                order.setCouponId(orderInfo.getCouponId());
                                if (orderInfo.getFavorableAmount()!=null){
                                    order.setFavourableFee(orderInfo.getFavorableAmount());////////////////////////////////////////////减去优惠金额
                                }else {
                                    order.setFavourableFee(0d);////////////////////////////////////////////减去优惠金额
                                }
                                Date adt = new Date(orderInfo.getAppointTime());
                                SimpleDateFormat aformat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                                aformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                order.setAppointTime(aformat.format(adt));
                                if (orderInfo.getAppointPerson() != null){
                                    order.setAppointPerson(orderInfo.getAppointPerson());
                                }
                                order.setAppointRoom(orderInfo.getAppointRoom());//预约是否要求包间(0-否，1-是)
                                if (orderInfo.getOrderType() != null){
                                    order.setOrderType(orderInfo.getOrderType());
                                    switch (orderInfo.getOrderType()){// (1:点餐，2:外卖 3预订)
                                        case 1:
                                        case 3:
                                            order.setByUserSelfIntoShop(true);
                                            break;
                                        case 2:
                                            order.setByUserSelfIntoShop(false);
                                            if (orderInfo.getIsByself() != null){//是外卖但是用户要自取
                                                if (orderInfo.getIsByself() == 2){//1-配送，2-自取(仅当外卖时填写)
                                                    order.setByUserSelfIntoShop(true);
                                                }else {
                                                    order.setByUserSelfIntoShop(false);
                                                }
                                            }
                                            break;
                                        default:
                                            order.setByUserSelfIntoShop(false);
                                            break;
                                    }
                                }else {
                                    order.setByUserSelfIntoShop(false);
                                }
                                if (orderInfo.getLogisticsAmount() != null){
                                    order.setFreight(orderInfo.getLogisticsAmount());////////////////////////////////////////////加上运费
                                }else {
                                    order.setFreight(0d);////////////////////////////////////////////加上运费
                                }
                                if (orderInfo.getPurchaserPhone() != null){
                                    order.setReceiverPhone(orderInfo.getPurchaserPhone());
                                }else {
                                    order.setReceiverPhone("没有预留电话");
                                }
                                order.setReceiverAddress(orderInfo.getAddress());//收货地址
                                order.setPayPrice(orderInfo.getOrderAmount());
                                Date dt = new Date(orderInfo.getCreateTime());
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                order.setCreateTime(format.format(dt));
                                if (orderInfo.getPackageCost() != null){
                                    order.setPackageCost(orderInfo.getPackageCost());//这个是单个打包费
                                }else {
                                    order.setPackageCost(0d);
                                }
                                List<OrderFood.DataBean.CookbookVoListBean> foodsOrGoods = s.getData().get(i).getCookbookVoList();
                                ArrayList<Product> foods = new ArrayList<Product>();
                                for (int j = 0;j<foodsOrGoods.size();j++){
                                    OrderFood.DataBean.CookbookVoListBean.GoodsBean itemOrderInfo = foodsOrGoods.get(j).getGoods();
                                    OrderFood.DataBean.CookbookVoListBean.CookbookBean itemFoodInfo = foodsOrGoods.get(j).getCookbook();
                                    if (itemOrderInfo != null && itemFoodInfo != null){
                                        Product product = new Product();
                                        product.setId(itemFoodInfo.getId());
                                        product.setName(itemFoodInfo.getName());
                                        product.setPrice(itemOrderInfo.getPrice());
                                        product.setCountInOrder(itemOrderInfo.getGoodsNum());
                                        product.setPicPath(itemFoodInfo.getImgpath() + Common.MORESMALLERPICPARAM);
                                        product.setIsEvaled(0);//是否已评价
                                        if (itemFoodInfo.getIspackagecost() != null){
                                            product.setIsPackageCost(itemFoodInfo.getIspackagecost());
                                        }else {
                                            product.setIsPackageCost(0);
                                        }
                                        if (product.getIsEvaled() == null || product.getIsEvaled() == 0){
                                            product.setPraiseNum(0);
                                        }else {
                                            product.setPraiseNum(5);
                                        }
                                        foods.add(product);
                                    }else {
                                        break;
                                    }
                                }
                                order.setProCount(foods.size());
                                order.setProList(foods);
                                newItems.add(order);
                                orders.add(order);
                            }
                        }
                        if (pager == 1){
                            if (orders.size()>0) {
                                //if  have datas
                                //如果有数据 显示ListView 隐藏没数据的view
                                tv_nodata.setVisibility(View.GONE);
                                srl_orders.setVisibility(View.VISIBLE);
                                if (orders.size()>9){
//                                    lv_orders.addFooterView(footerview,null,false);
                                }
                                orderListAdapter = new OrderListAdapter(orders,OrdersActivity.this);
                                lv_orders.setAdapter(orderListAdapter);
                                pager++;
                            }else {
                                //if have no datas
                                tv_nodata.setVisibility(View.VISIBLE);
                                srl_orders.setVisibility(View.GONE);
                            }
                            srl_orders.setRefreshing(false);
                        }else {
                            if (newItems.size()>0){
                                orderListAdapter.notifyDataSetChanged();
                                pager++;
                            }else {
                                if (orders.size()>=10){
//                                    lv_orders.removeFooterView(footerview);
                                }
                            }
                        }
                    }else {
                        Toast.makeText(OrdersActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            });
        }else if (type == 3){//如果是想查商城订单
            IntrestBuyNet.selectOrderListMall(10, pager, type, user.getId(), new HandleSuccess<OrderProduct>() {
                @Override
                public void success(OrderProduct s) {
                    newItems.clear();
                    if (s.getStatus() == 1){
                        if (pager == 1)
                            orders.clear();
                        if (s.getData() != null && s.getData().size()>0){
                            for (int i = 0;i < s.getData().size();i++){
                                Order order = new Order();
                                OrderProduct.DataBean.HdMallOrderBean orderInfo = s.getData().get(i).getHdMallOrder();
                                order.setType(type);
                                order.setShopId(orderInfo.getStoreId());
                                order.setShopName(s.getData().get(i).getStoreName());
                                order.setId(orderInfo.getId());
                                order.setOrderNum(orderInfo.getOrderSn());
                                order.setPayKind(orderInfo.getPaytype());
                                order.setTransid(orderInfo.getTransId());//transid
                                order.setStatus(orderInfo.getOrderStatus());///////////////////////////////////////需设置真实状态
                                order.setRealName(orderInfo.getPurchaser());
                                order.setGoodsAmount(orderInfo.getGoodsAmount());
                                if (orderInfo.getByway() != null){
                                    switch (orderInfo.getByway()){// 1:配送    2:自取
                                        case 1:
                                            order.setByUserSelfIntoShop(false);
                                            break;
                                        case 2:
                                            order.setByUserSelfIntoShop(true);
                                            break;
                                        default:
                                            order.setByUserSelfIntoShop(false);
                                            break;
                                    }
                                }else {
                                    order.setByUserSelfIntoShop(false);
                                }
                                if (orderInfo.getLogisticsAmount() != null){
                                    order.setFreight(orderInfo.getLogisticsAmount());////////////////////////////////////////////加上运费
                                }else {
                                    order.setFreight(0d);////////////////////////////////////////////加上运费
                                }
                                order.setFreight(orderInfo.getLogisticsAmount());////////////////////////////////////////////加上运费
                                order.setPackageCost(0d);////////////////////////////////////////////加上打包费
                                order.setCouponId(orderInfo.getCouponId());
                                if (orderInfo.getFavorableAmount()!=null){
                                    order.setFavourableFee(orderInfo.getFavorableAmount());////////////////////////////////////////////减去优惠金额
                                }else {
                                    order.setFavourableFee(0d);////////////////////////////////////////////减去优惠金额
                                }
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
                                List<OrderProduct.DataBean.MgVoListBean> foodsOrGoods = s.getData().get(i).getMgVoList();
                                ArrayList<Product> goods = new ArrayList<Product>();
                                if (foodsOrGoods != null){
                                    for (int j = 0;j<foodsOrGoods.size();j++){
                                        OrderProduct.DataBean.MgVoListBean.MallGoodsBean itemOrderInfo = foodsOrGoods.get(j).getMallGoods();
                                        if (itemOrderInfo != null) {
                                            Product product = new Product();
                                            product.setId(itemOrderInfo.getId());
                                            product.setName(itemOrderInfo.getName());
                                            product.setPrice(foodsOrGoods.get(j).getPlatformPrice());
                                            product.setCountInOrder(foodsOrGoods.get(j).getNum());
                                            product.setPicPath(itemOrderInfo.getPrimaryImage() + Common.MORESMALLERPICPARAM);
                                            product.setIsEvaled(0);//是否已评价
                                            if (product.getIsEvaled() == null || product.getIsEvaled() == 0) {
                                                product.setPraiseNum(0);
                                            } else {
                                                product.setPraiseNum(5);
                                            }
                                            goods.add(product);
                                        }
                                    }
                                }
                                order.setProCount(goods.size());
                                order.setProList(goods);
                                newItems.add(order);
                                orders.add(order);
                            }
                        }
                        if (pager == 1){
                            if (orders.size()>0) {
                                //if  have datas
                                //如果有数据 显示ListView 隐藏没数据的view
                                tv_nodata.setVisibility(View.GONE);
                                srl_orders.setVisibility(View.VISIBLE);
                                if (orders.size()>9){
//                                    lv_orders.addFooterView(footerview,null,false);
                                }
                                orderListAdapter = new OrderListAdapter(orders,OrdersActivity.this);
                                lv_orders.setAdapter(orderListAdapter);
                                pager++;
                            }else {
                                //if have no datas
                                tv_nodata.setVisibility(View.VISIBLE);
                                srl_orders.setVisibility(View.GONE);
                            }
                            srl_orders.setRefreshing(false);
                        }else {
                            if (newItems.size()>0){
                                orderListAdapter.notifyDataSetChanged();
                                pager++;
                            }else {
                                if (orders.size()>=10){
//                                    lv_orders.removeFooterView(footerview);
                                }
                            }
                        }
                    }else {
                        Toast.makeText(OrdersActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            });
        }else if (type == 4){//如果是想查服务订单
            FoodNet.findorderServices(user.getId(), pager, 10, new HandleSuccess<OrderServiceList>() {
                @Override
                public void success(OrderServiceList s) {
                    newItems.clear();
                    if (s.getStatus() == 1){
                        if (pager == 1)
                            orders.clear();
                        if (s.getData() != null && s.getData().size()>0){
                            for (int i = 0;i < s.getData().size();i++){
                                Order order = new Order();
                                OrderServiceList.DataBean orderInfo = s.getData().get(i);
                                order.setType(type);
                                order.setShopId(orderInfo.getStoreid());
                                order.setShopName(s.getData().get(i).getHdServeGoods().getName());
                                order.setId(orderInfo.getId());
                                if (orderInfo.getOrdersn() != null) {
                                    order.setOrderNum(orderInfo.getOrdersn() + "");//订单号
                                }else {
                                    order.setOrderNum(orderInfo.getId() + "");//没有订单号用id做订单号
                                }
                                if (orderInfo.getGoodsamount() != null) {
                                    order.setGoodsAmount(orderInfo.getGoodsamount());
                                    order.setPayKind(1);//1线上  2现金
                                }else {
                                    order.setGoodsAmount(orderInfo.getHdServeGoods().getPrice());
                                    order.setPayKind(2);//1线上  2现金
                                }
                                if (orderInfo.getOrderamount() != null){
                                    order.setPayPrice(orderInfo.getOrderamount());
                                }else {
                                    order.setPayPrice(orderInfo.getHdServeGoods().getPrice());
                                }
                                order.setTransid(orderInfo.getOrdersn());//服务将id当做支付ID
                                order.setStatus(orderInfo.getOrderstatus());//服务直接设成已完成
                                order.setRealName(orderInfo.getLinkname());
                                order.setByUserSelfIntoShop(false);//默认用户不去店里
                                order.setFreight(0d);////////////////////////////////////////////加上运费
                                order.setPackageCost(0d);////////////////////////////////////////////加上打包费
                                order.setCouponId(orderInfo.getCouponId());
                                if (orderInfo.getFavorableamount()!=null){
                                    order.setFavourableFee(orderInfo.getFavorableamount());////////////////////////////////////////////减去优惠金额
                                }else {
                                    order.setFavourableFee(0d);////////////////////////////////////////////减去优惠金额
                                }
                                if (orderInfo.getLinkphone() != null){
                                    order.setReceiverPhone(orderInfo.getLinkphone());
                                }else {
                                    order.setReceiverPhone("没有预留电话");
                                }
                                order.setReceiverAddress(orderInfo.getAddress()+"\n预约时间："+orderInfo.getAppointment());
                                Date dt = new Date(orderInfo.getCreatetime());
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                order.setCreateTime(format.format(dt));
                                OrderServiceList.DataBean.HdServeGoodsBean foodsOrGoods = orderInfo.getHdServeGoods();
                                ArrayList<Product> goods = new ArrayList<Product>();
                                if (foodsOrGoods != null){
                                    Product product = new Product();
                                    product.setId(foodsOrGoods.getId());
                                    product.setName(foodsOrGoods.getName()+"-"+foodsOrGoods.getContent());
                                    product.setPrice(foodsOrGoods.getPrice());
                                    product.setCountInOrder(1);
                                    if (foodsOrGoods.getImg() != null && !"".equals(foodsOrGoods.getImg())){
                                        product.setPicPath(foodsOrGoods.getImg()+ Common.MORESMALLERPICPARAM);
                                    }else if (foodsOrGoods.getImg1() != null && !"".equals(foodsOrGoods.getImg1())){
                                        product.setPicPath(foodsOrGoods.getImg1()+ Common.MORESMALLERPICPARAM);
                                    }else if (foodsOrGoods.getImg2() != null && !"".equals(foodsOrGoods.getImg2())){
                                        product.setPicPath(foodsOrGoods.getImg2()+ Common.MORESMALLERPICPARAM);
                                    }else if (foodsOrGoods.getImg3() != null && !"".equals(foodsOrGoods.getImg3())){
                                        product.setPicPath(foodsOrGoods.getImg3()+ Common.MORESMALLERPICPARAM);
                                    }
                                    product.setIsEvaled(1);//是否已评价
                                    if (product.getIsEvaled() == null || product.getIsEvaled() == 0){
                                        product.setPraiseNum(0);
                                    }else {
                                        product.setPraiseNum(5);
                                    }
                                    goods.add(product);
                                }
                                order.setProCount(goods.size());
                                order.setProList(goods);
                                newItems.add(order);
                                orders.add(order);
                            }
                        }
                        if (pager == 1){
                            if (orders.size()>0) {
                                //if  have datas
                                //如果有数据 显示ListView 隐藏没数据的view
                                tv_nodata.setVisibility(View.GONE);
                                srl_orders.setVisibility(View.VISIBLE);
                                if (orders.size()>9){
//                                    lv_orders.addFooterView(footerview,null,false);
                                }
                                orderListAdapter = new OrderListAdapter(orders,OrdersActivity.this);
                                lv_orders.setAdapter(orderListAdapter);
                                pager++;
                            }else {
                                //if have no datas
                                tv_nodata.setVisibility(View.VISIBLE);
                                srl_orders.setVisibility(View.GONE);
                            }
                            srl_orders.setRefreshing(false);
                        }else {
                            if (newItems.size()>0){
                                orderListAdapter.notifyDataSetChanged();
                                pager++;
                            }else {
                                if (orders.size()>=10){
//                                    lv_orders.removeFooterView(footerview);
                                }
                            }
                        }
                    }else {
                        Toast.makeText(OrdersActivity.this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(OrdersActivity.this,OrderDetailActivity.class);
        intent.putExtra("order", orders.get(position));
        intent.putExtra("cate", type);
        startActivityForResult(intent, 1800);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        Log.i("test",user.getOrderNo()+"");
       if (user.getOrderNo() != null){
           Log.i("test","订单编号为"+user.getOrderNo());
           user.setOrderNo(null);
           pager = 1;
           orders.clear();
           request_order(ALL);
       }
        super.onResume();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount()-1)){
                    request_order(ALL);
                }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (resultCode == 1800){
                int type = data.getIntExtra("type",0);
                switch (type){
                    case 1:
                        //刷新数据 去付款成功界面
                        pager = 1;
//                        lv_orders.removeFooterView(footerview);
                        orders.clear();
                        request_order(ALL);
                        startActivity(new Intent(OrdersActivity.this, PaySuccessActivity.class));
                        System.out.println("去付款成功通知界面");
                        break;
                    case 2://确认收货了 刷新数据
                        pager = 1;
//                        lv_orders.removeFooterView(footerview);
                        orders.clear();
                        request_order(ALL);
                        break;
                    case 3://删除订单了 或者取消订单了 刷新数据
                        pager = 1;
//                        lv_orders.removeFooterView(footerview);
                        orders.clear();
                        request_order(ALL);
                        break;

                    default:
                        break;
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        pager = 1;
//        lv_orders.removeFooterView(footerview);
        orders.clear();
        if (orderListAdapter != null){
            orderListAdapter.notifyDataSetChanged();
        }
        request_order(ALL);
    }
}
