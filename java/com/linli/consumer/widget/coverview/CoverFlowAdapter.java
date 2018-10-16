package com.linli.consumer.widget.coverview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.mock.NewsBean;

import java.util.LinkedList;

/**
 * Created by yichao on 16/2/25.
 *
 * 第三方控件，用于在美食详情展示美食
 *
 * 是一个coverView 的 适配器
 *
 * 实体类中要添加数据 isBorder
 */
public class CoverFlowAdapter extends RecyclerView.Adapter<CoverFlowAdapter.ViewHolder> {

    private static String TAG = "CoverFlowAdapter";

    private LinkedList<NewsBean.ResultBean.DataBean> list;
    private int border_position = 0;

    public CoverFlowAdapter(LinkedList<NewsBean.ResultBean.DataBean> list, Context context){
        this.list = list;

    }

    @Override
    public CoverFlowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.food_detail_main_container_im, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean)list.get(position);

        holder.card_image.setImageURI(bean.getThumbnail_pic_s());
        if(position <border_position || position > getItemCount() - border_position -1){

            holder.card_image.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView card_image;

        private ViewHolder(View v) {
            super(v);
            card_image = (SimpleDraweeView) v.findViewById(R.id.food_detail_main_container_sim);
        }
    }

    public void setBorder_position(int border_position) {
        this.border_position = border_position;
        for (int i  = 0; i < border_position; i++){
            NewsBean.ResultBean.DataBean dataBean1 = new NewsBean.ResultBean.DataBean();
            dataBean1.setBorder(true);
            NewsBean.ResultBean.DataBean dataBean2 = new NewsBean.ResultBean.DataBean();
            dataBean2.setBorder(true);

            list.addFirst(dataBean1);
            list.addLast(dataBean2);
        }
        this.notifyDataSetChanged();
    }


}
