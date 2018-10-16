package com.linli.consumer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;

/**
 * Created by tomoyo on 2016/12/9.
 */

public class ShopFoodOrderTitleWidget extends LinearLayout implements View.OnClickListener{

    private LinearLayout leftLl;
    private ImageView leftIm;
    private TextView leftTv;

    private LinearLayout rightLl;
    private ImageView rightIm;
    private TextView rightTv;

    private LinearLayout rightRightLl;
    private ImageView rightRightIm;
    private TextView rightRightTv;


    public ShopFoodOrderTitleWidget(Context context) {
        super(context);
    }

    public ShopFoodOrderTitleWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.shop_food_detail_order_title_widget,this);
        initView();
    }

    private void initView(){
        leftLl = (LinearLayout)findViewById(R.id.shop_food_detail_order_title_left_ll);
        leftIm = (ImageView)findViewById(R.id.shop_food_detail_order_title_left_im);
        leftTv = (TextView)findViewById(R.id.shop_food_detail_order_title_left_tv);

        rightLl = (LinearLayout)findViewById(R.id.shop_food_detail_order_title_right_ll);
        rightIm = (ImageView)findViewById(R.id.shop_food_detail_order_title_right_im);
        rightTv = (TextView)findViewById(R.id.shop_food_detail_order_title_right_tv);

        rightRightLl = (LinearLayout)findViewById(R.id.shop_food_detail_order_title_right_right_ll);
        rightRightIm = (ImageView)findViewById(R.id.shop_food_detail_order_title_right_right_im);
        rightRightTv = (TextView)findViewById(R.id.shop_food_detail_order_title_right_right_tv);

        leftLl.setOnClickListener(this);
        rightLl.setOnClickListener(this);
        rightRightLl.setOnClickListener(this);
        clickLeft();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_food_detail_order_title_left_ll:
                clickLeft();
                if(leftClickListener != null){
                    leftClickListener.onLeftClick();
                }
                break;
            case R.id.shop_food_detail_order_title_right_ll:
                clickRight();
                if(rightClickListener != null){
                    rightClickListener.onRightClick();
                }
                break;
            case R.id.shop_food_detail_order_title_right_right_ll:
                clickRR();
                if(rrClickListener != null ){
                    rrClickListener.onRRClick();
                }
        }
    }

    private void clickLeft(){
        leftIm.setImageResource(R.mipmap.ic_shop_detail_food_order_cked);
        leftTv.setTextColor(getResources().getColor(R.color.orange));
        leftLl.setBackgroundColor(getResources().getColor(R.color.white));

        rightIm.setImageResource(R.mipmap.ic_shop_detail_food_order_ck);
        rightTv.setTextColor(getResources().getColor(R.color.color_shop_sort_text_down));
        rightLl.setBackgroundColor(getResources().getColor(R.color.gray_outshop_bag));

        rightRightIm.setImageResource(R.mipmap.ic_shop_detail_food_order_ck);
        rightRightTv.setTextColor(getResources().getColor(R.color.color_shop_sort_text_down));
        rightRightLl.setBackgroundColor(getResources().getColor(R.color.gray_outshop_bag));
    }
    private void clickRight(){
        rightIm.setImageResource(R.mipmap.ic_shop_detail_food_order_cked);
        rightTv.setTextColor(getResources().getColor(R.color.orange));
        rightLl.setBackgroundColor(getResources().getColor(R.color.white));

        leftIm.setImageResource(R.mipmap.ic_shop_detail_food_order_ck);
        leftTv.setTextColor(getResources().getColor(R.color.color_shop_sort_text_down));
        leftLl.setBackgroundColor(getResources().getColor(R.color.gray_outshop_bag));

        rightRightIm.setImageResource(R.mipmap.ic_shop_detail_food_order_ck);
        rightRightTv.setTextColor(getResources().getColor(R.color.color_shop_sort_text_down));
        rightRightLl.setBackgroundColor(getResources().getColor(R.color.gray_outshop_bag));
    }
    private void clickRR(){

        rightRightIm.setImageResource(R.mipmap.ic_shop_detail_food_order_cked);
        rightRightTv.setTextColor(getResources().getColor(R.color.orange));
        rightRightLl.setBackgroundColor(getResources().getColor(R.color.white));

        rightIm.setImageResource(R.mipmap.ic_shop_detail_food_order_ck);
        rightTv.setTextColor(getResources().getColor(R.color.color_shop_sort_text_down));
        rightLl.setBackgroundColor(getResources().getColor(R.color.gray_outshop_bag));

        leftIm.setImageResource(R.mipmap.ic_shop_detail_food_order_ck);
        leftTv.setTextColor(getResources().getColor(R.color.color_shop_sort_text_down));
        leftLl.setBackgroundColor(getResources().getColor(R.color.gray_outshop_bag));
    }

    public interface OnLeftClickListener{
        void onLeftClick();
    }
    public interface OnRightClickListener{
        void onRightClick();
    }
    public interface OnRRClickListener{
        void onRRClick();
    }

    private OnLeftClickListener leftClickListener;

    private OnRightClickListener rightClickListener;

    private OnRRClickListener rrClickListener;

    public void setRightClickListener(OnRightClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }

    public void setLeftClickListener(OnLeftClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    public void setRrClickListener(OnRRClickListener rrClickListener) {
        this.rrClickListener = rrClickListener;
    }
}
