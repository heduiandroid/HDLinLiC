package com.linli.consumer.myview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.linli.consumer.R;


/**
 * Created by wwy on 2016/5/24.
 */
public class SmartSortPopupWindow extends PopupWindow implements View.OnClickListener {
    private RelativeLayout rl_smart_sort_default;
    private RelativeLayout rlHasGood;
    private RelativeLayout rlHsaHouse;
    private View view;
    private ImageView mImageViewHasGood;
    private ImageView mImageViewHasHouse;
    private ImageView mImageViewDefault;
    private View.OnClickListener mOnClickListener;
    private int flag = 0;//此处的flag 用于判断该筛选结果

    public SmartSortPopupWindow(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public SmartSortPopupWindow(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.popup_smart_sort, null);
        rl_smart_sort_default = (RelativeLayout) view.findViewById(R.id.rl_smart_sort_default);
        rlHasGood = (RelativeLayout) view.findViewById(R.id.rl_smart_sort_good);
        rlHsaHouse = (RelativeLayout) view.findViewById(R.id.rl_smart_sort_house);
        mImageViewHasGood = (ImageView) view.findViewById(R.id.iv_popup_hasgood);
        mImageViewHasHouse = (ImageView) view.findViewById(R.id.iv_popup_hashouse);
        mImageViewDefault = (ImageView) view.findViewById(R.id.iv_popup_default);
        mImageViewDefault.setVisibility(View.VISIBLE);
        mImageViewHasGood.setVisibility(View.INVISIBLE);
        mImageViewHasHouse.setVisibility(View.INVISIBLE);//有优惠和有包房默认为隐藏
        // 设置PopupWindow的View
        this.setContentView(view);
        // 设置PopupWindow弹出窗体的宽
        this.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        // 设置PopupWindow弹出窗体的高
        this.setHeight(android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimTop);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        rlHasGood.setOnClickListener(this);
        rlHsaHouse.setOnClickListener(this);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // int height = view.findViewById(R.id.ll_smart_food_all).getHeight();
                //;int y = (int) event.getY();
                //MotionEvent_up为单点触摸离开模式，用户点击屏幕中的位置，则弹出框消失
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    dismiss();

                }
                return true;

            }
        });


    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    public void close() {
        this.dismiss();
    }

    /*
       此处方法用于返回当前的筛选选中标志
     */
    public int getFlag() {

        return flag;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_smart_sort_good) {
            mImageViewHasGood.setVisibility(View.VISIBLE);
            mImageViewHasHouse.setVisibility(View.INVISIBLE);
            mImageViewDefault.setVisibility(View.INVISIBLE);


        }
        if (view.getId() == R.id.rl_smart_sort_house) {
            mImageViewHasGood.setVisibility(View.INVISIBLE);
            mImageViewHasHouse.setVisibility(View.VISIBLE);
            mImageViewDefault.setVisibility(View.INVISIBLE);


        }
        if (view.getId() == R.id.rl_smart_sort_default) {
            mImageViewHasGood.setVisibility(View.INVISIBLE);
            mImageViewHasHouse.setVisibility(View.INVISIBLE);
            mImageViewDefault.setVisibility(View.VISIBLE);


        }

    }
}
