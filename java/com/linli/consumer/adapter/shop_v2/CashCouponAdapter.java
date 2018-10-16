package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.CouponsBean;

import java.util.List;

/**
 * Created by hasee on 2017/11/6.
 */

public class CashCouponAdapter extends BaseAdapter {
    private List<CouponsBean.DataBean> list;
    private Context context;

    public CashCouponAdapter(List<CouponsBean.DataBean> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.cash_coupon_item,null);
            holder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_shopname.setText(list.get(position).getStoreName());
        holder.tv_price.setText("￥"+list.get(position).getCouponSum());
        return convertView;
    }
    public class HolderView{
        TextView tv_shopname;
        TextView tv_price;
    }
}
