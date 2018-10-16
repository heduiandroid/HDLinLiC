package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.ShopSortBean;

import java.util.List;

/**
 * Created by hasee on 2018/7/24.
 */

public class CatesDirectAdapter extends BaseAdapter {
    private List<ShopSortBean.DataBean> list;
    private Context context;
    //初始化位置
    private int clickStatus = 0;
    public CatesDirectAdapter(List<ShopSortBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setSeclection(int position) {
        clickStatus = position;
    }
    public int getSeclection() {
        return clickStatus;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.cates_direct_item,null);
            holder.rl_background = (RelativeLayout) convertView.findViewById(R.id.rl_background);
            holder.view_line = convertView.findViewById(R.id.view_line);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }

        if (clickStatus==position) {//special background
            holder.rl_background.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.view_line.setVisibility(View.VISIBLE);
            holder.tv_name.setText(list.get(position).getName());
        }else {//normal
            holder.rl_background.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            holder.view_line.setVisibility(View.GONE);
            holder.tv_name.setText(list.get(position).getName());
        }
        return convertView;
    }
    public class HolderView{
        RelativeLayout rl_background;
        View view_line;
        TextView tv_name;
    }
}
