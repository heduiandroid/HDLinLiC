package com.linli.consumer.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by tomoyo on 2016/12/1.
 * 自定义viewpager，更改高度，将list的上拉加载更多显示出来
 *
 */

public class MyViewPager extends ViewPager {



    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //获取父布局，找到bottom_widget的位置，减去其位置，重写计算高度
        //如果父布局内容改变，这里需要进行更改，但不会出异常
        if(((ViewGroup)(this.getParent())).getChildCount()>2){
            heightMeasureSpec = heightMeasureSpec-((ViewGroup)(this.getParent())).getChildAt(2).getHeight();
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }


}
