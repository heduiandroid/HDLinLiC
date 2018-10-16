package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FindFoodBean;
import com.linli.consumer.bean.ShopListCategoryBean;
import com.linli.consumer.utils.CommonUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_ALL;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopSortFindfoodAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    private static final int TYPE_DATA = 0;
    private static final int TYPE_AD = 1;
    private int type = 0;







    public ShopSortFindfoodAdapter(Context context, List<Object> list,int type){
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
                view = LayoutInflater.from(context).inflate(R.layout.shop_sort_findfood_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            if(type == FOOD_MAIN_FINDFOOD){
                final FindFoodBean.DataBean bean = (FindFoodBean.DataBean)data.get(position);

                if(!TextUtils.isEmpty(bean.getHdFoodStore().getName())){
                    ((ShopViewHolder)holder).nameTv.setText(bean.getHdFoodStore().getName());
                }
                //((ShopViewHolder) holder).likeTv.setText("");
                //((ShopViewHolder) holder).collectTv.setText("40");
                ((ShopViewHolder) holder).distanceTv.setText(bean.getHdFoodStore().getDistance()+"km");
                ((ShopViewHolder) holder).starRb.setRating(bean.getHdFoodStore().getCreditLevel());

                if(bean.getHdFoodStoreoperate() != null && bean.getHdFoodStoreoperate().getOpeavgcost() != null){
                    ((ShopViewHolder) holder).priceTv.setText("￥"+bean.getHdFoodStoreoperate().getOpeavgcost().toString()+"/人");
                }
                if(!TextUtils.isEmpty(bean.getHdFoodStore().getAddress())){
                    ((ShopViewHolder) holder).locationTv.setText(bean.getHdFoodStore().getAddress());
                }
                if(TextUtils.isEmpty(bean.getHdFoodStore().getLogoImg())){
                    Picasso.with(context).load(R.mipmap.ic_launcher).into(((ShopViewHolder)holder).avatarIm);
                }else {
                    Picasso.with(context).load(CommonUtil.zoomPic(bean.getHdFoodStore().getLogoImg()))
                            .into(((ShopViewHolder)holder).avatarIm);
                }
                ((ShopViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoShopDetailActivity(context,bean.getHdFoodStore().getId(),
                                bean.getHdFoodStore().getName(),FOOD_MAIN);
                    }
                });
            } else if(type == SHOP_MAIN_ALL){
                final ShopListCategoryBean.DataBean bean = (ShopListCategoryBean.DataBean)data.get(position);

                if(!TextUtils.isEmpty(bean.getName())){
                    ((ShopViewHolder)holder).nameTv.setText(bean.getName());
                }
                //((ShopViewHolder) holder).likeTv.setText("");
                //((ShopViewHolder) holder).collectTv.setText("40");
                ((ShopViewHolder) holder).distanceTv.setText(bean.getDistance()+"km");
                ((ShopViewHolder) holder).starRb.setRating(bean.getCreditLevel());

                ((ShopViewHolder) holder).priceTv.setVisibility(View.GONE);

                if(!TextUtils.isEmpty(bean.getAddress())){
                    ((ShopViewHolder) holder).locationTv.setText(bean.getAddress());
                }
                if(TextUtils.isEmpty(bean.getLogoImg())){
                    Picasso.with(context).load(R.mipmap.ic_launcher).into(((ShopViewHolder)holder).avatarIm);
                }else {
                    Picasso.with(context).load(CommonUtil.zoomPic(bean.getLogoImg()))
                            .into(((ShopViewHolder)holder).avatarIm);
                }
                ((ShopViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoShopDetailActivity(context,bean.getId(),
                                bean.getName(),SHOP_MAIN);
                    }
                });
            }

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

        ImageView avatarIm;
        TextView nameTv;
        TextView likeTv;
        TextView collectTv;
        TextView distanceTv;
        MaterialRatingBar starRb;
        TextView priceTv;
        TextView locationTv;
        LinearLayout containerLl;



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
            containerLl = (LinearLayout)itemView.findViewById(R.id.shop_sort_findfood_item_container_ll);

        }
    }



}
