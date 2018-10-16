package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

public class ResetPasswordActivity extends BaseActivity{
    private EditText et_phone_number;
    private TextView tv_get_verifycode;
    private EditText et_verifycode;
    private Button btn_next;
    private EditText et_password_new;
    private EditText et_password_newagain;
    private Button btn_finish;
    private LinearLayout ll_verify_phone,ll_reset_password;
    private TimeCountGetCode time;
    private AppContext appContext;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user != null){
            et_phone_number.setText(user.getPhone());
        }
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("密码设置");
        ll_verify_phone= findView(R.id.ll_verify_phone);
        ll_reset_password = findView(R.id.ll_reset_password);
        et_phone_number = findView(R.id.et_phone_number);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        et_verifycode = findView(R.id.et_verifycode);
        btn_next = findViewClick(R.id.btn_next);
        et_password_new = findView(R.id.et_password_new);
        et_password_newagain =findView(R.id.et_password_newagain);
        btn_finish = findViewClick(R.id.btn_finish);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_get_verifycode:
                if (TextUtils.isEmpty(et_phone_number.getText())){
                    Toast.makeText(ResetPasswordActivity.this,"请先输入手机号码",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verifycode.setClickable(false);
                    request_get_verifycode();
                }
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(et_verifycode.getText())){
                    Toast.makeText(ResetPasswordActivity.this,"请先输入验证码",Toast.LENGTH_SHORT).show();
                }else {
                    ll_verify_phone.setVisibility(View.GONE);
                    ll_reset_password.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_finish:
                if (TextUtils.isEmpty(et_password_new.getText()) || TextUtils.isEmpty(et_password_newagain.getText())){
                    Toast.makeText(ResetPasswordActivity.this,"请完整填写信息",Toast.LENGTH_SHORT).show();
                }else if (et_password_new.getText().length()<6 || et_password_newagain.getText().length()<6){
                    Toast.makeText(ResetPasswordActivity.this,"密码不能小于6位",Toast.LENGTH_SHORT).show();
                }else if (!et_password_new.getText().toString().equals(et_password_newagain.getText().toString())){
                    Toast.makeText(ResetPasswordActivity.this,"两次输入密码不同",Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("提示");
                    builder.setMessage("即将重置新的登录密码");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            request_update_password();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create();
                    builder.show();
                }
                break;
            default:
                break;
        }
    }

    private void request_get_verifycode() {
        String phone = et_phone_number.getText().toString().trim();
        IntrestBuyNet.getPhoneCode(2,phone, new HandleSuccess<PhoneCode>() {//是否缺少tag
            @Override
            public void success(PhoneCode phoneCode) {
                if (phoneCode.getStatus() == 1){
                    time.start();
                    Toast.makeText(ResetPasswordActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                }else {
                    // 如果是验证码错误
                    tv_get_verifycode.setClickable(true);
                    Toast.makeText(ResetPasswordActivity.this,phoneCode.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_update_password() {
        String phone = et_phone_number.getText().toString().trim();
        String phoneCode = et_verifycode.getText().toString().trim();
        String newPassWord = et_password_new.getText().toString();
        String ressPassWord = et_password_newagain.getText().toString();
        IntrestBuyNet.lost(phoneCode,ressPassWord, newPassWord,phone,new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    Toast.makeText(ResetPasswordActivity.this,"密码重置成功",Toast.LENGTH_SHORT).show();
                    Toast.makeText(ResetPasswordActivity.this,"请重新登录",Toast.LENGTH_LONG).show();
                    user = null;
                    appContext.setUser(user);
                    setResult(500);
                    ResetPasswordActivity.this.finish();
//                            System.out.println(jsonObject.toString());
                }else{
                    Toast.makeText(ResetPasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    ll_verify_phone.setVisibility(View.VISIBLE);
                    ll_reset_password.setVisibility(View.GONE);
                }
            }
        });

//        //修改成功 修改user对象
//        mProgressDialog = ProgressDialog.show(ResetPasswordActivity.this, "提示", "请稍后…", false, true);
//        RequestQueue requestQueue = VolleyTools.getInstance(ResetPasswordActivity.this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("phone",phone);
//            jsonParams.put("tag",0);
//            jsonParams.put("phoneCode",phoneCode);
//            jsonParams.put("newPassWord",newPassWord);
//            jsonParams.put("ressPassWord",ressPassWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url = Common.submitLostResetPassword() + jsonParams;/////////////////////////////////
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        System.out.println(s);
//                        if (jsonObject.getInt("code") == 0){
//                            Toast.makeText(ResetPasswordActivity.this,"密码重置成功",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(ResetPasswordActivity.this,"请重新登录",Toast.LENGTH_LONG).show();
//                            user = null;
//                            appContext.setUser(user);
//                            setResult(500);
//                            ResetPasswordActivity.this.finish();
////                            System.out.println(jsonObject.toString());
//                        }else if(jsonObject.getInt("code") == 8){
//                            // 如果是验证码错误
//                            Toast.makeText(ResetPasswordActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
//                            ll_verify_phone.setVisibility(View.VISIBLE);
//                            ll_reset_password.setVisibility(View.GONE);
//                        }else if(jsonObject.getInt("code") == 7){
//                            Toast.makeText(ResetPasswordActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
//                            ll_verify_phone.setVisibility(View.VISIBLE);
//                            ll_reset_password.setVisibility(View.GONE);
//                        }else if(jsonObject.getInt("code") == 6){
//                            Toast.makeText(ResetPasswordActivity.this,"标识为空",Toast.LENGTH_SHORT).show();
//                            ll_verify_phone.setVisibility(View.VISIBLE);
//                            ll_reset_password.setVisibility(View.GONE);
//                        }else if(jsonObject.getInt("code") == 2){
//                            Toast.makeText(ResetPasswordActivity.this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
//                            ll_verify_phone.setVisibility(View.VISIBLE);
//                            ll_reset_password.setVisibility(View.GONE);
//                        }else {
//                            Toast.makeText(ResetPasswordActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
//                            ll_verify_phone.setVisibility(View.VISIBLE);
//                            ll_reset_password.setVisibility(View.GONE);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                mProgressDialog.dismiss();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(ResetPasswordActivity.this,"网络繁忙,请重试",Toast.LENGTH_SHORT).show();
//                ll_verify_phone.setVisibility(View.VISIBLE);
//                ll_reset_password.setVisibility(View.GONE);
//                mProgressDialog.dismiss();
//            }
//        });
//        requestQueue.add(request);
        //修改失败 提示错误原因

    }
}
