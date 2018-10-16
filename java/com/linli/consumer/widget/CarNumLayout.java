package com.linli.consumer.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;

/**
 * Created by tomoyo on 2016/12/1.
 * 商品数量小圆点
 */

public class CarNumLayout extends LinearLayout {

    private TextView numTv;

    public CarNumLayout(Context context) {
        this(context,null);
    }

    public CarNumLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.goods_num_car_layout,this);
        initView();
    }

    private void initView(){
        numTv = (TextView)findViewById(R.id.car_num_layout_number_tv);
    }

    public void setMyNum(String num){
        if(!TextUtils.isEmpty(num)){
            numTv.setText(num);
        }
    }
}
