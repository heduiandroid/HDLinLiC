package com.linli.consumer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.BusinessInfo;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/5/2.
 */
public class BusinessInfoMemberAdapter extends BaseAdapter {
    private List<BusinessInfo> list;
    private Context context;

    public BusinessInfoMemberAdapter(List<BusinessInfo> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.business_info_member_item,null);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            holder.iv_info_pic = (SimpleDraweeView) convertView.findViewById(R.id.iv_info_pic);
            holder.tv_info_title = (TextView) convertView.findViewById(R.id.tv_info_title);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_info_title.setText(list.get(position).getTitle());
        holder.iv_info_pic.setImageURI(list.get(position).getImagePath()+ Common.WSMALLERPICPARAM400);
        if (list.get(position).getDistance() != null){
            holder.tv_distance.setText(list.get(position).getDistance()+"km");
        }
        if (!list.get(position).isShowed()){
            if (list.get(position).getCategory() == FOOD_MAIN){
                IntrestBuyNet.updateAdVoShowCountF(list.get(position).getId(), list.get(position).getShowCount()+1, new HandleSuccess<Generic>() {
                    @Override
                    public void success(Generic s) {
                        if (s.getStatus() == 1){
                            list.get(position).setShowed(true);
                            Log.i("test","美食广告展示数+1");
                        }
                    }
                });
            }else if (list.get(position).getCategory() == SHOP_MAIN){
                IntrestBuyNet.updateAdVoShowCount(list.get(position).getId(), list.get(position).getShowCount()+1, new HandleSuccess<Generic>() {
                    @Override
                    public void success(Generic s) {
                        if (s.getStatus() == 1){
                            list.get(position).setShowed(true);
                            Log.i("test","商城广告展示数+1");
                        }
                    }
                });
            }
        }
        return convertView;
    }
    public class HolderView{
        TextView tv_date;
        TextView tv_distance;
        SimpleDraweeView iv_info_pic;
        TextView tv_info_title;
    }
}
