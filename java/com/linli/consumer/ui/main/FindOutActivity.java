package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.common.Common;
import com.linli.consumer.domain.User;

public class FindOutActivity extends BaseActivity implements View.OnClickListener {
    private AppContext appContext;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_out;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("发现");
        findViewClick(R.id.ll_member_businessinfo);
        findViewClick(R.id.ll_businessinfo);
        findViewClick(R.id.ll_scanning);
        findViewClick(R.id.ll_paiyipai);
        findViewClick(R.id.ll_yaoyiyao);
        findViewClick(R.id.ll_red_bag);
        findViewClick(R.id.ll_circle_turnaround);
        findViewClick(R.id.ll_shake_precents);
        findViewClick(R.id.ll_fujidan);
        findViewClick(R.id.ll_special_adver);
        findViewClick(R.id.ll_join_songxiaobao);
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        super.onResume();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.ll_businessinfo:
                if (user != null){
                    startActivity(new Intent(FindOutActivity.this,BusinessInfoActivity.class));
                }else {
                    startActivity(new Intent(FindOutActivity.this,LoginYZXActivity.class));
                }
                break;
            case R.id.ll_member_businessinfo:
                if (user != null){
                    startActivity(new Intent(FindOutActivity.this,BusinessInfoMemberActivity.class));
                }else {
                    startActivity(new Intent(FindOutActivity.this,LoginYZXActivity.class));
                }
                break;
            case R.id.ll_scanning:
                startActivity(new Intent(FindOutActivity.this,ScanningBuyActivity.class));
                break;
            case R.id.ll_paiyipai:
                startActivity(new Intent(FindOutActivity.this, PaiYiPaiActivity.class));
                break;
            case R.id.ll_yaoyiyao:
                if (user != null){
                    startActivity(new Intent(FindOutActivity.this,YaoYiYaoActivity.class));
                }else {
                    UIHelper.togoLoginActivity(FindOutActivity.this);
                }
                break;
            case R.id.ll_red_bag:
//                startActivity(new Intent(FindOutActivity.this,RedBagActivity.class));
                break;
            case R.id.ll_circle_turnaround:
//                startActivity(new Intent(FindOutActivity.this, ZhuanPanActivity.class));
                break;
            case R.id.ll_shake_precents:
//                UIHelper.togoH5ParentActivity(this);
//                Toast.makeText(FindOutActivity.this,"暂未开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fujidan:
                if (user != null){
                    startActivity(new Intent(FindOutActivity.this,FujidanActivity.class));
                }else {
                    UIHelper.togoLoginActivity(FindOutActivity.this);
                }
                break;
            case R.id.ll_special_adver:
                UIHelper.callMyBrowser(this,Common.specilAdverUrl);
                break;
            case R.id.ll_join_songxiaobao:
                UIHelper.callSongXiaoBao(this);
                break;
            default:
                break;
        }
    }
}
