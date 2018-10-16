package com.linli.consumer.ui.shop_v2;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.GoodsByCatesAdapter;
import com.linli.consumer.adapter.GoodsBySearchedAdapter;
import com.linli.consumer.adapter.shop_v2.CatesDirectAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.DirectGoodBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.bean.ShopSortBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class ShopDirectSearchActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private LinearLayout ll_tosearch,ll_search_goods;
    private ListView lv_cates;
    private EditText et_search_text;
    private XRecyclerView rv_goods;
    private TextView tv_nodata_cate;
    private TextView tv_head_name;

    //关于筛选和排序的按钮
    private TextView tv_default,tv_soldout,tv_pricelow;

    private CatesDirectAdapter adapter;
    private ArrayList<ShopSortBean.DataBean> cates = new ArrayList<>();

    private RecyclerView.Adapter adapterRv,adapterSearchedRv;
    private ArrayList<GoodsDetailBean> goods = new ArrayList<>();
    private ArrayList<DirectGoodBean.DataBean> goodsSearched = new ArrayList<>();

    private XRecyclerView xrv_searched_goods;
    private long pager = 1;//页码初始为1
    private long cate;//当前类别id
    private String searchText = null;//用于搜索的文字

    private int pagerSearch = 1;//搜索到的商品列表页码 默认为1
    private Long salescategoryId = null;//活动类目id
    private Integer maxPrice = null;//用于是否按价格排序
    private Integer salesVolume = null;//是否按销量排序


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_direct_search;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("搜索");
        findView(R.id.view_line).setBackgroundColor(getResources().getColor(R.color.white));
        ll_tosearch = findView(R.id.ll_tosearch);
        ll_search_goods = findView(R.id.ll_search_goods);//搜索的商品展示界面 需要判断何时显示此块
        lv_cates = findView(R.id.lv_cates);
        lv_cates.setOnItemClickListener(this);
        rv_goods = findView(R.id.rv_goods);
        xrv_searched_goods = findView(R.id.xrv_searched_goods);
        et_search_text = findView(R.id.et_search_text);
        tv_nodata_cate = findView(R.id.tv_nodata_cate);

        tv_default = findViewClick(R.id.tv_default);
        tv_soldout = findViewClick(R.id.tv_soldout);
        tv_pricelow = findViewClick(R.id.tv_pricelow);
        ll_tosearch.setFocusable(true);
        ll_tosearch.setFocusableInTouchMode(true);
        ll_tosearch.requestFocus();
        ll_tosearch.requestFocusFromTouch();
        GridLayoutManager manager = new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_goods.setItemAnimator(new XDefaultItemAnimator());
        rv_goods.setLayoutManager(manager);
        GridLayoutManager managersearch = new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);
        managersearch.setOrientation(LinearLayoutManager.VERTICAL);
        xrv_searched_goods.setItemAnimator(new XDefaultItemAnimator());
        xrv_searched_goods.setLayoutManager(managersearch);
        rv_goods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                goods.clear();
                adapterRv.notifyDataSetChanged();
                findGoods(cate);
                rv_goods.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                pager ++;
                findGoods(cate);
                rv_goods.loadMoreComplete();
            }
        });
        xrv_searched_goods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pagerSearch = 1;
                goodsSearched.clear();
                adapterSearchedRv.notifyDataSetChanged();
                findSearchedGoods();
                xrv_searched_goods.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                pagerSearch ++;
                findSearchedGoods();
                xrv_searched_goods.loadMoreComplete();
            }
        });

    }


    @Override
    protected void initData() {
        adapter = new CatesDirectAdapter(cates,this);
        lv_cates.setAdapter(adapter);

        adapterRv = new GoodsByCatesAdapter(this,goods);
        rv_goods.setAdapter(adapterRv);

        adapterSearchedRv = new GoodsBySearchedAdapter(this,goodsSearched);
        xrv_searched_goods.setAdapter(adapterSearchedRv);
        findCates();
        et_search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(et_search_text.getText())){
                        showToast("请输入商品名称");
                    }else {
                        searchText = et_search_text.getText().toString();
                        pagerSearch = 1;
                        goodsSearched.clear();
                        adapterSearchedRv.notifyDataSetChanged();
                        setDefaultTextStyle();
                        tv_default.setTextColor(getResources().getColor(R.color.black));
                        findSearchedGoods();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void findSearchedGoods() {
        IntrestBuyNet.findBoutique(pagerSearch, 20, 999L, searchText, salescategoryId, maxPrice,salesVolume, new HandleSuccess<DirectGoodBean>() {
            @Override
            public void success(DirectGoodBean s) {
                if (s.getStatus() == 1 && s.getData() != null && s.getData().size() > 0){
                    for (int i = 0;i<s.getData().size();i++){
                        goodsSearched.add(s.getData().get(i));
                    }
                }
                if (goodsSearched.size() > 0){
                    //隐藏和显示
                    ll_search_goods.setVisibility(View.VISIBLE);
                    tv_head_name.setText("搜索商品");
                    adapterSearchedRv.notifyDataSetChanged();
                    hiddenEdittextInputmethod();
                }else {
                    showToast("抱歉，暂无该商品");
                }
                dismissSimDialog();
            }
        });
    }

    private void findCates() {
        ShopNet.goodsSortOfShop(999L+"", new HandleSuccess<ShopSortBean>() {
            @Override
            public void success(ShopSortBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    for (int i = 0; i < s.getData().size();i++){
                        cates.add(s.getData().get(i));
                    }
                    adapter.notifyDataSetChanged();
                    if (cates != null && cates.size() > 0){
                        cate = cates.get(0).getId();
                        findGoods(cates.get(0).getId());
                    }
                }
            }
        });

    }

    private void findGoods(long cateId) {
        ShopNet.goodsListOfShop2(999L, cateId, pager, 20, new HandleSuccess<GoodsDetailListBean>() {
            @Override
            public void success(GoodsDetailListBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    for (int i=0;i<s.getData().size();i++){
                        goods.add(s.getData().get(i));
                    }
                    adapterRv.notifyDataSetChanged();
                }
                if (goods == null || goods.size() == 0){
                    tv_nodata_cate.setVisibility(View.VISIBLE);
                }else {
                    tv_nodata_cate.setVisibility(View.GONE);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_default:
                filterSearch(1);
                break;
            case R.id.tv_soldout:
                filterSearch(2);
                break;
            case R.id.tv_pricelow:
                filterSearch(3);
                break;
            default:
                break;
        }
    }

    private void filterSearch(int tag) {
        showSimpleDialog();
        pagerSearch = 1;
        goodsSearched.clear();
        adapterSearchedRv.notifyDataSetChanged();
        salesVolume = null;
        setDefaultTextStyle();
        switch (tag){
            case 1:
                tv_default.setTextColor(getResources().getColor(R.color.black));
                maxPrice = null;
                break;
            case 2:
                tv_soldout.setTextColor(getResources().getColor(R.color.black));
                maxPrice = null;
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
        findSearchedGoods();
    }

    private void setDefaultTextStyle() {
        tv_default.setTextColor(getResources().getColor(R.color.gray));
        tv_soldout.setTextColor(getResources().getColor(R.color.gray));
        tv_pricelow.setTextColor(getResources().getColor(R.color.gray));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapter.getSeclection() != position) {
            adapter.setSeclection(position);
            adapter.notifyDataSetChanged();
            //request datas by new params
            cate = cates.get(position).getId();
            pager = 1;
            goods.clear();
            adapterRv.notifyDataSetChanged();
            findGoods(cates.get(position).getId());
        }

    }

    private void hiddenEdittextInputmethod() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_search_text.getWindowToken(),0);
    }
}
