package com.linli.consumer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.ui.main.AddFastTextActivity;
import com.linli.consumer.ui.main.V2VoicePhotoBuyActivity;

import java.util.List;

/**
 * Created by hasee on 2016/12/27.
 */
public class FastTextAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public FastTextAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.fast_text_item,null);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            holder.iv_edit = (ImageView) convertView.findViewById(R.id.iv_edit);
            holder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        holder.tv_text.setText(list.get(position));
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddFastTextActivity.class);
                intent.putExtra("updatetext",true);
                intent.putExtra("updatestr",list.get(position));
                ((V2VoicePhotoBuyActivity)context).startActivityForResult(intent,427);
            }
        });
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddFastTextActivity.class);
                intent.putExtra("deletestr",list.get(position));
                ((V2VoicePhotoBuyActivity)context).startActivityForResult(intent,427);
            }
        });
        return convertView;
    }
    public class HolderView{
        TextView tv_text;
        ImageView iv_edit;
        ImageView iv_delete;
    }
}
