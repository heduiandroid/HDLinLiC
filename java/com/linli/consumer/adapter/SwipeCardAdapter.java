package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.common.Common;

import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by hasee on 2017/6/2.
 */

public class SwipeCardAdapter extends BaseAdapter {
    private List<GoodsBean> list;
    private Context context;
    private int MARGINLEFTRIGHT = 60;//左右距离
    private int MARGINTOP = 120;//上方距离
    private int MARGINBOTTOM = 200;//下方距离
    private int CARDCOUNTS = 4;//需要几张卡片在视野内
    private int PROPORTION = 15;//固定值缩小比例
    public SwipeCardAdapter(List<GoodsBean> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;

        if (convertView == null){
            holder = new HolderView();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.swipecard_item, parent, false);
            holder.iv_pro_pic1 = (SimpleDraweeView) convertView.findViewById(R.id.iv_pro_pic1);
            holder.iv_pro_pic2 = (SimpleDraweeView) convertView.findViewById(R.id.iv_pro_pic2);
            holder.tv_prod_name = (TextView) convertView.findViewById(R.id.tv_prod_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.iv_num_down = (ImageView) convertView.findViewById(R.id.iv_num_down);
            holder.iv_num_up = (ImageView) convertView.findViewById(R.id.iv_num_up);
            holder.tv_shopcart_number = (TextView) convertView.findViewById(R.id.tv_shopcart_number);
            holder.tv_cailiao = (TextView) convertView.findViewById(R.id.tv_cailiao);
            holder.rl_frame = (RelativeLayout) convertView.findViewById(R.id.rl_frame);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        //android:text="用料：主料【白菜、肉】    辅料【盐、酱油、醋】"
        if (list.get(position).getType() == FOOD_MAIN){
            holder.tv_cailiao.setVisibility(View.VISIBLE);
            holder.tv_cailiao.setText(list.get(position).getGoodsSpecName());//这里显示用料用的是规格名称字段
        }else if (list.get(position).getType() == SHOP_MAIN){
            holder.tv_cailiao.setVisibility(View.GONE);
        }
        holder.iv_pro_pic1.setImageURI(list.get(position).getImagePath()+ Common.SMALLERPICPARAM);
        if (position == 0){//如果是第一张图片 就显示稍微清楚点的图片
            holder.iv_pro_pic2.setImageURI(list.get(position).getImagePath());
        }
        holder.tv_prod_name.setText(list.get(position).getGoodsName());
        holder.tv_price.setText(list.get(position).getPrice()+"");
        holder.tv_shopcart_number.setText(list.get(position).getNumber()+"");
        final HolderView finalHolder = holder;
        holder.iv_num_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getNumber()>1){
                    list.get(position).setNumber(list.get(position).getNumber() - 1);
                    finalHolder.tv_shopcart_number.setText(list.get(position).getNumber()+"");
                }
            }
        });
        holder.iv_num_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setNumber(list.get(position).getNumber() + 1);
                finalHolder.tv_shopcart_number.setText(list.get(position).getNumber()+"");
            }
        });
        if (position<CARDCOUNTS){
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) holder.rl_frame.getLayoutParams();
            p.setMargins(MARGINLEFTRIGHT + position * PROPORTION, MARGINTOP + position * PROPORTION, MARGINLEFTRIGHT + position * PROPORTION, MARGINBOTTOM - position * PROPORTION);
            holder.rl_frame.requestLayout();
        }
        return convertView;
    }
    public class HolderView{
        SimpleDraweeView iv_pro_pic1,iv_pro_pic2;//一个模糊的 一个清晰的
        TextView tv_prod_name;
        TextView tv_price;
        ImageView iv_num_down,iv_num_up;
        TextView tv_shopcart_number;
        TextView tv_cailiao;
        private RelativeLayout rl_frame;
    }
}
