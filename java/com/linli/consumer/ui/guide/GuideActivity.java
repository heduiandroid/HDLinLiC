package com.linli.consumer.ui.guide;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;

import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by tomoyo on 2017/3/29.
 */

public class GuideActivity extends BaseActivity {

    private Button enterBt;
    private BGABanner pagerBga;
    @Override
    protected int getLayoutId() {
        return R.layout.guide;
    }

    @Override
    protected void initView() {
        dismiss();
        enterBt = findViewClick(R.id.btn_guide_enter);
        pagerBga = findView(R.id.banner_guide_background);
        pagerBga.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == pagerBga.getItemCount() - 2) {
                    ViewCompat.setAlpha(enterBt, positionOffset);

                    if (positionOffset > 0.5f) {
                        enterBt.setVisibility(View.VISIBLE);
                    } else {
                        enterBt.setVisibility(View.GONE);
                    }
                } else if (position == pagerBga.getItemCount() - 1) {
                    enterBt.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(enterBt, 1.0f);
                } else {
                    enterBt.setVisibility(View.GONE);
                }
            }
        });
        processLogic();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onHDClick(View v) {
        if(v.getId() == R.id.btn_guide_enter ){  //点击跳过 或 进入 ，跳转到主界面
            UIHelper.togoYZXIndexActivity(this);
            finish();
        }
    }

    /**
     * 初始化引导页的数据
     * */
    private void processLogic() {
        pagerBga.setOverScrollMode(View.OVER_SCROLL_NEVER);
        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
        pagerBga.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
        pagerBga.setData(Arrays.asList(R.mipmap.ic_guide_1, R.mipmap.ic_guide_2, R.mipmap.ic_guide_3), null);

    }
}
