package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.ConcernedShopsAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.ConcernedList;
import com.linli.consumer.bean.UpdateLeaguer;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Shop;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class MyConcernedListActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView lv_concerned;
    private TextView tv_nodata;
    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private ConcernedShopsAdapter adapter;
//    private SwipeRefreshLayout srl_concerned;
//    private LinearLayout footerview;
    private AppContext appContext;
    private User user;
//    private int pager = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_concerned_list;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        adapter = new ConcernedShopsAdapter(shops,MyConcernedListActivity.this);
        lv_concerned.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        request_concerend_shops();
    }

    private void request_concerend_shops() {
        IntrestBuyNet.leaguer(user.getId(), 0,500,1,new HandleSuccess<ConcernedList>() {
            @Override
            public void success(ConcernedList s) {
                if (s.getStatus() == 1){
                    List<ConcernedList.DataBean> concernedShops = s.getData();
                    if (concernedShops != null && concernedShops.size() > 0){
                        for (int i = 0;i < concernedShops.size();i++){
                            Shop shop = new Shop();
                            shop.setId(concernedShops.get(i).getStoreId());
                            shop.setCollectionId(concernedShops.get(i).getId());
                            Date dt = new Date(concernedShops.get(i).getCreateTime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            shop.setCreateTime(format.format(dt));
                            if (concernedShops.get(i).getHdFoodStore().getName() != null){
                                ConcernedList.DataBean.HdFoodStoreBean item = concernedShops.get(i).getHdFoodStore();
                                shop.setShopName(item.getName());
                                shop.setLogoPath(item.getLogoImg());
                                shop.setLevel(item.getCreditLevel());
                                shop.setShopAddr(item.getAddress());
                                shop.setCategory(item.getCategoryType());
                            }else if (concernedShops.get(i).getHdMallStore() != null){
                                ConcernedList.DataBean.HdMallStoreBean item = concernedShops.get(i).getHdMallStore();
                                shop.setShopName(item.getName());
                                shop.setLogoPath(item.getLogoImg());
                                shop.setLevel(item.getCreditLevel());
                                shop.setShopAddr(item.getAddress());
                                shop.setCategory(item.getCategoryType());
                            }
                            shops.add(shop);
                        }
                    }
                    if (shops.size() > 0){
                        adapter.notifyDataSetChanged();
                    }else {
                        lv_concerned.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }else if (s.getStatus() == 2){
                    lv_concerned.setVisibility(View.GONE);
                    tv_nodata.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(MyConcernedListActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void init() {
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("关注商家");
        tv_nodata = findView(R.id.tv_nodata);
        lv_concerned = findView(R.id.lv_concerned);
        lv_concerned.setOnItemClickListener(this);
        lv_concerned.setOnItemLongClickListener(this);
//        lv_concerned.setOnScrollListener(this);
//        srl_concerned = findView(R.id.srl_concerned);
//        srl_concerned.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
//        srl_concerned.setSize(SwipeRefreshLayout.DEFAULT);
//        srl_concerned.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
//        srl_concerned.setProgressViewEndTarget(true, 100);
//        srl_concerned.setOnRefreshListener(this);
//        footerview = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (shops.get(position).getCategory()){
            case 1:
                UIHelper.togoShopDetailActivity(this,shops.get(position).getId(),shops.get(position).getShopName(),SHOP_MAIN);
                break;
            case 2:
                UIHelper.togoShopDetailActivity(this,shops.get(position).getId(),shops.get(position).getShopName(),FOOD_MAIN);
                break;
            case 3:
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
        AlertDialog.Builder builder2=new AlertDialog.Builder(MyConcernedListActivity.this);
        builder2.setTitle("提示");
        builder2.setMessage("确认取消关注吗？");
        builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    request_update_status(position);
            }
        });
        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder2.create();
        builder2.show();
        return true;
    }

    private void request_update_status(final int position) {
        IntrestBuyNet.leaguerUpdate(shops.get(position).getCollectionId(), 1, new HandleSuccess<UpdateLeaguer>() {
            @Override
            public void success(UpdateLeaguer s) {
                if (s.getStatus() == 1){
                    Toast.makeText(MyConcernedListActivity.this,"已取消关注",Toast.LENGTH_SHORT).show();
                    shops.remove(position);
                    if (shops.size() > 0){
                        adapter.notifyDataSetChanged();
                    }else {
                        lv_concerned.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(MyConcernedListActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        switch (scrollState){
//            case SCROLL_STATE_IDLE:
//                if (view.getLastVisiblePosition() == (view.getCount()-1)){
//                    request_concerend_shops();
//                }
//        }
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//    }

//    @Override
//    public void onRefresh() {
//        lv_concerned.removeFooterView(footerview);
//        pager = 1;
//        shops.clear();
//        if (adapter != null){
//            adapter.notifyDataSetChanged();
//        }
//        request_concerend_shops();
//    }
}
