package com.linli.consumer.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.linli.consumer.R;

/**
 * Created by tomoyo on 2016/12/1.
 * 自定义behavior
 * 未使用
 */

public class MyBehavior extends CoordinatorLayout.Behavior {

    public MyBehavior(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.shop_detail_vp;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getHeight());
        Log.i("WATER__B",dependency.getY()+"");
        Log.i("WATER__P",parent.getHeight()+"");
        Log.i("WATER__H",dependency.getHeight()+"");
        return true;
    }
}
