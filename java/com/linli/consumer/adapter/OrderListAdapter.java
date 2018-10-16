package com.linli.consumer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Order;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.ui.main.OrderDetailActivity;
import com.linli.consumer.ui.main.OrdersActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public class OrderListAdapter extends BaseAdapter {
    private List<Order> list;
    private Context context;
    public OrderListAdapter(List<Order> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.order_list_item,null);
            holder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tv_order_status = (TextView) convertView.findViewById(R.id.tv_order_status);
            holder.tv_payFee = (TextView) convertView.findViewById(R.id.tv_payFee);
            holder.btn_toPayOrReceived = (Button) convertView.findViewById(R.id.btn_toPayOrReceived);
            holder.tv_pro_count = (TextView) convertView.findViewById(R.id.tv_pro_count);
            holder.ll_pro_pictures = (LinearLayout) convertView.findViewById(R.id.ll_pro_pictures);
            holder.tv_finnal_price_tag = (TextView) convertView.findViewById(R.id.tv_finnal_price_tag);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_shop_name.setText(list.get(position).getShopName());
        holder.btn_toPayOrReceived.setVisibility(View.VISIBLE);
        switch (list.get(position).getStatus()){//0待付款  1待发货 2待收货  3已完成 4已关闭
            case 0://待付款
                holder.tv_order_status.setText("待付款");
                holder.btn_toPayOrReceived.setText("付款");
                break;
            case 1://待发货
                holder.tv_order_status.setText("待提供");
                holder.btn_toPayOrReceived.setText("催单");
                break;
            case 2://待收货
                if (list.get(position).getType()==4){
                    holder.tv_order_status.setText("待确认");
                    holder.btn_toPayOrReceived.setText("完成服务");
                }else {
                    holder.tv_order_status.setText("待接收");
                    holder.btn_toPayOrReceived.setText("确认收货");
                }
                break;
            case 3://交易完成
                holder.tv_order_status.setText("交易完成");
                holder.btn_toPayOrReceived.setVisibility(View.GONE);
                if (list.get(position).getType()==4){
                    holder.tv_order_status.setText("已完成");
                }
                break;
            case 4://交易关闭 可能是未付款之前取消了订单
                holder.tv_order_status.setText("已取消");
                holder.btn_toPayOrReceived.setVisibility(View.GONE);
                break;
            default:
//                holder.tv_order_status.setText("订单处理中");
                holder.btn_toPayOrReceived.setVisibility(View.GONE);
                break;
        }
        holder.tv_payFee.setText("￥"+list.get(position).getPayPrice().toString());
        int goodNum = 0;
        for (int i = 0;i < list.get(position).getProList().size();i++){
            goodNum = goodNum + list.get(position).getProList().get(i).getCountInOrder();
        }
        holder.tv_pro_count.setText(goodNum+"");
        holder.ll_pro_pictures.removeAllViews();
        if (list.get(position).getProList().size()>0){
            for (int i = 0;i < list.get(position).getProList().size();i++){
                View v_prod = LayoutInflater.from(context).inflate(R.layout.order_prod_listitem_item,null);
                SimpleDraweeView rciv_propic = (SimpleDraweeView) v_prod.findViewById(R.id.rciv_propic);
                TextView tv_pro_name = (TextView) v_prod.findViewById(R.id.tv_pro_name);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5, 0, 0, 0);
                String path = list.get(position).getProList().get(i).getPicPath();
                rciv_propic.setImageURI(path);
                tv_pro_name.setText(list.get(position).getProList().get(i).getName()+"*"+list.get(position).getProList().get(i).getCountInOrder());
                holder.ll_pro_pictures.addView(v_prod,layoutParams);
            }
        }
        holder.ll_pro_pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,OrderDetailActivity.class);
                intent.putExtra("order", list.get(position));
                ((Activity)context).startActivityForResult(intent,1800);
            }
        });
        holder.btn_toPayOrReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                switch (list.get(position).getStatus()){//0待付款  1待发货 2待收货  3已完成 4已关闭   5是现金交易？
                    case 0://待付款
                        intent.putExtra("order",list.get(position));
                        intent.putExtra("topay",true);
                        ((Activity)context).startActivityForResult(intent,1800);
                        break;
                    case 1://待发货
                        switch (list.get(position).getType()){
                            case 1:
                            case 2://查餐饮店铺信息
                                IntrestBuyNet.findFoodShopByshopId(list.get(position).getShopId(), new HandleSuccess<StoreInfo>() {
                                    @Override
                                    public void success(StoreInfo s) {
                                        if (s.getStatus() == 1){
                                            String shopPhone = null;
                                            if (s.getData().getMobilePhone()!= null) {
                                                shopPhone = s.getData().getMobilePhone();
                                            }else {
                                                shopPhone = s.getData().getPhone();
                                            }
                                            UIHelper.connectSeller(context,shopPhone,s.getData().getCompanyMemberId()+"",s.getData().getName());
                                        }else {
                                            Toast.makeText(context,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;
                            case 3://查商城店铺信息
                                IntrestBuyNet.findShopByshopId(list.get(position).getShopId(), new HandleSuccess<MallShopInfo>() {
                                    @Override
                                    public void success(MallShopInfo s) {
                                        if (s.getStatus() == 1){
                                            String shopPhone = null;
                                            if (s.getData().getMobilePhone()!= null) {
                                                shopPhone = s.getData().getMobilePhone();
                                            }else {
                                                shopPhone = s.getData().getPhone();
                                            }
                                            UIHelper.connectSeller(context,shopPhone,s.getData().getCompanyMemberId()+"",s.getData().getName());
                                        }else {
                                            Toast.makeText(context,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;
                            case 4://查服务店铺信息
                                FoodNet.findServiceStoresInfos(list.get(position).getShopId(), new HandleSuccess<ServiceStoreBean>() {
                                    @Override
                                    public void success(ServiceStoreBean s) {
                                        if (s.getStatus() == 1){
                                            String shopPhone = null;
                                            if (s.getData().getMobilePhone()!= null) {
                                                shopPhone = s.getData().getMobilePhone();
                                            }else {
                                                shopPhone = s.getData().getPhone();
                                            }
                                            UIHelper.connectSeller(context,shopPhone,s.getData().getCompanyMemberId()+"",s.getData().getName());
                                        }else {
                                            Toast.makeText(context,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2://待收货
                        intent.putExtra("order",list.get(position));
                        intent.putExtra("toreceive",true);
                        ((Activity)context).startActivityForResult(intent,1800);

                        break;
                    case 3://点完给商户加/点赞数

                        break;
                    default:
                        break;
                }
            }
        });
        return convertView;
    }
    private void request_praise_store() {
//        if (true){}//成功
        Log.i("test","点赞+1");

    }
    private void request_changeservicestatus(final int pos) {
        IntrestBuyNet.updateServiceOrderStatus(list.get(pos).getId(), 3, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    list.get(pos).setStatus(3);
                    OrdersActivity.orderListAdapter.notifyDataSetChanged(); //刷新adapter
                    Toast.makeText(context,"确认收货成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void request_changegoodstatus(final int pos) {
//        IntrestBuyNet.updateMallOrderStatus(list.get(pos).getId(), 3, new HandleSuccess<Generic>() {
//            @Override
//            public void success(Generic s) {
//                if (s.getStatus() == 1){
//                    list.get(pos).setStatus(3);
//                    OrdersActivity.orderListAdapter.notifyDataSetChanged(); //刷新adapter
//                    Toast.makeText(context,"确认收货成功",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

//    private void request_changefoodstatus(final int pos) {
//        IntrestBuyNet.updateFoodOrderStatus(list.get(pos).getId(), 3, new HandleSuccess<Generic>() {
//            @Override
//            public void success(Generic s) {
//                if (s.getStatus() == 1){
//                    list.get(pos).setStatus(3);
//                    OrdersActivity.orderListAdapter.notifyDataSetChanged(); //刷新adapter
//                    Toast.makeText(context,"确认收货成功",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    public class HolderView{
        LinearLayout ll_pro_pictures;
        TextView tv_shop_name;
        TextView tv_order_status;
        TextView tv_payFee;
        Button btn_toPayOrReceived;
        TextView tv_pro_count;
        TextView tv_finnal_price_tag;
    }
}
