package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopMoreAdapter;
import com.linli.consumer.adapter.shop_v2.ShopSortFlashSaleAdapter;
import com.linli.consumer.adapter.shop_v2.ShopSortHealthAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;

import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.bean.ServiceShopsBean;
import com.linli.consumer.bean.ShopListBean;
import com.linli.consumer.bean.ShopListCategoryBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.BackLayout;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_HEALTH;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_MARKET;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE0;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE1;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE10;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE2;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE3;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE4;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE5;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE6;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE7;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE8;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE9;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_FLASHSALE;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_WELLCHOICE;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Fashion;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Fresh;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Life;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Live;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_health;

/**
 * Created by tomoyo on 2016/12/8.
 *
 * 更多商店，优惠券 的activity
 * 只要是只有一个list，没有其他布局的模块，都用这个
 *
 * 对应 recyclerView
 *
 *  ——————单个———————
 *
 */

public class ShopMoreActivity extends BaseActivity {




    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;

    private XRecyclerView recyclerView;
    private List<Object> list = new ArrayList<>();
    private int TYPE;       // 小分类

    private BackLayout backLayout;
    private City city;
    private int pagerNum = 1;   //分页

    private int typeNum;    //type小分类对于的数字，这里单独拿出来，为0 ~ 5
    private int typeNumService;//type小分类对于的数字，这里单独拿出来，为0 ~ 10

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_more_v2;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        TYPE = intent.getIntExtra("Sort",1);
        city = AppContext.getInstance().getCity();

        backLayout = findView(R.id.shop_more_back);

        recyclerView = findView(R.id.shop_more_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());

        final RecyclerView.Adapter adapter = getAdapter(TYPE,list);
        recyclerView.setAdapter(adapter);
        initData(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if(TYPE == SHOP_MAIN_FLASHSALE){
                    moreFlashData(REFRESH,recyclerView,adapter,list);
                } else if (TYPE == SERVICE_MAIN_FINDSERVICE0 || TYPE == SERVICE_MAIN_FINDSERVICE1 || TYPE == SERVICE_MAIN_FINDSERVICE2 || TYPE == SERVICE_MAIN_FINDSERVICE3
                        || TYPE == SERVICE_MAIN_FINDSERVICE4 || TYPE == SERVICE_MAIN_FINDSERVICE5 || TYPE == SERVICE_MAIN_FINDSERVICE6 || TYPE == SERVICE_MAIN_FINDSERVICE7
                        || TYPE == SERVICE_MAIN_FINDSERVICE8 || TYPE == SERVICE_MAIN_FINDSERVICE9 || TYPE == SERVICE_MAIN_FINDSERVICE10){
                    moreServiceData(REFRESH,recyclerView,adapter,list);
                }else {
                    moreData(REFRESH,recyclerView,adapter,list);

                }
            }

