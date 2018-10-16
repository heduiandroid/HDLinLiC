package com.linli.consumer.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Product;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.ui.main.CollectionsActivity;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/13.
 */
public class CollectionProAdapter extends BaseAdapter {
    private ArrayList<Product> list;
    private Context context;
    public CollectionProAdapter(ArrayList<Product> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_pro_item,null);
            holder.niv_pro_pic = (SimpleDraweeView) convertView.findViewById(R.id.niv_pro_pic);
            holder.tv_pro_name = (TextView) convertView.findViewById(R.id.tv_pro_name);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_collect_cancel = (TextView) convertView.findViewById(R.id.tv_collect_cancel);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_pro_name.setText(list.get(position).getName());
        holder.tv_price.setTextColor(context.getResources().getColor(R.color.red));
        if (list.get(position).getPrice() != null){
            holder.tv_price.setText("￥"+list.get(position).getPrice());
        }else if (list.get(position).getDetailsText() != null){
            holder.tv_price.setTextColor(context.getResources().getColor(R.color.gray));
            holder.tv_price.setText(list.get(position).getDetailsText());
        }else {//没有价格就显示创建时间/关键词
            holder.tv_price.setTextColor(context.getResources().getColor(R.color.gray));
            holder.tv_price.setText("收藏时间："+list.get(position).getCreateTime());
        }

        holder.niv_pro_pic.setImageURI(list.get(position).getPicPath());
        holder.tv_collect_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                builder1.setTitle("提示");
                builder1.setMessage("确认删除吗？");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       request_delete_procollection(position);
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder1.create();
                builder1.show();
            }
        });
        return convertView;
    }
    public void request_delete_procollection(final int position) {
        IntrestBuyNet.collGoodDel(list.get(position).getCollectId(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    list.remove(position);
                    CollectionsActivity.proAdapter.notifyDataSetChanged();
                    Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                }else if (s.getStatus() == 2){
                    Toast.makeText(context,"太快了，小令还没反应过来呢~",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public class HolderView{
        SimpleDraweeView niv_pro_pic;
        TextView tv_pro_name;
        TextView tv_price;
        TextView tv_collect_cancel;
    }
}
