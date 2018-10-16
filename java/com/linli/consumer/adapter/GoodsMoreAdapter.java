package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.common.Common;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by hasee on 2018/7/20.
 */

public class GoodsMoreAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<MallGoodsVo> list;

    public GoodsMoreAdapter(Context context, List<MallGoodsVo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.good_more_item,null);
        ViewHolder holder = new GoodViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof  GoodViewHolder){
            ((GoodViewHolder)holder).sdv_pic.setImageURI(CommonUtil.zoomPic(list.get(position).getMallGoods().getPrimaryImage(),Common.WSMALLERPICPARAM400));
            ((GoodViewHolder)holder).tv_name.setText(list.get(position).getMallGoods().getName());
            ((GoodViewHolder)holder).tv_price.setText("￥"+list.get(position).getMallGoods().getMinprice());
            ((GoodViewHolder)holder).container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIHelper.togoGoodsDetailActivity(context,list.get(position).getMallGoods().getId(),list.get(position).getStoreName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class GoodViewHolder extends ViewHolder{
        LinearLayout container;
        SimpleDraweeView sdv_pic;
        TextView tv_name,tv_price;
        public GoodViewHolder(View itemView) {
            super(itemView);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            sdv_pic = (SimpleDraweeView) itemView.findViewById(R.id.sdv_pic);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
