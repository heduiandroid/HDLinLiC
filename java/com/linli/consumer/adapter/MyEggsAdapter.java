package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.MyEggBean;

import java.util.List;

/**
 * Created by hasee on 2017/3/20.
 */

public class MyEggsAdapter extends BaseAdapter {
    private List<MyEggBean.DataBean> list;
    private Context context;

    public MyEggsAdapter(List<MyEggBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.egg_item,null);
            holder.iv_imgegg = (ImageView) convertView.findViewById(R.id.iv_imgegg);
            holder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        switch (list.get(position).getType()){
            case 1://吉蛋（普通蛋）
                holder.iv_imgegg.setImageResource(R.mipmap.iv_small_normalegg);
                break;
            case 2://银蛋
                holder.iv_imgegg.setImageResource(R.mipmap.iv_small_silveregg);
                break;
            case 3://金蛋
                holder.iv_imgegg.setImageResource(R.mipmap.iv_small_goldenegg);
                break;
            default:
                break;
        }
        holder.tv_shopname.setText(list.get(position).getStoreName());
        return convertView;
    }
    public class HolderView{
        ImageView iv_imgegg;
        TextView tv_shopname;
    }
}
