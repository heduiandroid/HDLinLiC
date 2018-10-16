package com.linli.consumer.ui.main;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.ui.services.SubmitOrderServicesActivity;
import com.linli.consumer.ui.shop_v2.ShopFoodOrderActivity;

public class PaySuccessActivity extends Activity implements View.OnClickListener {
    private TextView tv_result;
    private boolean isOffline = false;
    private boolean isOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_go_orders).setOnClickListener(this);
        findViewById(R.id.tv_go_finish).setOnClickListener(this);
        TextView tvhead = (TextView) findViewById(R.id.tv_head_name);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tvhead.setOnClickListener(this);
        tvhead.setText("处理结果");
        isOffline = getIntent().getBooleanExtra("isOffline",false);
        isOrder = getIntent().getBooleanExtra("isOrder",false);
        if (isOffline){
            tv_result.setText("恭喜您下单成功！");
        }
        if (isOrder){
            tv_result.setText("恭喜您预约成功！");
        }
        closeOrder();
    }

    /**
     * 在购买成功后，将订单页面关闭
     **/
    private void closeOrder(){
        for(int i = 0 ; i < ActivityCollector.activities.size() ; i ++){
            if(ActivityCollector.activities.get(i) instanceof ShopFoodOrderActivity || ActivityCollector.activities.get(i) instanceof SubmitOrderServicesActivity){
                ActivityCollector.activities.get(i).finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
            case R.id.tv_go_finish:
                PaySuccessActivity.this.finish();
                break;
            case R.id.tv_go_orders:
                PaySuccessActivity.this.finish();
                UIHelper.togoOrdersActivity(this);
            default:
                break;
        }
    }
}
