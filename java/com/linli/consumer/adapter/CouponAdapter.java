package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.CouponsBean;

import java.util.List;

/**
 * Created by hasee on 2016/12/26.
 */

public class CouponAdapter extends BaseAdapter {
    private List<CouponsBean.DataBean> list;
    private Context context;

    public CouponAdapter(List<CouponsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mycoupons_item,null);
            holder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_yuan = (TextView) convertView.findViewById(R.id.tv_yuan);
            holder.iv_couponname = (ImageView) convertView.findViewById(R.id.iv_couponname);
            holder.view_show_couponQRCode = convertView.findViewById(R.id.view_show_couponQRCode);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        if (list.get(position).getStoreName() != null){
            holder.tv_shopname.setText(list.get(position).getStoreName());
        }else {
            holder.tv_shopname.setText("");
        }
        if (list.get(position).getCouponSum() != null){
            Double rprice = list.get(position).getCouponSum();
            String finalPrice = rprice.toString();
            if (finalPrice.length()>2 && finalPrice.length() < 4){//字太多了 字体大小小一些
                holder.tv_price.setTextSize(30);
            }else if (finalPrice.length() > 3){
                holder.tv_price.setTextSize(30);
            }
            holder.tv_price.setText(finalPrice);
        }

        switch (list.get(position).getType()){//类型（1代金卷，2折扣卷，3满减卷，4免单卷）
            case 1:
                holder.iv_couponname.setImageResource(R.mipmap.iv_xianjin_spare);//代金券是纯粹减去多少钱 免单是有多少减多少
                break;
            case 2:
                //“元”字改成“折”字
                holder.tv_yuan.setText("折");
                holder.iv_couponname.setImageResource(R.mipmap.iv_zhekou_spare);
                break;
            case 3:
                //“元”字改成没字
                holder.tv_yuan.setText("");
                holder.iv_couponname.setImageResource(R.mipmap.iv_manjian_spare);
                if (list.get(position).getCouponMaxSum() != null && list.get(position).getCouponSum() != null){
                    double subMoney = list.get(position).getCouponSum();
                    int price;
                    int jianPrice;
                    double rprice = list.get(position).getCouponMaxSum();
                    price = (int)rprice;
                    jianPrice = (int)subMoney;
                    String maxPrice = price + "";
                    String jianMoney = jianPrice + "";
                    holder.tv_price.setTextSize(25);//字太多了 字体大小小一些
                    holder.tv_price.setText("满"+maxPrice+"减"+jianMoney);
                }else {
                    holder.tv_price.setText("满减券");
                }
                break;
            case 4:
                holder.tv_yuan.setText("");
                holder.iv_couponname.setImageResource(R.mipmap.iv_miandan_spare);
                if (list.get(position).getCouponSum() != null){
                    int price;
                    double rprice = list.get(position).getCouponSum();
                    price = (int)rprice;
                    String maxPrice = price + "";
                    holder.tv_price.setTextSize(20);//字太多了 字体大小小一些
                    holder.tv_price.setText(maxPrice+"元以内全免");
                }else {
                    holder.tv_price.setText("全免");
                }
                break;
            default:
                break;
        }
        return convertView;
    }
    public class HolderView{
        TextView tv_shopname;
        TextView tv_price;
        TextView tv_yuan;
        ImageView iv_couponname;//券类型的图片
        View view_show_couponQRCode;
    }
}
