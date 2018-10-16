package com.linli.consumer.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FindAccounts;
import com.linli.consumer.bean.FindBanks;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CountNumberView;

import java.util.ArrayList;
import java.util.List;

public class EWalletActivity extends BaseActivity {
    private CountNumberView tv_balance;//余额数
    private Button btn_moneyout;//提现
    private Button btn_moneyin;//充值
    private Button btn_Bankcard;//银行卡管理
    private AppContext appContext;
    private User user;
    public static Double EWBanlance = null;//钱包余额
    public static Long EWbankId = 0l; //银行id
    public static Long bankBranchCode = 0l; //记录支行的id 判断 公  私
    public static String EWAccountName = null;//账户姓名
    public static String EWBankName = null;//银行卡所属银行
    public static String EWCardNumber = null;//银行卡号
    public static String WeiHao = null;//银行卡尾号（截取银行卡号得到）
    private TextView tv_Accounttip;
    private int roleType = 5; //  2 商城  3餐饮  4 服务  5 用户
    private int bankstauts = 0; //  1 正常 2. 冻结 3. 危险
    public List<FindBanks.DataBean> data = new ArrayList<FindBanks.DataBean>();
    private Boolean IsBandcard = false;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ewallet;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        init();
    }

    @Override
    protected void initData() {
//        dismiss();
    }

    private void request_balance_datas() {
//        showSimpleDialog();
//        IntrestBuyNet.queryUserAccout(user.getId(), new HandleSuccess<AccountInfo>() {
//            @Override
//            public void success(AccountInfo s) {
//                if (s.getStatus() == 1) {
//                    EWBanlance = s.getData().getRemainAmount();
//                    if (s.getData().getCardNumber() == null || s.getData().getCardNumber().equals("null") || s.getData().getCardNumber().equals("")) {
//                        isBindedCard = false;
//                    } else {
//                        isBindedCard = true;
//                    }
//                    if (s.getData().getAccountName() == null || s.getData().getAccountName().equals("null")) {
//                        EWAccountName = "";
//                    } else {
//                        EWAccountName = s.getData().getAccountName();
//                    }
//                    if (s.getData().getBankName() == null || s.getData().getBankName().equals("null")) {
//                        EWBankName = "";
//                    } else {
//                        EWBankName = s.getData().getBankName();
//                    }
//                    if (s.getData().getCardNumber() == null || s.getData().getCardNumber().equals("null")) {
//                        EWCardNumber = "";
//                        WeiHao = "";
//                    } else {
//                        EWCardNumber = s.getData().getCardNumber();
//                        if (EWCardNumber != null) {
//                            WeiHao = EWCardNumber.substring(EWCardNumber.length() - 4, EWCardNumber.length());
//                        }
//                    }
//                    if (EWBanlance != null) {
//                        tv_balance.setText(EWBanlance.toString());
//                        if (EWBanlance == 0d) {
//                            tv_balance.setText("0.00");
//                        }
//                    }
//                } else if (s.getStatus() == 2) {
//                    finish();
//                    Toast.makeText(EWalletActivity.this, "获取账户信息失败,建议您重新登录后再试", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(EWalletActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
//                }
//                dismissSimDialog();
//            }
//        });


//         账户状态：0正常，1冻结，2危险
//         3 商家  4 用户
        IntrestBuyNet.findAccounts(4, user.getId(), new HandleSuccess<FindAccounts>() {
            @Override
            public void success(final FindAccounts findAccounts) {
                if (findAccounts.getStatus() == 1) {
                    switch (findAccounts.getData().get(0).getStatus()) {  // // 账户状态：0正常，1冻结，2危险
                        case 0:  //
                            bankstauts = 1;
                            EWBanlance = findAccounts.getData().get(0).getBalance();
                            Log.v("wang", EWBanlance + "");
                            if (EWBanlance != null) {
                                tv_balance.setText(EWBanlance.toString());
                                tv_balance.showNumberWithAnimation(EWBanlance.floatValue(), CountNumberView.FLOATREGEX);
                            }

                            IntrestBuyNet.findBanks(roleType, user.getId(), 1000, 1, new HandleSuccess<FindBanks>() {
                                @Override
                                public void success(FindBanks findserviceShopid) {
                                    if (findserviceShopid.getStatus() == 1) {
                                        if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                                            for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                                FindBanks.DataBean date = findserviceShopid.getData().get(i);
                                                data.add(date);
                                            }
                                            EWbankId = findserviceShopid.getData().get(0).getId();
                                            EWAccountName = findserviceShopid.getData().get(0).getPerson();
                                            EWBankName = findserviceShopid.getData().get(0).getBank();
                                            EWCardNumber = findserviceShopid.getData().get(0).getNumber();//卡号
                                            bankBranchCode = findserviceShopid.getData().get(0).getBankBranchCode();
                                            Log.v("wang", "bankBranchCode   " + bankBranchCode);
                                            WeiHao = EWCardNumber.substring(EWCardNumber.length() - 4, EWCardNumber.length());
                                            IsBandcard = true;
                                        } else {
                                            Log.v("wang", "没有银行卡提示去绑定");
                                            IsBandcard = false;
                                        }
                                        dismiss();
                                    } else {
                                        dismiss();
                                        Toast.makeText(appContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        case 1:
                            bankstauts = 2;
                            tv_Accounttip.setText("账户已冻结");
                            break;
                        case 2:
                            bankstauts = 3;
                            tv_Accounttip.setText("账户可用余额(危险)");
                            break;
                        default:
                            bankstauts = 0;
                            Toast.makeText(EWalletActivity.this, "服务器链接失败", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    dismiss();
                } else {
                    dismiss();
                    Toast.makeText(EWalletActivity.this, "服务器链接失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("账户余额");
        TextView tv_head_right = findViewClick(R.id.tv_head_right);
        tv_head_right.setText("余额明细");
        ImageView iv = findView(R.id.iv_head_right);
        iv.setVisibility(View.GONE);
        tv_balance = (CountNumberView) findViewById(R.id.tv_balance);
        btn_moneyout = findViewClick(R.id.btn_moneyout);
        btn_moneyin = findViewClick(R.id.btn_moneyin);
        btn_Bankcard = findViewClick(R.id.btn_Bankcard);
        tv_Accounttip = (TextView) findViewById(R.id.tv_Accounttip);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.btn_moneyout://去提现界面
//                if (isBindedCard) {
//                    startActivity(new Intent(EWalletActivity.this, MoneyOutActivity.class));
//                } else {//去设置支付密码界面，成功后再去绑卡界面
//                    startActivity(new Intent(EWalletActivity.this, EWSettingPayPwdActivity.class));
//                }

                switch (bankstauts) {
                    case 0:
                        Toast.makeText(this, "抱歉，钱包系统升级中，暂不可用", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if (IsBandcard) {
                            startActivity(new Intent(EWalletActivity.this, MoneyOutActivity.class));
                        } else {
                            startActivityForResult(new Intent(EWalletActivity.this, BankCardAddActivity.class), 200);
                        }
                        break;
                    case 2:
                        Toast.makeText(this, "您的账号已冻结,如有疑问,请联系当地运营商", Toast.LENGTH_SHORT).show();
                        break;
                    case 3://危险但是可以提现
                        if (IsBandcard) {
                            startActivity(new Intent(EWalletActivity.this, MoneyOutActivity.class));
                        } else {
                            startActivityForResult(new Intent(EWalletActivity.this, BankCardAddActivity.class), 200);
                        }
                        break;
                }
                break;
            case R.id.btn_moneyin://去充值界面
                switch (bankstauts) {
                    case 0:
                        Toast.makeText(this, "抱歉，钱包系统升级中，暂不可用", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                    case 3://危险但是可以充值
                        Intent intent = new Intent(EWalletActivity.this, MoneyInActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(this, "您的账号已冻结,如有疑问,请联系当地运营商", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.tv_head_right:
                startActivity(new Intent(EWalletActivity.this, EWBankdetailActivity.class));
                break;
            case R.id.btn_Bankcard:
                Intent i = new Intent(EWalletActivity.this, BankCardActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
//        if (EWBanlance != null) {
//            tv_balance.setText(EWBanlance.toString());
//            if (EWBanlance == 0d) {
//                tv_balance.setText("0.00");
//            }
//        }
        if (EWBanlance != null) {
            tv_balance.setText(EWBanlance.toString());
            tv_balance.showNumberWithAnimation(EWBanlance.floatValue(), CountNumberView.FLOATREGEX);
        }
        request_balance_datas();
        super.onResume();
    }
}
