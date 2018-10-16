package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.OrderHealthWay;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class OrderHealthWayAdapter extends BaseAdapter {
    private Context ctx;
    private List<OrderHealthWay> mOrderHealthWayList;


    public OrderHealthWayAdapter(Context ctx, List<OrderHealthWay> listOrderHealthWay) {
        this.ctx = ctx;
        this.mOrderHealthWayList = listOrderHealthWay;
    }

    @Override
    public int getCount() {
        return mOrderHealthWayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.order_health_way_listview_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mImageView = (ImageView) view.findViewById(R.id.order_iv_health_way_item);
            mViewHolder.tvname = (TextView) view.findViewById(R.id.iv_order_healthway_itemtitle);
            mViewHolder.tvliulanliang = (TextView) view.findViewById(R.id.tv_health_way_itemliulanliang);
            mViewHolder.tvlead = (TextView) view.findViewById(R.id.tv_order_healthway_item_lead);
            mViewHolder.tvlabel = (TextView) view.findViewById(R.id.tv_order_healthway_item_label);
            mViewHolder.tvcontent = (TextView) view.findViewById(R.id.tv_order_healthway_item_content);
            mViewHolder.tvtime = (TextView) view.findViewById(R.id.iv_order_health_item_date);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }


        if (mOrderHealthWayList != null) {
            mViewHolder.mImageView.setImageResource(mOrderHealthWayList.get(position).getImgpath());
            if (mOrderHealthWayList.get(position).getName() != null && (!mOrderHealthWayList.get(position).getName().equals(""))) {
                mViewHolder.tvname.setText(mOrderHealthWayList.get(position).getName());
            } else {
                mViewHolder.tvname.setText("");
            }

            if (mOrderHealthWayList.get(position).getLiulanliang() != 0) {
                mViewHolder.tvliulanliang.setText(mOrderHealthWayList.get(position).getLiulanliang() + "");
            } else {
                mViewHolder.tvliulanliang.setText("");
            }
            if (mOrderHealthWayList.get(position).getLead() != null && (!mOrderHealthWayList.get(position).getLead().equals(""))) {
                mViewHolder.tvlead.setText(mOrderHealthWayList.get(position).getLead());
            } else {
                mViewHolder.tvlead.setText("");
            }
            if (mOrderHealthWayList.get(position).getLabel() != null && (!mOrderHealthWayList.get(position).getLabel().equals(""))) {
                mViewHolder.tvlabel.setText(mOrderHealthWayList.get(position).getLabel());
            } else {
                mViewHolder.tvlabel.setText("");
            }
            if (mOrderHealthWayList.get(position).getContent() != null && (!mOrderHealthWayList.get(position).getContent().equals(""))) {
                mViewHolder.tvcontent.setText(mOrderHealthWayList.get(position).getContent());
            } else {
                mViewHolder.tvcontent.setText("");
            }
            if (mOrderHealthWayList.get(position).getTime() != null && (!mOrderHealthWayList.get(position).getTime().equals(""))) {
                mViewHolder.tvtime.setText(mOrderHealthWayList.get(position).getTime());
            } else {
                mViewHolder.tvtime.setText("");
            }

            return view;
        } else {

            return null;
        }

    }

    public class ViewHolder {
        ImageView mImageView;
        TextView tvname;
        TextView tvliulanliang;
        TextView tvlead;
        TextView tvlabel;
        TextView tvcontent;
        TextView tvtime;


    }

}