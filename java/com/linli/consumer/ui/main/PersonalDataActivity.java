package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;

import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.domain.User;

public class PersonalDataActivity extends BaseActivity{
    private SimpleDraweeView iv_head_portrait;
    private TextView tv_nickname;
    private TextView tv_sex;
    private TextView tv_birthday;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
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

    private void setUserDatas() {
        if(user.getNickName() != null && !user.getNickName().equals("") && !user.getNickName().equals("null")){
            tv_nickname.setText(user.getNickName());
        }
        if(user.getBirthday() != null && !user.getBirthday().equals("") && !user.getBirthday().equals("null")){
            tv_birthday.setText(user.getBirthday());
        }
        if(user.getSex() != null && !user.getSex().equals("") && !user.getSex().equals("null")){
            if ("male".equals(user.getSex())){
                tv_sex.setText("男");
            }else if ("female".equals(user.getSex())){
                tv_sex.setText("女");
            }else {
                tv_sex.setText(user.getSex());
            }
        }
    }
    private void setHeadImage() {
        iv_head_portrait.setImageURI(user.getHeadPath());
    }
    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("个人资料");
        iv_head_portrait =  findView(R.id.iv_head_portrait);
        tv_nickname = findView(R.id.tv_nickname);
        tv_sex = findView(R.id.tv_sex);
        tv_birthday = findView(R.id.tv_birthday);

        findViewClick(R.id.iv_back);
        findViewClick(R.id.ll_update_portrait);
        findViewClick(R.id.ll_update_nickname);
        findViewClick(R.id.ll_update_sex);
        findViewClick(R.id.ll_birthday);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.ll_update_portrait:
                startActivity(new Intent(PersonalDataActivity.this,UploadWayActivity.class));
                break;
            case R.id.ll_update_nickname:
                Intent itNickname = new Intent();
                itNickname.putExtra("tag","nickname");
                itNickname.setClass(PersonalDataActivity.this, UpdateUserDatasActivity.class);
                startActivity(itNickname);
                break;
            case R.id.ll_update_sex:
                startActivity(new Intent(PersonalDataActivity.this, UpdateSexActivity.class));
                break;
            case R.id.ll_birthday:
                startActivity(new Intent(this,DatePickerActivity.class).putExtra("currentday",tv_birthday.getText().toString()));
                break;
            default:
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUserDatas();
        setHeadImage();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == 701){
//            if (data != null){
//                request_update_birthday(data);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void request_update_birthday(final Intent data) {
//        IntrestBuyNet.updateUserbirthday(user.getId(),data.getStringExtra("date")+ " 00:00:00", new HandleSuccess<Login>() {//是否缺少date
//            @Override
//            public void success(Login s) {
//                if (s.getStatus() == 1){
//                    user.setBirthday(s.getData().getBirthday().toString());
//                    tv_birthday.setText(data.getStringExtra("date"));
//                }else {
//                    Toast.makeText(PersonalDataActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
}
