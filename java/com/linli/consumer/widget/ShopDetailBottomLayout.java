package com.linli.consumer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linli.consumer.R;

import java.math.BigDecimal;

/**
 * Created by tomoyo on 2016/12/1.
 * 店铺底部布局，包含逻辑
 * 包含:
 *      购物车  （上拉购物车列表查看）
 *      总价
 *      递交
 *      座位号
 */

public class ShopDetailBottomLayout extends LinearLayout {

    private TextView tableTv;           //桌号
    private CarNumLayout carNumLayout;  //数量小圆点
    private TextView priceTv;           //总价
    private TextView commitTv;          //递交按钮
    private RelativeLayout cartRl;      //购物车按钮
    private TextView tv_can_send;//可配送金额显示

    public ShopDetailBottomLayout(Context context) {
        this(context,null);
    }

    public ShopDetailBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.shop_detail_bottom_widget,this);
        initView();
    }

    private void initView(){
        tableTv = (TextView)findViewById(R.id.shop_detail_bottom_tablenum_tv);
        carNumLayout = (CarNumLayout)findViewById(R.id.shop_detail_bottom_num_widget);
        priceTv = (TextView)findViewById(R.id.shop_detail_bottom_price_tv);
        commitTv = (TextView)findViewById(R.id.shop_detail_bottom_commit_tv);
        cartRl = (RelativeLayout)findViewById(R.id.shop_detail_bottom_cart_rl);
        cartRl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cartClickListener.onCart();
            }
        });
        tv_can_send = (TextView) findViewById(R.id.tv_can_send);
        commitTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commitClickListener != null ){
                    commitClickListener.onCommit();
                }
            }
        });
    }

    /**
     * 购物车图标点击事件回调
     * */
    public interface OnCartClickListener{
        void onCart();
    }
    private OnCartClickListener cartClickListener;

    public void setCartClickListener(OnCartClickListener cartClickListener) {
        this.cartClickListener = cartClickListener;
    }

    /**
     * 递交按钮点击事件回调
     * */
    public interface OnCommitClickListener{
        void onCommit();
    }
    private OnCommitClickListener commitClickListener;

    public void setCommitClickListener(OnCommitClickListener commitClickListener) {
        this.commitClickListener = commitClickListener;
    }


    /**
     * 设置购物车的物品数量
     * @param num  数量
     * */
    public void setCarNum(String num){
        carNumLayout.setVisibility(VISIBLE);
        carNumLayout.setMyNum(num);
    }
    /**
     * 设置购物车 数量小红点不显示
     * */
    public void setDis(){
        carNumLayout.setVisibility(INVISIBLE);
    }
    /**
     * 设置价格
     * @param price */
    public void setPrice(String price){
        priceTv.setText(price);

    }
    public void setPrice(double price){
        priceTv.setText("￥"+ BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
    }
    /*
    设置提交按钮不可点击
     */
    public void setSubmitButtonNotClick(){
        commitTv.setText("休息中");
        commitTv.setBackgroundResource(R.drawable.btn_gray_deep);
        commitTv.setOnClickListener(null);
    }
    /*
  设置显示可配送金额
   */
    public void setSendPrice(boolean visible,String sendPrice){
        tv_can_send.setVisibility(INVISIBLE);
        if (visible){
            tv_can_send.setVisibility(VISIBLE);
            tv_can_send.setText("满"+sendPrice+"元可提供配送");
        }else {
            tv_can_send.setVisibility(INVISIBLE);
        }
    }

    /**
     * 获取价格
     * */
    public String getPrice(){
        return priceTv.getText().toString().replace("￥","").trim();
    }

    /**
     * 设置桌号，在进店扫码后判断
     * 设置桌号显示，
     * 设置桌号
     * 设置提交文字
     * */
    public void setTable(String num){
        tableTv.setVisibility(VISIBLE);
        tableTv.setText("桌号:"+num);
        commitTv.setText("选好了");
    }

    /**
     * 暴露给activity
     * 用于起送费的配置
     * */
    public void setCommitState(String startPrice,boolean canCommit){
        if(canCommit){
            commitTv.setText("结算");
            commitTv.setBackgroundResource(R.mipmap.ic_shop_detail_bottom_bt_back);
            commitTv.setClickable(true);
        } else {
            commitTv.setBackgroundColor(getResources().getColor(R.color.gray_for_order_getcode));
            commitTv.setText(startPrice+"元 起送");
            commitTv.setClickable(false);
        }

    }


}
