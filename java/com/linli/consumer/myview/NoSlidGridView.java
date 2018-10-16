package com.linli.consumer.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 王征 on 2016/6/5.
 * 此处的gridview没有滑动效果
 */
public class NoSlidGridView extends GridView {
    public NoSlidGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSlidGridView(Context context) {
        super(context);
    }

    public NoSlidGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

