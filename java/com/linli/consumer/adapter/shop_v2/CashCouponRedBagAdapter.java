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

public class CashCouponRedBagAdapter extends BaseAdapter {
    private List<CouponsBean.DataBean> list;
    private Context context;

    public CashCouponRedBagAdapter(List<CouponsBean.DataBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.redbag_all_item,null);
            holder.tv_type_redbag = (TextView) convertView.findViewById(R.id.tv_type_redbag);
            holder.tv_counts_redbag = (TextView) convertView.findViewById(R.id.tv_counts_redbag);
            holder.tv_canuse_tag = (TextView) convertView.findViewById(R.id.tv_canuse_tag);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_canuse_tag.setVisibility(View.VISIBLE);
        holder.tv_type_redbag.setText(context.getResources().getString(R.string.myredbags_type1));
        holder.tv_counts_redbag.setText("￥"+list.get(position).getCouponSum());
        return convertView;
    }
    public class HolderView{
        TextView tv_type_redbag;
        TextView tv_counts_redbag;
        TextView tv_canuse_tag;
    }
}
