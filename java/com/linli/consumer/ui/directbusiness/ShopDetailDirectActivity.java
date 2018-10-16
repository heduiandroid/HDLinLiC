package com.linli.consumer.ui.directbusiness;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.GoodsDirectAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.CateIdAndNameBean;
import com.linli.consumer.bean.ShopSortBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.mock.MockNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.BackLayoutDirect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tomoyo on 2016/11/28.
 * 店铺分类详情
 *
 * 对应viewpager + recyclerView
 *
 * ————————多个——————————
 */

public class ShopDetailDirectActivity extends BaseActivity {
    private long shopDirectId = 999L;//直营店铺ID
    //返回布局
    private BackLayoutDirect backLayout;

    //tablayout等
    private ViewPager viewPager;
    private GoodsDirectAdapter pagerAdapter;
    private TabLayout tabLayout;

    private List<String> title = new ArrayList<>();
    private ArrayList<CateIdAndNameBean> mainCates = new ArrayList<CateIdAndNameBean>();

    /**
     * 这个type是具体的类别，小的类别，不是大类别
     * */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_detail_v2_direct;
    }

    @Override
    protected void initView() {

        //TODO 通过type先请求title，再实例化vp
        title = new ArrayList(Arrays.asList(MockNet.FoodTitle));

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
        //准备数据 查询美食类别
        getFoodMainCate();
        backLayout.setTitle(getResources().getString(R.string.title_good_direct_str));
    }

    private void getFoodMainCate() {
        CateIdAndNameBean allCate = new CateIdAndNameBean();
        allCate.setId(0l);
        allCate.setName("全部");
        mainCates.add(allCate);
        ShopNet.goodsSortOfShop(shopDirectId+"", new HandleSuccess<ShopSortBean>() {
            @Override
            public void success(ShopSortBean s) {
                if (s.getStatus() == 1 && s.getData().size() > 0){
                    for (int i = 0;i < s.getData().size();i++){
                        CateIdAndNameBean item = new CateIdAndNameBean();
                        item.setId(s.getData().get(i).getId());
                        item.setName(s.getData().get(i).getName());
                        mainCates.add(item);
                    }
                }
                pagerAdapter = new GoodsDirectAdapter(ShopDetailDirectActivity.this, title,mainCates,shopDirectId);
                viewPager.setAdapter(pagerAdapter);
                viewPager.setOffscreenPageLimit(pagerAdapter.cateShopDirect.size());
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
            }
        });
    }

    @Override
    public void onHDClick(View v) {

    }
}
