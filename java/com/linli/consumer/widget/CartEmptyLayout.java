package com.linli.consumer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;

import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/13.
 */

public class CartEmptyLayout extends LinearLayout {

    private RelativeLayout togoRl;

    public CartEmptyLayout(Context context) {
        this(context,null);

    }

    public CartEmptyLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.cart_empty,this);
        togoRl = (RelativeLayout)findViewById(R.id.cart_empty_togo_rl);
        togoRl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.togoShopActivity(context,SHOP_MAIN);
            }
        });
    }
}
