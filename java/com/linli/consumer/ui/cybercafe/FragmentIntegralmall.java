package com.linli.consumer.ui.cybercafe;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseFragment;

/**
 * Created by hasee on 2018/9/29.
 */

public class FragmentIntegralmall extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integralmall;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        dismiss();
    }



    public FragmentIntegralmall() {
    }
    public static FragmentIntegralmall newInstance() {
        FragmentIntegralmall fragment = new FragmentIntegralmall();
        return fragment;
    }
}
