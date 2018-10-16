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
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.MoneyInNew;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.linli.consumer.ui.main.EWalletActivity.EWBanlance;

public class MoneyInActivity extends BaseActivity {
    private EditText et_inmoney;//编辑充值金额
    private ImageView cleartext;//清除金额按钮
    private Button btn_next;//下一步
    private Double inMoney;//充值金额
    private AppContext appContext;
    private User user;
    private Long orderNo;//订单号

    @Override
    protected int getLayoutId() {
        return R.layout.activity_money_in;
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
        tv_head_name.setText("余额充值");
        et_inmoney = findView(R.id.et_inmoney);
        cleartext = findViewClick(R.id.cleartext);
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
                et_inmoney.setText("");
                break;
            case R.id.btn_next://去输入密码弹窗界面
                try {
                    inMoney = Double.valueOf(et_inmoney.getText().toString());
                }catch (Exception e){
                    inMoney = 0d;
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(et_inmoney.getText())){
                    Toast.makeText(MoneyInActivity.this,"请输入充值金额",Toast.LENGTH_SHORT).show();
                }else if (inMoney<0.01d){
                    et_inmoney.setText("");
                    Toast.makeText(MoneyInActivity.this,"请输入充值金额",Toast.LENGTH_SHORT).show();
                }else {//调用接口后 拿着订单号到支付宝支付
                    String formats = String.format("%.2f", inMoney);
                    et_inmoney.setText(formats);
                    inMoney = Double.valueOf(et_inmoney.getText().toString());
                    request_moneyin();
                }

                break;
            default:
                break;
        }
    }

    private void request_moneyin() {
        IntrestBuyNet.launchRechargeTransNew(user.getId(), Long.valueOf(user.getRegionId()),2,inMoney,2,1,"用户在线充值",EWBanlance, new HandleSuccess<MoneyInNew>() {
            @Override
            public void success(MoneyInNew s) {
                if (s.getStatus() == 1){
                    orderNo = s.getData().getOrderId();
                    UIHelper.togoOnLinePayActivity(MoneyInActivity.this,orderNo,inMoney,"recharge",orderNo);

                }else {
                    Toast.makeText(MoneyInActivity.this,"账户正在维护中，稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (resultCode == 1009){
                //获取订单信息成功后finish到成功界面 不成功不finish
                boolean success = data.getBooleanExtra("success",false);
                if (success){
                    request_getmoneyin_result();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void request_getmoneyin_result() {
        Toast.makeText(MoneyInActivity.this,"充值成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MoneyInActivity.this,EWMoneySuccessActivity.class);
        intent.putExtra("type",1);//1充值  2提现
        intent.putExtra("inmoney",inMoney);
        intent.putExtra("bankcard",EWalletActivity.EWBankName+"("+EWalletActivity.WeiHao+")");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        intent.putExtra("createtime",time);
        intent.putExtra("orderno",orderNo);
        if (EWBanlance != null){
            BigDecimal ew_balance = BigDecimal.valueOf(EWBanlance);
            BigDecimal in_money = BigDecimal.valueOf(inMoney);
            BigDecimal now_ew_balance = ew_balance.add(in_money);
            EWBanlance = now_ew_balance.doubleValue();//余额+
        }else {
            EWBanlance = inMoney;//余额 = inMoney
        }
        startActivity(intent);
        MoneyInActivity.this.finish();
//        IntrestBuyNet.launchResult(orderNo, new HandleSuccess<Generic>() {
//            @Override
//            public void success(Generic s) {
//                if (s.getStatus() == 1){
//
//                }else {
//                    Toast.makeText(MoneyInActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}
