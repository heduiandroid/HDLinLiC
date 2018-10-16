package com.linli.consumer.ui.main;

import android.view.MotionEvent;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;

import io.rong.imkit.RongIM;

public class ConnectToSellerActivity extends BaseActivity {

    private String phoneNum = null;
    private String shopUserId = null;
    private String shopName = null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_connect_to_seller;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.ll_connect);
        findViewClick(R.id.ll_call_num);
        findViewClick(R.id.ll_rongcloud);
    }

    @Override
    protected void initData() {
        //获取intent后dismiss
        phoneNum = getIntent().getStringExtra("phoneNum");
        shopUserId = getIntent().getStringExtra("shopUserId");
        shopName = getIntent().getStringExtra("shopName");
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.ll_call_num://打电话
                if (phoneNum != null){
                    UIHelper.callThePhoneNumber(ConnectToSellerActivity.this,phoneNum);
                }else {
                    showToast("该商家没有留下电话联系方式");
                }
                finish();
                break;
            case R.id.ll_rongcloud://融云
                if (shopUserId != null && shopName != null){
                    //去聊天界面
                    if (RongIM.getInstance() != null){
                        RongIM.getInstance().startPrivateChat(ConnectToSellerActivity.this,shopUserId.trim(),shopName);
                    }
                }else {
                    showToast("商户正忙，请稍后再试");
                }
                finish();
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
}
