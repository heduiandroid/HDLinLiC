package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.squareup.picasso.Picasso;


/**
 * Created by tomoyo on 2016/11/8.
 */

public class QRDialog extends Dialog {



    private CircleImageView avatarIm;
    private SimpleDraweeView qrIm;
    private ImageView closeIm;

    private String avatarStr;
    private String qrStr;
    private Context context;



    public QRDialog(Context context,String avatarStr,String qrStr){
        this(context, R.style.widget_shop_cart_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.avatarStr = avatarStr;
        this.qrStr = qrStr;
        this.context = context;
        this.avatarStr = avatarStr;
        this.qrStr = qrStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();

    }

    private void initView(){
        setContentView(R.layout.qr_dialog_widget);
        avatarIm = (CircleImageView) findViewById(R.id.qr_dialog_avatar_cim);
        qrIm = (SimpleDraweeView)findViewById(R.id.qr_dialog_qr_im);
        closeIm = (ImageView)findViewById(R.id.qr_dialog_close);
        closeIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRDialog.this.dismiss();
            }
        });
        if(!TextUtils.isEmpty(avatarStr)){
            Picasso.with(context).load(avatarStr).fit().into(avatarIm);
        }
        if(!TextUtils.isEmpty(qrStr)){
            qrIm.setImageURI(qrStr);
        }

    }
    private void initData(){

    }

    public QRDialog(Context context, int theme) {
        super(context, theme);
    }
}
