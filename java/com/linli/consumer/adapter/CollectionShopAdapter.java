package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.domain.Shop;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/13.
 */
public class CollectionShopAdapter extends BaseAdapter {
    private ArrayList<Shop> list;
    private Context context;
    public CollectionShopAdapter(ArrayList<Shop> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_shop_item,null);
            holder.niv_shop_pic = (SimpleDraweeView) convertView.findViewById(R.id.niv_shop_pic);
            holder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tv_introduce = (TextView) convertView.findViewById(R.id.tv_introduce);
            holder.shop_ratingbar = (RatingBar) convertView.findViewById(R.id.shop_ratingbar);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_shop_name.setText(list.get(position).getShopName());
        if (list.get(position).getCreateTime() != null){
            holder.tv_introduce.setText("收藏日期："+list.get(position).getCreateTime());
        }
        if (list.get(position).getShopAddr() != null){
            holder.tv_introduce.setText("地址："+list.get(position).getShopAddr());
        }
        if (list.get(position).getLogoPath()!=null){
            holder.niv_shop_pic.setImageURI(list.get(position).getLogoPath());
        }
//        holder.shop_ratingbar.setVisibility(View.INVISIBLE);
        if (list.get(position).getLevel() != null){
//            holder.shop_ratingbar.setVisibility(View.VISIBLE);
            holder.shop_ratingbar.setNumStars(list.get(position).getLevel());
        }else {
            holder.shop_ratingbar.setNumStars(1);
        }
        return convertView;
    }
    public class HolderView{
        SimpleDraweeView niv_shop_pic;
        TextView tv_shop_name;
        TextView tv_introduce;
        RatingBar shop_ratingbar;
    }
}
