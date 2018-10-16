package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.mock.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopSortCouponAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






    public ShopSortCouponAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_sort_coupon_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean)data.get(position);


            ((ShopViewHolder) holder).priceTv.setText("10元"+"代金券");
            ((ShopViewHolder)holder).nameTv.setText("小城故事");
            ((ShopViewHolder) holder).typeTv.setText("全品类");
            ((ShopViewHolder) holder).timeTv.setText("2016.1.1 - 2016.6.6");


            if(!bean.isGet()){
                ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.orange));
            } else  {
                ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.gray_online_line));
            }
            //TODO 这里加入购物车的逻辑
            ((ShopViewHolder) holder).addBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(bean.isAdd()){
                        ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.orange));
                        bean.setAdd(false);
                        Log.i("WATER","从购物车移除");
                    } else  {
                        ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.gray_online_line));
                        bean.setAdd(true);
                        Log.i("WATER","添加到购物车");
                    }
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof NewsBean.ResultBean.DataBean ){
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

        TextView priceTv;
        TextView nameTv;
        TextView typeTv;
        TextView timeTv;
        Button addBt;



        private ShopViewHolder(View itemView) {
            super(itemView);
            priceTv = (TextView)itemView.findViewById(R.id.shop_sort_coupon_item_price_tv);
            nameTv = (TextView)itemView.findViewById(R.id.shop_sort_coupon_item_name_tv);
            typeTv = (TextView)itemView.findViewById(R.id.shop_sort_coupon_item_type_tv);
            timeTv = (TextView)itemView.findViewById(R.id.shop_sort_coupon_item_time_tv);
            addBt = (Button)itemView.findViewById(R.id.shop_sort_coupon_item_add_bt);

        }
    }



}
