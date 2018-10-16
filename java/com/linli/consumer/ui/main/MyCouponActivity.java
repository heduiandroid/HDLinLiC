package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.CouponAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;

public class MyCouponActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView lv_coupons;
    private TextView tv_nodata;
    private ArrayList<CouponsBean.DataBean> coupons = new ArrayList<>();
    private CouponAdapter adapter;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("优惠券");
        lv_coupons = findView(R.id.lv_coupons);
        lv_coupons.setOnItemClickListener(this);
        adapter = new CouponAdapter(coupons,this);
        lv_coupons.setAdapter(adapter);
        tv_nodata = findView(R.id.tv_nodata);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        request_my_coupons();
        super.onResume();
    }

    private void request_my_coupons() {
        IntrestBuyNet.queryStoreCoupon(1L, user.getId(), null,new HandleSuccess<CouponsBean>() {
            @Override
            public void success(CouponsBean s) {
                dismiss();
                if (s.getStatus() == 0){
                    coupons.clear();
                    if (s.getData() != null && s.getData().size() > 0){
                        for (int i = 0;i < s.getData().size();i++){
                            if (s.getData().get(i).getType() != 5) {//5是红包 这里不做红包的展示
                                coupons.add(s.getData().get(i));
                            }
                        }
                        if (coupons.size() > 0){
                            tv_nodata.setVisibility(View.GONE);
                            lv_coupons.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        }
                    }else {
                        tv_nodata.setVisibility(View.VISIBLE);
                        lv_coupons.setVisibility(View.GONE);
                    }
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_coupons.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(MyCouponActivity.this,ShowQRCodeAcitivity.class);
        intent.putExtra("couponid",coupons.get(position).getId());
        startActivity(intent);
    }
}
