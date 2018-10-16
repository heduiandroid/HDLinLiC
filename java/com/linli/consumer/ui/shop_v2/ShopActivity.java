package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.NormalGoodsBean;
import com.linli.consumer.adapter.shop_v2.ShopAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.BroadCastBean;
import com.linli.consumer.bean.FoodRecommendBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.MallGoods;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.bean.ServicesBean;
import com.linli.consumer.bean.ShopListBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.AdNet;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.AppBarStateChangeListener;
import com.linli.consumer.widget.CarNumLayout;
import com.linli.consumer.widget.ShopSortLayout;
import com.linli.consumer.widget.ShopSortMoreLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bingoogolapple.bgabanner.BGABanner;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN_FINDSERVICE1;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Fashion;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Fresh;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_HOTSALE;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Life;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_Live;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_QUALITY;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_health;

/**
 * Created by tomoyo on 2016/11/23.
 *
 * shop food 主界面
 *
 * food 使用静态填充布局
 * shop 使用动态填充布局，所以使用时需要先将静态布局中的recyclerView 移除掉
 *
 */

public class ShopActivity extends BaseActivity implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener{

    private int type;       //美食或商城的类型

    private BGABanner shopBanner;       //轮播广告
    private List<String> bannerTempList = new ArrayList<>();        //轮播广告的数据
    private List<AdvertisementList.DataBean> adShopTempList = new ArrayList<>(); //商城的广告数据
    private List<AdvertisementListF.DataBean> adFoodTempList = new ArrayList<>();   //美食的广告数据

    private List<Object> rvlist = new ArrayList<>();            //列表数据
    private RecyclerView recyclerView;                          //listview
    private ShopAdapter adapter;                                //listview适配器
    private AppBarLayout appBarLayout;                          //appbar，用于下来刷新判断
    //private SwipeRefreshLayout refreshLayout;                   //下拉刷新

    private NestedScrollView nestedNs;                          //外层ns
    private LinearLayout containerLl;                           //内层父类
    private ShopSortLayout sortLayout;                          //分类布局
    private LinearLayout loadMoreLl;                            //加载更多的布局
    private LinearLayout noMoreLl;                              //没有更多的布局

    private String broadcastStr = "暂无公告";

    private ImageView backIm;       //title的返回键
    private TextView titleTv;       //title的标题
    private LinearLayout backLl;    //title返回框
    private ImageView searchIm;     //搜索框
    private CarNumLayout carNumLayout;  //数量小圆点
    private RelativeLayout cartRl;      //购物车按钮
    private ImageView cartIm;   //购物车图片
    private ImageView titleBack;   //title的背景图片

    private AppContext appContext = AppContext.getInstance();
    private City city = appContext.getCity();
    private User user ;


    private int pagerNum = 1;       //页码
    private int perPagerNum = 16;    //每页显示几个，转为String使用
    private boolean isHaveData = true ;  //加载数据的开关


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_v2;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        type = intent.getIntExtra("Sort",1);



        //轮播图初始化
        shopBanner = (BGABanner) findViewById(R.id.shop_main_banner);
        shopBanner.setOnItemClickListener(this);

