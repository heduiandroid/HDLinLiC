package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.BuyedProdsAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.IfEvaluate;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.domain.Order;
import com.linli.consumer.domain.Product;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.myview.MyListView;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class OrderDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private LinearLayout ll_transport_way;
    private TextView tv_trans_way;
    private TextView tv_text_coupontype;
    private TextView tv_order_status,tv_connect_shoper,tv_paymethod,tv_freight,tv_extraFee,tv_youhui,tv_prodsprice;//配送费一类的东西  优惠价格  商品总价不包含运费优惠计算
    private Order order;
    private TextView tv_receiver;//收货人
    private TextView tv_phone;//收货人电话
    private TextView tv_address;//receiver address
    private MyListView mlv_orderprods;//订单商品列表
    private TextView tv_totalprice;//订单总价
    private TextView tv_orderno;//订单号
    private TextView tv_shopname;//商家名称
    private TextView tv_ordertime;//下单时间
    private TextView tv_finnal_price_tag;//实付款文字
    private Button btn_evaluate;//点赞评价
    private Button btn_buyagain;//再次购买
    private Button btn_cancel;//取消订单
    private Button btn_delete;//删除订单
    private Button btn_topay;//去付款按钮
    private Button btn_receive;//收货按钮
    private Button btn_waitforsend;//等待商家发货

    private Button btn_returnmoney;//申请退款

    private Long orderId;//待用订单id
    private ArrayList<Product> products  =new ArrayList<Product>();
    private BuyedProdsAdapter adapter;
    private Integer status;//0待付款  1待发货 2待收货  3已完成 4已关闭
    private int category;//1订餐  2外卖   3商城 4服务
    private LinearLayout ll_packageFee;
    private TextView tv_packageFee;
    private TextView tv_nameprodsprice;
    private Boolean isEvalued = false;
    private TextView tv_receiver_tag;
    private boolean topay;//是否是去支付
    private String shopPhone = null;//店铺联系电话
    private String companyIdStr;//店铺用户id

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        order = (Order) getIntent().getSerializableExtra("order");
        category = getIntent().getIntExtra("cate",0);
        init();
        getUsefullDatas();
