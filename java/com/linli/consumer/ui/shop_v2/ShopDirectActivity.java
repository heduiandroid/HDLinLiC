package com.linli.consumer.ui.shop_v2;

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
import com.linli.consumer.adapter.shop_v2.ShopDirectAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.DirectGoodBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.AppBarStateChangeListener;
import com.linli.consumer.widget.CarNumLayout;
import com.linli.consumer.widget.ShopSortDirectMoreLayout;
import com.linli.consumer.widget.ShopSortV2Layout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_BEST_GOODS;
import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_FULL_MINUTE;
import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_HOT_SALE;

/**
 * Created by tomoyo on 2016/11/23.
 *
 * shop food 主界面
 *
 * food 使用静态填充布局
 * shop 使用动态填充布局，所以使用时需要先将静态布局中的recyclerView 移除掉
 *
 */

public class ShopDirectActivity extends BaseActivity implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener{

    private BGABanner shopBanner;       //轮播广告
    private List<String> bannerTempList = new ArrayList<>();        //轮播广告的数据
    private List<AdvertisementList.DataBean> adShopTempList = new ArrayList<>(); //商城的广告数据
    private List<AdvertisementListF.DataBean> adFoodTempList = new ArrayList<>();   //美食的广告数据

    private AppBarLayout appBarLayout;                          //appbar，用于下来刷新判断
    //private SwipeRefreshLayout refreshLayout;                   //下拉刷新

    private NestedScrollView nestedNs;                          //外层ns
    private LinearLayout containerLl;                           //内层父类
    private ShopSortV2Layout sortLayout;                          //分类布局
    private LinearLayout noMoreLl;                              //没有更多的布局

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


    private int perPagerNum = 16;    //每页显示几个，转为String使用
    private boolean isHaveData = true ;  //加载数据的开关


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_v2_direct;
    }

    @Override
    protected void initView() {
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
        sortLayout = new ShopSortV2Layout(this);
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
        titleTv.setText(R.string.title_good_direct_str);
        initShopView();

        getDataFromDB();
        //UIHelper.togoGoodsDetailActivity(this,1,"lalal");
    }


    @Override
    protected void onResume() {
        super.onResume();
        user = appContext.getUser();
        getDataFromDB();
        //test();
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

    /**
     * 商店主界面
     * */
    private int [] title = {SHOP_MORE_HOT_SALE,SHOP_MORE_BEST_GOODS,SHOP_MORE_FULL_MINUTE
            };
    private void initShopView(){

        //  remove掉 xml布局中的recyclerView
        containerLl.removeViewAt(1);

        //  循环生成3个布局
        for(int i = 0;i<title.length;i++){
            // 更多 布局
            ShopSortDirectMoreLayout moreLayout = new ShopSortDirectMoreLayout(this,title[i]);
            // rc 布局
            LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.recycler_widget,null);
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_widget_rc);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            //list adapter
            List<Object> list = new ArrayList<>();
            ShopDirectAdapter adapter = new ShopDirectAdapter(ShopDirectActivity.this,list,title[i]);

            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
            //  添加布局
            containerLl.addView(moreLayout);
            containerLl.addView(view);
            //请求数据
            initShopData(i,adapter,list,moreLayout);


        }

    }
    /**
     * 根据不同type加载不同数据
     * @param type  类型
     * @param adapter 适配器通知
     * @param list  要加载数据的list
     * @param moreLayout   */
    private void initShopData(int type, final ShopDirectAdapter adapter, final List<Object> list, final ShopSortDirectMoreLayout moreLayout){
        switch (title[type]){
            case SHOP_MORE_HOT_SALE:

                IntrestBuyNet.findBoutique(1, 20, 999L, null, null, null, 1, new HandleSuccess<DirectGoodBean>() {
                    @Override
                    public void success(DirectGoodBean directGoodBean) {
                        if(directGoodBean.getData() != null && directGoodBean.getData().size() != 0){
                            for(int i = 0 ; i < directGoodBean.getData().size() ; i ++){
                                DirectGoodBean.DataBean good = directGoodBean.getData().get(i);
                                list.add(good);
                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            containerLl.removeView(moreLayout);
                        }
                        dismiss();
                    }
                });
                break;
            case SHOP_MORE_BEST_GOODS:
                IntrestBuyNet.findBoutique(1, 5, 999L, null, 1L, null, null, new HandleSuccess<DirectGoodBean>() {
                    @Override
                    public void success(DirectGoodBean directGoodBean) {
                        if(directGoodBean.getData() != null && directGoodBean.getData().size() != 0){
                            for(int i = 0 ; i < directGoodBean.getData().size() ; i ++){
                                DirectGoodBean.DataBean good = directGoodBean.getData().get(i);
                                list.add(good);
                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            containerLl.removeView(moreLayout);
                        }
                        dismiss();
                    }
                });
                break;
            case SHOP_MORE_FULL_MINUTE:
                IntrestBuyNet.findBoutique(1, 5, 999L, null, 2L, null, null, new HandleSuccess<DirectGoodBean>() {
                    @Override
                    public void success(DirectGoodBean directGoodBean) {
                        if(directGoodBean.getData() != null && directGoodBean.getData().size() != 0){
                            for(int i = 0 ; i < directGoodBean.getData().size() ; i ++){
                                DirectGoodBean.DataBean good = directGoodBean.getData().get(i);
                                list.add(good);
                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            containerLl.removeView(moreLayout);
                        }
                        dismiss();
                    }
                });
                break;
        }
    }



    private int adPageNum = 1;      //广告分页
    private List<String> bannerList = new ArrayList<>();        //轮播广告的数据
    /**
     * 初始化banner数据
     * */
    @Override
    protected void initData() {
        ShopNet.shopInfo(999L, new HandleSuccess<GoodsShopInfoBean>() {
            @Override
            public void success(GoodsShopInfoBean goodsShopInfoBean) {
                if(goodsShopInfoBean.getData() != null){
                    if(goodsShopInfoBean.getData().getBackImg1()!= null){
                        bannerList.add(goodsShopInfoBean.getData().getBackImg1());
                    }
                    if(goodsShopInfoBean.getData().getBackImg2()!= null){
                        bannerList.add(goodsShopInfoBean.getData().getBackImg2());
                    }
                    if(goodsShopInfoBean.getData().getBackImg3()!= null){
                        bannerList.add(goodsShopInfoBean.getData().getBackImg3());
                    }
                    if(goodsShopInfoBean.getData().getBackImg4()!= null){
                        bannerList.add(goodsShopInfoBean.getData().getBackImg4());
                    }
                }
                if(bannerList.size()>0){
                    shopBanner.setAdapter(ShopDirectActivity.this);
                    shopBanner.setData(bannerList,bannerList);
                } else {
                    CommonUtil.handlerDefaultBanner(shopBanner);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onHDClick(View v) {

        switch (v.getId()){
            case R.id.shop_main_title_back:
            case R.id.shop_main_title_name_tv:
                finish();
                break;
            case R.id.shop_main_title_search_im:
                UIHelper.togoDirectSearchActivity(this);
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

        Picasso.with(ShopDirectActivity.this).load((String)model).into((ImageView)view);
    }

    /**
     * 轮播图点击事件
     * */
    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

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