        //appbar初始化
        appBarLayout = findView(R.id.shop_main_appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                changeBack(state);
            }
        });

        //下拉刷新初始化
        //refreshLayout = findView(R.id.shop_main_refresh_rf);
       /* refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //initFoodData();

            }
        });*/

        //ns , 子view 初始化
        nestedNs = findView(R.id.shop_main_nestedNs);
        containerLl = findView(R.id.shop_main_ll);
        //不同分类 布局
        sortLayout = new ShopSortLayout(this,type,broadcastStr);
        containerLl.addView(sortLayout,0);

        //title
        backIm = findViewClick(R.id.shop_main_title_back);          //返回按钮图片
        titleTv = findViewClick(R.id.shop_main_title_name_tv);      //title文字
        backLl = findViewClick(R.id.shop_main_title_back_ll);
        searchIm = findViewClick(R.id.shop_main_title_search_im);
        carNumLayout = findView(R.id.shop_main_title_num_widget);
        cartRl = findViewClick(R.id.shop_main_title_cart_rl);
        cartIm = findView(R.id.shop_main_title_cart_im);            //购物车图片
        titleBack = findView(R.id.shop_main_title_background_im);   //title黑色透明背景

        //根据不同type加载不同布局
        if(type == FOOD_MAIN){
            titleTv.setText("美食");
            initFoodView();
        }else if(type == SHOP_MAIN){
            titleTv.setText("商城");
            initShopView();
        }else if (type == SERVICE_MAIN){
            titleTv.setText("服务");
            initServiceView();
        }
        getDataFromDB();
        //UIHelper.togoGoodsDetailActivity(this,1,"lalal");
    }


    @Override
    protected void onResume() {
        super.onResume();
        user = appContext.getUser();
        sortLayout.updateUser();
        getDataFromDB();
        //test();
    }


    /**
     * 美食主界面
     * */
    private void initFoodView(){

        //初始化适配器
        adapter = new ShopAdapter(ShopActivity.this,rvlist,type);

        //recyclerView初始化
        recyclerView = (RecyclerView)findViewById(R.id.shop_main_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        //分类更多 布局
        ShopSortMoreLayout moreLayout = new ShopSortMoreLayout(this,FOOD_MAIN_FINDFOOD);
        //加载更多 布局
        loadMoreLl = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);
        noMoreLl = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.widget_nomore,null);

        //上拉加载更多 监听
        nestedNs.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(isHaveData){

                    if(nestedNs.getHeight() + scrollY == nestedNs.getChildAt(0).getHeight()){
                        if(containerLl.getChildCount() != 4){
                            containerLl.addView(loadMoreLl);
                            isHaveData = false;
                            initFoodDataMore();
                        }
                    }else {
                        if(containerLl.getChildCount() == 4){
                            containerLl.removeView(loadMoreLl);
                        }
                    }
                }

            }
        });

        containerLl.addView(moreLayout,1);
        isHaveData = false;
        initFoodData();
    }

    /**
     * 对底部加载更多视图的设置
     * */
    private void bottomView(int size){

        if(size < perPagerNum){
            isHaveData = false;

            if(containerLl.getChildAt(containerLl.getChildCount()-1) instanceof LinearLayout){
                containerLl.removeViewAt(containerLl.getChildCount()-1);
            }
            containerLl.addView(noMoreLl);
        } else {
            isHaveData = true;
        }

    }
    /* 两次点击的间隔时间*/
    private static final int MIN_CLICK_TIME = 1000;
    private static long lastClickTime = 0;

    /**
     * 商店主界面
     * */
    private int [] title = {SHOP_MORE_MAIN_Fresh,SHOP_MORE_MAIN_Life,SHOP_MORE_MAIN_Fashion,SHOP_MORE_MAIN_Live,SHOP_MORE_MAIN_health
            };
    private void initShopView(){

        //  remove掉 xml布局中的recyclerView
        containerLl.removeViewAt(1);

        //先加两个展示精品和热卖的商品的布局
        addQualityHotsaleViews(SHOP_MORE_MAIN_QUALITY);
        addQualityHotsaleViews(SHOP_MORE_MAIN_HOTSALE);

        //  循环生成5个布局
        for(int i = 0;i<title.length;i++){
            // 更多 布局
            ShopSortMoreLayout moreLayout = new ShopSortMoreLayout(this,title[i]);
            // rc 布局
            LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.recycler_widget,null);
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_widget_rc);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            //list adapter
            List<Object> list = new ArrayList<>();
            ShopAdapter adapter = new ShopAdapter(ShopActivity.this,list,type);

            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);

            //initShopData(title[i],adapter,list);
            initShopData(i,adapter,list,moreLayout);

            //  添加布局
            containerLl.addView(moreLayout);
            containerLl.addView(view);
        }

    }
    private void addQualityHotsaleViews(int sort) {
        ShopSortMoreLayout moreLayout = new ShopSortMoreLayout(this,sort);
        // rc 布局
        LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.recycler_widget,null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_widget_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //list adapter
        List<Object> list = new ArrayList<>();
        ShopAdapter adapter = new ShopAdapter(ShopActivity.this,list,type);

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        //  添加布局
        containerLl.addView(moreLayout);
        containerLl.addView(view);
        initGoodData(sort,adapter,list);
    }

    private void initGoodData(int sort, final ShopAdapter adapter, final List<Object> list) {
        switch (sort){
            case SHOP_MORE_MAIN_QUALITY:
                IntrestBuyNet.findRecommendList(1, 32, city.getLongitude(), city.getLatitude(), 3, new HandleSuccess<NormalGoodsBean>() {
                    @Override
                    public void success(NormalGoodsBean s) {
                        if (s.getData() != null && s.getData().size() > 0){
                            for (int i = 0;i<s.getData().size();i++){
                                //转换一下对象用于显示
                                NormalGoodsBean.DataBean item = s.getData().get(i);
                                MallGoodsVo mallGoodsVo = new MallGoodsVo();
                                mallGoodsVo.setStoreName("");

                                MallGoods mallgood = new MallGoods();
                                mallgood.setId(item.getId());
                                mallgood.setName(item.getName());
                                mallgood.setPrimaryImage(item.getPrimaryImage());
                                mallgood.setMinprice(item.getMinprice());

                                mallGoodsVo.setMallGoods(mallgood);
                                list.add(mallGoodsVo);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        dismiss();
                    }
                });
                break;
            case SHOP_MORE_MAIN_HOTSALE:
                ShopNet.shopRecommend(1, 0, 3, city.getLongitude(), city.getLatitude(), 1, 32, new HandleSuccess<ShopListBean>() {
                    @Override
                    public void success(ShopListBean shopListBean) {
                        if(shopListBean.getData() != null && shopListBean.getData().size() != 0){
                            for(int i = 0 ; i < shopListBean.getData().size() ; i ++){
                                for(int j = 0 ; j < shopListBean.getData().get(i).getVoList().size() ;  j ++){
                                    MallGoodsVo mallGoodsVo = shopListBean.getData().get(i).getVoList().get(j);
                                    mallGoodsVo.setStoreName(shopListBean.getData().get(i).getName());
                                    list.add(mallGoodsVo);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        dismiss();
                    }
                });
                break;
            default:
                break;
        }

    }

    private void initServiceView() {

        //初始化适配器
        adapter = new ShopAdapter(ShopActivity.this,rvlist,type);

        //recyclerView初始化
        recyclerView = (RecyclerView)findViewById(R.id.shop_main_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        //分类更多 布局
        ShopSortMoreLayout moreLayout = new ShopSortMoreLayout(this,SERVICE_MAIN_FINDSERVICE1);
        //加载更多 布局
        loadMoreLl = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);
        noMoreLl = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.widget_nomore,null);

        //上拉加载更多 监听
        nestedNs.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(isHaveData){

                    if(nestedNs.getHeight() + scrollY == nestedNs.getChildAt(0).getHeight()){
                        if(containerLl.getChildCount() != 4){
                            containerLl.addView(loadMoreLl);
                            isHaveData = false;
                            initServiceData();
                        }
                    }else {
                        if(containerLl.getChildCount() == 4){
                            containerLl.removeView(loadMoreLl);
                        }
                    }
                }

            }
        });

        containerLl.addView(moreLayout,1);
        isHaveData = false;
        initServiceData();
    }

    /**
     * 根据不同type加载不同数据
     * @param type  类型
     * @param adapter 适配器通知
     * @param list  要加载数据的list
     * @param moreLayout  */
    private void initShopData(int type, final ShopAdapter adapter, final List<Object> list, final ShopSortMoreLayout moreLayout){
        //暂时固定的5个值，对应于益招鲜，惠生活，时尚流，雅居馆，养生堂
        long [] cates = {14828949465519L,14828949725032L,14828949848257L ,14828949958941L ,14828950061514L};
        if(city != null){
            ShopNet.shopRecommend(1, cates[type], 3, city.getLongitude(), city.getLatitude(), pagerNum, 3, new HandleSuccess<ShopListBean>() {
                @Override
                public void success(ShopListBean shopListBean) {
                    if(shopListBean.getData() != null && shopListBean.getData().size() != 0){
                        for(int i = 0;i<shopListBean.getData().size();i++){
                            String dis = CommonUtil.setDistance(city,shopListBean.getData().get(i).getHdMallStore().getLongitude(),
                                    shopListBean.getData().get(i).getHdMallStore().getLatitude());

                            shopListBean.getData().get(i).getHdMallStore().setDistance(dis);
                            list.add(shopListBean.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        dismiss();
                    }else {
                        containerLl.removeView(moreLayout);
                    }
                }
            });
        }

        /*ShopNet.shopRecommend(1,14828949725032L, 1, city.getLongitude(), city.getLatitude(), pagerNum, 100, new HandleSuccess<ShopListBean>() {
            @Override
            public void success(ShopListBean shopListBean) {
                Log.i("WATER",shopListBean.getData()+"");
                if(shopListBean.getData() != null && shopListBean.getData().size() != 0){
                    for(int i = 0;i<shopListBean.getData().size();i++){
                        double dis = setDistance(shopListBean.getData().get(i).getHdMallStore().getLongitude(),
                                shopListBean.getData().get(i).getHdMallStore().getLatitude());

                        shopListBean.getData().get(i).getHdMallStore().setDistance(dis);
                        list.add(shopListBean.getData().get(i));
                    }
                    adapter.notifyDataSetChanged();
                    dismiss();
                }
            }
        });*/
    }

    /**
     * 初始化food 数据，包括下拉刷新和初始化用一个数据
     * */
    private void initFoodData(){
        if(city != null) {
            FoodNet.getFoodAndShopRecommend(perPagerNum+"", pagerNum, "3", city.getLongitude(), city.getLatitude(), new HandleSuccess<FoodRecommendBean>() {
                @Override
                public void success(FoodRecommendBean foodRecommendBean) {
                    for(int i = 0;i < foodRecommendBean.getData().size();i++){
                        String dis = CommonUtil.setDistance(city,foodRecommendBean.getData().get(i).getHdFoodStore().getLongitude(),
                                foodRecommendBean.getData().get(i).getHdFoodStore().getLatitude());
                        foodRecommendBean.getData().get(i).getHdFoodStore().setDistance(dis);
                        rvlist.add(0,foodRecommendBean.getData().get(i));
                    }
                    adapter.notifyDataSetChanged();
                    bottomView(foodRecommendBean.getData().size());
                    pagerNum = pagerNum + 1;
                    dismiss();
                }
            });
        }

    }
    private void initServiceData() {
        if (city != null){
            FoodNet.findServiceStoresRecByLocation(3, city.getLongitude(), city.getLatitude(),pagerNum,perPagerNum,1,new HandleSuccess<ServicesBean>() {
                @Override
                public void success(ServicesBean s) {
                    if (s.getStatus() == 1 && s.getData() != null){
                        for (int i = 0;i < s.getData().size();i++){
                            String dis = CommonUtil.setDistance(city,s.getData().get(i).getHdServeStore().getLongitude(),s.getData().get(i).getHdServeStore().getLatitude());
                            s.getData().get(i).getHdServeStore().setDistance(dis);
                            rvlist.add(s.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        bottomView(s.getData().size());
                        pagerNum = pagerNum + 1;
                        dismiss();
                    }
                }
            });
        }
    }
    /**
     * food 数据，上拉加载数据
     * */
    private void initFoodDataMore(){
        if(city != null){
            FoodNet.getFoodAndShopRecommend(perPagerNum+"", pagerNum, "3", city.getLongitude(), city.getLatitude(), new HandleSuccess<FoodRecommendBean>() {
                @Override
                public void success(FoodRecommendBean foodRecommendBean) {
                    for(int i = 0;i < foodRecommendBean.getData().size();i++){
                        String dis = CommonUtil.setDistance(city,foodRecommendBean.getData().get(i).getHdFoodStore().getLongitude(),
                                foodRecommendBean.getData().get(i).getHdFoodStore().getLatitude());
                        foodRecommendBean.getData().get(i).getHdFoodStore().setDistance(dis);
                        rvlist.add(foodRecommendBean.getData().get(i));
                    }
                    pagerNum = pagerNum + 1;
                    adapter.notifyDataSetChanged();
                    bottomView(foodRecommendBean.getData().size());
                    dismiss();
                }
            });
        }

    }



    private int adPageNum = 1;      //广告分页
    private List<String> bannerList = new ArrayList<>();        //轮播广告的数据
    private List<AdvertisementList.DataBean> adShopList = new ArrayList<>(); //商城的广告数据
    private List<AdvertisementListF.DataBean> adFoodList = new ArrayList<>();   //美食的广告数据
    /**
     * 初始化banner数据
     * */
    @Override
    protected void initData() {
        ShopNet.findSysPublic(new HandleSuccess<BroadCastBean>() {
            @Override
            public void success(BroadCastBean broadCastBean) {
                if(broadCastBean.getData() != null) {
                    if(broadCastBean.getData().size() > 0){
                        broadcastStr = broadCastBean.getData().get(0).getTitle()+
                                "--"+broadCastBean.getData().get(0).getContent();
                        sortLayout.setBroadcast(broadcastStr);
                    }
                }
            }
        });
        /*
        此处加额外全局广告//////////////////////
         */
        AdvertisementList.DataBean specilAdver = new AdvertisementList.DataBean();
        specilAdver.setStoreId(0L);
        adShopList.add(specilAdver);
//        AdvertisementListF.DataBean specilAdverF = new AdvertisementListF.DataBean();
//        specilAdverF.setStoreId(0L);
//        adFoodList.add(specilAdverF);
        if (type == SHOP_MAIN){
            bannerList.add(Common.ADVER_PIC_RIL);
            shopBanner.setAdapter(ShopActivity.this);
            shopBanner.setData(bannerList,bannerList);
        }

        AdNet.queryAd(this, city, user, type,"首页轮播图", adPageNum,new AdNet.AdInterface() {
            @Override
            public void onAdHandle(Object advertisementList) {
                if(type == FOOD_MAIN){      //美食的广告
                    if(((AdvertisementListF)advertisementList).getPage() != null ){
                        int page = ((AdvertisementListF)advertisementList).getPage().getTotalPage();
                        if(page != 1 ){
                            adPageNum = CommonUtil.randomPage(page);
                        }
                        if(((AdvertisementListF)advertisementList).getData() != null
                                && ((AdvertisementListF)advertisementList).getData().size() > 0){
                            for (int i = 0; i<((AdvertisementListF)advertisementList).getData().size(); i++){
                                bannerTempList.add(((AdvertisementListF)advertisementList).getData().get(i).getPicurl());
                                adFoodTempList.add(((AdvertisementListF)advertisementList).getData().get(i));
                            }
                            handleAd();

                        } else {
                            CommonUtil.handlerDefaultBanner(shopBanner);
                        }
                    } else {
                        CommonUtil.handlerDefaultBanner(shopBanner);
                    }

                } else if(type == SHOP_MAIN){       //商城的广告
                    if(((AdvertisementList)advertisementList).getPage() != null ){
                        int page = ((AdvertisementList)advertisementList).getPage().getTotalPage();
                        if(page != 1 ){
                            adPageNum = CommonUtil.randomPage(page);
                        }
                        if(((AdvertisementList)advertisementList).getData() != null &&
                                ((AdvertisementList)advertisementList).getData().size() > 0){
                            for (int i = 0; i<((AdvertisementList)advertisementList).getData().size(); i++){
                                bannerTempList.add(((AdvertisementList)advertisementList).getData().get(i).getPicurl());
                                adShopTempList.add(((AdvertisementList)advertisementList).getData().get(i));
                            }
                            handleAd();
                        } else {
                            CommonUtil.handlerDefaultBanner(shopBanner);
                        }
                    } else {
                        CommonUtil.handlerDefaultBanner(shopBanner);
                    }
                }
                //shopBanner.setAdapter(ShopActivity.this);
                //shopBanner.setData(bannerTempList,bannerTempList);
                dismiss();
            }
        });
    }
    private void handleAd(){
        AdNet.queryAd(this, city, user, type,"首页轮播图", adPageNum,new AdNet.AdInterface() {
            @Override
            public void onAdHandle(Object advertisementList) {
                if(type == FOOD_MAIN){      //美食的广告
                    if(((AdvertisementListF)advertisementList).getData().size() >= 4){
                        for (int i = 0;i<4;i++){        //如果数量大于4，就把广告全部装入
                            bannerList.add(((AdvertisementListF)advertisementList).getData().get(i).getPicurl());
                            adFoodList.add(((AdvertisementListF)advertisementList).getData().get(i));
                        }
                    }else {
                        for (int i = 0; i<((AdvertisementListF)advertisementList).getData().size(); i++){     //先把数据全放进list，数据数量一定小于4
                            bannerList.add(((AdvertisementListF)advertisementList).getData().get(i).getPicurl());
                            adFoodList.add(((AdvertisementListF)advertisementList).getData().get(i));
                        }
                        if(adPageNum != 1){         //随机页码不为第一页时，从tempList中取出临时数据，填入列表
                            int realSize = bannerList.size();
                            for(int i = 0 ; i < 4-realSize ; i ++){
                                itFood(addFoodAdToList());
                            }
                        }
                    }
                } else if(type == SHOP_MAIN){       //商城的广告
                    if(((AdvertisementList)advertisementList).getData().size() >= 4){
                        for (int i = 0;i<4;i++){        //如果数量大于4，就把广告全部装入
                            bannerList.add(((AdvertisementList)advertisementList).getData().get(i).getPicurl());
                            adShopList.add(((AdvertisementList)advertisementList).getData().get(i));
                        }
                    }else {
                        for (int i = 0; i<((AdvertisementList)advertisementList).getData().size(); i++){     //先把数据全放进list，数据数量一定小于4
                            bannerList.add(((AdvertisementList)advertisementList).getData().get(i).getPicurl());
                            adShopList.add(((AdvertisementList)advertisementList).getData().get(i));
                        }
                        if(adPageNum != 1){         //随机页码不为第一页时，从tempList中取出临时数据，填入列表
                            int realSize = bannerList.size();
                            for(int i = 0 ; i < 4-realSize ; i ++){
                                itShop(addShopAdToList());
                            }
                        }
                    }
                }
                shopBanner.setAdapter(ShopActivity.this);
                shopBanner.setData(bannerList,bannerList);

                dismiss();
            }
        });
    }

    /**
     * 这里防止随机数据重复，如果有重复数据，就在执行一次
     * */
    private void itShop(AdvertisementList.DataBean temp){
        if(adShopList.contains(temp)) {
            itFood(addFoodAdToList());
        } else {
            adShopList.add(temp);
        }
    }

    private void itFood(AdvertisementListF.DataBean temp){
        if(adFoodList.contains(temp)) {
            itFood(addFoodAdToList());
        } else {
            adFoodList.add(temp);
        }
    }
    private AdvertisementList.DataBean addShopAdToList(){
        int seed = new Random().nextInt(adShopTempList.size()-1);
        return adShopTempList.get(seed);
    }
    private AdvertisementListF.DataBean addFoodAdToList(){
        int seed = new Random().nextInt(adFoodTempList.size()-1);
        return adFoodTempList.get(seed);
    }

    @Override
    public void onHDClick(View v) {

        switch (v.getId()){
            case R.id.shop_main_title_back:
            case R.id.shop_main_title_name_tv:
                finish();
                break;
            case R.id.shop_main_title_search_im:
                UIHelper.togoSearchActivity(this,type);
                break;
            case R.id.shop_main_title_cart_rl:
                UIHelper.togoCartActivity(this);
                break;
        }

    }

    private DBUtil dbUtil =  DBUtil.getInstance(this);
    private List<GoodsBean> goodsList = new ArrayList<>();

    /**
     * 从数据库获取购物车信息
     *
     * 两处调用
     * 初始化数据    {@linkplain #initView()}
     * 跳转后同步数据  {@linkplain #onPause()}
     * */
    private void getDataFromDB(){
        goodsList = dbUtil.queryAll();
        int sum = 0;
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
            }
            carNumLayout.setVisibility(View.VISIBLE);
            carNumLayout.setMyNum(sum+"");
        }else {
            carNumLayout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 轮播图数据填充
     * */
    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Picasso.with(ShopActivity.this).load((String)model).into((ImageView)view);
    }

    /**
     * 轮播图点击事件
     * */
    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
        if(type == FOOD_MAIN){
            if (adFoodList.size() < position+1){
                return;
            }
            if (adFoodList.get(position).getStoreId() != 0L) {
                UIHelper.togoShopDetailActivity(this, adFoodList.get(position).getStoreId()
                        , adFoodList.get(position).getHdFoodStore().getName(), FOOD_MAIN);
            }else {
                UIHelper.callMyBrowser(this, Common.specilAdverUrl);
            }

        } else if(type == SHOP_MAIN){
            if (adShopList.size() < position+1){
                return;
            }
            if (adShopList.get(position).getStoreId() != 0L) {
                UIHelper.togoShopDetailActivity(this, adShopList.get(position).getStoreId()
                        , adShopList.get(position).getHdMallStore().getName(), SHOP_MAIN);
            }else {
                UIHelper.callMyBrowser(this,Common.specilAdverUrl);
            }

        }else if (type == SERVICE_MAIN){//服务模块没有轮播图点击事件可以到某个商铺首页///////////////////////////////////////////////////
            UIHelper.callMyBrowser(this,Common.specilAdverUrl);
//            if (adServiceList.size() < position+1){
//                return;
//            }
//            UIHelper.togoShopDetailActivity(this,adServiceList.get(position).getStoreId()
//                    ,adServiceList.get(position).getHdMallStore().getName(),SERVICE_MAIN);
        }
    }

    /**
     * 在appbar伸缩时，对title的图片文字进行颜色变化
     * */
    private void changeBack(AppBarStateChangeListener.State state){
        if(state == AppBarStateChangeListener.State.COLLAPSED){     //折叠
            backIm.setImageResource(R.mipmap.ic_shop_main_title_back_black);     //返回按钮
            titleTv.setTextColor(getResources().getColor(R.color.gray));    //title文字
            cartIm.setImageResource(R.mipmap.ic_shop_main_title_cart_black);                     //购物车图片
            titleBack.setVisibility(View.INVISIBLE);         //title背景
        } else if(state == AppBarStateChangeListener.State.EXPANDED){
            backIm.setImageResource(R.mipmap.ic_shop_main_title_back_white);     //返回按钮
            titleTv.setTextColor(getResources().getColor(R.color.white));    //title文字
            cartIm.setImageResource(R.mipmap.ic_shop_main_title_cart_white);                     //购物车图片
            titleBack.setVisibility(View.VISIBLE);         //title背景
        } else if(state == AppBarStateChangeListener.State.IDLE){
            backIm.setImageResource(R.mipmap.ic_shop_main_title_back_black);     //返回按钮
            titleTv.setTextColor(getResources().getColor(R.color.gray));    //title文字
            cartIm.setImageResource(R.mipmap.ic_shop_main_title_cart_black);                     //购物车图片
            titleBack.setVisibility(View.INVISIBLE);         //title背景
        }

    }

    /**
     * 下拉刷新监听
     * */
    //@Override
    //public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //refreshLayout.setEnabled(0 == verticalOffset);
    //}



}
