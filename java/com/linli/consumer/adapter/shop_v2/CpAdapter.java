package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.linli.consumer.R;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.coverflowview.ACoverFlowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoyo on 2016/12/26.
 */

public class CpAdapter extends ACoverFlowAdapter<ACoverFlowAdapter.ViewHolder> {

    private List<FoodListBean.DataBean> list = new ArrayList<>();
    private Context context ;


    public CpAdapter(Context context){
        this.context = context;
        for(int i = 0; i<7 ;i++){
            list.add(new FoodListBean.DataBean());
        };
    }


    @Override
    public int getCount() {
        //TODO 这里放在外面
        return list.size()%2 == 0 ? list.size()-1 : list.size() ;
    }

    public void setData(List<FoodListBean.DataBean> list ){
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        if(type == 1){
            View view = LayoutInflater.from(context).inflate(R.layout.shop_goods_detail_vp_image_widget,parent,false);
            return new CpViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(viewHolder instanceof CpViewHolder ){
            if(TextUtils.isEmpty(list.get(position).getImgpath())){
                ((CpViewHolder) viewHolder).draweeView.setVisibility(View.GONE);
            }else {
                if(!list.get(position).getImgpath().equals("hd")){
                    Uri imageUri = Uri.parse(CommonUtil.zoomPic(list.get(position).getImgpath()));
                    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                            .setResizeOptions(new ResizeOptions(dip2px(context, 40), dip2px(context, 40)))
                            .build();
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setImageRequest(request)
                            .setOldController(((CpViewHolder) viewHolder).draweeView.getController())
                            .setControllerListener(new BaseControllerListener<ImageInfo>())
                            .build();
                    ((CpViewHolder) viewHolder).draweeView.setController(controller);
                    ((CpViewHolder) viewHolder).draweeView.setVisibility(View.VISIBLE);
                    //((CpViewHolder) viewHolder).draweeView.setImageURI(list.get(position).getThumbnail_pic_s());
                }else {
                    ((CpViewHolder) viewHolder).draweeView.setImageResource(R.mipmap.ic_food_detail_fill);
                }

            }

        }

    }
    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    private class CpViewHolder extends ACoverFlowAdapter.ViewHolder{

        private SimpleDraweeView draweeView;

        public CpViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView)itemView.findViewById(R.id.shop_goods_detail_vp_image_im);
        }
    }

    private  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int value=(int) (dpValue * scale + 0.5f);
        return value;
    }
}
