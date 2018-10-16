package com.linli.consumer.ui.cybercafe;

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
import com.linli.consumer.adapter.cafe.ShopRecommendAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseFragment;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.AppBarStateChangeListener;
import com.linli.consumer.widget.CafeGoodSortMoreLayout;
import com.linli.consumer.widget.CafeShopMoreLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

import static com.linli.consumer.widget.CafeShopMoreLayout.SHOP_RECOMMEND;

/**
 * Created by hasee on 2018/9/29.
 */

public class FragmentHomepage extends BaseFragment implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener {
    private BGABanner shopBanner;       //轮播广告
    private List<String> bannerTempList = new ArrayList<>();        //轮播广告的数据

    private List<Object> rvlist = new ArrayList<>();            //列表数据
    private RecyclerView recyclerView;                          //listview
    private ShopRecommendAdapter adapter;                                //listview适配器
    private AppBarLayout appBarLayout;                          //appbar，用于下来刷新判断
    //private SwipeRefreshLayout refreshLayout;                   //下拉刷新

    private NestedScrollView nestedNs;                          //外层ns
    private LinearLayout containerLl;                           //内层父类
    private CafeShopMoreLayout sortLayout;                          //分类布局
    private LinearLayout loadMoreLl;                            //加载更多的布局
    private LinearLayout noMoreLl;                              //没有更多的布局

    private String broadcastStr = "暂无公告";

    private ImageView backIm;       //title的返回键
    private TextView titleTv;       //title的标题
    private LinearLayout backLl;    //title返回框
    private ImageView searchIm;     //搜索框
//    private CarNumLayout carNumLayout;  //数量小圆点
    private RelativeLayout cartRl;      //购物车按钮
    private ImageView cartIm;   //购物车图片
    private ImageView titleBack;   //title的背景图片

    private AppContext appContext = AppContext.getInstance();
    private User user ;

    private int adPageNum = 1;      //广告分页
    private List<String> bannerList = new ArrayList<>();        //轮播广告的数据
    private List<Object> adShopList = new ArrayList<>(); //商城的广告完整数据
    private int pagerNum = 1;       //页码
    private int perPagerNum = 16;    //每页显示几个，转为String使用
    private boolean isHaveData = true ;  //加载数据的开关
    private int [] title = {SHOP_RECOMMEND};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        //轮播图初始化
        shopBanner = findView(R.id.shop_main_banner);
        shopBanner.setOnItemClickListener(this);

        //appbar初始化
        appBarLayout = findView(R.id.shop_main_appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                changeBack(state);
            }
        });
        //ns , 子view 初始化
        nestedNs = findView(R.id.shop_main_nestedNs);
        containerLl = findView(R.id.shop_main_ll);
        //不同分类 布局
        sortLayout = new CafeShopMoreLayout(this.getActivity(),broadcastStr);
        containerLl.addView(sortLayout,0);

        //title
        backIm = findViewClick(R.id.shop_main_title_back);          //返回按钮图片
        titleTv = findViewClick(R.id.shop_main_title_name_tv);      //title文字
        backLl = findViewClick(R.id.shop_main_title_back_ll);
        searchIm = findViewClick(R.id.shop_main_title_search_im);
//        carNumLayout = findView(R.id.shop_main_title_num_widget);
        cartRl = findViewClick(R.id.shop_main_title_cart_rl);
        cartIm = findView(R.id.shop_main_title_cart_im);            //购物车图片
        titleBack = findView(R.id.shop_main_title_background_im);   //title黑色透明背景

        //  remove掉 xml布局中的recyclerView
        containerLl.removeViewAt(1);
        //  循环生成n个布局 “为你推荐”、“”...
        for(int i = 0;i<title.length;i++){
            // 更多 布局
            CafeGoodSortMoreLayout moreLayout = new CafeGoodSortMoreLayout(this.getActivity(),title[i]);
            // rc 布局
            LinearLayout view = (LinearLayout) LayoutInflater.from(this.getActivity()).inflate(R.layout.recycler_widget,null);
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_widget_rc);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            //list adapter
            List<Object> list = new ArrayList<>();
            ShopRecommendAdapter adapter = new ShopRecommendAdapter(this.getActivity(),list,1000000000);

            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);

            initShopData(i,adapter,list,moreLayout);

            //  添加布局
            containerLl.addView(moreLayout);
            containerLl.addView(view);
        }
    }

    @Override
    protected void initData() {
//        ShopNet.findSysPublic(new HandleSuccess<BroadCastBean>() {
//            @Override
//            public void success(BroadCastBean broadCastBean) {
//                if(broadCastBean.getData() != null) {
//                    if(broadCastBean.getData().size() > 0){
//                        broadcastStr = broadCastBean.getData().get(0).getTitle()+
//                                "--"+broadCastBean.getData().get(0).getContent();
//                        sortLayout.setBroadcast(broadcastStr);
//                    }
//                }
//            }
//        });
       handleAd();
        dismiss();
    }
    private void handleAd(){
//        AdNet.queryAd(this, city, user, type,"首页轮播图", adPageNum,new AdNet.AdInterface() {
//            @Override
//            public void onAdHandle(Object advertisementList) {
//                if(((AdvertisementList)advertisementList).getData() != null &&
//                        ((AdvertisementList)advertisementList).getData().size() > 0){
//                    for (int i = 0; i<((AdvertisementList)advertisementList).getData().size(); i++){
//                        bannerList.add(((AdvertisementList)advertisementList).getData().get(i).getPicurl());
//                        adShopList.add(((AdvertisementList)advertisementList).getData().get(i));
//                    }
//                } else {
//                    CommonUtil.handlerDefaultBanner(shopBanner);
//                }
//                shopBanner.setAdapter(FragmentHomepage.this);
//                shopBanner.setData(bannerList,bannerList);
//            }
//        });

    }
    /**
     * 根据不同type加载不同数据
     * @param type  类型
     * @param adapter 适配器通知
     * @param list  要加载数据的list
     * @param moreLayout  */
    private void initShopData(int type, final ShopRecommendAdapter adapter, final List<Object> list, final CafeGoodSortMoreLayout moreLayout) {
        //暂时固定的5个值，对应于益招鲜，惠生活，时尚流，雅居馆，养生堂
//        long[] cates = {14828949465519L, 14828949725032L, 14828949848257L, 14828949958941L, 14828950061514L};
//        ShopNet.shopRecommend(1, cates[type], 3, city.getLongitude(), city.getLatitude(), pagerNum, 3, new HandleSuccess<ShopListBean>() {
//            @Override
//            public void success(ShopListBean shopListBean) {
//                if (shopListBean.getData() != null && shopListBean.getData().size() != 0) {
//                    for (int i = 0; i < shopListBean.getData().size(); i++) {
//                        String dis = CommonUtil.setDistance(city, shopListBean.getData().get(i).getHdMallStore().getLongitude(),
//                                shopListBean.getData().get(i).getHdMallStore().getLatitude());
//
//                        shopListBean.getData().get(i).getHdMallStore().setDistance(dis);
//                        list.add(shopListBean.getData().get(i));
//                    }
//                    adapter.notifyDataSetChanged();
//                    dismiss();
//                } else {
//                    containerLl.removeView(moreLayout);
//                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_title_back:
            case R.id.shop_main_title_name_tv:
                //返回到未扫码状态
                break;
            default:
                break;
        }
    }

    public FragmentHomepage() {
    }
    public static FragmentHomepage newInstance() {
        FragmentHomepage fragment = new FragmentHomepage();
        return fragment;
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Picasso.with(this.getActivity()).load((String)model).into((ImageView)view);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
        //轮播图item点击事件
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
}
