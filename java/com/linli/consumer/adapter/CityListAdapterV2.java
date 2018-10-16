package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.ProvinceCityCounty;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class CityListAdapterV2 extends BaseAdapter {
    private List<ProvinceCityCounty.DataBean> list;
    private Context context;

    public CityListAdapterV2(List<ProvinceCityCounty.DataBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.city_list_item,null);
            holder.tv_string = (TextView) convertView.findViewById(R.id.tv_string);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_string.setText(list.get(position).getName());
        return convertView;
    }
    public class HolderView{
        TextView tv_string;
    }
}
