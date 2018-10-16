package com.linli.consumer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by hasee on 2018/7/20.
 */

public class GoodsByCatesAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<GoodsDetailBean> list;

    public GoodsByCatesAdapter(Context context, List<GoodsDetailBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.goods_bycate_item,null);
        ViewHolder holder = new GoodViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof  GoodViewHolder){
            ((GoodViewHolder)holder).niv_cell_goodpic.setImageURI(CommonUtil.zoomPic(list.get(position).getPrimaryImage(),Common.WSMALLERPICPARAM400));
            ((GoodViewHolder)holder).tv_good_name.setText(list.get(position).getName());
            ((GoodViewHolder)holder).tv_price.setText("￥"+list.get(position).getMinprice());
            Long salecate = list.get(position).getSalescategoryId();
            if (salecate != null){
                if (salecate == 1L){
                    ((GoodViewHolder) holder).tv_notice.setText("可使用红包余额购买");
                }else if (salecate == 2L){
                    ((GoodViewHolder) holder).tv_notice.setText("可参加满减活动");
                }else if (salecate == 3L){
                    ((GoodViewHolder) holder).tv_notice.setText("参加0元购活动");
                }else if (salecate == 4L){
                    ((GoodViewHolder) holder).tv_notice.setText("参加拼单活动");
                }
            }else {
                ((GoodViewHolder) holder).tv_notice.setText("品牌："+list.get(position).getBrand());
            }
            ((GoodViewHolder)holder).container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIHelper.togoGoodsDetailActivity(context,list.get(position).getId(),list.get(position).getStoreName());
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
        SimpleDraweeView niv_cell_goodpic;
        TextView tv_good_name,tv_notice,tv_price;
        public GoodViewHolder(View itemView) {
            super(itemView);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            niv_cell_goodpic = (SimpleDraweeView) itemView.findViewById(R.id.niv_cell_goodpic);
            tv_good_name = (TextView) itemView.findViewById(R.id.tv_good_name);
            tv_notice = (TextView) itemView.findViewById(R.id.tv_notice);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
