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
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

public class UpdatePhoneByPhoneActivity extends BaseActivity {
    private TextView tv_phone_number,tv_get_verifycode;
    private EditText et_verifycode;
    private AppContext appContext;
    private User user;
    private TimeCountGetCode time;
    private String mphone = null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_phone_by_phone;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();

        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("修改绑定手机");
        et_verifycode = findView(R.id.et_verifycode);
        tv_phone_number = findView(R.id.tv_phone_number);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        findViewClick(R.id.btn_next);
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
        if (user != null){
            mphone = user.getPhone();
            tv_phone_number.setText(secretPhone(mphone));
        }
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
                case R.id.tv_get_verifycode:

                        if (mphone != null){
                            tv_get_verifycode.setClickable(false);
                            request_get_verifycode();
                        }else {
                            Toast.makeText(this,"网络异常，请稍后再试",Toast.LENGTH_SHORT).show();
                        }

                    break;
                case R.id.btn_next:
                    if (TextUtils.isEmpty(et_verifycode.getText())){
                        Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    }else {
                        request_verify();//验证验证码是否正确 如果正确就去修改界面了
                    }
                    break;
                default:
                    break;
            }
    }

    private void request_verify() {
        //验证成功
        IntrestBuyNet.checkPhoneAndCode(1,et_verifycode.getText().toString().trim(), user.getPhone(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    startActivity(new Intent(UpdatePhoneByPhoneActivity.this,UpdatePhoneFinalActivity.class));
                    finish();
                }else {
                    Toast.makeText(UpdatePhoneByPhoneActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void request_get_verifycode() {
        IntrestBuyNet.getPhoneCode(mphone, new HandleSuccess<PhoneCode>() {//是否缺少tag
            @Override
            public void success(PhoneCode phoneCode) {
                if (phoneCode.getStatus() == 1){
                    time.start();
                    Toast.makeText(UpdatePhoneByPhoneActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verifycode.setClickable(true);
                    Toast.makeText(UpdatePhoneByPhoneActivity.this,phoneCode.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private String secretPhone(String phone){
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }
}
