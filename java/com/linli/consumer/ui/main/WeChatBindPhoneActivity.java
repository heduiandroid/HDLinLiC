package com.linli.consumer.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.General;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.LoginHelper;
import com.linli.consumer.utils.WechatTimeCountGetCode;

public class WeChatBindPhoneActivity extends BaseActivity {
    private String openId = null;
    private TextView tv_get_verifycode;
    private EditText et_idcard,et_phone_number;
    private EditText et_verifycode;
    private User user = AppContext.getInstance().getUser();;
    private WechatTimeCountGetCode time;
    private Boolean isNewAccount = null;//是否是新用户 默认不是
    private String phone = null;
    private String idCard = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_we_chat_bind_phone;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();

        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("绑定手机");
        et_verifycode = findView(R.id.et_verifycode);
        et_phone_number = findView(R.id.et_phone_number);
        et_idcard = findView(R.id.et_idcard);
        if (user != null){//et_phone_number不可编辑
            et_phone_number.setFocusable(false);
            et_phone_number.setFocusableInTouchMode(false);
            phone = user.getPhone();
            et_phone_number.setText(CommonUtil.secretPhone(user.getPhone()));

            et_idcard.setFocusable(false);
            et_idcard.setFocusableInTouchMode(false);
            idCard = user.getIdCardNum();
            et_idcard.setText(user.getIdCardNum());
        }
        tv_get_verifycode = findViewClick(R.id.tv_get_verifycode);
        findViewClick(R.id.btn_next);
        time = new WechatTimeCountGetCode(61000,1000,tv_get_verifycode);

    }

    @Override
    protected void initData() {
        openId = getIntent().getStringExtra("openId");
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_get_verifycode:
                if (TextUtils.isEmpty(et_idcard.getText())){
                    showToast(getResources().getString(R.string.id_card_number));
                }else if (TextUtils.isEmpty(et_phone_number.getText())){
                    showToast("请输入手机号");
                }else {
                    if (user == null){
                        phone = et_phone_number.getText().toString().trim();
                    }
                    tv_get_verifycode.setClickable(false);
                    request_get_verifycode();
                }

                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(et_idcard.getText())){
                    showToast(getResources().getString(R.string.id_card_number));
                }else if (TextUtils.isEmpty(et_verifycode.getText())){
                    showToast("请输入验证码");
                }else if (TextUtils.isEmpty(et_phone_number.getText())){
                    showToast("请输入手机号");
                }else if (isNewAccount==null){
                    showToast("手机号与验证码不匹配");
                }else {
                    if (user == null){
                        phone = et_phone_number.getText().toString().trim();
                        idCard = et_idcard.getText().toString().trim();
                    }
                    request_verify();//验证验证码是否正确 如果正确就去修改界面了
                }
                break;
            default:
                break;
        }
    }

    private void request_verify() {
        if (isNewAccount){//新用户注册
            IntrestBuyNet.appWXRegister(idCard,openId, et_verifycode.getText().toString().trim(), phone, new HandleSuccess<Login>() {
                @Override
                public void success(Login s) {
                    if (s.getResponseCode().equals(CafeNet.SUCCESS)) {
                        //存储openid到sharepreference中 以后自动登录备用
                        LoginHelper.saveOpenId(WeChatBindPhoneActivity.this, 1, s.getResponseData().getPersonalCode());
                        LoginHelper.doLoginSilentlyWeChat(WeChatBindPhoneActivity.this, s);
                        //送新人红包
//                        UIHelper.togoRedBagDialogActivity(WeChatBindPhoneActivity.this, "66",1);//收到红包的弹窗通知
                        finish();
                    } else {
                        showToast("注册失败，请稍后再试");
                    }
                }
            });
        }else {//老用户绑定
            //验证成功
            IntrestBuyNet.appWXBandPhone(openId,et_verifycode.getText().toString().trim(), phone, new HandleSuccess<Login>() {
                @Override
                public void success(Login s) {
                    if (s.getResponseCode().equals(CafeNet.SUCCESS)) {
                        //存储openid到sharepreference中 以后自动登录备用
                        LoginHelper.saveOpenId(WeChatBindPhoneActivity.this, 1, s.getResponseData().getPersonalCode());
                        if (s.getResponseData().getOpenId() != null){
                            user.setThirdWXid(s.getResponseData().getOpenId());
                        }else {
                            LoginHelper.doLoginSilentlyWeChat(WeChatBindPhoneActivity.this, s);
                        }
                        finish();
                    } else {
                        showToast("绑定失败，请稍后再试");
                    }
                }
            });
        }

    }

    private void request_get_verifycode() {
        IntrestBuyNet.appWXPhoneSendCode(phone, openId,new HandleSuccess<General>() {
            @Override
            public void success(General s) {
                if (s.getResponseCode().equals("400006")){
                    isNewAccount = false;
                    time.start();
                    showToast("获取成功");
                }else if (s.getResponseCode().equals("400009")){
                    isNewAccount = true;
                    time.start();
                    showToast("获取成功");
                }else {
                    tv_get_verifycode.setClickable(true);
                    showToast(s.getResponseMessage());
                }
            }
        });

    }
}
