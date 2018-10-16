package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.adapter.HorizontalGoodsAdapter;
import com.linli.consumer.adapter.HorizontalGoodsAdapterSmall;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FoodRecommendBean;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.bean.ServicesBean;
import com.linli.consumer.bean.ShopVo;
import com.linli.consumer.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    private static final int TYPE_DATA = 0;
    private static final int TYPE_AD = 1;

    private int type;
    private HorizontalGoodsAdapter adapter;
    private HorizontalGoodsAdapterSmall adapterSmall;





    public ShopAdapter(Context context, List<Object> list,int type){
        this.context = context;
        this.data = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.shop_main_item,parent,false);
                holder = new ShopViewHolder(view);
                break;
            case TYPE_AD:
                view = LayoutInflater.from(context).inflate(R.layout.goods_main_item_hor,parent,false);
                holder = new GoodsViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof GoodsViewHolder){
            final ArrayList<MallGoodsVo> list = new ArrayList<>();
            Log.i("test",data.size()+"");
            for (int i = 0;i < data.size();i++){
                MallGoodsVo item = (MallGoodsVo)data.get(i);
                list.add(item);
            }
            if (list != null && list.size() > 0){
                ((GoodsViewHolder) holder).hsv_goods.setVisibility(View.VISIBLE);
                for (int i = 0;i<list.size();i++){
                    View view = LayoutInflater.from(context).inflate(R.layout.horizontal_good_cell,null,false);
                    SimpleDraweeView niv_cell_goodpic = (SimpleDraweeView) view.findViewById(R.id.niv_cell_goodpic);
                    TextView tv_good_name = (TextView) view.findViewById(R.id.tv_good_name);
                    TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
                    niv_cell_goodpic.setImageURI(CommonUtil.zoomPic(list.get(i).getMallGoods().getPrimaryImage()));
                    tv_good_name.setText(list.get(i).getMallGoods().getName()+"\n");
                    tv_price.setText("￥" + list.get(i).getMallGoods().getMinprice());
                    final int finalI = i;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            UIHelper.togoGoodsDetailActivity(context,list.get(finalI).getMallGoods().getId(),list.get(finalI).getStoreName());
                        }
                    });
                    ((GoodsViewHolder) holder).ll_goods_container.addView(view);
                }
//                adapter = new HorizontalGoodsAdapter(list,context);
//                ((GoodsViewHolder)holder).gv_goods.setAdapter(adapter);
            }else {
                ((GoodsViewHolder) holder).hsv_goods.setVisibility(View.GONE);
            }
