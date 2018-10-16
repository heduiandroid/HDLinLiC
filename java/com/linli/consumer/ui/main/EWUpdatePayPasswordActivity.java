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
import com.linli.consumer.bean.UpdatePayPwd;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

public class EWUpdatePayPasswordActivity extends BaseActivity {
    private EditText et_oldpwd,et_newpwd,et_renewpwd;
    private Button btn_confirm;
    private AppContext appContext;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pay_password;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        init();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("修改支付密码");
        et_oldpwd = findView(R.id.et_oldpwd);
        et_newpwd = findView(R.id.et_newpwd);
        et_renewpwd =  findView(R.id.et_renewpwd);
        btn_confirm =  findViewClick(R.id.btn_confirm);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                    finish();
                break;
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_oldpwd.getText())){
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_newpwd.getText())){
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_renewpwd.getText())){
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
                }else if (!et_newpwd.getText().toString().equals(et_renewpwd.getText().toString())){
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"两次输入新密码不一致",Toast.LENGTH_SHORT).show();
                }else if (et_oldpwd.getText().length()<6 || et_newpwd.getText().length()<6 || et_renewpwd.getText().length()<6){
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"需要是6位数字支付密码",Toast.LENGTH_SHORT).show();
                }else {
                    request_update_paypwd();
                }
                break;
            default:
                break;
        }
    }

    private void request_update_paypwd() {
        String oldPwd = et_oldpwd.getText().toString();
        String newPwd = et_newpwd.getText().toString();
        showDialog();
        IntrestBuyNet.setupPayPassword(user.getId(), oldPwd, newPwd, new HandleSuccess<UpdatePayPwd>() {
            @Override
            public void success(UpdatePayPwd s) {
                if (s.getStatus() == 1){
                    //if success
                    EWUpdatePayPasswordActivity.this.finish();
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"支付密码修改成功",Toast.LENGTH_SHORT).show();
                }else if (s.getStatus() == 2){
                    Toast.makeText(EWUpdatePayPasswordActivity.this,"登录失效，建议重新登录后再试",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EWUpdatePayPasswordActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
