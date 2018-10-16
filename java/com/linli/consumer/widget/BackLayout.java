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
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.utils.CommonUtil;

/**
 * Created by tomoyo on 2016/11/28.
 *
 * 店铺分类返回布局
 *
 */

public class BackLayout extends LinearLayout {

    private ImageView backIm;
    private TextView backTv;
    private ImageView searchIm;

    private int whi = 0 ;


    public BackLayout(Context context) {
        super(context);
    }

    public BackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.back_layout,this);
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
                if(whi == 0 ){
                    UIHelper.togoSearchActivity(context,1);     //跳转到美食搜索
                } else if(whi == 1){
                    UIHelper.togoFoodRecipeSearchActivity(context);     //跳转到食谱搜索
                } else if(whi == 2){
                    UIHelper.togoSearchActivity(context,2);     //跳转到商城搜索
                }

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