            @Override
            public void onLoadMore() {
                if(TYPE == SHOP_MAIN_FLASHSALE){
                    moreFlashData(LOADMORE,recyclerView,adapter,list);
                } else if (TYPE == SERVICE_MAIN_FINDSERVICE0 || TYPE == SERVICE_MAIN_FINDSERVICE1 || TYPE == SERVICE_MAIN_FINDSERVICE2 || TYPE == SERVICE_MAIN_FINDSERVICE3
                        || TYPE == SERVICE_MAIN_FINDSERVICE4 || TYPE == SERVICE_MAIN_FINDSERVICE5 || TYPE == SERVICE_MAIN_FINDSERVICE6 || TYPE == SERVICE_MAIN_FINDSERVICE7
                        || TYPE == SERVICE_MAIN_FINDSERVICE8 || TYPE == SERVICE_MAIN_FINDSERVICE9 || TYPE == SERVICE_MAIN_FINDSERVICE10){
                    moreServiceData(REFRESH,recyclerView,adapter,list);
                }else {
                    moreData(LOADMORE,recyclerView,adapter,list);

                }
            }
        });
        fillData();
    }

    @Override
    protected void initData() {
    }
    private void initData(final RecyclerView.Adapter adapter){
        switch (TYPE){
            case FOOD_MAIN_MARKET:
                //TODO  SHOP_MORE_MAIN_Fresh;
                requestData(0,adapter);
                typeNum = 0;
                break;
            case SHOP_MORE_MAIN_Fresh:
                requestData(0,adapter);
                typeNum = 0;
                break;
            case SHOP_MORE_MAIN_Life:
                requestData(1,adapter);
                typeNum = 1;
                break;
            case SHOP_MORE_MAIN_Fashion:
                requestData(2,adapter);
                typeNum = 2;
                break;
            case SHOP_MORE_MAIN_Live:
                requestData(3,adapter);
                typeNum = 3;
                break;
            case SHOP_MORE_MAIN_health:
                requestData(4,adapter);
                typeNum = 4;
                break;
            case FOOD_MAIN_HEALTH:
                break;
            case SHOP_MAIN_WELLCHOICE:
                requestData(5,adapter);
                typeNum = 5;
                break;
            case SHOP_MAIN_FLASHSALE:           //限时促销
                requestFlashData(adapter);
                break;
            case SERVICE_MAIN_FINDSERVICE1:
                requestServiceData(1,adapter);
                typeNumService = 1;
                break;
            case SERVICE_MAIN_FINDSERVICE2:
                requestServiceData(2,adapter);
                typeNumService = 2;
                break;
            case SERVICE_MAIN_FINDSERVICE3:
                requestServiceData(3,adapter);
                typeNumService = 3;
                break;
            case SERVICE_MAIN_FINDSERVICE4:
                requestServiceData(4,adapter);
                typeNumService = 4;
                break;
            case SERVICE_MAIN_FINDSERVICE5:
                requestServiceData(5,adapter);
                typeNumService = 5;
                break;
            case SERVICE_MAIN_FINDSERVICE6:
                requestServiceData(6,adapter);
                typeNumService = 6;
                break;
            case SERVICE_MAIN_FINDSERVICE7:
                requestServiceData(7,adapter);
                typeNumService = 7;
                break;
            case SERVICE_MAIN_FINDSERVICE8:
                requestServiceData(8,adapter);
                typeNumService = 8;
                break;
            case SERVICE_MAIN_FINDSERVICE9:
                requestServiceData(9,adapter);
                typeNumService = 9;
                break;
            case SERVICE_MAIN_FINDSERVICE10:
                requestServiceData(10,adapter);
                typeNumService = 10;
                break;
            case SERVICE_MAIN_FINDSERVICE0:
                requestServiceData(0,adapter);
                typeNumService = 0;
                break;
        }
    }
    private long [] catesService = {0l,715016556145774L,715016556699505L,715016556252256L ,715016556337828L ,
            715016556429851L,715016556585723L,715016556799627L,715016556909349L,715016557001321L,715016557100643L};
    private void requestServiceData(int type, final RecyclerView.Adapter adapter) {
        //暂时固定的5个值，对应于益招鲜，惠生活，时尚流，雅居馆，养生堂

        if(city != null){
            FoodNet.findServiceStoresByCate(3, city.getLongitude(), city.getLatitude(), pagerNum++, 20, 2, catesService[type], new HandleSuccess<ServiceShopsBean>() {
                @Override
                public void success(ServiceShopsBean s) {
                    if(s.getData() != null && s.getData().size() != 0){
                        for(int i = 0;i<s.getData().size();i++){
                            String dis = CommonUtil.setDistance(city,s.getData().get(i).getLongitude(),
                                    s.getData().get(i).getLatitude());

                            s.getData().get(i).setDistance(dis);
                            list.add(s.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                    }
                    dismiss();
                }
            });
        } else {
            city = AppContext.getInstance().getCity();
            //requestData(type,adapter);
        }
    }
    private List<Object> moreServiceData(final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        FoodNet.findServiceStoresByCate(3, city.getLongitude(), city.getLatitude(), pagerNum++, 20, 2, catesService[typeNumService], new HandleSuccess<ServiceShopsBean>() {
            @Override
            public void success(ServiceShopsBean s) {
                if(s.getData() != null && s.getData().size() != 0){
                    switch (more){
                        case REFRESH:       //下拉刷新
                            for(int i = 0;i<s.getData().size();i++){
                                String dis = CommonUtil.setDistance(city,s.getData().get(i).getLongitude(),
                                        s.getData().get(i).getLatitude());

                                s.getData().get(i).setDistance(dis);
                                list.add(s.getData().get(i));
                            }
                            break;
                        case LOADMORE:      //上拉加载更多
                            for(int i = 0;i<s.getData().size();i++){
                                String dis = CommonUtil.setDistance(city,s.getData().get(i).getLongitude(),
                                        s.getData().get(i).getLatitude());

                                s.getData().get(i).setDistance(dis);
                                list.add(s.getData().size(),s.getData().get(i));
                            }
                    }

                }
                stopRefresh(adapter,recyclerView);
            }
        });
        return list;
    }
    /**
     * 请求数据
     * */
    private long [] cates = {14828949465519L,14828949725032L,14828949848257L ,14828949958941L ,14828950061514L,0};
    private void requestData(int type, final RecyclerView.Adapter adapter){
        //暂时固定的5个值，对应于益招鲜，惠生活，时尚流，雅居馆，养生堂

        if(city != null){
            ShopNet.shopRecommendCategory(cates[type], 3, city.getLongitude(), city.getLatitude(), pagerNum++, 20, new HandleSuccess<ShopListCategoryBean>() {
                @Override
                public void success(ShopListCategoryBean shopListCategoryBean) {
                    if(shopListCategoryBean.getData() != null && shopListCategoryBean.getData().size() != 0){
                        for(int i = 0;i<shopListCategoryBean.getData().size();i++){
                            String dis = CommonUtil.setDistance(city,shopListCategoryBean.getData().get(i).getLongitude(),
                                    shopListCategoryBean.getData().get(i).getLatitude());

                            shopListCategoryBean.getData().get(i).setDistance(dis);
                            list.add(shopListCategoryBean.getData().get(i));
                            Log.i("test",list.size()+"");
                        }
                        adapter.notifyDataSetChanged();
                    }
                    dismiss();
                }
            });
        } else {
            city = AppContext.getInstance().getCity();
            //requestData(type,adapter);
        }

    }

    private List<Object> moreData(final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        ShopNet.shopRecommendCategory(cates[typeNum], 3, city.getLongitude(), city.getLatitude(), pagerNum++, 20, new HandleSuccess<ShopListCategoryBean>() {
            @Override
            public void success(ShopListCategoryBean shopListCategoryBean) {
                if(shopListCategoryBean.getData() != null && shopListCategoryBean.getData().size() != 0){
                    switch (more){
                        case REFRESH:       //下拉刷新
                            for(int i = 0;i<shopListCategoryBean.getData().size();i++){
                                String dis = CommonUtil.setDistance(city,shopListCategoryBean.getData().get(i).getLongitude(),
                                        shopListCategoryBean.getData().get(i).getLatitude());

                                shopListCategoryBean.getData().get(i).setDistance(dis);
                                list.add(shopListCategoryBean.getData().get(i));
                            }
                            break;
                        case LOADMORE:      //上拉加载更多
                            Log.i("test",list.size()+"");
                            if (list.size() < shopListCategoryBean.getData().size()){//防止第二页数据比第一页数据多
                                for (int i = 0;i<shopListCategoryBean.getData().size() - list.size();i++){
                                    list.add(shopListCategoryBean.getData().get(i));
                                    shopListCategoryBean.getData().remove(i);
                                }
                            }
                            for(int i = 0;i<shopListCategoryBean.getData().size();i++){
                                String dis = CommonUtil.setDistance(city,shopListCategoryBean.getData().get(i).getLongitude(),
                                        shopListCategoryBean.getData().get(i).getLatitude());

                                shopListCategoryBean.getData().get(i).setDistance(dis);
                                list.add(shopListCategoryBean.getData().size(),shopListCategoryBean.getData().get(i));
                            }
                    }
                }
                stopRefresh(adapter,recyclerView);
            }
        });
        return list;
    }

    /**
     * 请求 限时促销
     *
     * 数据初始化
     *
     * (和商城主页的请求接口一样)
     * */
    private void requestFlashData(final RecyclerView.Adapter adapter){
        ShopNet.shopRecommend(1, 0, 3, city.getLongitude(), city.getLatitude(), pagerNum++, 20, new HandleSuccess<ShopListBean>() {
            @Override
            public void success(ShopListBean shopListBean) {
                if(shopListBean.getData() != null && shopListBean.getData().size() != 0){
                    for(int i = 0 ; i < shopListBean.getData().size() ; i ++){
                        for(int j = 0 ; j < shopListBean.getData().get(i).getVoList().size() ;  j ++){
                            MallGoodsVo mallGoodsVo = shopListBean.getData().get(i).getVoList().get(j);
                            mallGoodsVo.setStoreName(shopListBean.getData().get(i).getName());
                            list.add(mallGoodsVo);
                        }
                        //list.add(shopListBean.getData().get(i).getVoList().get(0));
                    }
                    adapter.notifyDataSetChanged();
                }
                dismiss();
            }
        });
    }

    /**
     * 限时促销
     * 请求更多数据
     * */
    private List<Object> moreFlashData(final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        if (more == REFRESH){
            list.clear();
            pagerNum = 1;
        }else {
            pagerNum++;
        }
        ShopNet.shopRecommend(1, 0, 3, city.getLongitude(), city.getLatitude(),pagerNum, 20, new HandleSuccess<ShopListBean>() {
            @Override
            public void success(ShopListBean shopListBean) {
                if(shopListBean.getData() != null && shopListBean.getData().size() != 0){
                    switch (more){
                        case REFRESH:       //下拉刷新
                            for(int i = 0 ; i < shopListBean.getData().size() ; i ++){
                                for(int j = 0 ; j < shopListBean.getData().get(i).getVoList().size() ;  j ++){
                                    MallGoodsVo mallGoodsVo = shopListBean.getData().get(i).getVoList().get(j);
                                    mallGoodsVo.setStoreName(shopListBean.getData().get(i).getName());
                                    list.add(mallGoodsVo);
                                }
                            }
                            break;
                        case LOADMORE:      //上拉加载更多
                            for(int i = 0 ; i < shopListBean.getData().size() ; i ++){
                                for(int j = 0 ; j < shopListBean.getData().get(i).getVoList().size() ;  j ++){
                                    MallGoodsVo mallGoodsVo = shopListBean.getData().get(i).getVoList().get(j);
                                    mallGoodsVo.setStoreName(shopListBean.getData().get(i).getName());
                                    list.add(list.size(),mallGoodsVo);      //装在list的末尾
                                }
                            }
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
                dismiss();
            }
        });
        return list;
    }

    /**
     * 动态获取一个适配器
     * @param type 用于判断是哪种类型的适配器
     * */
    private RecyclerView.Adapter getAdapter(int type, List<Object> list){
        RecyclerView.Adapter adapter = null;
        switch (type){
            case FOOD_MAIN_MARKET:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case FOOD_MAIN_HEALTH:
                adapter = new ShopSortHealthAdapter(this,list);
                break;
            case SHOP_MAIN_WELLCHOICE:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case SHOP_MAIN_FLASHSALE:
                adapter = new ShopSortFlashSaleAdapter(this,list);
                break;
            case SHOP_MORE_MAIN_Fresh:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case SHOP_MORE_MAIN_Life:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case SHOP_MORE_MAIN_Fashion:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case SHOP_MORE_MAIN_Live:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case SHOP_MORE_MAIN_health:
                adapter = new ShopMoreAdapter(this,list,SHOP_MAIN);
                break;
            case SERVICE_MAIN_FINDSERVICE0:
            case SERVICE_MAIN_FINDSERVICE1:
            case SERVICE_MAIN_FINDSERVICE2:
            case SERVICE_MAIN_FINDSERVICE3:
            case SERVICE_MAIN_FINDSERVICE4:
            case SERVICE_MAIN_FINDSERVICE5:
            case SERVICE_MAIN_FINDSERVICE6:
            case SERVICE_MAIN_FINDSERVICE7:
            case SERVICE_MAIN_FINDSERVICE8:
            case SERVICE_MAIN_FINDSERVICE9:
            case SERVICE_MAIN_FINDSERVICE10:
                adapter = new ShopMoreAdapter(this,list,SERVICE_MAIN);
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


    @Override
    protected void fillData() {
        String title = "";
        switch (TYPE){
            case FOOD_MAIN_MARKET:
                title = getResources().getString(R.string.food_main_sort_market_str);
                backLayout.setToSearchShop();
                break;
            case FOOD_MAIN_HEALTH:
                title = getResources().getString(R.string.food_main_sort_health_str);
                break;
            case SHOP_MAIN_WELLCHOICE:
                title = getResources().getString(R.string.shop_main_sort_wellchoice_str);
                backLayout.setToSearchShop();       //跳转到商城搜索界面
                break;
            case SHOP_MAIN_FLASHSALE:
                title = getResources().getString(R.string.shop_main_sort_flashsale_str);
                break;
            case SHOP_MORE_MAIN_Fresh:
                title = getResources().getString(R.string.shop_more_fresh);
                break;
            case SHOP_MORE_MAIN_Life:
                title = getResources().getString(R.string.shop_more_life);
                break;
            case SHOP_MORE_MAIN_Fashion:
                title = getResources().getString(R.string.shop_more_fashion);
                break;
            case SHOP_MORE_MAIN_Live:
                title = getResources().getString(R.string.shop_more_live);
                break;
            case SHOP_MORE_MAIN_health:
                title = getResources().getString(R.string.shop_more_health);
                break;
           /* case SHOP_MORE_MAIN:
                title = getResources().getString(R.string.shop_more);*/
            case SERVICE_MAIN_FINDSERVICE1:
                title = getResources().getString(R.string.service_cate1);
                break;
            case SERVICE_MAIN_FINDSERVICE2:
                title = getResources().getString(R.string.service_cate2);
                break;
            case SERVICE_MAIN_FINDSERVICE3:
                title = getResources().getString(R.string.service_cate3);
                break;
            case SERVICE_MAIN_FINDSERVICE4:
                title = getResources().getString(R.string.service_cate4);
                break;
            case SERVICE_MAIN_FINDSERVICE5:
                title = getResources().getString(R.string.service_cate5);
                break;
            case SERVICE_MAIN_FINDSERVICE6:
                title = getResources().getString(R.string.service_cate6);
                break;
            case SERVICE_MAIN_FINDSERVICE7:
                title = getResources().getString(R.string.service_cate7);
                break;
            case SERVICE_MAIN_FINDSERVICE8:
                title = getResources().getString(R.string.service_cate8);
                break;
            case SERVICE_MAIN_FINDSERVICE9:
                title = getResources().getString(R.string.service_cate9);
                break;
            case SERVICE_MAIN_FINDSERVICE10:
                title = getResources().getString(R.string.service_cate10);
                break;
            case SERVICE_MAIN_FINDSERVICE0:
                title = "全部服务";
                break;
        }
        backLayout.setTitle(title);
    }

    @Override
    public void onHDClick(View v) {

    }
}
