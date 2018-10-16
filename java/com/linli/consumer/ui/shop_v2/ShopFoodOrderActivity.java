package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.AddressInfoBean;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.bean.FoodExInfoBean;
import com.linli.consumer.bean.FoodOrderPayBean;
import com.linli.consumer.bean.FoodShopInfoBean;
import com.linli.consumer.bean.GoodDetailUploadBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.bean.HdFoodOrder;
import com.linli.consumer.bean.HdMallOrder;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.MallOrderGoods;
import com.linli.consumer.bean.ShopExtraInfoBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.main.PaySuccessActivity;
import com.linli.consumer.ui.shop_v2.shopdetail.GoodsDetailActivity;
import com.linli.consumer.utils.CommonUtil;
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
 */

public class ShopFoodOrderActivity extends BaseActivity {

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
    private TextView contactTv;                 //联系人    预定：显示联系人   堂吃：显示桌号


    //common
    private RecyclerView recyclerView;          //购物车的商品
    private ShopFoodOrderAdapter adapter;       //购物车的适配器
    private List<GoodsBean> dataList = new ArrayList<>();

    private RadioGroup lineRg;                  //线上线下
    private RadioGroup bywayRg;                 //自取配送
    private TextView sendTimeTv;                    //配送时长
    private TextView sendPriceTv;                   //配送费
    private TextView packagePriceTv;                //打包费
    private TextView packageNameTv;     //提示打包费
    private TextView totalPriceTv;                   //总价
    private RelativeLayout commitRl;                //递交的按钮
    private ImageView commitIm;     //递交的背景颜色
    private TextView commitTv;      //递交的文字
    private TextView tv_commit_alert;
    private TextView tv_order_detail;
    private LinearLayout containLl;     //总container
    private LinearLayout ll_order_phone;
    private TextView tv_address_alert;
    //关于红包的views
    private LinearLayout ll_order_coupons_redbag;//点击选择红包
    private TextView tv_coupon_name_redbag;//显示当前可用红包

    private int typeCC = 1 ;     //进入的类别，1:堂吃  2:外卖
    private boolean isRoom = false ;    //是否包间

    private HdFoodOrder foodBean = new HdFoodOrder();       //上传美食的实体
    private HdMallOrder shopBean = new HdMallOrder();   //上传商城的实体

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
    private SimpleDateFormat dateFormatUp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Date nowDate = new Date();

    private int type = 0 ; //美食为1，商城为2
    private long goodsId = 0;   //如果传入goodsId 为0，就从数据库中查这个店铺的所有商品（店铺内购物车跳转来的）
    //如果传入goodsId 不为0，就从数据库查这个商品(立即购买来的) TODO
    private long shopId = 0;    //店铺id

    private long specId = 0 ;
    private long voiceId = 0 ;
    private String room = null;

    private User user = AppContext.getInstance().getUser();

    private boolean hasDefaultAddress = false;      //是否获取到了默认地址
    private boolean hasShopInfo = false;            //是否获取到了店铺附加信息
    private boolean hasGoodsInfo = false;           //是否获取到了商品信息

    private long areaCode;      //区域码
    private boolean hasAreaCode = false;            //区域码
    private long companyMemberId = 0;           // 店铺管理者id
    private Long orderId = null;

    private String sendRange = "";  //配送范围
    private String distance = "" ;       //距离
    private City city = AppContext.getInstance().getCity();
    private BigDecimal sendStartMoney,orderRoomStartMoney;
    private boolean isOrderedSuccess = false;//下单成功

    private Long addressId;//收货地址ID
    private String address;//收货地址
    private String phone;//收货人电话
    private String purchaser;//收货人姓名
    private boolean canUseRedbags = false;//是否可以用红包  默认不可以
    private List<GoodsBean> goodsBeanList;

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
        specId = intent.getLongExtra("specId",0);
        voiceId = intent.getLongExtra("voiceId",0);
        room = intent.getStringExtra("room");
        packSum = BigDecimal.valueOf(intent.getDoubleExtra("packagePrice",0d));
        Log.i("test",packSum.toString());

        backLayout = findView(R.id.shop_order_back);
        titleWidget = findView(R.id.shop_order_title);
        ll_order_phone = findView(R.id.ll_order_phone);
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
        packageNameTv = findView(R.id.shop_order_main_packageprice_name_tv);
        tv_address_alert = findView(R.id.tv_address_alert);

        //右边布局
        rightContainerLL = findView(R.id.shop_order_main_right_ll);
        rNameEd = findView(R.id.shop_order_main_right_name_ed);
        rPhoneEd = findView(R.id.shop_order_main_right_phone_ed);
        rYearTv = findView(R.id.shop_order_main_right_year_tv);
        rSecondTv = findView(R.id.shop_order_main_right_second_tv);
        rCalenderIm = findViewClick(R.id.shop_order_main_right_calender_im);
        rPersonNumEd = findView(R.id.shop_order_main_right_personnum_ed);
        rRoomIm = findViewClick(R.id.shop_order_main_right_room_im);
        contactTv = findView(R.id.shop_order_main_right_contact_ed);

        //common
        recyclerView = findView(R.id.shop_order_main_rc);
        totalPriceTv = findView(R.id.shop_order_main_totalprice_tv);
        commitRl = findViewClick(R.id.shop_order_main_commit_rl);
        commitIm = findView(R.id.shop_order_main_commit_im);
        commitTv = findView(R.id.shop_order_main_commit_tv);
        tv_commit_alert = findView(R.id.tv_commit_alert);
        tv_order_detail = findViewClick(R.id.tv_order_detail);
        containLl = findView(R.id.shop_order_containerLl);
        canClickCommit(false);//默认不让点击

        //优惠券相关view
        ll_order_coupons = findViewClick(R.id.ll_order_coupons);
        tv_coupon_name = findView(R.id.tv_coupon_name);
        //红包相关view
        ll_order_coupons_redbag = findViewClick(R.id.ll_order_coupons_redbag);
        tv_coupon_name_redbag = findView(R.id.tv_coupon_name_redbag);

        adapter = new ShopFoodOrderAdapter(this,dataList,type);
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
        dbUtil =  DBUtil.getInstance(this);
        if(type == SHOP_MAIN){
            packagePriceTv.setVisibility(View.GONE);
            packageNameTv.setVisibility(View.GONE);
        }else if (type == FOOD_MAIN){
            ll_order_phone.setVisibility(View.GONE);//点餐时不要用户填写预订电话  但是其实默认填写了用户的登录电话号码  但是如果用户点击了预订还要将其显示出来的
            contactTv.setText("桌  号:");
            //rNameEd.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            rNameEd.setHint("请输入桌号/包间名");
            if (room != null){
                rNameEd.setText(room);
                rNameEd.setFocusableInTouchMode(false);
            }else {
                rNameEd.setText("");
            }
        }
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

        /**数据库查商品，再联网查*/
        if(goodsId == 0){       //通过 店铺id 查商品
            list = dbUtil.queryByShopId(shopId);        //这里是从店铺进来的
            if(list != null && list.size() != 0){
                if(type == FOOD_MAIN){
                    for(int i = 0 ; i < list.size() ; i ++ ){
                        if(list.get(i).getIsChoice()){
                            dataList.add(list.get(i));
                        }
                    }
                    //dataList.addAll(dbUtil.queryByShopId(shopId));
                    adapter.notifyDataSetChanged();
                    for(int i = 0 ;i < dataList.size();i++){
                        num = num + ((GoodsBean)dataList.get(i)).getNumber();
                        goodsSum = goodsSum.add(BigDecimal.valueOf(dataList.get(i).getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(dataList.get(i).getNumber())));
                        if(dataList.get(i).getIspackagecost()==1){
                            packNum = packNum+(dataList.get(i).getNumber());
//                            request_package_sum(dataList.get(i).getGoodId());
                        }
                        //goodsSum = goodsSum + ((GoodsBean)dataList.get(i)).getNumber()*((GoodsBean)dataList.get(i)).getPrice();
                    }
                    foodBean.setNumtotal(num);
                    shopBean.setNumtotal(num);
                    hasGoodsInfo = true;
                    totalCount(goodsSum.add(sendSum).add(packSum));   //展示总价，如果没有获取到打包费，先是0，获取到，后面有更新的动作

                } else if(type == SHOP_MAIN){
                    for(int i = 0 ; i < list.size() ; i ++ ){
                        if(list.get(i).getIsChoice()){
                            dataList.add(list.get(i));
                        }
                    }
                    //dataList.addAll(dbUtil.queryByShopId(shopId));
                    adapter.notifyDataSetChanged();
                    for(int i = 0 ;i < dataList.size();i++){
                        num = num + ((GoodsBean)dataList.get(i)).getNumber();
                        goodsSum = goodsSum.add(BigDecimal.valueOf(dataList.get(i).getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(dataList.get(i).getNumber())));
                        //goodsSum = goodsSum + ((GoodsBean)dataList.get(i)).getNumber()*((GoodsBean)dataList.get(i)).getPrice();
                    }
                    foodBean.setNumtotal(num);
                    shopBean.setNumtotal(num);
                    hasGoodsInfo = true;
                    totalCount(goodsSum.add(sendSum));
                    //TODO 这里看看商品的规格是否需要操作
                }

            }
        } else {            //通过 商品id 查商品,这里只有商城的,现在有美食的了(拍一拍)
            goodsBeanList = dbUtil.queryByGoodId(goodsId);      //这里是从商城>商品>立即购买已选进来的
            if(goodsBeanList != null && goodsBeanList.size() > 0){
//                shopId = goodsBean.getShopId();
                for (int i = 0;i < goodsBeanList.size();i++){
                    dataList.add(goodsBeanList.get(i));
                }
                adapter.notifyDataSetChanged();
                for(int i = 0 ;i < dataList.size();i++){
                    num = num + ((GoodsBean)dataList.get(i)).getNumber();
                    goodsSum = goodsSum.add(BigDecimal.valueOf(dataList.get(i).getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(dataList.get(i).getNumber())));
                    if(dataList.get(i).getIspackagecost()==1){
                        packNum = packNum+(dataList.get(i).getNumber());
                    }
                    //goodsSum = goodsSum + ((GoodsBean)dataList.get(i)).getNumber()*((GoodsBean)dataList.get(i)).getPrice();
                }
                foodBean.setNumtotal(num);
                shopBean.setNumtotal(num);
                hasGoodsInfo = true;
                totalCount(goodsSum.add(sendSum).add(packSum));
            }
        }

