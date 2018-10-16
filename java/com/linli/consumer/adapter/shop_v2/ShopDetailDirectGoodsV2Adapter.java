package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.ui.shop_v2.ShopDetailActivity;
import com.linli.consumer.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 * 店铺商品适配器
 * 店铺的商品
 */
public class ShopDetailDirectGoodsV2Adapter extends Adapter<ViewHolder> {

    private Context context;
    private List data;

    static final int TYPE_NORMAL = 0;       //商店进入的布局
    static final int TYPE_DIALOG = 1;       //dialog进入的布局
    private boolean isDialog;               //是否是dialpg进入
    private int type;

    private String shopName;

    //暂时的bean存储，用于保存要移除的bean，在check取消后，数据才进行改变，否则立即改变会造成数据不一致
    //recyclerView会在重用视图时报错，因为没有数据了
    @Deprecated
    private List<NewsBean.ResultBean.DataBean> tempList = new ArrayList<>();

    private DBUtil dbUtil ;

    private long titleId;


    public ShopDetailDirectGoodsV2Adapter(Context context, List list, boolean isDialog, int type, String shopName, long titleId) {
        this.context = context;
        this.data = list;
        this.isDialog = isDialog;
        dbUtil = DBUtil.getInstance(context);
        this.type = type;
        this.shopName = shopName;
        this.titleId = titleId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_NORMAL:
                view = LayoutInflater.from(context).inflate(R.layout.goods_shop_direct_item, parent, false);
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
        if (type == SHOP_MAIN) {
            if (holder instanceof ShopViewHolder) {

                final GoodsDetailBean bean = (GoodsDetailBean) data.get(position);
                ((ShopViewHolder) holder).sdv_direct_propic.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getPrimaryImage())));
                ((ShopViewHolder) holder).tv_pro_name.setText(bean.getName());
                ((ShopViewHolder) holder).tv_price.setText("￥"+ bean.getMinprice());

                ((ShopViewHolder) holder).ll_item_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoGoodsDetailActivity(context, bean.getId(), "精选专区");
                    }
                });
            } else if (type == FOOD_MAIN) {       //type等于美食的时候，由于美食和商城的实体类不同，这里需要分开判断
                //造成这个方法里有300行代码，能优化，但是不想
                if (holder instanceof ShopViewHolder) {

                    final GoodsDetailBean bean = (GoodsDetailBean) data.get(position);
                    ((ShopViewHolder) holder).sdv_direct_propic.setImageURI(bean.getPrimaryImage());
                    ((ShopViewHolder) holder).tv_pro_name.setText(bean.getName());
                    ((ShopViewHolder) holder).tv_price.setText(bean.getMinprice() + "");

                    ((ShopViewHolder) holder).ll_item_click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UIHelper.togoGoodsDetailActivity(context, bean.getId(), "精选专区");
                        }
                    });
                }
            } else if (type == SERVICE_MAIN) {
                if (holder instanceof ShopViewHolder) {

                    final GoodsDetailBean bean = (GoodsDetailBean) data.get(position);
                    ((ShopViewHolder) holder).sdv_direct_propic.setImageURI(bean.getPrimaryImage());
                    ((ShopViewHolder) holder).tv_pro_name.setText(bean.getName());
                    ((ShopViewHolder) holder).tv_price.setText(bean.getMinprice() + "");

                    ((ShopViewHolder) holder).ll_item_click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UIHelper.togoGoodsDetailActivity(context, bean.getId(), "精选专区");
                        }
                    });
                }
            }

        }
    }


    /**
     * 更新activity的视图
     * */
    private void upActivityView(){
        ((ShopDetailActivity)context).updateView();
    }


    /**
     * 更新dialog的视图
     * */
    private void upDialogView(){
        ((ShopDetailActivity)context).dialogView();
    }



    //TODO summary: 更改布局要对数据源进行处理，再在adapter中维护一个list，使用contain方法判读数据变化
    //TODO summary: 不要使用方法内的变量进行数据改变
    //TODO summary: Data ==>> Adapter ==>> View 时刻注意适配器读数据一定是一个数据源，有数据源适配数据，尽量不要有方法域的变量


    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;

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
        SimpleDraweeView sdv_direct_propic;
        TextView tv_pro_name;
        TextView tv_price;
        LinearLayout ll_item_click;

        private ShopViewHolder(View itemView) {
            super(itemView);
            sdv_direct_propic = (SimpleDraweeView)itemView.findViewById(R.id.sdv_direct_propic);
            tv_pro_name = (TextView)itemView.findViewById(R.id.tv_pro_name);
            tv_price = (TextView)itemView.findViewById(R.id.tv_price);
            ll_item_click = (LinearLayout) itemView.findViewById(R.id.ll_item_click);
        }
    }
    /**
     * dialog的列表的item的ViewHolder
     * */
    @Deprecated
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
     * @param isAdd true 为添加,false 为删除
     * */
    @Deprecated
    private void handleData(NewsBean.ResultBean.DataBean bean, boolean  isAdd){
        ((ShopDetailActivity)context).handleData(bean,isAdd);
    }

}
