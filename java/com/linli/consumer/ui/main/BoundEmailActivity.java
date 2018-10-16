package com.linli.consumer.ui.main;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

public class BoundEmailActivity extends BaseActivity {
    private EditText et_email,et_verifycode;
    private TextView tv_get_verifycode;
    private TextView tv_tag;//获取验证码成功将这个标签显示出来
    private Button btn_confirm;
    private TimeCountGetCode time;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bound_email;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("绑定邮箱");
        et_email = findView(R.id.et_email);
        et_email.setText(user.getEmail());
        et_verifycode = findView(R.id.et_verifycode);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        tv_tag = findView(R.id.tv_tag);
        tv_tag.setVisibility(View.INVISIBLE);
        btn_confirm = findViewClick(R.id.btn_confirm);
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
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
                if (TextUtils.isEmpty(et_email.getText())){
                    Toast.makeText(BoundEmailActivity.this,"请先输入邮箱地址",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verifycode.setClickable(false);
                    tv_get_verifycode.setText("获取中...");
                    request_get_verifycode();
                }
                break;
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_verifycode.getText())){
                    Toast.makeText(BoundEmailActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else {
                    request_verify();
                }
                break;
            default:
                break;
        }
    }
    private void request_verify() {
        final String email = et_email.getText().toString().trim();
        //如果成功
        IntrestBuyNet.checkEmailAndCode(2,et_verifycode.getText().toString().trim(), email, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){//验证成功修改邮箱号
                    request_update_email(email);
                }else {
                    Toast.makeText(BoundEmailActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_update_email(final String email) {
        IntrestBuyNet.updateUseremail(user.getId(), email, new HandleSuccess<Login>() {
            @Override
            public void success(Login login) {
                if (login.getResponseCode().equals(CafeNet.SUCCESS)){
                    user.setEmail(email);
                    Toast.makeText(BoundEmailActivity.this,"绑定邮箱成功",Toast.LENGTH_SHORT).show();
                    BoundEmailActivity.this.finish();
                }else {
                    showToast(login.getResponseMessage());
                }
            }
        });
    }

    private void request_get_verifycode() {
        String email = et_email.getText().toString().trim();
        IntrestBuyNet.sendEmail(email, new HandleSuccess<PhoneCode>() {//是否缺少userid
            @Override
            public void success(PhoneCode phoneCode) {
                if (phoneCode.getStatus() == 1){
                    time.start();
                    Toast.makeText(BoundEmailActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                    tv_tag.setVisibility(View.VISIBLE);
                }else {
                    tv_get_verifycode.setClickable(true);
                    Toast.makeText(BoundEmailActivity.this,phoneCode.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
