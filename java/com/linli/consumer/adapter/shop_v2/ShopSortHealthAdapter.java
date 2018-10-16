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
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopSortHealthAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;






    public ShopSortHealthAdapter(Context context, List<Object> list){
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_sort_health_item,null);
                holder = new ShopViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof ShopViewHolder){
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean)data.get(position);

            ((ShopViewHolder)holder).nameTv.setText("小城故事");

            if(!TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                ((ShopViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getThumbnail_pic_s())));
            }
            ((ShopViewHolder) holder).hitsTv.setText("333");
            ((ShopViewHolder) holder).contentTv.setText("良药苦口利于病，这说的就是中药膳食，对于长期中药养身或者中药治疗的人来说应该不陌生，这得益于");
            ((ShopViewHolder) holder).labelTv.setText("养身 治疗 药膳");
            ((ShopViewHolder) holder).timeTv.setText("2016-1-1");
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



    private class ShopViewHolder extends ViewHolder {

        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView hitsTv;
        TextView contentTv;
        TextView labelTv;
        TextView timeTv;



        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (SimpleDraweeView)itemView.findViewById(R.id.shop_sort_health_item_avatar_im);
            nameTv = (TextView)itemView.findViewById(R.id.shop_sort_health_item_name_tv);
            hitsTv = (TextView)itemView.findViewById(R.id.shop_sort_health_item_hits_tv);
            contentTv = (TextView)itemView.findViewById(R.id.shop_sort_health_item_content_tv);
            labelTv = (TextView)itemView.findViewById(R.id.shop_sort_health_item_label_tv);
            timeTv = (TextView)itemView.findViewById(R.id.shop_sort_health_item_time_tv);

        }
    }



}