//            ((GoodsViewHolder)holder).gv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//                    UIHelper.togoGoodsDetailActivity(context,list.get(pos).getMallGoods().getId(),list.get(pos).getStoreName());
//                }
//            });
        }else if(holder instanceof ShopViewHolder){
            if(type == SHOP_MAIN){
                final ShopVo bean = (ShopVo)data.get(position);

                ((ShopViewHolder)holder).shopName.setText(bean.getHdMallStore().getName());
                ((ShopViewHolder) holder).distance.setText(bean.getHdMallStore().getDistance()+"km");
                if(bean.getHdMallStore().getCreditLevel() != null){
                    ((ShopViewHolder) holder).ratingBar.setRating((float)(bean.getHdMallStore().getCreditLevel()));
                }
                ((ShopViewHolder) holder).like.setText(bean.getPraiseNum()+"");
                ((ShopViewHolder) holder).collect.setText(bean.getStoreUserNum()+"");


                if(TextUtils.isEmpty(bean.getHdMallStore().getLogoImg())){
                }else {
                    ((ShopViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(bean.getHdMallStore().getLogoImg()));
                }

                ((ShopViewHolder) holder).innerIm.setVisibility(View.GONE);
                ((ShopViewHolder) holder).outerIm.setVisibility(View.GONE);
                if(bean.getHdMallStoreoperate() != null){
                    ((ShopViewHolder) holder).lowestPrice.setText("￥"+bean.getHdMallStoreoperate().getBegingive());
                }
                if(bean.getHdMallStoreoperate() != null){
                    ((ShopViewHolder) holder).sendPrice.setText("￥"+bean.getHdMallStoreoperate().getSendcost());
                }
                ((ShopViewHolder) holder).perPrice.setVisibility(View.GONE);
                ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.GONE);
                if (bean.getHdMallStore().getIsactivity() != null){
                    switch (bean.getHdMallStore().getIsactivity()){
                        case 1:
                            ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.VISIBLE);
                            break;
                        case 0:
                            ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                }
                ((ShopViewHolder) holder).container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoShopDetailActivity(context,bean.getHdMallStore().getId(),bean.getHdMallStore().getName(),type);
                    }
                });

                if(bean.getVoList() != null && bean.getVoList().size() > 0){
                    ((ShopViewHolder) holder).hsv_goods.setVisibility(View.VISIBLE);
                    final List<MallGoodsVo> list = bean.getVoList();

                    for (int i = 0;i<list.size();i++){
                        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_good_small_cell,null,false);
                        SimpleDraweeView niv_cell_goodpic = (SimpleDraweeView) view.findViewById(R.id.niv_cell_goodpic);
                        TextView tv_good_name = (TextView) view.findViewById(R.id.tv_good_name);
                        TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
                        niv_cell_goodpic.setImageURI(CommonUtil.zoomPic(list.get(i).getMallGoods().getPrimaryImage()));
                        tv_good_name.setText(list.get(i).getMallGoods().getName()+"\n");
                        tv_price.setText("￥" + list.get(i).getMallGoods().getMinprice());
                        final int finalI = i;
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UIHelper.togoGoodsDetailActivity(context,list.get(finalI).getMallGoods().getId(),list.get(finalI).getStoreName());
                            }
                        });
                        ((ShopViewHolder) holder).ll_goods_container.addView(view);
                    }
                }else {
                    ((ShopViewHolder) holder).hsv_goods.setVisibility(View.GONE);
                }

            }else if (type == FOOD_MAIN){
                final FoodRecommendBean.DataBean bean = (FoodRecommendBean.DataBean)data.get(position);
                ((ShopViewHolder)holder).shopName.setText(bean.getHdFoodStore().getName());
                ((ShopViewHolder) holder).distance.setText(bean.getHdFoodStore().getDistance()+"km");
                ((ShopViewHolder) holder).ratingBar.setRating((float)(bean.getHdFoodStore().getServiceAttitude()));
                ((ShopViewHolder) holder).like.setText(bean.getSupportCount()+"");
                ((ShopViewHolder) holder).collect.setText(bean.getCollectCount()+"");
                if(TextUtils.isEmpty(bean.getHdFoodStore().getLogoImg())){
                }else {
                    ((ShopViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(bean.getHdFoodStore().getLogoImg()));
                }
                ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.GONE);
                if (bean.getHdFoodStore().getIsactivity() != null){
                    switch (bean.getHdFoodStore().getIsactivity()){
                        case 1:
                            ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.VISIBLE);
                            break;
                        case 0:
                            ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                }
                ((ShopViewHolder) holder).container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoShopDetailActivity(context,bean.getHdFoodStore().getId(),bean.getHdFoodStore().getName(),type);
                    }
                });
                if(bean.getHdFoodStoreoperate() !=null ){
                    if(bean.getHdFoodStoreoperate().getOpemethod() != null){
                        switch (bean.getHdFoodStoreoperate().getOpemethod()){
                            case "0":       //堂吃
                                ((ShopViewHolder) holder).outerIm.setVisibility(View.GONE);
                                ((ShopViewHolder) holder).perPrice.setText("￥"+bean.getHdFoodStoreoperate().getOpeavgcost()+"");
                                ((ShopViewHolder) holder).lowestPrice.setVisibility(View.GONE);
                                ((ShopViewHolder) holder).sendPrice.setVisibility(View.GONE);
                                break;
                            case "1":       //外卖
                                ((ShopViewHolder) holder).innerIm.setVisibility(View.GONE);
                                ((ShopViewHolder) holder).lowestPrice.setText("￥"+bean.getHdFoodStoreoperate().getOpesendstartmoney()+"");
                                ((ShopViewHolder) holder).sendPrice.setText("￥"+bean.getHdFoodStoreoperate().getOpesendcost()+"");
                                ((ShopViewHolder) holder).perPrice.setVisibility(View.GONE);
                                break;
                            case "2":       //都有
                                ((ShopViewHolder) holder).lowestPrice.setText("￥"+bean.getHdFoodStoreoperate().getOpesendstartmoney()+"");
                                ((ShopViewHolder) holder).sendPrice.setText("￥"+bean.getHdFoodStoreoperate().getOpesendcost()+"");
                                ((ShopViewHolder) holder).perPrice.setText("￥"+bean.getHdFoodStoreoperate().getOpeavgcost()+"");
                                break;
                        }
                    }

                }
                ((ShopViewHolder)holder).bottom_goods.setVisibility(View.VISIBLE);
                if(bean.getCookbookList() != null){
                    switch (bean.getCookbookList().size()){ //对美食 食品多少的判断，重复代码
                        case 1:
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(0).getImgpath()));
                            //((ShopViewHolder) holder).draweeView2.setVisibility(View.GONE);
                            //((ShopViewHolder) holder).draweeView3.setVisibility(View.GONE);
                            //((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView2.setAlpha(0);
                            ((ShopViewHolder) holder).draweeView3.setAlpha(0);
                            ((ShopViewHolder) holder).draweeView4.setAlpha(0);
                            ((ShopViewHolder) holder).priceTv1.setText("￥"+bean.getCookbookList().get(0).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv2.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                        case 2:
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(0).getImgpath()));
                            ((ShopViewHolder) holder).draweeView2.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(1).getImgpath()));
                            //((ShopViewHolder) holder).draweeView3.setVisibility(View.GONE);
                            //((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView3.setAlpha(0);
                            ((ShopViewHolder) holder).draweeView4.setAlpha(0);
                            ((ShopViewHolder) holder).priceTv1.setText("￥"+bean.getCookbookList().get(0).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv2.setText("￥"+bean.getCookbookList().get(1).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                        case 3:
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(0).getImgpath()));
                            ((ShopViewHolder) holder).draweeView2.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(1).getImgpath()));
                            ((ShopViewHolder) holder).draweeView3.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(2).getImgpath()));
                            //((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView4.setAlpha(0);
                            ((ShopViewHolder) holder).priceTv1.setText("￥"+bean.getCookbookList().get(0).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv2.setText("￥"+bean.getCookbookList().get(1).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv3.setText("￥"+bean.getCookbookList().get(2).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                        case 4:
                            //todo 送餐费
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(0).getImgpath()));
                            ((ShopViewHolder) holder).draweeView2.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(1).getImgpath()));
                            ((ShopViewHolder) holder).draweeView3.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(2).getImgpath()));
                            ((ShopViewHolder) holder).draweeView4.setImageURI(CommonUtil.zoomPic(bean.getCookbookList().get(3).getImgpath()));
                            ((ShopViewHolder) holder).priceTv1.setText("￥"+bean.getCookbookList().get(0).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv2.setText("￥"+bean.getCookbookList().get(1).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv3.setText("￥"+bean.getCookbookList().get(2).getPrice()+"");
                            ((ShopViewHolder) holder).priceTv4.setText("￥"+bean.getCookbookList().get(3).getPrice()+"");
                            break;
                        case 0:
                            ((ShopViewHolder) holder).draweeView1.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView2.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv1.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv2.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;


                    }
                }
            }else if (type == SERVICE_MAIN){
                final ServicesBean.DataBean bean = (ServicesBean.DataBean)data.get(position);
                ((ShopViewHolder)holder).shopName.setText(bean.getHdServeStore().getName());
                ((ShopViewHolder) holder).distance.setText(bean.getHdServeStore().getDistance()+"km");
                ((ShopViewHolder) holder).ratingBar.setRating(5f);
                ((ShopViewHolder) holder).like.setText(bean.getPraiseNum()+"");
                ((ShopViewHolder) holder).collect.setText(bean.getStoreUserNum()+"");


                if(TextUtils.isEmpty(bean.getHdServeStore().getLogoImg())){
                }else {
                    ((ShopViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(bean.getHdServeStore().getLogoImg()));
                }

                ((ShopViewHolder) holder).innerIm.setVisibility(View.GONE);
                ((ShopViewHolder) holder).outerIm.setVisibility(View.GONE);
                ((ShopViewHolder) holder).lowestPrice.setVisibility(View.GONE);
                ((ShopViewHolder) holder).sendPrice.setVisibility(View.GONE);
                ((ShopViewHolder) holder).perPrice.setVisibility(View.GONE);
                ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.GONE);
                ((ShopViewHolder) holder).iv_egg_isacitivty.setVisibility(View.GONE);
                ((ShopViewHolder) holder).container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoShopDetailActivity(context,bean.getHdServeStore().getId(),bean.getHdServeStore().getName(),type);
                    }
                });
                ((ShopViewHolder) holder).ll_begin_distribution.setVisibility(View.GONE);
                ((ShopViewHolder)holder).bottom_goods.setVisibility(View.VISIBLE);
                if(bean.getGoodsList() != null){
                    switch (bean.getGoodsList().size()) { //对美食 食品多少的判断，重复代码
                        case 1:
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(0).getImg()));
                            //((ShopViewHolder) holder).draweeView2.setVisibility(View.GONE);
                            //((ShopViewHolder) holder).draweeView3.setVisibility(View.GONE);
                            //((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView2.setAlpha(0);
                            ((ShopViewHolder) holder).draweeView3.setAlpha(0);
                            ((ShopViewHolder) holder).draweeView4.setAlpha(0);
                            ((ShopViewHolder) holder).priceTv1.setText("￥" + bean.getGoodsList().get(0).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv2.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                        case 2:
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(0).getImg()));
                            ((ShopViewHolder) holder).draweeView2.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(1).getImg()));
                            //((ShopViewHolder) holder).draweeView3.setVisibility(View.GONE);
                            //((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView3.setAlpha(0);
                            ((ShopViewHolder) holder).draweeView4.setAlpha(0);
                            ((ShopViewHolder) holder).priceTv1.setText("￥" + bean.getGoodsList().get(0).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv2.setText("￥" + bean.getGoodsList().get(1).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                        case 3:
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(0).getImg()));
                            ((ShopViewHolder) holder).draweeView2.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(1).getImg()));
                            ((ShopViewHolder) holder).draweeView3.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(2).getImg()));
                            //((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView4.setAlpha(0);
                            ((ShopViewHolder) holder).priceTv1.setText("￥" + bean.getGoodsList().get(0).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv2.setText("￥" + bean.getGoodsList().get(1).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv3.setText("￥" + bean.getGoodsList().get(2).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                        case 4:
                            //todo 送餐费
                            ((ShopViewHolder) holder).draweeView1.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(0).getImg()));
                            ((ShopViewHolder) holder).draweeView2.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(1).getImg()));
                            ((ShopViewHolder) holder).draweeView3.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(2).getImg()));
                            ((ShopViewHolder) holder).draweeView4.setImageURI(CommonUtil.zoomPic(bean.getGoodsList().get(3).getImg()));
                            ((ShopViewHolder) holder).priceTv1.setText("￥" + bean.getGoodsList().get(0).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv2.setText("￥" + bean.getGoodsList().get(1).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv3.setText("￥" + bean.getGoodsList().get(2).getPrice() + "");
                            ((ShopViewHolder) holder).priceTv4.setText("￥" + bean.getGoodsList().get(3).getPrice() + "");
                            break;
                        case 0:
                            ((ShopViewHolder) holder).draweeView1.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView2.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).draweeView4.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv1.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv2.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv3.setVisibility(View.GONE);
                            ((ShopViewHolder) holder).priceTv4.setVisibility(View.GONE);
                            break;
                    }

                    }
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof MallGoodsVo ){
            return TYPE_AD;
        } else {
            return TYPE_DATA;
        }
    }



    @Override
    public int getItemCount() {
        if (data != null&& data.size() > 0) {
            if (data.get(0) instanceof  MallGoodsVo){
                return 1;
            }
            return data.size();
        } else {
            return 0;
        }
    }

    class GoodsViewHolder extends ViewHolder{
        HorizontalScrollView hsv_goods;
        LinearLayout ll_goods_container;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            ll_goods_container = (LinearLayout) itemView.findViewById(R.id.ll_goods_container);
            hsv_goods = (HorizontalScrollView) itemView.findViewById(R.id.hsv_goods);
        }
    }

    class ShopViewHolder extends ViewHolder {

        SimpleDraweeView avatarIm;
        TextView shopName;
        TextView distance;
        TextView like;
        TextView collect;
        MaterialRatingBar ratingBar;

        SimpleDraweeView draweeView1;
        SimpleDraweeView draweeView2;
        SimpleDraweeView draweeView3;
        SimpleDraweeView draweeView4;
        TextView priceTv1;
        TextView priceTv2;
        TextView priceTv3;
        TextView priceTv4;
        private ImageView iv_egg_isacitivty;
        private ImageView innerIm;
        private ImageView outerIm;
        private TextView lowestPrice;
        private TextView sendPrice;
        private TextView perPrice;

        LinearLayout container,ll_begin_distribution;
        View bottom_goods;
        HorizontalScrollView hsv_goods;
        LinearLayout ll_goods_container;


        public ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (SimpleDraweeView)itemView.findViewById(R.id.shop_main_item_avatar_cim);
            shopName = (TextView)itemView.findViewById(R.id.shop_main_item_name_tv);
            distance = (TextView)itemView.findViewById(R.id.shop_main_item_distance_tv);
            like = (TextView)itemView.findViewById(R.id.shop_main_item_like_tv);
            collect = (TextView)itemView.findViewById(R.id.shop_main_item_collect_tv);
            ratingBar = (MaterialRatingBar)itemView.findViewById(R.id.shop_main_item_rb);

            innerIm = (ImageView)itemView.findViewById(R.id.shop_main_item_inner_im);
            iv_egg_isacitivty = (ImageView)itemView.findViewById(R.id.iv_egg_isacitivty);
            outerIm = (ImageView)itemView.findViewById(R.id.shop_main_item_outer_im);
            lowestPrice = (TextView)itemView.findViewById(R.id.shop_main_item_lowestprice_tv);
            sendPrice = (TextView)itemView.findViewById(R.id.shop_main_item_sendprice_tv);
            perPrice = (TextView)itemView.findViewById(R.id.shop_main_item_perprice_tv);
            bottom_goods = itemView.findViewById(R.id.bottom_goods);
            draweeView1 = (SimpleDraweeView)itemView.findViewById(R.id.shop_main_item_small_im1);
            draweeView2 = (SimpleDraweeView)itemView.findViewById(R.id.shop_main_item_small_im2);
            draweeView3 = (SimpleDraweeView)itemView.findViewById(R.id.shop_main_item_small_im3);
            draweeView4 = (SimpleDraweeView)itemView.findViewById(R.id.shop_main_item_small_im4);
            priceTv1 = (TextView)itemView.findViewById(R.id.shop_main_item_small_tv1);
            priceTv2 = (TextView)itemView.findViewById(R.id.shop_main_item_small_tv2);
            priceTv3 = (TextView)itemView.findViewById(R.id.shop_main_item_small_tv3);
            priceTv4 = (TextView)itemView.findViewById(R.id.shop_main_item_small_tv4);

            container = (LinearLayout)itemView.findViewById(R.id.shop_main_item_toshop_ll);
            ll_begin_distribution = (LinearLayout) itemView.findViewById(R.id.ll_begin_distribution);
            ll_goods_container = (LinearLayout) itemView.findViewById(R.id.ll_goods_container);
            hsv_goods = (HorizontalScrollView) itemView.findViewById(R.id.hsv_goods);
        }
    }



}
