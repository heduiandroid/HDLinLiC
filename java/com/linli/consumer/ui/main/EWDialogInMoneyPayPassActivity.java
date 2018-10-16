package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;

public class EWDialogInMoneyPayPassActivity extends Activity implements View.OnClickListener {
    private TextView tv_inmoney;//充值金额
    private EditText et_paypassword;//支付密码
    private LinearLayout ll_changebankcard;//修改使用的银行卡
    private Button btn_cancel;//取消充值按钮
    private Button btn_confirm;//确认提交充值按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_in_money_pay_pass);
        init();
    }

    private void init() {
        tv_inmoney = (TextView) findViewById(R.id.tv_inmoney);
        et_paypassword = (EditText) findViewById(R.id.et_paypassword);
        ll_changebankcard = (LinearLayout) findViewById(R.id.ll_changebankcard);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        ll_changebankcard.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_changebankcard://去已绑定银行卡列表弹窗
                Intent intent = new Intent(EWDialogInMoneyPayPassActivity.this,EWChangeBankCardActivity.class);
                startActivityForResult(intent,1700);
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_paypassword.getText())){
                    Toast.makeText(EWDialogInMoneyPayPassActivity.this,"请输入支付密码",Toast.LENGTH_SHORT).show();
                }else {
                    request_inmoney();
                }
                break;
            default:
                break;
        }
    }

    private void request_inmoney() {

        //如果充值成功
        Intent intent = new Intent(EWDialogInMoneyPayPassActivity.this,EWMoneySuccessActivity.class);
        intent.putExtra("type",1);//1充值  2提现
        startActivity(intent);
        EWDialogInMoneyPayPassActivity.this.finish();
    }
}
