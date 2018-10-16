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
 * Created by Administrator on 2016/5/18.
 */
public class SysMessageAdapter extends BaseAdapter {
    private List<Message> list;
    private Context context;

    public SysMessageAdapter(List<Message> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sys_message_item,null);
            holder.iv_SHT_logo = (ImageView) convertView.findViewById(R.id.iv_SHT_logo);
            holder.tv_messstyle_name = (TextView) convertView.findViewById(R.id.tv_messstyle_name);
            holder.tv_message_createtime = (TextView) convertView.findViewById(R.id.tv_message_createtime);
            holder.tv_new_message = (TextView) convertView.findViewById(R.id.tv_new_message);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }

        return convertView;
    }
    public class HolderView{
        ImageView iv_SHT_logo;
        TextView tv_messstyle_name;
        TextView tv_message_createtime;
        TextView tv_new_message;
    }
}
