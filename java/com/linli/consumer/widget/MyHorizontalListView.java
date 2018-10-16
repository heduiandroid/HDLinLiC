package com.linli.consumer.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by hasee on 2018/7/26.
 */

public class MyHorizontalListView extends HorizontalListView {
    public MyHorizontalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int width = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(width, heightMeasureSpec);
    }
}
