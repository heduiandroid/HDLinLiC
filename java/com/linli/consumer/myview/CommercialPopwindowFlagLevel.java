package com.linli.consumer.myview;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.linli.consumer.R;

/**
 * Created by wwy on 2016/6/10.
 */
public class CommercialPopwindowFlagLevel extends PopupWindow implements View.OnClickListener {
    private View mView;
    private LinearLayout ll_commercial_flaglevel;


    public CommercialPopwindowFlagLevel(Activity activity) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        mView = layoutInflater.inflate(R.layout.popwindow_commercial_flaglevel, null);
        ll_commercial_flaglevel = (LinearLayout) mView.findViewById(R.id.ll_commercial_flaglevel);
        // 设置PopupWindow的View
        this.setContentView(mView);
        // 设置PopupWindow弹出窗体的宽
        this.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        // 设置PopupWindow弹出窗体的高
        this.setHeight(android.view.WindowManager.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        ll_commercial_flaglevel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_commercial_flaglevel:
                dismiss();
                break;
        }
    }
}
