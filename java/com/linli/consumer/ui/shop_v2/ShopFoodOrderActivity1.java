package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopFoodOrderAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.AddressInfoBean;
import com.linli.consumer.bean.FoodExInfoBean;
import com.linli.consumer.bean.FoodOrderPayBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.HdFoodOrder;
import com.linli.consumer.bean.ShopExtraInfoBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.main.PaySuccessActivity;
import com.linli.consumer.widget.BackLayout;
import com.linli.consumer.widget.ShopFoodOrderTitleWidget;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/9.
 * 包含不同的逻辑，不要删除
 */
@Deprecated
public class ShopFoodOrderActivity1 extends BaseActivity {

    private BackLayout backLayout;
    private ShopFoodOrderTitleWidget titleWidget;

    //left
    private LinearLayout leftContainerLL;       //左面整体布局
    private TextView peopleTv;                  //联系人姓名
    private TextView phoneTV;                   //联系人电话
    private TextView locationTv;                //联系人地址
    private LinearLayout haveAddressLl;          //有联系人信息
    private RelativeLayout noAddressRl;         //无联系人信息
    private LinearLayout bywayContainerLl;      //预定部分部件

    //right
    private LinearLayout rightContainerLL;      //右面的总布局
    private EditText rNameEd;                    //联系人
    private EditText rPhoneEd;                  //电话
    private TextView rYearTv;                   //日期
    private TextView rSecondTv;                 //小时
    private ImageView rCalenderIm;              //日历图片点击
    private EditText rPersonNumEd;              //人数
    private ImageView rRoomIm;                  //是否包间


    //common
    private RecyclerView recyclerView;          //购物车的商品
    private ShopFoodOrderAdapter adapter;       //购物车的适配器
    private List<Object> dataList = new ArrayList<>();

    private RadioGroup lineRg;                  //线上线下
    private RadioGroup bywayRg;                 //自取配送
    private TextView sendTimeTv;                    //配送时长
    private TextView sendPriceTv;                   //配送费
    private TextView packagePriceTv;                //打包费
    private TextView totalPriceTv;                   //总价
    private RelativeLayout commitRl;                //递交


    private int typeCC = 0 ;     //进入的类别，1:堂吃  2:外卖
    private boolean isRoom = false ;    //是否包间

    private HdFoodOrder bean = new HdFoodOrder();       //上传的实体

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日-hh时mm分");
    private Date nowDate = new Date();

    private int type = 0 ; //美食为1，商城为2
    private long goodsId = 0;   //如果传入goodsId 为0，就从数据库中查这个店铺的所有商品（店铺内购物车跳转来的）
                                //如果传入goodsId 不为0，就从数据库查这个商品(立即购买来的) TODO
    private long shopId = 0;    //店铺id

    private User user = AppContext.getInstance().getUser();

    private boolean hasDefaultAddress = false;      //是否获取到了默认地址
    private boolean hasShopInfo = false;            //是否获取到了店铺附加信息
    private boolean hasGoodsInfo = false;           //是否获取到了商品信息
    private long orderId;//


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_food_order;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();

        goodsId = intent.getLongExtra("goodsId",1);
        shopId = intent.getLongExtra("shopId",1);
        type = intent.getIntExtra("Sort",1);

        backLayout = findView(R.id.shop_order_back);
        titleWidget = findView(R.id.shop_order_title);

        //左边布局
        leftContainerLL = findView(R.id.shop_order_left_ll);
        peopleTv = findView(R.id.shop_order_main_people_tv);
        phoneTV = findView(R.id.shop_order_main_phone_tv);
        locationTv = findView(R.id.shop_order_main_location_tv);
        haveAddressLl = findViewClick(R.id.shop_order_main_haveaddress_ll);
        noAddressRl = findViewClick(R.id.shop_order_main_noaddress_rl);
        bywayContainerLl = findView(R.id.shop_order_main_left_byway_container);
        lineRg = findView(R.id.shop_order_main_line_rg);
        bywayRg = findView(R.id.shop_order_main_byway_rg);
        sendTimeTv = findView(R.id.shop_order_main_sendtime_tv);
        sendPriceTv = findView(R.id.shop_order_main_sendprice_tv);
        packagePriceTv = findView(R.id.shop_order_main_packageprice_tv);

