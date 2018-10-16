package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.ui.directbusiness.ShopCartDirectActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ShopCarDirectAdapter extends BaseAdapter {
    private ArrayList<GoodsBean> list;
    private Context context;
    private static HashMap<Integer,Boolean> isSelected;
    private LayoutInflater inflater = null;

    public ShopCarDirectAdapter(ArrayList<GoodsBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer,Boolean>();
        initData();//init datas
    }

    private void initData() {
        for (int i = 0; i<list.size();i++){
            getIsSelected().put(i,false);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = new ViewHolder();
        convertView = inflater.inflate(R.layout.shopcartfeedback_item,null);
        holder.cb = (CheckBox) convertView.findViewById(R.id.cb_item);
        holder.proPic = (SimpleDraweeView) convertView.findViewById(R.id.niv_shopcart_propic);
        holder.proName = (TextView) convertView.findViewById(R.id.tv_shopcart_proname);
        holder.proPrice = (TextView) convertView.findViewById(R.id.tv_shopcart_price);
        holder.proNumber = (TextView) convertView.findViewById(R.id.tv_shopcart_number);
        holder.iv_num_down = (ImageView) convertView.findViewById(R.id.iv_num_down);
        holder.iv_num_up = (ImageView) convertView.findViewById(R.id.iv_num_up);
        holder.iv_deleteitem = (ImageView) convertView.findViewById(R.id.iv_deleteitem);
        convertView.setTag(holder);//
        holder.cb.setChecked(getIsSelected().get(position));
        holder.proPic.setImageURI(list.get(position).getImagePath() + Common.WSMALLERPICPARAM);
        holder.proPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (ShopCartDirectActivity.category){
                    case 1://商城商品详情
                        UIHelper.togoGoodsDetailActivity(context,list.get(position).getId(),list.get(position).getShopName());
//                        UIHelper.togoShopDetailActivity(context,FeedbackDetailActivity.shopId,FeedbackDetailActivity.shopName,SHOP_MAIN);
                        break;
                    case 2://美食商品详情
//                        UIHelper.togoFoodDetailActivity(context,list.get(position).getId(),list.get(position).getShopName(),0,0);
//                        UIHelper.togoShopDetailActivity(context,FeedbackDetailActivity.shopId,FeedbackDetailActivity.shopName,FOOD_MAIN);
                        break;
                    case 3://服务项详情
                        Toast.makeText(context,"暂不支持服务商家",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.proName.setText(list.get(position).getGoodsName());
        holder.proPrice.setText(list.get(position).getPrice()+"");
        holder.proNumber.setText(list.get(position).getNumber()+"");
        holder.iv_num_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = list.get(position).getNumber();
                if (holder.cb.isChecked() == true) {
                    if (number > 1) {
                        BigDecimal unitPrice = BigDecimal.valueOf(list.get(position).getPrice());
                        ShopCartDirectActivity.checkedPrice = ShopCartDirectActivity.checkedPrice.subtract(unitPrice);
                        list.get(position).setNumber(number - 1);
                    }
                } else {
                    if (number > 1) {
                        list.get(position).setNumber(number - 1);
                    }
                }
                ShopCartDirectActivity.dataChanged();
            }
        });
        holder.iv_num_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int residueNum = list.get(position).getResidueNum();
                int number = list.get(position).getNumber();
                BigDecimal unitPrice = BigDecimal.valueOf(list.get(position).getPrice());
                if (holder.cb.isChecked() == true){
                    ShopCartDirectActivity.checkedPrice = ShopCartDirectActivity.checkedPrice.add(unitPrice);
                    list.get(position).setNumber(number+1);
                }else {
                    list.get(position).setNumber(number+1);
                }

                ShopCartDirectActivity.dataChanged();
            }
        });
        holder.iv_deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除某一条在购物车并刷新
            }
        });
        return convertView;
    }
    public static HashMap<Integer,Boolean> getIsSelected(){
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected){
        ShopCarDirectAdapter.isSelected = isSelected;
    }
    public class ViewHolder{
        public SimpleDraweeView proPic;
        public CheckBox cb;
        public TextView proName;
        public TextView proPrice;
        public TextView proNumber;
        public ImageView iv_num_down;
        public ImageView iv_num_up;
        ImageView iv_deleteitem;
    }
}
