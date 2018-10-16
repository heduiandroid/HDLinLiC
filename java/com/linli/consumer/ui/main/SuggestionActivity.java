package com.linli.consumer.ui.main;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class SuggestionActivity extends BaseActivity {
    private EditText et_suggestion;
    private EditText et_contact_information;
    private AppContext appContext;
    private User user;
    private String phone;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggestion;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        TextView tv =  findViewClick(R.id.tv_head_name);
        tv.setText("意见反馈");
        findViewClick(R.id.iv_back);
        et_suggestion = findView(R.id.et_suggestion);
        et_contact_information = findView(R.id.et_contact_information);
        findViewClick(R.id.btn_submit);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.btn_submit:
                if (TextUtils.isEmpty(et_suggestion.getText())){
                    Toast.makeText(SuggestionActivity.this,"请输入您的建议",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(et_contact_information.getText())){
                    phone = user.getPhone().trim();
                    request_submit_suggestion();
                }else {
                    phone = et_contact_information.getText().toString().trim();
                    request_submit_suggestion();
                }
                break;
            default:
                break;
        }
    }

    private void request_submit_suggestion() {
        showDialog();
        String content = et_suggestion.getText().toString();
        IntrestBuyNet.addUserFeedback("无QQ", phone, 1, content, user.getId(), new HandleSuccess<PhoneCode>() {
            @Override
            public void success(PhoneCode phoneCode) {
                dismiss();
                Toast.makeText(SuggestionActivity.this,"感谢您，已提交成功",Toast.LENGTH_LONG).show();
                SuggestionActivity.this.finish();
            }
        });
    }
}
