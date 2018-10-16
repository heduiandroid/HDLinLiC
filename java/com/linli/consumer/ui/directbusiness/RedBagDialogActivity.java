package com.linli.consumer.ui.directbusiness;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;

public class RedBagDialogActivity extends BaseActivity {
    private TextView tv_redbag_sum;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_red_bag_dialog;
    }

    @Override
    protected void initView() {
        tv_redbag_sum = findView(R.id.tv_redbag_sum);
        findViewClick(R.id.iv_redbag);
        findViewClick(R.id.iv_close);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        tv_redbag_sum.setText("￥"+intent.getStringExtra("count"));
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_redbag://立即使用
                finish();
                UIHelper.togoDirectShopActivity(this);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
