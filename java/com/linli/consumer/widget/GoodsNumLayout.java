package com.linli.consumer.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;

/**
 * Created by tomoyo on 2016/12/1.
 * 商品数量小圆点
 */

public class GoodsNumLayout extends LinearLayout {

    public static final int GOODS_NUM = 91;
    public static final int CAR_NUM = 92;

    private TextView numTv;
    private ImageView backIm;

    public GoodsNumLayout(Context context) {
        this(context,null);
    }

    public GoodsNumLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.goods_num_layout,this);
        initView();
    }

    private void initView(){
        numTv = (TextView)findViewById(R.id.goods_num_layout_number_tv);
        backIm = (ImageView)findViewById(R.id.goods_num_layout_back_im);
    }

    //TODO 为什么重写加载就会位置改变

    /*public void setMyView(int type){
        switch (type){
            case GOODS_NUM:
                //backIm.setImageResource(R.mipmap.ic_goods_num_background);
                backIm.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_goods_num_background));
                numTv.setTextSize(R.dimen.shop_detail_goods_dp);
                break;
            case CAR_NUM:
                //backIm.setImageResource(R.mipmap.ic_shop_detail_card_num_backgroud);
                backIm.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_shop_detail_card_num_backgroud));
                numTv.setTextSize(R.dimen.shop_detail_car_dp);
                break;
        }
    }*/

    public void setMyNum(String num){
        if(!TextUtils.isEmpty(num)){
            numTv.setText(num);
        }
    }
}
