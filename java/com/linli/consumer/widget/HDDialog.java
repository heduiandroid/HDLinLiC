package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;


/**
 * Created by tomoyo on 2016/11/8.
 */

public class HDDialog extends Dialog {

    private AnimationDrawable mAnimation;
    private ImageView imageView;
    private TextView mLoadingTv;



    public HDDialog(Context context){
        this(context, R.style.widget_mc_dialog);
        setCancelable(true);
        //setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();

    }

    private void initView(){
        setContentView(R.layout.dialog_hd);
        imageView = (ImageView)findViewById(R.id.dialog_hd_im);
        mLoadingTv = (TextView)findViewById(R.id.dialog_hd_tv);

    }
    private void initData(){
        imageView.setBackgroundResource(R.drawable.dialog_anim);
        mAnimation = (AnimationDrawable)imageView.getBackground();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
        mLoadingTv.setText("正在加载");

    }

    public HDDialog(Context context, int theme) {
        super(context, theme);
    }
}
