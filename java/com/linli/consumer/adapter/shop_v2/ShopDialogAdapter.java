package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailSingleBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;

import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.shop_v2.FoodDetailActivity;
import com.linli.consumer.ui.shop_v2.ShopDetailActivity;
import com.linli.consumer.utils.CommonUtil;


import java.math.BigDecimal;

import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 * {@link com.linli.consumer.widget.ShopCartDialog}
 * 店铺商品适配器
 * 店铺的商品
 */

public class ShopDialogAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<GoodsBean> data ;

    private final int TYPE_DIALOG = 1;


    private DBUtil dbUtil ;
    private int type;



    public ShopDialogAdapter(Context context, List<GoodsBean> list,int type) {
        this.context = context;
        this.data = list;
        dbUtil =  DBUtil.getInstance(context);
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_DIALOG:
                view = LayoutInflater.from(context).inflate(R.layout.shop_cart_dialog_item,parent,false);
                holder = new DialogViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof DialogViewHolder){
            final GoodsBean goodsBean = data.get(position);
            ((DialogViewHolder) holder).nameTv.setText(goodsBean.getGoodsName());
            if (goodsBean.getGoodsSpecName() != null && !"".equals(goodsBean.getGoodsSpecName())) {
                ((DialogViewHolder) holder).tv_specname.setText(goodsBean.getGoodsSpecName());
            }else {
                ((DialogViewHolder) holder).tv_specname.setText("");
            }
            ((DialogViewHolder) holder).priceTv.setText("￥"+BigDecimal.valueOf(goodsBean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
            ((DialogViewHolder) holder).numTv.setText(goodsBean.getNumber()+"");
            ((DialogViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(goodsBean.getImagePath()));
            ((DialogViewHolder) holder).checkBox.setChecked(goodsBean.getIsChoice());
            Log.i("test",goodsBean.getPrice()+"diaadapter");
            ((DialogViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    goodsBean.setIsChoice(isChecked);
                    dbUtil.updateGoodInfo(goodsBean);
                    upDialogView();
                }
            });
            ((DialogViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == SHOP_MAIN) {
                        ShopNet.findGoodsById(goodsBean.getGoodId(), new HandleSuccess<GoodsDetailSingleBean>() {
                            @Override
                            public void success(GoodsDetailSingleBean s) {
                                if (s.getStatus() == 1 && s.getData() != null) {
                                    List<GoodsDetailBean.GoodsSpecsBean> specs = s.getData().getGoodsSpecs();
                                    if (specs != null && specs.size() > 0) {
                                        int inventory = 0;
                                        for (int i = 0; i < specs.size(); i++) {
                                            if (specs.get(i).getId() == goodsBean.getGoodsSpec()) {
                                                inventory = specs.get(i).getInventory();
                                            }
                                        }
                                        if (goodsBean.getNumber() < inventory) {//购物车商品数小于库存可以加
                                            goodsBean.setNumber(goodsBean.getNumber() + 1);
                                            goodsBean.setIsChoice(true);
                                            ((DialogViewHolder) holder).checkBox.setChecked(true);
                                            ((DialogViewHolder) holder).numTv.setText(goodsBean.getNumber() + "");
                                            dbUtil.addGoodNum(goodsBean);
                                            upDialogView();
                                        } else {//库存不足
                                            Toast.makeText(context, "库存不足", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                    }else if (type == FOOD_MAIN){
                        goodsBean.setNumber(goodsBean.getNumber()+1);
                        goodsBean.setIsChoice(true);
                        ((DialogViewHolder) holder).checkBox.setChecked(true);
                        ((DialogViewHolder) holder).numTv.setText(goodsBean.getNumber()+"");
                        dbUtil.addGoodNum(goodsBean);
                        upDialogView();
                    }
                }
            });
                ((DialogViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(goodsBean.getNumber() > 0){
                            if(goodsBean.getNumber() == 1 ){
                                //dbUtil.reduceGoodNum(goodsBean.getGoodId(),0);
                                dbUtil.deleteByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
                                //goodsBean.setNumber(goodsBean.getNumber() -1);
                                //goodsBean.setIsChoice(false);
                                //((DialogViewHolder) holder).numTv.setText(goodsBean.getNumber()+"");
                                //((DialogViewHolder) holder).checkBox.setChecked(false);
                                if(data.size() > 0){
                                    data.remove(position);
                                    ShopDialogAdapter.this.notifyItemRemoved(position);
                                    Log.i("WATER","position == >> "+position);
                                }
                                upDialogView();
                            }else {
                                dbUtil.reduceGoodNum(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
                                goodsBean.setNumber(goodsBean.getNumber() - 1);
                                goodsBean.setIsChoice(true);
                                ((DialogViewHolder) holder).numTv.setText(goodsBean.getNumber()+"");
                                ((DialogViewHolder) holder).checkBox.setChecked(true);
                                upDialogView();
                            }
                        }
                    }
                });
        }
    }


    /**
     * 更新dialog的视图
     * */
    private void upDialogView(){
        if(type == FOOD_MAIN ){
            try {
                ((ShopDetailActivity)context).dialogView();
            }catch (Exception e){
                ((FoodDetailActivity)context).dialogView();
            }
        } else if(type == SHOP_MAIN){
            ((ShopDetailActivity)context).dialogView();
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

        CheckBox checkBox;
        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView tv_specname;
        TextView priceTv;
        RelativeLayout reduceIb;
        RelativeLayout moreIb;
        TextView numTv;



        private DialogViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.shop_cart_dialog_item_cb);
            avatarIm = (SimpleDraweeView) itemView.findViewById(R.id.shop_cart_dialog_item_avatar_im);
            nameTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_name_tv);
            tv_specname = (TextView) itemView.findViewById(R.id.tv_specname);
            priceTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_price_tv);
            reduceIb = (RelativeLayout) itemView.findViewById(R.id.shop_cart_dialog_item_reduce_im);
            moreIb = (RelativeLayout) itemView.findViewById(R.id.shop_cart_dialog_item_more_im);
            numTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_num_tv);

        }
    }


}
