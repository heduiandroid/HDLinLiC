package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.linli.consumer.R;


/**
 * Created by tomoyo on 2016/11/8.
 */

public class CouponDialog extends Dialog {


    private RelativeLayout useRl;



    public CouponDialog(Context context){
        this(context, R.style.widget_shop_cart_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();

    }

    private void initView(){
        setContentView(R.layout.coupon_dialog_widget);
        useRl = (RelativeLayout) findViewById(R.id.coupon_dialog_use_rl);
        useRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO 跳转到某个界面
            }
        });

    }

    public CouponDialog(Context context, int theme) {
        super(context, theme);
    }
}
