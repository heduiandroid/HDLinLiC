package com.linli.consumer.widget;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.domain.User;
import com.linli.consumer.ui.shop_v2.ShopMoreActivity;
import com.linli.consumer.ui.shop_v2.ShopSortActivity;

/**
 * Created by tomoyo on 2016/11/28.
 * 商品，美食 分类的布局，用于动态的插入
 * 通过里面的标记进行选择跳转
 * 此布局封装了部分跳转逻辑，本应该在UIHelper中，但是为了区分模块
 * see {@link UIHelper}
 */

public class CafeShopMoreLayout extends LinearLayout implements View.OnClickListener {
    public static final int SHOP_RECOMMEND = 1;     //商城


    public static final int SHOP_MAIN = 2;     //商城
    public static final int FOOD_MAIN = 1;     //美食
    public static final int SERVICE_MAIN = 3;  //服务


    public static final int SHOP_MAIN_WELLCHOICE = 21;     //精选店铺
    public static final int SHOP_MAIN_COUPON = 22;         //优惠券
    public static final int SHOP_MAIN_FLASHSALE = 23;      //促销商品
    public static final int SHOP_MAIN_ALL = 24;            //全部

    public static final int FOOD_MAIN_FINDFOOD = 11;   //找美食
    public static final int FOOD_MAIN_MARKET = 12;     //逛市场
    public static final int FOOD_MAIN_RECIPE = 13;     //食靠谱
    public static final int FOOD_MAIN_HEALTH = 14;     //养生道

    public static final int SHOP_MORE_MAIN_Fresh = 31 ;         //益找鲜  逛市场
    public static final int SHOP_MORE_MAIN_Life = 32 ;          //惠生活
    public static final int SHOP_MORE_MAIN_Fashion = 33 ;       //时尚流
    public static final int SHOP_MORE_MAIN_Live = 34 ;          //雅居馆
    public static final int SHOP_MORE_MAIN_health = 35 ;        //养生堂

    public static final int SHOP_MORE_MAIN_QUALITY = 36 ;        //新增精选商品
    public static final int SHOP_MORE_MAIN_HOTSALE = 37 ;        //新增热卖商品

    public static final int SERVICE_MAIN_FINDSERVICE1 = 41 ;//美容休闲
    public static final int SERVICE_MAIN_FINDSERVICE2 = 42 ;//医疗健康
    public static final int SERVICE_MAIN_FINDSERVICE3 = 43 ;//家政维修
    public static final int SERVICE_MAIN_FINDSERVICE4 = 44 ;//快递物流
    public static final int SERVICE_MAIN_FINDSERVICE5 = 45 ;//缴费充值
    public static final int SERVICE_MAIN_FINDSERVICE6 = 46 ;//法律保险
    public static final int SERVICE_MAIN_FINDSERVICE7 = 47 ;//地产装修
    public static final int SERVICE_MAIN_FINDSERVICE8 = 48 ;//婚庆摄影
    public static final int SERVICE_MAIN_FINDSERVICE9 = 49 ;//广告印刷
    public static final int SERVICE_MAIN_FINDSERVICE10 = 50 ;//全部
    public static final int SERVICE_MAIN_FINDSERVICE0 = 51 ;//全部

    public static final int SHOP_FOOD_REDBAG = 52 ;//全部
    //关于服务views
    private LinearLayout ll_service;
    private LinearLayout ll_service_beauty_leisure,ll_service_yiliaojiankang,ll_service_home_maintenance,ll_service_express_logistics
            ,ll_service_zhusulvyou,ll_service_legal_insurance,ll_service_realestate_decoration,ll_service_wedding_photography
            ,ll_service_advertising_printing,ll_service_teach_training;
    private ImageView iv_service_beauty_leisure,iv_service_yiliaojiankang,iv_service_home_maintenance,iv_service_express_logistics
            ,iv_service_zhusulvyou,iv_service_legal_insurance,iv_service_realestate_decoration,iv_service_wedding_photography
            ,iv_service_advertising_printing,iv_service_teach_training;
    private TextView tv_service_beauty_leisure,tv_service_yiliaojiankang,tv_service_home_maintenance,tv_service_express_logistics
            ,tv_service_zhusulvyou,tv_service_legal_insurance,tv_service_realestate_decoration,tv_service_wedding_photography
            ,tv_service_advertising_printing,tv_service_teach_training;


//商城和美食图标那一行view
    private LinearLayout ll_shopandfood;


    //广播文字
    private MarqueeTextView broadcast;

    //外层布局
    private LinearLayout firstLl;
    private LinearLayout secondLl;
    private LinearLayout thirdLl;
    private LinearLayout fourthLl;

