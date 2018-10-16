package com.linli.consumer.widget.fooddetail_widget;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

/**
 * Created by tomoyo on 2017/2/8.
 */

public class CoverFlowAdapter extends BaseAdapter {

    private Context context;
    private List<FoodListBean.DataBean> list;
    private LayoutInflater inflater;

    public CoverFlowAdapter(Context context,List<FoodListBean.DataBean> list ){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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

        FoodListBean.DataBean bean = (FoodListBean.DataBean) getItem(position);
        ViewHolder viewHolder;
        View view ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.food_detail_cover_layout,parent,false);
            viewHolder.draweeView = (SimpleDraweeView)view.findViewById(R.id.my_image_view);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        if(bean.getImgpath() != null ){
            viewHolder.draweeView.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImgpath())));
        }
        return view;

    }

    private class ViewHolder{
        private SimpleDraweeView draweeView;
    }
}
