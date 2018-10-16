package com.linli.consumer.myview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.linli.consumer.R;
import com.linli.consumer.adapter.ViewAllFenLeiAdapter;
import com.linli.consumer.domain.ViewAllFenLei;

import java.util.List;


/**
 * Created by wwy on 2016/5/25.
 */
public class AllSortPopupWindow extends PopupWindow {
    private ListView mListViewLeft;
    private ListView mListViewRight;
    private View mView;
    private ViewAllFenLeiAdapter mViewAllFenLeiAdapter, mViewAllFenLeiAdapterSecond;//添加adapter
    private List<ViewAllFenLei> mViewAllFenLeis;
    private Activity mActivity;

    public AllSortPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AllSortPopupWindow(final Activity activity, List<ViewAllFenLei> viewAllFenLeis) {
        this.mActivity = activity;
        LayoutInflater inflater = activity.getLayoutInflater();
        mView = inflater.inflate(R.layout.popupwindow_all_sort, null);
        mListViewLeft = (ListView) mView.findViewById(R.id.order_popuplistview_allsort_left);
        mListViewRight = (ListView) mView.findViewById(R.id.order_popuplistview_allsort_right);

//        mListViewRight.setVisibility(View.INVISIBLE);
        this.mViewAllFenLeis = viewAllFenLeis;
        if (mViewAllFenLeis != null) {
            mViewAllFenLeiAdapter = new ViewAllFenLeiAdapter(activity, mViewAllFenLeis);
        }
        if (mViewAllFenLeiAdapter != null) {
            mListViewLeft.setAdapter(mViewAllFenLeiAdapter);
        }
        //同时此处让右侧的listview 显示
        mListViewRight.setVisibility(View.VISIBLE);
        mViewAllFenLeiAdapterSecond = new ViewAllFenLeiAdapter(activity, mViewAllFenLeis);
        if (mViewAllFenLeiAdapterSecond != null) {
            mListViewRight.setAdapter(mViewAllFenLeiAdapterSecond);
        }
        this.setContentView(mView);
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


        mListViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mViewAllFenLeiAdapter.setPosition(i);
                mViewAllFenLeiAdapter.notifyDataSetInvalidated();//此处用于判断用户点击的item，同时改变item背景



            }
        });
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int heightTop = mView.findViewById(R.id.order_popuplistview_allsort_left).getTop();
                int y = (int) event.getY();
                int heightBottom = mView.findViewById(R.id.order_popuplistview_allsort_left).getBottom();
                //MotionEvent_up为单点触摸离开模式，用户点击屏幕中的其他位置，则弹出框消失
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < heightTop || y > heightBottom) {

                        dismiss();
                    }
                }
                return true;
            }
        });


    }


}





