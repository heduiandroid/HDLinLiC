package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.domain.Order;
import com.linli.consumer.ui.main.YaoYiYaoActivity;

import java.util.List;

/**
 * Created by hasee on 2017/2/9.
 */

public class PayingOrderAdapter extends BaseAdapter {
    private List<Order> list;
    private Context context;

    public PayingOrderAdapter(List<Order> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.paying_orders_item,null);
            holder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tv_order_status = (TextView) convertView.findViewById(R.id.tv_order_status);
            holder.tv_payFee = (TextView) convertView.findViewById(R.id.tv_payFee);
            holder.btn_toPayOrReceived = (Button) convertView.findViewById(R.id.btn_toPayOrReceived);
            holder.tv_pro_count = (TextView) convertView.findViewById(R.id.tv_pro_count);
            holder.ll_pro_pictures = (LinearLayout) convertView.findViewById(R.id.ll_pro_pictures);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_shop_name.setText(list.get(position).getShopName());
        holder.tv_payFee.setText("¥"+list.get(position).getPayPrice().toString());
        holder.tv_pro_count.setText(list.get(position).getProList().size()+"");
        holder.ll_pro_pictures.removeAllViews();
        for (int i = 0;i < list.get(position).getProList().size();i++){
            View v_prod = LayoutInflater.from(context).inflate(R.layout.paying_orders_item_cell,null);
            SimpleDraweeView rciv_propic = (SimpleDraweeView) v_prod.findViewById(R.id.rciv_propic);
            TextView tv_pro_name = (TextView) v_prod.findViewById(R.id.tv_pro_name);
            TextView tv_prod_num = (TextView) v_prod.findViewById(R.id.tv_prod_num);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 0, 0, 0);
            String path = list.get(position).getProList().get(i).getPicPath();
            rciv_propic.setImageURI(path);
            tv_pro_name.setText(list.get(position).getProList().get(i).getName());
            tv_prod_num.setText("×"+list.get(position).getProList().get(i).getCountInOrder());
            holder.ll_pro_pictures.addView(v_prod,layoutParams);
        }
        holder.tv_order_status.setText("待付款");
        holder.btn_toPayOrReceived.setText("付款");
        holder.btn_toPayOrReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YaoYiYaoActivity.orderId = list.get(position).getId();
                UIHelper.togoOnLinePayActivity(context, Long.valueOf(list.get(position).getOrderNum()),list.get(position).getPayPrice(),list.get(position).getOrderNum().toString(),list.get(position).getId());
            }
        });
        return convertView;
    }
    public class HolderView{
        LinearLayout ll_pro_pictures;
        TextView tv_shop_name;
        TextView tv_order_status;
        TextView tv_payFee;
        Button btn_toPayOrReceived;
        TextView tv_pro_count;
    }
}
