package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.AddressListAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.ReceiveAddr;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Address;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import java.util.ArrayList;

public class AddressManageActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private ArrayList<Address> addresses = new ArrayList<Address>();
    private ListView lv_addresses;
    private TextView tv_nodata;
    private AddressListAdapter adapter;
    private AppContext appContext;
    private User user;
    private boolean choose;//是在选择收货地址吗

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_manage;
    }

    @Override
    protected void initView() {
        init();
        Intent intent = getIntent();
        choose = intent.getBooleanExtra("choose",false);//是在选择收货地址吗
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void request_address_list() {
        showDialog();
        IntrestBuyNet.selectListUserDistributAddress(user.getId(), new HandleSuccess<ReceiveAddr>() {
            @Override
            public void success(ReceiveAddr s) {
                //先判断type根据type放到不同list，并设置两个适配器
                addresses.clear();
                if (s.getStatus() == 1){
                    if (s.getData().size()>0){
                        for (int i = 0;i < s.getData().size();i++){
                            ReceiveAddr.DataBean addr = s.getData().get(i);
                            Address address = new Address();
                            address.setId(addr.getId());
                            address.setAddressee(addr.getName());
                            address.setPhone(addr.getPhone());
                            address.setAddress(addr.getAddress());
                            address.setProvinceId(addr.getProvinceId());
                            address.setCityId(addr.getCityId());
                            address.setAreaId(addr.getAreaId());
                            address.setType(addr.getType());
                            address.setIsDefault(addr.getIsDefault());
                            addresses.add(address);
                        }
                    }
                    if (addresses.size()>0) {
                        adapter = new AddressListAdapter(addresses,AddressManageActivity.this);
                        lv_addresses.setAdapter(adapter);
                        lv_addresses.setVisibility(View.VISIBLE);
                        tv_nodata.setVisibility(View.GONE);
                    }else {
                        if (adapter != null){
                            adapter.notifyDataSetChanged();
                        }
                        lv_addresses.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(AddressManageActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("常用地址管理");
        TextView tv_head_right = findViewClick(R.id.tv_head_right);
        tv_head_right.setText("添加");
        findViewClick(R.id.iv_back);
        ImageView iv = findView(R.id.iv_head_right);
        iv.setVisibility(View.GONE);
        lv_addresses = (ListView) findViewById(R.id.lv_addresses);
        lv_addresses.setOnItemClickListener(this);
        lv_addresses.setOnItemLongClickListener(this);
        tv_nodata = findView(R.id.tv_nodata);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_head_right:
                startActivity(new Intent(AddressManageActivity.this, NewOrUpdateAddressActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (choose){

            Intent intent = new Intent();
            intent.putExtra("id", addresses.get(position).getId());
            intent.putExtra("addressee", addresses.get(position).getAddressee());
            intent.putExtra("phone", addresses.get(position).getPhone());
            intent.putExtra("address",addresses.get(position).getAddress());
            setResult(1002,intent);
            finish();
        }else {
            Intent intent = new Intent();
            intent.putExtra("id", addresses.get(position).getId());
            intent.putExtra("addressee", addresses.get(position).getAddressee());
            intent.putExtra("phone", addresses.get(position).getPhone());
            intent.putExtra("address",addresses.get(position).getAddress());
            intent.putExtra("proregionid",addresses.get(position).getProvinceId());
            intent.putExtra("cityregionid",addresses.get(position).getCityId());
            intent.putExtra("couregionid",addresses.get(position).getAreaId());
            intent.putExtra("type",addresses.get(position).getType());
            intent.putExtra("isdefault",addresses.get(position).getIsDefault());
            intent.setClass(AddressManageActivity.this, NewOrUpdateAddressActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onResume() {
        request_address_list();
        super.onResume();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("将删除此收货地址信息");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request_delete_addr(addresses.get(position).getId(), position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
        return true;
    }

    private void request_delete_addr(long id, final int position) {
        showDialog();
        IntrestBuyNet.deleteUserDistributAddress(id, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                //删除成功并刷新适配器
                if (s.getStatus() == 1){
                    Toast.makeText(AddressManageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    addresses.remove(position);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(AddressManageActivity.this, "账号保护中，不可操作", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }
}
