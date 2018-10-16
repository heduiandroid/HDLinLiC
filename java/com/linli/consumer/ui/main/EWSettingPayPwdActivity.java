package com.linli.consumer.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

public class EWSettingPayPwdActivity extends BaseActivity {
    private EditText et_pwd,et_repwd;//支付密码编辑框
    private ImageView cleartext,recleartext;//清除密码，清除重输密码
    private Button btn_next;//下一步按钮

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_pay_pwd;
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
        tv_head_name.setText("设置支付密码");
        et_pwd = findView(R.id.et_pwd);
        et_repwd = findView(R.id.et_repwd);
        cleartext = findViewClick(R.id.cleartext);
        recleartext = findViewClick(R.id.recleartext);
        btn_next = findViewClick(R.id.btn_next);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.cleartext:
                et_pwd.setText("");
                break;
            case R.id.recleartext:
                et_repwd.setText("");
                break;
            case R.id.btn_next://带着密码 去设置银行卡界面
                if (TextUtils.isEmpty(et_pwd.getText())){
                    Toast.makeText(EWSettingPayPwdActivity.this,"请输入支付密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_repwd.getText())){
                    Toast.makeText(EWSettingPayPwdActivity.this,"需再次输入密码",Toast.LENGTH_SHORT).show();
                }else if (et_pwd.getText().length()<6 || et_repwd.getText().length()<6){
                    Toast.makeText(EWSettingPayPwdActivity.this,"需是6位数字的支付密码",Toast.LENGTH_SHORT).show();
                }else {
                    String pwd = et_pwd.getText().toString();
                    String repwd = et_repwd.getText().toString();
                    if (pwd.equals(repwd)) {//带着密码 去设置银行卡界面
                        Intent intent = new Intent(EWSettingPayPwdActivity.this,EWAddBankCardActivity.class);
                        intent.putExtra("isfirsttime",true);
                        intent.putExtra("paypwd",pwd);
                        startActivity(intent);
                        EWSettingPayPwdActivity.this.finish();
                    }else {
                        et_pwd.setText("");
                        et_repwd.setText("");
                        Toast.makeText(EWSettingPayPwdActivity.this,"两次输入不一致，请重试",Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                break;
        }
    }
}
