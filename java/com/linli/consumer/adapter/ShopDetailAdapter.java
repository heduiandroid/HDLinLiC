package com.linli.consumer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

import com.linli.consumer.bean.ShopBeanV2;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.List;


/**
 * @author tomoyo on 08/07/16.
 * 商城分类主页的适配器
 * 适配多种分类
 */
@Deprecated
public class ShopDetailAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titlesName = new ArrayList<>();    //分类的名字
    private List<Long> titlesId = new ArrayList<>();        //分类的id
    private long shopId;        //店铺id


    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;

    private List<ShopDetailGoodsAdapter> adapters = new ArrayList<>();


    public ShopDetailAdapter(Context context,List<String> titlesName,List<Long> titlesId,long shopId) {
        this.context = context;
        this.titlesName = titlesName;
        this.titlesId = titlesId ;
        this.shopId = shopId;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view ;
        view = LayoutInflater.from(context).inflate(R.layout.shop_sort_main_adapter_container, container, false);
        initView(view,position);
        if (view != null) {
            view.setTag(String.valueOf(position));
            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {
        return titlesName.size();
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
        return titlesName.get(position);
    }

    /**
     * 初始化
     * 包含视图初始化和数据初始化
     * 初始化根据出入的position
     * */
    private void initView(View view , final int position){

        final List<Object> list = new ArrayList<>();
        final XRecyclerView recyclerView = getRecyclerView(view);
        final RecyclerView.Adapter adapter = getAdapter(list);
        recyclerView.setAdapter(adapter);
        initData(position,adapter,list);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                moreData(position,REFRESH,recyclerView,adapter,list);
            }

            @Override
            public void onLoadMore() {
                moreData(position,LOADMORE,recyclerView,adapter,list);
            }
        });
        recyclerView.setPullRefreshEnabled(false);

    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     * @param position 位置参数
     * */
    private void initData(final int position, final RecyclerView.Adapter adapter, final List<Object> list){
        ShopNet.goodsListOfShop(shopId, titlesId.get(position), 1, 1000, new HandleSuccess<ShopBeanV2>() {
            @Override
            public void success(ShopBeanV2 shopBeanV2) {
                for(int i = 0 ; i < shopBeanV2.getData().size(); i++){
                    list.add(shopBeanV2.getData().get(i));
                }
                adapter.notifyDataSetChanged();
                ((BaseActivity)context).dismiss();
            }
        });
    }

    /**
     * 加载更多数据
     * @param position 通过位置参数获取数据
     * @param more 对于下拉刷新 还是 上拉加载的判断，
     *             上拉刷新将数据加载到list的前面
     *             下拉加载将数据加载到list的后面
     * @param recyclerView
     * @param adapter   刷新视图
     *
     * */
    private List<Object> moreData(int position, final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        /*MockNet.MockFoodData(new HandleSuccess<NewsBean>() {
            @Override
            public void success(NewsBean newsBean) {
                switch (more){
                    case REFRESH:
                        for (int i = 0;i<newsBean.getResult().getData().size();i++){
                            list.add(newsBean.getResult().getData().get(i));
                        }
                        break;
                    case LOADMORE:
                        for (int i = 0;i<newsBean.getResult().getData().size();i++){
                            list.add(list.size(),newsBean.getResult().getData().get(i));
                        }
                        break;
                }
                stopRefresh(adapter,recyclerView);
            }
        },titlesName.get(position));*/
        return list;
    }


    /**
     * 动态获取一个XRecyclerView
     * @param view  传入的根视图
     * */
    private XRecyclerView getRecyclerView(View view){
        XRecyclerView recyclerView = (XRecyclerView)view.findViewById(R.id.shop_sort_main_rc);
        //new LinearSnapHelper().attachToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        return recyclerView;
    }

    /**
     * 动态获取一个适配器
     * */
    private RecyclerView.Adapter getAdapter(List<Object> list){
        RecyclerView.Adapter adapter = null;
        adapter = new ShopDetailGoodsAdapter(context,list,false);
        adapters.add((ShopDetailGoodsAdapter)adapter);
        return adapter;
    }


    /**
     * 刷新完成，通知更新
     *
     * @param adapter   适配器
     * @param recyclerView   列表
     *
     * */
    private void stopRefresh(RecyclerView.Adapter adapter,XRecyclerView recyclerView){
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        adapter.notifyDataSetChanged();
    }


    /**
     * 在dialog中处理数据完后更新视图
     * */
    public void notifyGoodsUpdate(){
        if(adapters.size() != 0){
            for(int i = 0;i<adapters.size();i++){
                adapters.get(i).notifyDataSetChanged();
            }
        }
    }

}
