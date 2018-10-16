package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.linli.consumer.R;


/**
 * Created by tomoyo on 2016/11/8.
 */

public class CartDialog extends Dialog {

    private TextView deleteTv;
    private TextView cancelTv;


    public CartDialog(Context context, int theme) {
        super(context, theme);
    }


    public CartDialog(Context context){
        this(context, R.style.widget_mc_cart_delete_dialog);
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
        setContentView(R.layout.dialog_cart);
        deleteTv = (TextView)findViewById(R.id.delete);
        cancelTv = (TextView)findViewById(R.id.cancel);
        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteGoods != null){
                    deleteGoods.delete();
                    dismiss();
                }
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartDialog.this.dismiss();
            }
        });

    }
    public interface DeleteGoods{
        void delete();
    }
    private DeleteGoods deleteGoods;

    public void setDeleteGoods(DeleteGoods deleteGoods) {
        this.deleteGoods = deleteGoods;
    }
}
