package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.Login;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class UpdateUserDatasActivity extends Activity implements View.OnClickListener {
    private EditText et_new;
    private TextView tv_headshow;
    private AppContext appContext;
    private User user;
    private String tag;
    private String nickName;
    private String myAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        init();
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        if (tag.equals("nickname")){
            et_new.setHint("填写新的昵称");
            tv_headshow.setText("修改昵称");
        }else if (tag.equals("address")){
            et_new.setHint("填写新的地址");
            tv_headshow.setText("修改个人地址");
        }
    }

    private void init() {
        et_new = (EditText) findViewById(R.id.et_new);
        tv_headshow = (TextView) findViewById(R.id.tv_headshow);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
        findViewById(R.id.ll_all).setOnClickListener(this);
        appContext=(AppContext) getApplicationContext();
        user=appContext.getUser();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_new.getText())){
                    Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    if (tag.equals("nickname")){
                        request_update_nickname();
                    }else if (tag.equals("address")){
                        request_update_myaddress();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void request_update_nickname() {
        nickName = et_new.getText().toString();
        IntrestBuyNet.updateUsernickname(user.getId(),nickName,new HandleSuccess<Login>() {//是否少nickname
            @Override
            public void success(Login s) {
//                if (s.getStatus() == 1){
//                    Toast.makeText(UpdateUserDatasActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                    user.setNickName(nickName);///////////////////////
//                    UpdateUserDatasActivity.this.finish();
//                }else {
//                    Toast.makeText(UpdateUserDatasActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                    UpdateUserDatasActivity.this.finish();
//                }
            }
        });

    }
    private void request_update_myaddress() {
//        myAddress = et_new.getText().toString();
//        IntrestBuyNet.updateUser(user.getId(), new HandleSuccess<Login>() {//是否少address
//            @Override
//            public void success(Login s) {
//                if (s.getStatus() == 1){
//                    Toast.makeText(UpdateUserDatasActivity.this, "修改成功", Toast.LENGTH_SHORT).show();//
//                    user.setMyAddress(myAddress);
//                    UpdateUserDatasActivity.this.finish();
//                }else {
//                    user.setMyAddress(myAddress);//
//                    Toast.makeText(UpdateUserDatasActivity.this,"修改成功",Toast.LENGTH_SHORT).show();//
//                    UpdateUserDatasActivity.this.finish();
//                }
//            }
//        });
//        RequestQueue requestQueue = VolleyTools.getInstance(this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("id",user.getId());
//            jsonParams.put("myAddress",myAddress);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url = Common.updateUserData() + URLEncoder.encode(jsonParams.toString());/////////////////////////////////
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        if (jsonObject.getInt("code") == 0){
//                            Toast.makeText(UpdateUserDatasActivity.this, "修改成功", Toast.LENGTH_SHORT).show();////////////////////////////////假成功
//                            user.setMyAddress(myAddress);
//                            UpdateUserDatasActivity.this.finish();
////                            System.out.println(jsonObject.toString());
//                        }else {
//                            user.setMyAddress(myAddress);/////////////////////////////////////////////////////需删掉
//                            Toast.makeText(UpdateUserDatasActivity.this,"修改成功",Toast.LENGTH_SHORT).show();////////////////////////////////假成功
//                            UpdateUserDatasActivity.this.finish();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                user.setMyAddress(myAddress);/////////////////////////////////////////////////////需删掉
//                System.out.println("UpdateUserDatasActivity,"+volleyError.getLocalizedMessage());
//                Toast.makeText(UpdateUserDatasActivity.this,"修改成功",Toast.LENGTH_SHORT).show();////////////////////////////////假成功
//                UpdateUserDatasActivity.this.finish();
//            }
//        });
//        requestQueue.add(request);
    }
}
