package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.Message;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class MyMessageAdapter extends BaseAdapter {
    private List<Message> list;
    private Context context;

    public MyMessageAdapter(List<Message> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.message_item,null);
            holder.iv_shop_logo = (ImageView) convertView.findViewById(R.id.iv_shop_logo);
            holder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tv_message_createtime = (TextView) convertView.findViewById(R.id.tv_message_createtime);
            holder.tv_new_message = (TextView) convertView.findViewById(R.id.tv_new_message);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        return convertView;
    }
    public class HolderView{
        ImageView iv_shop_logo;
        TextView tv_shop_name;
        TextView tv_message_createtime;
        TextView tv_new_message;
    }
}
