package com.linli.consumer.ui.main;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.Login;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class UpdateSexActivity extends Activity implements View.OnClickListener {
    private AppContext appContext;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sex);
        init();
    }

    private void init() {
        findViewById(R.id.ll_all).setOnClickListener(this);
        findViewById(R.id.tv_male).setOnClickListener(this);
        findViewById(R.id.tv_female).setOnClickListener(this);
        appContext=(AppContext) getApplicationContext();
        user=appContext.getUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_male:
                request_update_sex(1);
                break;
            case R.id.tv_female:
                request_update_sex(0);
                break;
            default:
                break;
        }
    }

    private void request_update_sex(Integer sex) {
        IntrestBuyNet.updateUsersex(user.getId(),sex,new HandleSuccess<Login>() {//是否缺sex
            @Override
            public void success(Login s) {
//                if (s.getStatus() == 1){
//                    Toast.makeText(UpdateSexActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                    if (s.getData().getSex() == 1){
//                        user.setSex("男");///////////////////////
//                    }else {
//                        user.setSex("女");///////////////////////
//                    }
//                    UpdateSexActivity.this.finish();
//                }else {
//                    Toast.makeText(UpdateSexActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                    UpdateSexActivity.this.finish();
//                }
            }
        });
//        RequestQueue requestQueue = VolleyTools.getInstance(this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("id",user.getId());
//            jsonParams.put("sex",sex);
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
//                            Toast.makeText(UpdateSexActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            user.setSex(sex);///////////////////////
//                            UpdateSexActivity.this.finish();
////                            System.out.println(jsonObject.toString());
//                        }else {
//                            Toast.makeText(UpdateSexActivity.this,"登录失效，请重新登录",Toast.LENGTH_SHORT).show();
//                            UpdateSexActivity.this.finish();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("UpdateSexActivity,"+volleyError.getLocalizedMessage());
//                Toast.makeText(UpdateSexActivity.this,"网络繁忙,请重试",Toast.LENGTH_SHORT).show();
//                UpdateSexActivity.this.finish();
//            }
//        });
//        requestQueue.add(request);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
