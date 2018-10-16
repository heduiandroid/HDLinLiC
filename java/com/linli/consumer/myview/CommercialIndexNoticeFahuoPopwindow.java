package com.linli.consumer.myview;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.linli.consumer.R;

/**
 * Created by wwy on 2016/6/7.
 */
public class CommercialIndexNoticeFahuoPopwindow extends PopupWindow implements View.OnClickListener {
    private View mView;

    public CommercialIndexNoticeFahuoPopwindow(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();//得到activity
        mView = inflater.inflate(R.layout.popouwindow_commercial_index_noticefahuo, null);

        // 设置PopupWindow的View
        this.setContentView(mView);
        // 设置PopupWindow弹出窗体的宽
        this.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        // 设置PopupWindow弹出窗体的高
        this.setHeight(android.view.WindowManager.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimTop);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();


    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }


    @Override
    public void onClick(View v) {
    }
}
