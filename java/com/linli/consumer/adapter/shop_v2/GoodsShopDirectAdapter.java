package com.linli.consumer.adapter.shop_v2;

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

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class GoodsShopDirectAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    private static final int TYPE_DATA = 0;
    private static final int TYPE_AD = 1;
    private int type = 0;







    public GoodsShopDirectAdapter(Context context, List<Object> list, int type){
        this.context = context;
        this.data = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.goods_shop_direct_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final GoodsDetailBean bean = (GoodsDetailBean)data.get(position);
            ((ShopViewHolder) holder).sdv_direct_propic.setImageURI(bean.getPrimaryImage());
            ((ShopViewHolder) holder).tv_pro_name.setText(bean.getName());
            ((ShopViewHolder) holder).tv_price.setText(bean.getMinprice()+"");

            ((ShopViewHolder) holder).ll_item_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.togoGoodsDetailActivity(context,bean.getId(),"精选专区");
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        /*if(data.get(position) instanceof FindFoodBean.DataBean ){
            return TYPE_DATA;
        } else {
            return TYPE_AD;
        }*/
        return TYPE_DATA;

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

        SimpleDraweeView sdv_direct_propic;
        TextView tv_pro_name;
        TextView tv_price;
        LinearLayout ll_item_click;

        private ShopViewHolder(View itemView) {
            super(itemView);
            sdv_direct_propic = (SimpleDraweeView)itemView.findViewById(R.id.sdv_direct_propic);
            tv_pro_name = (TextView)itemView.findViewById(R.id.tv_pro_name);
            tv_price = (TextView)itemView.findViewById(R.id.tv_price);
            ll_item_click = (LinearLayout) itemView.findViewById(R.id.ll_item_click);
        }
    }



}
