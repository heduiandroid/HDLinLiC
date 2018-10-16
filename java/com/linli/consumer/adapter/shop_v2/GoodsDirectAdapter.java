package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.CateIdAndNameBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.directbusiness.ShopDetailDirectActivity;
import com.linli.consumer.widget.SpacesItem;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;

/**
 * @author tomoyo on 08/07/16.
 * 商城分类主页的适配器
 * 适配多种分类
 */
public class GoodsDirectAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titleList = new ArrayList<>();

    public List<String> cateShopDirect = new ArrayList<>();
    private City city ;
    private Map<Integer,Integer> numMap = new HashMap<>();  //分页的map,key:position  value:num
    private Long shopId;

    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;
    private List<CateIdAndNameBean> mainCates;
    private List<GoodsShopDirectAdapter> adapters = new ArrayList<>();

    public GoodsDirectAdapter(Context context, List<String> list, List<CateIdAndNameBean> mainCates,Long shopId) {
        this.context = context;
        this.titleList = list;
        this.mainCates = mainCates;
        this.shopId = shopId;
        city = AppContext.getInstance().getCity();
        for (int i = 0;i < mainCates.size();i++){
            cateShopDirect.add(mainCates.get(i).getName());
        }
        for(int i = 0 ; i < cateShopDirect.size() ; i ++){
            numMap.put(i,1);
        }

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
        return cateShopDirect.size();
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
        return cateShopDirect.get(position);
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
    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     * @param position 位置参数
     * */
    private void initData(final int position, final RecyclerView.Adapter adapter, final List<Object> list){
        if(city != null){
            findGoodsDirect(position,adapter,list);
        } else {
            Toast.makeText(context,"请获取位置信息",Toast.LENGTH_SHORT).show();
        }

        //TODO 根据position获取key，再拼接参数

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
    private List<Object> moreData(final int position, final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        //TODO 根据position获取key，再拼接参数
        findGoodsDirectMore(position,more,recyclerView,adapter,list);
        return list;
    }


    /**
     * 动态获取一个XRecyclerView
     * @param view  传入的根视图
     * */
    private XRecyclerView getRecyclerView(View view){
        XRecyclerView recyclerView = (XRecyclerView)view.findViewById(R.id.shop_sort_main_rc);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        SpacesItem decoration = new SpacesItem(8);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setPadding(2,8,2,8);
        return recyclerView;
    }

    /**
     * 动态获取一个适配器
     * @param
     * */
    private RecyclerView.Adapter getAdapter(List<Object> list){
        RecyclerView.Adapter adapter = null;
        adapter = new GoodsShopDirectAdapter(context,list,FOOD_MAIN_FINDFOOD);
        adapters.add((GoodsShopDirectAdapter)adapter);
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
        ((ShopDetailDirectActivity)context).dismiss();
    }


    /**
     * 找美食  初始化数据
     * FOOD_MAIN_FINDFOOD
     * */
    private void findGoodsDirect(final int position, final RecyclerView.Adapter adapter, final List<Object> list){
        if(city != null ) {
            ShopNet.goodsListOfShop2(shopId,mainCates.get(position).getId(), numMap.get(position),18
                    , new HandleSuccess<GoodsDetailListBean>() {
                        @Override
                        public void success(GoodsDetailListBean s) {
                            if(s.getData() != null){
                                for(int i = 0 ; i < s.getData().size(); i ++){
                                    list.add(s.getData().get(i));
                                }
                                adapter.notifyDataSetChanged();
                                perNum(position);
                            }
                            ((ShopDetailDirectActivity)context).dismiss();
                        }
                    });
        } else {
            city = AppContext.getInstance().getCity();
        }


    }
    /**
     * 找美食 更多数据
     * FOOD_MAIN_FINDFOOD
     * */
    private void findGoodsDirectMore(final int position, final int more, final XRecyclerView recyclerView,
                                     final RecyclerView.Adapter adapter, final List<Object> list){
        if(city != null) {
            ShopNet.goodsListOfShop2(shopId,mainCates.get(position).getId(), numMap.get(position),18
                    ,  new HandleSuccess<GoodsDetailListBean>() {
                        @Override
                        public void success(GoodsDetailListBean s) {
                            if(s.getData() != null){
                                switch (more){
                                    case REFRESH:
                                        for(int i = 0 ; i < s.getData().size(); i ++){
                                            list.add(s.getData().get(i));
                                        }
                                        break;
                                    case LOADMORE:      //加载更多，将数据填充到末尾
                                        for(int i = 0 ; i < s.getData().size(); i ++){
                                            list.add(list.size(),s.getData().get(i));
                                        }
                                        break;
                                }
                                perNum(position);

                            }
                            stopRefresh(adapter,recyclerView);
                        }
                    });
        } else {
            city = AppContext.getInstance().getCity();
        }

    }

    /**
     * 分页 +1 */
    private void perNum(int position){
        int num = numMap.get(position)+1;       //分页加1
        numMap.remove(position);
        numMap.put(position,num);
    }
    /**
     * 更新视图
     * */
    public void notifyGoodsUpdate(){
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

}
