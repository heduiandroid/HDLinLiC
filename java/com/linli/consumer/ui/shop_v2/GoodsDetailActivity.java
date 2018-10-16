package com.linli.consumer.ui.shop_v2;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoyo on 2016/12/1.
 */
@Deprecated
public class GoodsDetailActivity extends BaseActivity {

    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private List<ImageView> list = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_goods_v2;
    }

    @Override
    protected void initView() {
        viewPager = findView(R.id.shop_sort_main_vp);
        adapter = new MyPagerAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("WATER_int","positon = " +position +" offset = " +positionOffset +" pix = " +positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                Log.i("WATER_ed","p = "+ position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("WATER_ch","state = "+ state);
            }
        });
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.setPageMargin(-120);

    }

    @Override
    protected void initData() {

        for(int i = 0;i<5;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.bg_blue);
            list.add(imageView);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onHDClick(View v) {

    }

    private class MyPagerAdapter extends PagerAdapter {
        List<ImageView> list;

        public MyPagerAdapter(List<ImageView> list) {
            super();
            this.list = list;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

    }
}
