package com.linli.consumer.ui.main;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

public class UpdatePhoneActivity extends BaseActivity {
    private TextView tv_use_phone,tv_use_email;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_phone;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("修改绑定手机");
        TextView tv_tag = findView(R.id.tv_tag);
        String tag = "如果无法完成修改，请拨打人工客服 <font color='#FF0000'>400-898-8830</font>,由客服协助您完成修改";
        tv_tag.setText(Html.fromHtml(tag));
        tv_use_phone = findViewClick(R.id.tv_use_phone);
        tv_use_email = findViewClick(R.id.tv_use_email);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_use_phone:
                startActivity(new Intent(UpdatePhoneActivity.this,UpdatePhoneByPhoneActivity.class));
                break;
            case R.id.tv_use_email:
                startActivity(new Intent(UpdatePhoneActivity.this,UpdatePhoneByEmailActivity.class));
                break;
            default:
                break;
        }
    }
}
