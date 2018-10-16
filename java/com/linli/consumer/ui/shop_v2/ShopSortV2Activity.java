package com.linli.consumer.ui.shop_v2;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.GoodsMoreAdapter;
import com.linli.consumer.adapter.shop_v2.NormalGoodsBean;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.MallGoods;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.bean.ShopListBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_HOTSALE;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MORE_MAIN_QUALITY;

public class ShopSortV2Activity extends BaseActivity {
    private int sort;
    private TextView tv_head_name,tv_nodata;
    private XRecyclerView xrv_goods;
    private RecyclerView.Adapter adapter;
    private City city = appContext.getCity();
    private List<MallGoodsVo> list = new ArrayList<>();
    private int pager = 1;//页码默认为1

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_sort_vv2;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        tv_head_name = findViewClick(R.id.tv_head_name);
        tv_nodata = findView(R.id.tv_nodata);
        xrv_goods = findView(R.id.xrv_goods);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        xrv_goods.setLayoutManager(gridLayoutManager);
        xrv_goods.setItemAnimator(new XDefaultItemAnimator());
        xrv_goods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Log.i("test","刷新数据");
                pager = 1;
                list.clear();
                adapter.notifyDataSetChanged();
                requestGoodsBySort();
                xrv_goods.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                Log.i("test","加载更多");
                pager++;
                requestGoodsBySort();
                xrv_goods.loadMoreComplete();
            }
        });
    }
    @Override
    protected void initData() {
        sort = getIntent().getIntExtra("Sort",0);
        adapter = new GoodsMoreAdapter(this,list);
        xrv_goods.setAdapter(adapter);
        requestGoodsBySort();
    }
    private void requestGoodsBySort() {
        tv_nodata.setVisibility(View.GONE);
        switch (sort){
            case SHOP_MORE_MAIN_QUALITY:
                tv_head_name.setText("精选商品");
                initQualityGoods();
                break;
            case SHOP_MORE_MAIN_HOTSALE:
                tv_head_name.setText("每日热销");
                initHotSaleGoods();
                break;
            default:
                break;
        }
    }
    /*
        查询精品专区商品
         */
    private void initQualityGoods() {
        IntrestBuyNet.findRecommendList(pager, 20, city.getLongitude(), city.getLatitude(), 3, new HandleSuccess<NormalGoodsBean>() {
            @Override
            public void success(NormalGoodsBean s) {
                if (s.getData() != null && s.getData().size() > 0){
                    for (int i = 0;i<s.getData().size();i++){
                        //转换一下对象用于显示
                        NormalGoodsBean.DataBean item = s.getData().get(i);
                        MallGoodsVo mallGoodsVo = new MallGoodsVo();
                        mallGoodsVo.setStoreName("");
                        MallGoods mallgood = new MallGoods();
                        mallgood.setId(item.getId());
                        mallgood.setName(item.getName());
                        mallgood.setPrimaryImage(item.getPrimaryImage());
                        mallgood.setMinprice(item.getMinprice());

                        mallGoodsVo.setMallGoods(mallgood);
                        list.add(mallGoodsVo);
                    }
                    adapter.notifyDataSetChanged();
                }
                dismiss();
                if (list == null || list.size() == 0){
                    if (pager == 1) {
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
    /*
    查询每日热销商品
     */
    private void initHotSaleGoods() {
        ShopNet.shopRecommend(1, 0, 3, city.getLongitude(), city.getLatitude(), pager, 20, new HandleSuccess<ShopListBean>() {
            @Override
            public void success(ShopListBean shopListBean) {
                if(shopListBean.getData() != null && shopListBean.getData().size() != 0){
                    for(int i = 0 ; i < shopListBean.getData().size() ; i ++){
                        for(int j = 0 ; j < shopListBean.getData().get(i).getVoList().size() ;  j ++){
                            MallGoodsVo mallGoodsVo = shopListBean.getData().get(i).getVoList().get(j);
                            mallGoodsVo.setStoreName(shopListBean.getData().get(i).getName());
                            list.add(mallGoodsVo);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                dismiss();
                if (list == null || list.size() == 0){
                    if (pager == 1) {
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }
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

            default:
                break;
        }
    }
}
