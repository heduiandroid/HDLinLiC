package com.linli.consumer.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linli.consumer.R;

import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_HEALTH;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_MARKET;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_RECIPE;

/**
 * @author tomoyo on 08/07/16.
 * 商城分类主页的适配器
 * 适配多种分类
 */
@Deprecated
public class ShopSortAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titleList = new ArrayList<>();
    private int type ;     //标题的大分类


    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;


    public ShopSortAdapter(Context context,List<String> list,int type) {
        this.context = context;
        this.titleList = list;
        this.type = type;
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
     * */
    private void initView(View view , final int position){

        final List<Object> list = new ArrayList<>();
        final XRecyclerView recyclerView = getRecyclerView(view);
        final RecyclerView.Adapter adapter = getAdapter(type,list);
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
    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     * @param position 位置参数
     * */
    private void initData(int position, final RecyclerView.Adapter adapter, final List<Object> list){
        /*MockNet.MockFoodData(new HandleSuccess<NewsBean>() {
            @Override
            public void success(NewsBean newsBean) {

                for (int i = 0;i<newsBean.getResult().getData().size();i++){
                    list.add(newsBean.getResult().getData().get(i));

                }
                adapter.notifyDataSetChanged();
                ((ShopSortActivity)context).dismiss();

            }
        },titleList.get(position));*/
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
        },titleList.get(position));*/
        return list;
    }


    /**
     * 动态获取一个XRecyclerView
     * @param view  传入的根视图
     * */
    private XRecyclerView getRecyclerView(View view){
        XRecyclerView recyclerView = (XRecyclerView)view.findViewById(R.id.shop_sort_main_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        return recyclerView;
    }

    /**
     * 动态获取一个适配器
     * @param type 用于判断是哪种类型的适配器
     * */
    private RecyclerView.Adapter getAdapter(int type,List<Object> list){
        RecyclerView.Adapter adapter = null;
        switch (type){
            case FOOD_MAIN_FINDFOOD:
                adapter = new ShopSortFindfoodAdapter(context,list);
                break;
            case FOOD_MAIN_MARKET:
                break;
            case FOOD_MAIN_RECIPE:
                adapter = new ShopSortRecipeAdapter(context,list);
                break;
            case FOOD_MAIN_HEALTH:
                break;
        }
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


}
