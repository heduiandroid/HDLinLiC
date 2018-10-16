package com.linli.consumer.ui.main;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.TimeCountGetCode;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

public class UpdatePasswordActivity extends BaseActivity {
    private TextView tv_phone,tv_get_verifycode;
    private Button btn_confirm;
    private EditText et_verifycode,et_newpwd,et_renewpwd;
    private AppContext appContext;
    private User user;
    private TimeCountGetCode time;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("修改密码");
        tv_phone = findView(R.id.tv_phone);
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        btn_confirm = findViewClick(R.id.btn_confirm);
        et_verifycode = findView(R.id.et_verifycode);
        et_newpwd = findView(R.id.et_newpwd);
        et_renewpwd = findView(R.id.et_renewpwd);
        time = new TimeCountGetCode(61000,1000,tv_get_verifycode);
    }

    @Override
    protected void initData() {
        tv_phone.setText(secretPhone(user.getPhone()));
        dismiss();
    }
    private String secretPhone(String phone){
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_get_verifycode:
                if (TextUtils.isEmpty(tv_phone.getText())){
                    Toast.makeText(this,"抱歉，请重新登录再试",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verifycode.setClickable(false);
                    tv_get_verifycode.setText("获取中...");
                    request_get_verifycode();
                }
                break;
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_verifycode.getText())){
                    Toast.makeText(this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_newpwd.getText())){
                    Toast.makeText(this,"请先输入密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(et_renewpwd.getText())){
                    Toast.makeText(this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                }else if (et_newpwd.getText().length()<6 || et_renewpwd.getText().length()<6){
                    Toast.makeText(this,"密码不能少于6位",Toast.LENGTH_SHORT).show();
                }else if (!et_newpwd.getText().toString().equals(et_renewpwd.getText().toString())){
                    Toast.makeText(this,"再次输入密码不一致",Toast.LENGTH_SHORT).show();
                }else {
                    request_verifyphone_ifupdate();

                }
                break;
        }
    }

    private void request_verifyphone_ifupdate() {
        String code = et_verifycode.getText().toString().trim();
        IntrestBuyNet.checkPhoneAndCode(1, code, user.getPhone(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    request_update_password();
                }else {
                    Toast.makeText(UpdatePasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_get_verifycode() {
        IntrestBuyNet.getPhoneCode(2, user.getPhone(), new HandleSuccess<PhoneCode>() {
            @Override
            public void success(PhoneCode s) {
                if (s.getStatus() == 1){
                    time.start();
                    Toast.makeText(UpdatePasswordActivity.this,"获取成功",Toast.LENGTH_SHORT).show();
                }else {
                    tv_get_verifycode.setClickable(true);
                    Toast.makeText(UpdatePasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_update_password() {
        final String pwd = et_newpwd.getText().toString().trim();
        String repwd = et_renewpwd.getText().toString().trim();
        IntrestBuyNet.userPasswordUpdate(user.getPhone(), repwd, pwd, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    user = null;
                    appContext.setUser(user);
//                    RongIM.getInstance().disconnect();
                    RongIM.getInstance().logout();
                    JPushInterface.stopPush(getApplicationContext());
                    List<Integer> closeActivities = isExist();
                    for (int i = 0;i < closeActivities.size();i++){
                        ActivityCollector.activities.get(closeActivities.get(i)).finish();
                    }
                    UIHelper.togoLoginActivity(UpdatePasswordActivity.this);
                    UpdatePasswordActivity.this.finish();
                    Toast.makeText(UpdatePasswordActivity.this,"修改成功,需重新登录",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdatePasswordActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*
    判断这几个不登录状态不能显示的Aciivity是否存在 返回存在的位置
     */
    private List<Integer> isExist(){
        List<Integer> pos = new ArrayList<>();
        for(int i = 0; i< ActivityCollector.activities.size(); i++){
            Activity activity = ActivityCollector.activities.get(i);
            if(activity instanceof PersonalCenterActivity || activity instanceof PersonalDataChooseActivity || activity instanceof AccountSafeActivity){
                pos.add(i);
            }
        }
        return pos;
    }
}
