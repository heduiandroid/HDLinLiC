package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.utils.CommonUtil;

import java.math.BigDecimal;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 * 店铺商品适配器
 * 店铺的商品
 */
public class ShopFoodOrderAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<GoodsBean> data;

    private static final int TYPE_DIALOG = 1;       //dialog进入的布局
    private int type;

    //暂时的bean存储，用于保存要移除的bean，在check取消后，数据才进行改变，否则立即改变会造成数据不一致
    //recyclerView会在重用视图时报错，因为没有数据了

    public ShopFoodOrderAdapter(Context context, List<GoodsBean> list,int type) {
        this.context = context;
        this.data = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_DIALOG:
                view = LayoutInflater.from(context).inflate(R.layout.shop_food_detail_order_item,parent,false);
                holder = new DialogViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof DialogViewHolder) {
            if(type == FOOD_MAIN){
                GoodsBean bean = (GoodsBean)data.get(position);
                ((DialogViewHolder) holder).nameTv.setText(bean.getGoodsName());
                ((DialogViewHolder) holder).priceTv.setText("￥" + BigDecimal.valueOf(bean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                ((DialogViewHolder) holder).numTv.setText("x "+bean.getNumber());
                ((DialogViewHolder) holder).specTv.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(bean.getImagePath())) {
                    ((DialogViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImagePath())));
                }

            }else if( type == SHOP_MAIN){
                GoodsBean bean = (GoodsBean)data.get(position);
                ((DialogViewHolder) holder).nameTv.setText(bean.getGoodsName());
                ((DialogViewHolder) holder).priceTv.setText("￥" + BigDecimal.valueOf(bean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                ((DialogViewHolder) holder).numTv.setText("x "+bean.getNumber());
                ((DialogViewHolder) holder).specTv.setText(bean.getGoodsSpecName());
                if (!TextUtils.isEmpty(bean.getImagePath())) {
                    ((DialogViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImagePath())));
                }
            }
        }

    }
    @Override
    public int getItemViewType(int position) {
        return TYPE_DIALOG;
    }


    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    /**
     * dialog的列表的item的ViewHolder
     * */
    private class DialogViewHolder extends ViewHolder {

        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView priceTv;
        TextView numTv;
        TextView specTv;


        private DialogViewHolder(View itemView) {
            super(itemView);
            avatarIm = (SimpleDraweeView) itemView.findViewById(R.id.shop_food_detail_order_item_avatar_im);
            nameTv = (TextView) itemView.findViewById(R.id.shop_food_detail_order_item_name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.shop_food_detail_order_item_price_tv);
            numTv = (TextView) itemView.findViewById(R.id.shop_food_detail_order_item_num_tv);
            specTv = (TextView)itemView.findViewById(R.id.shop_food_detail_order_item_spec_tv);


        }
    }

}
