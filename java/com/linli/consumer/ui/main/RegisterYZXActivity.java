package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.General;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.utils.LoginHelper;
import com.linli.consumer.utils.TimeCountGetCode;

public class RegisterYZXActivity extends BaseActivity {
    private ImageView iv_agree;
    private TextView tv_agree;
    private boolean isAgree = true;
    private TextView tv_introduce;//去平台协议
    private EditText et_idcard;//idcard
    private EditText et_phone;
    private EditText et_password;
    private EditText et_verification_code;
    private TextView tv_region,tv_get_verification_code;
    private ImageView iv_seeingpwd;
    private Button btn_finish;
    private TimeCountGetCode time;

    private String phone;
    private String password;
    private String phoneCode;
    private String idCard;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_yzx;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        time = new TimeCountGetCode(61000,1000,tv_get_verification_code);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        et_idcard = findView(R.id.et_idcard);
        et_phone = findView(R.id.et_phone);
        et_password =  findView(R.id.et_password);
        et_verification_code =  findView(R.id.et_verification_code);
        tv_get_verification_code = findViewClick(R.id.tv_get_verification_code);
        btn_finish = findViewClick(R.id.btn_finish);
        iv_seeingpwd = findView(R.id.iv_seeingpwd);
        iv_agree = findViewClick(R.id.iv_agree);
        tv_agree = findViewClick(R.id.tv_agree);
        tv_introduce = findViewClick(R.id.tv_introduce);
        iv_seeingpwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.tv_head_name:
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_verification_code:
                if (TextUtils.isEmpty(et_phone.getText())){
                    Toast.makeText(RegisterYZXActivity.this,"请先输入手机号",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verification_code.setClickable(false);
                    request_get_verifycode();
                }
                break;
            case R.id.btn_finish:
                if (!isAgree){
                    Toast.makeText(RegisterYZXActivity.this,"须要同意平台协议，请仔细阅读",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(et_idcard.getText())){
                    showToast(getResources().getString(R.string.id_card_number));
                }
                else if (TextUtils.isEmpty(et_phone.getText())){
                    Toast.makeText(RegisterYZXActivity.this,"请先输入手机号",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_password.getText())){
                    Toast.makeText(RegisterYZXActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (et_password.getText().toString().trim().length()<6){
                    Toast.makeText(RegisterYZXActivity.this,"密码不能少于6位",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_verification_code.getText())){
                    Toast.makeText(RegisterYZXActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else {
                    request_register();
                }
                break;
            case R.id.iv_agree:
            case R.id.tv_agree:
                if (isAgree){
                    isAgree = false;
                    iv_agree.setImageResource(R.mipmap.imageview_unchecked);
                }else {
                    isAgree = true;
                    iv_agree.setImageResource(R.mipmap.imageview_checked);
                }
                break;
            case R.id.tv_introduce:
                startActivity(new Intent(RegisterYZXActivity.this,AgreementActivity.class));
                break;
            default:
                break;
        }
    }

    private void request_register() {
        showSimpleDialog();
        idCard = et_idcard.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        password = et_password.getText().toString();
        phoneCode = et_verification_code.getText().toString().trim();
        CafeNet.register(phone, password, phoneCode, idCard, new HandleSuccess<General>() {
            @Override
            public void success(General s) {
                if (s != null){
                    if (CafeNet.SUCCESS.equals(s.getResponseCode())){
                        SharedPreferences preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);//将注册好的账号密码存入本地
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("phone", phone);
                        editor.putString("password", password);
                        editor.commit();
                        LoginHelper.doLoginSilently(RegisterYZXActivity.this);
                        showToast("注册成功");
                    }else {
                        showToast(s.getResponseMessage());
                    }
                }
                dismissSimDialog();
            }
        });
    }

    private void request_get_verifycode() {
        phone = et_phone.getText().toString().trim();
        CafeNet.sendPhoneCode(phone, new HandleSuccess<General>() {
            @Override
            public void success(General s) {
                if (s != null) {
                    if (CafeNet.SUCCESS.equals(s.getResponseCode())) {
                        time.start();
                        showToast("获取成功");
                    }else {
                        // 如果是验证码错误
                        tv_get_verification_code.setClickable(true);
                        showToast(s.getResponseMessage());
                    }
                }
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 200){
//            if (data != null){
//                tv_region.setText(data.getStringExtra("city"));
//                tv_region.setHintTextColor(getResources().getColor(R.color.gray));
//                regionId = data.getIntExtra("couRegionId",0);
//                if (regionId == 0){
//                    data.getIntExtra("cityRegionId",0);
//                }
//                if (regionId == 0){
//                    data.getIntExtra("proRegionId",0);
//                }
//            }
//        }
//    }
}