    //图片
    private ImageView firstIm;
    private ImageView secondIm;
    private ImageView thirdIm;
    private ImageView fourthIm;

    //文字
    private TextView firstTv;
    private TextView secondTv;
    private TextView thirdTv;
    private TextView fourthTv;

    private int SORT = 0;           //大分类  food or goods

    private Context context;

    private String broadcastStr = "";

    private User user = AppContext.getInstance().getUser();

    public CafeShopMoreLayout(Context context,String broadcast) {
        this(context,null,broadcast);

    }
    public CafeShopMoreLayout(Context context, AttributeSet attrs, String broadcast) {
        super(context, attrs);
        this.context = context;
        this.broadcastStr = broadcast;
        LayoutInflater.from(context).inflate(R.layout.shop_main_sort,this);
        initView(SORT);
        initRes(SORT);
        initClick(SORT);

    }



    /**
     * 初始化视图
     * */
    private void initView( int sort){
        firstLl = (LinearLayout)findViewById(R.id.shop_main_sort_findfood_ll);
        secondLl = (LinearLayout)findViewById(R.id.shop_main_sort_market_ll);
        thirdLl = (LinearLayout)findViewById(R.id.shop_main_sort_recipe_ll);
        fourthLl = (LinearLayout)findViewById(R.id.shop_main_sort_health_ll);

        firstIm = (ImageView)findViewById(R.id.shop_main_sort_findfood_im);
        secondIm = (ImageView)findViewById(R.id.shop_main_sort_market_im);
        thirdIm = (ImageView)findViewById(R.id.shop_main_sort_recipe_im);
        fourthIm = (ImageView)findViewById(R.id.shop_main_sort_health_im);

        firstTv = (TextView)findViewById(R.id.shop_main_sort_findfood_tv);
        secondTv = (TextView)findViewById(R.id.shop_main_sort_market_tv);
        thirdTv = (TextView)findViewById(R.id.shop_main_sort_recipe_tv);
        fourthTv = (TextView)findViewById(R.id.shop_main_sort_health_tv);

        broadcast = (MarqueeTextView)findViewById(R.id.shop_main_sort_broadcast_tv);
        //关于服务
        ll_service  = (LinearLayout) findViewById(R.id.ll_service);
        ll_shopandfood = (LinearLayout) findViewById(R.id.ll_shopandfood);
        if (sort == SERVICE_MAIN){
            ll_service_beauty_leisure = (LinearLayout) findViewById(R.id.ll_service_beauty_leisure);
            ll_service_yiliaojiankang = (LinearLayout) findViewById(R.id.ll_service_yiliaojiankang);
            ll_service_home_maintenance = (LinearLayout) findViewById(R.id.ll_service_home_maintenance);
            ll_service_express_logistics = (LinearLayout) findViewById(R.id.ll_service_express_logistics);
            ll_service_zhusulvyou = (LinearLayout) findViewById(R.id.ll_service_zhusulvyou);
            ll_service_legal_insurance = (LinearLayout) findViewById(R.id.ll_service_legal_insurance);
            ll_service_realestate_decoration = (LinearLayout) findViewById(R.id.ll_service_realestate_decoration);
            ll_service_wedding_photography = (LinearLayout) findViewById(R.id.ll_service_wedding_photography);
            ll_service_advertising_printing = (LinearLayout) findViewById(R.id.ll_service_advertising_printing);
            ll_service_teach_training = (LinearLayout) findViewById(R.id.ll_service_teach_training);

            iv_service_beauty_leisure = (ImageView) findViewById(R.id.iv_service_beauty_leisure);
            iv_service_yiliaojiankang = (ImageView) findViewById(R.id.iv_service_yiliaojiankang);
            iv_service_home_maintenance = (ImageView) findViewById(R.id.iv_service_home_maintenance);
            iv_service_express_logistics = (ImageView) findViewById(R.id.iv_service_express_logistics);
            iv_service_zhusulvyou = (ImageView) findViewById(R.id.iv_service_zhusulvyou);
            iv_service_legal_insurance = (ImageView) findViewById(R.id.iv_service_legal_insurance);
            iv_service_realestate_decoration = (ImageView) findViewById(R.id.iv_service_realestate_decoration);
            iv_service_wedding_photography = (ImageView) findViewById(R.id.iv_service_wedding_photography);
            iv_service_advertising_printing = (ImageView) findViewById(R.id.iv_service_advertising_printing);
            iv_service_teach_training = (ImageView) findViewById(R.id.iv_service_teach_training);

            tv_service_beauty_leisure = (TextView) findViewById(R.id.tv_service_beauty_leisure);
            tv_service_yiliaojiankang = (TextView) findViewById(R.id.tv_service_yiliaojiankang);
            tv_service_home_maintenance = (TextView) findViewById(R.id.tv_service_home_maintenance);
            tv_service_express_logistics = (TextView) findViewById(R.id.tv_service_express_logistics);
            tv_service_zhusulvyou = (TextView) findViewById(R.id.tv_service_zhusulvyou);
            tv_service_legal_insurance = (TextView) findViewById(R.id.tv_service_legal_insurance);
            tv_service_realestate_decoration = (TextView) findViewById(R.id.tv_service_realestate_decoration);
            tv_service_wedding_photography = (TextView) findViewById(R.id.tv_service_wedding_photography);
            tv_service_advertising_printing = (TextView) findViewById(R.id.tv_service_advertising_printing);
            tv_service_teach_training = (TextView) findViewById(R.id.tv_service_teach_training);
        }
    }

