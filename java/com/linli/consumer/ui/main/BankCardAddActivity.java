package com.linli.consumer.ui.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Addbank;
import com.linli.consumer.bean.FindBanks;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

public class BankCardAddActivity extends BaseActivity {
    private EditText et_card_person, et_card_num;
    private TextView et_card_name, et_bank_name_a, tv_Provinces;
    private Button btn_card_add;
    private int roleType = 5; //  2 商城  3餐饮  4 服务
    private LinearLayout li_Provinces, li_Branch, lin_et_card_name;
    private Long bankid, Branchid, Provinceid;
    private TextView tv_duigong, tv_duisi;
    private int type = 2; // 1  公  2 私

    private AppContext appContext;
    private User user;
    private boolean Isnumcard = false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_card_add;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        this.mContext = this;
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setText("添加银行卡信息");
        tv_head_name.setOnClickListener(this);
        et_card_person = (EditText) findViewById(R.id.et_card_person);
        et_card_name = (TextView) findViewById(R.id.et_card_name);
        et_card_name.setOnClickListener(this);
        et_bank_name_a = (TextView) findViewById(R.id.et_bank_name_a);
        et_bank_name_a.setOnClickListener(this);
        tv_Provinces = (TextView) findViewById(R.id.tv_Provinces);

        ;
        lin_et_card_name = (LinearLayout) findViewById(R.id.lin_et_card_name);
        li_Branch = (LinearLayout) findViewById(R.id.li_Branch);
        li_Provinces = (LinearLayout) findViewById(R.id.li_Provinces);
        lin_et_card_name.setOnClickListener(this);
        li_Branch.setOnClickListener(this);
        li_Provinces.setOnClickListener(this);


        et_card_num = (EditText) findViewById(R.id.et_card_num);
        btn_card_add = (Button) findViewById(R.id.btn_card_add);
        btn_card_add.setOnClickListener(this);
        tv_duigong = (TextView) findViewById(R.id.tv_duigong);
        tv_duisi = (TextView) findViewById(R.id.tv_duisi);
        tv_duigong.setOnClickListener(this);
        tv_duisi.setOnClickListener(this);
        tv_duigong.setTextColor(0xff000000);
        tv_duisi.setTextColor(0xFFDCDCDC);
    }

    @Override
    protected void initData() {
        dismiss();
        //最多5张
        IntrestBuyNet.findBanks(roleType, user.getId(), 1000, 1, new HandleSuccess<FindBanks>() {
            @Override
            public void success(FindBanks findserviceShopid) {
                if (findserviceShopid.getStatus() == 1) {
                    if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                        if (findserviceShopid.getData().size() >= 5) {
                            Isnumcard = false;
                        } else {
                            Isnumcard = true;
                        }
                    } else {
                        Isnumcard = true;
                    }
                } else {
                    Toast.makeText(appContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        switch (user.getCategory_type()) {
//            case 1:
//                roleType = 2;
//                break;
//            case 2:
//                roleType = 3;
//                break;
//            case 3:
//                roleType = 4;
//                break;
//        }
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_duigong:
                type = 1;
                tv_duigong.setTextColor(0xff000000);
                tv_duisi.setTextColor(0xFFDCDCDC);
                li_Branch.setVisibility(View.VISIBLE);
                li_Provinces.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_duisi:
                type = 2;
                tv_duigong.setTextColor(0xFFDCDCDC);
                tv_duisi.setTextColor(0xff000000);
                li_Branch.setVisibility(View.GONE);
                li_Provinces.setVisibility(View.GONE);
                break;
            case R.id.btn_card_add:
                if (Isnumcard) {
                    if (type == 1) {
                        if (TextUtils.isEmpty(et_card_person.getText().toString().trim())
                                || TextUtils.isEmpty(et_card_name.getText().toString().trim())
                                || TextUtils.isEmpty(et_bank_name_a.getText().toString().trim())
                                || TextUtils.isEmpty(et_card_num.getText().toString().trim())
                                || TextUtils.isEmpty(tv_Provinces.getText().toString().trim())
                                ) {
                            Toast.makeText(BankCardAddActivity.this, "请完整填写信息", Toast.LENGTH_SHORT).show();
                        } else if (et_card_num.getText().length() < 16) {
                            Toast.makeText(BankCardAddActivity.this, "请填写正确银行卡号", Toast.LENGTH_SHORT).show();
                        } else {
                            String person = et_card_person.getText().toString().trim(); //
                            String name = et_card_name.getText().toString().trim();
                            String name_a = et_bank_name_a.getText().toString().trim();
                            String num = et_card_num.getText().toString().trim();
                            String Province = tv_Provinces.getText().toString().trim();
                            IntrestBuyNet.addBank(0, roleType, user.getId(), person, name, name_a, num, Province, bankid, Branchid, Provinceid
                                    , new HandleSuccess<Addbank>() {
                                        @Override
                                        public void success(Addbank addbank) {
                                            if (addbank.getStatus() == 1) {
                                                Intent mIntentb = new Intent();
                                                setResult(200, mIntentb);
                                                BankCardAddActivity.this.finish();
                                                Toast.makeText(BankCardAddActivity.this, "" + addbank.getMsg(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(BankCardAddActivity.this, "" + addbank.getMsg(), Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                        }
                    } else if (type == 2) {
                        if (TextUtils.isEmpty(et_card_person.getText().toString().trim())
                                || TextUtils.isEmpty(et_card_name.getText().toString().trim())
                                || TextUtils.isEmpty(et_card_num.getText().toString().trim())
                                ) {
                            Toast.makeText(BankCardAddActivity.this, "请完整填写信息", Toast.LENGTH_SHORT).show();
                        } else if (et_card_num.getText().length() < 16) {
                            Toast.makeText(BankCardAddActivity.this, "请填写正确银行卡号", Toast.LENGTH_SHORT).show();
                        } else {
                            String person = et_card_person.getText().toString().trim(); //
                            String name = et_card_name.getText().toString().trim();
                            String name_a = "";
                            String num = et_card_num.getText().toString().trim();
                            String Province = "";
                            IntrestBuyNet.addBank(1, roleType, user.getId(), person, name, name_a, num, Province, bankid, Branchid, Provinceid
                                    , new HandleSuccess<Addbank>() {
                                        @Override
                                        public void success(Addbank addbank) {
                                            if (addbank.getStatus() == 1) {
                                                Intent mIntentb = new Intent();
                                                setResult(200, mIntentb);
                                                BankCardAddActivity.this.finish();
                                                Toast.makeText(BankCardAddActivity.this, "" + addbank.getMsg(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(BankCardAddActivity.this, "" + addbank.getMsg(), Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                        }
                    }
                } else {
                    Toast.makeText(appContext, "最多填写5张银行卡", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.et_card_name:
                startActivityForResult(new Intent(this, BankcardpoiActivity.class).putExtra("type", 1), 200);
                break;
            case R.id.et_bank_name_a:
                startActivityForResult(new Intent(this, BankcardpoiActivity.class).putExtra("type", 2), 201);
                break;
            case R.id.li_Provinces:
                startActivityForResult(new Intent(this, BankcardpoiActivity.class).putExtra("type", 3), 202);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 200) {
            et_card_name.setText(data.getStringExtra("bank"));
            bankid = data.getLongExtra("id", 01);
        } else if (resultCode == 201) {
            et_bank_name_a.setText(data.getStringExtra("banks"));
            Branchid = data.getLongExtra("id", 01);
        } else if (resultCode == 202) {
            tv_Provinces.setText(data.getStringExtra("provinces"));
            Provinceid = data.getLongExtra("id", 01);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
