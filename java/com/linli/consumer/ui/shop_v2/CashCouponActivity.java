package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.CashCouponAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.CouponsBean;

import java.util.ArrayList;

public class CashCouponActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private GridView gv_cash_coupons;
    private TextView tv_no_coupon;
    private ArrayList<CouponsBean.DataBean> cashCoupons;
    private CashCouponAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_coupon;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.ll_cash_coupons);
        tv_no_coupon = findViewClick(R.id.tv_no_coupon);
        gv_cash_coupons = findView(R.id.gv_cash_coupons);
        gv_cash_coupons.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        cashCoupons = (ArrayList<CouponsBean.DataBean>) getIntent().getSerializableExtra("ccps");
        adapter = new CashCouponAdapter(cashCoupons,this);
        gv_cash_coupons.setAdapter(adapter);
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.tv_no_coupon:
                CouponsBean.DataBean ccp = new CouponsBean.DataBean();
                ccp.setId(0l);
                ccp.setCouponSum(0d);
                ccp.setType(0);
                Intent intent = new Intent();
                intent.putExtra("ccp",ccp);
                setResult(1718,intent);
                finish();
                break;
            case R.id.ll_cash_coupons:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CouponsBean.DataBean ccp = cashCoupons.get(i);
        Intent intent = new Intent();
        intent.putExtra("ccp",ccp);
        setResult(1718,intent);
        finish();
    }
}
