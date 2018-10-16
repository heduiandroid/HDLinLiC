package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.City;

import java.util.List;

/**
 * Created by hasee on 2016/11/15.
 */
public class AreasAdapter extends BaseAdapter {
    private List<City> list;
    private Context context;

    public AreasAdapter(List<City> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.areas_change_item,null);
            holder.tv_areadetail = (TextView) convertView.findViewById(R.id.tv_areadetail);
            holder.tv_area = (TextView) convertView.findViewById(R.id.tv_area);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_areadetail.setText(list.get(position).getStreet());
        holder.tv_area.setText(list.get(position).getName()+list.get(position).getArea());
        return convertView;
    }
    public class HolderView{
        TextView tv_areadetail;
        TextView tv_area;
    }
}
