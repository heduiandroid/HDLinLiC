package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by hasee on 2018/7/19.
 */

public class HorizontalGoodsAdapter extends BaseAdapter {
    private List<MallGoodsVo> list;
    private Context context;

    public HorizontalGoodsAdapter(List<MallGoodsVo> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.horizontal_good_cell,null);
            holder.niv_cell_goodpic = (SimpleDraweeView) convertView.findViewById(R.id.niv_cell_goodpic);
            holder.tv_good_name = (TextView) convertView.findViewById(R.id.tv_good_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.niv_cell_goodpic.setImageURI(CommonUtil.zoomPic(list.get(position).getMallGoods().getPrimaryImage()));
        holder.tv_good_name.setText(list.get(position).getMallGoods().getName());
        if (list.get(position).getMallGoods().getMinprice() != null) {
            holder.tv_price.setText("￥" + list.get(position).getMallGoods().getMinprice());
        }
        return convertView;
    }
    public class HolderView{
        SimpleDraweeView niv_cell_goodpic;
        TextView tv_good_name;
        TextView tv_price;
    }
}
