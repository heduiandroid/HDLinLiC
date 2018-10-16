package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.TakeCarNeedListBean;
import com.linli.consumer.utils.CommonUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2016/11/15.
 */
public class TakeCarNeedAdapter extends BaseAdapter {
    private List<TakeCarNeedListBean.DataBean> list;
    private Context context;

    public TakeCarNeedAdapter(List<TakeCarNeedListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.takecar_need_list_item,null);
            holder.tv_start = (TextView) convertView.findViewById(R.id.tv_start);
            holder.tv_end = (TextView) convertView.findViewById(R.id.tv_end);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_start.setText(list.get(position).getStartingPoint());
        String strEnd = list.get(position).getEndPoint();
        if (list.get(position).getType() == 2){
            strEnd = strEnd + " (转换文字内容)";
        }
        holder.tv_end.setText(strEnd);
        //需求状态:0-已取消  1-等待接单   2-进行中   3-已完成
        switch (list.get(position).getStatus()){
            case 0:
                holder.tv_status.setText("已取消");
                break;
            case 1:
                holder.tv_status.setText("等待接单");
                break;
            case 2:
                holder.tv_status.setText("进行中");
                break;
            case 3:
                holder.tv_status.setText("已完成");
                break;
            default:
                holder.tv_status.setText("");
                break;
        }
        Date releaseTime = new Date(list.get(position).getCreateTime());
        holder.tv_time.setText(CommonUtil.getFriendlytime(releaseTime)+"发布");
        return convertView;
    }
    public class HolderView{
        TextView tv_start;
        TextView tv_end;
        TextView tv_time;
        TextView tv_status;
    }
}
