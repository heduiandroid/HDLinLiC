package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linli.consumer.R;

public class EWInputPayPwdActivity extends Activity implements View.OnClickListener {
    private EditText et_paypassword;//支付密码
    private Button btn_cancel,btn_confirm;//确定取消按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pay_pwd);
        init();
    }

    private void init() {
        et_paypassword = (EditText) findViewById(R.id.et_paypassword);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_paypassword.getText())){
                    Toast.makeText(EWInputPayPwdActivity.this,"请输入支付密码",Toast.LENGTH_SHORT).show();
                }else if (et_paypassword.getText().length() < 6){
                    Toast.makeText(EWInputPayPwdActivity.this,"请输入6位数字支付密码",Toast.LENGTH_SHORT).show();
                }else {
                    String payPwd = et_paypassword.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("paypwd",payPwd);
                    setResult(876, intent);
                    EWInputPayPwdActivity.this.finish();
                }
                break;
            default:
                break;
        }
    }
}
