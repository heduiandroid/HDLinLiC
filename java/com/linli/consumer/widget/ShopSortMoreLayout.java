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

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE0;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE1;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Fashion;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Fresh;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_HOTSALE;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Life;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Live;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_QUALITY;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_health;

/**
 * Created by tomoyo on 2016/11/28.
 *
 */

public class ShopSortMoreLayout extends LinearLayout {

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


    public ShopSortMoreLayout(Context context,int sort) {
        this(context,null,sort);
        this.context = context;
    }

    public ShopSortMoreLayout(Context context, AttributeSet attrs,int sort) {
        super(context, attrs);
        SORT = sort;
        LayoutInflater.from(context).inflate(R.layout.shop_main_sort_more,this);
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

    //益招鲜   惠生活  时尚流   雅居馆   养生堂   找美食
    private void initRes(){
        switch (SORT){
            case SHOP_MORE_MAIN_QUALITY:
                view_line.setBackgroundColor(getResources().getColor(R.color.white));
                iv_ad_pic.setVisibility(VISIBLE);
                rl_shopcate.setVisibility(GONE);
                iv_ad_pic.setImageResource(R.mipmap.temple1_top);
                iv_ad_pic.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UIHelper.togoMoreV2Activity(context,SHOP_MORE_MAIN_QUALITY);
                    }
                });
                break;
            case SHOP_MORE_MAIN_HOTSALE:
                view_line.setBackgroundColor(getResources().getColor(R.color.white));
                iv_ad_pic.setVisibility(VISIBLE);
                rl_shopcate.setVisibility(GONE);
                iv_ad_pic.setImageResource(R.mipmap.temple2_top);
                iv_ad_pic.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UIHelper.togoMoreV2Activity(context,SHOP_MORE_MAIN_HOTSALE);
                    }
                });
                break;
            case SHOP_MORE_MAIN_Fresh:
//                nameTv.setText(getResources().getString(R.string.shop_more_fresh));
                iv_background.setImageResource(R.mipmap.cate_temple1);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoMoreActivity(SHOP_MORE_MAIN_Fresh);
                    }
                });
                break;
            case SHOP_MORE_MAIN_Life:
//                nameTv.setText(getResources().getString(R.string.shop_more_life));
                iv_background.setImageResource(R.mipmap.cate_temple2);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoMoreActivity(SHOP_MORE_MAIN_Life);
                    }
                });
                break;
            case SHOP_MORE_MAIN_Fashion:
//                nameTv.setText(getResources().getString(R.string.shop_more_fashion));
                iv_background.setImageResource(R.mipmap.cate_temple3);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoMoreActivity(SHOP_MORE_MAIN_Fashion);
                    }
                });
                break;
            case SHOP_MORE_MAIN_Live:
//                nameTv.setText(getResources().getString(R.string.shop_more_live));
                iv_background.setImageResource(R.mipmap.cate_temple4);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoMoreActivity(SHOP_MORE_MAIN_Live);
                    }
                });
                break;
            case SHOP_MORE_MAIN_health:
//                nameTv.setText(getResources().getString(R.string.shop_more_health));
                iv_background.setImageResource(R.mipmap.cate_temple5);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoMoreActivity(SHOP_MORE_MAIN_health);
                    }
                });
                break;
            case FOOD_MAIN_FINDFOOD:
                nameTv.setText("附近美食");
                tv_more.setTextColor(getResources().getColor(R.color.gray));
                iv_more.setBackgroundResource(R.mipmap.ic_shop_main_sort_enter);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoSortActivity(FOOD_MAIN_FINDFOOD);
                    }
                });
                break;
            case SERVICE_MAIN_FINDSERVICE1:
                nameTv.setText("附近服务");
                tv_more.setTextColor(getResources().getColor(R.color.gray));
                iv_more.setBackgroundResource(R.mipmap.ic_shop_main_sort_enter);
                moreLl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        togoMoreActivity(SERVICE_MAIN_FINDSERVICE0);
                    }
                });
                break;
                //view.setVisibility(GONE);
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
