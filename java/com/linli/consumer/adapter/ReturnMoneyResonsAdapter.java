package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.ReturnReson;

import java.util.List;

/**
 * Created by hasee on 2018/5/19.
 */

public class ReturnMoneyResonsAdapter extends BaseAdapter {
    private List<ReturnReson> list;
    private Context context;

    public ReturnMoneyResonsAdapter(List<ReturnReson> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.return_money_reson_item,null);
            holder.iv_checkbutton = (ImageView) convertView.findViewById(R.id.iv_checkbutton);
            holder.tv_reson = (TextView) convertView.findViewById(R.id.tv_reson);
            holder.tv_reson_extra = (TextView) convertView.findViewById(R.id.tv_reson_extra);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        if (list.get(position).getChoosed()){
            holder.iv_checkbutton.setImageResource(R.mipmap.cb_checked_direct);
        }else {
            holder.iv_checkbutton.setImageResource(R.mipmap.cb_unchecked_direct);
        }
        holder.tv_reson.setText(list.get(position).getReson());
        holder.tv_reson_extra.setText(list.get(position).getResonExtra());
        return convertView;
    }
    public class HolderView{
        ImageView iv_checkbutton;
        TextView tv_reson,tv_reson_extra;
    }
}
