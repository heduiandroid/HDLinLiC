package com.linli.consumer.ui.main;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

public class HelpActivity extends BaseActivity implements View.OnClickListener {
    private WebView wv_help;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
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
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("帮助中心");
        findViewClick(R.id.iv_back);
        wv_help = findView(R.id.wv_help);
    }

    @Override
    public void onHDClick(View v) {
        finish();
    }
}
