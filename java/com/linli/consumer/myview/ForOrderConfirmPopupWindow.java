package com.linli.consumer.myview;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.linli.consumer.R;

/**
 * Created by wwy on 2016/5/27.
 */
public class ForOrderConfirmPopupWindow implements View.OnClickListener, PopupWindow.OnDismissListener {

    private Context mContext;
    private View mView;
    private ImageView iv_fororder_check_man, iv_fororder_check_woman, iv_fororder_close;
    private PopupWindow mPopupWindow;
    private LinearLayout ll_fororder_below;

    public ForOrderConfirmPopupWindow(Context context) {
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.popup_outshop_fororder, null);
        iv_fororder_check_man = (ImageView) mView.findViewById(R.id.iv_fororder_check_man);
        iv_fororder_check_woman = (ImageView) mView.findViewById(R.id.iv_fororder_check_woman);
        iv_fororder_close = (ImageView) mView.findViewById(R.id.iv_fororder_close);
        iv_fororder_check_man.setOnClickListener(this);
        iv_fororder_check_woman.setOnClickListener(this);
        mPopupWindow = new PopupWindow(mView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popWindow_fororder_confirm);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
        iv_fororder_close.setOnClickListener(this);
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = mView.findViewById(R.id.ll_fororder_below).getTop();
                int y = (int) motionEvent.getY();
                if (y <= height) {
                    onDismiss();
                }


                return true;
            }
        });

    }

    /*
    点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fororder_close:
                this.onDismiss();
                break;
            case R.id.iv_fororder_check_man:
                iv_fororder_check_man.setBackgroundResource(R.mipmap.order_iv_online_checked);
                iv_fororder_check_woman.setBackgroundResource(R.mipmap.order_iv_online_nocheck);
                break;
            case R.id.iv_fororder_check_woman:
                iv_fororder_check_woman.setBackgroundResource(R.mipmap.order_iv_online_checked);
                iv_fororder_check_man.setBackgroundResource(R.mipmap.order_iv_online_nocheck);
                break;
        }


    }

    /*
    popupwindow 消失
     */
    @Override
    public void onDismiss() {
        mPopupWindow.dismiss();
    }

    /**
     * 设置点击确认按钮时监听接口
     */
    public interface OnItemClickListener {

        public void onClickOKPop();
    }

    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
        mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
    }

}
