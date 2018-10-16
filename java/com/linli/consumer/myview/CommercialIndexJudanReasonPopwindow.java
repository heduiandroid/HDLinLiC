package com.linli.consumer.myview;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.linli.consumer.R;

/**
 * Created by wwy on 2016/6/9.
 */
public class CommercialIndexJudanReasonPopwindow extends PopupWindow implements View.OnClickListener {
    private View mView;
    private ImageView iv_commercial_judan_checkfirst, iv_commercial_judan_checksecond, iv_commercial_judan_checkthird;
    private TextView tv_commercialpop_jujue_confirm;//界面中的确认


    public CommercialIndexJudanReasonPopwindow(Activity activity) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        mView = layoutInflater.inflate(R.layout.popupwindow_commercial_judanreason, null);
        iv_commercial_judan_checkfirst = (ImageView) mView.findViewById(R.id.iv_commercial_judan_checkfirst);
        iv_commercial_judan_checksecond = (ImageView) mView.findViewById(R.id.iv_commercial_judan_checksecond);
        iv_commercial_judan_checkthird = (ImageView) mView.findViewById(R.id.iv_commercial_judan_checkthird);
        tv_commercialpop_jujue_confirm = (TextView) mView.findViewById(R.id.tv_commercialpop_jujue_confirm);
        iv_commercial_judan_checkfirst.setOnClickListener(this);
        iv_commercial_judan_checksecond.setOnClickListener(this);
        iv_commercial_judan_checkthird.setOnClickListener(this);
        tv_commercialpop_jujue_confirm.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_commercialpop_jujue_confirm:
                dismiss();
                break;
            case R.id.iv_commercial_judan_checkfirst:
                iv_commercial_judan_checkfirst.setBackgroundResource(R.mipmap.iv_checked_commercial_judan);
                iv_commercial_judan_checksecond.setBackgroundResource(R.mipmap.iv_uncheck_commercial_judan);
                iv_commercial_judan_checkthird.setBackgroundResource(R.mipmap.iv_uncheck_commercial_judan);
                break;
            case R.id.iv_commercial_judan_checksecond:
                iv_commercial_judan_checksecond.setBackgroundResource(R.mipmap.iv_checked_commercial_judan);
                iv_commercial_judan_checkfirst.setBackgroundResource(R.mipmap.iv_uncheck_commercial_judan);
                iv_commercial_judan_checkthird.setBackgroundResource(R.mipmap.iv_uncheck_commercial_judan);
                break;
            case R.id.iv_commercial_judan_checkthird:
                iv_commercial_judan_checkthird.setBackgroundResource(R.mipmap.iv_checked_commercial_judan);
                iv_commercial_judan_checksecond.setBackgroundResource(R.mipmap.iv_uncheck_commercial_judan);
                iv_commercial_judan_checkfirst.setBackgroundResource(R.mipmap.iv_uncheck_commercial_judan);
                break;
        }

    }
}
