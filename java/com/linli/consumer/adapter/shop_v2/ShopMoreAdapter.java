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
import com.linli.consumer.bean.ServiceShopsBean;
import com.linli.consumer.bean.ShopListCategoryBean;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopMoreAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;
    private int type;
    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






    public ShopMoreAdapter(Context context, List<Object> list, int type){
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
                view = LayoutInflater.from(context).inflate(R.layout.shop_more_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            if (type == SHOP_MAIN) {
                final ShopListCategoryBean.DataBean bean = (ShopListCategoryBean.DataBean) data.get(position);


                ((ShopViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(bean.getLogoImg()));
                ((ShopViewHolder) holder).nameTv.setText(bean.getName());
                //((ShopViewHolder) holder).likeTv.setText("300");
                //((ShopViewHolder) holder).collectTv.setText("40");
                ((ShopViewHolder) holder).distanceTv.setText(bean.getDistance() + "km");
                ((ShopViewHolder) holder).starRb.setRating((float) (bean.getCreditLevel()));
                ((ShopViewHolder) holder).locationTv.setText(bean.getAddress());
                //((ShopViewHolder) holder).priceTv.setText("50"+"/人");


                //TODO 这里判断是否要显示优惠券等信息
                ((ShopViewHolder) holder).couponTv.setText("");
                ((ShopViewHolder) holder).voucherTv.setText("");
                ((ShopViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {       //TODO 这里可能需要一个判断,判断进入的店铺类型
                        UIHelper.togoShopDetailActivity(context, bean.getId(), bean.getName(), SHOP_MAIN);
                    }
                });
            }else if (type == SERVICE_MAIN){
                final ServiceShopsBean.DataBean bean = (ServiceShopsBean.DataBean) data.get(position);


                ((ShopViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(bean.getLogoImg()));
                ((ShopViewHolder) holder).nameTv.setText(bean.getName());
                //((ShopViewHolder) holder).likeTv.setText("300");
                //((ShopViewHolder) holder).collectTv.setText("40");
                ((ShopViewHolder) holder).distanceTv.setText(bean.getDistance() + "km");
                ((ShopViewHolder) holder).starRb.setRating(5f);
                ((ShopViewHolder) holder).locationTv.setText(bean.getAddress());
                //((ShopViewHolder) holder).priceTv.setText("50"+"/人");


                //TODO 这里判断是否要显示优惠券等信息
                ((ShopViewHolder) holder).couponTv.setText("");
                ((ShopViewHolder) holder).voucherTv.setText("");
                ((ShopViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {       //TODO 这里可能需要一个判断,判断进入的店铺类型
                        UIHelper.togoShopDetailActivity(context, bean.getId(), bean.getName(), SERVICE_MAIN);
                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof ShopListCategoryBean.DataBean || data.get(position) instanceof ServiceShopsBean.DataBean ){
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

        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView likeTv;
        TextView collectTv;
        TextView distanceTv;
        MaterialRatingBar starRb;
        TextView priceTv;
        TextView locationTv;

        LinearLayout couponLl;
        TextView couponTv;
        LinearLayout voucherLl;
        TextView voucherTv;

        LinearLayout containerLl;



        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (SimpleDraweeView)itemView.findViewById(R.id.shop_more_item_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.shop_more_item_name_tv);
            likeTv = (TextView)itemView.findViewById(R.id.shop_more_item_like_tv);
            collectTv = (TextView)itemView.findViewById(R.id.shop_more_item_collection_tv);
            distanceTv = (TextView)itemView.findViewById(R.id.shop_more_item_distance_tv);
            starRb = (MaterialRatingBar)itemView.findViewById(R.id.shop_more_item_star_rb);
            priceTv = (TextView)itemView.findViewById(R.id.shop_more_item_price_tv);
            locationTv = (TextView)itemView.findViewById(R.id.shop_more_item_location_tv);

            couponLl = (LinearLayout)itemView.findViewById(R.id.shop_more_coupon_ll);
            couponTv = (TextView)itemView.findViewById(R.id.shop_more_coupon_tv);
            voucherLl = (LinearLayout)itemView.findViewById(R.id.shop_more_voucher_ll);
            voucherTv = (TextView)itemView.findViewById(R.id.shop_more_voucher_tv);

            containerLl = (LinearLayout)itemView.findViewById(R.id.shop_more_item_container_ll);

        }
    }



}
