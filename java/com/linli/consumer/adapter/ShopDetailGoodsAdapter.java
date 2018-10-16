package com.linli.consumer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.ui.shop_v2.ShopDetailActivity;
import com.linli.consumer.widget.GoodsNumLayout;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 * 店铺商品适配器
 * 店铺的商品
 */
@Deprecated
public class ShopDetailGoodsAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List data;

    static final int TYPE_NORMAL = 0;       //商店进入的布局
    static final int TYPE_DIALOG = 1;       //dialog进入的布局
    private boolean isDialog;               //是否是dialpg进入

    //暂时的bean存储，用于保存要移除的bean，在check取消后，数据才进行改变，否则立即改变会造成数据不一致
    //recyclerView会在重用视图时报错，因为没有数据了
    private List<NewsBean.ResultBean.DataBean> tempList = new ArrayList<>();



    public ShopDetailGoodsAdapter(Context context, List list,boolean isDialog) {
        this.context = context;
        this.data = list;
        this.isDialog = isDialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_NORMAL:
                view = LayoutInflater.from(context).inflate(R.layout.shop_detail_goods_item, parent, false);
                holder = new ShopViewHolder(view);
                break;
            case TYPE_DIALOG:
                view = LayoutInflater.from(context).inflate(R.layout.shop_cart_dialog_item,parent,false);
                holder = new DialogViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof ShopViewHolder) {
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean) data.get(position);
            //MallGoodsVo bean = (MallGoodsVo) data.get(position);
            bean.setPosition(position);
            //((ShopViewHolder) holder).nameTv.setText(bean.getMallGoods().getName());
            //((ShopViewHolder) holder).priceTv.setText(bean.getMallGoods().getNospecPlatformPrice()+"");
            //if(!TextUtils.isEmpty(bean.getMallGoods().getPrimaryImage())){
                //((ShopViewHolder) holder).avatarIm.setImageURI(Uri.parse(bean.getMallGoods().getPrimaryImage()));
            //}
            if(bean.isShow()){
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
            }else {
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
            }
            ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setNumber(bean.getNumber()+1);
                    bean.setShow(true);
                    handleData(bean,true);
                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                    ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");

                }
            });
            ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.getNumber() > 0){
                        if((bean.getNumber() -1) == 0 ){
                            bean.setNumber(bean.getNumber() -1);
                            bean.setShow(false);
                            handleData(bean,false);
                            ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
                        }else {
                            bean.setNumber(bean.getNumber() - 1);
                            bean.setShow(true);
                            handleData(bean,true);
                            ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                            ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        }
                    }
                }
            });

        }else {
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean) data.get(position);
            ((DialogViewHolder) holder).nameTv.setText("包子"+position);
            ((DialogViewHolder) holder).priceTv.setText("￥"+bean.getPrice());
            ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
            if(!TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                ((DialogViewHolder) holder).avatarIm.setImageURI(Uri.parse(bean.getThumbnail_pic_s()));
            }
            ((DialogViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bean.setShow(true);
                        if(bean.getNumber() == 0){
                            bean.setNumber(1);
                            ((DialogViewHolder) holder).numTv.setText(1+"");
                        }
                        handleData(bean,true);
                        handleDialogData();
                    }else {
                        bean.setShow(false);
                        bean.setNumber(0);
                        ((DialogViewHolder) holder).numTv.setText(0+"");
                        handleDialogData();
                        //此处未选中不清除总list的数据，任然保持，防止dialog中的list在复用视图的时候出现数据不一致的错误
                        //handData中通过 isShow 属性来进行数据的总计
                        //handleData(bean,false);
                        /*if(!tempList.contains(bean)){
                            tempList.add(bean);
                        }*/

                    }
                }
            });

            ((DialogViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setNumber(bean.getNumber()+1);
                    bean.setShow(true);
                    handleData(bean,true);
                    ((DialogViewHolder) holder).checkBox.setChecked(true);
                    ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                    handleDialogData();

                }
            });
            ((DialogViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.getNumber() > 0){
                        if((bean.getNumber() -1) == 0 ){
                            bean.setNumber(bean.getNumber() -1);
                            bean.setShow(false);
                            //handleData(bean,false);
                            //ShopDetailGoodsAdapter.this.notifyDataSetChanged();
                            ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                            ((DialogViewHolder) holder).checkBox.setChecked(false);
                            handleDialogData();
                          /*  if(!tempList.contains(bean)){
                                tempList.add(bean);
                            }*/
                        }else {
                            bean.setNumber(bean.getNumber() - 1);
                            bean.setShow(true);
                            handleData(bean,true);
                            ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                            handleDialogData();
                        }
                    }
                }
            });
        }
    }



    /**
     * 删除数据，在dialog取消时调用
     * */
 /*   public void deleteTempBean(){
        if(tempList.size() != 0){
            for(int i =0;i<tempList.size();i++){
                handleData(tempList.get(i),false);
                tempList.remove(i);
            }
        }

    }*/
    /**
     * @param isAdd true 为添加,false 为删除
     * */
    private void handleData(NewsBean.ResultBean.DataBean bean,boolean  isAdd){
        ((ShopDetailActivity)context).handleData(bean,isAdd);
    }

    private void handleDialogData(){
        ((ShopDetailActivity)context).dialogView();
    }




    @Override
    public int getItemViewType(int position) {
        if (!isDialog) {
            return TYPE_NORMAL;
        } else {
            return TYPE_DIALOG;
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


    /**
     * 商品列表的item的viewHolder
     * */
    private class ShopViewHolder extends ViewHolder {

        SimpleDraweeView avatarIm;
        //ImageView avatarIm;
        TextView nameTv;
        TextView priceTv;
        LinearLayout reduceIb;
        LinearLayout moreIb;
        GoodsNumLayout goodsNumLayout;


        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (SimpleDraweeView) itemView.findViewById(R.id.shop_detail_goods_item_avatar_im);
            //avatarIm = (ImageView) itemView.findViewById(R.id.shop_detail_goods_item_avatar_im);
            nameTv = (TextView) itemView.findViewById(R.id.shop_detail_goods_item_name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.shop_detail_goods_item_price_tv);
            reduceIb = (LinearLayout) itemView.findViewById(R.id.shop_detail_goods_item_reduce_ib);
            moreIb = (LinearLayout) itemView.findViewById(R.id.shop_detail_goods_item_more_ib);
            goodsNumLayout = (GoodsNumLayout) itemView.findViewById(R.id.shop_detail_goods_item_num_widget);


        }
    }
    /**
     * dialog的列表的item的ViewHolder
     * */
    private class DialogViewHolder extends ViewHolder {

        CheckBox checkBox;
        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView priceTv;
        RelativeLayout reduceIb;
        RelativeLayout moreIb;
        TextView numTv;


        private DialogViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.shop_cart_dialog_item_cb);
            avatarIm = (SimpleDraweeView) itemView.findViewById(R.id.shop_cart_dialog_item_avatar_im);
            nameTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_price_tv);
            reduceIb = (RelativeLayout) itemView.findViewById(R.id.shop_cart_dialog_item_reduce_im);
            moreIb = (RelativeLayout) itemView.findViewById(R.id.shop_cart_dialog_item_more_im);
            numTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_num_tv);


        }
    }

    /**
     * 之前的一个写法，在不同适配器里会有两个beanList,会造成数据混乱难调
     * */
    @Deprecated
    public void bonBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof ShopViewHolder) {


            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean) data.get(position);
            ((ShopViewHolder) holder).nameTv.setText("包子"+position);
            ((ShopViewHolder) holder).priceTv.setText("￥"+bean.getPrice());
            if (TextUtils.isEmpty(bean.getThumbnail_pic_s())) {
                Picasso.with(context).load(R.mipmap.ic_launcher).fit().into(((ShopViewHolder) holder).avatarIm);
            } else {
                Picasso.with(context).load(bean.getThumbnail_pic_s()).fit()
                        .into(((ShopViewHolder) holder).avatarIm);
            }
            if(bean.getNumber() != 0){
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
            }else{
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
            }
            if(tempList.contains(bean)){
                //((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setNumber(bean.getNumber()+1);
                        ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                        ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        if(!tempList.contains(bean)){
                            tempList.add(bean);
                        }
                        bean.setShow(true);
                        handleData(bean,true);
                        Log.i("WATER",bean.isShow()?"true":"false");
                        Log.i("WATER","包含 more");
                    }
                });
                ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            bean.setNumber(bean.getNumber()-1);
                            ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                            if(bean.getNumber() == 0){
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
                                if(tempList.contains(bean)){
                                    tempList.remove(bean);
                                    handleData(bean,false);
                                    Log.i("WATER","包含 reduce = 0");
                                }
                                bean.setShow(false);
                            }else {
                                handleData(bean,true);
                                Log.i("WATER","包含 reduce != 0");
                            }

                        }
                    }
                });
            }else {
                ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            bean.setNumber(bean.getNumber() - 1);
                            ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                            ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                            if(bean.getNumber() == 0){
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
                                if(tempList.contains(bean)){
                                    tempList.remove(bean);
                                    handleData(bean,false);
                                    Log.i("WATER","不包含 reduce = 0");
                                }
                                bean.setShow(false);
                            }else {
                                handleData(bean,true);
                                Log.i("WATER","不包含 reduce != 0");
                            }

                        }
                    }
                });
                ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setNumber(bean.getNumber()+1);
                        ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                        ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        bean.setShow(true);
                        if(!tempList.contains(bean)){
                            tempList.add(bean);
                        }
                        handleData(bean,true);
                        Log.i("WATER","不包含 more");
                    }
                });
            }
        }
    }



}
