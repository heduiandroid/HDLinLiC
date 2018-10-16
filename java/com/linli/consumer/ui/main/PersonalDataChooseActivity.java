package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.domain.User;

public class PersonalDataChooseActivity extends BaseActivity {
    private TextView tv_phone,tv_nickname;
    private SimpleDraweeView sdv_headimg;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data_choose;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("个人信息");
        findViewClick(R.id.ll_personal_data);
        findViewClick(R.id.ll_account_safe);
        findViewClick(R.id.ll_receiver_address);
        findViewClick(R.id.ll_level_points);
        tv_phone = findView(R.id.tv_phone);
        tv_nickname = findView(R.id.tv_nickname);
        sdv_headimg =  findView(R.id.sdv_headimg);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.ll_account_safe:
                startActivityForResult(new Intent(this,AccountSafeActivity.class),502);
                break;
            case R.id.ll_personal_data:
                startActivity(new Intent(this,PersonalDataActivity.class));
                break;
            case R.id.ll_receiver_address:
                startActivity(new Intent(PersonalDataChooseActivity.this,AddressManageActivity.class));
                break;
            case R.id.ll_level_points:
                //去我的积分界面
                startActivity(new Intent(PersonalDataChooseActivity.this,LevelsUserActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        setUserDatas();
    }
    private void setUserDatas() {
        sdv_headimg.setImageURI(user.getHeadPath());
        tv_phone.setText(secretPhone(user.getPhone()));
        if(user.getNickName() == null || user.getNickName().equals("") || user.getNickName().equals("null") ){
            tv_nickname.setText("昵称：未设置");
        }else {
            tv_nickname.setText("昵称："+user.getNickName());
        }
    }
    private String secretPhone(String phone){
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 502){
            setResult(501);
            finish();
        }
    }
}
