package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.linli.consumer.R;


/**
 * Created by tomoyo on 2016/11/8.
 */

public class ProDialog extends Dialog {


    public ProDialog(Context context){
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
        setContentView(R.layout.dialog_pro);

    }
    private void initData(){

    }

    public ProDialog(Context context, int theme) {
        super(context, theme);
    }
}
