package com.linli.consumer.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.ui.shop_v2.ShopMoreActivity;
import com.linli.consumer.ui.shop_v2.ShopSortActivity;

import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_BEST_GOODS;
import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_FULL_MINUTE;
import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_HOT_SALE;

/**
 * Created by tomoyo on 2016/11/28.
 *
 */

public class ShopSortDirectMoreLayout extends LinearLayout {

    private static final int SHOP_MAIN = 1;     //商城
    private static final int FOOD_MAIN = 2;     //美食

    private TextView nameTv;
    private LinearLayout moreLl;
    private ImageView iv_background;
    private TextView tv_more;
    private ImageView iv_more;
    private ImageView iv_ad_pic;
    private RelativeLayout rl_shopcate;
    private View view_line;
    //private View view;

    private Context context;

    private int SORT = 0;       //小分类


    public ShopSortDirectMoreLayout(Context context, int sort) {
        this(context,null,sort);
        this.context = context;
    }

    public ShopSortDirectMoreLayout(Context context, AttributeSet attrs, int sort) {
        super(context, attrs);
        SORT = sort;
        LayoutInflater.from(context).inflate(R.layout.shop_main_sort_direct_more,this);
        initView();
    }

    private void initView(){
        iv_background = (ImageView) findViewById(R.id.iv_background);
        nameTv = (TextView)findViewById(R.id.shop_main_sort_more_name_tv);
        moreLl = (LinearLayout)findViewById(R.id.shop_main_sort_more_ll);
        tv_more = (TextView) findViewById(R.id.tv_more);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_ad_pic = (ImageView) findViewById(R.id.iv_ad_pic);
        rl_shopcate = (RelativeLayout) findViewById(R.id.rl_shopcate);
        view_line = findViewById(R.id.view_line);
        //view = (View)findViewById(R.id.shop_main_sort_more_view);
        initRes();
    }

    //热卖爆款 精品专区 满建专区
    private void initRes(){
        switch (SORT){
            case SHOP_MORE_HOT_SALE:
                view_line.setBackgroundColor(getResources().getColor(R.color.white));
                iv_ad_pic.setVisibility(VISIBLE);
                rl_shopcate.setVisibility(GONE);
                iv_ad_pic.setImageResource(R.mipmap.bg_direct_hotsale);
                iv_ad_pic.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UIHelper.togoGoodsMoreV2Activity(context,0);
                    }
                });
                break;
            case SHOP_MORE_BEST_GOODS:
                iv_background.setImageResource(R.mipmap.bg_direct_best_goods);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoGoodsMoreV2Activity(context,1);

                    }
                });
                break;
            case SHOP_MORE_FULL_MINUTE:
//                nameTv.setText(getResources().getString(R.string.shop_more_fresh));
                iv_background.setImageResource(R.mipmap.bg_direct_full_minute);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoGoodsMoreV2Activity(context,2);

                    }
                });
                break;
        }
    }



    /**
     * 点击事件跳转
     * @param sort 分类
     * */
    private void togoSortActivity(int sort){
        Intent intent = new Intent(context, ShopSortActivity.class);
        intent.putExtra("Sort",sort);
        context.startActivity(intent);
    }

    private void togoMoreActivity(int sort ){
        Intent intent = new Intent(context, ShopMoreActivity.class);
        intent.putExtra("Sort",sort);
        context.startActivity(intent);
    }
}
