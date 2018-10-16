package com.linli.consumer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.mock.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopItemAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;

    public ShopItemAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_main_item_item,parent,false);
                holder = new TitleViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof TitleViewHolder){
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean)data.get(position);


            ((TitleViewHolder)holder).priceTv.setText(bean.getTitle());

            if(TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                Picasso.with(context).load(R.mipmap.ic_launcher).into(((TitleViewHolder)holder).foodIm);
            }else {
                Picasso.with(context).load(bean.getThumbnail_pic_s())
                        .into(((TitleViewHolder)holder).foodIm);
            }
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



    class TitleViewHolder extends ViewHolder {

        ImageView foodIm;
        TextView priceTv;



        public TitleViewHolder(View itemView) {
            super(itemView);
            foodIm = (ImageView)itemView.findViewById(R.id.shop_main_item_item_food_im);
            priceTv = (TextView)itemView.findViewById(R.id.shop_main_item_item_price_tv);
        }
    }



}
