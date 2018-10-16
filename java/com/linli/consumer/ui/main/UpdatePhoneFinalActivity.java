package com.linli.consumer.ui.main;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

public class UpdatePhoneFinalActivity extends BaseActivity {
    private EditText et_phone_number,et_verifycode;
    private TextView tv_get_verifycode;
    private TimeCountGetCode time;
    private String phone;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_phone_final;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("修改绑定手机");
        findViewClick(R.id.btn_confirm);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        et_phone_number = findView(R.id.et_phone_number);
        et_verifycode = findView(R.id.et_verifycode);
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back:
                case R.id.tv_head_name:
                    finish();
                    break;
                case R.id.tv_get_verifycode:
                    if (TextUtils.isEmpty(et_phone_number.getText())) {
                        Toast.makeText(this, "请输入新手机号码", Toast.LENGTH_SHORT).show();
                    } else {
                        tv_get_verifycode.setText("获取中...");
                        request_get_verifycode();
                    }
                    break;
                case R.id.btn_confirm:
                    if (TextUtils.isEmpty(et_phone_number.getText())) {
                        Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(et_verifycode.getText())) {
                        Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        requset_verify_newphone();
                    }
                    break;
                default:
                    break;
            }
    }

    private void requset_verify_newphone() {
        showDialog();
        phone = et_phone_number.getText().toString().trim();
        String code = et_verifycode.getText().toString().trim();
        IntrestBuyNet.checkPhoneAndCode(1,code,phone, new HandleSuccess<Generic>() {//是否缺验证码 用户userID
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    request_submit_newphone();
                }else {
                    dismiss();
                    Toast.makeText(UpdatePhoneFinalActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_submit_newphone() {
        IntrestBuyNet.updateUserphone(user.getId(), phone, user.getPassword(),new HandleSuccess<Login>() {
            @Override
            public void success(Login s) {
//                if (s.getStatus() == 1){
//                    dismiss();
//                    user.setPhone(phone);
//                    appContext.setUser(user);
//                    Toast.makeText(UpdatePhoneFinalActivity.this,"修改绑定手机成功",Toast.LENGTH_LONG).show();
//                    finish();
//                }else {
//                    dismiss();
//                    Toast.makeText(UpdatePhoneFinalActivity.this,s.getMsg().toString(),Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    private void request_get_verifycode() {
        phone = et_phone_number.getText().toString().trim();
        IntrestBuyNet.getPhoneCode(1,phone, new HandleSuccess<PhoneCode>() {//是否缺少tag
            @Override
            public void success(PhoneCode phoneCode) {
                if (phoneCode.getStatus() == 1){
                    time.start();
                    Toast.makeText(UpdatePhoneFinalActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdatePhoneFinalActivity.this,phoneCode.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
//        RequestQueue requestQueue = VolleyTools.getInstance(ResetPasswordActivity.this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("phone",phone);
//            jsonParams.put("tag",1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url = Common.getPhoneCode() + jsonParams;/////////////////////////////////
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Res nse.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        if (jsonObject.getInt("code") == 0){
//                            Toast.makeText(ResetPasswordActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
//                        }else {
//                            // 如果是验证码错误
//                            Toast.makeText(ResetPasswordActivity.this,"获取错误，检查手机号码",Toast.LENGTH_SHORT).show();
//                            ll_verify_phone.setVisibility(View.VISIBLE);
//                            ll_reset_password.setVisibility(View.GONE);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(ResetPasswordActivity.this,"网络繁忙,请重试",Toast.LENGTH_SHORT).show();
//                ll_verify_phone.setVisibility(View.VISIBLE);
//                ll_reset_password.setVisibility(View.GONE);
//            }
//        });
//        requestQueue.add(request);
    }
}
