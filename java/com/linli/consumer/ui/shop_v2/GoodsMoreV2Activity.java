package com.linli.consumer.ui.shop_v2;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.GoodsBySearchedAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.DirectGoodBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.SaleCateBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.widget.CarNumLayout;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GoodsMoreV2Activity extends BaseActivity {
    private CarNumLayout carNumLayout;  //数量小圆点
    private TextView tv_head_name;
    private XRecyclerView xrv_goods;
    private View sort_title;
    private TextView tv_nodata;
    //关于筛选和排序的按钮
    private TextView tv_default,tv_soldout,tv_pricelow;
    private RelativeLayout shop_main_title_cart_rl;

    private RecyclerView.Adapter adapter;
    private ArrayList<DirectGoodBean.DataBean> goods = new ArrayList<>();
    private int cate;//类目 展示不同的优惠政策的商品的类别 1：精品专区（直减） 2：满减专区（满减）
    private int pager = 1;//页码 默认1
    private Long salescategoryId = null;//优惠策略id
    private Integer maxPrice = null;//用于是否按价格排序
    private Integer salesVolume = null;//是否按销量排序

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_more_v2;
    }

    @Override
    protected void initView() {
        carNumLayout = findView(R.id.shop_main_title_num_widget);
        findViewClick(R.id.shop_main_title_back);
        tv_head_name = findViewClick(R.id.shop_main_title_name_tv);
        xrv_goods = findView(R.id.xrv_goods);
        tv_nodata = findView(R.id.tv_nodata);
        tv_default = findViewClick(R.id.tv_default);
        tv_soldout = findViewClick(R.id.tv_soldout);
        tv_pricelow = findViewClick(R.id.tv_pricelow);
        shop_main_title_cart_rl = findViewClick(R.id.shop_main_title_cart_rl);
        sort_title = findView(R.id.sort_title);

        GridLayoutManager manager = new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv_goods.setItemAnimator(new XDefaultItemAnimator());
        xrv_goods.setLayoutManager(manager);
        xrv_goods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (salescategoryId != null) {
                    pager = 1;
                    //list清空 刷新adapter
                    goods.clear();
                    adapter.notifyDataSetChanged();
                    findGoodsByFavourable();
                }
                xrv_goods.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                if (salescategoryId != null) {
                    pager++;
                    findGoodsByFavourable();
                }
                xrv_goods.loadMoreComplete();
            }
        });
        setDefaultTextStyle();
        tv_default.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    protected void initData() {
        cate = getIntent().getIntExtra("cate",0);
        adapter = new GoodsBySearchedAdapter(this,goods);
        xrv_goods.setAdapter(adapter);
        switch (cate){
            case 1:
                tv_head_name.setText("精品专区");
                findCates("精选");
                break;
            case 2:
                tv_head_name.setText("满减专区");
                findCates("满减");
                break;
            case 3:
                tv_head_name.setText("团购");
                findCates("拼团");
                break;
            case 4:
                tv_head_name.setText("0元购");
                findCates("0元购");
                break;
            case 0://隐藏排序一行 默认按销量排序
                tv_head_name.setText("热卖爆款");
                sort_title.setVisibility(View.GONE);
                salesVolume = 1;
                salescategoryId = 1L;
                findGoodsByFavourable();
                break;
        }

    }

    private void findCates(final String str) {
        IntrestBuyNet.findHdMallGoodsSaleCategoryList(1, 50, new HandleSuccess<SaleCateBean>() {
            @Override
            public void success(SaleCateBean s) {
                if (s.getStatus() == 1 && s.getData() != null && s.getData().size() > 0){
                    List<SaleCateBean.DataBean> salecates = s.getData();
                    for (int i=0;i<salecates.size();i++){
                        if (salecates.get(i).getName().contains(str)){
                            salescategoryId = salecates.get(i).getId();
                        }
                    }
                    if (salescategoryId != null){
                        findGoodsByFavourable();
                    }else {
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void findGoodsByFavourable() {
        IntrestBuyNet.findBoutique(pager, 20, 999L, null, salescategoryId, maxPrice, salesVolume, new HandleSuccess<DirectGoodBean>() {
            @Override
            public void success(DirectGoodBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    List<DirectGoodBean.DataBean> templelist = s.getData();
                    for (int i=0;i<templelist.size();i++){
                        goods.add(templelist.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                dismiss();
                if (goods == null || goods.size() == 0){
                    tv_nodata.setVisibility(View.VISIBLE);
                }
                dismissSimDialog();
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_title_back:
            case R.id.shop_main_title_name_tv:
                finish();
                break;
            case R.id.tv_default:
                filterGoods(1);
                break;
            case R.id.tv_soldout:
                filterGoods(2);
                break;
            case R.id.tv_pricelow:
                filterGoods(3);
                break;
            case R.id.shop_main_title_cart_rl:
                UIHelper.togoCartActivity(this);
            default:
                break;
        }
    }
    private void filterGoods(int tag) {
        showSimpleDialog();
        pager = 1;
        goods.clear();
        adapter.notifyDataSetChanged();
        salesVolume = null;
        setDefaultTextStyle();
        switch (tag){
            case 1:
                maxPrice = null;
                tv_default.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                maxPrice = null;
                tv_soldout.setTextColor(getResources().getColor(R.color.black));
                salesVolume = 1;
                break;
            case 3:
                tv_pricelow.setTextColor(getResources().getColor(R.color.black));
                if (maxPrice != null){
                    switch (maxPrice){
                        case 1:
                            maxPrice = 2;
                            break;
                        case 2:
                            maxPrice = 1;
                            break;
                    }
                }else {
                    maxPrice = 2;
                }
                break;
            default:
                break;
        }
        findGoodsByFavourable();
    }
    private void setDefaultTextStyle() {
        tv_default.setTextColor(getResources().getColor(R.color.gray));
        tv_soldout.setTextColor(getResources().getColor(R.color.gray));
        tv_pricelow.setTextColor(getResources().getColor(R.color.gray));
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataFromDB();
    }
    private DBUtil dbUtil =  DBUtil.getInstance(this);

    /**
     * 从数据库获取购物车信息
     *
     * 两处调用
     * 初始化数据    {@linkplain #initView()}
     * 跳转后同步数据  {@linkplain #onPause()}
     * */
    private void getDataFromDB(){
        List<GoodsBean> goodsList = dbUtil.queryAll();
        int sum = 0;
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
            }
            carNumLayout.setVisibility(View.VISIBLE);
            carNumLayout.setMyNum(sum+"");
        }else {
            carNumLayout.setVisibility(View.INVISIBLE);
        }
    }
}
