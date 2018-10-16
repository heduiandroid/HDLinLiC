package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.MyEggsAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.MyEggBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;
import java.util.List;

public class AllMyEggsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private AppContext appContext;
    private User user;
    private GridView gv_myeggs;
    private TextView tv_nodata;
    private List<MyEggBean.DataBean> eggs = new ArrayList<>();
    private MyEggsAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_all_my_eggs;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("我的吉蛋");
        gv_myeggs = findView(R.id.gv_myeggs);
        gv_myeggs.setOnItemClickListener(this);
        tv_nodata = findView(R.id.tv_nodata);
    }

    @Override
    protected void initData() {
        dismiss();
        adapter = new MyEggsAdapter(eggs,this);
        gv_myeggs.setAdapter(adapter);
        request_all_myeggs();
    }

    private void request_all_myeggs() {
        IntrestBuyNet.queryEgg(user.getId(), new HandleSuccess<MyEggBean>() {
            @Override
            public void success(MyEggBean s) {
                if (s.getStatus() == 0){
                    if (s.getData() != null && s.getData().size() > 0){
                        for (int i = 0;i<s.getData().size();i++){
                            eggs.add(s.getData().get(i));
                        }
                        if (eggs.size() > 0){
                            //显示gridview
                            gv_myeggs.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }else {
                            //显示没蛋view
                            gv_myeggs.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                        }
                    }else {
                        //显示没蛋view
                        gv_myeggs.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }else {
                    //显示没蛋view
                    gv_myeggs.setVisibility(View.GONE);
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent();
        intent.putExtra("eggid",eggs.get(position).getId());
        intent.putExtra("eggtype",eggs.get(position).getType());
        intent.putExtra("shopid",eggs.get(position).getStoreId());
        if (eggs.get(position).getStoreType() != null){
            intent.putExtra("shoptype",eggs.get(position).getStoreType());
        }
        intent.putExtra("shopname",eggs.get(position).getStoreName());
        setResult(773,intent);
        finish();
    }
}