        /**查店铺附加信息，打包费，配送费，配送时长*/
        switch (type){
            case FOOD_MAIN:
                FoodNet.foodExInfo(shopId, new HandleSuccess<FoodExInfoBean>() {
                    @Override
                    public void success(FoodExInfoBean foodExInfoBean) {
                        if(foodExInfoBean.getData() != null){
                            Double startMoney = foodExInfoBean.getData().getOpesendstartmoney();
                            if(startMoney == null){
                                startMoney = 0d;
                            }
                            if (foodExInfoBean.getData().getOperoomcost() != null){
                                orderRoomStartMoney = BigDecimal.valueOf(foodExInfoBean.getData().getOperoomcost());
                            }else {
                                orderRoomStartMoney = new BigDecimal(0d);
                            }
                            sendStartMoney = BigDecimal.valueOf(startMoney);
                            if(foodExInfoBean.getData().getOpemethod() != null){
                                switch (foodExInfoBean.getData().getOpemethod()){
                                    case "0":       //堂吃
                                        titleWidget.setVisibility(View.GONE);
                                        bywayContainerLl.setVisibility(View.GONE);
                                        rightContainerLL.setVisibility(View.VISIBLE);
                                        leftContainerLL.setVisibility(View.GONE);
                                        foodBean.setAppointTime(nowDate);   //设置时间
                                        foodBean.setAppointRoom(0); //设置是否包间
                                        foodBean.setPaytype(1);    //设置默认支付方式
                                        foodBean.setOrderType(1);   //类型
                                        typeCC = 1;
                                        hasShopInfo = true;
                                        BigDecimal outsendMoney1 = goodsSum;
                                        if (useingCoupon != null){
                                            outsendMoney1 = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                                        }
                                        if (outsendMoney1.doubleValue()<0d){
                                            outsendMoney1 = BigDecimal.valueOf(0d);
                                        }
                                        totalCount(outsendMoney1);
                                        canClickCommit(true);
                                        break;
                                    case "1":       //外卖
                                        titleWidget.setVisibility(View.GONE);
                                        rightContainerLL.setVisibility(View.GONE);
                                        leftContainerLL.setVisibility(View.VISIBLE);
                                        foodBean.setPaytype(1);   //设置默认支付方法
                                        foodBean.setIsByself(1);       //设置默认获取方式
                                        foodBean.setOrderType(2);   //类型
                                        typeCC = 2;


                                        //填充数据
                                        foodBean.setPackageCost(foodExInfoBean.getData().getOpepackagecost());
                                        foodBean.setLogisticsAmount(foodExInfoBean.getData().getOpesendcost());
                                        sendSum = BigDecimal.valueOf(foodExInfoBean.getData().getOpesendcost()).setScale(2,BigDecimal.ROUND_HALF_UP);
//                                        packSum = BigDecimal.valueOf(foodExInfoBean.getData().getOpepackagecost()).setScale(2,BigDecimal.ROUND_HALF_UP);
                                        hasShopInfo = true;
                                        BigDecimal outsendMoney2 = goodsSum.add(sendSum).add(packSum);
                                        if (outsendMoney2.compareTo(sendStartMoney) < 0){//不满起送费 确定按钮变灰 变字 compare -1小于 0等于 1大于
                                            canClickCommit(false);
                                            commitTv.setText("还差"+sendStartMoney.subtract(outsendMoney2).toString()+"元");
                                            tv_commit_alert.setText("满"+sendStartMoney.toString()+"元配送");
                                        }else {
                                            canClickCommit(true);
                                        }
                                        if (useingCoupon != null){
                                            outsendMoney2 = (goodsSum.add(packSum)).subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                                        }
                                        if (outsendMoney2.doubleValue()<0d){
                                            outsendMoney2 = BigDecimal.valueOf(0d);
                                        }
                                        totalCount(outsendMoney2.add(sendSum));

                                        //填充视图
                                        sendTimeTv.setText(foodExInfoBean.getData().getOpelimittime());
                                        if (foodExInfoBean.getData().getOpesendcost() == 0d){
                                            sendPriceTv.setText("￥0");
                                        }else {
                                            sendPriceTv.setText("￥" + (int) foodExInfoBean.getData().getOpesendcost());
                                        }
                                        packagePriceTv.setText("￥"+packSum.toString());
                                        break;
                                    case "2":       //都有
                                        foodBean.setPaytype(1);    //设置默认支付方式
                                        foodBean.setIsByself(1);       //设置默认获取方式 默认为配送 为了点击外卖时加上配送费打包费  如果不点击外卖这个字段也是没用的不影响点餐
                                        foodBean.setAppointTime(nowDate);   //设置时间
                                        foodBean.setAppointRoom(0); //设置是否包间
                                        foodBean.setOrderType(3);   //类型
                                        typeCC = 1;


                                        //填充数据
                                        foodBean.setPackageCost(foodExInfoBean.getData().getOpepackagecost());
                                        foodBean.setLogisticsAmount(foodExInfoBean.getData().getOpesendcost());
                                        sendSum = BigDecimal.valueOf(foodExInfoBean.getData().getOpesendcost()).setScale(2,BigDecimal.ROUND_HALF_UP);
//                                        packSum = BigDecimal.valueOf(foodExInfoBean.getData().getOpepackagecost()).setScale(2,BigDecimal.ROUND_HALF_UP);
                                        hasShopInfo = true;
                                        BigDecimal outsendMoney3 = goodsSum;

                                        if (useingCoupon != null){
                                            outsendMoney3 = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                                        }
                                        if (outsendMoney3.doubleValue()<0d){
                                            outsendMoney3 = BigDecimal.valueOf(0d);
                                        }
                                        totalCount(outsendMoney3);
                                        canClickCommit(true);
                                        //填充视图
                                        sendTimeTv.setText(foodExInfoBean.getData().getOpelimittime());
                                        if (foodExInfoBean.getData().getOpesendcost() == 0d){
                                            sendPriceTv.setText("￥0");
                                        }else {
                                            sendPriceTv.setText("￥" + (int) foodExInfoBean.getData().getOpesendcost());
                                        }
                                        packagePriceTv.setText("￥"+packSum.toString());
                                        break;
                                }
                            } else {
                                containLl.setVisibility(View.INVISIBLE);        //美食店铺没有填写店铺ope的时候
                                showToa();
                            }
                            sendRange = foodExInfoBean.getData().getOpesendrange(); //获取配送范围

                        } else {
                            showToa();
                        }
//                        dismiss();
                        tempActivity(FOOD_MAIN);//临时活动
                    }
                });


                break;
            case SHOP_MAIN:
                findViewById(R.id.shop_order_main_offline_rb).setVisibility(View.GONE);
                ShopNet.shopExInfo(shopId, new HandleSuccess<ShopExtraInfoBean>() {
                    @Override
                    public void success(ShopExtraInfoBean shopExtraInfoBean) {
                        if(shopExtraInfoBean.getData() != null ){
                            Double startMoney = shopExtraInfoBean.getData().getBegingive();
                            if(startMoney == null){
                                startMoney = 0d;
                            }
                            sendStartMoney = BigDecimal.valueOf(startMoney);
                            titleWidget.setVisibility(View.GONE);
                            rightContainerLL.setVisibility(View.GONE);
                            leftContainerLL.setVisibility(View.VISIBLE);
                            bywayContainerLl.setVisibility(View.VISIBLE);
                            shopBean.setPaytype(1);     //设置默认支付方法
                            shopBean.setByway(1);       //设置默认获取方式
                            typeCC = 2;

                            //填充视图
                            sendTimeTv.setText((int)shopExtraInfoBean.getData().getSendtime()+"分钟");
                            if (shopExtraInfoBean.getData().getSendcost() == 0d){
                                sendPriceTv.setText("￥0");
                            }else {
                                sendPriceTv.setText("￥" + (int) shopExtraInfoBean.getData().getSendcost() + "");
                            }
                            packagePriceTv.setText("￥"+(int)shopExtraInfoBean.getData().getPacking()+"");
                            //填充数据
                            //shopBean.setPackageCost(shopExtraInfoBean.getData().getPacking());
                            //shopBean.setLogisticsAmount(shopExtraInfoBean.getData().getSendcost());
                            sendSum = BigDecimal.valueOf(shopExtraInfoBean.getData().getSendcost()).setScale(2,BigDecimal.ROUND_HALF_UP);
                            hasShopInfo = true;
                            BigDecimal outsendMoney = goodsSum.add(sendSum);
                            if (outsendMoney.compareTo(sendStartMoney) < 0){//不满起送费 确定按钮变灰 变字 compare -1小于 0等于 1大于
                                canClickCommit(false);
                                commitTv.setText("还差"+sendStartMoney.subtract(outsendMoney).toString()+"元");
                                tv_commit_alert.setText("满"+sendStartMoney.toString()+"元配送");
                            }else {
                                canClickCommit(true);
                            }
                            BigDecimal couponPrice = goodsSum;
                            if (useingCoupon != null){
                                couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                            }
                            if (couponPrice.doubleValue()<0d){
                                couponPrice = BigDecimal.valueOf(0d);
                            }
                            totalCount(couponPrice.add(sendSum));
                            sendRange = shopExtraInfoBean.getData().getScope()+"";
//                            dismiss();
                            tempActivity(SHOP_MAIN);//临时活动
                        } else {        //如果请求不到数据，这里设置了默认值(仅商城这样做)
                            titleWidget.setVisibility(View.GONE);
                            rightContainerLL.setVisibility(View.GONE);
                            leftContainerLL.setVisibility(View.VISIBLE);
                            shopBean.setPaytype(1);     //设置默认支付方法
                            shopBean.setByway(1);       //设置默认获取方式
                            typeCC = 2;

                            //填充视图
                            sendTimeTv.setText(0+"分钟");
                            sendPriceTv.setText("￥0");;
                            packagePriceTv.setText("￥0");
                            //填充数据
                            //shopBean.setPackageCost(shopExtraInfoBean.getData().getPacking());
                            //shopBean.setLogisticsAmount(shopExtraInfoBean.getData().getSendcost());
                            sendSum = BigDecimal.valueOf(0);
                            hasShopInfo = true;
                            canClickCommit(false);
                            BigDecimal couponPrice = goodsSum;
                            if (useingCoupon != null){
                                couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                            }
                            if (couponPrice.doubleValue()<0d){
                                couponPrice = BigDecimal.valueOf(0d);
                            }
                            totalCount(couponPrice.add(sendSum));
//                            dismiss();
                            tempActivity(SHOP_MAIN);//临时活动
                        }

                    }
                });

                break;
        }



