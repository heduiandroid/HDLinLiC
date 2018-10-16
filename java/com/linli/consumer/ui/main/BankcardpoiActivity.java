package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.BankcardSearchAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FindNoteBanks;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.util.ArrayList;
import java.util.List;

public class BankcardpoiActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private EditText et_card_name, et_bank_name_a;
    private ListView lv_et_card_name;
    private BankcardSearchAdapter bankcardSearchAdapter;
    public List<FindNoteBanks.DataBean> data;
    private List<FindNoteBanks.DataBean> newItems;
    private TextView tv_head_name;
    public static final int SHOW_MAP = 0;
    private static final int SHOW_SEARCH_RESULT = 1;
    private int type;
    private TextView poi_name;
    private int pager = 1;
    private int numPerPage = 15;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bankcardpoi;
    }

    @Override
    protected void initView() {
        this.mContext = this;
        findViewById(R.id.iv_back).setOnClickListener(this);
        tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setOnClickListener(this);
        et_card_name = (EditText) findViewById(R.id.et_card_name);
        et_card_name.setOnClickListener(this);
        et_bank_name_a = (EditText) findViewById(R.id.et_bank_name_a);
        lv_et_card_name = (ListView) findViewById(R.id.lv_et_card_name);
        lv_et_card_name.setOnItemClickListener(this);
        lv_et_card_name.setOnScrollListener(this);
        poi_name = (TextView) findViewById(R.id.poi_name);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);  // 1    2
        switch (type) {
            case 1:
                tv_head_name.setText("搜索开户行");
                poi_name.setText("开户行");
                IntrestBuyNet.findNoteBanks("", new HandleSuccess<FindNoteBanks>() {
                    @Override
                    public void success(FindNoteBanks findserviceShopid) {
                        if (findserviceShopid.getStatus() == 1) {
                            if (data == null) {
                                data = new ArrayList<FindNoteBanks.DataBean>();
                            }
                            data.clear();
                            if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                                for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                    FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                    data.add(date);
                                }
                                updateCityPoiListAdapter();
                            } else {
                                if (data != null) {
                                    data.clear();
                                }
                                showMapOrSearch(SHOW_MAP);
                            }
                            dismiss();
                        } else {
                            dismiss();
                            Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 2:
                poi_name.setText("开户支行");
                tv_head_name.setText("搜索开户支行");
                IntrestBuyNet.findBankBranchs("", numPerPage, pager, new HandleSuccess<FindNoteBanks>() {
                    @Override
                    public void success(FindNoteBanks findserviceShopid) {
                        if (findserviceShopid.getStatus() == 1) {
                            if (data == null) {
                                data = new ArrayList<FindNoteBanks.DataBean>();
                            }
                            newItems = new ArrayList<FindNoteBanks.DataBean>();
                            if (pager == 1) {
                                data.clear();
                            }
                            if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                                for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                    FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                    newItems.add(date);
                                    data.add(date);
                                }
                                if (pager == 1) {
                                    if (data.size() > 0) {
                                        updateCityPoiListAdapter();
                                        pager++;
                                    } else {
                                        //查所有
                                        ///不可能没有数据
                                    }
                                } else {
                                    if (newItems.size() > 0) {
                                        newItems.clear();
                                        updateCityPoiListAdapter();
                                        pager++;
                                    } else {
                                        if (data.size() > numPerPage) {
                                            Toast.makeText(BankcardpoiActivity.this, "没有了~", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else if (pager == 1) {
                                if (data != null) {
                                    data.clear();
                                }
                                showMapOrSearch(SHOW_MAP);
                            } else {
                                //不刷新
                            }
                        } else {
//                            dialog.dismiss();
                            Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 3:
                tv_head_name.setText("搜索省份");
                poi_name.setText("省份");
                IntrestBuyNet.findProvinces("", new HandleSuccess<FindNoteBanks>() {
                    @Override
                    public void success(FindNoteBanks findserviceShopid) {
                        if (findserviceShopid.getStatus() == 1) {
                            if (data == null) {
                                data = new ArrayList<FindNoteBanks.DataBean>();
                            }
                            data.clear();
                            if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                                for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                    FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                    data.add(date);
                                }
                                updateCityPoiListAdapter();
                            } else {
                                if (data != null) {
                                    data.clear();
                                }
                                showMapOrSearch(SHOW_MAP);
                            }
                        } else {
//                            dialog.dismiss();
                            Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
        et_card_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int start, int before,
                                      int count) {
                if (cs.toString().trim().length() >= 0) {
                    getDate();
                } else {
                    if (data != null) {
                        data.clear();
                    }
                    dismiss();
                    showMapOrSearch(SHOW_MAP);
                    hideSoftinput(mContext);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.et_card_name:
                if (et_card_name.getText().toString().trim().length() > 0) {
                    //请求数据
                    getDate();
                }
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type == 1) {
            setResult(200, new Intent().putExtra("bank", data.get(position).getBank()).putExtra("id", data.get(position).getId()));
            BankcardpoiActivity.this.finish();
        } else if (type == 2) {
            Log.v("wang", data.get(position).getId() + "");
            setResult(201, new Intent().putExtra("banks", data.get(position).getBank()).putExtra("id", data.get(position).getId()));
            BankcardpoiActivity.this.finish();
        } else if (type == 3) {
            setResult(202, new Intent().putExtra("provinces", data.get(position).getName()).putExtra("id", data.get(position).getId()));
            BankcardpoiActivity.this.finish();
        }
        if (data != null) {
            data.clear();
        }
        showMapOrSearch(SHOW_MAP);
        hideSoftinput(mContext);
    }

    private void getDate() {
        if (type == 1) {
            IntrestBuyNet.findNoteBanks(et_card_name.getText().toString().trim(), new HandleSuccess<FindNoteBanks>() {
                @Override
                public void success(FindNoteBanks findserviceShopid) {
                    if (findserviceShopid.getStatus() == 1) {
                        if (data == null) {
                            data = new ArrayList<FindNoteBanks.DataBean>();
                        }
                        data.clear();
                        if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                            for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                data.add(date);
                            }
                            updateCityPoiListAdapter();
                        } else {
                            //搜不到
                            if (data != null) {
                                data.clear();
                            }
                            showMapOrSearch(SHOW_MAP);
                        }
                        dismiss();
                    } else {
                        dismiss();
//                        dialog.dismiss();
                        Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (type == 2) {
            pager = 1;
            IntrestBuyNet.findBankBranchs(et_card_name.getText().toString().trim(), numPerPage, pager, new HandleSuccess<FindNoteBanks>() {
                @Override
                public void success(FindNoteBanks findserviceShopid) {
                    if (findserviceShopid.getStatus() == 1) {
                        if (data == null) {
                            data = new ArrayList<FindNoteBanks.DataBean>();
                        }
                        newItems = new ArrayList<FindNoteBanks.DataBean>();
                        if (pager == 1) {
                            data.clear();
                        }
                        if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                            for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                newItems.add(date);
                                data.add(date);
                            }
                            if (pager == 1) {
                                if (data.size() > 0) {
                                    updateCityPoiListAdapter();
                                    pager++;
                                } else {
                                    ///不可能没有数据
                                }
                            } else {
                                if (newItems.size() > 0) {
                                    newItems.clear();
                                    updateCityPoiListAdapter();
                                    pager++;
                                } else {
                                    if (data.size() > numPerPage) {
                                        Toast.makeText(BankcardpoiActivity.this, "没有了~", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } else if (pager == 1) {
                            if (data != null) {
                                data.clear();
                            }
                            showMapOrSearch(SHOW_MAP);
                        } else {
                            //不处理
                            //不可能没有数据
                        }
                    } else {
                        Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (type == 3) {
            IntrestBuyNet.findProvinces(et_card_name.getText().toString().trim(), new HandleSuccess<FindNoteBanks>() {
                @Override
                public void success(FindNoteBanks findserviceShopid) {
                    if (findserviceShopid.getStatus() == 1) {
                        if (data == null) {
                            data = new ArrayList<FindNoteBanks.DataBean>();
                        }
                        data.clear();
                        if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                            for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                data.add(date);
                            }
                            updateCityPoiListAdapter();
                        } else {
                            if (data != null) {
                                data.clear();
                            }
                            showMapOrSearch(SHOW_MAP);
                        }
                    } else {
//                        dialog.dismiss();
                        Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param
     */
    private void hideSoftinput(Context mContext) {
        InputMethodManager manager = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(et_card_name.getWindowToken(), 0);
        }
    }

    // 显示地图界面亦或搜索结果界面
    private void showMapOrSearch(int index) {
        if (index == SHOW_SEARCH_RESULT) {
//            llMLMain.setVisibility(View.GONE);
            lv_et_card_name.setVisibility(View.VISIBLE);
        } else {
            lv_et_card_name.setVisibility(View.GONE);
//            llMLMain.setVisibility(View.VISIBLE);
            if (data != null) {
                data.clear();
            }
        }
    }

    // 刷新当前城市兴趣地点列表界面的adapter
    private void updateCityPoiListAdapter() {
        if (bankcardSearchAdapter == null) {
            bankcardSearchAdapter = new BankcardSearchAdapter(mContext, data);
            lv_et_card_name.setAdapter(bankcardSearchAdapter);
        } else {
            bankcardSearchAdapter.notifyDataSetChanged();
        }
        showMapOrSearch(SHOW_SEARCH_RESULT);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                    if (type == 2) {
                        IntrestBuyNet.findBankBranchs(et_card_name.getText().toString().trim(), numPerPage, pager, new HandleSuccess<FindNoteBanks>() {
                            @Override
                            public void success(FindNoteBanks findserviceShopid) {
                                if (findserviceShopid.getStatus() == 1) {
                                    if (data == null) {
                                        data = new ArrayList<FindNoteBanks.DataBean>();
                                    }
                                    newItems = new ArrayList<FindNoteBanks.DataBean>();
                                    if (pager == 1) {
                                        data.clear();
                                    }
                                    if (findserviceShopid.getData() != null && findserviceShopid.getData().size() > 0) {
                                        for (int i = 0; i < findserviceShopid.getData().size(); i++) {
                                            FindNoteBanks.DataBean date = findserviceShopid.getData().get(i);
                                            newItems.add(date);
                                            data.add(date);
                                        }
                                        if (pager == 1) {
                                            if (data.size() > 0) {
                                                updateCityPoiListAdapter();
                                                pager++;
                                            } else {
                                                ///不可能没有数据
                                            }
                                        } else {
                                            if (newItems.size() > 0) {
                                                newItems.clear();
                                                updateCityPoiListAdapter();
                                                pager++;
                                            } else {
                                                if (data.size() > numPerPage) {
                                                    Toast.makeText(BankcardpoiActivity.this, "没有了~", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    } else if (pager == 1) {
                                        //不可能没有数据
                                    } else {
                                        //不可能没有数据
                                    }
                                } else {
//                                    dialog.dismiss();
                                    Toast.makeText(BankcardpoiActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
