package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.ConcernedList;
import com.linli.consumer.bean.LeaguerAdd;
import com.linli.consumer.bean.UpdateLeaguer;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.List;

public class FeedBackDetailRightDialog extends BaseActivity {
    private TextView tv_shopcart,tv_concern,tv_complaint;
    private long shopId;
    private String shopName;
    private String areaCode;
    private int category;
    private long regionId;
    private AppContext appContext;
    private User user;
    private Long conceredId = 0l;//关注ID
    private boolean concerned = false;//是否是用户已关注商家（默认没有关注）
    private Integer status = null;//关注状态 0-正常  1-删除

    @Override
    protected int getLayoutId() {
        return R.layout.feed_back_detail_right_dialog;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        tv_shopcart = findViewClick(R.id.tv_shopcart);
        tv_concern = findViewClick(R.id.tv_concern);
        tv_complaint = findViewClick(R.id.tv_complaint);
    }

    @Override
    protected void initData() {
        dismiss();
        Intent intent = getIntent();
        shopId = intent.getLongExtra("shopid",0l);
        shopName = intent.getStringExtra("shopname");
        areaCode = intent.getStringExtra("areacode");
        category = intent.getIntExtra("category",0);
        regionId = intent.getLongExtra("regionid",0l);
        request_if_concerned();

    }

    @Override
    public void onHDClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_shopcart:
                //go to Shopping Mall shopcart
                UIHelper.togoCartActivity(this);
                finish();
                break;
            case R.id.tv_concern:
                if (status != null){//改状态即可
                    if (status == 0){//改状态为1
                        request_update_status_attention(1);
                    }else if (status == 1){//改状态为0
                        request_update_status_attention(0);
                    }
                }else {//正常添加会员即可
                    request_add_attention();
                }
                break;
            case R.id.tv_complaint:
                intent.putExtra("shopid",shopId);
                intent.putExtra("shopname",shopName);
                intent.putExtra("areacode",areaCode);
                intent.putExtra("category",category);
                intent.putExtra("regionid",regionId);
                intent.setClass(FeedBackDetailRightDialog.this,ComplaintActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
    /*
       查询用户是否关注了此商家
        */
    private void request_if_concerned() {
        IntrestBuyNet.leaguer(user.getId(),shopId, new HandleSuccess<ConcernedList>() {
            @Override
            public void success(ConcernedList s) {
                if (s.getStatus() == 1){
                    List<ConcernedList.DataBean> concernedShops = s.getData();
                    if (concernedShops != null && concernedShops.size() > 0){
                        for (int i = 0;i < concernedShops.size();i++){
                            long serverShopId = concernedShops.get(i).getStoreId();
                            if (serverShopId == shopId){
                                concerned = true;
                                conceredId = concernedShops.get(i).getId();//如果这个ID == shopId   isConcerned = true    break;
                                //如果已关注 并且状态正常
                                status = concernedShops.get(i).getStatus();
                                if (status == 0){
                                    tv_concern.setText("已关注");
                                }
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
    private void request_add_attention() {
        //提交关注此店的操作
        String name = "";
        int sex = 3;//1男 0女  3未知
        if (user.getNickName() != null && !user.getNickName().trim().equals("")){
            name = user.getNickName();
        }else {
            name = user.getPhone();
        }
        if (user.getSex() != null){
            if (user.getSex().equals("男")){
                sex = 1;
            }else if (user.getSex().equals("女")){
                sex = 0;
            }
        }
        IntrestBuyNet.leaguerFor(shopId,user.getId(),name,regionId,user.getPhone(),user.getHeadPath()!=null?user.getHeadPath():" ",user.getBirthday()!=null?user.getBirthday():" ",sex,new HandleSuccess<LeaguerAdd>() {
            @Override
            public void success(LeaguerAdd s) {
                if (s.getStatus() == 1){
                    concerned = true;
                    status = 0;
                    conceredId = s.getData().getId();
                    tv_concern.setText("已关注");
                    Toast.makeText(FeedBackDetailRightDialog.this,"已成功关注商家！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FeedBackDetailRightDialog.this,"商家维护中，请稍后再试",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
    private void request_update_status(int sta) {
        IntrestBuyNet.leaguerUpdate(conceredId, sta, new HandleSuccess<UpdateLeaguer>() {
            @Override
            public void success(UpdateLeaguer s) {
                if (s.getStatus() == 1){
                    if (s.getData().getStatus() == 1){
                        Toast.makeText(FeedBackDetailRightDialog.this,"已取消关注",Toast.LENGTH_SHORT).show();
                        concerned = false;
                        //如果已关注
                        status = 1;
                        tv_concern.setText("关注");
                    }else if (s.getData().getStatus() == 0){
                        concerned = true;
                        status = 0;
                        tv_concern.setText("已关注");
                        Toast.makeText(FeedBackDetailRightDialog.this,"已成功关注商家！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(FeedBackDetailRightDialog.this,"商铺维护中，稍后关注吧",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
    private void request_update_status_attention(final int sta) {
        //提交取消关注此店的操作
        AlertDialog.Builder builder=new AlertDialog.Builder(FeedBackDetailRightDialog.this);
        builder.setTitle("提示");
        builder.setMessage("确认取消关注吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request_update_status(sta);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
