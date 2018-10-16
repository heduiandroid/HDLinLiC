package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.linli.consumer.R;

public class ShopTypeChooseActovity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type_choose_actovity);
        init();
    }
    private void init() {
        findViewById(R.id.tv_shitidian).setOnClickListener(this);
        findViewById(R.id.tv_changjia).setOnClickListener(this);
        findViewById(R.id.tv_quyuyunyingshang).setOnClickListener(this);
        findViewById(R.id.ll_all).setOnClickListener(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_shitidian:
                intent.putExtra("type",1);
                setResult(601, intent);
                finish();
                break;
            case R.id.tv_changjia:
                intent.putExtra("type",2);
                setResult(601,intent);
                finish();
                break;
            case R.id.tv_quyuyunyingshang:
                intent.putExtra("type",3);
                setResult(601,intent);
                finish();
                break;
            default:
                break;
        }
    }
}
