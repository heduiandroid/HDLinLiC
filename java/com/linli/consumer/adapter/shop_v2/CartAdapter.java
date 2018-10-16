package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
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
import com.linli.consumer.bean.CartShopBean;
import com.linli.consumer.bean.CartSumBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.ui.shop_v2.ShopCartActivity;
import com.linli.consumer.utils.CommonUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 * 购物车的适配器，包含三种布局
 */
public class CartAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;
    private int type;

    private static final int TYPE_SHOP = 0;      //店铺布局
    private static final int TYPE_GOODS = 1;      //商品布局
    private static final int TYPE_SUM = 2;       //总价布局





    public CartAdapter(Context context, List<Object> list,int type){
        this.context = context;
        this.data = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_SHOP:
                view = LayoutInflater.from(context).inflate(R.layout.cart_item_shop,parent,false);
                holder = new ShopViewHolder(view);
                break;
            case TYPE_GOODS:
                view = LayoutInflater.from(context).inflate(R.layout.cart_item_goods,parent,false);
                holder = new GoodsViewHolder(view);
                break;
            case TYPE_SUM:
                view = LayoutInflater.from(context).inflate(R.layout.cart_item_sum,parent,false);
                holder = new SumViewHolder(view);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            CartShopBean bean = (CartShopBean)data.get(position);
            ((ShopViewHolder)holder).shopName.setText(bean.getName());
        }
        else if(holder instanceof GoodsViewHolder){
            final GoodsBean bean = (GoodsBean) data.get(position);
            if(bean.getImagePath() != null){
                ((GoodsViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImagePath())));
            }
            ((GoodsViewHolder) holder).nameTv.setText(bean.getGoodsName());
            if (bean.getGoodsSpecName() != null && !"".equals(bean.getGoodsSpecName())) {
                ((GoodsViewHolder) holder).tv_specname.setText(bean.getGoodsSpecName());
            }else {
                ((GoodsViewHolder) holder).tv_specname.setText("");
            }
            ((GoodsViewHolder) holder).numTv.setText("数量:"+bean.getNumber());
            ((GoodsViewHolder) holder).priceTv.setText("￥"+ BigDecimal.valueOf(bean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
            ((GoodsViewHolder) holder).deleteLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ShopCartActivity)context).showDelDialog(position);

                }
            });
            ((GoodsViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UIHelper.togoShopDetailActivityFromCart(context,bean.getShopId(),bean.getShopName(),bean.getType());
                    /*CouponDialog couponDialog = new CouponDialog(context);
                    couponDialog.show();*/
                    //TODO 这里跳转到店铺详情
                }
            });
        }
        else if(holder instanceof SumViewHolder){
            CartSumBean bean = (CartSumBean)data.get(position);
            ((SumViewHolder) holder).priceTv.setText("￥"+bean.getPrice());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof GoodsBean){
            return TYPE_GOODS;
        } else if(data.get(position) instanceof CartSumBean){
            return TYPE_SUM;
        } else if(data.get(position) instanceof CartShopBean){
            return TYPE_SHOP;
        } else {
            return 1;
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

        TextView shopName;

        public ShopViewHolder(View itemView) {
            super(itemView);
            shopName = (TextView)itemView.findViewById(R.id.cart_item_shop_name_tv);
        }
    }

    private class GoodsViewHolder extends ViewHolder{

        LinearLayout containerLl;
        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView tv_specname;
        TextView numTv;
        TextView priceTv;
        LinearLayout deleteLl;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            containerLl = (LinearLayout)itemView.findViewById(R.id.cart_item_good_container_ll);
            avatarIm = (SimpleDraweeView)itemView.findViewById(R.id.cart_item_goods_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.cart_item_goods_name_tv);
            tv_specname = (TextView) itemView.findViewById(R.id.tv_specname);
            numTv = (TextView)itemView.findViewById(R.id.cart_item_goods_num_tv);
            priceTv = (TextView)itemView.findViewById(R.id.cart_item_goods_price_tv);
            deleteLl = (LinearLayout)itemView.findViewById(R.id.cart_item_goods_delete_ll);
        }
    }
    private class SumViewHolder extends ViewHolder{

        TextView priceTv;

        public SumViewHolder(View itemView) {
            super(itemView);
            priceTv = (TextView)itemView.findViewById(R.id.cart_item_sum_price_tv);
        }
    }



}
