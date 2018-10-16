package com.linli.consumer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.Address;
import com.linli.consumer.ui.main.NewOrUpdateAddressActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class AddressListAdapter extends BaseAdapter {
    private List<Address> list;
    private Context context;

    public AddressListAdapter(List<Address> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.receiver_address_item,null);
            holder.tv_addressee = (TextView) convertView.findViewById(R.id.tv_addressee);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_tag = (TextView) convertView.findViewById(R.id.tv_tag);
            holder.iv_update_addr = (ImageView) convertView.findViewById(R.id.iv_update_addr);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_addressee.setText(list.get(position).getAddressee());
        holder.tv_phone.setText(list.get(position).getPhone());
        int tag = list.get(position).getType();
        holder.tv_tag.setVisibility(View.GONE);
        switch (tag){
            case 1:
                holder.tv_tag.setText(" 家庭");
                holder.tv_tag.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.tv_tag.setText(" 单位");
                holder.tv_tag.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        if (list.get(position).getIsDefault() == 1){
            holder.tv_tag.setText(" 默认");
            holder.tv_tag.setVisibility(View.VISIBLE);
        }
        holder.tv_address.setText(list.get(position).getAddress());
        holder.iv_update_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("addressee",list.get(position).getAddressee());
                intent.putExtra("phone",list.get(position).getPhone());
                intent.putExtra("address",list.get(position).getAddress());
                intent.putExtra("proregionid",list.get(position).getProvinceId());
                intent.putExtra("cityregionid",list.get(position).getCityId());
                intent.putExtra("couregionid",list.get(position).getAreaId());
                intent.putExtra("type",list.get(position).getType());
                intent.putExtra("isdefault",list.get(position).getIsDefault());
                intent.setClass(context, NewOrUpdateAddressActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    public class HolderView{
        TextView tv_addressee;
        TextView tv_phone;
        TextView tv_address;
        TextView tv_tag;
        ImageView iv_update_addr;
    }
}
