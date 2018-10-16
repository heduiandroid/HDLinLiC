package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AddrNew;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.PhoneCode;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

public class NewOrUpdateAddressActivity extends BaseActivity {
    private ImageView iv_head_right;
    private TextView tv_selected_city;
    private EditText et_addr_detail;
    private EditText et_addressee;
    private EditText et_phone;
    private ImageView iv_default_ifcheck,iv_home_ifcheck,iv_company_ifcheck;
    private AppContext appContext;
    private User user;
    private Intent intent;
    private Integer type = 0;//0-普通收货地址 1-个人收货地址2-公司收货地址               3并不是设为3 默认收货地址即再调一个接口设为默认收货地址
    private int proRegionId;
    private int cityRegionId;
    private int couRegionId;
    private long id;//如果是修改 修改的地址id是多少

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_or_update_address;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        clearCheckBox();
        intent = this.getIntent();
        if (intent.getExtras()!=null){
            try {
                String pcc[] = intent.getStringExtra("address").split(" ");
                tv_selected_city.setText(pcc[0]+" "+pcc[1]+" "+pcc[2]);
                et_addr_detail.setText(pcc[3]);
            }catch (Exception e){
                e.printStackTrace();
            }
            id = intent.getLongExtra("id", -1l);
            et_addressee.setText(intent.getStringExtra("addressee"));
            et_phone.setText(intent.getStringExtra("phone"));
            proRegionId = intent.getIntExtra("proregionid",0);
            cityRegionId = intent.getIntExtra("cityregionid",0);
            couRegionId = intent.getIntExtra("couregionid",0);
            iv_head_right.setVisibility(View.VISIBLE);
            type = intent.getIntExtra("type",0);////////////////////////////////////////////////////////////////////获取type显示checkbox状态
            int isdefault = intent.getIntExtra("isdefault",0);
            if (isdefault == 1){
                type = 3;
            }
            switch (type){
                case 1:
                    iv_home_ifcheck.setImageResource(R.mipmap.cb_checked);
                    break;
                case 2:
                    iv_company_ifcheck.setImageResource(R.mipmap.cb_checked);
                    break;
                case 3:
                    iv_default_ifcheck.setImageResource(R.mipmap.cb_checked);
                    break;
                default:
                    break;
            }
        }

    }

    private void clearCheckBox() {
        iv_default_ifcheck.setImageResource(R.mipmap.cb_unchecked);
        iv_home_ifcheck.setImageResource(R.mipmap.cb_unchecked);
        iv_company_ifcheck.setImageResource(R.mipmap.cb_unchecked);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("添加/编辑地址");
        iv_head_right = findViewClick(R.id.iv_head_right);
        iv_head_right.setImageResource(R.mipmap.ic_addr_delete);
        iv_head_right.setVisibility(View.GONE);
        tv_selected_city = findView(R.id.tv_selected_city);
        et_addr_detail = findView(R.id.et_addr_detail);
        et_addressee = findView(R.id.et_addressee);
        et_phone = findView(R.id.et_phone);

        findViewClick(R.id.ll_select_city);
        findViewClick(R.id.btn_save);
        findViewClick(R.id.ll_default_addr);
        findViewClick(R.id.ll_home_addr);
        findViewClick(R.id.ll_company_addr);
        iv_default_ifcheck = findView(R.id.iv_default_ifcheck);
        iv_home_ifcheck = findView(R.id.iv_home_ifcheck);
        iv_company_ifcheck = findView(R.id.iv_company_ifcheck);
    }

    @Override
    public void onHDClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.ll_select_city:
                intent.setClass(NewOrUpdateAddressActivity.this,V2SelectCityActivity.class);
                intent.putExtra("tag",1);
                startActivityForResult(intent, 200);
                break;
            case R.id.btn_save:
                if (TextUtils.isEmpty(et_addressee.getText()) || TextUtils.isEmpty(et_addr_detail.getText()) || TextUtils.isEmpty(et_phone.getText()) || TextUtils.isEmpty(tv_selected_city.getText())){
                    Toast.makeText(NewOrUpdateAddressActivity.this, "请完整填写收货信息", Toast.LENGTH_SHORT).show();
                }else if (proRegionId == 0 || cityRegionId == 0 || couRegionId == 0){
                    Toast.makeText(NewOrUpdateAddressActivity.this, "选择区域不完整，需含省市县三级", Toast.LENGTH_SHORT).show();
                }else {
                    request_submit_newaddr();
                }
                break;
            case R.id.iv_head_right:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("将删除此收货地址信息");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request_delete_addr();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.ll_default_addr:
                type = 3;
                clearCheckBox();
                iv_default_ifcheck.setImageResource(R.mipmap.cb_checked);
                break;
            case R.id.ll_home_addr:
                type = 1;
                clearCheckBox();
                iv_home_ifcheck.setImageResource(R.mipmap.cb_checked);
                break;
            case R.id.ll_company_addr:
                type = 2;
                clearCheckBox();
                iv_company_ifcheck.setImageResource(R.mipmap.cb_checked);
                break;
            default:
                break;
        }
    }
    private void request_delete_addr() {
        showDialog();
        IntrestBuyNet.deleteUserDistributAddress(id, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                //删除成功并刷新适配器
                if (s.getStatus() == 1) {
                    Toast.makeText(NewOrUpdateAddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    dismiss();
                    finish();
                } else {
                    Toast.makeText(NewOrUpdateAddressActivity.this, "账号保护中，不可操作", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_submit_newaddr() {
        showDialog();
        String addressee = et_addressee.getText().toString();
        String phone = et_phone.getText().toString();
        String address = tv_selected_city.getText().toString() + " " + et_addr_detail.getText().toString().trim();
        int mtype = 0;
        switch (type){
            case 0:
                mtype = 0;
                break;
            case 1:
                mtype = 1;
                break;
            case 2:
                mtype = 2;
                break;
            case 3:
                mtype = 0;
                break;
        }
        if (intent.getExtras() != null){//调用修改接口
            IntrestBuyNet.updateUserDistributAddress(mtype,proRegionId, cityRegionId, couRegionId, address, phone, addressee,id, new HandleSuccess<PhoneCode>() {//是否拼个address给server
                @Override
                public void success(PhoneCode s) {
                    if (s.getStatus() == 1){
                        if (type == 3){
                            request_setdefault_address(id);
                        }else {
                            dismiss();
                            Toast.makeText(NewOrUpdateAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else {
                        dismiss();
                        Toast.makeText(NewOrUpdateAddressActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {//调用添加接口
            IntrestBuyNet.addUserDistributAddress(mtype,proRegionId, cityRegionId, couRegionId, address, phone, addressee, user.getId(), new HandleSuccess<AddrNew>() {
                @Override
                public void success(AddrNew s) {
                    if (s.getStatus() == 1){
                        if (type == 3){
                            request_setdefault_address(s.getData().getId());
                        }else {
                            dismiss();
                            Toast.makeText(NewOrUpdateAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else {
                        dismiss();
                        Toast.makeText(NewOrUpdateAddressActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void request_setdefault_address(long id) {
        IntrestBuyNet.setUserDistributAddress(id, user.getId(),new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                dismiss();
                Toast.makeText(NewOrUpdateAddressActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200){
            if (data != null){
                tv_selected_city.setText(data.getStringExtra("city"));
                proRegionId = data.getIntExtra("proRegionId",0);
                cityRegionId = data.getIntExtra("cityRegionId",0);
                couRegionId = data.getIntExtra("couRegionId",0);
            }
        }
    }
}
