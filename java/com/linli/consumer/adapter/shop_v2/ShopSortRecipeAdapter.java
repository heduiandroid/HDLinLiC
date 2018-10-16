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
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.utils.CommonUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopSortRecipeAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






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
            final FoodRecipeBean.DataBean bean = (FoodRecipeBean.DataBean)data.get(position);


            ((ShopViewHolder)holder).nameTv.setText(bean.getName());
            ((ShopViewHolder) holder).mainIngredientTv.setText(bean.getMaining());
            ((ShopViewHolder) holder).subIngredientTv.setText(bean.getAccessories());

            if(TextUtils.isEmpty(bean.getImgpath())){
                Picasso.with(context).load(R.mipmap.ic_launcher).into(((ShopViewHolder)holder).avatarIm);
            }else {
                Picasso.with(context).load(CommonUtil.assembleRecipe(bean.getImgpath()))
                        .into(((ShopViewHolder)holder).avatarIm);
            }
            ((ShopViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.togoFoodRecipeActivity(context,bean);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof FoodRecipeBean.DataBean ){
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
        LinearLayout containerLl;



        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (ImageView)itemView.findViewById(R.id.shop_sort_recipe_item_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.shop_sort_recipe_item_name_tv);
            mainIngredientTv = (TextView)itemView.findViewById(R.id.shop_sort_recipe_item_mainingredient_tv);
            subIngredientTv = (TextView)itemView.findViewById(R.id.shop_sort_recipe_item_subingredient_tv);
            containerLl = (LinearLayout)itemView.findViewById(R.id.shop_sort_recipe_item_container_ll);

        }
    }



}
