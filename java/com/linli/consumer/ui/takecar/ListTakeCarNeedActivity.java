package com.linli.consumer.ui.takecar;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.TakeCarNeedAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.TakeCarNeedListBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;

public class ListTakeCarNeedActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout srl_taxiuserneed;
    private ListView lv_taxiuserneed;
    private TextView tv_nodata;

    private AppContext appContext;
    private User user;
    private ArrayList<TakeCarNeedListBean.DataBean> takeCarNeedList = new ArrayList<>();
    private ArrayList<TakeCarNeedListBean.DataBean> newItems = new ArrayList<>();
    private TakeCarNeedAdapter adapter;
    private int pager = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_take_car_need;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView header = findViewClick(R.id.tv_head_name);
        header.setText("我的行程");
        lv_taxiuserneed = findView(R.id.lv_taxiuserneed);
        lv_taxiuserneed.setOnItemClickListener(this);
        tv_nodata = findView(R.id.tv_nodata);
        lv_taxiuserneed.setOnScrollListener(this);
        srl_taxiuserneed = findView(R.id.srl_taxiuserneed);
        srl_taxiuserneed.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        srl_taxiuserneed.setSize(SwipeRefreshLayout.DEFAULT);
        srl_taxiuserneed.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        srl_taxiuserneed.setProgressViewEndTarget(true, 100);
        srl_taxiuserneed.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        adapter = new TakeCarNeedAdapter(takeCarNeedList,this);
        lv_taxiuserneed.setAdapter(adapter);
        dismiss();
        request_list_takecar_need();
    }
    /*
    查询叫车记录
     */
    private void request_list_takecar_need() {
        if (pager == 1){
            showDialog();
            takeCarNeedList.clear();
        }
        IntrestBuyNet.findListTakeCarNeed(user.getId(), pager, 10, new HandleSuccess<TakeCarNeedListBean>() {
            @Override
            public void success(TakeCarNeedListBean s) {
                newItems.clear();
                if (s.getStatus() == 1 && s.getData() != null && s.getData().size()>0){
                    for (int i = 0;i < s.getData().size();i++){
                        TakeCarNeedListBean.DataBean item = s.getData().get(i);
                        newItems.add(item);
                        takeCarNeedList.add(item);
                    }
                    if (pager == 1){
                        if (takeCarNeedList.size()>0) {
                            //if  have datas
                            srl_taxiuserneed.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                            pager++;
                        }else {
                            //if have no datas
                            srl_taxiuserneed.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                        }
                        dismiss();
                        srl_taxiuserneed.setRefreshing(false);
                    }else {
                        if (newItems.size()>0){
                            adapter.notifyDataSetChanged();
                            pager++;
                        }
                    }
                }else {
                    if (pager==1){
                        dismiss();
                        srl_taxiuserneed.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                        srl_taxiuserneed.setRefreshing(false);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        UIHelper.togoTakeCarWaitDriverAcceptWithParam(this,takeCarNeedList.get(i).getId());
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount()-1)){
                    request_list_takecar_need();
                }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onRefresh() {
        pager = 1;
        request_list_takecar_need();
    }
}
