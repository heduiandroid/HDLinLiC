package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.PaiYiPaiCartAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiCartActivity extends BaseActivity {
    private LinearLayout ll_confirm;//结算按钮
    private TextView tv_title;
    private TextView tv_total;
    private RecyclerView rcv_goods;
    private PaiYiPaiCartAdapter adapter;
    private ArrayList<GoodsBean> list;
    private AppContext appContext;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pai_yi_pai_cart;
    }

    @Override
    protected void initView() {
        dismiss();
        ll_confirm = findViewClick(R.id.ll_confirm);
        tv_title = findViewClick(R.id.tv_title);
        tv_total = findView(R.id.tv_total);
        rcv_goods = findView(R.id.rcv_goods);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_goods.setLayoutManager(manager);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        list = (ArrayList<GoodsBean>) intent.getSerializableExtra("goods");
        if (list.size() > 0){
            adapter = new PaiYiPaiCartAdapter(list,this);
            rcv_goods.setAdapter(adapter);
            upDate();
        }else {
            closeDialog();
            Toast.makeText(PaiYiPaiCartActivity.this,"右划即可添加购物车哦~",Toast.LENGTH_SHORT).show();
        }
    }
    private void closeDialog(){
        new CountDownTimer(3 * 1000, 1000) {//三秒之后关闭页面并提示
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                PaiYiPaiCartActivity.this.finish();
            }
        }.start();
    }
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.ll_confirm:
                if (user != null){
                    if (list.size()>0){
                        Long shopId = list.get(0).getShopId();
                        if (shopId!=null){
                            switch (list.get(0).getType()){
                                case 1:
                                    UIHelper.togoShopFoodOrderActivity(PaiYiPaiCartActivity.this, 0l, shopId, FOOD_MAIN, 0l,null);
                                    break;
                                case 2:
                                    UIHelper.togoShopFoodOrderActivity(PaiYiPaiCartActivity.this, 0l, shopId, SHOP_MAIN, 0l,null);
                                    break;
                            }
                        }else {
                            finish();
                            Toast.makeText(PaiYiPaiCartActivity.this,"无可结算的商品",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(PaiYiPaiCartActivity.this,"无可结算的商品",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            default:
                break;
        }
    }
    /*
        计算/修改商品总价格
     */
    public void upDate(){
        //计算/修改商品总价格
        double price = 0;
        if (list.size() > 0){
            for (int i = 0;i < list.size();i++){
                price = price + list.get(i).getNumber()*list.get(i).getPrice();
            }
        }
        tv_total.setText("￥"+ BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
    }
    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
