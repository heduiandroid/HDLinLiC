package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.linli.consumer.R;

import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.CateIdAndNameBean;
import com.linli.consumer.bean.FindFoodBean;
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.bean.ShopListCategoryBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.shop_v2.ShopSortActivity;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_RECIPE;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_ALL;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_COUPON;

/**
 * @author tomoyo on 08/07/16.
 * 商城分类主页的适配器
 * 适配多种分类
 */
public class ShopSortAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titleList = new ArrayList<>();
    private int type ;     //标题的大分类

    public List<String> titleStrs = new ArrayList<>();
    private City city ;
    private Map<Integer,Integer> numMap = new HashMap<>();  //分页的map,key:position  value:num


    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;
    private List<CateIdAndNameBean> mainCates;

    public ShopSortAdapter(Context context, List<String> list, int type, List<CateIdAndNameBean> mainCates) {
        this.context = context;
        this.titleList = list;
        this.type = type;
        this.mainCates = mainCates;
        city = AppContext.getInstance().getCity();
        if( type == FOOD_MAIN_FINDFOOD){        //找美食，更多
            for (int i = 0;i < mainCates.size();i++){
                titleStrs.add(mainCates.get(i).getName());
            }
//            titleStrs.add("全部");
//            titleStrs.add("酒店/饭馆");
//            titleStrs.add("快餐/小吃");
//            titleStrs.add("茶吧/咖啡");
            for(int i = 0; i < titleStrs.size() ; i ++){
                numMap.put(i,1);
            }
        } else if(type == SHOP_MAIN_ALL){
            titleStrs.add("全部");
            titleStrs.add("益招鲜");
            titleStrs.add("惠生活");
            titleStrs.add("时尚流");
            titleStrs.add("雅居馆");
            titleStrs.add("养生堂");
            for(int i = 0; i < titleStrs.size() ; i ++){
                numMap.put(i,1);
            }
        } else if(type == FOOD_MAIN_RECIPE){
            String[] name = {"全部","川菜","湘菜","粤菜","川菜","东北菜","西北菜","浙菜","苏菜","上海菜","京菜"};
            for(int i = 0 ; i < name.length ; i ++){
                titleStrs.add(name[i]);
            }
            for(int i = 0; i < titleStrs.size() ; i ++){
                numMap.put(i,1);
            }
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
        if(type == FOOD_MAIN_FINDFOOD){
            return titleStrs.size();
        }else if(type == SHOP_MAIN_ALL){
            return titleStrs.size();
        }else if(type == FOOD_MAIN_RECIPE){
            return titleStrs.size();
        }
        else {
            return titleList.size();
        }

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

        if(type == FOOD_MAIN_FINDFOOD){
            return titleStrs.get(position);
        } else if(type == SHOP_MAIN_ALL){
            return titleStrs.get(position);
        } else if(type == FOOD_MAIN_RECIPE){
            return titleStrs.get(position);
        }
        else {
            return titleList.get(position);
        }

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
        initData(position,adapter,list,type);
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
    private void initData(final int position, final RecyclerView.Adapter adapter, final List<Object> list, int Type){
        if(city != null){
            switch (type){
                case FOOD_MAIN_FINDFOOD:        //找美食， 美食更多
                    foodFindFood(position,adapter,list);
                    break;
                case FOOD_MAIN_RECIPE:
                    foodRecipeData(position, adapter, list);
                    break;
                case SHOP_MAIN_COUPON:
                    break;
                case SHOP_MAIN_ALL:
                    shopAllData(position,adapter,list);
                    break;
            }
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
        switch (type){
            case FOOD_MAIN_FINDFOOD:
                foodFindFoodMore(position,more,recyclerView,adapter,list);
                break;
            case SHOP_MAIN_ALL:
                shopAllMore(position,more,recyclerView,adapter,list);
                break;
            case FOOD_MAIN_RECIPE:
                foodRecipeMoreData(position,more,recyclerView,adapter,list);
                break;
        }
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
            case FOOD_MAIN_FINDFOOD:        //找美食
                adapter = new ShopSortFindfoodAdapter(context,list,FOOD_MAIN_FINDFOOD);
                break;
            case FOOD_MAIN_RECIPE:          //食靠谱
                adapter = new ShopSortRecipeAdapter(context,list);
                break;
            case SHOP_MAIN_COUPON:          //优惠券
                adapter = new ShopSortCouponAdapter(context,list);
                break;
            case SHOP_MAIN_ALL:             //全部
                adapter = new ShopSortFindfoodAdapter(context,list,SHOP_MAIN_ALL);
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
        ((ShopSortActivity)context).dismiss();
    }


    /**
     * 找美食  初始化数据
     * FOOD_MAIN_FINDFOOD
     * */
    private void foodFindFood(final int position, final RecyclerView.Adapter adapter, final List<Object> list){
        if(city != null ) {
            FoodNet.findStoreByOpeType((position == 0 ? "" : mainCates.get(position).getId()+""), 3, city.getLongitude(), city.getLatitude()
                    , numMap.get(position), 10, new HandleSuccess<FindFoodBean>() {
                        @Override
                        public void success(FindFoodBean findFoodBean) {
                            if(findFoodBean.getData() != null){
                                for(int i = 0 ; i < findFoodBean.getData().size(); i ++){
                                    String dis = CommonUtil.setDistance(city
                                            ,findFoodBean.getData().get(i).getHdFoodStore().getLongitude()
                                            ,findFoodBean.getData().get(i).getHdFoodStore().getLatitude());
                                    findFoodBean.getData().get(i).getHdFoodStore().setDistance(dis);
                                    list.add(findFoodBean.getData().get(i));
                                }
                                adapter.notifyDataSetChanged();
                                perNum(position);
                            }
                            ((ShopSortActivity)context).dismiss();
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
    private void foodFindFoodMore(final int position, final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        if(city != null) {
            FoodNet.findStoreByOpeType((position == 0 ? "" :mainCates.get(position).getId()+""), 3, city.getLongitude(), city.getLatitude()
                    , numMap.get(position), 10, new HandleSuccess<FindFoodBean>() {
                        @Override
                        public void success(FindFoodBean findFoodBean) {
                            if(findFoodBean.getData() != null){
                                switch (more){
                                    case REFRESH:
                                        for(int i = 0 ; i < findFoodBean.getData().size(); i ++){
                                            String dis = CommonUtil.setDistance(city
                                                    ,findFoodBean.getData().get(i).getHdFoodStore().getLongitude()
                                                    ,findFoodBean.getData().get(i).getHdFoodStore().getLatitude());
                                            findFoodBean.getData().get(i).getHdFoodStore().setDistance(dis);
                                            list.add(findFoodBean.getData().get(i));
                                        }
                                        break;
                                    case LOADMORE:      //加载更多，将数据填充到末尾
                                        for(int i = 0 ; i < findFoodBean.getData().size(); i ++){
                                            String dis = CommonUtil.setDistance(city
                                                    ,findFoodBean.getData().get(i).getHdFoodStore().getLongitude()
                                                    ,findFoodBean.getData().get(i).getHdFoodStore().getLatitude());
                                            findFoodBean.getData().get(i).getHdFoodStore().setDistance(dis);
                                            list.add(list.size(),findFoodBean.getData().get(i));
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
     * 初始化食靠谱的数据
     * */
    private void foodRecipeData(final int position, final RecyclerView.Adapter adapter, final List<Object> list){
        FoodNet.findFoodCloud(position + "", numMap.get(position), 10,"", new HandleSuccess<FoodRecipeBean>() {
            @Override
            public void success(FoodRecipeBean foodRecipeBean) {
                if(foodRecipeBean.getData() != null){
                    for(int i = 0 ; i < foodRecipeBean.getData().size() ; i ++){
                        list.add(foodRecipeBean.getData().get(i));
                    }
                    adapter.notifyDataSetChanged();
                    perNum(position);
                }
                ((ShopSortActivity)context).dismiss();
            }
        });
    }

    /**
     * 食靠谱的数据加载更多
     * */
    private void foodRecipeMoreData(final int position, final int more, final XRecyclerView recyclerView,
                                    final RecyclerView.Adapter adapter, final List<Object> list){
        FoodNet.findFoodCloud(position + "", numMap.get(position), 10,"", new HandleSuccess<FoodRecipeBean>() {
            @Override
            public void success(FoodRecipeBean foodRecipeBean) {
                if(foodRecipeBean.getData() != null){
                    switch (more){
                        case REFRESH:
                            for(int i = 0 ; i < foodRecipeBean.getData().size() ; i ++){
                                list.add(foodRecipeBean.getData().get(i));
                            }
                            break;
                        case LOADMORE:      //加载更多，将数据填充到末尾
                            for(int i = 0 ; i < foodRecipeBean.getData().size() ; i ++){
                                list.add(list.size(),foodRecipeBean.getData().get(i));
                            }
                            break;
                    }
                    perNum(position);
                }
                stopRefresh(adapter,recyclerView);
            }
        });
    }

    /**
     * 请求数据
     * 商城全部
     * SHOP_MAIN_ALL
     * */
    private void shopAllData(final int type, final RecyclerView.Adapter adapter, final List<Object> list){
        //暂时固定的5个值，对应于益招鲜，惠生活，时尚流，雅居馆，养生堂
        long [] cates = {0,14828949465519L,14828949725032L,14828949848257L ,14828949958941L ,14828950061514L};
        if(city != null) {
            ShopNet.shopRecommendCategory(cates[type], 3, city.getLongitude(), city.getLatitude(),  numMap.get(type), 10, new HandleSuccess<ShopListCategoryBean>() {
                @Override
                public void success(ShopListCategoryBean shopListCategoryBean) {
                    if(shopListCategoryBean.getData() != null && shopListCategoryBean.getData().size() > 0){
                        for(int i = 0;i<shopListCategoryBean.getData().size();i++){
                            String dis = CommonUtil.setDistance(city,shopListCategoryBean.getData().get(i).getLongitude(),
                                    shopListCategoryBean.getData().get(i).getLatitude());

                            shopListCategoryBean.getData().get(i).setDistance(dis);
                            list.add(shopListCategoryBean.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        perNum(type);
                    }
                    ((ShopSortActivity)context).dismiss();
                }
            });
        }

    }
    private void shopAllMore(final int position, final int more, final XRecyclerView recyclerView,
                             final RecyclerView.Adapter adapter, final List<Object> list){
        long [] cates = {0,14828949465519L,14828949725032L,14828949848257L ,14828949958941L ,14828950061514L};
        if (more == REFRESH){
            list.clear();
            numMap.put(position,1);
        }
        if(city != null ){
            ShopNet.shopRecommendCategory(cates[position], 3, city.getLongitude(), city.getLatitude(),  numMap.get(position), 10, new HandleSuccess<ShopListCategoryBean>() {
                @Override
                public void success(ShopListCategoryBean shopListCategoryBean) {
                    Log.i("WATER",shopListCategoryBean.getData()+"");
                    if(shopListCategoryBean.getData() != null && shopListCategoryBean.getData().size() != 0){
                        switch (more){
                            case REFRESH:
                                for(int i = 0 ; i < shopListCategoryBean.getData().size(); i ++){
                                    String dis = CommonUtil.setDistance(city
                                            ,shopListCategoryBean.getData().get(i).getLongitude()
                                            ,shopListCategoryBean.getData().get(i).getLatitude());
                                    shopListCategoryBean.getData().get(i).setDistance(dis);
                                    list.add(shopListCategoryBean.getData().get(i));
                                }
                                break;
                            case LOADMORE:      //加载更多，将数据填充到末尾
                                for(int i = 0 ; i < shopListCategoryBean.getData().size(); i ++){
                                    String dis = CommonUtil.setDistance(city
                                            ,shopListCategoryBean.getData().get(i).getLongitude()
                                            ,shopListCategoryBean.getData().get(i).getLatitude());
                                    shopListCategoryBean.getData().get(i).setDistance(dis);
                                    list.add(list.size(),shopListCategoryBean.getData().get(i));
                                }
                                break;
                        }
                        perNum(position);
                    }
                    stopRefresh(adapter,recyclerView);
                }
            });
        }

    }

    /**
     * 分页 +1 */
    private void perNum(int position){
        int num = numMap.get(position)+1;       //分页加1
        numMap.remove(position);
        numMap.put(position,num);
    }


}
