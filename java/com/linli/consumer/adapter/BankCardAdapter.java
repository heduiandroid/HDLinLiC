package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.bean.Addbank;
import com.linli.consumer.bean.FindBanks;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BankCardAdapter extends BaseAdapter {
    private List<FindBanks.DataBean> list;
    private Context context;


    public BankCardAdapter(List<FindBanks.DataBean> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.bank_card_view, null);
            holder.tv_bank_name = (TextView) convertView.findViewById(R.id.tv_bank_name);
            holder.tv_bank_number = (TextView) convertView.findViewById(R.id.tv_bank_number);
            holder.bt_bank_de = (TextView) convertView.findViewById(R.id.bt_bank_de);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_bank_name.setText(list.get(position).getBank() + "");
        int cardnum = list.get(position).getNumber().length();
        String WeiHao = list.get(position).getNumber().substring(list.get(position).getNumber().length() - 4, list.get(position).getNumber().length());
        switch (cardnum) {
            case 16:
                holder.tv_bank_number.setText("****  ****  ****  " + WeiHao);
                break;
            case 17:
                holder.tv_bank_number.setText("****  ****  ****  *  " + WeiHao);
                break;
            case 18:
                holder.tv_bank_number.setText("****  ****  ****  **  " + WeiHao);
                break;
            case 19:
                holder.tv_bank_number.setText("****  ****  ****  ***  " + WeiHao);
                break;
            default:
                holder.tv_bank_number.setText("****  ****  ****  ****");
                break;
        }
        holder.bt_bank_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntrestBuyNet.delBank(list.get(position).getId(), new HandleSuccess<Addbank>() {
                    @Override
                    public void success(Addbank addbank) {
                        if (addbank.getStatus() == 1) {
                            list.remove(position);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "" + addbank.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return convertView;
    }

    public class ViewHolder {
        private TextView tv_bank_name;
        private TextView tv_bank_number;
        private TextView bt_bank_de;

    }
}
