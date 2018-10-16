package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopDetailAdapter;
import com.linli.consumer.base.BaseActivity;

import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.mock.MockNet;
import com.linli.consumer.mock.NewsBean;
import com.linli.consumer.widget.MyViewPager;
import com.linli.consumer.widget.ShopCartDialog;
import com.linli.consumer.widget.ShopDetailBottomLayout;
import com.linli.consumer.widget.ShopDetailTitleLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;


/**
 * Created by tomoyo on 2016/11/28.
 * 店铺信息，包含商品的列表，商品的购买
 */
@Deprecated
public class ShopListDetailActivity extends BaseActivity implements BGABanner.Adapter
        ,BGABanner.OnItemClickListener{

    //轮播图
    private BGABanner shopBanner;
    private List<String> bannerList = new ArrayList<>();

    //这个大的布局，滑动布局
    private MyViewPager viewPager;
    private ShopDetailAdapter pagerAdapter;
    private TabLayout tabLayout;

    private List<String> title = new ArrayList<>();

    private String shopName = "";  //TODO 通过intent传入商店名称

    //标题布局，底部结算布局
    private ShopDetailBottomLayout bottomLayout;
    private ShopDetailTitleLayout titleLayout;


    private ShopCartDialog dialog;



    @Override
    protected int getLayoutId() {
        return R.layout.shop_detail_v2;
    }

    @Override
    protected void initView() {


        //初始化banner
        shopBanner = (BGABanner) findViewById(R.id.shop_detail_banner);
        shopBanner.setOnItemClickListener(this);


        //TODO 通过type先请求title，再实例化vp
        title = new ArrayList(Arrays.asList(MockNet.FoodTitle));
        Intent intent = getIntent();
        shopName = intent.getStringExtra("Sort");

        //初始化viewpager
        viewPager = (MyViewPager) findViewById(R.id.shop_detail_vp);
        tabLayout = (TabLayout)findViewById(R.id.shop_detail_tablayout);
        //pagerAdapter = new ShopDetailAdapter(ShopListDetailActivity.this, title,1);
        viewPager.setOffscreenPageLimit(title.size());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //初始化bottom，title
        titleLayout = findView(R.id.shop_detail_title_widget);
        bottomLayout = findView(R.id.shop_detail_bottom_widget);



        //初始化底部购物车dialog


        dialog = new ShopCartDialog(this,1L,1,null,true);

        dialog.setListener(new ShopCartDialog.OnShopCancelListener() {
            @Override
            public void onCancel() {
                washUnCheckedData();
                upView();
            }
        });

        //对bottom监听设置
        bottomLayout.setCartClickListener(new ShopDetailBottomLayout.OnCartClickListener() {
            @Override
            public void onCart() {
                //dialog.setData(list);
                dialog.show();
                dialogView();
            }
        });
        bottomLayout.setCommitClickListener(new ShopDetailBottomLayout.OnCommitClickListener() {
            @Override
            public void onCommit() {

                for(int i = 0;i<list.size();i++){
                    Log.i("WATER","提交==>>>>>"+"种类 = "+list.get(i).getName());
                }

            }
        });

    }


    //购物车最终list，上传，处理 的list
    private List<NewsBean.ResultBean.DataBean > list = new ArrayList<>();

    //TODO 没问题 , 12.4 解决， 循环内重复添加，要考虑到
    /**
     * 修改 添加 移除 商品实体类
     * @param bean 要处理的商品实体类
     * @param isAdd true为 添加·修改 数据 , false为删除数据
     *        要有一个唯一的 key 进行实体类的标识
     * */
    public void handleData(NewsBean.ResultBean.DataBean bean,boolean isAdd){
        NewsBean.ResultBean.DataBean tempBean = new NewsBean.ResultBean.DataBean();
        tempBean.setUrl("wuLiu");
        int count = 0;
        int price = 0;
        if(isAdd){
            //保证可以循环，后面移除这个元素
            //list.add(tempBean);
            //通过对比判断元素是否添加过
            /*for(int i = 0 ;i<list.size();i++){
                if(list.get(i).getUrl().equals(bean.getUrl())){
                    list.remove(i);
                    list.add(bean);
                    list.set(i,bean);
                }else {
                    if(!list.contains(bean)){
                        list.add(bean);
                    }
                }
            }
            list.remove(tempBean);*/

            if(!list.contains(bean)){
                list.add(bean);
            }
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
            bottomLayout.setCarNum(count+"");
            bottomLayout.setPrice(price);
        }else {
            bottomLayout.setDis();
            bottomLayout.setPrice(0);
        }
        //Log.i("WATER","list的总数量 = " + list.size());

    }
    /**
     * 洗掉未被选中的数据
     * 只在dialog取消后调用
     * 中间需要还原数据
     * */
    private void washUnCheckedData(){
        Iterator<NewsBean.ResultBean.DataBean > iter = list.iterator();
        while (iter.hasNext()){
            NewsBean.ResultBean.DataBean bean = iter.next();
            if(!bean.isShow()){
                bean.setNumber(0);
                bean.setShow(false);
                iter.remove();
                Log.i("WATER","洗掉的数据 = >>"+bean.getName()+"=="+bean.getNumber());
            }
        }
        //这里会有问题，会遗漏某些元素
        /*for(int i = 0;i<list.size();i++){
            if(!list.get(i).isShow()){
                list.get(i).setNumber(0);
                list.get(i).setShow(false);
                Log.i("WATER","洗掉的数据 = >>"+list.get(i).getName()+"=="+list.get(i).getNumber());
                list.remove(i);
            }
        }*/
    }
    //TODO 递交表单的时候要看isShow这个属性是否为true

    /**
     * 将dialog暴露给adapter
     * 在dialog中的适配器点击中加载调用更新底部bottom(dialog中的bottom)
     * */
    public void dialogView(){
        dialog.updateDialogView();
    }

    /**
     * 任何时候更新视图
     * 主要是bottomLayout
     * 调用handleData()方法
     * */
    private void upView(){
        NewsBean.ResultBean.DataBean bean = new NewsBean.ResultBean.DataBean();
        bean.setUrl("wuLiu");
        handleData(bean,false);
        //此处刷新所有页面，未做优化
        Log.i("WATER","View upDate");
        pagerAdapter.notifyGoodsUpdate();
    }


    @Override
    protected void initData() {
        MockNet.MockData(new HandleSuccess<NewsBean>() {
            @Override
            public void success(NewsBean newsBean) {
                for (int i = 0;i<3;i++){
                    bannerList.add(newsBean.getResult().getData().get(i).getThumbnail_pic_s());

                }

               shopBanner.setAdapter(ShopListDetailActivity.this);
                shopBanner.setData(bannerList,bannerList);
                dismiss();
            }
        });
    }

    @Override
    public void onHDClick(View v) {

    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        Picasso.with(ShopListDetailActivity.this).load((String)model).into((ImageView)view);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {

        //TODO 记得跳转到商品详情
        Toast.makeText(ShopListDetailActivity.this, "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }
}
