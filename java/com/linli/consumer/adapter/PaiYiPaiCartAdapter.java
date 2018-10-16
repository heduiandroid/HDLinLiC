package com.linli.consumer.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.ui.main.PaiYiPaiCartActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/6/20.
 */

public class PaiYiPaiCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsBean> list;
    private Context context;
    private DBUtil dbUtil ;

    public PaiYiPaiCartAdapter(ArrayList<GoodsBean> list, Context context) {
        this.list = list;
        this.context = context;
        dbUtil =  DBUtil.getInstance(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        view = LayoutInflater.from(context).inflate(R.layout.paiyipai_shopcart_item,null,false);
        holder = new PaiYiPaiCartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof PaiYiPaiCartViewHolder) {
            final GoodsBean good = list.get(position);
            ((PaiYiPaiCartViewHolder) holder).tv_pro_name.setText(good.getGoodsName());
            ((PaiYiPaiCartViewHolder) holder).tv_pro_num.setText(""+good.getNumber());
            ((PaiYiPaiCartViewHolder) holder).sdv_pro_pic.setImageURI(good.getImagePath()+ Common.MORESMALLERPICPARAM);
            ((PaiYiPaiCartViewHolder) holder).tv_price.setText("￥"+ (BigDecimal.valueOf(good.getPrice()).multiply(BigDecimal.valueOf(good.getNumber()))).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
            ((PaiYiPaiCartViewHolder) holder).ll_num_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    good.setNumber(good.getNumber()+1);
                    ((PaiYiPaiCartViewHolder) holder).tv_pro_num.setText(good.getNumber()+"");
                    ((PaiYiPaiCartViewHolder) holder).tv_price.setText("￥"+ (BigDecimal.valueOf(good.getPrice()).multiply(BigDecimal.valueOf(good.getNumber()))).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    dbUtil.addGoodNum(good);
                    upDateView();
                }
            });
            ((PaiYiPaiCartViewHolder) holder).ll_num_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (good.getNumber()<=1){
                        dbUtil.deleteByGoodId(good.getGoodId(),good.getGoodsSpec());
                        if(list.size() > 0){
                            list.remove(position);
                            PaiYiPaiCartAdapter.this.notifyDataSetChanged();
                        }
                    }else {
                        dbUtil.reduceGoodNum(good.getGoodId(),good.getGoodsSpec());
                        good.setNumber(good.getNumber()-1);
                        ((PaiYiPaiCartViewHolder) holder).tv_pro_num.setText(good.getNumber()+"");
                        ((PaiYiPaiCartViewHolder) holder).tv_price.setText("￥"+ (BigDecimal.valueOf(good.getPrice()).multiply(BigDecimal.valueOf(good.getNumber()))).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    }
                    upDateView();
                }
            });
        }
    }
    /**
     * 更新dialog的视图
     * */
    private void upDateView(){
        ((PaiYiPaiCartActivity)context).upDate();
    }
    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }else {
            return 0;
        }
    }
    private class PaiYiPaiCartViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_num_down,ll_num_up;
        SimpleDraweeView sdv_pro_pic;
        TextView tv_pro_num;
        TextView tv_pro_name;
        TextView tv_price;
        private PaiYiPaiCartViewHolder(View itemView) {
            super(itemView);
            ll_num_down = (LinearLayout) itemView.findViewById(R.id.ll_num_down);
            ll_num_up = (LinearLayout) itemView.findViewById(R.id.ll_num_up);
            sdv_pro_pic = (SimpleDraweeView) itemView.findViewById(R.id.sdv_pro_pic);
            tv_pro_num = (TextView) itemView.findViewById(R.id.tv_pro_num);
            tv_pro_name = (TextView) itemView.findViewById(R.id.tv_pro_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
