package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.domain.Shop;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class PaiYiPaiNearbyShopsAdapter extends BaseAdapter {
    private List<Shop> list;
    private Context context;

    public PaiYiPaiNearbyShopsAdapter(List<Shop> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.nearby_shops_item,null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            holder.niv_logo = (SimpleDraweeView) convertView.findViewById(R.id.niv_logo);
            holder.tv_category = (TextView) convertView.findViewById(R.id.tv_category);
            holder.tv_introduce = (TextView) convertView.findViewById(R.id.tv_introduce);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).getShopName());
        holder.tv_distance.setText("距离"+list.get(position).getDistance()+"km");
        Integer cate = list.get(position).getCategory();//1-商城2-订餐3-服务
        if (cate != null){
            switch (cate){
                case 1:
                    holder.tv_category.setText("零售/商贸");
                    break;
                case 2:
                    holder.tv_category.setText("餐饮/美食");
                    break;
                case 3:
                    holder.tv_category.setText("生活/服务");
                    break;
                default:
                    break;
            }
        }else {
            holder.tv_category.setText("其他");
        }
        if (list.get(position).getIntroduce() == null){
            holder.tv_introduce.setText("暂无公告");
        }else {
            holder.tv_introduce.setText(list.get(position).getIntroduce());
        }
        holder.niv_logo.setImageURI(list.get(position).getLogoPath());

        return convertView;
    }
    public class HolderView{
        TextView tv_name;
        TextView tv_distance;
        SimpleDraweeView niv_logo;
        TextView tv_category;
        TextView tv_introduce;
    }
}
