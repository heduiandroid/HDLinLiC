package com.linli.consumer.ui.cybercafe;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseFragment;

/**
 * Created by hasee on 2018/9/29.
 */

public class FragmentMine extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        dismiss();
    }



    public FragmentMine() {
    }
    public static FragmentMine newInstance() {
        FragmentMine fragment = new FragmentMine();
        return fragment;
    }
}
