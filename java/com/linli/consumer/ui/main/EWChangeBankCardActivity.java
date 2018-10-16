package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FindBanks;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;
import java.util.List;

public class EWChangeBankCardActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView iv_close;//关闭界面
    private ListView mlv_bandedbanks;//已绑定的银行卡列表
    private TextView tv_bankinfo;////当前银行卡信息
    private LinearLayout ll_mybankinfo;////用于处理点击当前银行卡信息
    private LinearLayout ll_addbankcard;//添加新的银行卡

    private BankCardChooseAdapter adapter;//
    private List<FindBanks.DataBean> data = new ArrayList<FindBanks.DataBean>();
    private int roleType = 5; //  2 商城  3餐饮  4 服务

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ewchange_bank_card;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        dismiss();
        getdata();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.ll_addbankcard://去添加新的银行卡界面
                startActivity(new Intent(EWChangeBankCardActivity.this, EWAddBankCardActivity.class));
                break;
            case R.id.ll_mybankinfo:////
                finish();
                break;
            default:
                break;
        }
    }

    private void init() {
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        mlv_bandedbanks = (ListView) findViewById(R.id.mlv_bandedbanks);
        mlv_bandedbanks.setOnItemClickListener(this);
        tv_bankinfo = (TextView) findViewById(R.id.tv_bankinfo);
        ll_mybankinfo = (LinearLayout) findViewById(R.id.ll_mybankinfo);
        ll_mybankinfo.setOnClickListener(this);
        ll_addbankcard = (LinearLayout) findViewById(R.id.ll_addbankcard);
        ll_addbankcard.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        tv_bankinfo.setText(EWalletActivity.EWBankName + "(" + EWalletActivity.WeiHao + ")");
        super.onResume();
    }
    private void getdata() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
       showDialog();
        IntrestBuyNet.findBanks(roleType, user.getId(), 1000, 1, new HandleSuccess<FindBanks>() {
            @Override
            public void success(FindBanks findserviceShopid) {
                if (findserviceShopid.getStatus() == 1) {
                    if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                        for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                            FindBanks.DataBean date = findserviceShopid.getData().get(i);
                            data.add(date);
                        }
                        adapter = new BankCardChooseAdapter(data, EWChangeBankCardActivity.this);
                        mlv_bandedbanks.setAdapter(adapter);
                    }
                    dismiss();
                } else {
                    dismiss();
                    Toast.makeText(appContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        EWalletActivity.EWBankName = data.get(position).getBank();
        EWalletActivity.EWCardNumber = data.get(position).getNumber();//卡号
        EWalletActivity.EWbankId = data.get(position).getId();//id
        EWalletActivity.bankBranchCode = data.get(position).getBankBranchCode();
        EWalletActivity.WeiHao = EWalletActivity.EWCardNumber.substring(EWalletActivity.EWCardNumber.length() - 4, EWalletActivity.EWCardNumber.length());
        setResult(1700, intent);
        finish();
    }
}
