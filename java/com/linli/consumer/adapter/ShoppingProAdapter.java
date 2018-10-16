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
 * Created by Administrator on 2016/5/5.
 */
public class ShoppingProAdapter extends BaseAdapter {
    private List<Product> list;
    private Context context;

    public ShoppingProAdapter(List<Product> list, Context context) {
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
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.searched_result_item,null);
            holder.niv_pro_pic = (SimpleDraweeView) convertView.findViewById(R.id.niv_pro_pic);
            holder.tv_pro_name = (TextView) convertView.findViewById(R.id.tv_pro_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_intro = (TextView) convertView.findViewById(R.id.tv_intro);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.niv_pro_pic.setImageURI(list.get(position).getPicPath());
        holder.tv_pro_name.setText(list.get(position).getName());
        if (list.get(position).getPrice() != null){
            holder.tv_price.setText("￥"+list.get(position).getPrice().toString());
        }
        if (list.get(position).getDetailsText()!=null || !"".equals(list.get(position).getDetailsText())) {
            holder.tv_intro.setText(list.get(position).getDetailsText());
        }else {
            holder.tv_intro.setText("暂无详情");
        }
        return convertView;
    }
    public class ViewHolder{
        SimpleDraweeView niv_pro_pic;
        TextView tv_pro_name;
        TextView tv_price;
        TextView tv_intro;
    }
}
