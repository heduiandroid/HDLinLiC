package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.bean.UpdatePayPwd;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

public class EWResetPayPasswordActivity extends BaseActivity {
    private TextView et_phone_number;
    private TextView tv_get_verifycode;
    private EditText et_verifycode;
    private Button btn_next;
    private EditText et_password_new;
    private EditText et_password_newagain;
    private Button btn_finish;
    private LinearLayout ll_verify_phone,ll_reset_password;
    private TimeCountGetCode time;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_ewreset_pay_password;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("密码设置");
        ll_verify_phone= findView(R.id.ll_verify_phone);
        ll_reset_password = findView(R.id.ll_reset_password);
        et_phone_number = findView(R.id.et_phone_number);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        et_verifycode = findView(R.id.et_verifycode);
        btn_next = findViewClick(R.id.btn_next);
        et_password_new = findView(R.id.et_password_new);
        et_password_newagain =findView(R.id.et_password_newagain);
        btn_finish = findViewClick(R.id.btn_finish);
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user != null){
            et_phone_number.setText(secretPhone(user.getPhone()));
        }
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
    }
    private String secretPhone(String phone){
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
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
                if (TextUtils.isEmpty(et_phone_number.getText())){
                    Toast.makeText(EWResetPayPasswordActivity.this,"请先输入手机号码",Toast.LENGTH_SHORT).show();
                }else {

                    request_get_verifycode();
                }
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(et_verifycode.getText())){
                    Toast.makeText(EWResetPayPasswordActivity.this,"请先输入验证码",Toast.LENGTH_SHORT).show();
                }else if (et_verifycode.getText().length()< 6){
                    Toast.makeText(EWResetPayPasswordActivity.this,"请输入6位验证码",Toast.LENGTH_SHORT).show();
                }else {
                    request_check_v_code();

                }
                break;
            case R.id.btn_finish:
                if (TextUtils.isEmpty(et_password_new.getText()) || TextUtils.isEmpty(et_password_newagain.getText())){
                    Toast.makeText(EWResetPayPasswordActivity.this,"请完整填写信息",Toast.LENGTH_SHORT).show();
                }else if (!et_password_new.getText().toString().equals(et_password_newagain.getText().toString())){
                    Toast.makeText(EWResetPayPasswordActivity.this,"两次输入密码不同",Toast.LENGTH_SHORT).show();
                }else if (et_password_new.getText().length() < 6){
                    Toast.makeText(EWResetPayPasswordActivity.this,"请输入6位支付密码",Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("提示");
                    builder.setMessage("即将重置新的支付密码");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            request_reset_paypassword();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create();
                    builder.show();
                }
                break;
            default:
                break;
        }
    }

    private void request_check_v_code() {
        IntrestBuyNet.checkPhoneAndCode(1, et_verifycode.getText().toString().trim(), user.getPhone(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    ll_verify_phone.setVisibility(View.GONE);
                    ll_reset_password.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(EWResetPayPasswordActivity.this,"验证码不正确",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_reset_paypassword() {
        String resetPass = et_password_new.getText().toString();
        IntrestBuyNet.setupPayPasswordSet(user.getId(), resetPass, new HandleSuccess<UpdatePayPwd>() {
            @Override
            public void success(UpdatePayPwd s) {
                if (s.getStatus() == 1){
                    Toast.makeText(EWResetPayPasswordActivity.this,"支付密码重置成功",Toast.LENGTH_SHORT).show();
                    EWResetPayPasswordActivity.this.finish();
                }else {
                    Toast.makeText(EWResetPayPasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_get_verifycode() {
        String phone = user.getPhone().trim();
        IntrestBuyNet.getPhoneCode(phone, new HandleSuccess<PhoneCode>() {
            @Override
            public void success(PhoneCode phoneCode) {
                if (phoneCode.getStatus() == 1){
                    time.start();
                    Toast.makeText(EWResetPayPasswordActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                }else {
                    // 如果是验证码错误
                    Toast.makeText(EWResetPayPasswordActivity.this,phoneCode.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
