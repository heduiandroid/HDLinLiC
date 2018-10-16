package com.linli.consumer.ui.directbusiness;

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
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopDetailDirectV2Adapter;
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
import com.linli.consumer.widget.CarNumLayout;
import com.linli.consumer.widget.ShopCartDialog;
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

public class ShopDetailDirectV2Activity extends BaseActivity implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener{


    //这个大的布局，滑动布局
    private ViewPager viewPager;
    private ShopDetailDirectV2Adapter pagerAdapter;
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
    private ImageView iv_have_new;
    private AppBarLayout appBarLayout;


    private ShopCartDialog dialog;
    private DBUtil dbUtil;
    private User user = AppContext.getInstance().getUser();

    private City city = AppContext.getInstance().getCity();
    private boolean isFromCart = false;

    private BigDecimal startPrice = new BigDecimal(0);  //起送费
    private boolean canStartPrice = false;      //是否可以起送
    private AppContext appContext;

    @Override
    protected int getLayoutId() {
        return R.layout.shop_detail_direct_v2;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        type = intent.getIntExtra("Sort",1);
        shopId = intent.getLongExtra("shopId",1);
        shopName = intent.getStringExtra("shopName");
        room = intent.getStringExtra("room");
        isFromCart = intent.getBooleanExtra("isFromCart",false);



        //初始化bottom，title
        findViewClick(R.id.iv_back);
        TextView tv_head = findViewClick(R.id.tv_head_name);
        tv_head.setText("精选专区");
        findViewClick(R.id.rl_direct_cart);
        iv_have_new = findView(R.id.iv_have_new);
        appBarLayout = findView(R.id.shop_detail_title_widget_appbar);

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
        }else {
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
                    dismiss();
                }
            });
        } else if(type == SHOP_MAIN){
            ShopNet.shopInfo(shopId, new HandleSuccess<GoodsShopInfoBean>() {
                @Override
                public void success(GoodsShopInfoBean goodsShopInfoBean) {
                    dismiss();
                }
            });
        }else if(type == SERVICE_MAIN) {
            FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
                @Override
                public void success(ServiceStoreBean s) {
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
                        Toast.makeText(ShopDetailDirectV2Activity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ShopNet.shopExInfo(shopId, new HandleSuccess<ShopExtraInfoBean>() {
                @Override
                public void success(ShopExtraInfoBean shopExtraInfoBean) {
                    if (shopExtraInfoBean.getData()!=null){
                        startPrice = BigDecimal.valueOf(shopExtraInfoBean.getData().getBegingive());
                    }else {
                        Toast.makeText(ShopDetailDirectV2Activity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ShopDetailDirectV2Activity.this,"店铺维护中，请稍后再试",Toast.LENGTH_SHORT).show();
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
        }

    }

    /**
     * 这个暴露给适配器进行调用
     * 负责在数据库数据发生改变是调用
     * */
    private List<GoodsBean> goodsList = new ArrayList<>();
    public void updateView(){
        int sum = 0;
        double price = 0;
        //goodsList = dbUtil.queryByShopId(shopId);
        goodsList.addAll(dbUtil.queryByShopId(shopId));
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
                price = price + goodsList.get(i).getNumber()*goodsList.get(i).getPrice();
            }
            iv_have_new.setVisibility(View.VISIBLE);
        }else {
            iv_have_new.setVisibility(View.GONE);
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

    public void updateAllView(){
        int sum = 0;
        double price = 0;
        //goodsList = dbUtil.queryByShopId(shopId);
        goodsList.addAll(dbUtil.queryByShopId(shopId));
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
                price = price + goodsList.get(i).getNumber()*goodsList.get(i).getPrice();
            }
            iv_have_new.setVisibility(View.VISIBLE);
        }else {
            iv_have_new.setVisibility(View.GONE);
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
        viewPager = (ViewPager) findViewById(R.id.shop_detail_vp);
        tabLayout = (TabLayout)findViewById(R.id.shop_detail_tablayout);
        pagerAdapter = new ShopDetailDirectV2Adapter(ShopDetailDirectV2Activity.this, titlesName,titlesId,shopId,shopName,type);
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
        dialog = new ShopCartDialog(ShopDetailDirectV2Activity.this,shopId,type,null,true);
        if(isFromCart){
            dialog.show();
        }
        dialog.setListener(new ShopCartDialog.OnShopCancelListener() {
            @Override
            public void onCancel() {
                pagerAdapter.notifyGoodsUpdate();
                updateAllView();
            }
        });
        updateAllView();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.rl_direct_cart:
                UIHelper.togoDirectShopCartActivity(this);
                break;
        }
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
        Picasso.with(ShopDetailDirectV2Activity.this).load((String)model).into((ImageView)view);
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
        //updateView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        user = AppContext.getInstance().getUser();
        if(pagerAdapter != null){
            Log.i("test","onRestart++++++++++");
            updateAllView();

        }
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