    /**
     * 初始化图片资源和文字资源
     * @param sort 详细分类
     * */
    private void initRes(int sort){
        switch (sort){
            case FOOD_MAIN:
                firstIm.setImageResource(R.mipmap.ic_food_main_sort_findfood);
                secondIm.setImageResource(R.mipmap.ic_food_main_sort_market);
                thirdIm.setImageResource(R.mipmap.ic_food_main_sort_recipe);
                fourthIm.setImageResource(R.mipmap.ic_food_main_sort_health);

                firstTv.setText(R.string.food_main_sort_findfood_str);
                secondTv.setText(R.string.food_main_sort_market_str);
                thirdTv.setText(R.string.food_main_sort_recipe_str);
                fourthTv.setText(R.string.food_main_sort_health_str);
                break;
            case SHOP_MAIN:
                firstIm.setImageResource(R.mipmap.ic_shop_main_sort_wellchoice);
                secondIm.setImageResource(R.mipmap.ic_shop_main_sort_coupon);
                thirdIm.setImageResource(R.mipmap.ic_shop_main_sort_flashsale);
                fourthIm.setImageResource(R.mipmap.ic_shop_main_sort_all);

                firstTv.setText(R.string.shop_main_sort_nearby_shop);
                secondTv.setText(R.string.shop_main_sort_coupon_str);
                thirdTv.setText(R.string.shop_main_sort_flashsale_str);
                fourthTv.setText(R.string.shop_main_sort_all_str);

                break;
            case SERVICE_MAIN:
                ll_shopandfood.setVisibility(GONE);
                ll_service.setVisibility(VISIBLE);
                iv_service_beauty_leisure.setImageResource(R.mipmap.icon_service_beauty_leisure);
                iv_service_yiliaojiankang.setImageResource(R.mipmap.icon_service_yiliaojiankang);
                iv_service_home_maintenance.setImageResource(R.mipmap.icon_service_home_maintenance);
                iv_service_express_logistics.setImageResource(R.mipmap.icon_service_express_logistics);
                iv_service_zhusulvyou.setImageResource(R.mipmap.icon_service_zhusulvyou);
                iv_service_legal_insurance.setImageResource(R.mipmap.icon_service_legal_insurance);
                iv_service_realestate_decoration.setImageResource(R.mipmap.icon_service_realestate_decoration);
                iv_service_wedding_photography.setImageResource(R.mipmap.icon_service_wedding_photography);
                iv_service_advertising_printing.setImageResource(R.mipmap.icon_service_advertising_printing);
                iv_service_teach_training.setImageResource(R.mipmap.icon_service_teach_training);

                tv_service_beauty_leisure.setText(R.string.service_cate1);
                tv_service_yiliaojiankang.setText(R.string.service_cate2);
                tv_service_home_maintenance.setText(R.string.service_cate3);
                tv_service_express_logistics.setText(R.string.service_cate4);
                tv_service_zhusulvyou.setText(R.string.service_cate5);
                tv_service_legal_insurance.setText(R.string.service_cate6);
                tv_service_realestate_decoration.setText(R.string.service_cate7);
                tv_service_wedding_photography.setText(R.string.service_cate8);
                tv_service_advertising_printing.setText(R.string.service_cate9);
                tv_service_teach_training.setText(R.string.service_cate10);
                break;
        }
        if(!TextUtils.isEmpty(broadcastStr)){
            broadcast.setText(broadcastStr);
        }


    }
    /**
     * 设置公告
     * */
    public void setBroadcast(String broadcastStr){
        if(!TextUtils.isEmpty(broadcastStr)){
            broadcast.setText(broadcastStr);
        }
    }

