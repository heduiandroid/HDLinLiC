package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.CashCouponRedBagAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.CouponsBean;

import java.util.ArrayList;

public class CashCouponRedBagActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView lv_cash_coupons;
    private TextView tv_no_coupon;
    private ArrayList<CouponsBean.DataBean> cashCoupons;
    private CashCouponRedBagAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_coupon_redbag;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView headName = findViewClick(R.id.tv_head_name);
        headName.setText(getResources().getString(R.string.myredbags));
        findViewClick(R.id.ll_cash_coupons);
        tv_no_coupon = findViewClick(R.id.tv_no_coupon);
        lv_cash_coupons = findView(R.id.lv_cash_coupons);
        lv_cash_coupons.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        cashCoupons = (ArrayList<CouponsBean.DataBean>) getIntent().getSerializableExtra("rcps");
        adapter = new CashCouponRedBagAdapter(cashCoupons,this);
        lv_cash_coupons.setAdapter(adapter);
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_no_coupon:
                CouponsBean.DataBean rcp = new CouponsBean.DataBean();
                rcp.setId(0l);
                rcp.setCouponSum(0d);
                rcp.setType(0);
                Intent intent = new Intent();
                intent.putExtra("rcp",rcp);
                setResult(1719,intent);
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CouponsBean.DataBean rcp = cashCoupons.get(i);
        Intent intent = new Intent();
        intent.putExtra("rcp",rcp);
        setResult(1719,intent);
        finish();
    }
}
