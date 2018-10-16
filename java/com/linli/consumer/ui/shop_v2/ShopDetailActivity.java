package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopDetailAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FoodExInfoBean;
import com.linli.consumer.bean.FoodShopInfoBean;
import com.linli.consumer.bean.FoodTitleTenantBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.ShopExtraInfoBean;
import com.linli.consumer.bean.ShopSortBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;

import com.linli.consumer.domain.User;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.AppBarStateChangeListener;
import com.linli.consumer.widget.CarNumLayout;
import com.linli.consumer.widget.MyViewPager;
import com.linli.consumer.widget.ShopCartDialog;
import com.linli.consumer.widget.ShopDetailBottomLayout;
import com.linli.consumer.widget.ShopDetailTitleLayout;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/11/28.
 * 店铺信息，包含商品的列表，商品的购买
 */

public class ShopDetailActivity extends BaseActivity implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener{

    //轮播图
    private BGABanner shopBanner;
    private List<String> bannerList = new ArrayList<>();

    //这个大的布局，滑动布局
    private MyViewPager viewPager;
    private ShopDetailAdapter pagerAdapter;
    private TabLayout tabLayout;
    //获取到的提交按钮
    //店铺内部分类的    名字 , id
    private List<String> titlesName = new ArrayList<>();
    private List<Long > titlesId = new ArrayList<>();

    private long shopId ;
    private String shopName = "";  //TODO 通过intent传入商店名称
    private String room = null;
    private int type = 0;           //判断美食还是商城

    //标题布局，底部结算布局
    private ShopDetailBottomLayout bottomLayout;
    private ShopDetailTitleLayout titleLayout;
    private AppBarLayout appBarLayout;


    private ShopCartDialog dialog;
    private DBUtil dbUtil;
    private User user = AppContext.getInstance().getUser();

    private City city = AppContext.getInstance().getCity();
    private boolean isFromCart = false;

    private BigDecimal startPrice = new BigDecimal(0);  //起送费
    private boolean canStartPrice = false;      //是否可以起送
    private AppContext appContext;
    private boolean isopened = true;//是否开店 默认是true


    @Override
    protected int getLayoutId() {
        return R.layout.shop_detail_v2;
    }

