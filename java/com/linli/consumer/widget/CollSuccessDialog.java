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

public class CollSuccessDialog extends Dialog {

    private AnimationDrawable mAnimation;
    private ImageView imageView;
    private TextView mLoadingTv;



    public CollSuccessDialog(Context context){
        this(context, R.style.widget_mc_dialog);
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
        setContentView(R.layout.dialog_coll_success);


    }

    public CollSuccessDialog(Context context, int theme) {
        super(context, theme);
    }
}
