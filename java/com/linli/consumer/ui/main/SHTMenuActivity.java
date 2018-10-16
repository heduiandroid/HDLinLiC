package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.linli.consumer.R;

public class SHTMenuActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shtmenu);
        init();
    }

    private void init() {
        findViewById(R.id.rl_order_food).setOnClickListener(this);
        findViewById(R.id.rl_shopping).setOnClickListener(this);
        findViewById(R.id.rl_services).setOnClickListener(this);
        findViewById(R.id.rl_allcate).setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();// 0-全部1-商城2-订餐3-服务
        switch (v.getId()){
            case R.id.rl_shopping:
                intent.putExtra("cate",1);
                setResult(100,intent);
                finish();
                break;
            case R.id.rl_order_food:
                intent.putExtra("cate",2);
                setResult(100,intent);
                finish();
                break;
            case R.id.rl_services:
                intent.putExtra("cate",3);
                setResult(100,intent);
                finish();
                break;
            case R.id.rl_allcate:
                intent.putExtra("cate",0);
                setResult(100,intent);
                finish();
                break;
            default:
                break;
        }
    }
}
