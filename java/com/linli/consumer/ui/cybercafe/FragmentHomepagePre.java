package com.linli.consumer.ui.cybercafe;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.BaseFragment;
import com.linli.consumer.base.UIHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by hasee on 2018/9/29.
 */

public class FragmentHomepagePre extends BaseFragment implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener {
    private BGABanner shopBanner;       //轮播广告
    private List<String> bannerList = new ArrayList<>();        //轮播广告的数据
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage_pre;
    }

    @Override
    protected void initView() {
        TextView tv_title = findView(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.index_name));
        //轮播图初始化
        shopBanner = findView(R.id.shop_main_banner);
        shopBanner.setOnItemClickListener(this);
        findViewClick(R.id.tv_tosanning);
    }

    @Override
    protected void initData() {
        findAdverRecommend();
        dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tosanning:
                UIHelper.togoScanningBuyActivity(this.getActivity(),0);
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    /*
        查询附近推荐的网吧
         */
    private void findAdverRecommend() {

        bannerList.add("http://pic1a.nipic.com/2009-01-07/20091713417344_2.jpg");
        bannerList.add("http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg");
        //再add一个完整对象 用于点击跳转 onitemclick
        shopBanner.setAdapter(this);
        shopBanner.setData(bannerList,bannerList);
    }
    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Picasso.with(this.getActivity()).load((String)model).into((ImageView)view);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

    }

    public FragmentHomepagePre() {
    }
    public static FragmentHomepagePre newInstance() {
        FragmentHomepagePre fragment = new FragmentHomepagePre();
        return fragment;
    }


}
