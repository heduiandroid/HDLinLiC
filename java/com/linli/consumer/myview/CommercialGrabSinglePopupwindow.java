package com.linli.consumer.myview;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.linli.consumer.R;

/**
 * Created by wwy on 2016/6/9.
 */
public class CommercialGrabSinglePopupwindow extends PopupWindow implements View.OnClickListener {
    private View mView;
    private Button mButtonQiang, mButtonJu;


    public CommercialGrabSinglePopupwindow(Activity activity) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        mView = layoutInflater.inflate(R.layout.popwindow_commercial_grabsingle, null);
        mButtonQiang = (Button) mView.findViewById(R.id.btn_popwindow_commercial_grabsingle_qiang);
        mButtonJu = (Button) mView.findViewById(R.id.btn_popwindow_commercial_grabsingle_ju);
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
        mButtonQiang.setOnClickListener(this);
        mButtonJu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_popwindow_commercial_grabsingle_ju:
                dismiss();
                break;
            case R.id.btn_popwindow_commercial_grabsingle_qiang:
                dismiss();
                break;
        }

    }
}
