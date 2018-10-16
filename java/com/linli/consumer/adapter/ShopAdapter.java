package com.linli.consumer.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by Administrator on 2016/8/9.
 */
@Deprecated
public class ShopAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






    public ShopAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_main_item,parent,false);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean)data.get(position);

            ShopItemAdapter adapter = new ShopItemAdapter(context,data);

            ((ShopViewHolder)holder).shopName.setText(bean.getTitle());
            ((ShopViewHolder) holder).distance.setText(bean.getDate());
            ((ShopViewHolder) holder).recyclerView.setAdapter(adapter);
            ((ShopViewHolder) holder).recyclerView.setNestedScrollingEnabled(false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ShopViewHolder) holder).recyclerView.setLayoutManager(layoutManager);

            if(TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                Picasso.with(context).load(R.mipmap.ic_launcher).into(((ShopViewHolder)holder).avatarIm);
            }else {
                Picasso.with(context).load(bean.getThumbnail_pic_s())
                        .into(((ShopViewHolder)holder).avatarIm);
            }
            ((ShopViewHolder) holder).avatarIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //UIHelper.togoShopDetailActivity(context,1,"",1);
                    //UIHelper.togoShopGoodsActivity(context);
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



    class ShopViewHolder extends ViewHolder {

        CircleImageView avatarIm;
        TextView shopName;
        TextView distance;
        TextView like;
        TextView collect;
        MaterialRatingBar ratingBar;
        RecyclerView recyclerView;



        public ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (CircleImageView)itemView.findViewById(R.id.shop_main_item_avatar_cim);
            shopName = (TextView)itemView.findViewById(R.id.shop_main_item_name_tv);
            distance = (TextView)itemView.findViewById(R.id.shop_main_item_distance_tv);
            like = (TextView)itemView.findViewById(R.id.shop_main_item_like_tv);
            collect = (TextView)itemView.findViewById(R.id.shop_main_item_collect_tv);
            ratingBar = (MaterialRatingBar)itemView.findViewById(R.id.shop_main_item_rb);
        }
    }



}