    @Override
    protected void initView() {


        //初始化banner
        shopBanner = (BGABanner) findViewById(R.id.shop_detail_banner);
        shopBanner.setOnItemClickListener(this);


        Intent intent = getIntent();
        type = intent.getIntExtra("Sort",1);
        shopId = intent.getLongExtra("shopId",1);
        shopName = intent.getStringExtra("shopName");
        room = intent.getStringExtra("room");
        isFromCart = intent.getBooleanExtra("isFromCart",false);



        //初始化bottom，title
        titleLayout = findView(R.id.shop_detail_title_widget);
        bottomLayout = findView(R.id.shop_detail_bottom_widget);
        numLayout = (CarNumLayout) bottomLayout.findViewById(R.id.shop_detail_bottom_num_widget);
        appBarLayout = findView(R.id.shop_detail_title_widget_appbar);
        titleLayout.setName(shopName,type,shopId,false);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {       //监听title的变化
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                titleLayout.changeTitle(state);
            }
        });

        dbUtil =  DBUtil.getInstance(this);

    }


    //购物车最终list，上传，处理 的list
    @Deprecated
    private List<NewsBean.ResultBean.DataBean > list = new ArrayList<>();
    //TODO 没问题 , 12.4 解决， 循环内重复添加，要考虑到
    /**
     * 修改 添加 移除 商品实体类
     * @param bean 要处理的商品实体类
     * @param isAdd true为 添加·修改 数据 , false为删除数据
     *        要有一个唯一的 key 进行实体类的标识
     * */
    @Deprecated
    public void handleData(NewsBean.ResultBean.DataBean bean,boolean isAdd){
        NewsBean.ResultBean.DataBean tempBean = new NewsBean.ResultBean.DataBean();
        tempBean.setUrl("wuLiu");
        int count = 0;
        int price = 0;
        if(isAdd){
            //保证可以循环，后面移除这个元素
            list.add(tempBean);
            //通过对比判断元素是否添加过
            for(int i = 0 ;i<list.size();i++){
                if(list.get(i).getUrl().equals(bean.getUrl())){
                    list.remove(i);
                    list.add(bean);
                }else {
                    if(!list.contains(bean)){
                        list.add(bean);
                    }
                }
            }
            list.remove(tempBean);
        }else {
            if(list.contains(bean)){
                list.remove(bean);
            }
        }

        //循环得到总数，每次点击事件发生都要重新循环遍历总列队来获取值
        for(int i = 0;i<list.size();i++){
            if(list.get(i).isShow()){
                count = count + list.get(i).getNumber();
                price = price + list.get(i).getNumber()*list.get(i).getPrice();
            }
        }
        //设置视图
        if(count != 0){
            //满多少起送设为visible 并设置金额
            bottomLayout.setSendPrice(true,startPrice.setScale(0,BigDecimal.ROUND_DOWN).toString());
            bottomLayout.setCarNum(count+"");
            bottomLayout.setPrice(price);
        }else {
            bottomLayout.setDis();
            bottomLayout.setPrice(0);
            //满多少起送设为visible 并设置金额
            bottomLayout.setSendPrice(false,startPrice.setScale(0,BigDecimal.ROUND_DOWN).toString());
        }

    }


    private String jude = "http://obqlfysk2.bkt.clouddn.com/0"; //服务器判断字符串
    /**
     * 请求数据
     *
     * 轮播图
     * 店铺分类
     * */
    @Override
    protected void initData() {
        if(type == FOOD_MAIN){
            FoodNet.foodShopInfo(shopId, new HandleSuccess<FoodShopInfoBean>() {
                @Override
                public void success(FoodShopInfoBean foodShopInfoBean) {
                    if(foodShopInfoBean.getData() != null){
                        if (foodShopInfoBean.getData().getOpenStatus()!=0){//!=0就是等于1   0开店  1闭店
                            bottomLayout.setSubmitButtonNotClick();
                            isopened = false;
                        }
                        if(foodShopInfoBean.getData().getBackImg1()!= null && !foodShopInfoBean.getData().getBackImg1().equals(jude) ){
                            bannerList.add(foodShopInfoBean.getData().getBackImg1());
                        }
                        if(foodShopInfoBean.getData().getBackImg2()!= null && !foodShopInfoBean.getData().getBackImg2().equals(jude) ){
                            bannerList.add(foodShopInfoBean.getData().getBackImg2());
                        }
                        if(foodShopInfoBean.getData().getBackImg3()!= null && !foodShopInfoBean.getData().getBackImg3().equals(jude) ){
                            bannerList.add(foodShopInfoBean.getData().getBackImg3());
                        }
                        if(foodShopInfoBean.getData().getBackImg4()!= null && !foodShopInfoBean.getData().getBackImg4().equals(jude) ){
                            bannerList.add(foodShopInfoBean.getData().getBackImg4());
                        }
                    }
                    if(bannerList.size()>0){
                        shopBanner.setAdapter(ShopDetailActivity.this);
                        shopBanner.setData(bannerList,bannerList);
                    } else {
                        CommonUtil.handlerDefaultBanner(shopBanner);
                    }
                    dismiss();
                }
            });
        } else if(type == SHOP_MAIN){
            ShopNet.shopInfo(shopId, new HandleSuccess<GoodsShopInfoBean>() {
                @Override
                public void success(GoodsShopInfoBean goodsShopInfoBean) {
                    if(goodsShopInfoBean.getData() != null){
                        if (goodsShopInfoBean.getData().getId() == 999L) {
                            titleLayout.setName(goodsShopInfoBean.getData().getName(), type, shopId, false);
                        }
                        if (goodsShopInfoBean.getData().getOpenStatus()!=0){//!=0就是等于1   0开店  1闭店
                            bottomLayout.setSubmitButtonNotClick();
                            isopened = false;
                        }
                        if(goodsShopInfoBean.getData().getBackImg1()!= null && !goodsShopInfoBean.getData().getBackImg1().equals(jude)){
                            bannerList.add(goodsShopInfoBean.getData().getBackImg1());
                        }
                        if(goodsShopInfoBean.getData().getBackImg2()!= null && !goodsShopInfoBean.getData().getBackImg2().equals(jude)){
                            bannerList.add(goodsShopInfoBean.getData().getBackImg2());
                        }
                        if(goodsShopInfoBean.getData().getBackImg3()!= null && !goodsShopInfoBean.getData().getBackImg3().equals(jude)){
                            bannerList.add(goodsShopInfoBean.getData().getBackImg3());
                        }
                        if(goodsShopInfoBean.getData().getBackImg4()!= null && !goodsShopInfoBean.getData().getBackImg4().equals(jude)){
                            bannerList.add(goodsShopInfoBean.getData().getBackImg4());
                        }
                    }
                    if(bannerList.size()>0){
                        shopBanner.setAdapter(ShopDetailActivity.this);
                        shopBanner.setData(bannerList,bannerList);
                    } else {
                        CommonUtil.handlerDefaultBanner(shopBanner);
                    }
                    dismiss();
                }
            });
        }else if(type == SERVICE_MAIN) {
            FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
                @Override
                public void success(ServiceStoreBean s) {
                    if(s.getData() != null){
                        if (s.getData().getOpenStatus()!=0){//!=0就是等于1   0开店  1闭店
                            bottomLayout.setSubmitButtonNotClick();
                            isopened = false;
                        }
                        if(s.getData().getBackImg1()!= null && !s.getData().getBackImg1().equals(jude)){
                            bannerList.add(s.getData().getBackImg1());
                        }
                        if(s.getData().getBackImg2()!= null && !s.getData().getBackImg2().equals(jude)){
                            bannerList.add(s.getData().getBackImg2());
                        }
                        if(s.getData().getBackImg3()!= null && !s.getData().getBackImg3().equals(jude)){
                            bannerList.add(s.getData().getBackImg3());
                        }
                        if(s.getData().getBackImg4()!= null && !s.getData().getBackImg4().equals(jude)){
                            bannerList.add(s.getData().getBackImg4());
                        }
                    }
                    if(bannerList.size()>0){
                        shopBanner.setAdapter(ShopDetailActivity.this);
                        shopBanner.setData(bannerList,bannerList);
                    } else {
                        CommonUtil.handlerDefaultBanner(shopBanner);
                    }
                    dismiss();
                }
            });
        }

        //请求title
        //请求到title在初始化 vp
        titlesName.add("全部");
        titlesId.add(Long.decode("0"));
        if(type == SHOP_MAIN){
            ShopNet.goodsSortOfShop(shopId+"", new HandleSuccess<ShopSortBean>() {
                @Override
                public void success(ShopSortBean shopSortBean) {
                    if(shopSortBean.getData() != null &&shopSortBean.getData().size() != 0){
                        for(int i = 0 ; i < shopSortBean.getData().size() ; i++){
                            titlesName.add(shopSortBean.getData().get(i).getName());
                            titlesId.add(shopSortBean.getData().get(i).getId());
                        }
                        initVp();
                    }else {
                        bottomLayout.setVisibility(View.GONE);//分类都没有，商品也没有 显示底部布局也没用
                        Toast.makeText(ShopDetailActivity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ShopNet.shopExInfo(shopId, new HandleSuccess<ShopExtraInfoBean>() {
                @Override
                public void success(ShopExtraInfoBean shopExtraInfoBean) {
                    if (shopExtraInfoBean.getData()!=null){
                        startPrice = BigDecimal.valueOf(shopExtraInfoBean.getData().getBegingive());
                    }else {
                        Toast.makeText(ShopDetailActivity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                    canStartPrice = true;
                    updateView();
                }
            });
        }else if(type == FOOD_MAIN){
            FoodNet.foodTitleById(shopId, new HandleSuccess<FoodTitleTenantBean>() {

                @Override
                public void success(FoodTitleTenantBean foodTitleTenantBean) {
                    if( foodTitleTenantBean.getData() != null &&foodTitleTenantBean.getData().size() != 0){
                        for(int i = 0;i < foodTitleTenantBean.getData().size();i++){
                            titlesName.add(foodTitleTenantBean.getData().get(i).getGroupname());
                            titlesId.add(foodTitleTenantBean.getData().get(i).getId());
                        }
                        initVp();
                    }else {
                        bottomLayout.setVisibility(View.GONE);//分类都没有，商品也没有 显示底部布局也没用
                        Toast.makeText(ShopDetailActivity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            FoodNet.foodExInfo(shopId, new HandleSuccess<FoodExInfoBean>() {        //获取店铺附加信息，为了起送费
                @Override
                public void success(FoodExInfoBean foodExInfoBean) {
                    if(foodExInfoBean.getData() != null){
                        startPrice = BigDecimal.valueOf(foodExInfoBean.getData().getOpesendstartmoney());
                        canStartPrice = true;
                        updateView();
                        //bottomLayout.setCommitState(startPrice.toString(),false);
                    }

                }
            });
        }else if (type == SERVICE_MAIN){
            initVp();
            tabLayout.setVisibility(View.GONE);//服务项暂时没有分类
            bottomLayout.setVisibility(View.GONE);//服务暂不支持购买等系列操作
        }

    }

    /**
     * 这个暴露给适配器进行调用
     * 负责在数据库数据发生改变是调用
     * */
    private List<GoodsBean> goodsList = new ArrayList<>();
    public void showCart(){//弹出购物车
        dialog.show();
    }
    public void updateView(){
        int sum = 0;
        double price = 0;
        //goodsList = dbUtil.queryByShopId(shopId);
        goodsList.addAll(dbUtil.queryByShopId(shopId));
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                Log.i("test",goodsList.get(i).getPrice()+"updateView");
                sum = sum + goodsList.get(i).getNumber();
                price = price + goodsList.get(i).getNumber()*goodsList.get(i).getPrice();
            }
            //满多少起送设为visible 并设置金额
            bottomLayout.setSendPrice(true,startPrice.setScale(0,BigDecimal.ROUND_DOWN).toString());
            bottomLayout.setCarNum(sum+"");
            bottomLayout.setPrice(price);
        }else {
            //满多少起送设为gone
            bottomLayout.setSendPrice(false,startPrice.setScale(0,BigDecimal.ROUND_DOWN).toString());
            bottomLayout.setDis();
            bottomLayout.setPrice(0);
        }
        /**param2 比较总价和起送金额*/
        /*if(type == FOOD_MAIN){
            if(canStartPrice){
                bottomLayout.setCommitState(startPrice.toString(),BigDecimal.valueOf(price).compareTo(startPrice) >= 0);
            }
        }*/
        goodsList.clear();
        //pagerAdapter.notifyGoodsUpdate();
    }
    /*
    不要轻易使用此公共方法 此方法会对比网路数据和本地数据 而且只适用于列表显示 不适用于计算金额
     */
    public void updateAllView(){
        int sum = 0;
        double price = 0d;
        //goodsList = dbUtil.queryByShopId(shopId);
        goodsList.addAll(dbUtil.queryByShopId(shopId));
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
                price = price + goodsList.get(i).getNumber()*goodsList.get(i).getPrice();
                Log.i("test",goodsList.get(i).getPrice()+"");
            }
            //满多少起送设为visible 并设置金额
            bottomLayout.setSendPrice(true,startPrice.setScale(0,BigDecimal.ROUND_DOWN).toString());
            bottomLayout.setCarNum(sum+"");
            bottomLayout.setPrice(price);
        }else {
            //满多少起送设为gone
            bottomLayout.setSendPrice(false,startPrice.setScale(0,BigDecimal.ROUND_DOWN).toString());
            bottomLayout.setDis();
            bottomLayout.setPrice(0);
        }
        /**param2 比较总价和起送金额*/
        /*if(type == FOOD_MAIN){
            if(canStartPrice){
                bottomLayout.setCommitState(startPrice.toString(),BigDecimal.valueOf(price).compareTo(startPrice) >= 0);
            }
        }*/
        goodsList.clear();
        if(pagerAdapter != null){
            pagerAdapter.notifyGoodsUpdate();
        }

    }

    /**
     * 初始化vp
     * 在数据请求成功后才能加载vp
     * */
    private void initVp(){
        //初始化viewpager
        viewPager = (MyViewPager) findViewById(R.id.shop_detail_vp);
        tabLayout = (TabLayout)findViewById(R.id.shop_detail_tablayout);
        pagerAdapter = new ShopDetailAdapter(ShopDetailActivity.this, titlesName,titlesId,shopId,shopName,type);
        viewPager.setOffscreenPageLimit(titlesName.size());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                pagerAdapter.notifyGoodsUpdate();

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //初始化底部购物车dialog                这里放在请求成功后进行设置
        dialog = new ShopCartDialog(ShopDetailActivity.this,shopId,type,room,isopened);
        if(isFromCart){
            dialog.show();
        }
        dialog.setListener(new ShopCartDialog.OnShopCancelListener() {
            @Override
            public void onCancel() {
                pagerAdapter.notifyGoodsUpdate();
                updateView();
            }
        });

        //对bottom监听设置
        bottomLayout.setCartClickListener(new ShopDetailBottomLayout.OnCartClickListener() {
            @Override
            public void onCart() {
                dialog.show();
                dialogView();
            }
        });
        bottomLayout.setCommitClickListener(new ShopDetailBottomLayout.OnCommitClickListener() {
            @Override
            public void onCommit() {
                if(user != null){
                    if(Double.valueOf(bottomLayout.getPrice()) != 0){
                        UIHelper.togoShopFoodOrderActivity(ShopDetailActivity.this,0l,shopId,type,0l,room);
                    } else {
                        Toast.makeText(ShopDetailActivity.this,"请选择商品",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    UIHelper.togoLoginActivity(ShopDetailActivity.this);
                }


                //TODO 递交数据，和dialog一样的方法
            }
        });
        updateView();
    }

    @Override
    public void onHDClick(View v) {

    }

    /**
     * 将dialog暴露给adapter
     * 在dialog中的适配器点击中加载调用更新底部bottom(dialog中的bottom)
     * */
    public void dialogView(){
        dialog.updateDialogView();
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Picasso.with(ShopDetailActivity.this).load((String)model).into((ImageView)view);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

        //TODO 记得跳转到商品详情
//        Toast.makeText(DirectShopDetailActivity.this, "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = AppContext.getInstance().getUser();
        titleLayout.initCollection();
        if(pagerAdapter != null){
            updateAllView();

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        user = AppContext.getInstance().getUser();
    }
    private CarNumLayout numLayout;
    private ViewGroup anim_mask_layout;//动画层
    /**
     * 设置动画
     *
     * @param view1
     * @param startLocation
     */
    public void setAnim(final View view1, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(view1);//把动画小球添加到动画层
        final View viewAnim = addViewToAnimLayout(anim_mask_layout, view1, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标

        numLayout.getLocationInWindow(endLocation);// numLayout是动画结束位置的图标

        // 计算位移
        int endX = endLocation[0] - startLocation[0] - 30;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1] - 40;// 动画位移的y坐标

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, endX, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(true);

        AnimationSet set1 = new AnimationSet(false);
        set1.setFillAfter(false);
        set1.addAnimation(translateAnimationY);
        set1.setDuration(1000);// 动画的执行时间
        viewAnim.startAnimation(set1);
        // 动画监听事件
        set1.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                view1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                view1.setVisibility(View.INVISIBLE);
            }
        });
    }
    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }
    /**
     * @param parent
     * @param view
     * @param location
     * @return
     */
    private View addViewToAnimLayout(final ViewGroup parent, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }
}
