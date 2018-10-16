package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.ui.shop_v2.ShopDetailActivity;
import com.linli.consumer.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 * {@link com.linli.consumer.widget.ShopCartDialog}
 * 店铺商品适配器
 * 店铺的商品
 */

//*****原始版本，不建议删除******
@Deprecated
public class ShopDialogAdapter1 extends Adapter<ViewHolder> {

    private Context context;
    private List data;

    static final int TYPE_DIALOG = 1;       //dialog进入的布局
    private int type;

    private String shopName;

    //暂时的bean存储，用于保存要移除的bean，在check取消后，数据才进行改变，否则立即改变会造成数据不一致
    //recyclerView会在重用视图时报错，因为没有数据了
    private List<NewsBean.ResultBean.DataBean> tempList = new ArrayList<>();

    private DBUtil dbUtil ;



    public ShopDialogAdapter1(Context context, List list,boolean isDialog,int type) {
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
        if(type == SHOP_MAIN){
                final MallGoodsVo bean = (MallGoodsVo) data.get(position);
                ((DialogViewHolder) holder).nameTv.setText(bean.getMallGoods().getName());
                ((DialogViewHolder) holder).priceTv.setText(bean.getMallGoods().getNospecPlatformPrice()+"");
                ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                if(!TextUtils.isEmpty(bean.getMallGoods().getPrimaryImage())){
                    ((DialogViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getMallGoods().getPrimaryImage())));
                }
                ((DialogViewHolder) holder).checkBox.setChecked(bean.isShow());
                ((DialogViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            bean.setShow(true);
                            upDialogView();
                        }else {
                            bean.setShow(false);
                            upDialogView();
                        }
                    }
                });

                /**
                 * 减到0 移除
                 * 未选中：添加或减少 都变选中
                 * 由选中 》 未选中 数字不变
                 * */
                ((DialogViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setNumber(bean.getNumber()+1);
                        bean.setShow(true);
                        ((DialogViewHolder) holder).checkBox.setChecked(true);
                        ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");

                        upDialogView();

                    }
                });
                ((DialogViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            if((bean.getNumber() -1) == 0 ){
                                bean.setNumber(bean.getNumber() -1);
                                bean.setShow(false);
                                ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                                ((DialogViewHolder) holder).checkBox.setChecked(false);
                                if(bean.getMallGoods().getIsSpec() == 0){
                                    dbUtil.reduceGoodNum(bean.getMallGoods().getId(),0);
                                } else if(bean.getMallGoods().getIsSpec() == 1){
                                    dbUtil.reduceGoodNum(bean.getMallGoods().getId(),0);
                                }
                                upDialogView();
                            }else {
                                bean.setNumber(bean.getNumber() - 1);
                                bean.setShow(true);
                                ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                                ((DialogViewHolder) holder).checkBox.setChecked(true);
                                if(bean.getMallGoods().getIsSpec() == 0){
                                    dbUtil.reduceGoodNum(bean.getMallGoods().getId(),0);
                                } else if(bean.getMallGoods().getIsSpec() == 1){
                                    dbUtil.reduceGoodNum(bean.getMallGoods().getId(),0);
                                }
                                upDialogView();
                            }
                        }
                    }
                });

        } else if(type == FOOD_MAIN) {       //type等于美食的时候，由于美食和商城的实体类不同，这里需要分开判断


            final FoodListBean.DataBean bean = (FoodListBean.DataBean) data.get(position);
            ((DialogViewHolder) holder).nameTv.setText(bean.getName());
            ((DialogViewHolder) holder).priceTv.setText(bean.getPrice() + "");
            ((DialogViewHolder) holder).numTv.setText(bean.getNumber() + "");
            if (!TextUtils.isEmpty(bean.getImgpath())) {
                ((DialogViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImgpath())));
            }
            ((DialogViewHolder) holder).checkBox.setChecked(bean.isShow());
            ((DialogViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        bean.setShow(true);
                        upDialogView();
                    } else {
                        bean.setShow(false);
                        upDialogView();
                        //此处未选中不清除总list的数据，任然保持，防止dialog中的list在复用视图的时候出现数据不一致的错误
                        //handData中通过 isShow 属性来进行数据的总计
                        //handleData(bean,false);

                    }
                }
            });

            /**
             * 减到0 移除
             * 未选中：添加或减少 都变选中
             * 由选中 》 未选中 数字不变
             * */
            ((DialogViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setNumber(bean.getNumber() + 1);
                    bean.setShow(true);
                    ((DialogViewHolder) holder).checkBox.setChecked(true);
                    ((DialogViewHolder) holder).numTv.setText(bean.getNumber() + "");
                    dbUtil.addGoodNum(dbUtil.convertFood(bean));    //TODO shopName
                    upDialogView();

                }
            });
            ((DialogViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getNumber() > 0) {
                        if ((bean.getNumber() - 1) == 0) {
                            bean.setNumber(bean.getNumber() - 1);
                            bean.setShow(false);
                            ((DialogViewHolder) holder).numTv.setText(bean.getNumber() + "");
                            ((DialogViewHolder) holder).checkBox.setChecked(false);
                            dbUtil.reduceGoodNum(bean.getId(),0);
                            upDialogView();
                        } else {
                            bean.setNumber(bean.getNumber() - 1);
                            bean.setShow(true);
                            ((DialogViewHolder) holder).numTv.setText(bean.getNumber() + "");
                            ((DialogViewHolder) holder).checkBox.setChecked(true);
                            dbUtil.reduceGoodNum(bean.getId(),0);
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
        ((ShopDetailActivity)context).dialogView();
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


}
