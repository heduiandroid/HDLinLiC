package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopSortFlashSaleAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






    public ShopSortFlashSaleAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_sort_flashsale_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final MallGoodsVo bean = (MallGoodsVo)data.get(position);
            ((ShopViewHolder)holder).nameTv.setText(bean.getMallGoods().getName());
            //((ShopViewHolder) holder).typeTv.setText(bean.getStoreName());
            ((ShopViewHolder) holder).saveNumTv.setText(bean.getMallGoods().getBrand());//库存不正确后台改起来太麻烦 用于显示品牌了
            ((ShopViewHolder) holder).oldPriceTv.setText("￥"+bean.getMallGoods().getMaxprice());
            ((ShopViewHolder) holder).newPriceTv.setText("￥"+bean.getMallGoods().getMinprice());

            if(!TextUtils.isEmpty(bean.getImgSrc())){
                ((ShopViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImgSrc())));
            }
            /*if(!bean.isAdd()){
                ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.orange));
            } else  {
                ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.gray_online_line));
            }*/
            //TODO 这里加入购物车的逻辑
            ((ShopViewHolder) holder).addBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*if(bean.isAdd()){
                        ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.orange));
                        bean.setAdd(false);
                        Log.i("WATER","从购物车移除");
                    } else  {
                        ((ShopViewHolder) holder).addBt.setBackgroundColor(context.getResources().getColor(R.color.gray_online_line));
                        bean.setAdd(true);
                        Log.i("WATER","添加到购物车");
                    }*/
                }
            });
            ((ShopViewHolder) holder).containerLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.togoGoodsDetailActivity(context,bean.getMallGoods().getId(),bean.getStoreName());
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof MallGoodsVo ){
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
        TextView typeTv;
        TextView saveNumTv;
        TextView oldPriceTv;
        TextView newPriceTv;
        Button addBt;

        LinearLayout containerLl;



        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (ImageView)itemView.findViewById(R.id.shop_sort_flashsale_item_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.shop_sort_flashsale_item_name_tv);
            typeTv = (TextView)itemView.findViewById(R.id.shop_sort_flashsale_item_type_tv);
            saveNumTv = (TextView)itemView.findViewById(R.id.shop_sort_flashsale_item_savenum_tv);
            oldPriceTv = (TextView)itemView.findViewById(R.id.shop_sort_flashsale_item_oldprice_tv);
            newPriceTv = (TextView)itemView.findViewById(R.id.shop_sort_flashsale_item_newprice_tv);
            addBt = (Button)itemView.findViewById(R.id.shop_sort_flashsale_item_add_bt);

            containerLl = (LinearLayout)itemView.findViewById(R.id.shop_sort_flashsale_item_container_ll);

        }
    }



}
