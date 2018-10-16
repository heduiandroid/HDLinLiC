package com.linli.consumer.ui.main;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_product,iv_service,iv_food;
    private TextView tv_product,tv_service,tv_food;
    private EditText et_search;
    private LinearLayout ll_shopping_search,ll_services_search,ll_foods_search,ll_contacts_search;
    private int searchType;//2商城  3服务  1美食 （默认商城）
    private String searchText=null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        searchType = getIntent().getIntExtra("searchtype",2);//（默认商城）

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(et_search.getText())){
                        showToast("请输入商品名称");
                    }else {
                        searchText = et_search.getText().toString();
                        UIHelper.togoSearchResultActivity(SearchActivity.this,searchType,searchText);
                    }
                    return true;
                }
                return false;
            }
        });
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_head_right:
                if (TextUtils.isEmpty(et_search.getText().toString().trim())){
                    Toast.makeText(this,"请输入搜索关键字",Toast.LENGTH_SHORT).show();
                }else {
                    searchText = et_search.getText().toString();
                    UIHelper.togoSearchResultActivity(this,searchType,searchText);
                }
                break;
            case R.id.ll_shopping_search:
                clearBackGround();
                searchType = 2;
                et_search.setHint("输入商品名称");
                iv_product.setImageResource(R.mipmap.search_shopping_light);
                tv_product.setTextColor(getResources().getColor(R.color.orange));
                break;
            case R.id.ll_services_search:
                clearBackGround();
                searchType = 3;
                et_search.setHint("输入服务内容");
                iv_service.setImageResource(R.mipmap.search_services_light);
                tv_service.setTextColor(getResources().getColor(R.color.orange));
                break;
            case R.id.ll_foods_search:
                clearBackGround();
                searchType = 1;
                et_search.setHint("输入美食名称");
                iv_food.setImageResource(R.mipmap.search_foods_light);
                tv_food.setTextColor(getResources().getColor(R.color.orange));
                break;
            case R.id.ll_contacts_search:
                clearBackGround();
                searchType = 4;
                ll_contacts_search.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_light_green));
                break;
            default:
                break;
        }
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        et_search = (EditText) findViewById(R.id.et_search);
        findViewById(R.id.tv_head_right).setOnClickListener(this);
        ll_shopping_search = (LinearLayout) findViewById(R.id.ll_shopping_search);
        ll_services_search = (LinearLayout) findViewById(R.id.ll_services_search);
        ll_foods_search = (LinearLayout) findViewById(R.id.ll_foods_search);
        ll_contacts_search = (LinearLayout) findViewById(R.id.ll_contacts_search);
        tv_product = (TextView) findViewById(R.id.tv_product);
        tv_service = (TextView) findViewById(R.id.tv_service);
        tv_food = (TextView) findViewById(R.id.tv_food);
        iv_product = (ImageView) findViewById(R.id.iv_product);
        iv_service = (ImageView) findViewById(R.id.iv_service);
        iv_food = (ImageView) findViewById(R.id.iv_food);
        ll_shopping_search.setOnClickListener(this);
        ll_services_search.setOnClickListener(this);
        ll_foods_search.setOnClickListener(this);
        ll_contacts_search.setOnClickListener(this);
        switch (searchType){
            case 1:
                clearBackGround();
                et_search.setHint("输入美食名称");
                iv_food.setImageResource(R.mipmap.search_foods_light);
                tv_food.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 2:
                clearBackGround();
                et_search.setHint("输入商品名称");
                iv_product.setImageResource(R.mipmap.search_shopping_light);
                tv_product.setTextColor(getResources().getColor(R.color.orange));
                break;
            case 3:
                clearBackGround();
                et_search.setHint("输入服务内容");
                iv_service.setImageResource(R.mipmap.search_services_light);
                tv_service.setTextColor(getResources().getColor(R.color.orange));
                break;
            default:
                break;
        }

    }

    private void clearBackGround() {
        iv_product.setImageResource(R.mipmap.search_shopping);
        iv_service.setImageResource(R.mipmap.search_services);
        iv_food.setImageResource(R.mipmap.search_foods);
        tv_service.setTextColor(getResources().getColor(R.color.gray));
        tv_product.setTextColor(getResources().getColor(R.color.gray));
        tv_food.setTextColor(getResources().getColor(R.color.gray));
//        ll_contacts_search.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
