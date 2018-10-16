package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AccountInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

public class AccountSafeActivity extends BaseActivity {
    private TextView tv_ifboundemail;
    private AppContext appContext;
    private User user;
    private boolean isBindedCard = false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_safe;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("账户安全设置");
        findViewClick(R.id.iv_back);
        findViewClick(R.id.ll_reset_password);
        findViewClick(R.id.ll_reset_phone);
        findViewClick(R.id.ll_bound_email);
        findViewClick(R.id.ll_pay_password);
        tv_ifboundemail = findView(R.id.tv_ifboundemail);
        if (user.getEmail() != null){
            tv_ifboundemail.setText("已绑定邮箱："+user.getEmail());
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
            case R.id.ll_reset_password:
                startActivityForResult(new Intent(this, UpdatePasswordActivity.class), 500);
                break;
            case R.id.ll_reset_phone://修改绑定手机
                startActivity(new Intent(AccountSafeActivity.this,UpdatePhoneActivity.class));
                break;
            case R.id.ll_bound_email://修改绑定邮箱
                if (user.getEmail() != null){
                    Toast.makeText(AccountSafeActivity.this,"验证邮箱已被绑定",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(AccountSafeActivity.this,BoundEmailActivity.class));
                }
                break;
            case R.id.ll_pay_password:
                request_balance_datas();
                break;
            default:
                 break;
        }
    }

    @Override
    protected void onResume() {
        if (user.getEmail() != null){
            tv_ifboundemail.setText("已绑定邮箱："+user.getEmail());
        }
        super.onResume();
    }

    private void request_balance_datas() {//EWalletActivity也调用了此方法
        IntrestBuyNet.queryUserAccout(user.getId(), new HandleSuccess<AccountInfo>() {
            @Override
            public void success(AccountInfo s) {
                if (s.getStatus() == 1){
                    if (s.getData().getCardNumber() ==null || s.getData().getCardNumber().equals("null") || s.getData().getCardNumber().equals("")) {
                        isBindedCard = false;
                        startActivity(new Intent(AccountSafeActivity.this,EWSettingPayPwdActivity.class));
                    } else {
                        isBindedCard = true;
                        startActivity(new Intent(AccountSafeActivity.this,EWUpdatePayPasswordActivity.class));
                    }
                }else if (s.getStatus() == 2){
                    finish();
                    Toast.makeText(AccountSafeActivity.this, "获取账户信息失败,建议您重新登录后再试", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AccountSafeActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 500){
            setResult(502);
            finish();
        }
    }
}