        //查询是否有默认地址
        ShopNet.changeAddress(user.getId(), new HandleSuccess<AddressInfoBean>() {
            @Override
            public void success(AddressInfoBean addressInfoBean) {
                if(addressInfoBean.getData() != null){
                    addressId = addressInfoBean.getData().getId();
                    purchaser = addressInfoBean.getData().getName();
                    phone = addressInfoBean.getData().getPhone();
                    address = addressInfoBean.getData().getAddress();

                    peopleTv.setText(purchaser);
                    phoneTV.setText(phone);
                    locationTv.setText(address);
                    foodBean.setAddressId(addressId);       //美食地址
                    foodBean.setPurchaser(purchaser);     // 美食外卖 联系人
                    foodBean.setPurchaserPhone(phone);//美食外卖 电话
                    foodBean.setAddressStr(address);

                    shopBean.setAddressId(addressId);
                    shopBean.setAddress(address);    //商城地址
                    shopBean.setPhone(phone);        //联系人电话
                    shopBean.setPurchaser(purchaser);     //联系人名字
                    shopBean.setPurchaserPhone(phone);//联系人电话
                    hasDefaultAddress = true;
                }else {
                    haveAddressLl.setVisibility(View.GONE);
                    noAddressRl.setVisibility(View.VISIBLE);
                }
                rNameEd.setFocusableInTouchMode(true);
            }
        });

        shopBean.setMemberId(user.getId());
        shopBean.setStoreId(shopId == 0 ? dbUtil.queryByGoodId(goodsId,specId).getShopId():shopId);



