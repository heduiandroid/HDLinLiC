package com.linli.consumer.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.linli.consumer.myview.MyListView;

/**
 * Created by Administrator on 2016/6/21.
 */
public class HorizontalScrollListView extends MyListView {
    private final String TAG = "HorizontalScrollListView";
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;
    public HorizontalScrollListView(Context context) {
        super(context);
    }

    public HorizontalScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }

    public HorizontalScrollListView(Context context, AttributeSet attrs,
                                    int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev)
                || mGestureDetector.onTouchEvent(ev);
    }
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if(distanceY !=0 && distanceX != 0){
                if (Math.abs(distanceY) >= Math.abs(distanceX)) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }
}
