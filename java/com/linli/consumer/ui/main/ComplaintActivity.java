package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class ComplaintActivity extends Activity implements View.OnClickListener {
    private EditText et_content;
    private Long shopId;
    private String shopName;
    private String areaCode;
    private int category;
    private AppContext appContext;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        Intent intent = getIntent();
        shopId = intent.getLongExtra("shopid",0);
        shopName = intent.getStringExtra("shopname");
        areaCode = intent.getStringExtra("areacode");
        category = intent.getIntExtra("category",0);
    }

    private void init() {
        et_content = (EditText) findViewById(R.id.et_content);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_content.getText())){
                    Toast.makeText(ComplaintActivity.this,"请输入投诉理由",Toast.LENGTH_SHORT).show();
                }else {
                    request_submit_complaint();
                    finish();
                }
                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void request_submit_complaint() {
        int type = 0;
        String name;
        switch (category){
            case 1:
                type = 2;
                break;
            case 2:
                type = 1;
                break;
            case 3:
                type = 3;
                break;
        }
        if (user.getNickName()!= null){
            name = user.getNickName();
        }else {
            name = user.getPhone();
        }
        IntrestBuyNet.addUserConplaint(type,name, Long.valueOf(areaCode), et_content.getText().toString(), user.getId(), shopName, shopId, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    Toast.makeText(ComplaintActivity.this, "已提交投诉信息！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ComplaintActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