//        requset_order_detail();//如果上个页面订单列表字段齐全，就不用这个接口。如果用这个接口 接口返回成功后在执行一系列setViewsText()
        setViewsText();
        setProdsList();
        getIfToPay();
        getIfToReceive();
    }



    @Override
    protected void initData() {
        //先查店铺信息 确定订单的店铺类型以及联系电话等信息
        switch (order.getType()){
            case 1:
            case 2://查餐饮店铺信息
                showDialog();
                IntrestBuyNet.findFoodShopByshopId(order.getShopId(), new HandleSuccess<StoreInfo>() {
                    @Override
                    public void success(StoreInfo s) {
                        dismiss();
                        if (s.getStatus() == 1){
                            if (s.getData().getMobilePhone() != null) {
                                shopPhone = s.getData().getMobilePhone();
                            }else {
                                shopPhone = s.getData().getPhone();
                            }
                            companyIdStr = s.getData().getCompanyMemberId()+"";
                            if (s.getData().getType() != null ){
                                if (s.getData().getType() == 5 || s.getData().getType() == 3) {
                                    ll_transport_way.setVisibility(View.VISIBLE);
                                    tv_text_coupontype.setText(getResources().getString(R.string.redbags_tag_short));
                                }
                            }
                        }else {
                            Toast.makeText(OrderDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 3://查商城店铺信息
                showDialog();
                IntrestBuyNet.findShopByshopId(order.getShopId(), new HandleSuccess<MallShopInfo>() {
                    @Override
                    public void success(MallShopInfo s) {
                        dismiss();
                        if (s.getStatus() == 1){
                            if (s.getData().getMobilePhone() != null) {
                                shopPhone = s.getData().getMobilePhone();
                            }else {
                                shopPhone = s.getData().getPhone();
                            }
                            companyIdStr = s.getData().getCompanyMemberId()+"";
                            if (s.getData().getType() != null){
                                if (s.getData().getType() == 5 || s.getData().getType() == 3) {
                                    ll_transport_way.setVisibility(View.VISIBLE);
                                    tv_text_coupontype.setText(getResources().getString(R.string.redbags_tag_short));
                                }
                            }
                        }else {
                            Toast.makeText(OrderDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 4://查服务店铺信息
                btn_receive.setText("完成服务");
                showDialog();
                FoodNet.findServiceStoresInfos(order.getShopId(), new HandleSuccess<ServiceStoreBean>() {
                    @Override
                    public void success(ServiceStoreBean s) {
                        dismiss();
                        if (s.getStatus() == 1){
                            if (s.getData().getMobilePhone() != null) {
                                shopPhone = s.getData().getMobilePhone();
                            }else {
                                shopPhone = s.getData().getPhone();
                            }
                            companyIdStr = s.getData().getCompanyMemberId()+"";
//                            if (s.getData().getType() != null){
//                                if (s.getData().getType() == 5 || s.getData().getType() == 3) {
//                                    ll_transport_way.setVisibility(View.VISIBLE);
//                                    tv_text_coupontype.setText(getResources().getString(R.string.redbags_tag_short));
//                                }
//                            }
                        }else {
                            Toast.makeText(OrderDetailActivity.this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            default:
                break;
        }
        dismiss();
    }

    private void requset_order_detail() {
        IntrestBuyNet.orderDetail(category, orderId, new HandleSuccess<PhoneCode>() {
            @Override
            public void success(PhoneCode phoneCode) {


                setViewsText();//用这个就不用oncreate里的这个方法
            }
        });
    }

    private void getIfToPay() {
        topay = getIntent().getBooleanExtra("topay",false);
        if (topay){
            toPay();
        }
    }
    private void getIfToReceive() {
        boolean toreceive = getIntent().getBooleanExtra("toreceive",false);
        if (toreceive){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("请确认已收到货");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //修改订单状态
                    switch (order.getType()){
                        case 1:
                        case 2:
                            request_update_foodstatus(3);
                            break;
                        case 3:
                            request_update_mallstatus(3);
                            break;
                        case 4:
                            request_update_servicestatus(3);
                            break;
                        default:
                            break;
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create();
            builder.show();
        }
    }
    private void setProdsList() {
        adapter = new BuyedProdsAdapter(products,this);
        mlv_orderprods.setAdapter(adapter);
    }

    private void getUsefullDatas() {
        orderId = Long.valueOf(order.getOrderNum());
        status = order.getStatus();
        products = order.getProList();
        //底部操作按钮先全部隐藏
        btn_evaluate.setVisibility(View.GONE);
        btn_receive.setVisibility(View.GONE);
        btn_topay.setVisibility(View.GONE);
        btn_delete.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);
        btn_waitforsend.setVisibility(View.GONE);
        btn_buyagain.setVisibility(View.GONE);
        btn_returnmoney.setVisibility(View.GONE);

        switch (status){//0待付款  1待发货 2待收货  3已完成 4已关闭
            case 0:
                tv_order_status.setText("待付款");
                btn_topay.setVisibility(View.VISIBLE);//可以去付款
                btn_cancel.setVisibility(View.VISIBLE);//取消
                break;
            case 1:
                tv_order_status.setText("待提供");
                btn_returnmoney.setVisibility(View.VISIBLE);
                btn_returnmoney.setText("取消订单");//取消并退款
                btn_waitforsend.setVisibility(View.VISIBLE);//可以催单
                break;
            case 2:
                tv_order_status.setText("待接收");
                btn_returnmoney.setVisibility(View.VISIBLE);
                btn_returnmoney.setText("退款");
                btn_receive.setVisibility(View.VISIBLE);//可以收货
                if (order.getType()==4){
                    tv_order_status.setText("待用户确认");
                }
                break;
            case 3://已经收货，待评价（交易完成）/////////////还可以删除订单？
                tv_order_status.setText("已完成");
                btn_returnmoney.setVisibility(View.VISIBLE);
                btn_returnmoney.setText("退换");
                if (order.getType()==4){
                    btn_evaluate.setVisibility(View.GONE);
                }else {
                    btn_evaluate.setVisibility(View.VISIBLE);
                }
                btn_buyagain.setVisibility(View.VISIBLE);//可以再次购买

                break;
            case 4:
                tv_order_status.setText("已取消");
                btn_delete.setVisibility(View.VISIBLE);//可以删除
                break;
            default:
                break;
        }
    }

    private void setViewsText() {
        if (order.getPayKind() == 2){
            btn_returnmoney.setVisibility(View.GONE);
            tv_paymethod.setText("货到付款");
            if (order.getOrderType() != null){
                if (order.getOrderType() == 3)
                    tv_paymethod.setText("餐后付款");
            }
        }else {
            tv_paymethod.setText("在线支付");
        }
        tv_shopname.setText(order.getShopName());
        tv_orderno.setText(order.getOrderNum());
        tv_ordertime.setText(order.getCreateTime());
        tv_totalprice.setText("￥" + order.getPayPrice());
        if (status == 1 || status == 2){
            if (order.getPayKind() == 2){
                tv_totalprice.setText("￥" + order.getPayPrice()+"(现金付)");
            }
        }
        tv_receiver.setText(order.getRealName());
        tv_phone.setText(CommonUtil.secretPhone(order.getReceiverPhone()));
        if (order.getOrderType() != null && order.getOrderType() == 1){//预订的不显示地址 显示预订信息
            tv_receiver_tag.setText("预订信息：");
            String appointInfo = "时间：";
            appointInfo = appointInfo + order.getAppointTime() + "    "+order.getAppointPerson() + "位";
            if (order.getAppointRoom() == 0){//预约是否要求包间(0-否，1-是)
                appointInfo = appointInfo + "    未订包间";
            }else if (order.getAppointRoom() == 1){
                appointInfo = appointInfo + "    包间";
            }
            tv_address.setTextSize(14);
            tv_address.setText(appointInfo);
        }else if (order.getOrderType() != null && order.getOrderType() == 3){//堂吃（点餐）
            tv_receiver_tag.setText("点餐信息：");
            tv_receiver.setText("桌号/包间："+order.getRealName());
            tv_phone.setText("手机号："+CommonUtil.secretPhone(order.getReceiverPhone()));
            tv_address.setText("用餐人数："+order.getAppointPerson() + "位");
        }else {
            tv_address.setText(order.getReceiverAddress());
        }
        if (order.getType() == 3){//商城订单
            if (order.getByUserSelfIntoShop()){
                tv_address.setText("用户到店自取");
                tv_trans_way.setText("到店自取");
                tv_receiver.setText("");
                tv_phone.setText("");
            }
        }
        if (order.getOrderType() != null && order.getOrderType() == 2){
            if (order.getByUserSelfIntoShop()){
                tv_address.setText("用户到店自取");
                tv_trans_way.setText("到店自取");
                tv_receiver.setText("");
                tv_phone.setText("");
            }
        }
        request_shop_extendsFee();
    }

    private void request_shop_extendsFee() {
        switch (order.getType()){
            case 1:
            case 2:
                ll_packageFee.setVisibility(View.VISIBLE);//美食商品有打包费项
                tv_freight.setText("+￥" + order.getFreight());
                tv_prodsprice.setText("￥"+order.getGoodsAmount());
                BigDecimal packCost = new BigDecimal(0d);//打包费
                if (order.getOrderType() == 2){//如果是外卖考虑打包费 (1:点餐，2:外卖 3预订)
                    packCost = BigDecimal.valueOf(order.getPackageCost());
//                    for (int i = 0;i<order.getProList().size();i++){
//                        if (order.getProList().get(i).getIsPackageCost() == 1){//要打包费
//                            packCost = packCost.add((BigDecimal.valueOf(order.getPackageCost())).multiply(BigDecimal.valueOf(order.getProList().get(i).getCountInOrder())));
//                        }
//                    }
                }
                tv_packageFee.setText("+￥"+packCost.toString());
                break;
            case 3:
                ll_packageFee.setVisibility(View.GONE);//商城商品无打包费项
                tv_freight.setText("+￥" + order.getFreight());
                tv_prodsprice.setText("￥"+order.getGoodsAmount());
                break;
            case 4:
                ll_packageFee.setVisibility(View.GONE);//商城商品无打包费项
                tv_freight.setText("+￥0.00");
                tv_prodsprice.setText("￥"+order.getGoodsAmount());
                break;
        }
        if (order.getByUserSelfIntoShop()){
            tv_freight.setText("+￥0.00");
        }else {
            tv_freight.setText("+￥" + order.getFreight());
        }
        if (order.getFavourableFee() != null && order.getFavourableFee() != 0d){
            tv_youhui.setText("-￥" + order.getFavourableFee() );/////////////////////////////////////////////////////////
        }else {
            tv_youhui.setText("-￥" + "0.00");
        }
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("订单详情");
        ll_transport_way = findView(R.id.ll_transport_way);
        tv_trans_way = findView(R.id.tv_trans_way);
        tv_text_coupontype = findView(R.id.tv_text_coupontype);
        tv_order_status = findView(R.id.tv_order_status);
        tv_connect_shoper = findViewClick(R.id.tv_connect_shoper);
        tv_paymethod = findView(R.id.tv_paymethod);
        tv_prodsprice = findView(R.id.tv_prodsprice);
        tv_receiver = findView(R.id.tv_receiver);
        tv_phone = findView(R.id.tv_phone);
        tv_address =  findView(R.id.tv_address);
        tv_freight =findView(R.id.tv_freight);
        tv_extraFee = findView(R.id.tv_extraFee);
        mlv_orderprods = findView(R.id.mlv_orderprods);
        tv_totalprice = findView(R.id.tv_totalprice);
        tv_youhui = findView(R.id.tv_youhui);
        tv_orderno = findView(R.id.tv_orderno);
        tv_shopname = findView(R.id.tv_shopname);
        tv_ordertime = findView(R.id.tv_ordertime);
        btn_evaluate = findViewClick(R.id.btn_evaluate);
        btn_buyagain = findViewClick(R.id.btn_buyagain);
        btn_delete = findViewClick(R.id.btn_delete);
        btn_cancel = findViewClick(R.id.btn_cancel);
        btn_topay = findViewClick(R.id.btn_topay);
        btn_receive =  findViewClick(R.id.btn_receive);
        btn_waitforsend = findViewClick(R.id.btn_waitforsend);

        btn_returnmoney = findViewClick(R.id.btn_returnmoney);

        mlv_orderprods.setOnItemClickListener(this);
        ll_packageFee = findView(R.id.ll_packageFee);
        tv_packageFee = findView(R.id.tv_packageFee);
        tv_nameprodsprice = findView(R.id.tv_nameprodsprice);
        tv_receiver_tag = findView(R.id.tv_receiver_tag);
        tv_finnal_price_tag = findView(R.id.tv_finnal_price_tag);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_connect_shoper://打电话 还是聊天//////////////////////////////////////////
            case R.id.btn_waitforsend:
                UIHelper.connectSeller(OrderDetailActivity.this,shopPhone,companyIdStr,order.getShopName());
                break;
            case R.id.btn_cancel://未付款订单可取消订单 更改状态
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("请确认关闭交易");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (order.getType()){
                            case 1://更改订餐订单状态
                            case 2://更改外卖...
                                request_update_foodstatus(4);//设为关闭状态
                                break;
                            case 3://更改商城...
                                request_update_mallstatus(4);//设为关闭状态
                                break;
                            case 4://更改服务...
                                request_update_servicestatus(4);//设为关闭状态
                                break;
                            default:
                                break;
                        }

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
            case R.id.btn_delete:
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setTitle("提示");
                builder1.setMessage("请确认删除订单");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    request_delete_order();
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder1.create();
                builder1.show();
                break;
            case R.id.btn_receive:
                String alert = "请确认已收到货";
                if (order.getType() == 4){
                    alert = "请确认已完成服务";
                }
                AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setTitle("提示");
                builder2.setMessage(alert);
                builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //修改订单状态
                        switch (order.getType()){
                            case 1:
                            case 2:
                                request_update_foodstatus(3);
                                break;
                            case 3:
                                request_update_mallstatus(3);
                                break;
                            case 4:
                                request_update_servicestatus(3);
                                break;
                            default:
                                break;
                        }

                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder2.create();
                builder2.show();

                break;
            case R.id.btn_topay:
                toPay();
                break;
            case R.id.btn_buyagain:
                Integer type = null;
                switch (order.getType()){
                    case 1:
                    case 2:
                        type = FOOD_MAIN;
                        break;
                    case 3:
                        type = SHOP_MAIN;
                        break;
                    case 4:
                        type = SERVICE_MAIN;
                        break;
                    default:
                        break;
                }
                if (type != null){
                    UIHelper.togoShopDetailActivity(this,order.getShopId(),order.getShopName(),type);
                }
                break;
            case R.id.btn_evaluate://点赞评价
                if (isEvalued){
                    Toast.makeText(OrderDetailActivity.this,"已经评价过了",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(OrderDetailActivity.this,ReceiveAddPriseActivity.class);
                    intent.putExtra("order",order);
                    startActivityForResult(intent,1801);
                }
                break;
            case R.id.btn_returnmoney:
                UIHelper.togoReturnMoney(this,order);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user.getOrderNo() != null){
            orderId = user.getOrderNo();
            tv_orderno.setText(user.getOrderNo().toString());
        }
        request_if_evalueted();
        super.onResume();
    }

    private void request_if_evalueted() {//1商城 2美食
        int cate = 0;
        switch (order.getType()){
            case 1:
            case 2:
                cate = 2;
                break;
            case 3:
                cate = 1;
                break;
            case 4:
                cate = 3;
                break;
            default:
                break;
        }
        if (cate != 0){
            if (cate == 3){
                return;
            }
            IntrestBuyNet.findSupportList(cate, order.getOrderNum(), new HandleSuccess<IfEvaluate>() {
                @Override
                public void success(IfEvaluate s) {
                    if (s.getStatus() == 1){
                        if (s.getData()!=0){
                            isEvalued = true;
                            btn_evaluate.setText("已评价");
                        }else {
                            isEvalued = false;
                        }
                    }
                }
            });
        }else {
            Toast.makeText(OrderDetailActivity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
        }
    }

    private void request_update_servicestatus(final int status) {
        IntrestBuyNet.updateServiceOrderStatus(order.getId(), status, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    Intent intent = new Intent();
                    intent.putExtra("type",2);
                    if (status == 4){//取消订单成功
                        Toast.makeText(OrderDetailActivity.this,"订单已取消",Toast.LENGTH_SHORT).show();
                    }else if (status == 3){//收货成功
                        Toast.makeText(OrderDetailActivity.this,"确认收货成功",Toast.LENGTH_SHORT).show();
                    }
                    setResult(1800, intent);
                    OrderDetailActivity.this.finish();
                }else {
                    Toast.makeText(OrderDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void toPay() {
        if (order.getCouponId() != null && order.getCouponId() == 0L && order.getFavourableFee() > 0d){
            IntrestBuyNet.findUserInfo(user.getId(), new HandleSuccess<Login>() {
                @Override
                public void success(Login s) {
//                    if (s.getData()!=null && s.getData().getRedpackageAccount() != null && s.getData().getRedpackageAccount() > 0d){
//                        if (order.getFavourableFee() > s.getData().getRedpackageAccount()){
//                            showToast("抱歉，当前红包余额不足");
//                        }else {
//                            UIHelper.togoOnLinePayActivity(OrderDetailActivity.this, orderId, order.getPayPrice(), order.getOrderNum(), order.getId());
//                        }
//                    }
                }
            });
        }else {
            UIHelper.togoOnLinePayActivity(OrderDetailActivity.this, orderId, order.getPayPrice(), order.getOrderNum(), order.getId());
        }
    }
    private void request_update_mallstatus(final int status) {
        IntrestBuyNet.updateMallOrderStatus(order.getId(), status,order.getPayKind(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    Intent intent = new Intent();
                    intent.putExtra("type",2);
                    if (status == 4){//取消订单成功
                        Toast.makeText(OrderDetailActivity.this,"订单已取消",Toast.LENGTH_SHORT).show();
                    }else if (status == 3){//收货成功
                        Toast.makeText(OrderDetailActivity.this,"确认收货成功",Toast.LENGTH_SHORT).show();
                    }
                    setResult(1800, intent);
                    OrderDetailActivity.this.finish();
                }else {
                    Toast.makeText(OrderDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_update_foodstatus(final int status) {
        IntrestBuyNet.updateFoodOrderStatus(order.getId(), status, order.getPayKind(),new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    Intent intent = new Intent();
                    intent.putExtra("type",2);
                    if (status == 4){//取消订单成功
                        Toast.makeText(OrderDetailActivity.this,"订单已取消",Toast.LENGTH_SHORT).show();
                    }else if (status == 3){//收货成功
                        Toast.makeText(OrderDetailActivity.this,"确认收货成功",Toast.LENGTH_SHORT).show();
                    }
                    setResult(1800, intent);
                    OrderDetailActivity.this.finish();
                }else {
                    Toast.makeText(OrderDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //删除订单
    private void request_delete_order() {
        IntrestBuyNet.deleteOrder(order.getType(), order.getId(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    Toast.makeText(OrderDetailActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("type",3);
                    setResult(1800,intent);
                    OrderDetailActivity.this.finish();
                }else {
                    Toast.makeText(OrderDetailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (resultCode == 1009){
                //将订单号设为已付款订单后finish
                boolean success = data.getBooleanExtra("success",false);
                if (success){
                    request_set_payed_order();
                }
            }else if (resultCode == 1801){
                Intent intent = new Intent();
                intent.putExtra("type",2);
                setResult(1800, intent);
                OrderDetailActivity.this.finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void request_set_payed_order() {
        Intent intent = new Intent();
        intent.putExtra("type",1);
        setResult(1800, intent);
        OrderDetailActivity.this.finish();
    }
}
