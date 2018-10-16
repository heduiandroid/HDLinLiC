package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class DialogInputPasswordActivity extends Activity implements View.OnClickListener {
    private TextView tv_outmoney;//提现金额
    private EditText et_paypassword;//支付密码
    private Button btn_cancel,btn_confirm;//取消 提交
    private long userId;
    private Double outMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_input_password);
        init();
        Intent intent = getIntent();
        userId = intent.getLongExtra("userid",0l);
        outMoney = intent.getDoubleExtra("outmoney", 0d);
        tv_outmoney.setText("￥"+outMoney);
    }
    private void init() {
        tv_outmoney = (TextView) findViewById(R.id.tv_outmoney);
        et_paypassword = (EditText) findViewById(R.id.et_paypassword);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm://确认提现
                if (TextUtils.isEmpty(et_paypassword.getText())){
                    Toast.makeText(DialogInputPasswordActivity.this,"请输入支付密码",Toast.LENGTH_SHORT).show();
                }else {
                    request_confirm_outmoney();
                }
                break;
            default:
                break;
        }
    }

    private void request_confirm_outmoney() {
        String pass = et_paypassword.getText().toString();
        IntrestBuyNet.launchWithdrawTrans(outMoney, pass, userId, new HandleSuccess<Generic>() {//少一个type参数？
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){

                    Toast.makeText(DialogInputPasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("success",true);
                    setResult(309,intent);
                    DialogInputPasswordActivity.this.finish();
                }else{
                    Toast.makeText(DialogInputPasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("success",false);
                    setResult(309,intent);
                    DialogInputPasswordActivity.this.finish();
                }
            }
        });
//        RequestQueue requestQueue = VolleyTools.getInstance(this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("owerid", userId);////////////////////
//            jsonParams.put("paypassword", pass);
//            jsonParams.put("price", outMoney);
//            jsonParams.put("type", 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url = Common.getOutMoneyResrult() + jsonParams.toString();/////////////////////////////////
//        System.out.println(url);
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        System.out.println(s);
//                        if (jsonObject.getInt("code") == 0){
//                            //如果成功
//                            Intent intent = new Intent();
//                            intent.putExtra("success",true);
//                            setResult(309,intent);
//                            DialogInputPasswordActivity.this.finish();
//                        }else {
//                            //如果失败
//                            Intent intent = new Intent();
//                            intent.putExtra("success",false);
//                            setResult(309,intent);
//                            DialogInputPasswordActivity.this.finish();
//                            System.out.println("id为空");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("DialogInputPasswordActivity," + volleyError.getLocalizedMessage());
//                Toast.makeText(DialogInputPasswordActivity.this, "网络繁忙,请重试", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(request);
    }
}
