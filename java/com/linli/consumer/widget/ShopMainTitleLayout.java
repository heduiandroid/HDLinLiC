package com.linli.consumer.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linli.consumer.R;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/27.
 */
@Deprecated
public class ShopMainTitleLayout extends LinearLayout implements View.OnClickListener{

    private ImageView backIm;
    private ImageView searchIm;
    private TextView titleTv;
    private CarNumLayout carNumLayout;  //数量小圆点
    private RelativeLayout cartRl;      //购物车按钮

    private Context context;


    public ShopMainTitleLayout(Context context) {
        this(context,null);
        this.context = context;
    }

    public ShopMainTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.shop_main_title,this);
        initView();
    }

    private void initView(){
        backIm = (ImageView) findViewById(R.id.shop_main_title_back);
        searchIm = (ImageView)findViewById(R.id.shop_main_title_search_im);
        titleTv = (TextView)findViewById(R.id.shop_main_title_name_tv);
        carNumLayout = (CarNumLayout)findViewById(R.id.shop_main_title_num_widget);
        cartRl = (RelativeLayout)findViewById(R.id.shop_detail_bottom_cart_rl);
        backIm.setOnClickListener(this);
        searchIm.setOnClickListener(this);
        cartRl.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_title_back:
                ((Activity)context).finish();
                break;
            case R.id.shop_main_title_search_im:
                break;
        }
    }

    /**
     * 设置title的名字
     * @param type 类型
     * */
    public void setName(int type){
        switch (type){
            case FOOD_MAIN:
                titleTv.setText("美食");
                break;
            case SHOP_MAIN:
                titleTv.setText("商城");
                break;
            default:
                titleTv.setText("商城");
                break;
        }
    }
}
