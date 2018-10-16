package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.DirectGoodBean;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by hasee on 2018/7/19.
 */

public class HorizontalGoodsVerAdapter extends BaseAdapter {
    private List<DirectGoodBean.DataBean> list;
    private Context context;

    public HorizontalGoodsVerAdapter(List<DirectGoodBean.DataBean> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.horizontal_good_ver_cell,null);
            holder.niv_cell_goodpic = (SimpleDraweeView) convertView.findViewById(R.id.niv_cell_goodpic);
            holder.tv_good_name = (TextView) convertView.findViewById(R.id.tv_good_name);
            holder.tv_notice = (TextView) convertView.findViewById(R.id.tv_notice);
            holder.tv_soldout = (TextView) convertView.findViewById(R.id.tv_soldout);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.niv_cell_goodpic.setImageURI(CommonUtil.zoomPic(list.get(position).getPrimaryImage()));
        holder.tv_good_name.setText(list.get(position).getName());
        Long salecate = list.get(position).getSalescategoryId();
        if (salecate != null){
            holder.tv_notice.setTextColor(context.getResources().getColor(R.color.lightred));
            if (salecate == 1L){
                holder.tv_notice.setText("可使用红包余额购买");
            }else if (salecate == 2L){
                holder.tv_notice.setText("可参加满减活动");
            }else if (salecate == 3L){
                holder.tv_notice.setText("参加0元购活动");
            }else if (salecate == 4L){
                holder.tv_notice.setText("参加拼单活动");
            }
        }else {
            holder.tv_notice.setTextColor(context.getResources().getColor(R.color.notselect_text));
            holder.tv_notice.setText("品牌："+list.get(position).getBrand());
        }
        int soldnum = 0;
        if (list.get(position).getSalesVolume() != null){
            soldnum = list.get(position).getSalesVolume();
        }
        holder.tv_soldout.setText("已售"+soldnum+"件");
        if (list.get(position).getMinprice() != null) {
            holder.tv_price.setText("￥" + list.get(position).getMinprice());
        }
        return convertView;
    }
    public class HolderView{
        SimpleDraweeView niv_cell_goodpic;
        TextView tv_good_name,tv_notice,tv_soldout;
        TextView tv_price;
    }
}
