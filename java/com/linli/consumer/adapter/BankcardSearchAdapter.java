package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.bean.FindNoteBanks;

import java.util.List;

/**
 * 类说明：
 *
 * @author Liucd
 * @version 1.0
 * @date 2014-10-30
 */
public class BankcardSearchAdapter extends BaseAdapter {
    private Context mContext;
    private List<FindNoteBanks.DataBean> cityPoiList;

    public BankcardSearchAdapter(Context context, List<FindNoteBanks.DataBean> list) {
        this.mContext = context;
        this.cityPoiList = list;
    }

    @Override
    public int getCount() {
        if (cityPoiList != null) {
            return cityPoiList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (cityPoiList != null) {
            return cityPoiList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class CityPoiHolder {
        public TextView tvMLIPoiName, tvMLIPoiAddress;
    }

    private CityPoiHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new CityPoiHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(
                    R.layout.bank_card_poi_lv_item, null);
            holder.tvMLIPoiName = (TextView) convertView
                    .findViewById(R.id.tvMLIPoiName);
            convertView.setTag(holder);
        } else {
            holder = (CityPoiHolder) convertView.getTag();
        }
        holder.tvMLIPoiName.setText(cityPoiList.get(position).getBank());
        if (cityPoiList.get(position).getBank() == null) {
            holder.tvMLIPoiName.setText(cityPoiList.get(position).getName());
        }
        return convertView;
    }
}
