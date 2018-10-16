package com.linli.consumer.widget;

import android.support.v7.widget.RecyclerView;

/**
 * XRecyclerView声明时引入，用于崩溃处理，防止bug
 * */
public class XDefaultItemAnimator extends BaseItemAnimator {

    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {

    }

    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {

    }
}