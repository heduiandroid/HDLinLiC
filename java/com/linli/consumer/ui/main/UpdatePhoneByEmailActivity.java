package com.linli.consumer.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

public class UpdatePhoneByEmailActivity extends BaseActivity {
    private EditText et_verifycode;
    private TextView tv_email,tv_get_verifycode,tv_tag;
    private TimeCountGetCode time;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_phone_by_email;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("修改绑定手机");
        findViewClick(R.id.btn_next);
        tv_email = findView(R.id.tv_email);
        et_verifycode = findView(R.id.et_verifycode);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        tv_tag = findView(R.id.tv_tag);
        tv_tag.setVisibility(View.INVISIBLE);
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
    }

    @Override
    protected void initData() {
        if (user.getEmail() != null){
            tv_email.setText(user.getEmail());
        }else {
            Toast.makeText(UpdatePhoneByEmailActivity.this,"先去绑定验证邮箱吧~",Toast.LENGTH_SHORT).show();
            finish();
        }

        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_get_verifycode:
                if (TextUtils.isEmpty(tv_email.getText())){
                    Toast.makeText(UpdatePhoneByEmailActivity.this,"先去绑定验证邮箱吧~",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verifycode.setClickable(false);
                    tv_get_verifycode.setText("获取中...");
                    request_get_verifycode();
                }
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(et_verifycode.getText())){
                    Toast.makeText(UpdatePhoneByEmailActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else {
                    request_verify();
                }
                break;
            default:
                break;
        }
    }

    private void request_verify() {
        String email = tv_email.getText().toString().trim();
        //如果成功
        IntrestBuyNet.checkEmailAndCode(2,et_verifycode.getText().toString().trim(), email, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    startActivity(new Intent(UpdatePhoneByEmailActivity.this,UpdatePhoneFinalActivity.class));
                    finish();
                }else {
                    Toast.makeText(UpdatePhoneByEmailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_get_verifycode() {
        String email = tv_email.getText().toString().trim();
        IntrestBuyNet.sendEmail(email, new HandleSuccess<PhoneCode>() {//是否缺少userid
            @Override
            public void success(PhoneCode phoneCode) {
                if (phoneCode.getStatus() == 1){
                    time.start();
                    Toast.makeText(UpdatePhoneByEmailActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                    tv_tag.setVisibility(View.VISIBLE);
                }else {
                    tv_get_verifycode.setClickable(true);
                    Toast.makeText(UpdatePhoneByEmailActivity.this,phoneCode.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