    /**
     * 初始化点击事件
     * */
    private void initClick(int sort){
        if (sort == SERVICE_MAIN){//关于服务类型的点击事件
            ll_service_beauty_leisure.setOnClickListener(this);
            ll_service_yiliaojiankang.setOnClickListener(this);
            ll_service_home_maintenance.setOnClickListener(this);
            ll_service_express_logistics.setOnClickListener(this);
            ll_service_zhusulvyou.setOnClickListener(this);
            ll_service_legal_insurance.setOnClickListener(this);
            ll_service_realestate_decoration.setOnClickListener(this);
            ll_service_wedding_photography.setOnClickListener(this);
            ll_service_advertising_printing.setOnClickListener(this);
            ll_service_teach_training.setOnClickListener(this);
        }else {
            firstLl.setOnClickListener(this);
            secondLl.setOnClickListener(this);
            thirdLl.setOnClickListener(this);
            fourthLl.setOnClickListener(this);
        }
    }



    /**
     * 重写点击事件
     * 通过SORT分类
     * */
    @Override
    public void onClick(View v) {

        switch (SORT){
            case FOOD_MAIN:
                switch (v.getId()){
                    case R.id.shop_main_sort_findfood_ll:
                        togoSortActivity(FOOD_MAIN_FINDFOOD);   //找美食
                        break;
                    case R.id.shop_main_sort_market_ll:
                        togoMoreActivity(FOOD_MAIN_MARKET);     //用这个，要修改标题 逛市场
                        break;
                    case R.id.shop_main_sort_recipe_ll:
                        togoSortActivity(FOOD_MAIN_RECIPE);         //食靠谱
                        break;
                    case R.id.shop_main_sort_health_ll:
                        //togoMoreActivity(FOOD_MAIN_HEALTH);         //养身膳
                        UIHelper.togoHealth(context);
                        break;
                    default:
                        Toast.makeText(context,"The sort code is error",Toast.LENGTH_SHORT).show();
                }
                break;
            case SHOP_MAIN:
                switch (v.getId()){
                    case R.id.shop_main_sort_findfood_ll:
                        togoSortActivity(SHOP_MAIN_ALL);
                        break;
                    case R.id.shop_main_sort_market_ll:
                        if(user != null){
                            UIHelper.togoMyCouponActivity(context);
                        } else {
                            UIHelper.togoLoginActivity(context);
                        }
                        //togoSortActivity(SHOP_MAIN_COUPON);         //优惠券
                        break;
                    case R.id.shop_main_sort_recipe_ll:
                        UIHelper.togoMoreV2Activity(context,SHOP_MORE_MAIN_HOTSALE);//热卖商品
//                        togoMoreActivity(SHOP_MAIN_FLASHSALE);
                        break;
                    case R.id.shop_main_sort_health_ll:             //全部
                        togoSortActivity(SHOP_MAIN_ALL);
                        break;
                    default:
                        Toast.makeText(context,"The sort code is error",Toast.LENGTH_SHORT).show();
                }
                break;
            case SERVICE_MAIN:
                switch (v.getId()){
                    case R.id.ll_service_beauty_leisure:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE1);
                        break;
                    case R.id.ll_service_yiliaojiankang:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE2);
                        break;
                    case R.id.ll_service_home_maintenance:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE3);
                        break;
                    case R.id.ll_service_express_logistics:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE4);
                        break;
                    case R.id.ll_service_zhusulvyou:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE5);
                        break;
                    case R.id.ll_service_legal_insurance:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE6);
                        break;
                    case R.id.ll_service_realestate_decoration:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE7);
                        break;
                    case R.id.ll_service_wedding_photography:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE8);
                        break;
                    case R.id.ll_service_advertising_printing:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE9);
                        break;
                    case R.id.ll_service_teach_training:
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE10);
                        break;
                    default:
                        break;
                }
                break;
        }


    }

    /**
     * 点击事件跳转
     * @param sort 分类( 小分类 )
     * */
    private void togoSortActivity(int sort){
        Intent intent = new Intent(context, ShopSortActivity.class);
        intent.putExtra("Sort",sort);
        context.startActivity(intent);
    }

    /**
     * 跳转到更多界面，列表为单个
     * */
    private void togoMoreActivity(int sort ){
        Intent intent = new Intent(context, ShopMoreActivity.class);
        intent.putExtra("Sort",sort);
        context.startActivity(intent);
    }

    /**
     * 在activity中更新user的状态
     * */
    public void updateUser(){
        user = AppContext.getInstance().getUser();
    }
}
