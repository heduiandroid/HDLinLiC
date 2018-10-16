package com.linli.consumer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Product;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.List;

/**
 * Created by hasee on 2017/1/16.
 */

public class EvalGoodsAdapter extends BaseAdapter {
    private List<Product> list;
    private Context context;
    private int type;
    private String userId;
    private Long storeId;
    private String orderNum;

    public EvalGoodsAdapter(List<Product> list, Context context,int type,String userId,Long storeId,String orderNum) {
        this.list = list;
        this.context = context;
        this.type = type;
        this.userId = userId;
        this.storeId = storeId;
        this.orderNum = orderNum;
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
        HolderView holder = new HolderView();
        convertView = LayoutInflater.from(context).inflate(R.layout.evalgood_item,null);
        holder.tv_praisenum = (TextView) convertView.findViewById(R.id.tv_praisenum);
        holder.rciv_propic = (SimpleDraweeView) convertView.findViewById(R.id.rciv_propic);
        holder.tv_pro_name = (TextView) convertView.findViewById(R.id.tv_pro_name);
        holder.iv_ifpraised = (ImageView) convertView.findViewById(R.id.iv_ifpraised);
        convertView.setTag(holder);

        if (list.get(position).getIsEvaled() != null || list.get(position).getIsEvaled() == 0){
            holder.iv_ifpraised.setImageResource(R.mipmap.praiseadd);
            final HolderView finalHolder = holder;
            holder.iv_ifpraised.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list.get(position).getPraiseNum() < 5){
                        finalHolder.tv_praisenum.setVisibility(View.VISIBLE);
                        list.get(position).setPraiseNum(list.get(position).getPraiseNum()+1);
                        finalHolder.tv_praisenum.setText("+"+list.get(position).getPraiseNum());
                        if (type == 1 || type == 2){
                            request_food_praise(list.get(position).getId(),list.get(position).getPraiseNum());
                        }else if (type == 3){
                            request_mall_praise(list.get(position).getId(),list.get(position).getPraiseNum());
                        }else if (type ==4){
                            request_service_praise(list.get(position).getId(),list.get(position).getPraiseNum());
                        }
                    }else {
                        Toast.makeText(context,"点赞次数已满",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if (list.get(position).getIsEvaled() == 1){
            holder.iv_ifpraised.setImageResource(R.mipmap.praised);
        }else {
            holder.iv_ifpraised.setImageResource(R.mipmap.praised);
        }

        holder.rciv_propic.setImageURI(list.get(position).getPicPath());
        holder.tv_pro_name.setText(list.get(position).getName());
        return convertView;
    }

    private void request_food_praise(Long goodId,Integer praiseNum) {
        IntrestBuyNet.userFoodPraise(orderNum,goodId, userId.toString(), praiseNum, storeId, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic generic) {
                Log.i("test","点赞成功");
            }
        });
    }

    private void request_mall_praise(Long goodId,Integer praiseNum) {
        IntrestBuyNet.userGoodsPraise(orderNum,goodId, userId.toString(), praiseNum, storeId, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                Log.i("test","点赞成功");
            }
        });
    }

    private void request_service_praise(long goodId,Integer praiseNum) {
        IntrestBuyNet.userServicePraise(orderNum,goodId, userId.toString(), praiseNum, storeId, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                Log.i("test","点赞成功");
            }
        });
    }

    public class HolderView{
        TextView tv_praisenum;//已点赞数量
        SimpleDraweeView rciv_propic;
        TextView tv_pro_name;
        ImageView iv_ifpraised;
    }
}
