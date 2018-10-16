package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;

import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.ui.shop_v2.FoodDetailActivity;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.fooddetail_widget.CoverFlowAdapter;
import com.linli.consumer.widget.fooddetail_widget.CoverFlowView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tomoyo on 08/07/16.
 *         美食 详情 的适配器
 */
public class FoodDetailAdapter2 extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titleList = new ArrayList<>();     //小title
    private List<Long> idList = new ArrayList<>();          //title的id



    private DBUtil dbUtil;
    private long storeId;
    private LayoutInflater inflater;
    private long foodId;        //美食id，用于定位
    //private Map<Long,TextView> mapTv = new HashMap<>();  //key:商品id  value:某个textView
    private Map<Integer,Map> bigMap = new HashMap<>();
    private LongSparseArray<FoodListBean.DataBean> mapBean = new LongSparseArray<>();   //key:商品id  value:某个bean


    public FoodDetailAdapter2(Context context, List<String> titleList, List<Long> idList, long storeId,long foodId) {
        this.context = context;
        this.titleList = titleList;
        this.idList = idList;
        dbUtil =  DBUtil.getInstance(context);
        this.storeId = storeId;
        inflater = LayoutInflater.from(context);
        this.foodId = foodId;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.food_detail_main_container2, container, false);
        initData(view, position);
        if (view != null) {
            view.setTag(String.valueOf(position));
            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    /**
     * 初始化
     * 包含视图初始化和数据初始化
     * 初始化根据出入的position
     */
    private void initView(View view, final List<FoodListBean.DataBean> list,int position) {


        int currentPosition = 0 ;
        //父布局
        for(int i = 0 ; i < list.size() ; i ++){        //如果有要定位到具体位置的美食，就具体定位，没有就定位第一个
            if(list.get(i).getId() == foodId){
                currentPosition = i;
                break;
            }
        }
        final LinearLayout containerAllLl = (LinearLayout) view.findViewById(R.id.food_detail_main_container_ll);
        final SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.food_detail_main_container_avatar_big_im);
        final TextView nameTv = (TextView) view.findViewById(R.id.food_detail_main_container_name_tv);
        final TextView mainInTv = (TextView) view.findViewById(R.id.food_detail_main_container_mainingredient_tv);
        final TextView subInTv = (TextView) view.findViewById(R.id.food_detail_main_container_subingredient_tv);
        final TextView priceTv = (TextView) view.findViewById(R.id.food_detail_main_container_price_tv);
        final ImageButton reduceIb = (ImageButton) view.findViewById(R.id.food_detail_main_container_reduce_ib);
        final ImageView moreIb = (ImageView) view.findViewById(R.id.food_detail_main_container_more_ib);
        final TextView numTv = (TextView) view.findViewById(R.id.food_detail_main_container_num_tv);

        draweeView.setImageURI(Uri.parse(CommonUtil.zoomPic(list.get(0).getImgpath())));
        numTv.setText(list.get(0).getNumber()+"");

        CoverFlowAdapter adapter = new CoverFlowAdapter(context,list);

        ((FoodDetailActivity) context).dismiss();
        final CoverFlowView coverFlowView = (CoverFlowView)view.findViewById(R.id.food_detail_main_container_coverflowview);
        coverFlowView.setAdapter(adapter);
        coverFlowView.setSelection(currentPosition);
        //coverFlowView.reset();
        final Map<Long,TextView> mapTv = new HashMap<>();           //声明map

        coverFlowView.setListener(new CoverFlowView.OnSelectListener() {
            @Override
            public void onSelect(final int position) {
                Log.i("WATER","selection = "+position);
                draweeView.setImageURI(CommonUtil.zoomPic(list.get(position).getImgpath()));
                nameTv.setText(list.get(position).getName());
                mainInTv.setText(list.get(position).getMaining()+"");
                subInTv.setText(list.get(position).getAccessories()+"");
                priceTv.setText("￥"+list.get(position).getPrice() + "");
                //numTv.setText(list.get(position).getNumber()+"");
                GoodsBean goodsBean = dbUtil.queryByGoodId(list.get(position).getId(),0);
                numTv.setText(goodsBean!=null?goodsBean.getNumber()+"":0+"");  //显示的数据从数据库拿
                reduceIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsBean goodsBean1 = dbUtil.queryByGoodId(list.get(position).getId(),0);
                        if(goodsBean1 != null){
                            if(goodsBean1.getNumber() > 0){
                                if(goodsBean1.getNumber() ==1){
                                    dbUtil.reduceGoodNum(goodsBean1.getGoodId(),0);
                                    list.get(position).setNumber(list.get(position).getNumber() - 1);
                                    numTv.setText(goodsBean1.getNumber()+"");
                                    upActivityView();
                                } else {
                                    dbUtil.reduceGoodNum(goodsBean1.getGoodId(),0);
                                    list.get(position).setNumber(list.get(position).getNumber() - 1);
                                    numTv.setText(goodsBean1.getNumber()+"");
                                    upActivityView();
                                }
                            }

                        } else {
                            numTv.setText("0");
                        }
                        //GoodsBean goodsBean = dbUtil.queryByGoodId(list.get(position).getId(),0);
                       /* if (list.get(position).getNumber() > 0) {
                            if ((list.get(position).getNumber() - 1) == 0) {
                                dbUtil.reduceGoodNum(list.get(position).getId(),0);
                                list.get(position).setNumber(list.get(position).getNumber() - 1);
                                numTv.setText(list.get(position).getNumber() + "");
                                //numTv.setText((goodsBean != null?goodsBean.getNumber()+"":"0"));
                                upActivityView();
                            } else {
                                dbUtil.reduceGoodNum(list.get(position).getId(),0);
                                list.get(position).setNumber(list.get(position).getNumber() - 1);
                                numTv.setText(list.get(position).getNumber() + "");
                                //numTv.setText((goodsBean != null?goodsBean.getNumber()+"":"0"));
                                upActivityView();
                            }
                        }*/
                    }
                });
                moreIb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbUtil.addGoodNum(dbUtil.convertFood(list.get(position)));
                        list.get(position).setNumber(list.get(position).getNumber() + 1);
                        GoodsBean goodsBean = dbUtil.queryByGoodId(list.get(position).getId(),0);
                        numTv.setText(goodsBean != null ? goodsBean.getNumber()+"": 0+ "");
                        upActivityView();
                    }
                });
                mapTv.clear();
                mapTv.put(list.size()>0?list.get(coverFlowView.getSelection()).getId():0,numTv);    //存放数据，每次变化时存放，调用后清除
                mapBean.put(list.size()>0?list.get(coverFlowView.getSelection()).getId():0
                        ,list.size()>0?list.get(coverFlowView.getSelection()):null);
            }
        });
        mapTv.put(list.size()>0?list.get(coverFlowView.getSelection()).getId():0,numTv);
        bigMap.put(position,mapTv);
    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     *
     * @param position 位置参数
     */
    private void initData(final View view, final int position) {
        //TODO 根据position获取key，再拼接参数

        final List<FoodListBean.DataBean> list = new ArrayList<>();
        FoodNet.foodListOfSHop(storeId, idList.get(position), 1, 1000, new HandleSuccess<FoodListBean>() {
            @Override
            public void success(FoodListBean foodListBean) {
                if(foodListBean.getData() != null &&foodListBean.getData().size() > 0){
                    dbUtil.compareDataToDB(foodListBean.getData(),idList.get(position),storeId,false);
                    for (int i = 0; i < foodListBean.getData().size(); i++) {
                        list.add(foodListBean.getData().get(i));
                    }
                    initView(view, list,position);
                }


            }
        });
    }

    /**
     * 更新activity的视图
     */
    private void upActivityView() {
        ((FoodDetailActivity) context).updateView();
    }


    public void upTextNu(){
        for(int i = 0 ; i < bigMap.size() ; i ++){
            Map<Long,TextView> map = bigMap.get(i);
            if(map != null ){
                for(Long id : map.keySet()){
                    GoodsBean goodsBean = dbUtil.queryByGoodId(id,0);
                    if(goodsBean != null){
                        map.get(id).setText(goodsBean.getNumber()+"");
                    } else {
                        map.get(id).setText("0");
                    }
                }
            }
        }
    }
}
