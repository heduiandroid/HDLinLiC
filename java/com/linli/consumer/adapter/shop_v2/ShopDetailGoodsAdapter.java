package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailSingleBean;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.shop_v2.ShopDetailActivity;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.CircleImageView;
import com.linli.consumer.widget.GoodsNumLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by Administrator on 2016/8/9.
 * 店铺商品适配器
 * 店铺的商品
 */
public class ShopDetailGoodsAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List data;

    static final int TYPE_NORMAL = 0;       //商店进入的布局
    static final int TYPE_DIALOG = 1;       //dialog进入的布局
    private boolean isDialog;               //是否是dialpg进入
    private int type;

    private String shopName;

    //暂时的bean存储，用于保存要移除的bean，在check取消后，数据才进行改变，否则立即改变会造成数据不一致
    //recyclerView会在重用视图时报错，因为没有数据了
    @Deprecated
    private List<NewsBean.ResultBean.DataBean> tempList = new ArrayList<>();

    private DBUtil dbUtil ;

    private long titleId;


    public ShopDetailGoodsAdapter(Context context, List list, boolean isDialog, int type, String shopName, long titleId) {
        this.context = context;
        this.data = list;
        this.isDialog = isDialog;
        dbUtil = DBUtil.getInstance(context);
        this.type = type;
        this.shopName = shopName;
        this.titleId = titleId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_NORMAL:
                view = LayoutInflater.from(context).inflate(R.layout.shop_detail_goods_item, parent, false);
                holder = new ShopViewHolder(view);
                break;
            case TYPE_DIALOG:
                view = LayoutInflater.from(context).inflate(R.layout.shop_cart_dialog_item,parent,false);
                holder = new DialogViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(type == SHOP_MAIN){
            if (holder instanceof ShopViewHolder) {

                final GoodsDetailBean bean = (GoodsDetailBean) data.get(position);
                if (bean.getIsRecommend() != null && bean.getIsRecommend() == 1){
                    ((ShopViewHolder) holder).nameTv.setText(bean.getName());
                    ((ShopViewHolder) holder).tv_activity_tag.setVisibility(View.VISIBLE);
                }else {
                    ((ShopViewHolder) holder).nameTv.setText(bean.getName());
                    ((ShopViewHolder) holder).tv_activity_tag.setVisibility(View.GONE);
                }
                if(bean.getMinprice() != null){
                    ((ShopViewHolder) holder).priceTv.setText("￥"+bean.getMinprice()+"");
                } else {
                    ((ShopViewHolder) holder).priceTv.setText("￥"+"--");
                }

                if(!TextUtils.isEmpty(bean.getPrimaryImage())){
                    ((ShopViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getPrimaryImage())));
                }
                ((ShopViewHolder) holder).avatarIm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getMinprice() != null){
                            UIHelper.togoGoodsDetailActivity(context,bean.getId(),shopName);
                        } else {
                            Toast.makeText(context,"请刷新界面重试",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
//                ((ShopViewHolder) holder).addContainerRl.setVisibility(View.GONE);
//                ((ShopViewHolder) holder).moreIb.setVisibility(View.GONE);
//                ((ShopViewHolder) holder).reduceIb.setVisibility(View.GONE);
                if(bean.getNumber() != 0){
                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                    ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                }else {
                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.GONE);
                }


                ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<GoodsDetailBean.GoodsSpecsBean> specsGood = bean.getGoodsSpecs();
                        if (specsGood != null && specsGood.size() == 1){//没有规格 直接添加数据
                            int numCar = 0;
                            if (dbUtil.queryByGoodId(bean.getId(),specsGood.get(0).getId()) != null){
                                numCar = dbUtil.queryByGoodId(bean.getId(),specsGood.get(0).getId()).getNumber();
                            }
                            if (numCar < specsGood.get(0).getInventory()) {
                                //先执行动画
                                int[] startLocation = new int[2];//一个整型数组，用来存储按钮在屏幕的xy坐标
                                ((ShopViewHolder) holder).moreIb.getLocationInWindow(startLocation);
                                CircleImageView ball = new CircleImageView(context);
                                Picasso.with(context)
                                        .load(Uri.parse(bean.getPrimaryImage() + Common.MORESMALLERPICPARAM))
                                        .placeholder(R.mipmap.ic_shop_detail_goods_more)
                                        .resize(55, 55)
                                        .centerCrop()
                                        .error(R.mipmap.ic_shop_detail_goods_more)
                                        .into(ball);
                                ((ShopDetailActivity) context).setAnim(ball, startLocation);
                                String specaName = "";
                                if (specsGood.get(0).getSpecaValue()!=null){
                                    specaName = specsGood.get(0).getSpecaValue();
                                }
                                dbUtil.addGoodNum(dbUtil.convertGoodForDetail(bean, specsGood.get(0).getId(), specaName,
                                        specsGood.get(0).getPlatformPrice(), specsGood.get(0).getInventory()), 1);
                                bean.setNumber(bean.getNumber()+1);
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                                ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                                upActivityView();
                            }else {
                                Toast.makeText(context,"库存不足",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }else if (specsGood != null && specsGood.size() > 1){//有规格弹出规格选择框
                            ShopNet.findGoodsById(bean.getId(), new HandleSuccess<GoodsDetailSingleBean>() {
                                @Override
                                public void success(GoodsDetailSingleBean s) {
                                    if (s.getData() != null && s.getData().getSpecNameValues() != null && s.getData().getSpecNameValues().size() > 0){
                                        UIHelper.togoGoodsSpecActivity(context,s.getData(),0);
                                    }
                                }
                            });
                        }else {
                            return;
                        }
                    }
                });
                ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            List<GoodsDetailBean.GoodsSpecsBean> specsGood = bean.getGoodsSpecs();
                            if (specsGood != null && specsGood.size() == 1) {//没有规格 直接减数据
                                dbUtil.reduceGoodNum(bean.getId(),specsGood.get(0).getId());
                                if((bean.getNumber() -1) == 0 ){
                                    bean.setNumber(bean.getNumber() -1);
                                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.GONE);
                                }else {
                                    bean.setNumber(bean.getNumber() -1);
                                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                                    ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                                }
                                upActivityView();
                            }else if (specsGood != null && specsGood.size() > 1) {//多规格弹出购物车就不用管了
                                ((ShopDetailActivity)context).showCart();
                                Toast.makeText(context,"多规格请在购物车操作",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
        } else if(type == FOOD_MAIN){       //type等于美食的时候，由于美食和商城的实体类不同，这里需要分开判断
                                            //造成这个方法里有300行代码，能优化，但是不想
            if (holder instanceof ShopViewHolder) {

                final FoodListBean.DataBean bean = (FoodListBean.DataBean) data.get(position);
                ((ShopViewHolder) holder).nameTv.setText(bean.getName());
                ((ShopViewHolder) holder).priceTv.setText("￥"+bean.getPrice()+"");
                if(!TextUtils.isEmpty(bean.getImgpath())){
                    ((ShopViewHolder) holder).avatarIm.setImageURI(CommonUtil.zoomPic(bean.getImgpath()));
                }
                ((ShopViewHolder) holder).avatarIm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.showThePicClearly(context,bean.getImgpath());
//                        UIHelper.togoFoodDetailActivity(context,bean.getBussId(),shopName,
//                                titleId !=0?titleId:bean.getBelongtype(),bean.getId());
                    }
                });
                if(bean.getNumber() != 0){
                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                    ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                }else {
                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.GONE);
                }
                ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //先执行动画
                        int[] startLocation = new int[2];//一个整型数组，用来存储按钮在屏幕的xy坐标
                        ((ShopViewHolder) holder).moreIb.getLocationInWindow(startLocation);
                        CircleImageView ball = new CircleImageView(context);
                        Picasso.with(context)
                                .load(Uri.parse(bean.getImgpath()+ Common.MORESMALLERPICPARAM))
                                .placeholder(R.mipmap.ic_shop_detail_goods_more)
                                .resize(55,55)
                                .centerCrop()
                                .error(R.mipmap.ic_shop_detail_goods_more)
                                .into(ball);
                        ((ShopDetailActivity)context).setAnim(ball,startLocation);
                        dbUtil.addGoodNum(dbUtil.convertFood(bean));
                        bean.setNumber(bean.getNumber()+1);
                        ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                        ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        upActivityView();
                    }
                });
                ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            if((bean.getNumber() -1) == 0 ){
                                dbUtil.reduceGoodNum(bean.getId(),0);
                                bean.setNumber(bean.getNumber() -1);
                                //handleData(bean,false);
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.GONE);
                                upActivityView();
                            }else {
                                dbUtil.reduceGoodNum(bean.getId(),0);
                                bean.setNumber(bean.getNumber() - 1);
                                //handleData(bean,true);
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                                ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                                upActivityView();
                            }
                        }
                    }
                });

            }
        }else if (type == SERVICE_MAIN){
            if (holder instanceof ShopViewHolder) {

                final ServiceGoodBean.DataBean bean = (ServiceGoodBean.DataBean) data.get(position);
                ((ShopViewHolder) holder).nameTv.setText(bean.getName());
                ((ShopViewHolder) holder).priceTv.setText("￥"+bean.getPrice()+"");
                if(!TextUtils.isEmpty(bean.getImg())){
                    ((ShopViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getImg())));
                }
                ((ShopViewHolder) holder).avatarIm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoServiceGoodDetail(context,bean);
                    }
                });
                ((ShopViewHolder) holder).moreIb.setVisibility(View.GONE);
                ((ShopViewHolder) holder).reduceIb.setVisibility(View.GONE);
            }
        }

    }


    /**
     * 更新activity的视图
     * */
    private void upActivityView(){
        ((ShopDetailActivity)context).updateView();
    }


    /**
     * 更新dialog的视图
     * */
    private void upDialogView(){
        ((ShopDetailActivity)context).dialogView();
    }



    //TODO summary: 更改布局要对数据源进行处理，再在adapter中维护一个list，使用contain方法判读数据变化
    //TODO summary: 不要使用方法内的变量进行数据改变
    //TODO summary: Data ==>> Adapter ==>> View 时刻注意适配器读数据一定是一个数据源，有数据源适配数据，尽量不要有方法域的变量


    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;

    }


    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }


    /**
     * 商品列表的item的viewHolder
     * */
    private class ShopViewHolder extends ViewHolder {

        SimpleDraweeView avatarIm;
        //ImageView avatarIm;
        TextView nameTv;
        TextView priceTv;
        LinearLayout reduceIb;
        LinearLayout moreIb;
        GoodsNumLayout goodsNumLayout;
        RelativeLayout addContainerRl;      //增加按钮减少按钮的 container
        TextView tv_activity_tag;

        private ShopViewHolder(View itemView) {
            super(itemView);
            avatarIm = (SimpleDraweeView) itemView.findViewById(R.id.shop_detail_goods_item_avatar_im);
            tv_activity_tag = (TextView) itemView.findViewById(R.id.tv_activity_tag);
            nameTv = (TextView) itemView.findViewById(R.id.shop_detail_goods_item_name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.shop_detail_goods_item_price_tv);
            reduceIb = (LinearLayout) itemView.findViewById(R.id.shop_detail_goods_item_reduce_ib);
            moreIb = (LinearLayout) itemView.findViewById(R.id.shop_detail_goods_item_more_ib);
            goodsNumLayout = (GoodsNumLayout) itemView.findViewById(R.id.shop_detail_goods_item_num_widget);
            addContainerRl = (RelativeLayout) itemView.findViewById(R.id.shop_detail_goods_item_add_container_rl);


        }
    }
    /**
     * dialog的列表的item的ViewHolder
     * */
    @Deprecated
    private class DialogViewHolder extends ViewHolder {

        CheckBox checkBox;
        SimpleDraweeView avatarIm;
        TextView nameTv;
        TextView priceTv;
        RelativeLayout reduceIb;
        RelativeLayout moreIb;
        TextView numTv;



        private DialogViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.shop_cart_dialog_item_cb);
            avatarIm = (SimpleDraweeView) itemView.findViewById(R.id.shop_cart_dialog_item_avatar_im);
            nameTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_price_tv);
            reduceIb = (RelativeLayout) itemView.findViewById(R.id.shop_cart_dialog_item_reduce_im);
            moreIb = (RelativeLayout) itemView.findViewById(R.id.shop_cart_dialog_item_more_im);
            numTv = (TextView) itemView.findViewById(R.id.shop_cart_dialog_item_num_tv);

        }
    }

    /**
     * 没有连接网络时的写法
     * 可用
     * */
    @Deprecated
    public void onBind2ViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof ShopViewHolder) {
            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean) data.get(position);
            //MallGoodsVo bean = (MallGoodsVo) data.get(position);
            bean.setPosition(position);
            //TODO 和activity的联动,需要将值传出以及将值写入
            ((ShopViewHolder) holder).nameTv.setText(bean.getName());
            ((ShopViewHolder) holder).priceTv.setText(bean.getPrice()+"");
            if(!TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                ((ShopViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getThumbnail_pic_s())));
            }
            if(bean.isShow()){
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
            }else {
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
            }
            ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setNumber(bean.getNumber()+1);
                    bean.setShow(true);
                    handleData(bean,true);
                    ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                    ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");

                }
            });
            ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.getNumber() > 0){
                        if((bean.getNumber() -1) == 0 ){
                            bean.setNumber(bean.getNumber() -1);
                            bean.setShow(false);
                            handleData(bean,false);
                            ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
                        }else {
                            bean.setNumber(bean.getNumber() - 1);
                            bean.setShow(true);
                            handleData(bean,true);
                            ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                            ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        }
                    }
                }
            });

        }else {     //这里是dialog的适配，两个适配器只有样式不一样

            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean) data.get(position);
            ((DialogViewHolder) holder).nameTv.setText("包子"+position);
            ((DialogViewHolder) holder).priceTv.setText("￥"+bean.getPrice());
            ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
            if(!TextUtils.isEmpty(bean.getThumbnail_pic_s())){
                ((DialogViewHolder) holder).avatarIm.setImageURI(Uri.parse(CommonUtil.zoomPic(bean.getThumbnail_pic_s())));
            }
            ((DialogViewHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bean.setShow(true);
                        if(bean.getNumber() == 0){
                            bean.setNumber(1);
                            ((DialogViewHolder) holder).numTv.setText(1+"");
                        }
                        handleData(bean,true);
                    }else {
                        bean.setShow(false);
                        bean.setNumber(0);
                        ((DialogViewHolder) holder).numTv.setText(0+"");
                        //此处未选中不清除总list的数据，任然保持，防止dialog中的list在复用视图的时候出现数据不一致的错误
                        //handData中通过 isShow 属性来进行数据的总计
                        //handleData(bean,false);
                        /*if(!tempList.contains(bean)){
                            tempList.add(bean);
                        }*/

                    }
                }
            });

            ((DialogViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setNumber(bean.getNumber()+1);
                    bean.setShow(true);
                    handleData(bean,true);
                    ((DialogViewHolder) holder).checkBox.setChecked(true);
                    ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");

                }
            });
            ((DialogViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.getNumber() > 0){
                        if((bean.getNumber() -1) == 0 ){
                            bean.setNumber(bean.getNumber() -1);
                            bean.setShow(false);
                            //handleData(bean,false);
                            //ShopDetailGoodsAdapter.this.notifyDataSetChanged();
                            ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                            ((DialogViewHolder) holder).checkBox.setChecked(false);
                          /*  if(!tempList.contains(bean)){
                                tempList.add(bean);
                            }*/
                        }else {
                            bean.setNumber(bean.getNumber() - 1);
                            bean.setShow(true);
                            handleData(bean,true);
                            ((DialogViewHolder) holder).numTv.setText(bean.getNumber()+"");
                        }
                    }
                }
            });
        }
    }


    /**
     * 之前的一个写法，在不同适配器里会有两个beanList,会造成数据混乱难调
     * */
    @Deprecated
    public void bonBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof ShopViewHolder) {


            final NewsBean.ResultBean.DataBean bean = (NewsBean.ResultBean.DataBean) data.get(position);
            ((ShopViewHolder) holder).nameTv.setText("包子"+position);
            ((ShopViewHolder) holder).priceTv.setText("￥"+bean.getPrice());
            if (TextUtils.isEmpty(bean.getThumbnail_pic_s())) {
                Picasso.with(context).load(R.mipmap.ic_launcher).fit().into(((ShopViewHolder) holder).avatarIm);
            } else {
                Picasso.with(context).load(bean.getThumbnail_pic_s()).fit()
                        .into(((ShopViewHolder) holder).avatarIm);
            }
            if(bean.getNumber() != 0){
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
            }else{
                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
            }
            if(tempList.contains(bean)){
                //((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setNumber(bean.getNumber()+1);
                        ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                        ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        if(!tempList.contains(bean)){
                            tempList.add(bean);
                        }
                        bean.setShow(true);
                        handleData(bean,true);
                        Log.i("WATER",bean.isShow()?"true":"false");
                        Log.i("WATER","包含 more");
                    }
                });
                ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            bean.setNumber(bean.getNumber()-1);
                            ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                            if(bean.getNumber() == 0){
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
                                if(tempList.contains(bean)){
                                    tempList.remove(bean);
                                    handleData(bean,false);
                                    Log.i("WATER","包含 reduce = 0");
                                }
                                bean.setShow(false);
                            }else {
                                handleData(bean,true);
                                Log.i("WATER","包含 reduce != 0");
                            }

                        }
                    }
                });
            }else {
                ((ShopViewHolder) holder).reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getNumber() > 0){
                            bean.setNumber(bean.getNumber() - 1);
                            ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                            ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                            if(bean.getNumber() == 0){
                                ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.INVISIBLE);
                                if(tempList.contains(bean)){
                                    tempList.remove(bean);
                                    handleData(bean,false);
                                    Log.i("WATER","不包含 reduce = 0");
                                }
                                bean.setShow(false);
                            }else {
                                handleData(bean,true);
                                Log.i("WATER","不包含 reduce != 0");
                            }

                        }
                    }
                });
                ((ShopViewHolder) holder).moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setNumber(bean.getNumber()+1);
                        ((ShopViewHolder) holder).goodsNumLayout.setVisibility(View.VISIBLE);
                        ((ShopViewHolder) holder).goodsNumLayout.setMyNum(bean.getNumber()+"");
                        bean.setShow(true);
                        if(!tempList.contains(bean)){
                            tempList.add(bean);
                        }
                        handleData(bean,true);
                        Log.i("WATER","不包含 more");
                    }
                });
            }
        }
    }

    /**
     * @param isAdd true 为添加,false 为删除
     * */
    @Deprecated
    private void handleData(NewsBean.ResultBean.DataBean bean, boolean  isAdd){
        ((ShopDetailActivity)context).handleData(bean,isAdd);
    }

}
