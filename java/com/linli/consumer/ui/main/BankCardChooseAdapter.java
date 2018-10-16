package com.linli.consumer.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.FindBanks;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public class BankCardChooseAdapter extends BaseAdapter {
    private List<FindBanks.DataBean> list;
    private Context context;

    public BankCardChooseAdapter(List<FindBanks.DataBean> list, Context context) {
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
        if (convertView == null) {
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.banksaccount_item, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        String weihao = list.get(position).getNumber().substring(list.get(position).getNumber().length() - 4, list.get(position).getNumber().length());
        holder.tv_name.setText(list.get(position).getBank() + "(" + weihao + ")");
        return convertView;
    }

    public class HolderView {
        TextView tv_name;
    }
}
