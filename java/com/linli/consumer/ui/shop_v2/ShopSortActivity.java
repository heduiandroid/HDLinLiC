package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopSortAdapter;
import com.linli.consumer.base.BaseActivity;

import com.linli.consumer.bean.CateIdAndNameBean;
import com.linli.consumer.bean.FoodMainCateBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.mock.MockNet;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.widget.BackLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_FINDFOOD;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN_RECIPE;
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
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_ALL;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN_COUPON;

/**
 * Created by tomoyo on 2016/11/28.
 * 店铺分类详情
 *
 * 对应viewpager + recyclerView
 *
 * ————————多个——————————
 */

public class ShopSortActivity extends BaseActivity {
    private long foodMainCateId = 14828947671413L;//美食父类别ID
    //返回布局
    private BackLayout backLayout;

    //tablayout等
    private ViewPager viewPager;
    private ShopSortAdapter pagerAdapter;
    private TabLayout tabLayout;

    private List<String> title = new ArrayList<>();
    private ArrayList<CateIdAndNameBean> mainCates = new ArrayList<CateIdAndNameBean>();

    /**
     * 这个type是具体的类别，小的类别，不是大类别
     * */
    private int TYPE;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_sort_v2;
    }

    @Override
    protected void initView() {

        //TODO 通过type先请求title，再实例化vp
        title = new ArrayList(Arrays.asList(MockNet.FoodTitle));
        Intent intent = getIntent();
        TYPE = intent.getIntExtra("Sort",1);

        viewPager = (ViewPager)findViewById(R.id.shop_sort_main_vp);
        tabLayout = (TabLayout)findViewById(R.id.shop_sort_main_tablayout);

        backLayout = findView(R.id.shop_sort_main_back);
        fillData();
       // viewPager.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {
        //TODO 这里执行网络请求，根据不同type来进行

    }

    /**
     * 填充title布局
     * */
    @Override
    protected void fillData() {
        String title = "";
        switch (TYPE){
            case FOOD_MAIN_FINDFOOD:
                title = getResources().getString(R.string.food_main_sort_findfood_str);
                //准备数据 查询美食类别
                getFoodMainCate();
                break;
            case FOOD_MAIN_RECIPE:
                title = getResources().getString(R.string.food_main_sort_recipe_str);
                backLayout.setToSearch();
                getOtherMainCate();
                break;

            case SHOP_MAIN_COUPON:
                title = getResources().getString(R.string.shop_main_sort_coupon_str);
                getOtherMainCate();
                break;
            case SHOP_MAIN_ALL:
                title = getResources().getString(R.string.shop_main_sort_all_str);
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE1:
                title = getResources().getString(R.string.service_cate1);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE2:
                title = getResources().getString(R.string.service_cate2);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE3:
                title = getResources().getString(R.string.service_cate3);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE4:
                title = getResources().getString(R.string.service_cate4);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE5:
                title = getResources().getString(R.string.service_cate5);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE6:
                title = getResources().getString(R.string.service_cate6);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE7:
                title = getResources().getString(R.string.service_cate7);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE8:
                title = getResources().getString(R.string.service_cate8);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE9:
                title = getResources().getString(R.string.service_cate9);;
                getOtherMainCate();
                break;
            case SERVICE_MAIN_FINDSERVICE10:
                title = getResources().getString(R.string.service_cate10);;
                getOtherMainCate();
                break;
        }
        backLayout.setTitle(title);
    }

    private void getOtherMainCate() {
        pagerAdapter = new ShopSortAdapter(ShopSortActivity.this, title,TYPE,mainCates);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.titleStrs.size());
        tabLayout.setupWithViewPager(viewPager);
    }

    private void getFoodMainCate() {
        CateIdAndNameBean allCate = new CateIdAndNameBean();
        allCate.setId(0l);
        allCate.setName("全部");
        mainCates.add(allCate);
        FoodNet.findMainCatesByParentId(foodMainCateId, new HandleSuccess<FoodMainCateBean>() {
            @Override
            public void success(FoodMainCateBean s) {
                if (s.getStatus() == 1 && s.getData().size() > 0){
                    for (int i = 0;i < s.getData().size();i++){
                        CateIdAndNameBean item = new CateIdAndNameBean();
                        item.setId(s.getData().get(i).getId());
                        item.setName(s.getData().get(i).getName());
                        mainCates.add(item);
                    }
                }
                pagerAdapter = new ShopSortAdapter(ShopSortActivity.this, title,TYPE,mainCates);
                viewPager.setAdapter(pagerAdapter);
                viewPager.setOffscreenPageLimit(pagerAdapter.titleStrs.size());
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    @Override
    public void onHDClick(View v) {

    }
}
