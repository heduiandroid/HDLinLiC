package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;

import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class JoinUsActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_shop_name;
    private EditText et_contactor;
    private EditText et_contact_way;
    private TextView tv_shop_type;
    private TextView tag_name;
    private TextView tv_area;
    private Integer shopType = 1;//1实体店  2厂家  3区域代理
    private int regionId = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_join_us;
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
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("我要入驻");
        findViewClick(R.id.iv_back);
        et_shop_name = findView(R.id.et_shop_name);
        et_contactor = findView(R.id.et_contactor);
        et_contact_way = findView(R.id.et_contact_way);
        findViewClick(R.id.btn_confirm);
        tv_shop_type = findViewClick(R.id.tv_shop_type);
        tag_name = findView(R.id.tag_name);
        tv_area = findViewClick(R.id.tv_area);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_shop_type:
                startActivityForResult(new Intent(JoinUsActivity.this, ShopTypeChooseActovity.class), 601);
                break;
            case R.id.tv_area:
                Intent intent = new Intent();
                intent.setClass(JoinUsActivity.this,V2SelectCityActivity.class);
                intent.putExtra("tag",1);
                startActivityForResult(intent, 200);
                break;
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(et_contactor.getText()) || TextUtils.isEmpty(et_contact_way.getText()) || TextUtils.isEmpty(et_shop_name.getText())){
                    Toast.makeText(JoinUsActivity.this,"请完整填写申请信息",Toast.LENGTH_SHORT).show();
                }else if (regionId == 0){
                    Toast.makeText(JoinUsActivity.this,"请选择您的区域",Toast.LENGTH_SHORT).show();
                }else {
                    request_join_us();
                }
                break;
            default:
                break;
        }
    }

    private void request_join_us() {
        showDialog();
        String shopName = et_shop_name.getText().toString();
        if (shopName.equals("")){
            shopName = "未知";
        }
        String linkMan = et_contactor.getText().toString();
        String content = "";
        switch (shopType){//1实体店  2厂家  3区域代理
            case 1:
            case 2:
                content = "意向加盟区域："+tv_area.getText().toString();
                break;
            case 3:
                content = "意向代理区域："+tv_area.getText().toString();
                break;
            default:
                break;
        }
        String linkPhone = et_contact_way.getText().toString();

        IntrestBuyNet.addShopSettled(shopName, linkMan, regionId, linkPhone, content, shopType, new HandleSuccess<Generic>() {//第三个参数代理区域id是不是改成String类型shoptype==3不用传代理id？
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    AlertDialog.Builder builder=new AlertDialog.Builder(JoinUsActivity.this);
                    builder.setTitle("申请已经提交！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                            JoinUsActivity.this.finish();
                        }
                    });
                    builder.create();
                    builder.show();
                }else if (s.getStatus() == 9){
                    Toast.makeText(JoinUsActivity.this, "检测到当前手机号您已经申请过入驻了", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(JoinUsActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 601){
            if (data != null){
                int type = data.getIntExtra("type",0);
                switch (type){
                    case 1:
                        tag_name.setText("店铺名称");
                        tv_shop_type.setText("实体店入驻");
                        shopType = type;//1实体店  2厂家  3区域代理
                        break;
                    case 2:
                        et_shop_name.setHint("输入厂家名称");
                        tag_name.setText("厂家名称");
                        tv_shop_type.setText("厂家入驻");
                        shopType = type;//1实体店  2厂家  3区域代理
                        break;
                    case 3:
                        tag_name.setText("单位名称");
                        tv_shop_type.setText("区域运营商");
                        shopType = type;//1实体店  2厂家  3区域代理
                        break;
                    default:
                        break;
                }
            }
        }else if (resultCode == 200){
            if (data != null){
                tv_area.setText(data.getStringExtra("city"));
                tv_area.setTextColor(getResources().getColor(R.color.gray));
                regionId = data.getIntExtra("couRegionId",0);
                if (regionId == 0){
                    data.getIntExtra("cityRegionId",0);
                }
                if (regionId == 0){
                    data.getIntExtra("proRegionId",0);
                }
            }
        }
    }
}
