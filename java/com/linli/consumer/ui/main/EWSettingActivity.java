package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

public class EWSettingActivity extends BaseActivity {
    private LinearLayout ll_updatepaypwd,ll_resetpaypwd;//修改支付密码//找回支付密码

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ewsetting;
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
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("钱包设置");

        ll_updatepaypwd =  findViewClick(R.id.ll_updatepaypwd);
        ll_resetpaypwd = findViewClick(R.id.ll_resetpaypwd);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.ll_updatepaypwd://去修改支付密码界面
                startActivity(new Intent(EWSettingActivity.this,EWUpdatePayPasswordActivity.class));
                break;
            case R.id.ll_resetpaypwd:
                startActivity(new Intent(EWSettingActivity.this,EWResetPayPasswordActivity.class));
                break;
            default:
                break;
        }
    }
}
