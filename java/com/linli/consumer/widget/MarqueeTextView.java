package com.linli.consumer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by tomoyo on 2016/11/28.
 * 带有跑马灯效果的TextView
 * 通过在xml配置中实现
 * 但是需要获得焦点才可以，所以在此设置焦点
 */

public class MarqueeTextView extends TextView {
    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context) {
        super(context);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
