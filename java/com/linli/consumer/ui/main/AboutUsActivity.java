package com.linli.consumer.ui.main;

import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

public class AboutUsActivity extends BaseActivity {
    private TextView tv_about;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        TextView tv_head_name = (TextView) findViewClick(R.id.tv_head_name);
        tv_head_name.setText("关于我们");
        findViewClick(R.id.iv_back);
        //关于文字内容
        tv_about = (TextView) findViewById(R.id.tv_about);
    }


    @Override
    public void onHDClick(View v) {
        finish();
    }
}
