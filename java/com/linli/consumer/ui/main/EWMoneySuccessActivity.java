package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;

public class EWMoneySuccessActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_bankcard,ll_orderno;//type = 2时隐藏
    private TextView tv_info;//提示tips
    private TextView tv_outmoney;//提现金额
    private TextView tv_banknum;//提现账户卡号
    private TextView tv_createtime;//交易时间
    private TextView tv_orderno;//订单号
    private TextView tv_type;//1充值成功  2提现成功
    private int type;//1充值  2提现
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_out_success);
        intent = getIntent();
        type = intent.getIntExtra("type",0);
        init();
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_head_name = (TextView)findViewById(R.id.tv_head_name);
        tv_head_name.setText("交易详情");
        tv_head_name.setOnClickListener(this);
        ll_bankcard= (LinearLayout) findViewById(R.id.ll_bankcard);
        ll_orderno = (LinearLayout) findViewById(R.id.ll_orderno);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_outmoney = (TextView) findViewById(R.id.tv_outmoney);
        tv_banknum = (TextView) findViewById(R.id.tv_banknum);
        tv_createtime = (TextView) findViewById(R.id.tv_createtime);
        tv_orderno = (TextView) findViewById(R.id.tv_orderno);
        tv_type = (TextView) findViewById(R.id.tv_type);
        if (type == 1){
            tv_type.setText("充值成功");
            tv_info.setText("资金已转入余额");
            Double inmoney = intent.getDoubleExtra("inmoney", 0d);
            tv_outmoney.setText(inmoney+"元");
            ll_bankcard.setVisibility(View.GONE);
            tv_banknum.setText(intent.getStringExtra("bankcard"));
            tv_createtime.setText(intent.getStringExtra("createtime"));
            tv_orderno.setText(intent.getStringExtra("orderno"));
        }else if (type == 2){
            tv_type.setText("提现成功");
            ll_orderno.setVisibility(View.GONE);
            tv_info.setText("预计24小时内到账");
            Double outmoney = intent.getDoubleExtra("outmoney", 0d);
            tv_outmoney.setText(outmoney+"元");
            tv_banknum.setText(intent.getStringExtra("bankcard"));
            tv_createtime.setText(intent.getStringExtra("createtime"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            default:
                break;
        }
    }
}
