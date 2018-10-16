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

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class FeedBacksAdapter extends BaseAdapter {
    private List<Shop> list;
    private Context context;

    public FeedBacksAdapter(List<Shop> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.feedback_cell,null);
            holder.niv_cell_shoppic = (SimpleDraweeView) convertView.findViewById(R.id.niv_cell_shoppic);
            holder.tv_cell_name = (TextView) convertView.findViewById(R.id.tv_cell_name);
            holder.room_ratingbar = (RatingBar) convertView.findViewById(R.id.room_ratingbar);
            holder.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_cell_name.setText(list.get(position).getShopName());
        if (list.get(position).getLogoPath()!=null){
            holder.niv_cell_shoppic.setImageURI(list.get(position).getLogoPath());
        }
        if (list.get(position).getLevel() != null){
            holder.room_ratingbar.setNumStars(list.get(position).getLevel());
        }else {
            holder.room_ratingbar.setNumStars(1);
        }
        if (list.get(position).getDistance() != null){
            holder.tv_distance.setText(list.get(position).getDistance()+"km");
        }
        return convertView;
    }
    public class HolderView{
        SimpleDraweeView niv_cell_shoppic;
        TextView tv_cell_name;
        RatingBar room_ratingbar;
        TextView tv_distance;
    }
}