        //TODO 从服务器，数据库读取数据，填充列表
        //默认收货地址//TODO 收货地址改
        //本地数据联网查询  根据type
        //根据店铺id查询经营设置  ,  店铺附加信息查询
    }

    private void showToa(){
        Toast.makeText(ShopFoodOrderActivity.this,"店铺维护中,请稍后重试",Toast.LENGTH_SHORT).show();
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
                if(type == FOOD_MAIN){
                    if(typeCC == 1 ) {   //堂吃
                        upLoadFood();
                    } else if( typeCC == 2 ){   //外卖
                        if(hasDefaultAddress){
                            upLoadFood();
                        } else {
                            if (foodBean.getIsByself() == 2){//自取
                                upLoadFood();
                            }else if (foodBean.getIsByself() == 1){//配送
                                Toast.makeText(ShopFoodOrderActivity.this,"请选择您的收货地址",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else if(type == SHOP_MAIN){
                    if(hasDefaultAddress){
                        upLoadShop();
                    } else {
                        if (shopBean.getByway() == 2){//自取
                            upLoadShop();
                        }else if (shopBean.getByway() == 1){//配送
                            Toast.makeText(ShopFoodOrderActivity.this,"请选择您的收货地址",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                break;
            case R.id.shop_order_main_haveaddress_ll:       //有联系人信息
                UIHelper.togoAddressManageActivity(this);
                break;
            case R.id.shop_order_main_noaddress_rl:         //没有联系人信息
                UIHelper.togoAddressManageActivity(this);
                break;
            case R.id.ll_order_coupons:
                if (!useableCoupon){
                    if (allCoupons!= null && allCoupons.size()>0){
                        UIHelper.togoCashCouponActivity(this,allCoupons);
                    }else {
                        showToast("暂无可用优惠券");
                    }
                }else if (useableCoupon){
                    UIHelper.togoCashCouponActivity(this,allCoupons);
                }
                break;
            case R.id.ll_order_coupons_redbag:
                if (!useableRedbag){
                    if (allRedbags != null && allRedbags.size() > 0){
                        if (allRedbags.get(0).getCouponSum() > 0d) {
                            UIHelper.togoRedBagCouponActivity(this, allRedbagsUINeeded);
                        }else {
                            showToast("商品暂不支持使用红包");
                        }
                    }else {
                        showToast("暂无可用红包");
                    }
                }else if (useableRedbag){
                    UIHelper.togoRedBagCouponActivity(this,allRedbagsUINeeded);
                }
                break;
        }
    }

    /**
     * 对checkbox的处理，数据保存在bean里
     * */
    private void initCommonClick(){

        //这俩个数据先设置默认值
        map.put("hdFoodOrder.orderStatus",0);
        map.put("order.orderStatus",0);                        //商城订单状态 0待付款

        //设置标题
        backLayout.setTitle("确认订单");
        //设置顶部按钮
        titleWidget.setLeftClickListener(new ShopFoodOrderTitleWidget.OnLeftClickListener() {
            @Override
            public void onLeftClick() {//堂吃（点餐）
                findViewById(R.id.shop_order_main_offline_rb).setVisibility(View.VISIBLE);
                ll_order_phone.setVisibility(View.GONE);
                leftContainerLL.setVisibility(View.GONE);
                rightContainerLL.setVisibility(View.VISIBLE);
                bywayContainerLl.setVisibility(View.GONE);
                typeCC = 1;     //堂吃
                canClickCommit(true);
                BigDecimal couponPrice = goodsSum;
                if (useingCoupon != null){
                    couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                }
                if (couponPrice.doubleValue()<0d){
                    couponPrice = BigDecimal.valueOf(0d);
                }
                totalCount(couponPrice);
                foodBean.setOrderType(3);
                contactTv.setText("桌  号:");
                //rNameEd.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                rNameEd.setHint("请输入桌号/包间名");
                if (room != null){
                    rNameEd.setText(room);
                }else {
                    rNameEd.setText("");
                }
                if (canUseRedbags){//可以用红包并且是餐饮 肯定是餐饮
                    findRedBagDatas();
                }
            }
        });
        titleWidget.setRightClickListener(new ShopFoodOrderTitleWidget.OnRightClickListener() {
            @Override
            public void onRightClick() {//外卖
                RadioButton left = (RadioButton) findViewById(R.id.shop_order_main_online_rb);
                RadioButton right = (RadioButton) findViewById(R.id.shop_order_main_offline_rb);
                right.setVisibility(View.GONE);
                left.setChecked(true);
                right.setChecked(false);
                foodBean.setPaytype(1);
                shopBean.setPaytype(1);
                map.put("hdFoodOrder.orderStatus",0);
                map.put("order.orderStatus",0);
                leftContainerLL.setVisibility(View.VISIBLE);
                rightContainerLL.setVisibility(View.GONE);
                typeCC = 2;     //外卖
                foodBean.setOrderType(2);
                BigDecimal outsendMoney = goodsSum.add(sendSum).add(packSum);
                if (outsendMoney.compareTo(sendStartMoney) < 0){//不满起送费 确定按钮变灰 变字 compare -1小于 0等于 1大于
                    canClickCommit(false);
                    commitTv.setText("还差"+sendStartMoney.subtract(outsendMoney).toString()+"元");
                    tv_commit_alert.setText("满"+sendStartMoney.toString()+"元配送");
                }else {
                    canClickCommit(true);
                }
//                totalCount(outsendMoney);?
                showIsBySelf();
                bywayContainerLl.setVisibility(View.VISIBLE);
                if (canUseRedbags){//可以用红包并且是餐饮 肯定是餐饮
                    findRedBagDatas();
                }
            }
        });
        titleWidget.setRrClickListener(new ShopFoodOrderTitleWidget.OnRRClickListener() {
            @Override
            public void onRRClick() {//预订
                RadioButton left = (RadioButton) findViewById(R.id.shop_order_main_online_rb);
                RadioButton right = (RadioButton) findViewById(R.id.shop_order_main_offline_rb);
                right.setVisibility(View.GONE);
                left.setChecked(true);
                right.setChecked(false);
                foodBean.setPaytype(1);
                shopBean.setPaytype(1);
                map.put("hdFoodOrder.orderStatus",0);
                map.put("order.orderStatus",0);
                leftContainerLL.setVisibility(View.GONE);
                rightContainerLL.setVisibility(View.VISIBLE);
                bywayContainerLl.setVisibility(View.GONE);
                ll_order_phone.setVisibility(View.VISIBLE);
                rPhoneEd.setText(user.getPhone());
                typeCC = 1;     //预定
                BigDecimal couponPrice = goodsSum;
                if (useingCoupon != null){
                    couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                }
                if (couponPrice.doubleValue()<0d){
                    couponPrice = BigDecimal.valueOf(0d);
                }
                totalCount(couponPrice);
                if (goodsSum.compareTo(orderRoomStartMoney) < 0){//不满起订费 确定按钮变灰 变字 compare -1小于 0等于 1大于
                    canClickCommit(false);
                    commitTv.setText("还差"+orderRoomStartMoney.subtract(goodsSum).toString()+"元");
                    tv_commit_alert.setText("满"+orderRoomStartMoney.toString()+"元可订");
                }else {
                    canClickCommit(true);
                }
                foodBean.setOrderType(1);
                contactTv.setText("联系人:");
                //rNameEd.setInputType(EditorInfo.TYPE_NULL);
                rNameEd.setHint("请输入姓名");
                rNameEd.setText("");
                if (canUseRedbags){//可以用红包并且是餐饮 肯定是餐饮
                    findRedBagDatas();
                }
            }
        });
        //线上线下选择
        lineRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shop_order_main_online_rb:
                        foodBean.setPaytype(1);
                        shopBean.setPaytype(1);
                        map.put("hdFoodOrder.orderStatus",0);
                        map.put("order.orderStatus",0);                        //商城订单状态 0待付款
                        break;
                    case R.id.shop_order_main_offline_rb:
                        foodBean.setPaytype(2);
                        shopBean.setPaytype(2);
                        map.put("hdFoodOrder.orderStatus",1);
                        map.put("order.orderStatus",1);                        //商城订单状态 1待发货
                        break;
                }
            }
        });
        //自取配送选择
        bywayRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shop_order_main_byself_rb:    //自取 2
                        if (type == SHOP_MAIN && shopId != 999L) {
                            findViewById(R.id.shop_order_main_offline_rb).setVisibility(View.VISIBLE);
                        }
                        foodBean.setIsByself(2);
                        shopBean.setByway(2);
                        sendPriceTv.setText("￥0");

                        tv_address_alert.setText("用户到店自取");
                        if (hasDefaultAddress){
                            shopBean.setAddressId(addressId);
                            shopBean.setPurchaser(purchaser);
                            shopBean.setPurchaserPhone(phone);
                            shopBean.setPhone(phone);
                            shopBean.setAddress(address);
                            foodBean.setAddressId(addressId);
                            foodBean.setPurchaser(purchaser);
                            foodBean.setPurchaserPhone(phone);
                            foodBean.setAddressStr(address);
                        }else {
                            shopBean.setAddressId(0l);
                            if (user.getNickName() != null){
                                shopBean.setPurchaser(user.getNickName());
                            }else {
                                shopBean.setPurchaser("用户"+user.getPhone());
                            }
                            shopBean.setPurchaserPhone(user.getPhone());
                            shopBean.setPhone(user.getPhone());
                            shopBean.setAddress("（自取）未填写");
                            foodBean.setAddressId(0l);
                            if (user.getNickName() != null){
                                foodBean.setPurchaser(user.getNickName());
                            }else {
                                foodBean.setPurchaser("用户"+user.getPhone());
                            }
                            foodBean.setPurchaserPhone(user.getPhone());
                            foodBean.setAddressStr("（自取）未填写");
                        }
                        showIsBySelf();
                        break;
                    case R.id.shop_order_main_byother_rb:   //配送 1
                        RadioButton left = (RadioButton) findViewById(R.id.shop_order_main_online_rb);
                        RadioButton right = (RadioButton) findViewById(R.id.shop_order_main_offline_rb);
                        right.setVisibility(View.GONE);
                        left.setChecked(true);
                        right.setChecked(false);
                        foodBean.setIsByself(1);
                        shopBean.setByway(1);
                        if (sendSum.doubleValue() == 0d){
                            sendPriceTv.setText("￥0");
                        }else {
                            sendPriceTv.setText("￥" + sendSum.toString());
                        }
                        tv_address_alert.setText("请选择您的收货地址");
                        if (hasDefaultAddress){
                            shopBean.setAddressId(addressId);
                            shopBean.setPurchaser(purchaser);
                            shopBean.setPurchaserPhone(phone);
                            shopBean.setPhone(phone);
                            shopBean.setAddress(address);
                            foodBean.setAddressId(addressId);
                            foodBean.setPurchaser(purchaser);
                            foodBean.setPurchaserPhone(phone);
                            foodBean.setAddressStr(address);
                        }
                        showIsBySelf();
                        break;
                }
            }
        });

    }

    /**
     * 这个方法针对自取和配送这个选项，重新展示价格
     * */
    private void showIsBySelf(){
        if(type == FOOD_MAIN){
            switch (typeCC){
                case 1:     //堂吃
                    canClickCommit(true);
                    BigDecimal couponPrice = goodsSum;
                    if (useingCoupon != null){
                        couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                    }
                    if (couponPrice.doubleValue()<0d){
                        couponPrice = BigDecimal.valueOf(0d);
                    }
                    totalCount(couponPrice);
                    break;
                case 2:     //外卖
                    if(foodBean.getIsByself() == 1){        //配送  考虑起送费
                        BigDecimal outsendMoney = goodsSum.add(sendSum).add(packSum);
                        if (outsendMoney.compareTo(sendStartMoney) < 0){//不满起送费 确定按钮变灰 变字 compare -1小于 0等于 1大于
                            canClickCommit(false);
                            commitTv.setText("还差"+sendStartMoney.subtract(outsendMoney).toString()+"元");
                            tv_commit_alert.setText("满"+sendStartMoney.toString()+"元配送");
                        }else {
                            canClickCommit(true);
                        }
                        BigDecimal couponPrice2 = goodsSum;
                        if (useingCoupon != null){
                            couponPrice2 = (goodsSum.add(packSum)).subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                        }else {
                            couponPrice2 = goodsSum.add(packSum);
                        }
                        if (couponPrice2.doubleValue()<0d){
                            couponPrice2 = BigDecimal.valueOf(0d);
                        }
                        totalCount(couponPrice2.add(sendSum));
                    }else if(foodBean.getIsByself() == 2){  //自取
                        canClickCommit(true);
                        BigDecimal couponPrice2 = goodsSum;
                        if (useingCoupon != null){
                            couponPrice2 = (goodsSum.add(packSum)).subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                        }else {
                            couponPrice2 = goodsSum.add(packSum);
                        }
                        if (couponPrice2.doubleValue()<0d){
                            couponPrice2 = BigDecimal.valueOf(0d);
                        }
                        totalCount(couponPrice2);
                    }
                    break;
            }
        } else if(type == SHOP_MAIN){
            if(shopBean.getByway() == 1 ) {         //配送
                BigDecimal outsendMoney = goodsSum.add(sendSum);
                if (outsendMoney.compareTo(sendStartMoney) < 0){//不满起送费 确定按钮变灰 变字 compare -1小于 0等于 1大于
                    canClickCommit(false);
                    commitTv.setText("还差"+sendStartMoney.subtract(outsendMoney).toString()+"元");
                    tv_commit_alert.setText("满"+sendStartMoney.toString()+"元配送");
                }else {
                    canClickCommit(true);
                }
                BigDecimal couponPrice = goodsSum;
                if (useingCoupon != null){
                    couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                }
                if (couponPrice.doubleValue()<0d){
                    couponPrice = BigDecimal.valueOf(0d);
                }
                totalCount(couponPrice.add(sendSum));
            } else if(shopBean.getByway() == 2){    //自取
                canClickCommit(true);
                BigDecimal couponPrice = goodsSum;
                if (useingCoupon != null){
                    couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCoupon.getCouponSum()));
                }
                if (couponPrice.doubleValue()<0d){
                    couponPrice = BigDecimal.valueOf(0d);
                }
                totalCount(couponPrice);
            }
        }
    }

    /**
     *      时间 由listener组装
     * */

    private void upLoadFood(){

        boolean canYou = false;     //common选项判断上传
        //Map<String,Object> map = new HashMap<>();   //要上传的map

        if(typeCC == 1){    //堂吃或预订
            if(!TextUtils.isEmpty(rNameEd.getText().toString())){
                foodBean.setPurchaser(rNameEd.getText().toString());
                if (foodBean.getOrderType() == 3){//堂吃（点餐）
                    foodBean.setPurchaserPhone(user.getPhone());//不需要用户填写电话号码  自动填写
                }else if (foodBean.getOrderType() == 1){//预订
                    if (TextUtils.isEmpty(rPhoneEd.toString())){
                        Toast.makeText(this,"请输入预订电话",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        foodBean.setPurchaserPhone(rPhoneEd.getText().toString().trim());
                    }
                }
                if(!TextUtils.isEmpty(rPersonNumEd.getText().toString())){
                    foodBean.setAppointPerson(Integer.valueOf(rPersonNumEd.getText().toString()));
                    map.put("hdFoodOrder.appointPerson",foodBean.getAppointPerson());
                    map.put("hdFoodOrder.appointRoom",foodBean.getAppointRoom());
                    String orderTime = dateFormatUp.format(foodBean.getAppointTime());
                    map.put("apptime",orderTime);
                    Log.i("test",orderTime);
                    map.put("hdFoodOrder.purchaser",foodBean.getPurchaser());
                    map.put("hdFoodOrder.purchaserPhone",foodBean.getPurchaserPhone());
                    map.put("hdFoodOrder.isByself",3);
                    canYou = true;
                }else {
                    Toast.makeText(this,"请输入人数",Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this,"请输入"+contactTv.getText().toString().replace(":","").replace(" ",""),Toast.LENGTH_SHORT).show();
            }
        } else if(typeCC == 2){     //外卖

            if(TextUtils.isEmpty(String.valueOf(foodBean.getAddressId()))){
                Toast.makeText(this,"请选择收货地址",Toast.LENGTH_SHORT).show();
            } else {
                map.put("hdFoodOrder.addressId",foodBean.getAddressId());
                map.put("hdFoodOrder.purchaser",foodBean.getPurchaser());
                map.put("hdFoodOrder.purchaserPhone",foodBean.getPurchaserPhone());
                map.put("hdFoodOrder.address",foodBean.getAddressStr());
                map.put("hdFoodOrder.logisticsAmount",foodBean.getLogisticsAmount());        //送餐费
                map.put("hdFoodOrder.packageCost",packSum);            //打包费
                map.put("hdFoodOrder.isByself",foodBean.getIsByself());
                canYou = true;
            }

        }
        if(canYou){         //common
            final List<HdFoodOrder.HdFoodOrderGoodsList> temlist = new ArrayList<>();
            if(goodsId == 0){
                if(dataList.size() > 0){
                    for(int i = 0;i<dataList.size();i++){
                        HdFoodOrder.HdFoodOrderGoodsList aa = new HdFoodOrder.HdFoodOrderGoodsList();
                        aa.setGoodsId(dataList.get(i).getGoodId());
                        aa.setGoodsNum(dataList.get(i).getNumber());
                        aa.setGoodsPrice(BigDecimal.valueOf(dataList.get(i).getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
                        temlist.add(aa);
                    }
                }
            }else {
                HdFoodOrder.HdFoodOrderGoodsList aa = new HdFoodOrder.HdFoodOrderGoodsList();
                aa.setGoodsId(goodsBean.getGoodId());
                aa.setGoodsNum(goodsBean.getNumber());
                aa.setGoodsPrice(BigDecimal.valueOf(goodsBean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
                temlist.add(aa);
            }

            map.put("hdFoodOrder.goodsAmount",goodsSum);                 //商品总价 (新增参数)//////////////
            BigDecimal bd_coupon = new BigDecimal(0);
            if (useingCoupon != null && useingCoupon.getId() == -1L){
                map.put("hdFoodOrder.couponId", 000L);           //优惠券ID (新增参数)///////////
                bd_coupon = BigDecimal.valueOf(useingCoupon.getCouponSum());
                map.put("hdFoodOrder.favorableAmount", bd_coupon);           //优惠券金额 (新增参数)///////////
            }else {
                if (useingCoupon != null && useingCoupon.getCouponSum() != 0d) {
                    map.put("hdFoodOrder.couponId", useingCoupon.getId());           //优惠券ID (新增参数)///////////
                    bd_coupon = BigDecimal.valueOf(useingCoupon.getCouponSum());
                    if (useingCoupon.getType() == 1 || useingCoupon.getType() == 5) {
                        map.put("hdFoodOrder.favorableAmount", bd_coupon);           //优惠券金额 (新增参数)///////////
                    }
                }
            }
            BigDecimal priceWithoutsendSum = new BigDecimal(0d);
            if (typeCC == 2){
                priceWithoutsendSum = (goodsSum.add(packSum)).subtract(bd_coupon);//要不要先加打包费再减优惠券
            }else if (typeCC == 1){
                priceWithoutsendSum = goodsSum.subtract(bd_coupon);//要不要先加打包费再减优惠券
            }
            if (priceWithoutsendSum.doubleValue() < 0d){
                priceWithoutsendSum = BigDecimal.valueOf(0d);
            }
            if(typeCC == 2){       //外卖
                if (foodBean.getIsByself() == 1){//1配送
                    map.put("hdFoodOrder.orderAmount",priceWithoutsendSum.add(sendSum));  //订单金额 (新改参数)///////////
                }else if(foodBean.getIsByself() == 2){     //2 自取    (减去优惠金额)
                    map.put("hdFoodOrder.orderAmount",(priceWithoutsendSum));
                }
            } else if(typeCC == 1 ){       //订餐堂吃
                map.put("hdFoodOrder.orderAmount",priceWithoutsendSum);               //订单金额 (新改参数)///////////
            }


            String data = JSON.toJSONString(temlist);
            map.put("hdFoodOrder.memberId",user.getId());
            map.put("hdFoodOrder.paytype",foodBean.getPaytype());
            //map.put("hdFoodOrder.orderAmount",bean.getOrderAmount());
            map.put("hdFoodOrder.storeId",shopId);
            map.put("hdFoodOrder.areaCode",areaCode);
            map.put("hdFoodOrder.orderType",foodBean.getOrderType());
            map.put("hdFoodOrder.numtotal",foodBean.getNumtotal());
            map.put("hdFoodOrder.phone",foodBean.getShopPhone());
            map.put("hdFoodOrder.voiceId",voiceId);         //趣购
            //map.put("hdFoodOrder.isByself",foodBean.getIsByself());
            showSimpleDialog();
            if(Float.valueOf(distance) <= Float.valueOf(sendRange)){        //判断配送范围
                //调用美食订单递交
                if(hasGoodsInfo && hasShopInfo && hasAreaCode){
                    FoodNet.foodOrder(foodBean.getAddressId(), map, data,dateFormatUp.format(nowDate), new HandleSuccess<FoodOrderPayBean>() {
                        @Override
                        public void success(FoodOrderPayBean foodOrderPayBean) {
                            if (foodOrderPayBean.getStatus() == 1) {
                                isOrderedSuccess = true;
                                if (foodOrderPayBean.getData() != null) {
                                    if (foodBean.getPaytype() == 1) {
                                        orderId = Long.valueOf(foodOrderPayBean.getData().getOrderSn());
                                        UIHelper.togoOnLinePayActivity(ShopFoodOrderActivity.this,
                                                orderId
                                                , foodOrderPayBean.getData().getOrderAmount()
                                                , foodOrderPayBean.getData().getOrderSn() + "", foodOrderPayBean.getData().getId());
                                    } else if (foodBean.getPaytype() == 2) {
                                        UIHelper.togoPaySuccessActivity(ShopFoodOrderActivity.this);
                                    }
                                    finishCommit(temlist);

                                } else {
                                    Toast.makeText(ShopFoodOrderActivity.this, "店铺维护中", Toast.LENGTH_SHORT).show();
                                }

                            }else if (foodOrderPayBean.getStatus() == 10){
                                Toast.makeText(ShopFoodOrderActivity.this,"用餐人数最多10000人",Toast.LENGTH_LONG).show();
                                rPersonNumEd.setText("10000");
                            }else if (foodOrderPayBean.getStatus() == 11){
                                showToast("用餐人数输入错误");
                                rPersonNumEd.setText("");
                            }else if (foodOrderPayBean.getStatus() == 13){
                                showToast("收货地址已超出配送范围");
                            }else if (foodOrderPayBean.getStatus() == 15){
                                showToast("请您重新添加新的收货地址");
                            }
                            dismissSimDialog();
                        }
                    });
                }else {dismissSimDialog();}
            } else {
                dismissSimDialog();
                Toast.makeText(this,"超出店家配送范围",Toast.LENGTH_SHORT).show();
            }

        }

        //TODO 这里进行联网处理，将数据库中的数据递交到服务器
    }


    Map<String,Object> map = new HashMap<>();   //要上传的map
    private void upLoadShop(){
        if (shopBean.getByway() == 1){//需要配送
            if(TextUtils.isEmpty(String.valueOf(shopBean.getAddress()))){
                Toast.makeText(this,"请选择收货地址",Toast.LENGTH_SHORT).show();
            } else {
                executeStart();
            }
        }else if (shopBean.getByway() == 2){//不要配送
            executeStart();
        }
    }
    private void  executeStart(){//开始下单
        showSimpleDialog();
        shopBean.setMemberId(user.getId());
        map.put("order.memberId",user.getId());                       //用户id
        map.put("addressId",shopBean.getAddressId());
        map.put("order.purchaser",shopBean.getPurchaser());                      //购买人
        map.put("order.purchaser_phone",shopBean.getPurchaserPhone());          //购买人电话
        map.put("order.phone",shopBean.getPhone());                             //购买人电话
        map.put("order.address",shopBean.getAddress());                         //收货地址

        final List<MallOrderGoods> goodsList = new ArrayList<>();
        if(dataList.size() > 0){
            for(int i = 0;i<dataList.size();i++){
                MallOrderGoods aa = new MallOrderGoods();
                aa.setGoodsId(dataList.get(i).getGoodId());
                aa.setGoodsNum(dataList.get(i).getNumber());
                aa.setPrice(BigDecimal.valueOf(dataList.get(i).getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
                //aa.setSpecId(dataList.get(i).getShopId());      //TODO specId
                aa.setSpecId(dataList.get(i).getGoodsSpec());
                aa.setGoodsSpec(dataList.get(i).getGoodsSpecName());

                //"颜色:红色 大小:大 尺寸:18";
                goodsList.add(aa);
            }
        }

        String data = JSON.toJSONString(goodsList);
        //map.put("order.paymentId",shopBean.getPaymentId());                     //支付方式
        map.put("order.paytype",shopBean.getPaytype());                     //支付方式
        map.put("order.storeId",shopBean.getStoreId());                         //店铺id
        map.put("order.numtotal",shopBean.getNumtotal());                        //商品总数
        map.put("order.voiceId",voiceId);       //趣购


        //map.put("order.createTime",new Date());                 //下单时间
        map.put("order.areaCode",areaCode);                            //区域码
        map.put("order.byway",shopBean.getByway());                 //自取配送
        map.put("order.goodsAmount",goodsSum);                 //商品总价 (新增参数)//////////////
        BigDecimal bd_coupon = new BigDecimal(0);
        if (useingCoupon != null && useingCoupon.getId() == -1L && useingCoupon.getCouponSum() > 0d){
            map.put("order.couponId", 0L);           //优惠券ID (新增参数)///////////
            bd_coupon =BigDecimal.valueOf(useingCoupon.getCouponSum());
            map.put("order.favorableAmount", bd_coupon);           //优惠券金额 (新增参数)///////////
        }else {
            if (useingCoupon != null && useingCoupon.getCouponSum() != 0d) {
                map.put("order.couponId", useingCoupon.getId());           //优惠券ID (新增参数)///////////
                bd_coupon = BigDecimal.valueOf(useingCoupon.getCouponSum());
                if (useingCoupon.getType() == 1 || useingCoupon.getType() == 5) {
                    map.put("order.favorableAmount", bd_coupon);           //优惠券金额 (新增参数)///////////
                }
            }
        }
        BigDecimal priceWithoutsendSum = goodsSum.subtract(bd_coupon);
        if (priceWithoutsendSum.doubleValue() < 0d){
            priceWithoutsendSum = BigDecimal.valueOf(0d);
        }
        if(shopBean.getByway() == 1){       //1配送   （减去优惠金额）
            map.put("order.orderAmount",priceWithoutsendSum.add(sendSum));  //订单金额 (新改参数)///////////
        } else if(shopBean.getByway() == 2 ){       //2自取    （减去优惠金额）
            map.put("order.orderAmount",priceWithoutsendSum);               //订单金额 (新改参数)///////////
        }

        //if(Float.valueOf(distance) <= Float.valueOf(sendRange)){    //配送范围
        //调用商城订单递交
        if(hasGoodsInfo && hasShopInfo && hasAreaCode){
            ShopNet.shopOrder(companyMemberId, map, data, new HandleSuccess<FoodOrderPayBean>() {
                @Override
                public void success(FoodOrderPayBean foodOrderPayBean) {
                    if(foodOrderPayBean.getStatus() == 1){
                        if(foodOrderPayBean.getData() != null){
                            if(shopBean.getPaytype() == 1 ){
                                orderId = Long.valueOf(foodOrderPayBean.getData().getOrderSn());
                                UIHelper.togoOnLinePayActivity(ShopFoodOrderActivity.this,
                                        orderId
                                        ,foodOrderPayBean.getData().getPrice().doubleValue()
                                        ,foodOrderPayBean.getData().getOrderSn()+"",foodOrderPayBean.getData().getId());
                            } else if(shopBean.getPaytype() == 2){
                                UIHelper.togoPaySuccessActivity(ShopFoodOrderActivity.this);
                            }
                            finishCommit(goodsList);

                        } else {
                            Toast.makeText(ShopFoodOrderActivity.this,"店铺维护中",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if(foodOrderPayBean.getStatus() == 3) {
                            Toast.makeText(ShopFoodOrderActivity.this,"库存不足",Toast.LENGTH_SHORT).show();
                        } else if(foodOrderPayBean.getStatus() == 5){
                            Toast.makeText(ShopFoodOrderActivity.this,"价格不正确",Toast.LENGTH_SHORT).show();
                        }else if (foodOrderPayBean.getStatus() == 4){
                            showToast("收货地址已超出配送范围");
                        }else if (foodOrderPayBean.getStatus() == 6){
                            showToast("抱歉，该店铺还未开始营业");
                        }else if (foodOrderPayBean.getStatus() == 7){
                            showToast("请您重新添加新的收货地址");
                        } else {
                            Toast.makeText(ShopFoodOrderActivity.this,"店铺维护中",Toast.LENGTH_SHORT).show();

                        }
                    }
                    dismissSimDialog();
                }
            });
        }else {dismissSimDialog();}
    }
    /**
     * 在购买成功后，数据库响应商品会清空，此处要调用前面的视图更新
     * */
    private void updateActivityView(){
        for(int i = 0; i < ActivityCollector.activities.size() ; i ++){
            if(ActivityCollector.activities.get(i) instanceof ShopDetailActivity){
                ((ShopDetailActivity) ActivityCollector.activities.get(i)).updateAllView();
            }
            if(ActivityCollector.activities.get(i) instanceof WidgetActivity){
                ActivityCollector.activities.get(i).finish();
            }
            if(ActivityCollector.activities.get(i) instanceof GoodsDetailActivity){
                ActivityCollector.activities.get(i).finish();
            }
        }
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
            Log.i("testTime",date.toString());
            String now = dateFormat.format(date);
            String[] nowS = now.split("-");
            rYearTv.setText(nowS[0]);
            rSecondTv.setText(nowS[1]);
            foodBean.setAppointTime(date);

        }
        @Override
        public void onDateTimeCancel() {
            foodBean.setAppointTime(nowDate);

        }
    };

    /**
     * 拼装商品订单
     *
     * ******未使用，但不建议删除*******
     * */
    @Deprecated
    private void assembleGoodOrder(){
        HdMallOrder bean = new HdMallOrder();
        long companyMemberId =0;           // 店铺管理者id
        List<MallOrderGoods> goodsList = new ArrayList<>();               //商品list
        for(int i = 0 ; i < 3 ; i++){
            MallOrderGoods goods = new MallOrderGoods();
            goods.setGoodsId(12333L+i);         //商品id
            goods.setSpecId(2332L);             //规格id
            goods.setGoodsNum(11);              //商品数量
            goods.setPrice(BigDecimal.valueOf(1));//商品价格
            goodsList.add(goods);
        }
        String data = JSON.toJSONString(goodsList);       //list的序列化
        Map<String,Object> map = new HashMap<>();                           //订单的map
        map.put("order.memberId",bean.getMemberId());                       //用户id
        map.put("order.purchaser",bean.getPurchaser());                      //购买人
        map.put("order.purchaser_phone",bean.getPurchaserPhone());          //购买人电话
        map.put("order.phone",bean.getPhone());                             //购买人电话
        map.put("order.address",bean.getAddress());                         //收货地址

        map.put("order.paytype",bean.getPaytype());                     //支付方式
        map.put("order.storeId",bean.getStoreId());                         //店铺id
        map.put("order.numtotal",bean.getNumtotal());                        //商品总数

        map.put("order.orderStatus",0);                        //'0待付款
        map.put("order.createTime",new Date());                 //下单时间
        map.put("order.areaCode",1);                            //区域码
        map.put("order.byway",bean.getByway());                 //自取配送


        ShopNet.shopOrder(companyMemberId, map, data, new HandleSuccess<FoodOrderPayBean>() {
            @Override
            public void success(FoodOrderPayBean foodOrderPayBean) {
                //Log.i("WATER","success"+responseBody+"");
            }
        });
    }


    /**
     * 接受从更改默认地址获取的结果
     *
     * 填充部分数据，主要是addressId
     *
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1002){
            if(data != null){
                addressId = data.getLongExtra("id",1);
                purchaser = data.getStringExtra("addressee");
                phone = data.getStringExtra("phone");
                address = data.getStringExtra("address");

                foodBean.setAddressId(addressId);
                foodBean.setPurchaser(purchaser);
                foodBean.setPurchaserPhone(phone);
                foodBean.setAddressStr(address);

                shopBean.setAddressId(addressId);
                shopBean.setPurchaser(purchaser);
                shopBean.setPurchaserPhone(phone);
                shopBean.setPhone(phone);
                shopBean.setAddress(address);
                hasDefaultAddress = true;
                upDateView();
            }
        }else if (resultCode == 1009){
            boolean success = data.getBooleanExtra("success",false);
            if (success){
                request_set_payed_order();
            }
        }else if (requestCode == 1718){
            if (data != null) {
                useingCoupon = (CouponsBean.DataBean) data.getSerializableExtra("ccp");
                if (useingCoupon.getId() == 0l) {
                    useableCoupon = false;
                    resetCouponViewDatas(null,1);
                } else {
                    useableCoupon = true;
                    resetCouponViewDatas(useingCoupon,1);
                }
            }
        }else if (requestCode == 1719){
            if (data != null) {
                useingCoupon = (CouponsBean.DataBean) data.getSerializableExtra("rcp");
                if (useingCoupon.getId() == 0l) {
                    useableRedbag = false;
                    resetCouponViewDatas(null,2);
                } else if (useingCoupon.getId() == -1L){
                    if (allRedbags != null && allRedbags.size() > 0) {
                        useingCoupon = allRedbags.get(0);
                        useableRedbag = true;
                        resetCouponViewDatas(useingCoupon, 2);
                    }
                }
            }
        }
    }
    private void request_set_payed_order() {
        //已经付款成功 返回的刚刚成功的数据 可以去某个界面显示一下付款成功
        startActivity(new Intent(ShopFoodOrderActivity.this, PaySuccessActivity.class));
    }
    /**
     * 计算总价和总数
     * */
    private void totalCount(BigDecimal price){
        if(hasGoodsInfo && hasShopInfo){
            totalPriceTv.setText("￥"+price.toString());
        }
    }

    /**
     * 在从地址管理回来后更新视图
     * */
    private void upDateView(){
        haveAddressLl.setVisibility(View.VISIBLE);
        noAddressRl.setVisibility(View.GONE);
        peopleTv.setText(foodBean.getPurchaser());
        phoneTV.setText(foodBean.getPurchaserPhone());
        locationTv.setText(foodBean.getAddressStr());
    }

    /**
     * 是否包间
     * */
    private void roomCrash(){
        if(isRoom){     //不要包间
            rRoomIm.setImageResource(R.mipmap.ic_shop_order_main_switch_off);
            isRoom = false;
            foodBean.setAppointRoom(0);
        } else {        //要包间
            rRoomIm.setImageResource(R.mipmap.ic_shop_order_main_switch_on);
            isRoom = true;
            foodBean.setAppointRoom(1);

        }
    }

    private void finishCommit(List list){
        commitTv.setText(getResources().getString(R.string.order_submited));
        commitIm.setImageResource(R.drawable.btn_gray_deep_rec);
        commitRl.setOnClickListener(null);
        commitRl.setClickable(false);
        if(list.get(0)!= null ){
            if(list.get(0) instanceof MallOrderGoods){
                for(int i = 0 ; i < list.size() ; i ++){
                    MallOrderGoods mallOrderGoods = ((MallOrderGoods)list.get(i));
                    dbUtil.deleteByGoodId(mallOrderGoods.getGoodsId());
                }
            } else if(list.get(0) instanceof HdFoodOrder.HdFoodOrderGoodsList){
                for(int i = 0 ; i < list.size() ; i ++){
                    HdFoodOrder.HdFoodOrderGoodsList mallOrderGoods = ((HdFoodOrder.HdFoodOrderGoodsList)list.get(i));
                    dbUtil.deleteByGoodId(mallOrderGoods.getGoodsId(),0);
                }
            }
        }
        updateActivityView();
    }

    /**
     * 确认按钮可不可以被点击
     * @param b_can true可以  false不可以
     */
    private void canClickCommit(boolean b_can){
        if (b_can && !isOrderedSuccess){
            commitIm.setImageResource(R.drawable.btn_orange_rec);
            commitRl.setOnClickListener(this);
            commitRl.setClickable(true);
            commitTv.setText(getResources().getString(R.string.order_submit));
            tv_commit_alert.setText("");
        }else {
            commitIm.setImageResource(R.drawable.btn_gray_deep_rec);
            commitRl.setOnClickListener(null);
            commitRl.setClickable(false);
            if (isOrderedSuccess){
                commitTv.setText(getResources().getString(R.string.order_submited));
            }
        }
    }

    private BigDecimal goodsSum =  new BigDecimal(0);    //商品总价
    private BigDecimal sendSum =  new BigDecimal(0); //送餐费
    private BigDecimal packSum = new BigDecimal(0);     //打包费
    private int packNum = 0;            //打包数
    private int num = 0;        //商品总数



    /*
    活动方法
     */
    private void tempActivity(int SHOPCATE) {
        if (SHOPCATE == FOOD_MAIN){//查美食店铺信息
            FoodNet.foodShopInfo(shopId, new HandleSuccess<FoodShopInfoBean>() {    //获取区域码
                @Override
                public void success(FoodShopInfoBean foodShopInfoBean) {
                    if(foodShopInfoBean.getData() != null){
                        areaCode = foodShopInfoBean.getData().getRegionId();
                        hasAreaCode = true;
                        foodBean.setShopPhone(foodShopInfoBean.getData().getPhone());
                        distance = CommonUtil.setDistance(city,foodShopInfoBean.getData().getLongitude()
                                ,foodShopInfoBean.getData().getLatitude());     //获取距离
                        //如果type==5，也就是直营店，才查红包 ；直营店不可以用券//////////////////////////
                        checkShopTypeToActivity(foodShopInfoBean.getData().getType());//type==5，也就是直营店
                    }
                    else {
                        showToa();
                    }
                }
            });
        }else if (SHOPCATE == SHOP_MAIN){//查商城店铺信息
            ShopNet.shopInfo(shopId, new HandleSuccess<GoodsShopInfoBean>() {   //获取区域码
                @Override
                public void success(GoodsShopInfoBean goodsShopInfoBean) {
                    if(goodsShopInfoBean.getData() != null){
                        areaCode = goodsShopInfoBean.getData().getRegionId();
                        companyMemberId = goodsShopInfoBean.getData().getCompanyMemberId();
                        hasAreaCode = true;
                        distance = CommonUtil.setDistance(city,goodsShopInfoBean.getData().getLongitude(),
                                goodsShopInfoBean.getData().getLatitude());
                        //如果type==5，也就是直营店，才查红包 ；直营店不可以用券//////////////////////////
                        checkShopTypeToActivity(goodsShopInfoBean.getData().getType());//type==5，也就是直营店
                    } else {
                        showToa();
                    }
                }
            });
        }
    }

    /**
     * 根据店铺Type参加活动
     * @param type 店铺类型 0:旗舰店 ，1：代理店 2 分销店 3-联盟店4-普通店铺 5- 直营店
     */
    private void checkShopTypeToActivity(Integer type) {
        if (type == null){
            type = -1;//不知道啥类型店铺-为防止崩溃
        }
        switch (type){
//            case 3://是联盟店铺，不参加其他类型店铺活动（联盟不可以用红包了）
            case 5://是直营店铺，不参加其他类型店铺活动
                findRedBagDatas();
                break;
            default://其他类型店铺活动暂无区分
                if (dataList != null && dataList.size() > 0) {
                    checkGoods(dataList);
                }else {
                    dismiss();
                    showToast("购物车有商品暂不可售");
                    finish();
                }
                break;
        }
    }


    /**
     * 重新检查商品（是否是特推商品）
     * @param list
     */
    private void checkGoods(List<GoodsBean> list) {
        if (type == SHOP_MAIN){
            List<Long> ids = new ArrayList<>();
            for (int i = 0;i<list.size();i++){
                ids.add(list.get(i).getGoodId());
            }
            GoodDetailUploadBean uploadBean = new GoodDetailUploadBean();
            uploadBean.setGoodIds(ids);
            ShopNet.findGoodByIds(uploadBean, new HandleSuccess<GoodsDetailListBean>() {////////////////////
                @Override
                public void success(GoodsDetailListBean s) {
                    if (s.getData() != null && s.getData().size() > 0){
                        List<GoodsDetailBean> goods = s.getData();
                        int typeGood = 0;// 0默认 1全是特推   2全是普通   3既有特推也有普通
                        for (int i = 0;i < goods.size();i++){
                            if (goods.get(i).getIsRecommend() != null) {
                                if (goods.get(i).getIsRecommend() == 1) {//0不推荐  1推荐
                                    if (typeGood == 2) {
                                        typeGood = 3;
                                    } else if (typeGood == 3) {
                                        typeGood = 3;
                                    } else {
                                        typeGood = 1;
                                    }
                                }
                                if (goods.get(i).getIsRecommend() == 0) {
                                    if (typeGood == 1) {
                                        typeGood = 3;
                                    } else if (typeGood == 3) {
                                        typeGood = 3;
                                    } else {
                                        typeGood = 2;
                                    }
                                }
                            }else {
                                if (typeGood == 1) {
                                    typeGood = 3;
                                } else if (typeGood == 3) {
                                    typeGood = 3;
                                } else {
                                    typeGood = 2;
                                }
                            }
                        }
                        //如果全是特推商品 只能线上付款 不查优惠券默认无优惠券 （付款成功可自动获得优惠券）
                        //如果全是普通商品 查优惠券 记录将用的优惠券信息准备给服务器
                        //如果既有特推又有普通商品 提示用户特推商品不可与其他商品一起下单哦 关闭页面
                        switch (typeGood){
                            case 0://0默认
                                dismiss();
                                showToast("购物车有商品暂不可售");
                                ShopFoodOrderActivity.this.finish();
                                break;
                            case 1://1全是特推
                                RadioButton offline = (RadioButton) findViewById(R.id.shop_order_main_offline_rb);
                                offline.setVisibility(View.GONE);
                                request_coupons(false);
                                break;
                            case 2://2全是普通
                                request_coupons(true);
                                break;
                            case 3://3既有特推也有普通
                                dismiss();
                                showToast("特推商品暂不可与其他商品一同下单");
                                ShopFoodOrderActivity.this.finish();
                                break;
                            default:
                                break;
                        }

                    }else {
                        dismiss();
                        showToast("购物车有商品暂不可售");
                        ShopFoodOrderActivity.this.finish();
                    }
                }
            });
        }else if (type == FOOD_MAIN){


            dismiss();
        }
    }
    private boolean useableCoupon = false;//订单是否有可用优惠券  默认无可用
    private LinearLayout ll_order_coupons;//点击触发选择优惠券
    private TextView tv_coupon_name;//显示当前使用的优惠券名称
    private List<CouponsBean.DataBean> allCoupons = null;//所有可用优惠券
    private CouponsBean.DataBean useingCoupon = null;//正使用的优惠券
    private void request_coupons(boolean checkCoupons) {
        if (!checkCoupons){
            //无可用优惠券
            useableCoupon = false;
            dismiss();
        }else if (checkCoupons){
            IntrestBuyNet.queryStoreCouponByType(1, user.getId(),shopId, 1,new HandleSuccess<CouponsBean>() {
                @Override
                public void success(CouponsBean s) {
                    if (s.getData() != null && s.getData().size()>0){
                        allCoupons = s.getData();
                        useingCoupon = s.getData().get(0);
                        useableCoupon = true;
                        resetCouponViewDatas(useingCoupon,1);
                    }else {
                        useableCoupon = false;
                    }
                    dismiss();
                }
            });
        }
    }

    /**
     * 重置总价重置优惠券的views
     * @param useingCouponOrRedbag 当前正在使用的优惠券或红包
     */
    private void resetCouponViewDatas(CouponsBean.DataBean useingCouponOrRedbag,int tag) {
        if(type == FOOD_MAIN){
            switch (typeCC){
                case 1:     //堂吃
                    BigDecimal couponPrice = goodsSum;
                    if (useingCouponOrRedbag != null){
                        couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCouponOrRedbag.getCouponSum()));
                    }
                    if (couponPrice.doubleValue()<0d){
                        couponPrice = BigDecimal.valueOf(0d);
                    }
                    totalCount(couponPrice);
                    break;
                case 2:     //外卖
                    if(foodBean.getIsByself() == 1){        //配送  考虑起送费
                        BigDecimal couponPrice2 = goodsSum;
                        if (useingCouponOrRedbag != null){
                            couponPrice2 = (goodsSum.add(packSum)).subtract(BigDecimal.valueOf(useingCouponOrRedbag.getCouponSum()));
                        }else {
                            couponPrice2 = goodsSum.add(packSum);
                        }
                        if (couponPrice2.doubleValue()<0d){
                            couponPrice2 = BigDecimal.valueOf(0d);
                        }
                        totalCount(couponPrice2.add(sendSum));
                    }else if(foodBean.getIsByself() == 2){  //自取
                        BigDecimal couponPrice2 = goodsSum;
                        if (useingCouponOrRedbag != null){
                            couponPrice2 = (goodsSum.add(packSum)).subtract(BigDecimal.valueOf(useingCouponOrRedbag.getCouponSum()));
                        }else {
                            couponPrice2 = goodsSum.add(packSum);
                        }
                        if (couponPrice2.doubleValue()<0d){
                            couponPrice2 = BigDecimal.valueOf(0d);
                        }
                        totalCount(couponPrice2);
                    }
                    break;
            }
        } else if(type == SHOP_MAIN){
            if(shopBean.getByway() == 1 ) {         //配送
                BigDecimal couponPrice = goodsSum;
                if (useingCouponOrRedbag != null){
                    couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCouponOrRedbag.getCouponSum()));
                }
                if (couponPrice.doubleValue()<0d){
                    couponPrice = BigDecimal.valueOf(0d);
                }
                totalCount(couponPrice.add(sendSum));
            } else if(shopBean.getByway() == 2){    //自取
                BigDecimal couponPrice = goodsSum;
                if (useingCouponOrRedbag != null){
                    couponPrice = goodsSum.subtract(BigDecimal.valueOf(useingCouponOrRedbag.getCouponSum()));
                }
                if (couponPrice.doubleValue()<0d){
                    couponPrice = BigDecimal.valueOf(0d);
                }
                totalCount(couponPrice);
            }
        }
        //总价也要减掉优惠券
        //总价不要改，只显示总价减去优惠券后的金额
//        tv_coupon_name.setText("不使用优惠券");//手动选择不使用优惠
//        tv_coupon_name_redbag.setText(getResources().getString(R.string.giveup_useing_coupon_redbags));//手动选择不使用优惠
        switch (tag){
            case 1://用的是优惠券
                if (useingCouponOrRedbag != null) {
                    tv_coupon_name.setText(useingCouponOrRedbag.getStoreName() + useingCouponOrRedbag.getCouponSum() + "元代金券");//显示优惠金额
                }else {
                    tv_coupon_name.setText("不使用优惠券");//手动选择不使用优惠
                }
                break;
            case 2://用的是红包
                if (useingCouponOrRedbag != null) {
                    tv_coupon_name_redbag.setTextColor(getResources().getColor(R.color.lightred));
                    tv_coupon_name_redbag.setText("-￥"+ useingCouponOrRedbag.getCouponSum());//显示优惠金额
                }else {
                    tv_coupon_name_redbag.setText(getResources().getString(R.string.giveup_useing_coupon_redbags));//手动选择不使用优惠
                }
                break;
        }

    }

    /*
    关于红包的一些列操作
     */
    private boolean useableRedbag = false;//订单是否有可用红包  默认无可用
    private List<CouponsBean.DataBean> allRedbagsUINeeded = null;//所有可用红包新增用于显示的字段
    private List<CouponsBean.DataBean> allRedbags = null;//所有可用红包
    private void findRedBagDatas() {
        //查用户信息中的红包总量
        IntrestBuyNet.findUserInfo(user.getId(), new HandleSuccess<Login>() {
            @Override
            public void success(Login s) {
//                if (s.getStatus() == 1 && s.getData().getRedpackageAccount() != null){
//                    Double redbagSum = s.getData().getRedpackageAccount();
//                    if (redbagSum > 0d){
//                        //可用红包
//                        canUseRedbags = true;
//                        CouponsBean.DataBean bean = new CouponsBean.DataBean();
//                        bean.setId(-1L);
//                        bean.setCouponSum(redbagSum);
//                        allRedbagsUINeeded = new ArrayList<CouponsBean.DataBean>();//传参用 用于显示
//                        allRedbagsUINeeded.add(bean);
//                        //查商品 算出最多可用多少红包
//                        checkGoodsInfos(redbagSum);
//                    }
//                }

            }
        });

        //查拥有红包数量
        IntrestBuyNet.queryStoreCoupon(1L, user.getId(), 5, new HandleSuccess<CouponsBean>() {
            @Override
            public void success(CouponsBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    allRedbags = new ArrayList<CouponsBean.DataBean>();
                    for (int i = 0;i<s.getData().size();i++){
                        double cs = s.getData().get(i).getCouponSum();//优惠券金额
                        double gs;
                        if (typeCC == 2){//是外卖加打包费 是外卖就加打包费么 也不一定 万一packNum是0呢
                            gs = (goodsSum.add(packSum)).doubleValue();//商品总价
                        }else {//不是外卖不加打包费
                            gs = goodsSum.doubleValue();//商品总价
                        }
                        if (gs >= cs){          //如果商品金额大于等于优惠券金额 优惠券才可以用
                            allRedbags.add(s.getData().get(i));
                        }
                    }
                    if (allRedbags!=null && allRedbags.size() > 0){
                        useableRedbag = true;
                        tv_coupon_name_redbag.setTextColor(getResources().getColor(R.color.lightred));
                        tv_coupon_name_redbag.setText(allRedbags.size()+getResources().getString(R.string.have_coupon_redbags));
                    }
                }
                dismiss();
            }
        });
    }
    private void checkGoodsInfos(final Double redbagSum) {
        List<Long> ids = new ArrayList<>();
        for (int i = 0;i<dataList.size();i++){
            ids.add(dataList.get(i).getGoodId());
        }
        GoodDetailUploadBean uploadBean = new GoodDetailUploadBean();
        uploadBean.setGoodIds(ids);
        ShopNet.findGoodByIds(uploadBean, new HandleSuccess<GoodsDetailListBean>() {
            @Override
            public void success(GoodsDetailListBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    BigDecimal favourableSum = new BigDecimal(0d);
                    for (int i = 0;i < s.getData().size();i++){
                        GoodsDetailBean goodDB = s.getData().get(i);
                        long goodIdDB = goodDB.getId();
                        Long salecate = goodDB.getSalescategoryId();
                        if (salecate != null) {
                            if (salecate == 1L) {//直减红包
                                for (int j = 0; j < dataList.size(); j++) {
                                    long myGoodId = dataList.get(j).getGoodId();
                                    BigDecimal favourableOne = new BigDecimal(0d);
                                    if (goodIdDB == myGoodId) {
                                        List<GoodsDetailBean.GoodsSpecsBean> specs = goodDB.getGoodsSpecs();
                                        long myspec = dataList.get(j).getGoodsSpec();
                                        for (int k = 0; k < specs.size(); k++) {
                                            if (specs.get(k).getId() == myspec) {
                                                if (specs.get(k).getStockPrice() != null) {
                                                    favourableOne = specs.get(k).getStockPrice();
                                                }
                                            }
                                        }
                                    }
                                    favourableSum = favourableSum.add((favourableOne.multiply(BigDecimal.valueOf(dataList.get(j).getNumber()))));
                                }
                            }else if (salecate == 2L){//满减红包

                            }else if (salecate == 3L){//0元购

                            }else if (salecate == 4){//拼团购

                            }
                        }
                    }
                    if (redbagSum < favourableSum.doubleValue()){//有多少红包就可以用多少红包
                        CouponsBean.DataBean bean = new CouponsBean.DataBean();
                        bean.setId(-1L);
                        bean.setCouponSum(redbagSum);
                        allRedbags = new ArrayList<CouponsBean.DataBean>();
                        allRedbags.add(bean);//真实的 用于计算的
                    }else if (redbagSum >= favourableSum.doubleValue()){//只能用到优惠上线的红包金额
                        CouponsBean.DataBean bean = new CouponsBean.DataBean();
                        bean.setId(-1L);
                        bean.setCouponSum(favourableSum.doubleValue());
                        allRedbags = new ArrayList<CouponsBean.DataBean>();
                        allRedbags.add(bean);//真实的 用于计算的
                    }
                    if (allRedbags!=null && allRedbags.size() > 0 && allRedbags.get(0).getCouponSum().doubleValue() > 0d){
                        useableRedbag = true;
                        useingCoupon = allRedbags.get(0);
                        resetCouponViewDatas(useingCoupon, 2);
                    }
                }else {
                    showToa();
                    ShopFoodOrderActivity.this.finish();
                    return;
                }
                dismiss();
            }
        });
    }
}