        //右边布局
        rightContainerLL = findView(R.id.shop_order_main_right_ll);
        rNameEd = findView(R.id.shop_order_main_right_name_ed);
        rPhoneEd = findView(R.id.shop_order_main_right_phone_ed);
        rYearTv = findView(R.id.shop_order_main_right_year_tv);
        rSecondTv = findView(R.id.shop_order_main_right_second_tv);
        rCalenderIm = findViewClick(R.id.shop_order_main_right_calender_im);
        rPersonNumEd = findView(R.id.shop_order_main_right_personnum_ed);
        rRoomIm = findViewClick(R.id.shop_order_main_right_room_im);

        //common
        recyclerView = findView(R.id.shop_order_main_rc);
        totalPriceTv = findView(R.id.shop_order_main_totalprice_tv);
        commitRl = findViewClick(R.id.shop_order_main_commit_rl);

        //adapter = new ShopFoodOrderAdapter(this,dataList(GoodsBean),type);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //right初始化时间显示
        String time = dateFormat.format(nowDate);
        String[] timeS = time.split("-");

        rYearTv.setText(timeS[0]);
        rSecondTv.setText(timeS[1]);


        initCommonClick();
        dbUtil =  DBUtil.getInstance(this                                                                                                                                                                                                                                                    );
    }

    private List<GoodsBean> list = new ArrayList<>();
    private GoodsBean goodsBean = new GoodsBean();
    private DBUtil dbUtil ;

    /**
     * 初始化3组数据:
     *
     * 查询店铺信息   配送时长，配送费，打包费
     * 查询数据库商品的信息
     * 查询默认地址
     * */
    @Override
    protected void initData() {

        /**查店铺附加信息，打包费，配送费，配送时长*/
        switch (type){
            case FOOD_MAIN:
                FoodNet.foodExInfo(shopId, new HandleSuccess<FoodExInfoBean>() {
                    @Override
                    public void success(FoodExInfoBean foodExInfoBean) {
                        if(foodExInfoBean.getData() != null){
                            switch (foodExInfoBean.getData().getOpemethod()){
                                case "0":       //堂吃
                                    titleWidget.setVisibility(View.GONE);
                                    bywayContainerLl.setVisibility(View.GONE);
                                    rightContainerLL.setVisibility(View.GONE);
                                    leftContainerLL.setVisibility(View.VISIBLE);
                                    bean.setAppointTime(nowDate);   //设置时间
                                    bean.setAppointRoom(0); //设置是否包间
                                    bean.setPaymentId(1);    //设置默认支付方式
                                    bean.setOrderType(1);   //类型
                                    typeCC = 1;
                                    hasShopInfo = true;
                                    totalCount(goodsSum);
                                    break;
                                case "1":       //外卖
                                    titleWidget.setVisibility(View.GONE);
                                    rightContainerLL.setVisibility(View.VISIBLE);
                                    leftContainerLL.setVisibility(View.GONE);
                                    bean.setPaymentId(1);   //设置默认支付方法
                                    //bean.setByway(1);       //设置默认获取方式
                                    bean.setOrderType(2);   //类型
                                    typeCC = 2;

                                    //填充视图
                                    sendTimeTv.setText(foodExInfoBean.getData().getOpelimittime());
                                    sendPriceTv.setText("￥"+foodExInfoBean.getData().getOpesendcost());;
                                    packagePriceTv.setText("￥"+foodExInfoBean.getData().getOpepackagecost());
                                    //填充数据
                                    bean.setPackageCost(foodExInfoBean.getData().getOpepackagecost());
                                    bean.setLogisticsAmount(foodExInfoBean.getData().getOpesendcost());
                                    packSum = foodExInfoBean.getData().getOpesendcost()+
                                            foodExInfoBean.getData().getOpepackagecost();
                                    hasShopInfo = true;
                                    totalCount(goodsSum+packSum);
                                    break;
                                case "2":       //都有
                                    bean.setPaymentId(1);    //设置默认支付方式
                                    //bean.setByway(1);       //设置默认获取方式
                                    bean.setAppointTime(nowDate);   //设置时间
                                    bean.setAppointRoom(0); //设置是否包间
                                    bean.setOrderType(2);   //类型
                                    typeCC = 2;

                                    //填充视图
                                    sendTimeTv.setText(foodExInfoBean.getData().getOpelimittime());
                                    sendPriceTv.setText("￥"+foodExInfoBean.getData().getOpesendcost());;
                                    packagePriceTv.setText("￥"+foodExInfoBean.getData().getOpepackagecost());
                                    //填充数据
                                    bean.setPackageCost(foodExInfoBean.getData().getOpepackagecost());
                                    bean.setLogisticsAmount(foodExInfoBean.getData().getOpesendcost());
                                    packSum = foodExInfoBean.getData().getOpesendcost()+
                                            foodExInfoBean.getData().getOpepackagecost();
                                    hasShopInfo = true;
                                    totalCount(goodsSum+packSum);
                                    break;
                            }

                        }
                    }
                });

                break;
            case SHOP_MAIN:
                ShopNet.shopExInfo(shopId, new HandleSuccess<ShopExtraInfoBean>() {
                    @Override
                    public void success(ShopExtraInfoBean shopExtraInfoBean) {
                        titleWidget.setVisibility(View.GONE);
                        rightContainerLL.setVisibility(View.VISIBLE);
                        leftContainerLL.setVisibility(View.GONE);
                        bean.setPaymentId(1);   //设置默认支付方法
                        //bean.setByway(1);       //设置默认获取方式
                        bean.setOrderType(2);   //类型
                        typeCC = 2;

                        //填充视图
                        sendTimeTv.setText(shopExtraInfoBean.getData().getSendtime()+"");
                        sendPriceTv.setText("￥"+shopExtraInfoBean.getData().getSendcost());;
                        packagePriceTv.setText("￥"+shopExtraInfoBean.getData().getPacking());
                        //填充数据
                        bean.setPackageCost(shopExtraInfoBean.getData().getPacking());
                        bean.setLogisticsAmount(shopExtraInfoBean.getData().getSendcost());
                        packSum = shopExtraInfoBean.getData().getSendcost()+
                                shopExtraInfoBean.getData().getPacking();
                        hasShopInfo = true;
                        totalCount(goodsSum+packSum);
                    }
                });
                break;
        }


        /**数据库查商品，再联网查*/
        if(goodsId == 0){       //通过 店铺id 查商品
            list = dbUtil.queryByShopId(shopId);
            if(list != null && list.size() != 0){
                if(type == FOOD_MAIN){
                    /*FoodNet.queryFoodDetailByIds(dbUtil.queryFoodsDetailFromSer(list), new HandleSuccess<FoodListBean>() {
                        @Override
                        public void success(FoodListBean foodListBean) {
                            dbUtil.compareDataToDB(foodListBean.getData(),1,shopId);
                            for(int i = 0 ;i < foodListBean.getData().size();i ++){
                                dataList.add(foodListBean.getData().get(i));
                            }
                            adapter.notifyDataSetChanged();
                            for(int i = 0 ;i < dataList.size();i++){
                                num = num + ((FoodListBean.DataBean)dataList.get(i)).getNumber();
                                goodsSum = goodsSum + ((FoodListBean.DataBean)dataList.get(i)).getNumber()*((FoodListBean.DataBean)dataList.get(i)).getPrice();
                            }
                            bean.setNumtotal(num);
                            hasGoodsInfo = true;
                            totalCount(goodsSum+packSum);
                        }

                    });*/
                    dataList.addAll(dbUtil.queryByShopId(shopId));
                    adapter.notifyDataSetChanged();
                    for(int i = 0 ;i < dataList.size();i++){
                        num = num + ((GoodsBean)dataList.get(i)).getNumber();
                        goodsSum = goodsSum + ((GoodsBean)dataList.get(i)).getNumber()*((GoodsBean)dataList.get(i)).getPrice();
                    }
                    bean.setNumtotal(num);
                    hasGoodsInfo = true;
                    totalCount(goodsSum+packSum);

                } else if(type == SHOP_MAIN){

                }

            }
        } else {            //通过 商品id 查商品
            goodsBean = dbUtil.queryByGoodId(goodsId,0);
            if(goodsBean != null){
                if(type == FOOD_MAIN){
                    /*FoodNet.queryFoodDetailByIds(String.valueOf(goodsBean.getGoodId()), new HandleSuccess<FoodListBean>() {
                        @Override
                        public void success(FoodListBean foodListBean) {
                            dbUtil.compareDataToDB(foodListBean.getData(),1,shopId);
                            for(int i = 0 ;i < foodListBean.getData().size();i ++){
                                dataList.add(foodListBean.getData().get(i));
                            }
                            adapter.notifyDataSetChanged();
                            for(int i = 0 ;i < dataList.size();i++){
                                num = num + ((FoodListBean.DataBean)dataList.get(i)).getNumber();
                                goodsSum = goodsSum + ((FoodListBean.DataBean)dataList.get(i)).getNumber()*((FoodListBean.DataBean)dataList.get(i)).getPrice();
                            }
                            bean.setNumtotal(num);
                            hasGoodsInfo = true;
                            totalCount(goodsSum+packSum);
                        }
                    });*/
                    /*FoodNet.foodListOfSHop(shopId, 0, 1, 1000, new HandleSuccess<FoodListBean>() {
                        @Override
                        public void success(FoodListBean foodListBean) {
                            dbUtil.compareDataToDB(foodListBean.getData(),0,shopId);
                            list = dbUtil.queryByShopId(shopId);

                            for(int i = 0 ;i < list.size();i ++){
                                dataList.add(list.get(i));
                            }
                            adapter.notifyDataSetChanged();
                            for(int i = 0 ;i < dataList.size();i++){
                                num = num + ((GoodsBean)dataList.get(i)).getNumber();
                                goodsSum = goodsSum + ((GoodsBean)dataList.get(i)).getNumber()*((GoodsBean)dataList.get(i)).getPrice();
                            }
                            bean.setNumtotal(num);
                            hasGoodsInfo = true;
                            totalCount(goodsSum+packSum);
                        }
                    });*/
                } else if(type == SHOP_MAIN){

                }

            }
        }
        //查询是否有默认地址
        ShopNet.changeAddress(user.getId(), new HandleSuccess<AddressInfoBean>() {
            @Override
            public void success(AddressInfoBean addressInfoBean) {
                if(addressInfoBean.getData() != null){
                    peopleTv.setText(addressInfoBean.getData().getName());
                    phoneTV.setText(addressInfoBean.getData().getPhone());
                    locationTv.setText(addressInfoBean.getData().getAddress());
                    bean.setAddressId(addressInfoBean.getData().getId());
                    hasDefaultAddress = true;
                }else {
                    haveAddressLl.setVisibility(View.GONE);
                    noAddressRl.setVisibility(View.VISIBLE);
                }
            }
        });



        //TODO 从服务器，数据库读取数据，填充列表
        //默认收货地址//TODO 收货地址改
        //本地数据联网查询  根据type
        //根据店铺id查询经营设置  ,  店铺附加信息查询
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.shop_order_main_right_calender_im:      //选择时间
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setIndicatorColor(Color.parseColor("#f98910"))
                        .setMinDate(nowDate)
                        .setIs24HourTime(true)
                        .setInitialDate(new Date())
                        .build()
                        .show();
                break;
            case R.id.shop_order_main_right_room_im:        //是否包间的按钮
                roomCrash();
                break;
            case R.id.shop_order_main_commit_rl:       //递交按钮
                upData();
                break;
            case R.id.shop_order_main_haveaddress_ll:       //有联系人信息
                UIHelper.togoAddressManageActivity(this);
                break;
            case R.id.shop_order_main_noaddress_rl:         //没有联系人信息
                UIHelper.togoAddressManageActivity(this);
                break;
        }
    }

    /**
     * 对checkbox的处理，数据保存在bean里
     * */
    private void initCommonClick(){
        //设置标题
        backLayout.setTitle("确认订单");
        //设置顶部按钮
        titleWidget.setLeftClickListener(new ShopFoodOrderTitleWidget.OnLeftClickListener() {
            @Override
            public void onLeftClick() {
                leftContainerLL.setVisibility(View.VISIBLE);
                rightContainerLL.setVisibility(View.GONE);
                typeCC = 2;     //外卖
                bean.setOrderType(2);
                totalCount(goodsSum+packSum);
                bywayContainerLl.setVisibility(View.VISIBLE);
            }
        });
        titleWidget.setRightClickListener(new ShopFoodOrderTitleWidget.OnRightClickListener() {
            @Override
            public void onRightClick() {
                leftContainerLL.setVisibility(View.GONE);
                rightContainerLL.setVisibility(View.VISIBLE);
                bywayContainerLl.setVisibility(View.GONE);
                typeCC = 1;     //堂吃
                totalCount(goodsSum);
                bean.setOrderType(1);
            }
        });

        //线上线下选择
        lineRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shop_order_main_online_rb:
                        bean.setPaymentId(1);
                        break;
                    case R.id.shop_order_main_offline_rb:
                        bean.setPaymentId(2);
                        break;
                }
            }
        });
        //自取配送选择
        bywayRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shop_order_main_byself_rb:
                        //bean.setByway(2);
                        break;
                    case R.id.shop_order_main_byother_rb:
                        //bean.setByway(1);
                        break;
                }
            }
        });

    }

    /**
     *      时间 由listener组装
     * */

    private void upData(){

        boolean canYou = false;     //common选项判断上传
        Map<String,Object> map = new HashMap<>();   //要上传的map

        if(typeCC == 1){    //堂吃
            if(!TextUtils.isEmpty(rNameEd.getText().toString())){
                bean.setPurchaser(rNameEd.getText().toString());
                if(!TextUtils.isEmpty(rPhoneEd.getText().toString())){
                    bean.setPurchaserPhone(rPhoneEd.getText().toString());
                    if(!TextUtils.isEmpty(rPersonNumEd.getText().toString())){
                        bean.setAppointPerson(Integer.valueOf(rPersonNumEd.getText().toString()));
                        map.put("hdFoodOrder.appointPerson",bean.getAppointPerson());
                        map.put("hdFoodOrder.appointRoom",bean.getAppointRoom());
                        map.put("hdFoodOrder.appointTime",bean.getAppointTime());
                        map.put("hdFoodOrder.purchaser",bean.getPurchaser());
                        map.put("hdFoodOrder.purchaserPhone",bean.getPurchaserPhone());
                        map.put("hdFoodOrder.orderAmount",goodsSum);
                        canYou = true;
                    }else {
                        Toast.makeText(this,"请输入人数",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"请输入电话号",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"请输入联系人",Toast.LENGTH_SHORT).show();
            }
        } else if(typeCC == 2){     //外卖

            if(TextUtils.isEmpty(String.valueOf(bean.getAddressId()))){
                Toast.makeText(this,"请选择收货地址",Toast.LENGTH_SHORT).show();
            } else {
                bean.setMemberId(user.getId());
                map.put("hdFoodOrder.addressId",bean.getAddressId());
                map.put("hdFoodOrder.logisticsAmount",bean.getLogisticsAmount());        //送餐费
                map.put("hdFoodOrder.packageCost",bean.getPackageCost());            //打包费
                map.put("hdFoodOrder.orderAmount",goodsSum+packSum);
                canYou = true;
            }

        }
        if(canYou){         //common
            List<HdFoodOrder.HdFoodOrderGoodsList> temlist = new ArrayList<>();
            if(goodsId == 0){

                for(int i = 0;i<list.size();i++){
                    HdFoodOrder.HdFoodOrderGoodsList aa = new HdFoodOrder.HdFoodOrderGoodsList();
                    aa.setGoodsId(list.get(i).getGoodId());
                    aa.setGoodsNum(list.get(i).getNumber());
                    aa.setGoodsPrice(BigDecimal.valueOf(list.get(i).getPrice()));
                    temlist.add(aa);
                }
            }else {
                    HdFoodOrder.HdFoodOrderGoodsList aa = new HdFoodOrder.HdFoodOrderGoodsList();
                    aa.setGoodsId(goodsBean.getGoodId());
                    aa.setGoodsNum(goodsBean.getNumber());
                    aa.setGoodsPrice(BigDecimal.valueOf(goodsBean.getPrice()));
                    temlist.add(aa);
            }

            String data = JSON.toJSONString(temlist);

            map.put("hdFoodOrder.memberId",bean.getMemberId());
            map.put("hdFoodOrder.paymentId",bean.getPaymentId());
            //map.put("hdFoodOrder.orderAmount",bean.getOrderAmount());
            map.put("hdFoodOrder.storeId",shopId);
            //map.put("HdFoodOrder.areaCode",bean.getAreaCode());
            map.put("hdFoodOrder.orderType",bean.getOrderType());
            map.put("hdFoodOrder.numtotal",bean.getNumtotal());
            //map.put("hdFoodOrder.byway",bean.getByway());

            //调用美食订单递交
            if(hasGoodsInfo && hasShopInfo){
                if(type == FOOD_MAIN){
                    FoodNet.foodOrder(bean.getAddressId(), map, data,"", new HandleSuccess<FoodOrderPayBean>() {
                        @Override
                        public void success(FoodOrderPayBean foodOrderPayBean) {
                            orderId = foodOrderPayBean.getData().getId();
                            UIHelper.togoOnLinePayActivity(ShopFoodOrderActivity1.this,
                                    orderId
                                    ,foodOrderPayBean.getData().getOrderAmount()
                                    ,foodOrderPayBean.getData().getOrderSn()+"",foodOrderPayBean.getData().getId());
                        }
                    });
                } else if(type == SHOP_MAIN){

                }
            }
        }



        //TODO 这里进行联网处理，将数据库中的数据递交到服务器
    }

    /**
     * 时间选择器的监听器
     * 如果选择了时间，就将时间赋给bean
     * 如果没有选择，就将初始事件(当前时间)赋给bean
     * */
    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            String now = dateFormat.format(date);
            String[] nowS = now.split("-");
            rYearTv.setText(nowS[0]);
            rSecondTv.setText(nowS[1]);
            bean.setAppointTime(date);

        }
        @Override
        public void onDateTimeCancel() {
            bean.setAppointTime(nowDate);

        }
    };


    /**
     * 接受从更改默认地址获取的结果
     *
     * 填充部分数据，主要是addressId
     *
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if (resultCode == 1002) {
                bean.setAddressId(data.getLongExtra("id", 1));
                bean.setName(data.getStringExtra("addressee"));
                bean.setPhone(data.getStringExtra("phone"));
                bean.setAddressStr(data.getStringExtra("address"));
                hasDefaultAddress = true;
                upDateView();
            }
        }else if (resultCode == 1009){
            boolean success = data.getBooleanExtra("success",false);
            if (success){
                request_set_payed_order();
            }
        }
    }
    private void request_set_payed_order() {
        //已经付款成功 返回的刚刚成功的数据 可以去某个界面显示一下付款成功
        startActivity(new Intent(ShopFoodOrderActivity1.this, PaySuccessActivity.class));
    }
    /**
     * 计算总价和总数
     * */
    private void totalCount(double price){
        if(hasGoodsInfo && hasGoodsInfo){
            totalPriceTv.setText("￥"+price);
            dismiss();
        }
    }

    /**
     * 在从地址管理回来后更新视图
     * */
    private void upDateView(){
        haveAddressLl.setVisibility(View.VISIBLE);
        noAddressRl.setVisibility(View.GONE);
        peopleTv.setText(bean.getName());
        phoneTV.setText(bean.getPhone());
        locationTv.setText(bean.getAddressStr());
    }

    /**
     * 是否包间
     * */
    private void roomCrash(){
        if(isRoom){     //不要包间
            rRoomIm.setImageResource(R.mipmap.ic_shop_order_main_switch_off);
            isRoom = false;
            bean.setAppointRoom(0);
        } else {        //要包间
            rRoomIm.setImageResource(R.mipmap.ic_shop_order_main_switch_on);
            isRoom = true;
            bean.setAppointRoom(1);

        }
    }


    private double goodsSum = 0;    //商品总价
    private double packSum = 0; //打包费+送餐费
    private int num = 0;        //商品总数

}
