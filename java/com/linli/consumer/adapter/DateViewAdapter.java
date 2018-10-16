package com.linli.consumer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;

import java.util.List;

/**
 * Created by wwy on 2016/5/30.
 */
public class DateViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> mStringList;
    private LayoutInflater mLayoutInflater;

    public DateViewAdapter(Context context, List<Integer> stringList) {
        this.mContext = context;
        this.mStringList = stringList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringList.get(position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.order_date_center_item, parent,false);
            mViewHolder = new ViewHolder();
            mViewHolder.mTextViewDate = (TextView) convertView.findViewById(R.id.tv_date_item);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
//        if (mStringList == null) {
//            return null;
//        } else {
//            mViewHolder.mTextViewDate.setText(mStringList.get(position) + "");
//        }
        try{
            if(mStringList != null && mContext !=null){
                //if (mStringList.get(position) != 0){
                    mViewHolder.mTextViewDate.setText(mStringList.get(position) + "");
                //}else{
                    //mViewHolder.mTextViewDate.setText("");
               // }
            }else{
                return null;
            }
            return convertView;
        }catch (Exception e){
            return null;
        }

    }

    public class ViewHolder {
        TextView mTextViewDate;
    }
}
