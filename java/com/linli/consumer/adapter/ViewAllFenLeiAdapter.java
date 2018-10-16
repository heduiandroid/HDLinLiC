package com.linli.consumer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.ViewAllFenLei;

import java.util.List;

/**
 * Created by wwy on 2016/5/26.
 * 全部分类的adapter
 */
public class ViewAllFenLeiAdapter extends BaseAdapter {
    private Context mContext;
    private List<ViewAllFenLei> mViewAllFenLeis;
    private int flag = 0;

    public ViewAllFenLeiAdapter(Context context, List<ViewAllFenLei> viewAllFenLeis) {
        mContext = context;
        mViewAllFenLeis = viewAllFenLeis;
    }


    @Override
    public int getCount() {
        return mViewAllFenLeis.size();
    }

    @Override
    public Object getItem(int i) {
        return mViewAllFenLeis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.order_viewall_sort_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mTextViewTypes = (TextView) view.findViewById(R.id.tv_order_viewall_types);
            mViewHolder.mTextViewNum = (TextView) view.findViewById(R.id.tv_order_viewall_num);
            if (i == 0) {
                view.setBackgroundColor(Color.WHITE);
            } else {
                view.setBackgroundColor(Color.rgb(238, 238, 238));
            }
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        if (i == flag) {
            view.setBackgroundColor(Color.WHITE);
        } else {

            view.setBackgroundColor(Color.rgb(238, 238, 238));


        }

        if (mViewAllFenLeis == null) {
            return null;
        } else {
            if (mViewAllFenLeis.get(i).getType() != null) {
                mViewHolder.mTextViewTypes.setText(mViewAllFenLeis.get(i).getType());
            }
            if (mViewHolder.mTextViewNum != null) {
                mViewHolder.mTextViewNum.setText(mViewAllFenLeis.get(i).getNumber());
            }

            return view;
        }


    }

    /*
    从点击事件中获得点击的位置
     */
    public void setPosition(int position) {
        this.flag = position;
    }


    public class ViewHolder {
        TextView mTextViewTypes;
        TextView mTextViewNum;


    }


}
