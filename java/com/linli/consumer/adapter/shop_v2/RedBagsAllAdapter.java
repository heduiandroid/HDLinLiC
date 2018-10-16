package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.CouponsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class RedBagsAllAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






    public RedBagsAllAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.redbag_all_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final CouponsBean.DataBean bean = (CouponsBean.DataBean)data.get(position);

            if (bean.getStatus() != 1){
                ((ShopViewHolder) holder).iv_redbag.setImageResource(R.mipmap.icon_redbag_used);
                ((ShopViewHolder) holder).tv_counts_redbag.setTextColor(context.getResources().getColor(R.color.gray));
            }else {
                ((ShopViewHolder) holder).iv_redbag.setImageResource(R.mipmap.icon_redbag);
            }
            ((ShopViewHolder) holder).tv_type_redbag.setText(context.getResources().getString(R.string.myredbags_type1));
            ((ShopViewHolder) holder).tv_counts_redbag.setText("￥"+bean.getCouponSum());

            ((ShopViewHolder) holder).redbag_item_container_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getStatus() == 1) {
                        UIHelper.togoDirectShopActivity(context);
//                        UIHelper.togoShopDetailActivity(context, 999L, "精选专区", SHOP_MAIN);
//                        UIHelper.togoDirectShopActivity(YZXIndexActivity.this);//？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
                    }
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof CouponsBean.DataBean ){
            return TYPE_DATA;
        } else {
            return TYPE_AD;
        }

    }



    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }



    private class ShopViewHolder extends ViewHolder {

        ImageView iv_redbag;
        TextView tv_type_redbag;
        TextView tv_counts_redbag;
        TextView tv_canuse_tag;
        LinearLayout redbag_item_container_ll;



        private ShopViewHolder(View itemView) {
            super(itemView);
            iv_redbag = (ImageView)itemView.findViewById(R.id.iv_redbag);
            tv_type_redbag = (TextView)itemView.findViewById(R.id.tv_type_redbag);
            tv_counts_redbag = (TextView)itemView.findViewById(R.id.tv_counts_redbag);
            tv_canuse_tag = (TextView)itemView.findViewById(R.id.tv_canuse_tag);
            redbag_item_container_ll = (LinearLayout)itemView.findViewById(R.id.redbag_item_container_ll);

        }
    }



}
