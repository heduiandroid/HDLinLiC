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
@Deprecated
public class ShopSortRecipeAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    private static final int TYPE_DATA = 0;
    private static final int TYPE_AD = 1;






    public ShopSortRecipeAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_sort_recipe_item,null);
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
            ((ShopViewHolder) holder).mainIngredientTv.setText("小麦粉，食用油，西红柿，鸡蛋，生菜，沙拉酱");
            ((ShopViewHolder) holder).subIngredientTv.setText("味精，食用香精，增味品，水");

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
        TextView mainIngredientTv;
        TextView subIngredientTv;



        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (ImageView)itemView.findViewById(R.id.shop_sort_recipe_item_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.shop_sort_recipe_item_name_tv);
            mainIngredientTv = (TextView)itemView.findViewById(R.id.shop_sort_recipe_item_mainingredient_tv);
            subIngredientTv = (TextView)itemView.findViewById(R.id.shop_sort_recipe_item_subingredient_tv);

        }
    }



}
