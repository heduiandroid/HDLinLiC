package com.linli.consumer.ui.main;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.BankcarddetilsAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.BankDetials;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;
import java.util.List;

public class EWBankdetailActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private ImageView iv_back;
    private TextView tv_head_name;
    private ListView lv_bank_card_detials;
    private TextView tv_nodata;
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout refreshLayout;//swipe refresh
    //    private SwpipeListViewOnScrollListener swpipeListViewOnScrollListener;
    private LinearLayout footerview;
    private int pager = 1;
    private int numPerPage = 20;
    private List<BankDetials.DataBean> data = new ArrayList<BankDetials.DataBean>();
    private List<BankDetials.DataBean> newItems;
    private BankcarddetilsAdapter serviceManagesAdapter;

    private int roleType = 5; //  2 商城  3餐饮  4 服务
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    lv_bank_card_detials.removeFooterView(footerview);
                    pager = 1;
                    data.clear();
                    getdata();
                    refreshLayout.setRefreshing(false);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ewbankdetail;
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setOnClickListener(this);
        tv_head_name.setText("余额明细");
        lv_bank_card_detials = (ListView) findViewById(R.id.lv_bank_card_detials);
        tv_nodata = (TextView) findViewById(R.id.tv_nodata);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_index_datas);
        refreshLayout.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        refreshLayout.setProgressViewEndTarget(true, 100);
        refreshLayout.setOnRefreshListener(this);
        lv_bank_card_detials.setOnItemClickListener(this);
        footerview = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_loadmore, null);
        lv_bank_card_detials.setOnScrollListener(this);
    }

    @Override
    protected void initData() {
        dismiss();
//        switch (user.getCategory_type()) {
//            case 1:
//                roleType = 2;
//                break;
//            case 2:
//                roleType = 3;
//                break;
//            case 3:
//                roleType = 4;
//                break;
//        }
        getdata();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;

        }
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessage(REFRESH_COMPLETE);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                    getdata();
                }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        FindserviceShopid.DataBean dataBean = (FindserviceShopid.DataBean) parent.getAdapter().getItem(position);
//        startActivityForResult(new Intent(this, CommerciaDescriptionActvity.class).putExtra("dataBean", dataBean).putExtra("position", position), 101);
    }

    private void getdata() {
        if (serviceManagesAdapter != null) {
            serviceManagesAdapter.notifyDataSetChanged();
        }
        if (pager == 1) {
            showDialog();
        }
        showDialog();
        IntrestBuyNet.findRecords(1, user.getId(), numPerPage, pager, new HandleSuccess<BankDetials>() {
            @Override
            public void success(BankDetials findserviceShopid) {
                if (findserviceShopid.getStatus() == 1) {
                    if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                        newItems = new ArrayList<BankDetials.DataBean>();
                        newItems.clear();//
                        for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                            BankDetials.DataBean date = findserviceShopid.getData().get(i);
                            newItems.add(date);
                            data.add(date);
                        }
                        if (pager == 1) {
                            if (data.size() > 0) {
                                lv_bank_card_detials.setVisibility(View.VISIBLE);
                                tv_nodata.setVisibility(View.GONE);
                                if (data.size() > 19) {
                                    lv_bank_card_detials.addFooterView(footerview, null, false);
                                }
                                serviceManagesAdapter = new BankcarddetilsAdapter(data, EWBankdetailActivity.this);
                                lv_bank_card_detials.setAdapter(serviceManagesAdapter);
                                pager++;
                            } else {
                                lv_bank_card_detials.setVisibility(View.GONE);
                                tv_nodata.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (newItems.size() > 0) {
                                newItems.clear();
                                serviceManagesAdapter.notifyDataSetChanged();
                                pager++;
                            } else {
                                if (data.size() > numPerPage) {
                                    lv_bank_card_detials.removeFooterView(footerview);
                                }
                            }
                        }
                        dismiss();
                    } else if (pager == 1) {
                        lv_bank_card_detials.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                        dismiss();
                    } else {
                        lv_bank_card_detials.removeFooterView(footerview);
                        dismiss();
                    }
                } else {
                    dismiss();
                    Toast.makeText(appContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
