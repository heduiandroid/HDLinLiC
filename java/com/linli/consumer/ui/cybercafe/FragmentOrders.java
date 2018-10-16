package com.linli.consumer.ui.cybercafe;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseFragment;

/**
 * Created by hasee on 2018/9/29.
 */

public class FragmentOrders extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_orders;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        dismiss();
    }



    public FragmentOrders() {
    }
    public static FragmentOrders newInstance() {
        FragmentOrders fragment = new FragmentOrders();
        return fragment;
    }
}
