package com.linli.consumer.ui.shop_v2;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.CouponsSortAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.CateIdAndNameBean;
import com.linli.consumer.mock.MockNet;
import com.linli.consumer.widget.BackLayoutNormal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.linli.consumer.utils.TablayoutLineWrapContentUtil.setIndicator;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_FOOD_REDBAG;
/**
 * Created by tomoyo on 2016/11/28.
 * 红包列表
 *
 * 对应viewpager + recyclerView
 *
 * ————————多个——————————
 */

public class MyCouponRedBagActivity extends BaseActivity {
    //返回布局
    private BackLayoutNormal backLayout;

    //tablayout等
    private ViewPager viewPager;
    private CouponsSortAdapter pagerAdapter;
    private TabLayout tabLayout;

    private List<String> title = new ArrayList<>();
    private ArrayList<CateIdAndNameBean> mainCates = new ArrayList<CateIdAndNameBean>();

    /**
     * 这个type是具体的类别，小的类别，不是大类别
     * */
    private int TYPE;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mycoupon_redbag;
    }

    @Override
    protected void initView() {

        //TODO 通过type先请求title，再实例化vp
        title = new ArrayList(Arrays.asList(MockNet.FoodTitle));
        TYPE = SHOP_FOOD_REDBAG;

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
            case SHOP_FOOD_REDBAG:
                title = getResources().getString(R.string.myredbags);
                //准备数据 查询美食类别
                getMainCate();
                break;
        }
        backLayout.setTitle(title);
    }

    private void getMainCate() {
//        CateIdAndNameBean item1 = new CateIdAndNameBean();
//        item1.setId(1L);
//        item1.setName(getResources().getString(R.string.redbags_unused));
        CateIdAndNameBean item2 = new CateIdAndNameBean();
        item2.setId(2L);
        item2.setName(getResources().getString(R.string.redbags_used));
//        mainCates.add(item1);
        mainCates.add(item2);
        pagerAdapter = new CouponsSortAdapter(MyCouponRedBagActivity.this, title,TYPE,mainCates);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.titleStrings.size());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout, 50, 50);
            }
        });
    }

    @Override
    public void onHDClick(View v) {

    }
}
