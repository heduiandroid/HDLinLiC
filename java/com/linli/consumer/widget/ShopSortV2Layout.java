package com.linli.consumer.widget;

import android.content.Context;
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

/**
 * Created by tomoyo on 2016/11/28.
 * 商品，美食 分类的布局，用于动态的插入
 * 通过里面的标记进行选择跳转
 * 此布局封装了部分跳转逻辑，本应该在UIHelper中，但是为了区分模块
 * see {@link UIHelper}
 */

public class ShopSortV2Layout extends LinearLayout implements View.OnClickListener {
    public static final int SHOP_MORE_HOT_SALE = 100 ;         //热卖爆款
    public static final int SHOP_MORE_BEST_GOODS = 101 ;          //精品专区
    public static final int SHOP_MORE_FULL_MINUTE = 102 ;          //满减专区

    public static final int SHOP_MAIN = 2;     //商城
    public static final int FOOD_MAIN = 1;     //美食
    public static final int SERVICE_MAIN = 3;  //服务

//商城和美食图标那一行view
    private LinearLayout ll_shopandfood;
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

    private Context context;


    private User user = AppContext.getInstance().getUser();

    public ShopSortV2Layout(Context context) {
        this(context,null);

    }
    public ShopSortV2Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.shop_main_direct_sort,this);
        initView();
        initRes();
        initClick();

    }



    /**
     * 初始化视图
     * */
    private void initView(){
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

        ll_shopandfood = (LinearLayout) findViewById(R.id.ll_shopandfood);
    }

    /**
     * 初始化图片资源和文字资源
     * */
    private void initRes(){
        //下方注释内容是本应该的写法 但与实际情况不符，暂时更改为其他链接
//        firstIm.setImageResource(R.mipmap.direct_best_goods);
//        secondIm.setImageResource(R.mipmap.direct_full_minute);
//        thirdIm.setImageResource(R.mipmap.direct_buy_together);
//        fourthIm.setImageResource(R.mipmap.direct_zero_buy);
//
//        firstTv.setText(R.string.food_main_sort_jingpin);
//        secondTv.setText(R.string.food_main_sort_manjian);
//        thirdTv.setText(R.string.food_main_sort_tuangou);
//        fourthTv.setText(R.string.food_main_sort_zerobuy);

        firstIm.setImageResource(R.mipmap.direct_best_goods);
        secondIm.setImageResource(R.mipmap.ic_shop_main_sort_flashsale);//改为热卖
        thirdIm.setImageResource(R.mipmap.ic_shop_main_sort_all);//改为更多
        fourthIm.setImageResource(R.mipmap.direct_zero_buy);

        firstTv.setText(R.string.food_main_sort_jingpin);
        secondTv.setText("爆款");//改为热卖
        thirdTv.setText("全部");//改为更多
        fourthTv.setText(R.string.food_main_sort_zerobuy);
    }

    /**
     * 初始化点击事件
     * */
    private void initClick(){
        firstLl.setOnClickListener(this);
        secondLl.setOnClickListener(this);
        thirdLl.setOnClickListener(this);
        fourthLl.setOnClickListener(this);
    }



    /**
     * 重写点击事件
     * 通过SORT分类
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_sort_findfood_ll://精品
                UIHelper.togoGoodsMoreV2Activity(context,1);
                break;
            case R.id.shop_main_sort_market_ll://满减
//                UIHelper.togoGoodsMoreV2Activity(context,2);//正常应该的写法 暂时更改
                UIHelper.togoGoodsMoreV2Activity(context,0);
                break;
            case R.id.shop_main_sort_recipe_ll://团购
//                UIHelper.togoGoodsMoreV2Activity(context,3);//正常应该的写法 暂时更改
                UIHelper.togoShopDetailActivity(context,999L,"精选专区",SHOP_MAIN);
                break;
            case R.id.shop_main_sort_health_ll://0元购
                UIHelper.togoGoodsMoreV2Activity(context,4);
                break;
            default:
                Toast.makeText(context,"The sort code is error",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
