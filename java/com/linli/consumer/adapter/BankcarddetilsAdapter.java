package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.BankDetials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BankcarddetilsAdapter extends BaseAdapter {
    private List<BankDetials.DataBean> list;
    private Context context;


    public BankcarddetilsAdapter(List<BankDetials.DataBean> list, Context context) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.bankcarddetails_item, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Date dt = new Date(list.get(position).getCreateTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        holder.tv_time.setText(format.format(dt) + "");
        //账务类别：1用户消费，2用户充值，3用户转账，4商户消费，5商户充值，6商户转账，7运营商充值，8运营商消费，9运营商转账，10平台充值，11平台消费，12平台转账
        if (list != null && list.size() > 0) {
            switch (list.get(position).getType()) {
                case 1:  //1用户消费
                    holder.tv_name.setText("用户消费");
                    holder.tv_money.setText("- " + list.get(position).getPayAmount());
                    break;
                case 2:
                    holder.tv_name.setText("用户充值");
                    holder.tv_money.setText("+ " + list.get(position).getPayAmount());
                    break;
                case 3:
                    holder.tv_name.setText("用户提现");
                    holder.tv_money.setText("- " + list.get(position).getPayAmount());
                    break;
                case 4:
                    holder.tv_name.setText("商户消费");
                    holder.tv_money.setText("- " + list.get(position).getPayAmount());
                    break;
                case 5:
                    break;
                case 6: //6商户转账
                    holder.tv_name.setText("商户提现");
                    holder.tv_money.setText("- " + list.get(position).getPayAmount());
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    holder.tv_name.setText("用户红包消费");
                    holder.tv_money.setText(list.get(position).getPayAmount()+"");
                    break;
                case 14:
                    holder.tv_name.setText("用户退款");
                    holder.tv_money.setText("+ " + list.get(position).getPayAmount());
                    break;
                default:
                    holder.tv_name.setText(list.get(position).getDiscount());
                    holder.tv_money.setText(list.get(position).getPayAmount()+"");
                    break;
            }

        }
        return convertView;
    }

    public class ViewHolder {
        TextView tv_name;
        TextView tv_time;
        TextView tv_money;
    }
}
