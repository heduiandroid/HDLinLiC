package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;

import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.ui.shop_v2.FoodDetailActivity;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.coverflowview.CoverFlowView;


import java.util.ArrayList;
import java.util.List;

/**
 * @author tomoyo on 08/07/16.
 *         美食 详情 的适配器
 */
public class FoodDetailAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titleList = new ArrayList<>();     //小title
    private List<Long> idList = new ArrayList<>();          //title的id

    private SparseArray<CpAdapter> adapterMap = new SparseArray<>();
    private SparseArray<List<FoodListBean.DataBean>> listMap = new SparseArray<>();
    private SparseArray<CoverFlowView> coverMap = new SparseArray<>();
    private SparseArray<SimpleDraweeView> singleMap = new SparseArray<>();

    private DBUtil dbUtil;
    private long storeId;
    private LayoutInflater inflater;


    public FoodDetailAdapter(Context context, List<String> titleList, List<Long> idList, long storeId) {
        this.context = context;
        this.titleList = titleList;
        this.idList = idList;
        dbUtil = DBUtil.getInstance(context);
        this.storeId = storeId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.food_detail_main_container, container, false);
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
    private void initView(View view, final int position, final List<FoodListBean.DataBean> list) {

        //final List<FoodListBean.DataBean> dataList = new ArrayList<>();
        //listMap.put(position,dataList);


        //父布局
        final LinearLayout containerAllLl = (LinearLayout) view.findViewById(R.id.food_detail_main_container_ll);
        final SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.food_detail_main_container_avatar_big_im);
        final TextView nameTv = (TextView) view.findViewById(R.id.food_detail_main_container_name_tv);
        final TextView mainInTv = (TextView) view.findViewById(R.id.food_detail_main_container_mainingredient_tv);
        final TextView subInTv = (TextView) view.findViewById(R.id.food_detail_main_container_subingredient_tv);
        final TextView priceTv = (TextView) view.findViewById(R.id.food_detail_main_container_price_tv);
        final ImageButton reduceIb = (ImageButton) view.findViewById(R.id.food_detail_main_container_reduce_ib);
        final ImageButton moreIb = (ImageButton) view.findViewById(R.id.food_detail_main_container_more_ib);
        final TextView numTv = (TextView) view.findViewById(R.id.food_detail_main_container_num_tv);

        //CoverFlowView coverFlowView = (CoverFlowView)view.findViewById(R.id.food_detail_main_fc);
        //coverFlowView.setViImage(3);
        CpAdapter cpAdapter = new CpAdapter(context);
        cpAdapter.setData(list);

        SimpleDraweeView singleView = (SimpleDraweeView) view.findViewById(R.id.food_detail_main_single_im);// 图片为1时显示这个
        //CoverFlowView coverFlowView = initCoverView(list, position, cpAdapter, singleView, view);

        CoverFlowView coverFlowView = null;
        if (list.size() == 0) {                //没有数据的情况
            //coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
            //coverFlowView.setVisibility(View.GONE);
            singleView.setVisibility(View.VISIBLE);
            singleView.setImageResource(R.mipmap.ic_food_detail_fill);
        } else if (list.size() == 1) {       //等于1的情况
            //coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
            //coverFlowView.setVisibility(View.GONE);
            singleView.setVisibility(View.VISIBLE);
            singleView.setImageURI(Uri.parse(CommonUtil.zoomPic(list.get(0).getImgpath())));

            draweeView.setImageURI(CommonUtil.zoomPic(list.get(0).getImgpath()));
            nameTv.setText(list.get(0).getName());
            mainInTv.setText(list.get(0).getMaining()+"");
            subInTv.setText(list.get(0).getAccessories()+"");
            priceTv.setText(list.get(0).getPrice() + "");
            numTv.setText(list.get(0).getNumber()+"");
            reduceIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(0).getNumber() > 0) {
                        if ((list.get(0).getNumber() - 1) == 0) {
                            dbUtil.reduceGoodNum(list.get(0).getId(),0);
                            list.get(0).setNumber(list.get(0).getNumber() - 1);
                            numTv.setText(list.get(0).getNumber() + "");
                            upActivityView();
                        } else {
                            dbUtil.reduceGoodNum(list.get(0).getId(),0);
                            list.get(0).setNumber(list.get(0).getNumber() - 1);
                            numTv.setText(list.get(0).getNumber() + "");
                            upActivityView();
                        }
                    }

                }
            });
            moreIb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbUtil.addGoodNum(dbUtil.convertFood(list.get(0)));
                    list.get(0).setNumber(list.get(0).getNumber() + 1);
                    numTv.setText(list.get(0).getNumber() + "");
                    upActivityView();
                }
            });
        } else {                        //数据大于等于2的情况
            if (list.size() % 2 == 0) {
                FoodListBean.DataBean dataBean = new FoodListBean.DataBean();
                dataBean.setImgpath("hd");
                list.add(list.size() - 1, dataBean);
            }
            if (list.size() >= 3 && list.size() < 5) {       //3,5之间，设置可显示数量为3
                //小布局的父布局
                LinearLayout containerSmallLl = (LinearLayout) inflater.inflate(R.layout.food_detail_main_coverflow_3,null);
                coverFlowView = (CoverFlowView)containerSmallLl.findViewById(R.id.food_detail_main_fc);
                //coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
                //coverFlowView.setViImage(3);
                coverFlowView.setAdapter(cpAdapter);
                containerAllLl.addView(containerSmallLl,3);
            } else if (list.size() >= 5 && list.size() < 7) {    //5,7之间，设置可显示数量为5
                LinearLayout containerSmallLl = (LinearLayout) inflater.inflate(R.layout.food_detail_main_coverflow_5,null);
                coverFlowView = (CoverFlowView)containerSmallLl.findViewById(R.id.food_detail_main_fc);
                //coverFlowView.setViImage(5);
                coverFlowView.setAdapter(cpAdapter);
                containerAllLl.addView(containerSmallLl,3);
            } else if (list.size() >= 7) {                    //7以上，设置可显示数量为7
                LinearLayout containerSmallLl = (LinearLayout) inflater.inflate(R.layout.food_detail_main_coverflow_7,null);
                coverFlowView = (CoverFlowView)containerSmallLl.findViewById(R.id.food_detail_main_fc);
                //coverFlowView.setViImage(7);
                coverFlowView.setAdapter(cpAdapter);
                containerAllLl.addView(containerSmallLl,3);
            }
            cpAdapter.notifyDataSetChanged();
            ((FoodDetailActivity) context).dismiss();
        }
        if(coverFlowView != null){
            coverFlowView.setClick2SwitchEnabled(true);
            coverFlowView.setOnViewOnTopListener(new CoverFlowView.OnViewOnTopListener() {
                @Override
                public void viewOnTop(final int position, View itemView) {
                    draweeView.setImageURI(CommonUtil.zoomPic(list.get(position).getImgpath()));
                    nameTv.setText(list.get(position).getName());
                    mainInTv.setText(list.get(position).getMaining()+"");
                    subInTv.setText(list.get(position).getAccessories()+"");
                    priceTv.setText(list.get(position).getPrice() + "");
                    numTv.setText(list.get(position).getNumber());
                    reduceIb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (list.get(position).getNumber() > 0) {
                                if ((list.get(position).getNumber() - 1) == 0) {
                                    dbUtil.reduceGoodNum(list.get(position).getId(),0);
                                    list.get(position).setNumber(list.get(position).getNumber() - 1);
                                    numTv.setText(list.get(position).getNumber() + "");
                                    upActivityView();
                                } else {
                                    dbUtil.reduceGoodNum(list.get(position).getId(),0);
                                    list.get(position).setNumber(list.get(position).getNumber() - 1);
                                    numTv.setText(list.get(position).getNumber() + "");
                                    upActivityView();
                                }
                            }

                        }
                    });
                    moreIb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dbUtil.addGoodNum(dbUtil.convertFood(list.get(position)));
                            list.get(position).setNumber(list.get(position).getNumber() + 1);
                            numTv.setText(list.get(position).getNumber() + "");
                            upActivityView();
                        }
                    });

                }
            });
        }

        //singleMap.put(position,singleView);     //当数据为一条时，填充一条，就这一条，平时为隐藏状态
        //coverMap.put(position,coverFlowView);
        //adapterMap.put(position,cpAdapter);


        //initData(view, position);
    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     *
     * @param position 位置参数
     */
    private void initData(final View view, final int position) {
        //TODO 根据position获取key，再拼接参数
        /*MockNet.MockFoodData(new HandleSuccess<NewsBean>() {
            @Override
            public void success(NewsBean newsBean) {
                for(int i = 0;i<newsBean.getResult().getData().size();i++){
                }
                initCoverView(listMap.get(position),position);
                adapterMap.get(position).setData(listMap.get(position));
                adapterMap.get(position).notifyDataSetChanged();
                ((FoodDetailActivity) context).dismiss();

            }
        }, titleList.get(position));*/
        final List<FoodListBean.DataBean> list = new ArrayList<>();
        FoodNet.foodListOfSHop(storeId, idList.get(position), 1, 1000, new HandleSuccess<FoodListBean>() {
            @Override
            public void success(FoodListBean foodListBean) {
                if(foodListBean.getData() != null && foodListBean.getData().size() > 0 ){
                    dbUtil.compareDataToDB(foodListBean.getData(),idList.get(position),storeId,false);
                    for (int i = 0; i < foodListBean.getData().size(); i++) {
                        //listMap.get(position).add(foodListBean.getData().get(i));
                        list.add(foodListBean.getData().get(i));
                    }
                    //listMap.put(position,list);
                    initView(view, position, list);
                    //initCoverView(listMap.get(position),position);
                    //adapterMap.get(position).setData(listMap.get(position));
                    //adapterMap.get(position).notifyDataSetChanged();
                    //((FoodDetailActivity) context).dismiss();
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


    private CoverFlowView initCoverView(List<FoodListBean.DataBean> list, int position, CpAdapter cpAdapter,
                                        SimpleDraweeView draweeView, View view) {

        CoverFlowView coverFlowView = null;
        if (list.size() == 0) {                //没有数据的情况
            coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
            coverFlowView.setVisibility(View.GONE);
            draweeView.setImageResource(R.mipmap.ic_food_detail_fill);
        } else if (list.size() == 1) {       //等于1的情况
            coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
            coverFlowView.setVisibility(View.GONE);
            draweeView.setImageURI(CommonUtil.zoomPic(list.get(0).getImgpath()));
        } else {                        //数据大于等于2的情况
            if (list.size() % 2 == 0) {
                FoodListBean.DataBean dataBean = new FoodListBean.DataBean();
                dataBean.setImgpath("hd");
                list.add(list.size() - 1, dataBean);
            }
            if (list.size() >= 3 && list.size() < 5) {       //3,5之间，设置可显示数量为3
                //小布局的父布局
                LinearLayout containerSmallLl = (LinearLayout) inflater.inflate(R.layout.food_detail_main_coverflow_3,null);
                coverFlowView = (CoverFlowView)containerSmallLl.findViewById(R.id.food_detail_main_fc);
                //coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
                //coverFlowView.setViImage(3);
                coverFlowView.setAdapter(cpAdapter);
            } else if (list.size() >= 5 && list.size() < 7) {    //5,7之间，设置可显示数量为5
                coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
                //coverFlowView.setViImage(5);
                coverFlowView.setAdapter(cpAdapter);
            } else if (list.size() >= 7) {                    //7以上，设置可显示数量为7
                coverFlowView = (CoverFlowView) view.findViewById(R.id.food_detail_main_fc);
                //coverFlowView.setViImage(7);
                coverFlowView.setAdapter(cpAdapter);
            }
            cpAdapter.notifyDataSetChanged();
            ((FoodDetailActivity) context).dismiss();
        }
        return coverFlowView;
    }



    @Deprecated
    private void initCoverView(List<FoodListBean.DataBean> list,int position){

        if(list.size() == 0){                //没有数据的情况
            coverMap.get(position).setVisibility(View.GONE);
            singleMap.get(position).setImageResource(R.mipmap.ic_food_detail_fill);
        } else if( list.size() == 1){       //等于1的情况
            coverMap.get(position).setVisibility(View.GONE);
            singleMap.get(position).setImageURI(CommonUtil.zoomPic(list.get(0).getImgpath()));
        } else {                        //数据大于等于2的情况
            if(list.size()%2 == 0){
                FoodListBean.DataBean dataBean = new FoodListBean.DataBean();
                dataBean.setImgpath("hd");
                list.add(list.size()-1,dataBean);
            }
            if(list.size() >= 3 && list.size() <= 5){       //3,5之间，设置可显示数量为3
                coverMap.get(position).setViImage(3);
                coverMap.get(position).setAdapter(adapterMap.get(position));
            } else if(list.size() >= 5 && list.size() <= 7){    //5,7之间，设置可显示数量为5
                coverMap.get(position).setViImage(5);
                coverMap.get(position).setAdapter(adapterMap.get(position));
            } else if(list.size() >= 7){                    //7以上，设置可显示数量为7
                coverMap.get(position).setViImage(7);
                coverMap.get(position).setAdapter(adapterMap.get(position));
            }
        }
    }

}
