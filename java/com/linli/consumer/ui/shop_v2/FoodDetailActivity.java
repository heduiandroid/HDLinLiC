package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.FoodDetailAdapter2;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;

import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FoodExInfoBean;
import com.linli.consumer.bean.FoodTitleTenantBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.widget.MyViewPager;
import com.linli.consumer.widget.ShopCartDialog;
import com.linli.consumer.widget.ShopDetailBottomLayout;
import com.linli.consumer.widget.ShopDetailTitleLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;

/**
 * Created by tomoyo on 2016/11/28.
 * 美食详情
 *
 * 对应viewpager + recyclerView
 *
 */

public class FoodDetailActivity extends BaseActivity {


    //tablayout等
    private MyViewPager viewPager;
    private FoodDetailAdapter2 pagerAdapter;
    private TabLayout tabLayout;


    private ShopDetailBottomLayout bottomLayout;
    private ShopDetailTitleLayout titleLayout;
    private ShopCartDialog dialog;

    private long shoreId;       //店铺id，通过这个id请求
    private String shopName;    //店铺名称

    private List<String> titlesName = new ArrayList<>();    //分类的名字
    private List<Long> titlesId = new ArrayList<>();        //分类的id

    private DBUtil dbUtil;
    private User user = AppContext.getInstance().getUser();

    private long titleId;           //商家分类id    通过商家分类id，获取要展示的位置
    private long foodId;            //美食id
    private int currentPosition;        //当前位置

    private BigDecimal startPrice = new BigDecimal(0);  //起送费
    private boolean canStartPrice = false;      //是否可以起送


    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_detail;
    }

    @Override
    protected void initView() {

        //TODO 通过type先请求title，再实例化vp
        Intent intent = getIntent();
        shoreId = intent.getLongExtra("shoreId",1);
        shopName = intent.getStringExtra("shopName");
        titleId = intent.getLongExtra("titleId",0);
        foodId = intent.getLongExtra("foodId",0);


        titleLayout = findView(R.id.shop_detail_title_widget1);
        bottomLayout = findView(R.id.shop_detail_bottom_widget);
        titleLayout.setName(shopName,FOOD_MAIN,shoreId,true);

        //初始化底部购物车dialog
        dialog = new ShopCartDialog(this,shoreId,FOOD_MAIN,null,true);
        dialog.setListener(new ShopCartDialog.OnShopCancelListener() {
            @Override
            public void onCancel() {
                updateView();
            }
        });

        //对bottom监听设置
        bottomLayout.setCartClickListener(new ShopDetailBottomLayout.OnCartClickListener() {
            @Override
            public void onCart() {
                dialog.show();
            }
        });
        bottomLayout.setCommitClickListener(new ShopDetailBottomLayout.OnCommitClickListener() {
            @Override
            public void onCommit() {
                if(user != null){
                    UIHelper.togoShopFoodOrderActivity(FoodDetailActivity.this,0,shoreId,FOOD_MAIN,0,null);
                } else {
                    UIHelper.togoLoginActivity(FoodDetailActivity.this);
                }



                //TODO 递交数据，和dialog一样的方法
            }
        });

        dbUtil =  DBUtil.getInstance(this);
        updateView();
    }

    @Override
    protected void initData() {

        //获取title ，名字和id
        FoodNet.foodTitleById(shoreId, new HandleSuccess<FoodTitleTenantBean>() {
            @Override
            public void success(FoodTitleTenantBean foodTitleTenantBean) {

                for(int i = 0;i < foodTitleTenantBean.getData().size();i++){
                    titlesName.add(foodTitleTenantBean.getData().get(i).getGroupname());
                    titlesId.add(foodTitleTenantBean.getData().get(i).getId());
                }
                for(int i = 0 ; i < titlesId.size() ; i ++){
                    if(titlesId.get(i) == titleId){
                        currentPosition = i;
                        break;
                    }
                }
                initVp();
            }
        });
        FoodNet.foodExInfo(shoreId, new HandleSuccess<FoodExInfoBean>() {        //获取店铺附加信息，为了起送费
            @Override
            public void success(FoodExInfoBean foodExInfoBean) {
                if(foodExInfoBean.getData() != null){
                    startPrice = BigDecimal.valueOf(foodExInfoBean.getData().getOpesendstartmoney());
                    canStartPrice = true;
                    //bottomLayout.setCommitState(startPrice.toString(),false);
                }

            }
        });


        //TODO 这里执行网络请求，根据不同type来进行
    }

    /**
     * 初始化vp
     * 在数据请求成功后才能加载vp
     * */
    private void initVp(){
        //初始化viewpager
        viewPager = (MyViewPager)findViewById(R.id.food_detail_main_vp);
        tabLayout = (TabLayout)findViewById(R.id.food_detail_main_tablayout);
        pagerAdapter = new FoodDetailAdapter2(FoodDetailActivity.this, titlesName,titlesId,shoreId,foodId);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(titlesId.size());
        viewPager.setCurrentItem(currentPosition);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onHDClick(View v) {

    }

    /**
     * 这个暴露给适配器进行调用
     * 负责在数据库数据发生改变是调用
     * */
    private List<GoodsBean> goodsList = new ArrayList<>();
    public void updateView(){
        int sum = 0;
        double price = 0;
        goodsList = dbUtil.queryByShopId(shoreId);
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
                price = price + goodsList.get(i).getNumber()*goodsList.get(i).getPrice();
            }
            bottomLayout.setCarNum(sum+"");
            bottomLayout.setPrice(price);

        }else {
            bottomLayout.setDis();
            bottomLayout.setPrice(0);
        }
        goodsList.clear();
    }

    public void dialogView(){
        dialog.updateDialogView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        titleLayout.initCollection();
        user = AppContext.getInstance().getUser();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pagerAdapter.upTextNu();
        updateView();

    }

    public void updateNumTv(){
        pagerAdapter.upTextNu();
    }

}
