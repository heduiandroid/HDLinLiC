package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.SpacesItem;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.linli.consumer.dao.DBUtil.SHOP_GOODS_LIST;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * @author tomoyo on 08/07/16.
 * 商城分类主页的适配器
 * 适配多种分类
 */
public class ShopDetailAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titlesName = new ArrayList<>();    //分类的名字
    private List<Long> titlesId = new ArrayList<>();        //分类的id
    private long shopId;        //店铺id
    private String shopName;


    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;

    private List<ShopDetailGoodsAdapter> adapters = new ArrayList<>();
    private DBUtil dbUtil;

    private int type;

    private SparseArray<List<Object>> listMap = new SparseArray<>();

    private Map<Integer,Integer> numMap = new HashMap<>();  //分页的map,key:position  value:num


    public ShopDetailAdapter(Context context, List<String> titlesName, List<Long> titlesId, long shopId, String shopName, int type) {
        this.context = context;
        this.titlesName = titlesName;
        this.titlesId = titlesId ;
        this.shopId = shopId;
        dbUtil =  DBUtil.getInstance(context);
        this.type = type;
        this.shopName = shopName;
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
        numMap.put(position,1);
        final XRecyclerView recyclerView = getRecyclerView(view);
        final RecyclerView.Adapter adapter = getAdapter(list,titlesId.get(position));
        recyclerView.setAdapter(adapter);
        initData(position,adapter,list,recyclerView);
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
        listMap.put(position,list);

    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     * @param position 位置参数
     * */
    private void initData(final int position, final RecyclerView.Adapter adapter, final List<Object> list, final XRecyclerView recyclerView){
        if(type == SHOP_MAIN){
            ShopNet.goodsListOfShop2(shopId, titlesId.get(position), numMap.get(position), 20, new HandleSuccess<GoodsDetailListBean>() {
                @Override
                public void success(GoodsDetailListBean goodsDetailListBean) {

                    if(goodsDetailListBean.getData() != null && goodsDetailListBean.getData().size() > 0){
                        dbUtil.compareDataToDB2(goodsDetailListBean.getData(),SHOP_GOODS_LIST,titlesId.get(position),shopId,false);
                        for(int i = 0 ; i < goodsDetailListBean.getData().size(); i++){
                            list.add(goodsDetailListBean.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        ((BaseActivity)context).dismiss();
                        perNum(position);
                    } else {
                        noData(recyclerView);
                    }
                }
            });
        } else if(type == FOOD_MAIN){
            FoodNet.foodListOfSHop(shopId,titlesId.get(position),numMap.get(position),20, new HandleSuccess<FoodListBean>() {
                @Override
                public void success(FoodListBean foodListBean) {
                    if(foodListBean.getData() != null && foodListBean.getData().size() > 0){
                        dbUtil.compareDataToDB(foodListBean.getData(),titlesId.get(position),shopId,false);
                        for(int i = 0 ;i <foodListBean.getData().size(); i ++){
                            foodListBean.getData().get(i).setStoreName(shopName);
                            list.add(foodListBean.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        ((BaseActivity)context).dismiss();
                        perNum(position);
                    }
                    else {
                        noData(recyclerView);
                    }
                }
            });
        }else if (type == SERVICE_MAIN){
            FoodNet.findServiceStoresGoodsByStoreId(shopId, numMap.get(position), 20, new HandleSuccess<ServiceGoodBean>() {
                @Override
                public void success(ServiceGoodBean s) {
                    if (s.getData() != null && s.getData().size() > 0){
                        for (int i = 0;i < s.getData().size();i++){
                            s.getData().get(i).setStoreName(shopName);
                            list.add(s.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        ((BaseActivity)context).dismiss();
                        perNum(position);
                    }else {
                        noData(recyclerView);
                    }
                }
            });
        }

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
        switch (type){
            case FOOD_MAIN:
                foodMoreData(position, more, recyclerView, adapter, list);
                break;
            case SHOP_MAIN:
                shopMoreData(position, more, recyclerView, adapter, list);
                break;
            case SERVICE_MAIN:
                serviceMoreData(position,more,recyclerView,adapter,list);
                break;
        }
        return list;
    }

    private void serviceMoreData(final int position, final int more, final XRecyclerView recyclerView, final RecyclerView.Adapter adapter, final List<Object> list) {
        FoodNet.findServiceStoresGoodsByStoreId(shopId, numMap.get(position), 20, new HandleSuccess<ServiceGoodBean>() {
            @Override
            public void success(ServiceGoodBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    switch (more){
                        case REFRESH:
                            for (int i = 0;i < s.getData().size();i++){
                                s.getData().get(i).setStoreName(shopName);
                                list.add(s.getData().get(i));
                            }
                            break;
                        case LOADMORE:
                            for (int i = 0;i < s.getData().size();i++){
                                s.getData().get(i).setStoreName(shopName);
                                list.add(list.size(),s.getData().get(i));
                            }
                            break;
                    }

                    adapter.notifyDataSetChanged();
                    perNum(position);
                }else {
                    noData(recyclerView);
                }
                stopRefresh(adapter, recyclerView);
            }
        });
    }

    private void shopMoreData(final int position, final int more, final XRecyclerView recyclerView, final RecyclerView.Adapter adapter, final List<Object> list){
        ShopNet.goodsListOfShop2(shopId,titlesId.get(position),numMap.get(position),20, new HandleSuccess<GoodsDetailListBean>() {
            @Override
            public void success(GoodsDetailListBean goodsDetailListBean) {
                if(goodsDetailListBean.getData() != null && goodsDetailListBean.getData().size() > 0){
                    dbUtil.compareDataToDB2(goodsDetailListBean.getData(),SHOP_GOODS_LIST,titlesId.get(position),shopId,false);
                    switch (more){
                        case REFRESH:
                            for(int i = 0 ;i <goodsDetailListBean.getData().size(); i ++){
                                goodsDetailListBean.getData().get(i).setStoreName(shopName);
                                list.add(goodsDetailListBean.getData().get(i));
                            }
                            break;
                        case LOADMORE:
                            for(int i = 0 ;i <goodsDetailListBean.getData().size(); i ++){
                                goodsDetailListBean.getData().get(i).setStoreName(shopName);
                                list.add(list.size(),goodsDetailListBean.getData().get(i));
                            }
                            break;
                    }
                    adapter.notifyDataSetChanged();
                    perNum(position);
                }
                else {
                    noData(recyclerView);
                }
                stopRefresh(adapter, recyclerView);
            }
        });

    }

    private void foodMoreData(final int position, final int more, final XRecyclerView recyclerView, final RecyclerView.Adapter adapter, final List<Object> list){
        FoodNet.foodListOfSHop(shopId,titlesId.get(position),numMap.get(position),20, new HandleSuccess<FoodListBean>() {
            @Override
            public void success(FoodListBean foodListBean) {
                if(foodListBean.getData() != null && foodListBean.getData().size() > 0){
                    dbUtil.compareDataToDB(foodListBean.getData(),titlesId.get(position),shopId,false);
                    switch (more){
                        case REFRESH:
                            for(int i = 0 ;i <foodListBean.getData().size(); i ++){
                                foodListBean.getData().get(i).setStoreName(shopName);
                                list.add(foodListBean.getData().get(i));
                            }
                            break;
                        case LOADMORE:
                            for(int i = 0 ;i <foodListBean.getData().size(); i ++){
                                foodListBean.getData().get(i).setStoreName(shopName);
                                list.add(list.size(),foodListBean.getData().get(i));
                            }
                            break;
                    }
                    adapter.notifyDataSetChanged();
                    perNum(position);
                }
                else {
                    noData(recyclerView);
                }
                stopRefresh(adapter, recyclerView);
            }
        });

    }



    /**
     * 动态获取一个XRecyclerView
     * @param view  传入的根视图
     * */
    private XRecyclerView getRecyclerView(View view){
        XRecyclerView recyclerView = (XRecyclerView)view.findViewById(R.id.shop_sort_main_rc);
        //new LinearSnapHelper().attachToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false);
        int leftRight = CommonUtil.dip2px(12,context);
        int topBottom = CommonUtil.dip2px(12,context);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        SpacesItem decoration = new SpacesItem(8);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setPadding(8,8,8,8);
        //recyclerView.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom)); //使布局边距相等
        return recyclerView;
    }

    /**
     * 动态获取一个适配器
     * */
    private RecyclerView.Adapter getAdapter(List<Object> list, long titleId){
        RecyclerView.Adapter adapter = null;
        adapter = new ShopDetailGoodsAdapter(context,list,false,type,shopName,titleId);
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
    private void stopRefresh(RecyclerView.Adapter adapter, XRecyclerView recyclerView){
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        adapter.notifyDataSetChanged();
    }


    /**
     * 在dialog中处理数据完后更新视图
     * */
    public void notifyGoodsUpdate(){
        for(int i = 0;i<titlesId.size();i++){
            if(type == SHOP_MAIN){
                List<GoodsDetailBean> list = (List<GoodsDetailBean>)(List)listMap.get(i);
                if(list != null && list.size() >0){
                    dbUtil.compareDataToDB2(list,SHOP_GOODS_LIST,titlesId.get(i),shopId,false);
                }
            } else if(type == FOOD_MAIN){
                List<FoodListBean.DataBean> list = (List<FoodListBean.DataBean>)(List)listMap.get(i);
                if(list != null && list.size() > 0){
                    dbUtil.compareDataToDB(list,titlesId.get(i),shopId,false);
                }
            }
        }
        if(adapters.size() != 0){
            for(int i = 0;i<adapters.size();i++){
                adapters.get(i).notifyDataSetChanged();
            }
        }
        for(int i = 0;i<adapters.size();i++){
            if(adapters.get(i) != null){
                adapters.get(i).notifyDataSetChanged();
            }
        }

    }

    /**
     * 分页 +1
     * */
    private void perNum(int position){
        int num = numMap.get(position)+1;       //分页加1
        numMap.remove(position);
        numMap.put(position,num);
    }
    /**
     * 无数据的时候
     * */
    private void noData(XRecyclerView recyclerView){
        if(recyclerView != null){
            recyclerView.setNoMore(true);
        }
    }

}
