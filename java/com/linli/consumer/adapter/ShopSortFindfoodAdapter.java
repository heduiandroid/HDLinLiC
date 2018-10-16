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

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


/**
 * Created by Administrator on 2016/8/9.
 */
@Deprecated
public class ShopSortFindfoodAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    private static final int TYPE_DATA = 0;
    private static final int TYPE_AD = 1;






    public ShopSortFindfoodAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_sort_findfood_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean)data.get(position);


            ((ShopViewHolder)holder).nameTv.setText("小城故事");
            ((ShopViewHolder) holder).likeTv.setText("300");
            ((ShopViewHolder) holder).collectTv.setText("40");
            ((ShopViewHolder) holder).distanceTv.setText("600"+"m");
            ((ShopViewHolder) holder).starRb.setRating(3);
            ((ShopViewHolder) holder).priceTv.setText("50"+"/人");
            ((ShopViewHolder) holder).locationTv.setText(bean.getDate());

            if(TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                Picasso.with(context).load(R.mipmap.ic_launcher).into(((ShopViewHolder)holder).avatarIm);
            }else {
                Picasso.with(context).load(bean.getThumbnail_pic_s())
                        .into(((ShopViewHolder)holder).avatarIm);
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



    private class ShopViewHolder extends ViewHolder {

        ImageView avatarIm;
        TextView nameTv;
        TextView likeTv;
        TextView collectTv;
        TextView distanceTv;
        MaterialRatingBar starRb;
        TextView priceTv;
        TextView locationTv;



        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (ImageView)itemView.findViewById(R.id.shop_sort_findfood_item_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.shop_sort_findfood_item_name_tv);
            likeTv = (TextView)itemView.findViewById(R.id.shop_sort_findfood_item_like_tv);
            collectTv = (TextView)itemView.findViewById(R.id.shop_sort_findfood_item_collection_tv);
            distanceTv = (TextView)itemView.findViewById(R.id.shop_sort_findfood_item_distance_tv);
            starRb = (MaterialRatingBar)itemView.findViewById(R.id.shop_sort_findfood_item_star_rb);
            priceTv = (TextView)itemView.findViewById(R.id.shop_sort_findfood_item_price_tv);
            locationTv = (TextView)itemView.findViewById(R.id.shop_sort_findfood_item_location_tv);

        }
    }



}
