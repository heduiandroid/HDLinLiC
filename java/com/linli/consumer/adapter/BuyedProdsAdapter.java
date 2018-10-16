package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.domain.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class BuyedProdsAdapter extends BaseAdapter {
    private List<Product> list;
    private Context context;

    public BuyedProdsAdapter(List<Product> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.buyed_prod_item,null);
            holder.pic = (SimpleDraweeView) convertView.findViewById(R.id.pic);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.count = (TextView) convertView.findViewById(R.id.count);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.count.setText(list.get(position).getCountInOrder().toString());
        holder.price.setText(list.get(position).getPrice().toString());
        holder.pic.setImageURI(list.get(position).getPicPath());
        return convertView;
    }
    public class HolderView{
        SimpleDraweeView pic;
        TextView name;
        TextView count;
        TextView price;
    }
}
