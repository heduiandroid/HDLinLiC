package com.linli.consumer.ui.main;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.ReturnMoneyResonsAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Order;
import com.linli.consumer.domain.ReturnReson;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ReturnMoneyActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView lv_return_resons;
    private Button btn_cancel,btn_confirm;

    private Order order = null;
    private AppContext appContext;
    private User user;

    private ArrayList<ReturnReson> resons = new ArrayList<>();
    private ReturnMoneyResonsAdapter adapter;
    private ReturnReson reson = null;//当前用户选择的退款原因
    @Override
    protected int getLayoutId() {
        return R.layout.activity_return_money;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.ll);
        lv_return_resons = findView(R.id.lv_return_resons);
        lv_return_resons.setOnItemClickListener(this);
        btn_cancel = findViewClick(R.id.btn_cancel);
        btn_confirm = findViewClick(R.id.btn_confirm);
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        adapter = new ReturnMoneyResonsAdapter(resons,this);
        lv_return_resons.setAdapter(adapter);
        order = (Order) getIntent().getSerializableExtra("order");
        if (order != null){
            insertMyListDatas();
            //刷新数据
            adapter.notifyDataSetChanged();
        }else {
            showToast("未能查到此订单可退款信息");
            dismiss();
            finish();
        }
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm:
                if (reson != null){
                    ConfirmReturnReson();
                }else {
                    showToast("请选择退款原因");
                }
                break;
        }
    }
    private void ConfirmReturnReson() {
        Integer orderType = order.getType();
        switch (orderType){
            case 1:
            case 2:
                orderType = 2;//餐饮
                break;
            case 3:
                orderType = 1;//商城
                break;
            case 4:
                orderType = 3;//服务
                break;
            default:
                return;//stop here
        }
        BigDecimal returnMoney = new BigDecimal(0d);

        IntrestBuyNet.refundOrderAdd(user.getId(), order.getId(), orderType, returnMoney, reson.getReson(),
                new HandleSuccess<Generic>() {
                    @Override
                    public void success(Generic s) {
                        switch (s.getStatus()){
                            case 1:
                                showToast("退款申请提交成功");
                                break;
                            case 9:
                                showToast("退款申请正在处理中");
                                break;
                            default:
                                showToast("申请失败，请稍后重试");
                                break;
                        }
                        ReturnMoneyActivity.this.finish();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        for (int i = 0;i<resons.size();i++){
            resons.get(i).setChoosed(false);//先全标记为未选中
        }
        resons.get(position).setChoosed(true);//再标记当前为选中
        reson = resons.get(position);//记录已选中条目
        //刷新数据
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    //插入自定义的类假数据
    private void insertMyListDatas() {
        switch (order.getStatus()){
            case 1://待提供 取消订单并退款
                ReturnReson reson1 = new ReturnReson();
                reson1.setChoosed(false);
                reson1.setReson(getResources().getString(R.string.reson1));
                reson1.setResonExtra("");
                ReturnReson reson2 = new ReturnReson();
                reson2.setChoosed(false);
                reson2.setReson(getResources().getString(R.string.reson2));
                reson2.setResonExtra("");
                ReturnReson reson3 = new ReturnReson();
                reson3.setChoosed(false);
                reson3.setReson(getResources().getString(R.string.reson3));
                reson3.setResonExtra("");
                resons.add(reson1);
                resons.add(reson2);
                resons.add(reson3);
                break;
            case 2://带接收 退款退货
            case 3://交易完成 退款退货
                ReturnReson reson4 = new ReturnReson();
                reson4.setChoosed(false);
                reson4.setReson(getResources().getString(R.string.reson4));
                reson4.setResonExtra(getResources().getString(R.string.reson4_extra));
                ReturnReson reson5 = new ReturnReson();
                reson5.setChoosed(false);
                reson5.setReson(getResources().getString(R.string.reson5));
                reson5.setResonExtra(getResources().getString(R.string.reson5_extra));
                resons.add(reson4);
                resons.add(reson5);
                break;
            default:
                break;
        }
    }
}
