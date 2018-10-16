package com.linli.consumer.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.BankDetials;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoneyOutActivity extends BaseActivity {
    private TextView tv_totalmoney;//可提现金额
    private TextView tv_outallmoney;//全部提现
    private TextView tv_outbank;//当前银行卡
    private EditText et_outmoney;//提现金额
    private LinearLayout ll_changebankcard;//改变提现到的银行卡
    private Button btn_next;//下一步
    private AppContext appContext;
    private User user;
    private Double outmoney;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_money_out;
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

    private void setMyAccountDatas() {
        tv_totalmoney.setText("可提现额度" + EWalletActivity.EWBanlance + "元");
        tv_outbank.setText(EWalletActivity.EWBankName + "(" + EWalletActivity.WeiHao + ")");
        Log.v("wang", "     " + EWalletActivity.bankBranchCode);
        if (EWalletActivity.bankBranchCode != null) {
            Log.v("wang", "这是一张  对公卡");
            et_outmoney.setHint("最低100 最高100w");
        } else {
            Log.v("wang", "这是一张  对私卡");
            et_outmoney.setHint("最低100 最高20w");
        }
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("余额提现");
        tv_totalmoney = findView(R.id.tv_totalmoney);
        tv_outallmoney = findViewClick(R.id.tv_outallmoney);
        tv_outbank = findView(R.id.tv_outbank);
        et_outmoney = findView(R.id.et_outmoney);
        ll_changebankcard = findViewClick(R.id.ll_changebankcard);
        btn_next = findViewClick(R.id.btn_next);
    }

    @Override
    protected void onResume() {
        setMyAccountDatas();
        super.onResume();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.tv_head_name:
                MoneyOutActivity.this.finish();
                break;
            case R.id.tv_outallmoney://全部提现按钮
                if (EWalletActivity.EWBanlance > 0d) {
                    et_outmoney.setText(EWalletActivity.EWBanlance.toString());
                }
                break;
            case R.id.ll_changebankcard://改变提现银行卡，调银行卡列表弹窗
                startActivity(new Intent(MoneyOutActivity.this, EWChangeBankCardActivity.class));
                break;
            case R.id.btn_next://下一步，调输入密码弹窗
//                outmoney = Double.valueOf(et_outmoney.getText().toString());
                if (TextUtils.isEmpty(et_outmoney.getText())) {
                    Toast.makeText(MoneyOutActivity.this, "请输入提现金额", Toast.LENGTH_SHORT).show();
                } else if (et_outmoney.getText().subSequence(0, 1).equals(".") || et_outmoney.getText().toString().equals(".")) {
                    et_outmoney.setText("");
                    Toast.makeText(MoneyOutActivity.this, "请正确输入提现金额", Toast.LENGTH_SHORT).show();
                } else if (Double.valueOf(et_outmoney.getText().toString()) > EWalletActivity.EWBanlance) {
                    et_outmoney.setText("");
                    Toast.makeText(MoneyOutActivity.this, "余额不足", Toast.LENGTH_SHORT).show();
                } else {
                    outmoney = Double.valueOf(et_outmoney.getText().toString());
                    String formats = String.format("%.2f", outmoney);
                    et_outmoney.setText(formats);
                    outmoney = Double.valueOf(et_outmoney.getText().toString());
//                    Intent intent = new Intent(MoneyOutActivity.this,DialogInputPasswordActivity.class);
//                    intent.putExtra("userid",user.getId());
//                    intent.putExtra("outmoney",outmoney);
//                    startActivityForResult(intent,309);
                    if (EWalletActivity.bankBranchCode != null) {
                        Log.v("wang", "这是一张  对公卡");
                        if (outmoney == 0 || outmoney == 0.0 || outmoney < 100 || outmoney > 1000000) {
                            Toast.makeText(MoneyOutActivity.this, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                        } else {
                            getwithdraw();
                        }
                    } else {
                        Log.v("wang", "这是一张  对私卡");
                        if (outmoney == 0 || outmoney == 0.0 || outmoney < 100 || outmoney > 200000) {
                            Toast.makeText(MoneyOutActivity.this, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                        } else {
                            getwithdraw();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private void getwithdraw() {
        IntrestBuyNet.cashAccounting(user.getId(), 3, outmoney, Long.decode(user.getRegionId() + ""),
                EWalletActivity.EWbankId, EWalletActivity.EWBanlance, new HandleSuccess<BankDetials>() {
                    @Override
                    public void success(BankDetials bankDetials) {
                        if (bankDetials.getStatus() == 1) {
                            Intent intent = new Intent(MoneyOutActivity.this, EWMoneySuccessActivity.class);
                            intent.putExtra("type", 2);
                            intent.putExtra("outmoney", outmoney);
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(System.currentTimeMillis());
                            String time = format.format(date);
                            intent.putExtra("createtime", time);
                            if (EWalletActivity.EWBanlance != null) {
                                BigDecimal ew_balance = BigDecimal.valueOf(EWalletActivity.EWBanlance);
                                BigDecimal out_money = BigDecimal.valueOf(outmoney);
                                BigDecimal now_ew_balance = ew_balance.subtract(out_money);
                                EWalletActivity.EWBanlance = now_ew_balance.doubleValue();
                            }
                            startActivity(intent);
                            MoneyOutActivity.this.finish();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (resultCode == 309) {
                boolean success = data.getBooleanExtra("success", false);
                if (success) {
                    Intent intent = new Intent(MoneyOutActivity.this, EWMoneySuccessActivity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("outmoney", outmoney);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    String time = format.format(date);
                    intent.putExtra("createtime", time);
                    intent.putExtra("bankcard", EWalletActivity.EWBankName + "(" + EWalletActivity.WeiHao + ")");
                    if (EWalletActivity.EWBanlance != null) {
                        BigDecimal ew_balance = BigDecimal.valueOf(EWalletActivity.EWBanlance);
                        BigDecimal out_money = BigDecimal.valueOf(outmoney);
                        BigDecimal now_ew_balance = ew_balance.subtract(out_money);
                        EWalletActivity.EWBanlance = now_ew_balance.doubleValue();
                    }
                    startActivity(intent);
                    MoneyOutActivity.this.finish();
                } else {
                    Toast.makeText(MoneyOutActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
