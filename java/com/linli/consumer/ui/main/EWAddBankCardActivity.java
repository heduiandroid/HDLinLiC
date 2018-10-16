package com.linli.consumer.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AddBankCard;
import com.linli.consumer.bean.UpdatePayPwd;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class EWAddBankCardActivity extends BaseActivity{
    private EditText et_name;//持卡人姓名
    private EditText et_cardnum;//卡号
    private EditText et_bankname;//卡号所属银行
    private EditText et_phonenum;//银行预留手机号
    private Button btn_next;//下一步按钮
    private AppContext appContext;
    private User user;
    private boolean isfirsttime = false;//第一次绑卡 需要先调用设置支付密码接口在调用添加银行卡接口
    private String payPwd = null;
    private String bankUserName;//持卡人姓名
    private String bankCard;//卡号
    private String bankName;//银行名称

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ewadd_bank_card;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        init();
        Intent intent = getIntent();
        isfirsttime = intent.getBooleanExtra("isfirsttime",false);
        if (isfirsttime){
            payPwd = intent.getStringExtra("paypwd");
        }
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("添加银行卡");
        et_name = findView(R.id.et_name);
        et_cardnum  = findView(R.id.et_cardnum);
        et_bankname = findView(R.id.et_bankname);
        et_phonenum = findView(R.id.et_phonenum);
        btn_next = findViewClick(R.id.btn_next);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(et_cardnum.getText()) || TextUtils.isEmpty(et_phonenum.getText()) || TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_bankname.getText())){
                    Toast.makeText(EWAddBankCardActivity.this,"请完整填写信息",Toast.LENGTH_SHORT).show();
                }else if (et_cardnum.getText().length() < 16){
                    Toast.makeText(EWAddBankCardActivity.this,"请检查银行卡号是否正确",Toast.LENGTH_SHORT).show();
                }else if (et_phonenum.getText().length() < 11){
                    Toast.makeText(EWAddBankCardActivity.this,"请检查手机号是否正确",Toast.LENGTH_SHORT).show();
                }else {
                    bankUserName = et_name.getText().toString();
                    bankCard = et_cardnum.getText().toString();
                    bankName = et_bankname.getText().toString();//?????????
                    if (isfirsttime){
                        request_setting_paypwd();
                    }else {//去让用户输入支付密码界面，将支付密码返回后，在调用request_addbankcard();
                        Intent intent = new Intent(EWAddBankCardActivity.this,EWInputPayPwdActivity.class);
                        startActivityForResult(intent,876);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void request_setting_paypwd() {
        IntrestBuyNet.setupPayPasswordSet(user.getId(), payPwd, new HandleSuccess<UpdatePayPwd>() {
            @Override
            public void success(UpdatePayPwd s) {
                if (s.getStatus() == 1){
                    Toast.makeText(EWAddBankCardActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    request_addbankcard();
                }else if (s.getStatus() == 2){
                    finish();
                    Toast.makeText(EWAddBankCardActivity.this,"登录已失效，请重新登录后再试",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EWAddBankCardActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_addbankcard() {
        IntrestBuyNet.addBankCard(payPwd, bankName, bankCard, bankUserName, user.getId(), new HandleSuccess<AddBankCard>() {
            @Override
            public void success(AddBankCard s) {
                if (s.getStatus() == 1){
                    //if success
                    EWalletActivity.EWBankName = bankName;
                    EWalletActivity.EWCardNumber = bankCard;
                    EWalletActivity.EWAccountName = bankUserName;
                    if (bankCard != null){
                        EWalletActivity.WeiHao  = bankCard.substring(bankCard.length()-4,bankCard.length());
                    }
                    Toast.makeText(EWAddBankCardActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    EWAddBankCardActivity.this.finish();
                }else if (s.getStatus() == 2){
                    finish();
                    Toast.makeText(EWAddBankCardActivity.this,"登录已失效，请重新登录后再试",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EWAddBankCardActivity.this,s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (resultCode == 876){
                payPwd = data.getStringExtra("paypwd");
                request_addbankcard();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
