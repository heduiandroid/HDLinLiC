package com.linli.consumer.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.utils.CommonUtil;

/**
 * Created by tomoyo on 2016/11/28.
 *
 * 店铺分类返回布局
 *
 */

public class BackLayoutDirect extends LinearLayout {

    private ImageView backIm;
    private TextView backTv;
    private ImageView searchIm;

    private int whi = 0 ;


    public BackLayoutDirect(Context context) {
        super(context);
    }

    public BackLayoutDirect(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.back_direct_layout,this);
        initView(context);
    }

    private void initView(final Context context){
        backIm = (ImageView)findViewById(R.id.back_im);
        backTv = (TextView)findViewById(R.id.back_tv);
        searchIm = (ImageView)findViewById(R.id.back_search_im);
        backTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.scanForActivity(context).finish();
            }
        });
        backIm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.scanForActivity(context).finish();
            }
        });
        searchIm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               //去直营购物车

            }
        });

    }


    public void setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            backTv.setText(title);
        }
    }
    /**
     * 这个提供给食靠谱进行搜索跳转重定向
     * */
    public void setToSearch(){
        whi = 1;
    }

    public void setToSearchShop(){
        whi = 2;
    }

}
